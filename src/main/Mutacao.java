package main;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import selecao.Selecao;
import util.GeneticoUtils;

public class Mutacao {
	
	Double taxa;
	
	public Mutacao(Double taxa) {
		this.taxa = taxa;
	}
	
	Logger log = LoggerFactory.getLogger(Mutacao.class);

	public void exec(Map<Integer, Individuo> populacao) {
		log.info("Mutação... ");
		
		for (Integer i : populacao.keySet()) {
			
			Individuo individuo = populacao.get(i);
			
			for (int j = 1; j <= individuo.getCromossomo().length(); j++) {
				if (Math.random() <= this.taxa) {
					String cromossomo = individuo.getCromossomo();
					
					String primeiraParte = cromossomo.substring(0, j-1);
					String mutacao = GeneticoUtils.binarioInverso(cromossomo.charAt(j-1));
					String segundaParte = cromossomo.substring((j), cromossomo.length());
							
					individuo.setCromossomo(primeiraParte + mutacao + segundaParte);
					log.debug("Mutação para " + cromossomo + ". Gene: " + (j) + ". Resultante: " + individuo.getCromossomo());
				}
			}
		}
	}
}
