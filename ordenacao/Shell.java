class Shell extends Geracao{
	Shell(int tam){
		super(tam);
	}
	public void sort(){
		int h=1;
		while(h<array.length){
			h=3*h+1;
		}
		h=h/3;
		while(h>=1){
			for(int cor=0;cor<h;cor++){
				insercao(cor,h);
			}
			h=h/3;
		}
	}
	public void insercao(int cor,int h){
			for(int i=h+cor;i<array.length;i+=h){
				int j=i-h;
				int tmp=array[i];
				while(j>=0 && array[j]>tmp){
					array[j+h]=array[j];
					j-=h;
				}
				array[j+h]=tmp;
			}
	}
}


