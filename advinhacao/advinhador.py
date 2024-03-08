#definindo as palavras chave para nosso programa funcionar
p_acertou = "Acertou"
p_maior = "Maior"
p_menor = "Menor"

# Utilizando busca binaria para encontar qual numero o usuario pensou
def busca_binaria(inicio, fim):
    if inicio > fim:
        return -1

    meio = (inicio + fim) / 2
    # primeiro chute
    print("Seu número é %d" % meio)
    resposta = input()
    if (p_acertou == resposta): return meio
    elif (p_menor == resposta): return busca_binaria(inicio, meio - 1)
    else: return busca_binaria(meio + 1, fim)
    

# Pergunta ao usuario o intervalo dos numeros:
print("Bem-vindo ao meu programa! Pense em um número dentro do intervalo que você estabelecer e divirta-se vendo o programa tentando advinha-lo, caso ele acerte, digite Acertou, caso o chute do programa seja maior, digite Maior e caso seja menor, digite Menor")
valor_max = int(input("Digite o valor máximo do intervalo: "))
numero_adivinhado = busca_binaria(0, valor_max)
if (numero_adivinhado == -1): print("O número que você pensou não existe ou não está nesse intervalo\n")
else: print("O número que você pensou é %d" % numero_adivinhado)