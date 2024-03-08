import datetime
import random

# Fazendo algumas pre-definições para o programa rodar
saldo_total = 200 #valor estabelecido na questão´ 
max_valor_carta = 13 #valor maximo que uma carta pode atingir
max_naipes = 4  
cartas_por_jogada = 5
max_rodadas_mudanca = 2 #quantidade máxima de rodadas em que o jogador pode mudar as cartas, não está incluso as cartas iniciais

import datetime

# Classe que realiza as funcionalidades básicas do jogo: exibir, sortear, trocar as cartas
class Cartas(object): 
    def valor_carta(self):
        # Chama a função aleatória para definir um valor para a carta
        valor_carta = random.randint(2, max_valor_carta) #quantidade máxima de valores, começa no dois, pois A não vale 1 e sim 14
        return valor_carta
            
    def naipe_carta(self):
        # Chama a função aleatória para definir um naipe para a carta
        naipe_carta = random.randint(1, max_naipes) #quantidade máxima de valores
        return naipe_carta
    
    def valida_cartas(self, vetor_cartas):
        # Verifica se o vetor de cartas é válido, exemplo, se existem cartas repetidas
        # A função retorna 0 se a jogada pode ser realizada e 1 caso contrário
        # A função primeiro vai comparar os números das cartas e só caso forem iguais, compara os naipes
        for i in range (cartas_por_jogada - 1):
            # o j começa a partir de i + 1, para o j não se comparar com ele mesmo
            for j in range (i + 1, cartas_por_jogada, 1):
                if (vetor_cartas[i][0] == vetor_cartas[j][0]):
                    #compara naipe
                    if (vetor_cartas[i][1] == vetor_cartas[j][1]):
                        return False
        return True
    
    def mostra_naipe(self, vetor_cartas, i):
        #Função separada para imprimir o naipe pois estamos usando códigos do unicode
        if (vetor_cartas[i][1] == 1): # naipe 0 = espadas
            print('\u2660', end=" ")
        elif (vetor_cartas[i][1] == 2): # naipe 1 = paus
            print('\u2663', end=" ")
        elif (vetor_cartas[i][1] == 3): # naipe 2 = copas
            print('\u2764', end=" ")
        else:                        # naipe 3 = ouros
            print('\u2666', end=" ")
    
    def formata_numero(self, numero):
        #Função para deixar o número formatado, ex.: 11 = J, 12 = Q, 13 = K e 14 = A
        if (numero == 10):
            print("J", end=" ")
        elif (numero == 11):
            print("Q", end=" ")
        elif (numero == 12):
            print("K", end=" ")
        elif (numero == 13):
            print("A", end=" ")
        else:
            print(numero, end=" ")


    
    def mostra_cartas(self, vetor_cartas):
        #Impirme as cartas para o usuário ver
        partida = Cartas()
        print("+-----+      +-----+      +-----+      +-----+      +-----+")
        print("|     |      |     |      |     |      |     |      |     |")
        for i in range (cartas_por_jogada):
            print("|", end=" ")
            partida.formata_numero(vetor_cartas[i][0])
            partida.mostra_naipe(vetor_cartas, i)
            print("|     ", end=" ")
        print("")
        print("|     |      |     |      |     |      |     |      |     |")
        print("+-----+      +-----+      +-----+      +-----+      +-----+")
        print("  (1)          (2)          (3)          (4)          (5)  ")

    
    def sorteia_cartas(self, vetor_mudanca, vetor_cartas):
        # O parâmetro vetor mudança representa quais cartas o usuário quer mudar, se o elemento for false, ela não será mudada, caso contrário, será. 
        # Para quardar a informação das cartas, fizemos um vetor de tuplas, onde vetor[i][0] é referente ao valor numérico da carta e vetor[i][1] é 
        # referente ao naipe da carta
        partida = Cartas()
        for i in range(cartas_por_jogada):
            #se o elemento do vetor for diferente a zero, ele será mudado
            if (vetor_mudanca[i] != 0):
                valor = partida.valor_carta()
                naipe = partida.naipe_carta()
                vetor_cartas[i] = (valor, naipe)
            #se o elemento for zero, aí ele não deve ser mudado

        #Após criar o vetor, verifica se essa rodada é valida:
        if (partida.valida_cartas(vetor_cartas)):
            #Se estiver tudo okay, manda imprimir
            partida.mostra_cartas(vetor_cartas)
        else:
            #Caso contrário, repete o processo
            partida.sorteia_cartas(vetor_mudanca, vetor_cartas)
    
    def ler_cartas_a_trocar(self):
        # lê a linha inteira como uma string
        numeros = input("Digite os números das cartas que você deseja trocar, separados por espaço, se não quiser nada basta digitar enter: ")
        # Remove espaços extras e separa os números em uma lista
        numeros = numeros.strip().split()
        # Converte os números de strings para inteiros
        numeros = list(map(int, numeros))
        return numeros
    
    def booleano_cartas(self, vetor_cartas_mudanca):
        vetor_booleano = []
        #lê os inteiros, que seriam as cartas que o usuário quer trocar e transforma isso em vetor, composto por 0 e 1, indicado se a carta será trocada ou não
        for i in range (cartas_por_jogada):
            #verifica se a carta quer ser mudada
            if i+1 in vetor_cartas_mudanca:
                # caso sim o valor será true
                vetor_booleano.append(True)
            else:
                #caso contrário, será false
                vetor_booleano.append(False)
        return vetor_booleano

# Classe que contabiliza quanto o jagador ganhou com a aposta     
class Aposta(object):
    # As funções com o nome das combinações retornam apenas true or false, e, a partir daí, chamamos outra função que faz os cálculos de dinheiro acumulado

    def ganho_aposta(self, vetor_cartas, aposta):
        # essa função é a função principal da classe, que vai chamar as outras, verificando na ordem de qual gera maior ganho, 
        # se o conjunto de cinco cartas atende a alguma combinação, caso sim, já retorna o valor do ganho
        combinacoes = Aposta()
        if (combinacoes.royal_straight_flush(vetor_cartas)):
            return aposta * 200
        if (combinacoes.straight_flush(vetor_cartas)):
            return aposta * 100
        if (combinacoes.quadra(vetor_cartas)):
            return aposta * 50
        if (combinacoes.full_hand(vetor_cartas)):
            return aposta * 20
        if (combinacoes.flush(vetor_cartas)):
            return aposta * 10
        if (combinacoes.straight(vetor_cartas)):
            return aposta * 5
        if (combinacoes.trinca(vetor_cartas)):
            return aposta * 2
        if (combinacoes.dois_pares(vetor_cartas)):
            return aposta
        #Se ele não conseguiu nenhuma combinação, então terá o prejuízo do valor da aposta
        return 0
    
    #Exemplo: [10♠, J♠, Q♠, K♠, A♠]
    def royal_straight_flush(self, vetor_cartas):
        combinacoes = Aposta()
        if (combinacoes.verificar_sequencia(vetor_cartas) and combinacoes.todos_naipes_iguais(vetor_cartas) and vetor_cartas[0][0] == 10):
            return True
        return False
    
    # Exemplo: [8♣, 9♣, 10♣, J♣, Q♣]
    def straight_flush(self, vetor_cartas):
        combinacoes = Aposta()
        if (combinacoes.verificar_sequencia(vetor_cartas) and combinacoes.todos_naipes_iguais(vetor_cartas)):
            return True
        return False
    
    # Exemplo: [7♣ 7♠ 7♦ 7♥ J♥]
    def quadra(self, vetor_cartas):
        combinacoes = Aposta()
        vetor_cartas_iguais = combinacoes.qtd_cartas_iguais(vetor_cartas)
        if max(vetor_cartas_iguais) == 4:
            return True
        return False

    #Exemplo: [3♣, 3♠, 3♦, 6♣, 6♥]
    def full_hand(self, vetor_cartas):
        combinacoes = Aposta()
        vetor_cartas_iguais = combinacoes.qtd_cartas_iguais(vetor_cartas)
        if max(vetor_cartas_iguais) == 3:
            if 2 in vetor_cartas_iguais:
                return True
        return False
        
    #Exemplo: [Q♣, 10♣, 7♣, 6♣, 4♣]
    def flush(self, vetor_cartas):
        combinacoes = Aposta()
        if((combinacoes.todos_naipes_iguais(vetor_cartas)==True) and (combinacoes.verificar_sequencia(vetor_cartas)==False)):
            return True
        return False

    #Exemplo: [9♣, 10♦, J♥, Q♠, K♦]
    def straight(self, vetor_cartas):
        combinacoes = Aposta()
        if(combinacoes.verificar_sequencia(vetor_cartas) == False):
            return False
        else:
            for i in range(cartas_por_jogada - 1):
                if vetor_cartas[i][1] == vetor_cartas[i+1][1]:
                    return False  # Dois naipes iguais encontrados
            return True # Nenhum par de naipes iguais encontrado
    
    #Exemplo: [Q♠, Q♥, Q♦, 7♠, 4♣]
    def trinca(self, vetor_cartas):
        combinacoes = Aposta()
        vetor_cartas_iguais = combinacoes.qtd_cartas_iguais(vetor_cartas)
        if max(vetor_cartas_iguais) == 3:
            return True
        return False

    # Exemplo: [J♥, J♣, 4♣, 4♠, 9♥]
    def dois_pares(self, vetor_cartas):
        pares = 0
        for i in range(cartas_por_jogada):
            for j in range(i+1, cartas_por_jogada, 1):
                # A função só compara os inteiros, pois o naipe é irrelevante para essa combinação
                if vetor_cartas[i][0] == vetor_cartas[j][0]:
                    pares = pares + 1
                    break
        if pares == 2:
            return True
        return False

    #função auxiliar para verificar se há uma sequência no vetor
    def verificar_sequencia(self, vetor_cartas):
        combinacoes = Aposta()
        # primeiro ordenamos o vetor
        vetor_cartas = sorted(vetor_cartas, key=lambda x: x[0])
        for i in range(cartas_por_jogada):
            if i + 1 < cartas_por_jogada and vetor_cartas[i][0] + 1 != vetor_cartas[i + 1][0]:
                return False
        return True

    
    #função auxiliar para verificar se todos os naipes são iguais:
    def todos_naipes_iguais(self, vetor_cartas):
        for i in range(cartas_por_jogada):
            for j in range(i + 1, cartas_por_jogada, 1):
                if vetor_cartas[i][1] != vetor_cartas[j][1]:
                    return False
        return True
    
    #função auxiliar para verificar a quantidade de cartas iguais em um vetor
    def qtd_cartas_iguais(self, vetor_cartas):
        #retorna um vetor, onde cada elemento representa uma carta e seu valor representa quantas vezes ela aparece no vetor
        vetor_contador = [0] * cartas_por_jogada
        for i in range(cartas_por_jogada):
            contador = 0
            for j in range(cartas_por_jogada):
                if vetor_cartas[i][0] == vetor_cartas[j][0]:
                    contador += 1
            vetor_contador[i] = contador
        return vetor_contador

# MAIN
#repete o processo enquanto ele tiver fichas
while(saldo_total > 0):
    # printa o saldo atual antes de mostrar as cartas
    print("Saldo atual: $%d" % saldo_total)

    # Recebe do usuario o valor da aposta ou se ele quer sair do jogo
    print("Digite o valor da aposta ou "F" para terminar ==> ", end=" ")
    aposta = int(input())

    # usa a tabela ascii para ver se o usuario digitou F
    if(aposta == 78):
        print("Desistiu")
        break

    # verifica se o valor da aposta é maior que zero e menor que o saldo total
    if (aposta <= 0 or aposta > saldo_total):
        print("Valor de aposta inválido. Não trapaceie")
        exit(1)

    # Define o objeto partidas, para usar as funções da classe
    partida = Cartas()

    #definir o vetor mudança inicial como todos sendo 1, apar todos serem (re)definidos
    vetor_mudanca = [1, 1, 1, 1, 1]

    # sorteia as cinco cartas e exibe na tela
    vetor_cartas = [0] * (cartas_por_jogada)
    partida.sorteia_cartas(vetor_mudanca, vetor_cartas)

    #vê se o usuário quer mudar alguma carta
    cartas_a_trocar = partida.ler_cartas_a_trocar()

    #atualiza o nosso vetor_mudanca para o funcionamento da função sorteia cartas:
    vetor_mudanca = partida.booleano_cartas(cartas_a_trocar)

    #caso o usuário queira trocar cartas, devemos fazer um loop até que ele não queira mais:
    rodadas_mudanca = max_rodadas_mudanca
    while 1 in vetor_mudanca and rodadas_mudanca >= 0:
        print("Você pode mudar as cartas por mais %d rodadas" % rodadas_mudanca)
        # sorteia as cinco cartas e exibe na tela
        partida.sorteia_cartas(vetor_mudanca, vetor_cartas)

        #vê se o usuário quer mudar alguma carta
        cartas_a_trocar = partida.ler_cartas_a_trocar()
        if len(cartas_a_trocar) > 0 and cartas_a_trocar[0] == 13:
            break

        #atualiza o nosso vetor_mudanca para o funcionamento da função sorteia cartas:
        vetor_mudanca = partida.booleano_cartas(cartas_a_trocar)

        #decrementando a quantidade de rodadas
        rodadas_mudanca = rodadas_mudanca - 1

    #Após o jogador ter feitos as mudanças que queria e que podia, vamos ver quanto ele conquistou
    combinacoes = Aposta()
    ganho_rodada = combinacoes.ganho_aposta(vetor_cartas, aposta)
    saldo_total += (ganho_rodada - aposta)
    
    #Verifica que o jogadoa não perdeu tudo, caso sim, printa uma mensagem e sai do programa
    if (saldo_total <= 0):
        print("Você perdeu tudo! Mais sorte da próxima")
        exit(1)