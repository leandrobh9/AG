package main;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reproducao.Reproducao;
import selecao.Selecao;

public class AlgoritmoGenetico {

	private final Integer numGeracoes = 50;
	private final Double taxaMutacao = 0.1;
	private final Fitness fitness = new Fitness("010010010010");
	private final Integer numIndivi = 20;
	private final Integer numGenes = 12;
	private final Double cp = 0.6;
	
	private Selecao selecao;
	private Reproducao reproducao;
	private Mutacao mutacao;
	private Map<Integer, Individuo> populacao;
	
	Logger log = LoggerFactory.getLogger(AlgoritmoGenetico.class);
	
	public void principal() throws Exception{
		
		this.inicializacao(numIndivi, numGenes);
		
		for (int i = 1; i <= numGeracoes; i++) {
			
			
			if (this.avaliacaoFitness()) {
				log.info("Solucao final encontra-se na populacao: " + this.populacao + ".");
				return;
			}
			
			log.info("Geracao " + i + "...");
			this.calculaDistanciaHamming();
			this.selecao();
			this.reproducao();
			this.mutacao();
		}
		log.info("Solucao nao encontrada. ");
				
	}
	
	public void execucaoAlgoritmo() throws Exception {
			
	}
	
	public void inicializacao(Integer numIndivi, Integer numGenes) {
		this.populacao = Inicializacao.ramdom(numIndivi, numGenes);
		log.debug("Resultado da Inicializacao: " + populacao);
	}
	
	public void calculaDistanciaHamming() {
		// hamming distance
		this.fitness.calculaDistanceHamming(this.populacao);
		this.fitness.printDistanceHamming(this.populacao);
		this.fitness.printValorFitness(this.populacao);
	}
	
	public Boolean avaliacaoFitness() {
	
		for (Individuo i : this.populacao.values()) {
			if (i.getCromossomo().equals(this.fitness.getCromossomoDesejado())) {
				log.info("O individuo de cromossomo " + i.getCromossomo() + " é igual a função fitness. ");
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}
	
	public void selecao() throws Exception {
		selecao = new Selecao();
		selecao.preparaRouletteWhellSelection(this.populacao);
		selecao.printRoulette(this.populacao);
		selecao.rouletteWhellSelection(this.populacao);
		this.populacao = selecao.getPopulacaoSelecionada();
	}
	
	public void reproducao() {
		reproducao = new Reproducao(this.cp);
		reproducao.processoCruzamento(this.populacao);
		log.debug("Populacao ao final de reproducao" + this.populacao);
	}
	
	public void mutacao(){
		this.mutacao = new Mutacao(this.taxaMutacao);
		this.mutacao.exec(this.populacao);
		log.debug("Populacao ao final de reproducao" + this.populacao);
	}
}
