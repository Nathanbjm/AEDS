import java.util.*;

class Geracao{
	protected int[] array;
	protected int n;
	public Geracao(int tam){
		array= new int[tam];
		n=array.length;
	}

	public void decrescente(){
		for(int i=0;i<n;i++){
			array[i]=n-i;
		}
	}

	public void mostrarVetorGerado(){
		System.out.print("Vetor gerado: ");
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]);
			System.out.print(" ");
		}
		System.out.print("\n");
	}
	public void swap(int menor, int i){
		int temp=array[menor];
		array[menor]=array[i];
		array[i]=temp;
	}

	int getMaior(int[] array){
		int maior=array[0];
		for(int i=1;i<n;i++){
			if(array[i]>maior) maior=array[i];
		}
		return maior;
	}

	public void mostrarVetorOrdenado(){
		System.out.print("Vetor ordenado: ");
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
	}
}
