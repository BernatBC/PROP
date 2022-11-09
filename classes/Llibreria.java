package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

/** Llibreria (conjunt) de documents.
 * @author Alexandre Ros i Roger (alexandre.ros.roger@estudiantat.upc.edu)
 */
public class Llibreria {

	// Sparse vector representation as pairs of ints (idx, tf-idf)
	//private ArrayList< Pair<Document, ArrayList< Pair<Integer, Double> >> > docs;
	private ArrayList< Pair<Document, HashMap<Integer, Double>>> docs;

	// HashMap that maps all words in the library to the number of documents it appears in.
	private HashMap<Integer, Integer> word_ocurrences;

	private int nDocs;
	
	public Llibreria(){
		docs = new ArrayList< Pair<Document, HashMap<Integer, Double>>>();
		nDocs = 0;
		word_ocurrences = new HashMap<>();
	}
	
	public Double computeCosinus(Document d1, Document d2){
		int i = 0;
		int j = 0;

		while (docs.get(i).getL() != d1) ++i;
		while (docs.get(j).getL() != d2) ++j;

		double len1 = 0;
		double len2 = 0;

		for (int w : docs.get(i).getR().keySet()){
			double val = docs.get(i).getR().get(w);
			len1 += (val*val);
		}
		
		for (int w : docs.get(j).getR().keySet()){
			double val = docs.get(j).getR().get(w);
			len2 += (val*val);
		}

		len1 = Math.sqrt(len1);
		len2 = Math.sqrt(len2);

		double dotproduct = 0;

		for (int w : docs.get(i).getR().keySet()){
			if (!docs.get(j).getR().containsKey(w)) continue;

			dotproduct += docs.get(i).getR().get(w) * docs.get(j).getR().get(w);
		}

		return (Double) dotproduct / (len1 * len2);
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
	
	public Set<Document> getSetDocuments(){
		Set<Document> mySet = new HashSet<Document>();

		for (int i = 0; i < docs.size(); ++i){
			mySet.add(docs.get(i).getL());
		}

		return mySet;
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
