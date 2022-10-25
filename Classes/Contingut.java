class Contingut {
	
	private String plaintext;
	private Frase[] phrases;
	private double[] tf;
	private ArrayList<Pair<int, int>> words;
	
	Contingut(){
		// Constructor
	}
	
	public double getTf(int phr, int idx){
		// Get the TF value of word from phrase 'phr' in position 'idx'
		int off = 0;
		
		for (int i = 0; i < phr; ++i) off += phrases[phr].getLength();
			
		return tf[off+idx];
	}
	
	public ArrayList<Pair<int, int>> getWords(){
		return words;
	}
	
	public String toString(){
		return plaintext;
	}
}
