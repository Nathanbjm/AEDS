public class BICOLOR {
    No raiz;
    BICOLOR(){
        raiz=null;
    }
    public void inserir(int x){
        if(raiz==null) raiz=new No(x);
        else inserir(x,null,null,null,raiz);
        raiz.cor=false;
    }
    void inserir(int x,No bisavo,No avo,No pai,No i){
        if(i==null){
            if(x<pai.elem) i.esq=new No(x);
            else i.dir=new No(x);
            if(pai.cor) balancear(bisavo,avo,pai,i);
        }
        else{
            if(noEhDoTipo4(i)) {
                recolorir(i);
                if(pai==raiz) pai.cor=false;
                else if(pai.cor) balancear(bisavo,avo,pai,i);
            }
            if(x<i.elem) inserir(x,avo,pai,i,i.esq);
            else inserir(x,avo,pai,i,i.dir);
        }
    }
    
    boolean noEhDoTipo4(No i){
        return(i.esq!=null && i.dir!=null && i.esq.cor && i.dir.cor);
    }
    void recolorir(No i){
        i.cor=true;
        i.esq.cor=i.dir.cor=false;
    }

    void balancear(No bisavo, No avo, No pai, No i){
        if(avo.elem<pai.elem && pai.elem<i.elem) avo=rotacaoEsq(avo);
        else if(avo.elem>pai.elem && pai.elem>i.elem) avo=rotacaoDir(avo);
        else if(avo.elem<pai.elem && pai.elem>i.elem) avo=rotacaoDirEsq(avo);
        else avo=rotacaoEsqDir(avo);
        if(bisavo==null){
            raiz=avo;
        }
        else if(avo.elem<bisavo.elem){
            bisavo.esq=avo;
        }
        else bisavo.dir=avo;
        avo.cor=false;
        pai.cor=i.cor=true;
    }
    No rotacaoEsq(No i){
        No noDir=i.dir;
        No noDirEsq=noDir.esq;
        noDir.esq=i;
        i.dir=noDirEsq;
        return noDir;
    }

    No rotacaoDir(No i){
        No noEsq=i.esq;
        No noEsqDir=noEsq.dir;
        noEsq.dir=i;
        i.esq=noEsqDir;
        return noEsq;
    }

    No rotacaoDirEsq(No i){
        i.dir=rotacaoDir(i.dir);
        return rotacaoEsq(i);
    }

    No rotacaoEsqDir(No i){
        i.esq=rotacaoEsq(i.esq);
        return rotacaoDir(i);
    }

}

class No{
    int elem;
    No esq,dir;
    boolean cor;
    No(int x){
        elem=x;
        esq=dir=null;
        cor=true;
    }
}
