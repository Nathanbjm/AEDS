import java.util.Scanner;

class Q8{

public static int numero(char c){//se o caractere estiver entre 0 e 9, é um digito.
	if(c>='0' && c<='9') return 1;
	return 0;
}

public static int minuscula(char c){//se estiver entre 'a' e 'z', é minusculo.
	if(c>='a' && c<='z') return 1;
	return 0;
}

public static int maiuscula(char c){//mesma coisa para maiusculo.
	if(c>='A' && c<='Z') return 1;
	return 0;
}

public static int especial(char c){//se nao for nenhum dos tres anteriores, só pode ser especial.
	if(numero(c)==0 && minuscula(c)==0 && maiuscula(c)==0) return 1;
	return 0;
}

public static boolean validacaoSenha(String string){
		int numeros=0,maiusculas=0,minusculas=0,especiais=0;
		for(int i=0;i<string.length();i++){//vai percorrendo a string até o seu final
			if(numero(string.charAt(i))==1) numeros++;
			if(minuscula(string.charAt(i))==1) minusculas++;
			if(maiuscula(string.charAt(i))==1) maiusculas++;
			if(especial(string.charAt(i))==1) especiais++;
		}
		if(string.length()>=8 && numeros>=1 && maiusculas>=1 && minusculas>=1 && especiais>=1) return true;//para ser senha, tem que ter ao menos 8 posicoes, pelo menos1 minuscula,ao menos 1 maiuscula e ao menos um especial.
		return false;
}

public static void main(String[] args){
	Scanner scanner= new Scanner(System.in);
	String str=scanner.nextLine();
	while(str.length()!=3 || str.charAt(0)!='F' || str.charAt(1)!='I' || str.charAt(2)!='M'){//leitura de entradas
		if(validacaoSenha(str)==true) System.out.println("SIM");
		else System.out.println("NAO");
		str=scanner.nextLine();
	}
}

}






