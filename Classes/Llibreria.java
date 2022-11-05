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
	private int nDocs;
	
	Llibreria(){
		docs = new ArrayList< Pair<Document, HashMap<Integer, Double>>>();
		nDocs = 0;
	}
	
	public void addDocument(Document d){
		//docs.add(new Pair<Document, ArrayList< Pair<Integer, Integer> >>(d, d.getWords());
		docs.add(new Pair<>(d, d.getTFIDF()));
		++nDocs;
	} 
	
	public void deleteDocument(Document d){
		for (int i = 0; i < docs.size(); ++i){
			if (d == docs.get(i).getL()){
				docs.remove(i);
				--nDocs;
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
