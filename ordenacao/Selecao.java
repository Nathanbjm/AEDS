

class Selecao extends Geracao{
	Selecao(int tam){
		super(tam);
	}
	public void sort(){
		for(int i=0;i<array.length-1;i++){
			int menor=i;
			for(int j=i+1;j<array.length;j++){
				if(array[j]<array[menor]){
					menor=j;
				}
			}
			swap(menor,i);
		}
	}
}

