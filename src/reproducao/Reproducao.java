package reproducao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import selecao.Selecao;
import util.GeneticoUtils;

import main.Individuo;

public class Reproducao {
	
	Logger log = LoggerFactory.getLogger(Reproducao.class);

	// crossover probability
	private Double pc;
	
	public Reproducao(Double pc) {
		this.pc = pc;
	}
	
	public void processoCruzamento(Map<Integer, Individuo> populacao) {
		List<Double> listPc = new LinkedList<Double>();
		
		for (int i = 1; i <= populacao.size(); i = i + 2) {
			double sorteio = Math.random();
			listPc.add(sorteio);
			if (sorteio <= pc) {
				List<Individuo> filhos = cruza(populacao.get(i), populacao.get(i+1));
				populacao.put(i, filhos.get(0));
				populacao.put(i+1, filhos.get(1));
			}
		}
		log.debug("Array de crossover probability: " + listPc);
	}
	
	protected List<Individuo> cruza(Individuo i1, Individuo i2) {
		log.debug("Cruzamento de " + i1 + " e " + i2);
		// crossover point
		Integer cp = (int)(Math.random() * i1.getCromossomo().length()) + 1;
		List<String> cromossomosFilhos = GeneticoUtils.cruzamento2Cromossomos(i1.getCromossomo(), i2.getCromossomo(), cp);
		Individuo filho1 = new Individuo(cromossomosFilhos.get(0));
		Individuo filho2 = new Individuo(cromossomosFilhos.get(1));
		
		filho1.setIndiceNaPopulacao(i1.getIndiceNaPopulacao());
		filho2.setIndiceNaPopulacao(i2.getIndiceNaPopulacao());
		
		List<Individuo> filhos = new ArrayList<Individuo>();
		filhos.add(filho1);
		filhos.add(filho2);
		log.debug("Resultado deste cruzamento: " + filhos + " com crossover point = " + cp);
		return filhos;
	}
}


