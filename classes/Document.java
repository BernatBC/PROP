package classes;

import java.time.LocalDate;
import java.util.HashMap;

/** Document.
 * @author Alexandre Ros i Roger (alexandre.ros.roger@estudiantat.upc.edu)
 */
public class Document {
	
	private Frase author;
	private Frase title;
	private boolean isFav;
	private String path;
	private LocalDate date;
	private Contingut cont;
	
	public Document(Frase author, Frase title, boolean isFav, String path, LocalDate date, Contingut cont){
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

	public void setData(LocalDate d){
		date = d;
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
		str.append(author + "\n\n");
		str.append(cont + "\n\n");
		
		str.append("Date creation: "+date.toString()+" / Marked as favourite: ");
		if (isFav) str.append("YES");
		else str.append("NO");

		str.append("\n");

		return str.toString();
	}

	public boolean conteSequencia(String seq){
		return (author.conteCaracters(seq) || title.conteCaracters(seq) || cont.conteSequencia(seq));
	}
}
