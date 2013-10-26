package main;

public class Execucao {

	public static void main(String[] args) {
		AlgoritmoGenetico algoritmoGenetico = new AlgoritmoGenetico();
		
		try {
			algoritmoGenetico.principal();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
