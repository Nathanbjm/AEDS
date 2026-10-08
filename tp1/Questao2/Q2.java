import java.util.Scanner;
import java.util.Random;
class Q2{

        public static void aleatorizar(String str,Random random){
                String strNova="";
		char letra1=(char)('a'+(Math.abs(random.nextInt())%26));//gera a primeira letra aleatoria
		char letra2=(char)('a'+(Math.abs(random.nextInt())%26));//gera a segunda letra aleatoria
                        for(int i=0;i<str.length();i++){
                                if(str.charAt(i)==letra1)
                                        strNova+=letra2;//se a letra na posicao atual for igual a aleatoria, ela é substituida
                                else
                                        strNova+=str.charAt(i);//caso contrario, a letra que já estava na string original é colocada.
                        }
                                System.out.println(strNova);
        }

        public static void main(String[] args){
		Random random=new Random();//geração de numero aleatorio
		random.setSeed(4);
                Scanner sc=new Scanner(System.in);
                String str=sc.nextLine();
                while(!(str.length()==3 && str.charAt(0)=='F' && str.charAt(1)=='I' && str.charAt(2)=='M')){//leitura da entrada
                        aleatorizar(str,random);
                        str=sc.nextLine();
                }
        }
}

