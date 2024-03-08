from abc import ABC, abstractmethod

class Funcionario(ABC):
    # definição dos campos do funcionário
    def __init__(self, nome, cpf, salario_base):
        self.nome = nome
        self.cpf = cpf
        self.salario_base = salario_base

    # criação do funcionário, verificando o tipo e o cargo
    def criar_funcionario(funcionarios, cargo, nome, cpf, salario_base):
        #Antes de tudo, validar o CPF
        if (Funcionario.verificaCPF(cpf) == False):
            print("CPF inválido")
            return
        #Verificando se ele é gerente
        elif (cargo == "Gerente"):
            funcionarios.append(Gerente(nome, cpf, salario_base))
        #Verificando se ele é Assistente
        elif (cargo == "Assistente"):
            funcionarios.append(Assistente(nome, cpf, salario_base))
        #Verificando se ele é Vendedor
        elif (cargo == "Vendedor"):
            comissao = int(input("Comissão: "))
            if comissao < 0:
                print("Comissao invalida")
                return
            funcionarios.append(Vendedor(nome, cpf, salario_base, comissao))
        else:
            print("Cargo inválido")
            return

    # cálculo de salário
    @abstractmethod
    def calculaSalario(self):
        pass

    # verifica o CPF
    @staticmethod
    def verificaCPF(possivel_cpf):
        cpf = [int(char) for char in possivel_cpf if char.isdigit()]

        #  Verifica se o CPF tem 11 dígitos
        if len(cpf) != 11:
            return False

        #  Verifica se o CPF tem todos os números iguais, ex: 111.111.111-11
        #  Esses CPFs são considerados inválidos mas passam na validação dos dígitos
        #  Antigo código para referência: if all(cpf[i] == cpf[i+1] for i in range (0, len(cpf)-1))
        if cpf == cpf[::-1]:
            return False

        #  Valida os dois dígitos verificadores
        for i in range(9, 11):
            value = sum((cpf[num] * ((i+1) - num) for num in range(0, i)))
            digit = ((value * 10) % 11) % 10
            if digit != cpf[i]:
                # Retorna True se o CPF for válido e False caso contrário
                return False
        return True

# salário do gerente
class Gerente(Funcionario):
    def calculaSalario(self):
        return 2 * self.salario_base

# salário do assistente
class Assistente(Funcionario):
    def calculaSalario(self):
        return self.salario_base

# salário do vendedor
class Vendedor(Funcionario):
    def __init__(self, nome, cpf, salario_base, comissao):
        super().__init__(nome, cpf, salario_base)
        self.comissao = comissao

    def calculaSalario(self):
        return self.salario_base + self.comissao

# main
class Teste:
    def main(self):
        # usando um for para ler todos os funcionários
        qtd_funcionarios = int(input("Digite a quantidade de funcionários: "))
        funcionarios = []
        total_salarios = 0.00

        # lendo as informações de todos os funcionários
        salario_base = float(input("Salário base da empresa: "))
        for i in range(qtd_funcionarios):
            print("Coloque as seguintes informações do seu funcionário: ")
            cargo = input("Cargo: ")
            nome = input("Nome: ")
            cpf = input("CPF: ")

            # Chamar a função pra definir a classe
            Funcionario.criar_funcionario(funcionarios, cargo, nome, cpf, salario_base)

        #Calculando a folha de pagamento
        for funcionario in funcionarios:
            total_salarios += funcionario.calculaSalario()

        print("Folha Salarial: R$", total_salarios)

# Chamando a main
teste = Teste()
teste.main()