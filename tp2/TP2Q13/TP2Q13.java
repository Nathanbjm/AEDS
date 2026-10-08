import java.util.*;
import java.io.*;
import java.io.FileReader;
class Veiculo{
	private int id;
	private String marca;
	private String modelo;
	private int ano;
	private String categoria;
	private String[] combustivel;
	private int cilindro;
	private double cilindrada;
	private String transmissao;
	private String tracao;
	private double consumoCidade;
	private double consumoEstrada;
	private double co2;
	private boolean turbo;
	Data dataRegistro;
	Veiculo(){

	}
	public int getId(){
		return id;
	}
	public String getMarca(){
		return marca;
	}
	public String getModelo(){
		return modelo;
	}
	public int getAno(){
		return ano;
	}
	public String getCategoria(){
		return categoria;
	}
	public String[] getCombustivel(){
		return combustivel;
	}
	public int getCilindro(){
		return cilindro;
	}
	public double getCilindrada(){
		return cilindrada;
	}
	public String getTransmissao(){
		return transmissao;
	}
	public String getTracao(){
		return tracao;
	}
	public double getConsumoCidade(){
		return consumoCidade;
	}
	public double getConsumoEstrada(){
		return consumoEstrada;
	}
	public double getCo2(){
		return co2;
	}
	public boolean getTurbo(){
		return turbo;
	}
	public Data getData(){
		return dataRegistro;
	}
	public void setId(int id){
		this.id=id;
	}
	public void setMarca(String marca){
		this.marca=marca;
	}
	public void setModelo(String modelo){
		this.modelo=modelo;
	}
	public void setAno(int ano){
		this.ano=ano;
	}
	public void setCategoria(String categoria){
		this.categoria=categoria;
	}
	public void setCombustivel(String[] combustivel){
		this.combustivel=combustivel;
	}
	public void setCilindro(int cilindro){
		this.cilindro=cilindro;
	}
	public void setCilindrada(double cilindrada){
		this.cilindrada=cilindrada;
	}
	public void setTransmissao(String transmissao){
		this.transmissao=transmissao;
	}
	public void setTracao(String tracao){
		this.tracao=tracao;
	}
	public void setConsumoCidade(double consumoCidade){
		this.consumoCidade=consumoCidade;
	}
	public void setConsumoEstrada(double consumoEstrada){
		this.consumoEstrada=consumoEstrada;
	}
	public void setCo2(double co2){
		this.co2=co2;
	}
	public void setTurbo(boolean turbo){
		this.turbo=turbo;
	}
	public void setData(Data dataRegistro){
		this.dataRegistro=dataRegistro;
	}
	public static Veiculo parseVeiculo(String s){
		int id,ano;
		String marca,modelo,categoria,transmissao,tracao;
		String[] combustivel;
		double cilindradas,consumoCidade,consumoEstrada,co2;
		boolean turbo;
		Veiculo carro=new Veiculo();
		String[] dados=s.split(",");
		combustivel=dados[5].split(";");
		carro.setData(Data.parseData(dados[14]));
		carro.setId(Integer.parseInt(dados[0]));
		carro.setMarca(dados[1]);
		carro.setModelo(dados[2]);
		carro.setAno(Integer.parseInt(dados[3]));
		carro.setCategoria(dados[4]);
		carro.setCombustivel(dados[5].split(";"));
		carro.setCilindro(Integer.parseInt(dados[6]));
		carro.setCilindrada(Double.parseDouble(dados[7]));
		carro.setTransmissao(dados[8]);
		carro.setTracao(dados[9]);
		carro.setConsumoCidade(Double.parseDouble(dados[10]));
		carro.setConsumoEstrada(Double.parseDouble(dados[11]));
		carro.setCo2(Double.parseDouble(dados[12]));
		carro.setTurbo(Boolean.parseBoolean(dados[13]));
		return carro;
	}
	public String format() {
		 String strCombustivel = "[";
      		 for(int i=0;i<combustivel.length;i++){
	      		strCombustivel += combustivel[i];
	       		if(i<combustivel.length-1){
		       	strCombustivel+=",";

	                }
       		}
       		strCombustivel+="]";
    	return String.format(Locale.US,"[%s ## %s ## %s ## %d ## %s ## %s ## %s ## %s ## %s ## %s ## %.2f ## %.2f ## %s ## %s ## %s]",
            getId(), getMarca(), getModelo(), getAno(), getCategoria(),
            strCombustivel, getCilindro(), getCilindrada(), getTransmissao(),
            getTracao(), getConsumoCidade(), getConsumoEstrada(), getCo2(),
            getTurbo(), getData().format());
	}
}
class Data{
	private int ano;
	private int mes;
	private int dia;
	public Data(){

	}
	public Data(int ano,int mes,int dia){
		this.ano=ano;
		this.mes=mes;
		this.dia=dia;
	}
	public int getAno(){
		return ano;
	}
	public int getDia(){
		return dia;
	}
	public int getMes(){
		return mes;
	}
	public void setAno(int ano){
		this.ano=ano;
	}
	public void setMes(int mes){
		this.mes=mes;
	}
	public void setDia(int dia){
		this.dia=dia;
	}
	public static Data parseData(String s){
		int dia,mes,ano;
		String[] split= s.split("-");

		ano=Integer.parseInt(split[0]);
		mes=Integer.parseInt(split[1]);
		dia=Integer.parseInt(split[2]);
		return new Data(ano,mes,dia);
	}
	public String format(){
		String formatada=String.format("%02d/%02d/%04d",dia,mes,ano);
		return formatada;
	}

}

class Leitor{
	public static Veiculo[] ler(String caminho){
		Veiculo[]  veiculos=new Veiculo[500];
		try{
		File file=new File(caminho);
		Scanner scanner=new Scanner(file);
		if(scanner.hasNextLine()) scanner.nextLine();

		int cont=0;
		while(scanner.hasNextLine()){
			String linha=scanner.nextLine();
			veiculos[cont]=Veiculo.parseVeiculo(linha);
			cont++;
		}
		scanner.close();
		}
		catch (Exception e){
			return new Veiculo[0];
		}
		return veiculos;
	}
	public static Veiculo[] ler(){
		Veiculo[] resultado= ler("/tmp/veiculos.csv");
		return resultado;
	}
}

class Celula{
	Veiculo elemento;
	Celula ant,prox;
	Celula(){
		ant=prox=null;
	}
	Celula(Veiculo x){
		ant=prox=null;
		elemento=x;
	}
}

class Lista{
	Celula primeiro,ultimo;
	Lista(){
		Celula cabeca=new Celula();
		primeiro=cabeca;
		ultimo=primeiro;
	}
	public void inserirInicio(Veiculo x) throws Exception{
		Celula nova=new Celula(x);
		nova.prox=primeiro.prox;
		nova.ant=primeiro;
		primeiro.prox=nova;
		if(primeiro==ultimo) ultimo=nova;
		else nova.prox.ant=nova;
	}
	public Veiculo removerInicio() throws Exception{
		if(primeiro==ultimo) throw new Exception("erro");
		Celula tmp=primeiro.prox;
		Veiculo resp=tmp.elemento;
		primeiro.prox=tmp.prox;
		if(tmp==ultimo) ultimo=primeiro;
		else primeiro.prox.ant=primeiro;
		tmp.prox=null;
		tmp.ant=null;
		tmp=null;
		return resp;
	}
	public void inserirFim(Veiculo x) throws Exception{
		ultimo.prox=new Celula(x);
		ultimo.prox.ant=ultimo;
		ultimo=ultimo.prox;
	}
	public Veiculo removerFim() throws Exception{
		if(primeiro==ultimo) throw new Exception("erro");
		Veiculo resp=ultimo.elemento;
		ultimo=ultimo.ant;
		ultimo.prox.ant=null;
		ultimo.prox=null;
		return resp;
	}
	public void inserir(Veiculo x,int pos) throws Exception{
		Celula i=primeiro;
		for(int j=0;j<pos;j++,i=i.prox);
		Celula nova=new Celula(x);
		nova.prox=i.prox;
		nova.ant=i;
		i.prox.ant=nova;
		i.prox=nova;
		i.ant=null;
		i=null;
		nova=null;
	}
	public Veiculo remover(int pos) throws Exception{
		if(primeiro==ultimo) throw new Exception("erro");
		Celula i=primeiro.prox;
		for(int j=0;j<pos;j++,i=i.prox);
		Veiculo resp=i.elemento;
		i.ant.prox=i.prox;
		if(i==ultimo) ultimo=i.ant;
		else i.prox.ant=i.ant;
		i.prox=i=null;
		return resp;
	}
	public void mostrar(){
		for(Celula i=primeiro.prox;i!=null;i=i.prox){
			System.out.println(i.elemento.format());
		}
	}
}


class TP2Q13{
	public static Veiculo buscarVeiculo(Veiculo array[],int id){
		for(int i=0;i<array.length;i++){
			if(array[i].getId()==id) return array[i];
		}
		return null;
	}
	public static void main(String[] args){
		try{
		Veiculo[] array=Leitor.ler();
		Scanner sc=new Scanner(System.in);
		Lista lista=new Lista();
		Veiculo[]removidos;
		int numeroId=sc.nextInt();
		while(numeroId!=-1){
			for(int i=0;i<array.length;i++){
				if(array[i].getId()==numeroId){
					lista.inserirFim(array[i]);
					i=array.length;
				}
			}
			numeroId=sc.nextInt();
		}
		int operacoes=sc.nextInt();
		for(int i=0;i<operacoes;i++){
			String comando=sc.next();
			if(comando.equals("II")){
				int id=sc.nextInt();
				Veiculo v=buscarVeiculo(array,id);
				v.setId(id);
				lista.inserirInicio(v);
			}
			else if(comando.equals("IF")){
				int id=sc.nextInt();
				Veiculo v=buscarVeiculo(array,id);
				v.setId(id);
				lista.inserirFim(v);
			}
			else if(comando.equals("I*")){
				int pos=sc.nextInt();
				int id=sc.nextInt();
				Veiculo v=buscarVeiculo(array,id);
				v.setId(id);
				lista.inserir(v,pos);
			}
			else if(comando.equals("RI")){
				Veiculo removido=lista.removerInicio();
				System.out.println("(R)"+removido.getMarca()+" "+removido.getModelo());
			}
			else if(comando.equals("RF")){
				Veiculo removido=lista.removerFim();
				System.out.println("(R)"+removido.getMarca()+" "+removido.getModelo());
			}
			else if(comando.equals("R*")){
				int pos=sc.nextInt();
				Veiculo removido=lista.remover(pos);
				System.out.println("(R)"+removido.getMarca()+" "+removido.getModelo());
			}
		}
		lista.mostrar();

		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
