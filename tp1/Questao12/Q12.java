import java.util.Scanner;

class Q12{
	public static int soma(int n){//a recursao acaba quando n for de um digito só. Se nao for, volta para a funcao,somando o digito encontrado,que é o resto da divisao por 10 de n.O valor a ser passado deve ser o mesmo numero, mas dividido por 10. Como exemplo, 502. Se faço o resto por 10, encontro 2. Pego o resultado da divisao e faço o mod por 10 de novo, dando 0. Divido novamente o resultado, 5, por 10, e dá 0 com resto 5. 5+0+2=7.
		if(n/10==0) return n;
		return n%10 + soma(n/10);
	}

	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		while(sc.hasNextInt()){//leitura pela milésima vez
			int n=sc.nextInt();
			System.out.println(soma(n));
		}
	}
}
