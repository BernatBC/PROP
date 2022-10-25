import java.time.LocalDate;
import java.util.ArrayList;

class Document {
	
	private Frase author;
	private Frase title;
	private boolean isFav;
	private String path;
	private LocalDate date;
	private Contingut cont;
	
	Document(Frase author, Frase title, boolean isFav, String path, LocalDate date, Contingut cont){
		this.author = author;
		this.title = title;
		this.isFav = isFav;
		this.path = path;
		this.date = date;
		this.cont = cont;
	}
	
	public void setFavourite(boolean val){
		isFav = val;
	}
	
	public Frase getAutor(){
		return author;
	}
	
	public Frase getTitol(){
		return titol;
	}
	
	public LocalDate getData(){
		return date; // LocalDate is immutable type
	}
	
	public boolean getFavourite(){
		return isFav;
	}
	
	// Index of word and number of occurrences
	public ArrayList<Pair<int, int>> getWords(){
		return cont.getWords();
	}

	
	public String toString(){
		StringBuilder str = new StringBuilder();
		
		str.append(title + '\n');
		str.append(author + '\n');
		str.append(cont);
		
		return str.toString();
	}
}
