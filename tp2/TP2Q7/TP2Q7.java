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

class Bucket{
	public Veiculo veiculos[];
	public int qtd;
	Bucket(int x){
		veiculos=new Veiculo[x];
		qtd=0;
		}	
}


class TP2Q7{
	public static int comparar(String a,String b){
		int i=0;
		int tamanho;
		if(a.length()<b.length()) tamanho=a.length();
		else tamanho=b.length();
		while(i!=tamanho){
			char A=a.charAt(i);
			char B=b.charAt(i);
			if(a.charAt(i)>='A' && a.charAt(i) <='Z'){
				A=(char)(a.charAt(i)+32);
			}
			if(b.charAt(i)>='A' && b.charAt(i)<='Z'){
				B=(char)(b.charAt(i)+32);
			}
			if(A!=B) return A-B;
			i++;
		}
		return a.length()-b.length();
	}

	public static void bucket(Veiculo[] vetor,int n){
		Bucket []b=new Bucket[10];
		for(int i=0;i<10;i++){
			b[i]=new Bucket(500);
		}
		for(int i=0;i<n;i++){
			double normalizado=vetor[i].getCilindrada()/8.1;
			int index=(int)(normalizado*10);
			b[index].veiculos[b[index].qtd]=vetor[i];
			b[index].qtd++;
		}

		for(int i=0;i<10;i++){
			insercao(b[i],b[i].qtd);
		}
		int k=0;
		for(int i=0;i<10;i++){
			for(int j=0;j<b[i].qtd;j++){
				vetor[k]=b[i].veiculos[j];
				k++;
			}
		}

	}

	public static void insercao(Bucket b,int tam){
		for(int i=1;i<tam;i++){
			int j=i-1;
			Veiculo tmp=b.veiculos[i];
			while(j>=0 && b.veiculos[j].getCilindrada()>tmp.getCilindrada()){
				b.veiculos[j+1]=b.veiculos[j];
				j--;
			}
			b.veiculos[j+1]=tmp;
		}
	}

	public static void main(String[] args){
		Veiculo[] array=Leitor.ler();
		Scanner sc=new Scanner(System.in);
		Veiculo[] vetor=new Veiculo[500];
		int j=0;
		int numeroId=sc.nextInt();
		while(numeroId!=-1){
			for(int i=0;i<array.length;i++){
				if(array[i].getId()==numeroId){
					vetor[j]=array[i];
					i=array.length;
					j++;
				}
			}
			numeroId=sc.nextInt();
		}
		sc.close();
		bucket(vetor,j);
		for(int i=0;i<j;i++){
			System.out.println(vetor[i].format());
		}
	}
}
