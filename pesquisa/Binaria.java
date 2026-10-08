class Binaria{
	public static void main(String[] args){
		int[] vet={1,2,3,4,5,6,7,8,9,10};
		int esq=0,dir=9,meio;
		int x=31;
		int encontrou=0;
		while(esq<=dir){
			meio=(esq+dir)/2;
			if(x==meio){
				encontrou=1;
			       	System.out.print("encontrado");
				esq=dir+1;
			}
			else{
				if(x>meio) esq=meio+1;
				else dir=meio-1;
			}
		}
		if(encontrou==0) System.out.print("nao encontrou");
	}
}

