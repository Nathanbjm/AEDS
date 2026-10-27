package oficina;
import java.util.Scanner;

 class No{
    int elemento;
    No esq,dir;
    public No(int x){
        elemento=x;
        esq=dir=null;
    }
}
class Arvore{
    No raiz;
    public Arvore(){
        raiz=null;
    }
    public void inserir(int x){
        raiz=inserir(x,raiz);
    }
    public No inserir(int x, No i){
        if(i==null) i=new No(x);
        else if(x<i.elemento) i.esq=inserir(x,i.esq);
        else i.dir=inserir(x,i.dir);
        return i;
    }
    boolean pesquisar(int x){
        return pesquisar(x,raiz);
    }
    boolean pesquisar(int x,No i){
        boolean aux;
        if(i==null){ 
            aux=false;
        }
        else if(i.elemento==x){
            aux=true;
            System.out.print(i.elemento);
        }
        else if(x<i.elemento){
            System.out.print(i.elemento+" ");
            aux=pesquisar(x,i.esq);
        }
        else{
            System.out.print(i.elemento+" ");
            aux=pesquisar(x,i.dir);
        }
        return aux;
    }
    void preOrdem(No i){
        if(raiz==null) System.out.println("V");
        else{
            if(i!=null){
                System.out.print(i.elemento+" ");
                preOrdem(i.esq);
                preOrdem(i.dir);
            }
        }
    }
    void emOrdem(No i){
        if(raiz==null) System.out.println("V");
        else{
            if(i!=null){
            emOrdem(i.esq);
            System.out.print(i.elemento+" ");
            emOrdem(i.dir);
            }
        }    
    }
    void posOrdem(No i){
        if(raiz==null) System.out.println("V");
        else{
            if(i!=null){
                posOrdem(i.esq);
                posOrdem(i.dir);
                System.out.print(i.elemento+" ");
            }
        }
    }
}

public class ArvoreMain{
    public static void main(String[] args){
        Arvore arvore=new Arvore();
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            String s=sc.next();
            if(s.equals("I")){
                int a=sc.nextInt();
                arvore.inserir(a);
            }
            else if(s.equals("P")){
                int a=sc.nextInt();
                boolean resp=arvore.pesquisar(a,arvore.raiz);
                if(resp) System.out.println("S");
                else System.out.println("N");
            }
            if(s.equals("PRE")){arvore.preOrdem(arvore.raiz);System.out.println();} 
            if(s.equals("POS")){arvore.posOrdem(arvore.raiz);System.out.println();}
            if(s.equals("EM")){arvore.emOrdem(arvore.raiz); System.out.println();}
        }
        sc.close();
    }
}

