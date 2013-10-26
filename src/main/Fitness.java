package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.GeneticoUtils;

public class Fitness {
	
	private String cromossomoDesejado;
	
	private Logger log = LoggerFactory.getLogger(Fitness.class);
	
	/**
	 * @param cromossomoDesejado
	 */
	public Fitness(String cromossomoDesejado) {
		
		this.cromossomoDesejado = cromossomoDesejado;
	}

	public void calculaDistanceHamming(Map<Integer, Individuo> conjIndiv) {
		
		if (conjIndiv.size() <= 0) {
			throw new IllegalArgumentException("Parametro do metodo nao pode ser um conjunto vazio.");
		}
		
		for (int i = 1; i <= conjIndiv.size(); i++) {
			Integer distancia = GeneticoUtils.compara2StringsPorCaracteres(this.cromossomoDesejado, conjIndiv.get(i).getCromossomo());
			conjIndiv.get(i).setDistanceHamming(distancia);
		}
		
	}
	
	public void printDistanceHamming(Map<Integer, Individuo> conjIndiv) {
		
		StringBuilder dadosCompletos = new StringBuilder();
		StringBuilder conjHamming = new StringBuilder("Vetor de distancias de hamming: [");
		
		for (int i = 1; i <= conjIndiv.size(); i++) {
			Individuo individuo = conjIndiv.get(i);
			String cromossomo = individuo.getCromossomo();
			Integer distanceHamming = individuo.getDistanceHamming();
			dadosCompletos.append("Cromossomo: " + cromossomo + ". Distance hamming: " + distanceHamming + "\n");
			conjHamming.append(distanceHamming);
			conjHamming.append(",");
		}
		
		conjHamming.replace((conjHamming.length()-1), conjHamming.length(), "]");
		log.debug(dadosCompletos.toString());
		log.info(conjHamming.toString());
	}
	
	public void printValorFitness(Map<Integer, Individuo> conjIndiv) {
		List<Integer> valoresFitness = new ArrayList<Integer>();
		for (Integer key : conjIndiv.keySet()) {
			Individuo individuo = conjIndiv.get(key);
			valoresFitness.add(individuo.getValorFitness());
		}
		log.debug(valoresFitness.toString());
	}

	public String getCromossomoDesejado() {
		return cromossomoDesejado;
	}
	
	public void setCromossomoDesejado(String cromossomoDesejado) {
		this.cromossomoDesejado = cromossomoDesejado;
	}
}
