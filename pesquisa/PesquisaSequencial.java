class PesquisaSequencial{
	public static void main(String[] args){
		int[] array={10,5,6,20};
		int x=6;
		boolean encontrou=false;
		for(int i=0;i<array.length;i++){
			if(array[i]==x){ 
				System.out.print("Posicao: "+i);
				encontrou=true;
			}
		}
		if(encontrou==false) System.out.print("Nao encontrado");
		
	}
}









