

public class RolaDados{
    Dado dados[];
    public RolaDados(int n){
        dados = new Dado[n];
        for(int i=0;i<n;i++){
            dados[i] = new Dado(6);
        }
    }
    public int[] rolar(){
        int resultados[] = new int[dados.length];
        for(int i=0;i<resultados.length;i++){
            resultados[i] = dados[i].rolar();
        }
        return resultados;
    }
    public int[] rolar(boolean[] quais){
        int resultados[] = new int[dados.length];
        for(int i=0;i<resultados.length;i++){
            if(quais[i]){
                resultados[i] = dados[i].rolar();   
            }
        }
        return resultados;
    }
    public int[] rolar(String s){
        int resultados[] = new int[dados.length];
        for(int i=0;i<dados.length;i++){
            resultados[i]=dados[i].getLado();
        }
        if(!s.equals("")){
            String dados_rolar[] = s.split("\\s+");
            int d;
            for(int i=0;i<dados_rolar.length;i++){
                d = Integer.parseInt(dados_rolar[i]);
                resultados[d-1] = dados[d-1].rolar();
            }
        }
        return resultados;
        
    }
    public String toString(){
        String stringui = "";
        String[][] dados_part_str = new String[dados.length][6];
        String part_aux[];
        for(int i=0;i<dados.length;i++){
            dados_part_str[i][0] = " "+String.valueOf(i+1)+"         ";
            part_aux = String.valueOf(dados[i]).split(System.lineSeparator());
            for(int l=0;l<5;l++){
                dados_part_str[i][l+1] = part_aux[l];
            }
        }
        String final_str="";
        for(int l=0;l<6;l++){
            for(int i=0;i<dados.length;i++){
                final_str += dados_part_str[i][l];
            }
            final_str+="\n";
        }
        return final_str;
    }
}