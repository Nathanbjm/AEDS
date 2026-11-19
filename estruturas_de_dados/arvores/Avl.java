class No{
    int valor;
    No esq,dir;
    int nivel;
    No(int x){
        esq=dir=null;
        valor=x;
        nivel=-1;
    }
    int getNivel(){
        return nivel;
    }
    void setNivel(No no){
        nivel=1 + no.esq.getNivel()>no.dir.getNivel()?no.esq.getNivel():no.dir.getNivel();
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
    No inserir(int x,No i) throws Exception{
        if(i==null) i=new No(x);
        else if(x<i.valor) i.esq=inserir(x,i.esq);
        else if(x>i.valor) i.dir=inserir(x,i.dir);
        else throw new Exception("O elemento ja existe");
    }
}