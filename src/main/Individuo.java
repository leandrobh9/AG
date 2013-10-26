package main;

public class Individuo {

	private String cromossomo;
	private Integer distanceHamming;
	private Integer valorFitness;
	private Fracao fracao;
	private Integer indiceNaPopulacao;
	
	public Individuo(){}
	
	public Individuo(String cromossomo){
		this.cromossomo = cromossomo;
	}
	
	public String getCromossomo() {
		return cromossomo;
	}
	
	public void setCromossomo(String cromossomo) {
		this.cromossomo = cromossomo;
	}
	
	public Integer getDistanceHamming() {
		return distanceHamming;
	}
	
	public void setDistanceHamming(Integer distanceHamming) {
		this.distanceHamming = distanceHamming;
	}
	
	public Integer getValorFitness() {
		if (distanceHamming == null) {
			throw new RuntimeException("Erro: Valor de distance haming esta null.");
		}
		return getCromossomo().length() - distanceHamming;
	}
	
	public Fracao getFracao() {
		if (fracao == null) {
			fracao = new Fracao();
		}
		return fracao;
	}
	
	public void setFracao(Fracao fracao) {
		this.fracao = fracao;
	}
	
	public Integer getIndiceNaPopulacao() {
		return indiceNaPopulacao;
	}
	
	public void setIndiceNaPopulacao(Integer indiceNaPopulacao) {
		this.indiceNaPopulacao = indiceNaPopulacao;
	}
	
	@Override
	public String toString() {
		return getCromossomo();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Individuo)) return false;
		Individuo i = (Individuo)obj;
		return this.getIndiceNaPopulacao().equals(i.getIndiceNaPopulacao());
	}
	
}
