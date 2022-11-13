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
	
	/**
	 * Constructora d'un Document.
	 * 
	 * @param author Frase que representa l'autor del document.
	 * @param title Frase que representa el títol del document.
	 * @param isFav Booleà que ens indica si inicialment el document està marcat com a preferit o no.
	 * @param path Path que té el document en el Sistema de Fitxers.
	 * @param date Data de creació del document.
	 * @param cont Contingut del document.
	 */
	public Document(Frase author, Frase title, boolean isFav, String path, LocalDate date, Contingut cont){
		this.author = author;
		this.title = title;
		this.isFav = isFav;
		this.path = path;
		this.date = date;
		this.cont = cont;
	}
	
	/** Mètode setter per a l'atribut <i>isFav</i>
	 * 
	 * @param val Booleà setter.
	 */
	public void setFavourite(boolean val){
		isFav = val;
	}
	
	/** Getter de la Frase Autor del document.
	 * 
	 * @return Frase de l'Autor.
	 */
	public Frase getAutor(){
		return author;
	}

	/** Getter del Contingut del document.
	 * 
	 * @return Contingut del document.
	 */
	public Contingut getContingut(){
		return cont;
	}
	
	/** Getter de la Frase Títol del document.
	 * 
	 * @return Frase del Títol.
	 */
	public Frase getTitol(){
		return title;
	}
	
	/** Getter de la data de creació del document.
	 * 
	 * @return Data (LocalDate) de creació del document.
	 */
	public LocalDate getData(){
		return date; // LocalDate is immutable type
	}

	/** Setter de la data de creació del document.
	 * 
	 * @param d LocalDate de la nova data de creació.
	 */
	public void setData(LocalDate d){
		date = d;
	}
	
	/** Getter del booleà <i>isFav</i>
	 * 
	 * @return Booleà que ens indica si el document està marcat com a preferit.
	 */
	public boolean getFavourite(){
		return isFav;
	}

	/** Funció que retorna la Term Frequency (TF) d'una paraula al contingut del document.
	 * 
	 * @param index Índex de la paraula de la qual volem obtenir el TF.
	 * @return Retorna un double, el TF de la paraula 'índex'. Si la paraula no està al contingut retorna -1.
	 */
	public double getTFofWord(int index)
	{
		return cont.getTFofWord(index);
	}

	/** Getter de les Term Frequencies de totes les paraules del contingut del document.
	 * 
	 * @return Reotnra un HashMap que mapeja cada índex de paraula amb el seu TF (double) al contingut.
	 */
	public HashMap<Integer, Double> getTF(){
		return cont.getTF();
	}
	
	/** Mètode que ens indica si la seqüència 'seq' existeix al títol, autor o contingut del nostre document.
	 * 
	 * @param seq La seqüència que volem cercar.
	 * @return Booleà que indica si la seqüència 'seq' es troba al document (o al títol, o a l'autor, o al contingut).
	 */
	public boolean conteSequencia(String seq){
		return (author.conteCaracters(seq) || title.conteCaracters(seq) || cont.conteSequencia(seq));
	}

	public String toString(){
		StringBuilder str = new StringBuilder("\n");
		
		str.append(title + "\n");
		str.append(author + "\n\n");
		str.append(cont + "\n\n");
		
		str.append("Date creation: "+date.toString()+" / Marked as favourite: ");
		if (isFav) str.append("YES");
		else str.append("NO");

		str.append("\n");

		return str.toString();
	}

}