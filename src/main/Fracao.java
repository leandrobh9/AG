package main;

import java.math.BigDecimal;


public class Fracao {
	private BigDecimal minimo;
	private BigDecimal maximo;
	
	public Fracao(){}
	
	public Fracao(BigDecimal minimo, BigDecimal maximo) {
		super();
		this.minimo = minimo;
		this.maximo = maximo;
	}



	public BigDecimal getMinimo() {
		return minimo;
	}
	
	public void setMinimo(BigDecimal minimo) {
		this.minimo = minimo;
	}
	
	public BigDecimal getMaximo() {
		return maximo;
	}
	
	public void setMaximo(BigDecimal maximo) {
		this.maximo = maximo;
	}
	
	@Override
	public String toString() {
		return "fracao:(" + getMinimo() + " e " + getMaximo() + "]";
	}
}