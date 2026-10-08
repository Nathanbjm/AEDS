import java.util.*;
class Q1{
	public static void ciframento(String str){
		String novaStr="";//string vazia, pois aqui strings sao imutaveis.	
			for(int i=0;i<str.length();i++){
				novaStr+=(char)(str.charAt(i)+3);
			}//loop para ir aumentar em 3 a posicao de uma letra. 'a' vira 'd', por exemplo. +3 é somado ao numero correspondente na tab ascii.
			System.out.println(novaStr);
		}
	public static void main(String[] args){
		String str;
		Scanner sc=new Scanner(System.in);
		str=sc.nextLine();
		while(!(str.charAt(0)=='F' && str.charAt(1)=='I' && str.charAt(2)=='M')){//ate a palavra FIM.
			ciframento(str);
			str=sc.nextLine();
		}
	}
}

	



