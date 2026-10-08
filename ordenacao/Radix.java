class Radix extends Geracao{
	Radix(int tam){
		super(tam);
	}
	public void sort(){
		int maior=getMaior(array);
		System.out.print(maior);
	}
}
