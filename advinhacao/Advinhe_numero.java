import java.util.Calendar;
import java.util.Scanner;
//import Random;

public class Advinhe_numero {
	public static void main(String[] args) {
		int chute = 0;

		Scanner ler = new Scanner(System.in);

		Random rand = new Random();
		int numeroAleatorio = rand.getIntRand(100);
		//System.out.printf("Número aleatório: %d\n", numeroAleatorio);

		while (chute != numeroAleatorio) {
			System.out.print("Chute um número de 0 a 100: ");
			chute = ler.nextInt();
				
			if (chute < numeroAleatorio) {
				System.out.println("Seu palpite é menor do que o número gerado.");
			} else if (chute > numeroAleatorio) {
				System.out.println("Seu palpite é maior do que o número gerado.");
			}
		}
			
		System.out.println("Parabéns! Você adivinhou o número");
	}
}

class Random {
	private long p = 2147483648L;
	private long m = 843314861;
	private long a = 453816693;
	
	
	private long xi = 1023;


	public double getRand() {
		xi = (a + m * xi) % p;
		double d = xi;
		//System.out.println(d + " " + xi + " " + d / p);
		return d / p;
	}
	
	public int getIntRand(int max)
	{
		double d = getRand() * max;
		return (int) d;
	}

	public void setSemente(int semente) {
       xi = semente;		
	}

	public Random(int k)
	{
		xi = k;
	}
	
	public Random() {
		xi = Calendar.getInstance().getTimeInMillis() % p;
	}
	
	@Override
	public String toString() {
		return xi + "";
	}
	
	static public void aleatorio() {
		Random rd = new Random(5);
		Random rd2 = new Random();
		String s = rd.toString();
		//System.out.println(rd);
		//System.out.println(rd2);
		//int k = rd.getIntRand(100);
		//System.out.println(rd);
		rd.getRand();

	}
}