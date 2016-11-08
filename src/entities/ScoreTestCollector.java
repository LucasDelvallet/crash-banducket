package entities;

public class ScoreTestCollector {
	private int mScr, pScr, adScr, arScr, vaScr;
	
	public ScoreTestCollector() {
		mScr = 0;
		pScr = 0;
		adScr = 0;
		arScr = 0;
		vaScr = 0;
	}
	
	public ScoreTestCollector(int mScr, int pScr, int adScr, int arScr, int vaScr) {
		this.mScr = mScr;
		this.pScr = pScr;
		this.adScr = adScr;
		this.arScr = arScr;
		this.vaScr = vaScr;
	}
	
	
	public int getmScr() {
		return mScr;
	}



	public void upMethScr() {
		this.mScr ++;
	}



	public int getpScr() {
		return pScr;
	}



	public void upPathScr() {
		this.pScr ++;
	}



	public int getAdScr() {
		return adScr;
	}



	public void upAddressScr() {
		this.adScr++;
	}



	public int getArScr() {
		return arScr;
	}



	public void upArgsScr() {
		this.arScr ++;
	}



	public int getVaScr() {
		return vaScr;
	}



	public void upVarsScr() {
		this.vaScr ++;
	}



	public void add(ScoreTestCollector sc) {
		this.mScr += sc.getmScr();
		this.pScr += sc.getpScr();
		this.adScr += sc.getAdScr();
		this.arScr += sc.getArScr();
		this.vaScr += sc.getVaScr();
	}
	
	public void print() {
		System.out.println("\n");
		System.out.println("Methode: " + this.mScr);
		System.out.println("Path: " + this.pScr);
		System.out.println("Adress: " + this.adScr);
		System.out.println("Args: " + this.arScr);
		System.out.println("Var: " + this.vaScr);
	}
}
