import java.util.*;

class Geracao{
	protected int[] array;
	public Geracao(int tam){
		array= new int[tam];
		Random random=new Random();

		for(int i=0;i<tam;i++){
			array[i]=random.nextInt(10);
		}
	}

	public void mostrarVetorGerado(){
		System.out.print("Vetor gerado: ");
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]);
			System.out.print(" ");
		}
	}
	public void swap(int menor, int i){
		int temp=array[menor];
		array[menor]=array[i];
		array[i]=temp;
	}
	public void mostrarVetorOrdenado(){
		System.out.print("Vetor ordenado: ");
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
	}
}
