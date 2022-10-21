import java.time.LocalDate;

class Document {
	
	private Frase author;
	private Frase title;
	private boolean isFav;
	private String path;
	private LocalDate date;
	private Contingut cont;
	
	Document(){
		// Constructora
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
	
	public String toString(){
		StringBuilder str = new StringBuilder();
		
		str.append(title + '\n');
		str.append(author + '\n');
		str.append(cont);
		
		return str.toString();
	}
}
