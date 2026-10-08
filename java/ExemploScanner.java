import java.util.*;

class ExemploScanner{
	public static void main(String[] args){
		Scanner scanner=new Scanner(System.in);
		System.out.println("Digite seu nome:");
		String nome=scanner.nextLine();
		System.out.println("Digite sua idade:");
		int idade=scanner.nextInt();
		System.out.println("Olá, meu nome é "+nome+" e eu tenho "+idade+" anos!");
		scanner.close();
	}
}
