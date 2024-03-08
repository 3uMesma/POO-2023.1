# Folha de pagamento
A função do programa é imprimir a folha salarial da empresa basaedo no cargo dos funcionários. Nessa empresa o salário é calculado da seguinte forma:
- o gerente recebe duas vezes o salário_base,
- o assistente recebe o salário_base e
- o vendedor recebe o salário_base mais uma comissão
O valor do salário base e o percentual de comissão é definido pelo usuário. 

## Implementação
Através da heirarquia de classes, foram representados diferentes tipos de funcionários de um escritório que tem os seguintes cargos: gerente, assistente, vendedor. Para a implementação, foi usadaa uma classe abastrata Funcionario que declara o método abstrato double calculaSalario(). Esta classe também define os seguintes atributos:
- nome (tipo String)
- CPF (tipo String)
- salarioBase (tipo double)

E o método estático:
- verificaCPF(String)

que verifica se um CPF é válido ou não.

