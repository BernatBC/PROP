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
	private ArrayList< Pair<Document, HashMap<Integer, Double>>> docs0;

	// Sparse vector representation as pairs of ints (idx, ocurrences)
	// Second strategy of weight assignment
	private ArrayList<Pair<Document, HashMap<Integer, Double>>> docs1;


	// HashMap that maps all words in the library to the number of documents it appears in.
	private HashMap<Integer, Integer> word_ocurrences;

	private int nDocs;
	
	/**
	 * Constructora d'una Llibreria per defecte.
	 */
	public Llibreria(){
		docs0 = new ArrayList< Pair<Document, HashMap<Integer, Double>>>();
		docs1 = new ArrayList<Pair<Document, HashMap<Integer, Double>>>();
		nDocs = 0;
		word_ocurrences = new HashMap<>();
	}
	
	/** Mètode que ens calcula el cosinus entre dos documents existents en la llibreria.
	 * 
	 * @param d1 Referència al primer document.
	 * @param d2 Referència al segon document.
	 * @return El cosinus entre els vectors tf-idf que representen d1 i d2.
	 */
	public Double computeCosinus(Document d1, Document d2, int mode){
		ArrayList<Pair<Document, HashMap<Integer, Double>>> docs;

		if (mode == 0) docs = docs0;
		else docs = docs1;
		
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
	
	/** Mètode per a afegir un document a la llibreria.
	 * 
	 * @param d Document a afegir.
	 */
	public void addDocument(Document d){
		HashMap<Integer, Double> tfs = d.getTF();
		HashMap<Integer, Double> TFIDF = new HashMap<Integer, Double>();
		HashMap<Integer, Double> OCURR = new HashMap<Integer, Double>();

		++nDocs;

		// Since there can't be repeated keys, no need to check if the word has already been processed
		for (int index : tfs.keySet()){
			//System.out.println("Processing word #"+index+"...");

			if (word_ocurrences.containsKey(index)) word_ocurrences.put(index, word_ocurrences.get(index) + 1);
			else word_ocurrences.put(index, 1);

			TFIDF.put(index, tfs.get(index) * ((double) nDocs / word_ocurrences.get(index)));
			OCURR.put(index, (double) d.getContingut().getWords().get(index));

		}

		// Now we need to update the IDF of every word added
		for (int j = 0; j < docs0.size(); ++j){

			HashMap<Integer, Double> words_of_doc = docs0.get(j).getR();
			
			for (int w : words_of_doc.keySet()){
									
				words_of_doc.put(w, docs0.get(j).getL().getTFofWord(w) * (double) nDocs / word_ocurrences.get(w));
			}
		}

		docs0.add(new Pair<>(d, TFIDF));
		docs1.add(new Pair<>(d, OCURR));
	} 
	
	/** Mètode per a eliminar un document de la llibreria.
	 * 
	 * @param d Document a eliminar.
	 */
	public void deleteDocument(Document d){
		for (int i = 0; i < docs0.size(); ++i){
			if (d == docs0.get(i).getL()){

				HashMap<Integer, Double> words_to_remove = docs0.get(i).getR();

				for (int index : words_to_remove.keySet()){
					word_ocurrences.put(index, word_ocurrences.get(index) - 1);
					if (word_ocurrences.get(index) == 0) word_ocurrences.remove(index);
				}

				docs0.remove(i);
				docs1.remove(i);
				--nDocs;

				// Now we need to update all IDFs
				for (int j = 0; j < docs0.size(); ++j){
					
					HashMap<Integer, Double> words_of_doc = docs0.get(j).getR();

					for (int w : words_of_doc.keySet()){
													
						words_of_doc.put(w, docs0.get(j).getL().getTFofWord(w) * (double) nDocs / word_ocurrences.get(w));
					}

				}

				return;
			}
		}
	}

	/** Getter d'una referència a un document donat el seu autor i títol en Strings.
	 * 
	 * @param nomAutor String del nom de l'autor.
	 * @param nomTitol String del títol del document.
	 * @return Un parell de Document (el document cercat) i booleà, on el booleà és <b>false</b> si el document no existeix.
	 */
	public Pair<Document, Boolean> getDocument(String author, String title){
		// Donat un autor i títol, ens retorna el document
		for (int i = 0; i < docs0.size(); ++i){
			if (docs0.get(i).getL().getAutor().toString().equals(author) && docs0.get(i).getL().getTitol().toString().equals(title)){
				return new Pair<>(docs0.get(i).getL(), true);
			}
		}

		// Nothing found
		return new Pair<>(null, false);

	}
	
	/** Mètode que retorna una nova llibreria amb tots els documents marcats com a preferits.
	 * 
	 * @return Llibreria (subconjunt de la llibreria actual) que conté només els documents preferits.
	 */
	public Llibreria getPreferits(){
		Llibreria favLib = new Llibreria();
		
		
		for (int i = 0; i< docs0.size(); ++i){
			if (docs0.get(i).getL().getFavourite()){
				// Si el document és preferit
				favLib.addDocument(docs0.get(i).getL());
			}
		}
		
		return favLib;
	}
	
	/** Mètode que retorna l'i-éssim document inserit a la llibreria.
	 * 
	 * @param i Índex del document a la llibreria.
	 * @return Una referència al Document i-éssim.
	 */
	public Document getIessim(int i){
		return docs0.get(i).getL();
	}

	/** Getter del nombre de documents totals a la llibreria.
	 * 
	 * @return Natural que indica el nombre de documents actuals a la llibreria.
	 */
	public int getNdocs(){
		return nDocs;
	}
	
	/** Mètode que retorna el conjunt de documents de la llibreria amb un Set.
	 * 
	 * @return Un Set de referències als documents de la llibreria.
	 */
	public Set<Document> getSetDocuments(){
		Set<Document> mySet = new HashSet<Document>();

		for (int i = 0; i < docs0.size(); ++i){
			mySet.add(docs0.get(i).getL());
		}

		return mySet;
	}

	public String toString(){
		StringBuilder str = new StringBuilder("");

		for (int i = 0; i < docs0.size(); ++i){
			str.append(docs0.get(i).getL().getTitol());
			str.append("\n");
			str.append(docs0.get(i).getL().getAutor());
			str.append("\n");
			str.append("---------------------\n");
		}

		return str.toString();
	}
}