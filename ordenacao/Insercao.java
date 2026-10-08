class Insercao extends Geracao{
	Insercao(int tam){
		super(tam);
	}
	public void sort(){
		int tmp;
		int j;
		for(int i=1;i<n;i++){
			tmp=array[i];
			j=i-1;
			while(j>=0 && array[j]>tmp){
				array[j+1]=array[j];
				j--;
			}
			array[j+1]=tmp;

		}

	}
}



//10 9 8 7 6 5 4 3 2 1

