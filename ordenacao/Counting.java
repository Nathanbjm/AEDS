
public class Counting extends Geracao {
    Counting(int tam){
        super(tam);
    }
    public void sort(){
        int maior=array[0],tam=array.length;
        int[]ordenado=new int[tam];
        for(int i=1;i<tam;i++){
            if(array[i]>maior) maior=array[i];
        }
        int[] count=new int[maior+1];
        for(int i=0;i<tam;i++){
            count[array[i]]++;
        }
        for(int i=1;i<maior+1;i++){
            count[i]+=count[i-1];
        }
        for(int i=tam-1;i>=0;i--){
            ordenado[count[array[i]]-1]=array[i];
            count[array[i]]--;
        }
        for(int i=0;i<tam;i++){
            array[i]=ordenado[i];
        }   
    }
}
