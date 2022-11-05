package Classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/** Contingut d'un document.
 * @author Alexandre Ros i Roger (alexandre.ros.roger@estudiantat.upc.edu)
 */
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
	
	public HashMap<Integer, Double> getTFIDF(){

		HashMap<Integer, Double> tfidf = new HashMap<Integer, Double>();

		for (int p = 0; p < phrases.length; ++p){
			ArrayList<Pair<Integer, Integer>> arrWords = phrases[p].donaWords();
			HashMap<Integer, Double> idfs = phrases[p].getIdfs();

			for (int i = 0; i < arrWords.size(); ++i){
				int index = arrWords.get(i).getL();

				if (tfidf.containsKey(index)) continue;

				tfidf.put(arrWords.get(i).getL(), getTf(index) * idfs.get(index));
				
			}
		}

		return tfidf;
	}

	public String toString(){
		return plaintext;
	}
}
