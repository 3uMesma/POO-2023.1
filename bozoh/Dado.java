
public class Dado{
    int lados[];
    int sel=-1;
    Random r;
    public Dado(){
        lados = new int[]{1,2,3,4,5,6,7};
        r = new Random();
    }
    public Dado(int k){
        lados = new int[k];
        for(int i=0;i<k;i++){
            lados[i]=i;
        }
        r = new Random();
    }
    public int getLado(){
        return sel;
    }
    public int rolar(){
        sel = lados[r.getIntRand(lados.length)]+1;
        return sel;
    }
    public String toString(){
        String final_str = "";
        String p[] = new String[9];
        p[0] = (sel>1) ? "*" : " ";
        p[1] = (sel==6)? "*" : " ";
        p[2] = (sel>3)? "*" : " ";
        p[3] = (false)? "*" : " ";
        p[4] = (sel%2==1)? "*" : " ";
        p[5] = (false)? "*" : " ";
        p[6] = (sel>3)? "*" : " ";
        p[7] = (sel==6)? "*" : " ";
        p[8] = (sel>1)? "*" : " ";
        final_str += "+-----+    \n";
        final_str += "|"+p[0]+" "+p[1]+" "+p[2]+"|    \n";
        final_str += "|"+p[3]+" "+p[4]+" "+p[5]+"|    \n";
        final_str += "|"+p[6]+" "+p[7]+" "+p[8]+"|    \n";
        final_str += "+-----+    \n";
        return final_str;
        
    }
}