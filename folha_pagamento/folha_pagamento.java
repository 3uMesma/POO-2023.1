import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Funcionario{

    // Criando variáveis para o funcionário
    protected String nome;
    protected String cpf;
    protected double salario_base;
    
    // Método construtor da classe funcionários
    public Funcionario(String nome_, String cpf_, double salario_base_){
        this.nome = nome_;
        this.cpf = cpf_;
        this.salario_base = salario_base_;
    }

    // Cálculo de salário
    public abstract double calculaSalario();

    // Verifica o CPF (DEVMEDIA)
    public static boolean verificarCPF(String CPF){

        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") ||
        CPF.equals("11111111111") ||
        CPF.equals("22222222222") || CPF.equals("33333333333") ||
        CPF.equals("44444444444") || CPF.equals("55555555555") ||
        CPF.equals("66666666666") || CPF.equals("77777777777") ||
        CPF.equals("88888888888") || CPF.equals("99999999999") ||
        (CPF.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return(true);
            else return(false);

        } catch (InputMismatchException erro) {
            return(false);
        }
    }
}

// Criando Gerente / Calculando salário
class Gerente extends Funcionario {

    // Construtor da classe Gerente
    public Gerente(String nome, String cpf, double salario_base){
        super(nome, cpf, salario_base);
    }

    // Calculando o sálario do Gerente
    public double calculaSalario() {
        return 2 * salario_base;
    }
}

// Criando Assistente / Calculando salário
class Assistente extends Funcionario {

    // Construtor da classe Assistente
    public Assistente(String nome, String cpf, double salario_base){
        super(nome, cpf, salario_base);
    }

    // Calculando salário do Assistente
    public double calculaSalario() {
        return salario_base;
    }
}

// Criando Vendedor / Calculando salário
class Vendedor extends Funcionario {
    // Variáveis próprios do Vendedor
    private double comissao;

    // Construtor da classe Vendedor
    public Vendedor(String nome, String cpf, double salario_base, double comissao){
        super(nome, cpf, salario_base);
        this.comissao = comissao;
    }

    // Calculando salário do Vendedor 
    public double calculaSalario() {
        return salario_base + comissao;
    }
}


class folha_pagamento{
    public static void main(String[] args) {

        // Variáveis
        String cargo;
        String nome;
        String cpf;
        double salario_base = 0;
        double comissao = 0;
        double folha_pagamento = 0;
        boolean cpf_valido;

        // usando o scanner
        Scanner scan = new Scanner(System.in);

        // Criando array dinâmico de funcionários
        List<Funcionario> funcionarios = new ArrayList<>();

        // Pegar valor de funcionários que vão ser cadastrados
        System.out.print("Digite a quantidade de funcionários: ");
        int qtd_funcionarios = scan.nextInt();

        // Pegando o salário base da empresa
        System.out.print("Salário base: ");
        salario_base = scan.nextDouble();
        scan.nextLine();
        // lendo as informações de todos os funcionários
        for (int i = 0; i<qtd_funcionarios; i++){

            System.out.println("Coloque as seguintes informações do seu funcionário: ");

            System.out.print("Cargo: ");
            cargo = scan.nextLine().trim();

            System.out.print("Nome: ");
            nome = scan.nextLine().trim();

            System.out.print("CPF: ");
            cpf = scan.nextLine().trim();

            // Verifivcando se o cpf é válido
            cpf_valido = Funcionario.verificarCPF(cpf);

            // Verificando se é um vendedor
            if(cargo.equalsIgnoreCase("Vendedor") && cpf_valido){
                System.out.print("Comissão: ");
                comissao = scan.nextDouble();
                
                // Tratando possíveis erros do scan
                if(scan.hasNextLine()){
                    scan.nextLine();
                }

                //Validando a comissão
                if (comissao < 0){

                    System.out.println("Comissao invalida");   

                }else{

                    // Adcionando vendedor no vetor de funcionários
                    funcionarios.add(new Vendedor(nome, cpf,salario_base, comissao));

                }

            // Verificando se é um Gerente
            } else if (cargo.equalsIgnoreCase("Gerente") && cpf_valido){

                //Adcionando Gerente no vetor de funcionários
                funcionarios.add(new Gerente(nome, cpf, salario_base));

            // Verificando se é um Assistente
            } else if(cargo.equalsIgnoreCase("Assistente") && cpf_valido) {

                // Adicionando Assistente no vetor de funcionários
                funcionarios.add(new Assistente(nome, cpf, salario_base));

            } else { // Caso não exista o cargo ou o cpf seja inválido
                System.out.println("Cargo do funcionário não encontrado ou cpf inválido!");
            }
        }

        // Calcular folha de pagamento
        for(int i = 0; i<funcionarios.size(); i++){
            folha_pagamento += funcionarios.get(i).calculaSalario();
        }

        System.out.println("Valor da folha de pagamento: "+folha_pagamento);

        scan.close();
    }
}