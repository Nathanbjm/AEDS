package exercicios;
//arvore indexada por letra da placa. Depois de achar a letra, inserir na T1, que tem uma funcao hash que soma os caracteres da placa e faz mod 7. Se a posicao estiver ocupada, chamar rehash(somar mais 1). Se nao tiver espaco, ir pra T2, chamando a funcao hash t2, que soma os caracteres do CHASSI. Achando o dele, inserir na t2. Então, inserir na lista encontrada em determinado index da T2.

public class DOIDONA {
    No raiz;
    No pesquisarArvore(char c,No i){
        No tmp;
        if(c==i.getLetra()) tmp=i;
        else if(c<i.getLetra()) tmp=pesquisarArvore(c, i.esq);
        else tmp=pesquisarArvore(c,i.dir);
        return tmp;
    }
    public void inserir(Veiculo x){
        char inicialPlaca=x.getPlaca().charAt(0);
        No a=pesquisarArvore(inicialPlaca,raiz);
        int pos1=a.t1.hash(x.getPlaca());
        if(a.t1.tabela[pos1]==null){
            a.t1.tabela[pos1]=x;
        }
        else{
            int pos2=a.t1.rehash(x.getPlaca());
            if(a.t1.tabela[pos2]==null) a.t1.tabela[pos2]=x;
            else{
                T2 var=a.t1.t2;
                int pos3=var.hash2(x.getChassi());
                var.tabela2[pos3].inserir(x);
            }
        }
    }
}

class No{
    char letra;
    No esq,dir;
    T1 t1;
    public char getLetra(){
        return letra;
    }
}

class T1{
    Veiculo[] tabela;
    T2 t2;
    int hash(String s){
        int soma=0;
        for(int i=0;i<s.length();i++){
            soma+=(int)s.charAt(i);
        }
        return soma%7;
    }
    int rehash(String s){
        int soma=1;
        for(int i=0;i<s.length();i++){
            soma+=(int)s.charAt(i);
        }
        return soma%7; 
    }
}

class T2{
    Lista[] tabela2;
    int hash2(String s){
        int soma=0;
        for(int i=0;i<s.length();i++){
            soma+=(int)s.charAt(i);
        }
        return soma%7;
    }
    int rehash2(String s){
        int soma=1;
        for(int i=0;i<s.length();i++){
            soma+=(int)s.charAt(i);
        }
        return soma%7; 
    }
}

class Veiculo{
    String modelo,chassi,placa,tipo;
    Veiculo(String modelo,String chassi,String placa,String tipo){
        this.modelo=modelo;
        this.chassi=chassi;
        this.placa=placa;
        this.tipo=tipo;
    }
    public String getPlaca(){
        return placa;
    }
    public String getChassi(){
        return chassi;
    }
}

class Lista{
    Celula inicio,fim;
    Lista(){
        Celula cabeca=new Celula();
        inicio=fim=cabeca;
    }
    void inserir(Veiculo x){
        fim.prox=new Celula(x);
        fim=fim.prox;
    }
}

class Celula{
    Veiculo elemento;
    Celula prox;
    Celula(){
        this.prox=null;
    }
    Celula(Veiculo x){
        this.elemento=x;
        this.prox=null;
    }
}
