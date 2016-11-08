package entities;

public class ScoreTestCollector {
	public static int mScr, pScr, adScr, arScr, vaScr;
	
	public static void reset() {
		mScr = 0;
		pScr = 0;
		adScr = 0;
		arScr = 0;
		vaScr = 0;
	}
	
	public static void print() {
		System.out.println("\n");
		System.out.println("Methode: " + ScoreTestCollector.mScr);
		System.out.println("Path: " + ScoreTestCollector.pScr);
		System.out.println("Adress: " + ScoreTestCollector.adScr);
		System.out.println("Args: " + ScoreTestCollector.arScr);
		System.out.println("Var: " + ScoreTestCollector.vaScr);
	}
}
