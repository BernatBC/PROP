import java.util.ArrayList;

class Llibreria {
	// Sparse vector representation as pairs of ints (idx, occurrences)
	private ArrayList< Pair<Document, ArrayList< Pair<int, int> >> > docs;
	private int nDocs;
	
	Llibreria(){
		docs = new ArrayList< Pair<Document, ArrayList< Pair<int, int> >> >(); 
		nDocs = 0;
	}
	
	public void addDocument(Document d){
		docs.add(new Pair<Document, ArrayList< Pair<int, int> >>(d, d.getWords());
		++nDocs;
	} 
	
	public void deleteDocument(Document d){
		for (int i = 0; i < docs.size(); ++i){
			if (d == docs[i].getL()){
				docs.remove(i);
				--nDocs;
				return;
			}
		}
	}
	
	public Llibreria getPreferits(){
		Llibreria favLib = new Llibreria();
		
		
		for (int i = 0; i< docs.size(); ++i){
			if (docs[i].getL().getFavourite()){
				// Si el document és preferit
				Llibreria.addDoc(docs[i].getL());
			}
		}
		
		return favLib;
	}
	
	public int getNdocs(){
		return nDocs;
	}
	
	public String toString()
		for (int i = 0; i < docs.size(); ++i){
			System.out.println(docs[i].getL().getTitol());
			System.out.print(docs[i].getL().getAutor());
			System.out.println("---------------------");
		}
	}
}
