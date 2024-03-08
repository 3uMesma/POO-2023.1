import java.util.Scanner;
public class EntradaTeclado{
    static Scanner prompt_scanner;
    public EntradaTeclado(){
        prompt_scanner = new Scanner( System.in );
    }
    public static double leDouble(){
        double lido = prompt_scanner.nextDouble();
        return lido;
    }
    public static int leInt(){
        int lido = prompt_scanner.nextInt();
        return lido;
    }
    public static String leString(){
        String lido = prompt_scanner.nextLine();
        return lido;
    }
}