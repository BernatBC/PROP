import java.util.ArrayList;
import java.util.HashMap;

class Contingut {
	
	private String plaintext;
	private Frase[] phrases;
	private HashMap<Integer, Double> tf;
	private HashMap<Integer, Integer> words;
	private int n_paraules;
	
	Contingut(String plaintext, Frase[] phrases){
		this.plaintext = plaintext;

		n_paraules = 0;

		this.phrases = phrases.clone();

		words = new HashMap<Integer, Integer>();

		for (int p = 0; p < phrases.length; ++p){
			ArrayList<Pair<Integer, Integer>> arrWords = phrases[p].donaWords();
			
			for (int w = 0; w < arrWords.size(); ++w){
				int index = arrWords.get(w).getL();
				int ocurr = arrWords.get(w).getR();

				if (words.containsKey(index)){
					// If the word has already been inserted
					words.put(index, words.get(index) + ocurr);
				} else {
					words.put(index, ocurr);
				}

				n_paraules += ocurr;
			}
		}
	}
	
	public double getTf(int index){
		int rawcount = 0;

		// Get the TF value of word 'index'
		for (int p = 0; p < phrases.length; ++p){
			ArrayList<Pair<Integer, Integer>> arrWords = phrases[p].donaWords();

			for (int i = 0; i < arrWords.size(); ++i){
				if (arrWords.get(i).getL() == index){
					rawcount += arrWords.get(i).getR();
					break;
				}
			}
		}

		return (double) rawcount / n_paraules;
	}
	
	public HashMap<Integer, Integer> getWords(){
		return words;
	}
	
	public String toString(){
		return plaintext;
	}
}
