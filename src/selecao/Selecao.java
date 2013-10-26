package selecao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.GeneticoUtils;

import main.AlgoritmoGenetico;
import main.Fracao;
import main.Individuo;

public class Selecao {
	
	Map<Integer, Individuo> populacaoSelecionada;
	
	Logger log = LoggerFactory.getLogger(Selecao.class);

	/**
	 * Realiza a divisao por fracao
	 */
	public void preparaRouletteWhellSelection(Map<Integer, Individuo> populacao) {
		Integer totalFitness = calculaTotalFitness(populacao);
		
		log.info("RouletteWhellSelection...");
		log.debug("Total Fitness: " + totalFitness);
		
		BigDecimal minAtual = new BigDecimal(0);
		
		for (Integer indice : populacao.keySet()) {
			Individuo individuo = populacao.get(indice);
			Integer valorFitness = individuo.getValorFitness();
			
			BigDecimal f = new BigDecimal(valorFitness).divide(new BigDecimal(totalFitness), 4, RoundingMode.HALF_UP);
			
			BigDecimal fracaoIndivi = f.multiply(new BigDecimal(360.0));
			BigDecimal maximoAtual = fracaoIndivi.add(minAtual);
			if ( (maximoAtual.compareTo(new BigDecimal(360)) > 1) || indice.equals(populacao.size())) 
				maximoAtual = new BigDecimal(360);
			
			individuo.getFracao().setMinimo(minAtual);
			individuo.getFracao().setMaximo(maximoAtual);
			minAtual = fracaoIndivi.add(minAtual);
		}
		
		log.debug("---------------------------------------------");
		log.debug("Final da preparacao da RouletteWhellSelection");
		for (Integer i : populacao.keySet()) {
			Individuo j = populacao.get(i);
			log.debug("Individuo (" + j.getIndiceNaPopulacao() + ") " + j + " Fracao: " + j.getFracao());
		}
		log.debug("---------------------------------------------");
	}
	
	public void rouletteWhellSelection(Map<Integer, Individuo> populacao) throws Exception {
		
		populacaoSelecionada = new TreeMap<Integer, Individuo>();
		
		List<BigDecimal> numerosSorteados = new ArrayList<BigDecimal>();
		for (int i = 0; i < populacao.size(); i++){
			BigDecimal numeroSorteado = new BigDecimal(Math.random() * 360);
			numerosSorteados.add(numeroSorteado);
		}
		log.debug("Numeros sorteados (random): " + numerosSorteados);
		
		log.debug("Resultado da seleção: ");
		
		for (int i = 1; i <= populacao.size(); i++){
			Individuo individuoSelecionado = getIndividuoCorrepondente(numerosSorteados.get(i-1), populacao);
			
			// inicializa individuo
			Individuo ind = new Individuo();
			ind.setCromossomo(individuoSelecionado.getCromossomo());
			ind.setDistanceHamming(individuoSelecionado.getDistanceHamming());
			ind.setFracao(null);
			ind.setIndiceNaPopulacao(i);
			
			log.debug("Individuo selecionado: " + ind.getIndiceNaPopulacao() + ". Cromossomo: " + ind);
			this.populacaoSelecionada.put(i, ind);
		}
		
		log.debug("Populacao selecionada: ");
		log.debug(this.populacaoSelecionada.toString());
	}
	
	public void printRoulette(Map<Integer, Individuo> populacao) {
		for (Integer indice : populacao.keySet()) {
			Individuo j = populacao.get(indice);
			log.debug("Individuo (" + indice + ") " + j + " Fracao: " + j.getFracao() + " indiceNaPopulacao: " + j.getIndiceNaPopulacao());
		}
	}
	
	public Integer calculaTotalFitness(Map<Integer, Individuo> populacao) {
		int total = 0;
		for (Integer i : populacao.keySet()) {
			total += populacao.get(i).getValorFitness();
		}
		return Integer.valueOf(total);
	}
	
	public Individuo getIndividuoCorrepondente(BigDecimal valor, Map<Integer, Individuo> populacao) throws Exception {
		for (Integer key : populacao.keySet()) {
			Individuo individuo = populacao.get(key);
			if ((valor.compareTo(individuo.getFracao().getMinimo())>0) && (valor.compareTo(individuo.getFracao().getMaximo()) <= 0)) {
				return individuo;
			}
		}
		throw new Exception("Não foi possível obter nenhum valor de: " + valor
		+ ". Para população: " + GeneticoUtils.printFracao(populacao));
	}
	
	
	public Map<Integer, Individuo> getPopulacaoSelecionada() {
		return populacaoSelecionada;
	}
}
