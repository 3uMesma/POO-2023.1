
public class Placar{
    // De 0 a 5: pontuações normais
    // de 6 a 9: pontuações de 
    int pontuacoes[];
    
    public Placar(){
        pontuacoes = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
    }
    void add(int posicao,int[] dados){
        if(pontuacoes[posicao-1]==-1){
            int pontuacao = 0;
            pontuacoes[posicao-1]=0;
            if(posicao<7){
                //Inserção por contagem
                for(int i=0;i<dados.length;i++){
                    if(posicao==dados[i]){
                        pontuacoes[posicao-1] += posicao;
                    }
                }
            }else{
                //Inserção de grupo
                if(posicao==7){//full hand
                    int count;
                    for(int i=0;i<dados.length;i++){
                        count = 0;
                        for(int j=0;j<dados.length;j++){
                            if(i==j){continue;}
                            if(dados[i]==dados[j]){count++;}
                        }
                        if(count>=2){
                            pontuacoes[posicao-1]+=15;
                            break;
                        }
                    }
                                    
                }else if(posicao==8){//sequencia
                    //Inserção de grupo
                    boolean continua=false;int q_sequencia;
                    for(int i=0;i<2;i++){
                        continua=true;q_sequencia=0;
                        for(int j=0;j<5 && continua;j++){
                            continua = false;
                            for(int k=0;k<dados.length;k++){
                                if(dados[k]==i+j+2){continua=true;q_sequencia++;break;}
                            }
                        }
                        if(q_sequencia==4){
                            pontuacoes[posicao-1]+=20;
                            break;
                        }
                    }
                }else if(posicao==9){//quadrada (4 iguais)
                    int count;
                    for(int i=0;i<dados.length;i++){
                        count = 0;
                        for(int j=0;j<dados.length;j++){
                            if(i==j){continue;}
                            if(dados[i]==dados[j]){count++;}
                        }
                        if(count>=3){
                            pontuacoes[posicao-1]+=30;
                            break;
                        }
                    }
                }else if(posicao==10){//general (5 iguais)
                    int count;
                    for(int i=0;i<dados.length;i++){
                        count = 0;
                        for(int j=0;j<dados.length;j++){
                            if(i==j){continue;}
                            if(dados[i]==dados[j]){count++;}
                        }
                        if(count>=4){
                            pontuacoes[posicao-1]+=40;
                            break;
                        }
                    }
                }
            }
        }
    }
    public int getScore(){
        int soma = 0;
        for(int i=0;i<10;i++){
            if(pontuacoes[i]!=-1){
                soma += pontuacoes[i];   
            }
        }
        return soma;
    }
    public String toString(){
    String p[] = new String[10];
    p[0] = (pontuacoes[0]==-1) ? " (1)    " : " "+Integer.valueOf(pontuacoes[0])+((pontuacoes[0]<10)?"      ":(pontuacoes[0]<100)?"     ":"    ");
    p[1] = (pontuacoes[1]==-1) ? " (2)    " : " "+Integer.valueOf(pontuacoes[1])+((pontuacoes[1]<10)?"      ":(pontuacoes[1]<100)?"     ":"    ");
    p[2] = (pontuacoes[2]==-1) ? " (3)    " : " "+Integer.valueOf(pontuacoes[2])+((pontuacoes[2]<10)?"      ":(pontuacoes[2]<100)?"     ":"    ");
    
    p[3] = (pontuacoes[3]==-1) ? "   (4)" : "   "+Integer.valueOf(pontuacoes[3])+((pontuacoes[3]<10)?"  ":(pontuacoes[3]<100)?" ":"");
    p[4] = (pontuacoes[4]==-1) ? "   (5)" : "   "+Integer.valueOf(pontuacoes[4])+((pontuacoes[4]<10)?"  ":(pontuacoes[4]<100)?" ":"");
    p[5] = (pontuacoes[5]==-1) ? "   (6)" : "   "+Integer.valueOf(pontuacoes[5])+((pontuacoes[5]<10)?"  ":(pontuacoes[5]<100)?" ":"");
    
    p[6] = (pontuacoes[6]==-1) ? "   (7)    " : "   "+Integer.valueOf(pontuacoes[6])+((pontuacoes[6]<10)?"      ":(pontuacoes[6]<100)?"     ":"    ");
    p[7] = (pontuacoes[7]==-1) ? "   (8)    " : "   "+Integer.valueOf(pontuacoes[7])+((pontuacoes[7]<10)?"      ":(pontuacoes[7]<100)?"     ":"    ");
    p[8] = (pontuacoes[8]==-1) ? "   (9)    " : "   "+Integer.valueOf(pontuacoes[8])+((pontuacoes[8]<10)?"      ":(pontuacoes[8]<100)?"     ":"    ");
    p[9] = (pontuacoes[9]==-1) ? "   (10)   " : "   "+Integer.valueOf(pontuacoes[9])+((pontuacoes[9]<10)?"      ":(pontuacoes[9]<100)?"     ":"    ");
    String final_str = "";
    final_str += p[0]+"|"+p[6]+"|"+p[3]+"\n";
    final_str += "--------------------------\n";
    final_str += p[1]+"|"+p[7]+"|"+p[4]+"\n";
    final_str += "--------------------------\n";
    final_str += p[2]+"|"+p[8]+"|"+p[5]+"\n";
    final_str += "--------------------------\n";
    final_str += "        |"+p[9]+"|\n";
    final_str += "        +----------+ ";
    return final_str;
    }
}