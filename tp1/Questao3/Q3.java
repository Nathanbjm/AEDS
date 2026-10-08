import java.util.*;

class Q3{
	public static void main(String[] args){
		String string;
		Scanner scanner= new Scanner(System.in);
		string=scanner.nextLine();
		while(string.length()!=3 || string.charAt(0)!='F' || string.charAt(1)!='I' || string.charAt(2)!='M'){// até a palavra FIM
			if(vogais(string)==true) System.out.print("SIM "); else System.out.print("NAO ");
			if(consoantes(string)==true) System.out.print("SIM "); else System.out.print("NAO ");
			if(inteiro(string)==true) System.out.print("SIM "); else System.out.print("NAO ");
			if(ehReal(string)==true) System.out.print("SIM"); else System.out.print("NAO");
			System.out.print("\n");
			string=scanner.nextLine();
		}
	}
	public static boolean ehPalavra(String string){// se só tiver letras ( maiusculas ou minusculas) é uma palavra
		for(int i=0;i<string.length();i++){
			if(!((string.charAt(i)>='A' && string.charAt(i)<='Z') || (string.charAt(i)>='a' && string.charAt(i)<='z'))){
				return false;	
			}
		}
	return true;
	}
	public static boolean vogais(String string){//bem óbvio
		if(ehPalavra(string)==true){
		for(int i=0;i<string.length();i++){
			if(!(string.charAt(i)=='A' || string.charAt(i) =='E' || string.charAt(i)=='I' || string.charAt(i)=='O' || string.charAt(i)=='U' || 
			     string.charAt(i)=='a' || string.charAt(i) =='e' || string.charAt(i)=='i' || string.charAt(i)=='o' || string.charAt(i)=='u')){
					return false;
			}
		}
		return true;
		}
		return false;
	}

	public static boolean consoantes(String string){//óbvio
		if(ehPalavra(string)==true){
		for(int i=0;i<string.length();i++){
			if(string.charAt(i)=='A' || string.charAt(i) =='E' || string.charAt(i)=='I' || string.charAt(i)=='O' || string.charAt(i)=='U' || 
			     string.charAt(i)=='a' || string.charAt(i) =='e' || string.charAt(i)=='i' || string.charAt(i)=='o' || string.charAt(i)=='u'){
					return false;
			}
		}
		return true;
		}
		return false;
	}

	public static boolean inteiro(String string){//48 na tab ascii é '0' e 57 é '9'
		for(int i=0;i<string.length();i++){
			if(!(string.charAt(i)>=48 && string.charAt(i)<=57)){
				return false;
			}
		}
		return true;
	}


	public static int contarSeparador(String string){//funcao para saber quantos '.' ou ',' a string tem
		int separador=0;
		for(int i=0;i<string.length();i++){
			if(string.charAt(i)=='.' || string.charAt(i)==',') separador++;
		}
		return separador;
	}
	public static boolean ehNumero(String string){//para ser um numero, tem que ter 1 ou 0 ponto/virgula, e alem dele apenas digitos.
		if((contarSeparador(string))<=1){
			for(int i=0;i<string.length();i++){
				if(!(string.charAt(i)>=48 && string.charAt(i)<=57 || string.charAt(i)=='.' || string.charAt(i)==',')){
					return false;
				}
			}
		}
		return true;
	}

	public static boolean ehReal(String string){//um real tem apenas digitos e 1 ou 0 separadores;
		if(ehNumero(string)==true && contarSeparador(string)<=1) return true;
		return false;	
}
}





