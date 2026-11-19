class No{
    int elemento;
    No esq,dir;
    int nivel;
    No(int x){
        elemento=x;
        esq=dir=null;
        nivel=1;
    }
    public int getNivel(No i){
        return i==null?0:i.nivel;
    }
    public void setNivel(){
        nivel=1+(getNivel(esq)>getNivel(dir)?getNivel(esq):getNivel(dir));
    }
}

class Arvore{
    No raiz;
    Arvore(){
        raiz=null;
    }
    public void inserir(int x){
        raiz=inserir(x,raiz);
    }
    private No inserir(int x,No i){
        if(i==null) i=new No(x);
        else if(x<i.elemento) i.esq=inserir(x,i.esq);
        else i.dir=inserir(x,i.dir);
        i.setNivel();
        return i;
    }
    public void caminharPre(No i){
        if(i!=null){
            System.out.print(i.elemento+" "+i.getNivel(i)+" ");
            caminharPre(i.esq);
            caminharPre(i.dir);
        }
    }
    public void caminharCentral(No i){
        if(i!=null){
            caminharCentral(i.esq);
            System.out.print(i.elemento+"("+i.getNivel(i)+")"+" ");
            caminharCentral(i.dir);
        }
    }
}

public class AVL{
    public static void main(String[] args){
        Arvore arvore=new Arvore();
        arvore.inserir(20);
        arvore.inserir(10);
        arvore.inserir(30);
        arvore.inserir(5);
        arvore.inserir(25);
        arvore.inserir(40);
        arvore.inserir(50);  
        arvore.caminharCentral(arvore.raiz);
    }
}