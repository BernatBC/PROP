package Classes;

import java.time.LocalDate;
import java.util.HashMap;

/** Document.
 * @author Alexandre Ros i Roger (alexandre.ros.roger@estudiantat.upc.edu)
 */
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

	public Contingut getContingut(){
		return cont;
	}
	
	public Frase getTitol(){
		return title;
	}
	
	public LocalDate getData(){
		return date; // LocalDate is immutable type
	}
	
	public boolean getFavourite(){
		return isFav;
	}

	public double getTFofWord(int index)
	{
		return cont.getTFofWord(index);
	}

	// Index of word -> TF for the document
	public HashMap<Integer, Double> getTF(){
		return cont.getTF();
	}
	
	public String toString(){
		StringBuilder str = new StringBuilder();
		
		str.append(title + "\n");
		str.append(author + "\n");
		str.append(cont);
		
		return str.toString();
	}
}
