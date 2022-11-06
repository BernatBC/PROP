package Classes;

import java.util.ArrayList;
import java.util.HashMap;

/** Llibreria (conjunt) de documents.
 * @author Alexandre Ros i Roger (alexandre.ros.roger@estudiantat.upc.edu)
 */
class Llibreria {

	// Sparse vector representation as pairs of ints (idx, tf-idf)
	//private ArrayList< Pair<Document, ArrayList< Pair<Integer, Double> >> > docs;
	private ArrayList< Pair<Document, HashMap<Integer, Double>>> docs;

	// HashMap that maps all words in the library to the number of documents it appears in.
	private HashMap<Integer, Integer> word_ocurrences;

	private int nDocs;
	
	Llibreria(){
		docs = new ArrayList< Pair<Document, HashMap<Integer, Double>>>();
		nDocs = 0;
	}
	
	public void addDocument(Document d){
		HashMap<Integer, Double> tfs = d.getTF();
		HashMap<Integer, Double> TFIDF = new HashMap<Integer, Double>();

		// Since there can't be repeated keys, no need to check if the word has already been processed
		for (int index : tfs.keySet()){
			if (word_ocurrences.containsKey(index)) word_ocurrences.put(index, word_ocurrences.get(index) + 1);
			else word_ocurrences.put(index, 1);

			TFIDF.put(index, tfs.get(index) * ((double) word_ocurrences.get(index) / nDocs));
		}

		docs.add(new Pair<>(d, TFIDF));
		++nDocs;
	} 
	
	public void deleteDocument(Document d){
		for (int i = 0; i < docs.size(); ++i){
			if (d == docs.get(i).getL()){

				HashMap<Integer, Double> words_to_remove = docs.get(i).getR();

				for (int index : words_to_remove.keySet()){
					word_ocurrences.put(index, word_ocurrences.get(index) - 1);
					if (word_ocurrences.get(index) == 0) word_ocurrences.remove(index);
				}

				docs.remove(i);
				--nDocs;

				// Now we need to update all IDFs on documents that contained that specific word(s)
				for (int j = 0; j < docs.size(); ++j){
					
					HashMap<Integer, Double> words_of_doc = docs.get(j).getR();

					for (int w : words_to_remove.keySet()){
						
						if (words_of_doc.containsKey(w)){
							
							words_of_doc.put(w, docs.get(j).getL().getTFofWord(w) * (double) word_ocurrences.get(j) / nDocs);
						}
					}

				}

				return;
			}
		}
	}

	public Pair<Document, Boolean> getDocument(String author, String title){
		// Donat un autor i títol, ens retorna el document
		for (int i = 0; i < docs.size(); ++i){
			if (docs.get(i).getL().getAutor().toString().equals(author) && docs.get(i).getL().getTitol().toString().equals(title)){
				return new Pair<>(docs.get(i).getL(), true);
			}
		}

		// Nothing found
		return new Pair<>(null, false);

	}
	
	public Llibreria getPreferits(){
		Llibreria favLib = new Llibreria();
		
		
		for (int i = 0; i< docs.size(); ++i){
			if (docs.get(i).getL().getFavourite()){
				// Si el document és preferit
				favLib.addDocument(docs.get(i).getL());
			}
		}
		
		return favLib;
	}
	
	public Document getIessim(int i){
		return docs.get(i).getL();
	}

	public int getNdocs(){
		return nDocs;
	}
	
	public String toString(){
		StringBuilder str = new StringBuilder("");

		for (int i = 0; i < docs.size(); ++i){
			str.append(docs.get(i).getL().getTitol());
			str.append("\n");
			str.append(docs.get(i).getL().getAutor());
			str.append("\n");
			str.append("---------------------\n");
		}

		return str.toString();
	}
}
