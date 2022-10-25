import java.util.ArrayList;
import java.util.HashMap;

class Llibreria {
	// Sparse vector representation as pairs of ints (idx, occurrences)
	//private ArrayList< Pair<Document, ArrayList< Pair<Integer, Integer> >> > docs;
	private ArrayList< Pair<Document, HashMap<Integer, Integer>>> docs;
	private int nDocs;
	
	Llibreria(){
		docs = new ArrayList< Pair<Document, HashMap<Integer, Integer>>>();
		nDocs = 0;
	}
	
	public void addDocument(Document d){
		//docs.add(new Pair<Document, ArrayList< Pair<Integer, Integer> >>(d, d.getWords());
		docs.add(new Pair<>(d, d.getWords()));
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
