package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import main.Individuo;

public class GeneticoUtils {

	/**
	 * Compara 2 strings de mesmo tamanho e retorna quantos caracteres
	 * estao diferentes
	 * @param a
	 * @param b
	 */
	public static Integer compara2StringsPorCaracteres(String a, String b) {
		
		int resultado = 0;
		
		if (a.length() != b.length()) {
			throw new IllegalArgumentException("Não é possível comparar duas strings de tamanhos diferentes.");
		}
		
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) != b.charAt(i)) {
				resultado++;
			}
		}
		
		return Integer.valueOf(resultado);
	}
	
	/**
	 * Realiza o "cruzamento" de 2 strings dado um crossoverPoint
	 * @param a
	 * @param b
	 * @param crossoverPoint
	 * @return
	 */
	public static List<String> cruzamento2Cromossomos(String a, String b, Integer crossoverPoint) {
		if (a.length() != b.length()) {
			throw new IllegalArgumentException("Não é possível cruzar 2 strings de tamanhos diferentes.");
		}
		
		String primeiraParte1 = a.substring(0, crossoverPoint);
		String primeiraParte2 = b.substring(0, crossoverPoint);
		String segundaParte1 = a.substring(crossoverPoint, a.length());
		String segundaParte2 = b.substring(crossoverPoint, b.length());
				
		String f1 = primeiraParte1 + segundaParte2;
		String f2 = primeiraParte2 + segundaParte1;
		
		List<String> lista = new ArrayList<String>();
		lista.add(f1);
		lista.add(f2);
		return lista;
	}
	
	public static String binarioInverso(char param) {
		if(param == '1') return "0";
		if(param == '0') return "1";
		throw new RuntimeException("O parametro da funcao não é 0 ou 1. Parametro: " + param);
	}
	
	public static String printFracao(Map<Integer, Individuo> populacao) {
		String retorno = "";
		for (Integer i : populacao.keySet()) {
			Individuo ind = populacao.get(i);
			retorno += "Individuo " + i + ", fracao: " + ind.getFracao().getMinimo() + " e " + ind.getFracao().getMaximo();
		}
		return retorno;
	}
}
