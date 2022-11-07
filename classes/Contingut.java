package classes;

import java.util.HashMap;


/** Contingut d'un document.
 * @author Alexandre Ros i Roger (alexandre.ros.roger@estudiantat.upc.edu)
 */
class Contingut {
	
	private String plaintext;
	private Frase[] phrases;
	private HashMap<Integer, Integer> words;
	private int n_paraules;
	
	Contingut(String plaintext, Frase[] phrases){
		this.plaintext = plaintext;

		n_paraules = 0;

		this.phrases = phrases.clone();

		words = new HashMap<Integer, Integer>();

		for (int p = 0; p < phrases.length; ++p){
			HashMap<Integer, Integer> arrWords = phrases[p].donaWords();
			
			for (int index : arrWords.keySet()){

				int ocurr = arrWords.get(index);

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
	
	public double getTFofWord(int index){
		if (!words.containsKey(index)) return -1.0;

		return (double) words.get(index) / n_paraules;
	}

	public Frase[] getFrases(){
		return phrases;
	}
	
	public HashMap<Integer, Integer> getWords(){
		return words;
	}

	public HashMap<Integer, Double> getTF(){

		HashMap<Integer, Double> tf_map = new HashMap<Integer, Double>();

		for (int index : words.keySet()){
			tf_map.put(index, getTFofWord(index));
		}

		return tf_map;
	}

	public String toString(){
		return plaintext;
	}
}
