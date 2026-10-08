import java.util.Scanner;
class Q11{
	public static String inversao(String str,int n){
		if(n==0) return "";
		return str.charAt(n-1) + inversao(str,n-1);
	}
	public static String inversaoIniciar(String str){
		return inversao(str,str.length());
	}

	public static void main(String[] args){
		String str;
		Scanner sc=new Scanner(System.in);
		str=sc.nextLine();
		while(!(str.charAt(0)=='F' && str.charAt(1)=='I' && str.charAt(2)=='M')){
			System.out.println(inversaoIniciar(str));
			str=sc.nextLine();
		}
	}
}
