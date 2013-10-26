package main;

import java.util.Map;
import java.util.TreeMap;

public class Inicializacao {

	/**
	 * Informa a largura do individuo e a quantidade de individuos a ser gerada 
	 * @param largura
	 * @param quantidade
	 */
	public static Map<Integer, Individuo> ramdom(Integer quantIndiv, Integer largura) {
		
		Map<Integer, Individuo> conjuntoIndiv = new TreeMap<Integer, Individuo>();
		
		for (int i = 1; i <= quantIndiv; i++) {

			Individuo individuo = new Individuo();
			StringBuilder cromossomo = new StringBuilder();
			
			for (int j = 0; j < largura; j++) {
				double random = Math.random();
				cromossomo.append((random > 0.5) ? "1" : "0");
			}
			individuo.setCromossomo(cromossomo.toString());
			individuo.setIndiceNaPopulacao(i);
			
			conjuntoIndiv.put(i, individuo);
		}
		
		return conjuntoIndiv;
	}
}
