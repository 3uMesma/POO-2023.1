
public class Bozo
{
	public static void main(String[] args) {
		EntradaTeclado et = new EntradaTeclado();
		RolaDados rd = new RolaDados(5);
		Placar placar = new Placar();
		int resultados[] = new int[5];
		int posicao;
		for(int r=0;r<10;r++){
		    System.out.println(placar);
		    System.out.println("\n****** Rodada "+Integer.toString(r));
		    System.out.println("Pressione ENTER para lançar os dados\n");
		    et.leString();
		    
		    //Rola os dados sem nenhum critério
		    resultados = rd.rolar();
		    System.out.println(rd);
		    System.out.println("Digite os números dos dados que quiser TROCAR. Separados por espaços.");
		    resultados = rd.rolar(et.leString());
		    System.out.println(rd);
		    System.out.println("Digite os números dos dados que quiser TROCAR. Separados por espaços.");
		    resultados = rd.rolar(et.leString());
		    System.out.println(rd);
		    System.out.println("\n\n"+placar);
		    System.out.print("\nEscolha a posição que quer ocupar com essa jogada ===>");
		    posicao = et.leInt();
		    placar.add(posicao,resultados);
		}
		System.out.println(placar);
		System.out.println("\n\n**********************");
		System.out.println("****");
		System.out.println("**** Seu score final foi: "+Integer.toString(placar.getScore()));
		System.out.println("****");
		System.out.println("**********************");
	}
}
