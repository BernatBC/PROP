package classes;

import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.time.DateTimeException;
import java.time.LocalDate;

/** Classe controladora de les classes de domini.
 * @author Alexandre Ros i Roger (alexandre.ros.roger@estudiantat.upc.edu)
 */
public class CtrlDomini {

	private Vocabulari vocab;
	private Llibreria lib;
	private ConsultaData CD;
	private ConsultaTitol CT;
	private ConsultaPreferit CP;
	private ConsultaAutors CA;

	static private 
	Comparator<Document> documentDataComparator = new Comparator<Document>(){
		@Override
		public int compare(Document d1, Document d2){
			LocalDate v1 = d1.getData(); 
			LocalDate v2 = d2.getData();

			if (v1.isBefore(v2)) return -1;
			else if (v1.isAfter(v2)) return 1;
			else return 0;
		}
	};

	static private
	Comparator<Document> documentFavouriteComparator = new Comparator<Document>(){
		@Override
		public int compare(Document d1, Document d2){
			boolean f1 = d1.getFavourite();
			boolean f2 = d2.getFavourite();

			if (f1 && !f2) return -1;
			else if (f2 && !f1) return 1;
			else return 0;
		}
	};

	static private 
	Comparator<Document> documentAuthorComparator = new Comparator<Document>(){
		@Override
		public int compare(Document d1, Document d2){
			String a1 = d1.getAutor().toString();
			String a2 = d2.getAutor().toString();

			return a1.compareTo(a2);
		}
	};

	static private 
	Comparator<Document> documentTitleComparator = new Comparator<Document>(){
		@Override
		public int compare(Document d1, Document d2){
			String a1 = d1.getTitol().toString();
			String a2 = d2.getTitol().toString();

			return a1.compareTo(a2);
		}
	};


	public boolean docExists(String title, String author){
		return lib.getDocument(author, title).getR();
	}

	/** Mètode static per a ordenar llistes de documents segons un tipus d'ordenació.
	 * 
	 * Els tipus d'ordenació són per dates (0), per preferit (1), per autor alfabèticament (2) o per títol alfabèticament (3).
	 * 
	 * @param docs La llista de Documents a ordenar per un cert criteri.
	 * @param type Enter que representa el criteri d'ordenació.
	 * 
	 */
	static public ArrayList<Document> sortDocuments(ArrayList<Document> docs, int type){
		
		switch (type){
			case 0:
			// Per dates
			docs.sort(documentDataComparator);
			break;
			
			case 1:
			// Per preferit
			docs.sort(documentFavouriteComparator);
			break;

			case 2:
			// Per autor
			docs.sort(documentAuthorComparator);
			break;

			case 3:
			// Per títol
			docs.sort(documentTitleComparator);
		}

		return docs;
	}

	/** Mètode static per a ordenar conjunts de documents segons un tipus d'ordenació.
	 * 
	 * Els tipus d'ordenació són per dates (0), per preferit (1), per autor alfabèticament (2) o per títol alfabèticament (3)
	 * 
	 * @param docs El conjunt de Documents a ordenar per un cert criteri.
	 * @param type Enter que representa el criteri d'ordenació.
	 * @return La llista de Documents ordenada.
	 */
	static public ArrayList<Document> sortDocuments(Set<Document> docs, int type){

		ArrayList<Document> doclist = new ArrayList<Document>();

		for (Document doc : docs) doclist.add(doc);

		return sortDocuments(doclist, type);
	}

	/** Constructora del Controlador de Document.
	 * 
	 */
	public CtrlDomini () {
		vocab = new Vocabulari();
		lib = new Llibreria();
		CD = new ConsultaData();
		CT = new ConsultaTitol();
		CP = new ConsultaPreferit();
		CA = new ConsultaAutors();
	};

	/** Mètode privat que ens descomposa una String 'frase' en una llista de Strings, que són les paraules
	 *  que extraiem de 'frase'. Només es tenen en compte cadenes de lletres per a identificar les paraules.
	 * 
	 * @param frase La String que volem descomposar en paraules
	 * @return Una llista de Strings, que representa la descomposició de 'frase' en paraules.
	 */
	static private ArrayList<String> decomposeWords(String frase) {
        ArrayList<String> paraules = new ArrayList<>();
        String a_insert = "";

        for (char c: frase.toCharArray()) {
            if (!Character.isLetter(c)) {
                if (a_insert.length() != 0) {
                    paraules.add(a_insert);
                    a_insert = "";
                }
            }
            else a_insert += c;
        }
		if (a_insert.length() != 0) paraules.add(a_insert);

        return paraules;
    }

	/** Getter d'una referència a un document donat el seu autor i títol en Strings.
	 * 
	 * @param nomAutor String del nom de l'autor.
	 * @param nomTitol String del títol del document.
	 * @return Un parell de Document (el document cercat) i booleà, on el booleà és <b>false</b> si el document no existeix.
	 */
	public Pair<Document, Boolean> getDocument(String nomAutor, String nomTitol){
		return lib.getDocument(nomAutor, nomTitol);
	}
	
	/**
	 *  Funció que interacciona amb l'usuari i crea un document.
	 */
	public void crearDocument(String title, String author, ArrayList<String> content, LocalDate dia, boolean isFav){
		
		ArrayList<String> titledecomp = decomposeWords(title);
		
		ArrayList<String> authordecomp = decomposeWords(author);
		
		ArrayList<ArrayList<String>> contentdecomp = new ArrayList<ArrayList<String>>();

		for (int s = 0; s < content.size(); ++s){
			contentdecomp.add(decomposeWords(content.get(s)));
		}
		
		ArrayList<Paraula> arrWords = new ArrayList<Paraula>();

		for (int w = 0; w < titledecomp.size(); ++w){
			Paraula wd = vocab.inserirObtenirParaula(titledecomp.get(w));
			arrWords.add(wd);
		}

		Frase titlePhrase = new Frase(arrWords, title); 

		arrWords.clear();
		for (int w = 0; w < authordecomp.size(); ++w){
			Paraula wd = vocab.inserirObtenirParaula(authordecomp.get(w));
			arrWords.add(wd);
		}

		Frase authorPhrase = new Frase(arrWords, author);

		CA.addAutor(authorPhrase);
				 
		Frase[] cont = new Frase[contentdecomp.size()];

		for (int w = 0; w < contentdecomp.size(); ++w){
			ArrayList<String> currphrase = contentdecomp.get(w);
			arrWords.clear();

			for (int y = 0; y < currphrase.size(); ++y){
				Paraula wd = vocab.inserirObtenirParaula(currphrase.get(y));
				arrWords.add(wd);
			}

			cont[w] = new Frase(arrWords, content.get(w));
		}

		Contingut contentFinal = new Contingut(String.join("\n", content), cont);
		
		Document doc = new Document(authorPhrase, titlePhrase, isFav, "NULL", dia, contentFinal);

		lib.addDocument(doc);
		CT.afegirDocument(doc);
		CD.addDoc(doc);

		if (isFav) CP.afegirDocument(doc);
	}
	
	/**
	 *  Funció que interacciona amb l'usuari i modifica un document existent.
	 */
	public void modificarDocument(){
		Scanner in = new Scanner(System.in);

		
		Pair<Document, Boolean> docboolean = lib.getDocument(author, title);

		Document doc = docboolean.getL();

		if (!docboolean.getR()){
			System.out.println("Document with such author and title not found!");
			 return;
		}

		System.out.println("\nPreview of the document: \n\n"+doc);

		System.out.println("What do you wish to modify from the document (1..3)?\n");
		System.out.println("1) The date of creation");
		System.out.println("2) I want to toggle the document's favourite status");
		System.out.println("3) I want to modify the document itself\n");
		
		int choice = in.nextInt();

		switch(choice){
			case 1:
			System.out.println("\nCurrent date: "+doc.getData());
			System.out.print("New date (YYYY MM DD): ");
			int year = in.nextInt(); int month = in.nextInt(); int day = in.nextInt();

			try {
				LocalDate newDate = LocalDate.of(year, month, day);

				doc.setData(newDate);

				CD.deleteDoc(doc);
				CD.addDoc(doc);
				 return;

			} catch (DateTimeException e){
				System.out.println("\nPlease enter a valid date in the format YYYY MM DD next time.");
				 return;
			}

			case 2:

			System.out.print("Current favourite status is ");
			System.out.println(doc.getFavourite());

			togglePreferit(doc);

			System.out.println("New favourite status is "+doc.getFavourite());
			return;
			
			case 3:

			LocalDate dia = doc.getData();
			boolean isFav = doc.getFavourite();
			eliminarDocument(doc);
			crearDocument(dia, isFav);

			break;

			default:

			System.out.println("Didn't recognize "+choice);
			 return;
		}
		
	}

	// PRECONDICIÓ: Existeix (title, author).
	public String preview(String title, String author){
		return getDocument(author, title).getL().toString();
	}

	public void modificarData(String title, String author, LocalDate dat){
		Document d = getDocument(author, title).getL();
		
		d.setData(dat);

		CD.deleteDoc(d);
		CD.addDoc(d);
	}

	/** Funció que elimina un document donada la seva referència
	 * 
	 * @param d El Document que volem eliminar de la llibreria.
	 */
	public void eliminarDocument(Document d){

		// Rebaixar per 1 les ocurrències de cada paraula
		Frase authorFrase = d.getAutor();
		Frase titleFrase  =d.getTitol();
		Contingut content = d.getContingut();

		Frase[] frasesContingut = content.getFrases();
		ArrayList<Paraula[]> wordsContingut = new ArrayList<>();

		for (int i = 0; i < frasesContingut.length; ++i){
			wordsContingut.add(frasesContingut[i].getOracio());
		}

		Paraula[] wordsAutor = authorFrase.getOracio();
		Paraula[] wordsTitol = titleFrase.getOracio();

		// Eliminem el document, paraules i frases eliminant les referències (després es crida al GC de Java)
		if (d.getFavourite()) CP.eliminarDocument(d);
		CT.eliminarDocument(d);
		CD.deleteDoc(d);
		lib.deleteDocument(d);


		for (int i = 0; i < wordsAutor.length; ++i){
			vocab.decrementarOcurrencia(wordsAutor[i]);
			
		}

		for (int i = 0; i < wordsTitol.length; ++i){
			vocab.decrementarOcurrencia(wordsTitol[i]);
		}


		for (int i = 0; i < wordsContingut.size(); ++i){
			for (int j = 0; j < wordsContingut.get(i).length; ++j){
				vocab.decrementarOcurrencia(wordsContingut.get(i)[j]);
			}
		}
		
		System.gc();

	}

	/**
	 * Mètode que interacciona amb l'usuari amb l'objectiu d'eliminar un document.
	 */
	public void eliminarDocument(){
		Scanner in = new Scanner(System.in);

		System.out.print("\nEnter the name of the title: ");
		String title = in.nextLine();

		System.out.print("Enter the author's name: ");
		String author = in.nextLine();
		

		Pair<Document, Boolean> docboolean = lib.getDocument(author, title);
		
		if (!docboolean.getR()){
			System.out.println("Document with such author and title not found!");
			return;
		}

		// Rebaixar per 1 les ocurrències de cada paraula
		Frase authorFrase = docboolean.getL().getAutor();
		Frase titleFrase  =docboolean.getL().getTitol();
		Contingut content = docboolean.getL().getContingut();

		Frase[] frasesContingut = content.getFrases();
		ArrayList<Paraula[]> wordsContingut = new ArrayList<>();

		for (int i = 0; i < frasesContingut.length; ++i){
			wordsContingut.add(frasesContingut[i].getOracio());
		}

		Paraula[] wordsAutor = authorFrase.getOracio();
		Paraula[] wordsTitol = titleFrase.getOracio();

		// Eliminem el document, paraules i frases eliminant les referències (després es crida al GC de Java)
		if (docboolean.getL().getFavourite()) CP.eliminarDocument(docboolean.getL());
		CT.eliminarDocument(docboolean.getL());
		CD.deleteDoc(docboolean.getL());
		lib.deleteDocument(docboolean.getL());


		for (int i = 0; i < wordsAutor.length; ++i){
			vocab.decrementarOcurrencia(wordsAutor[i]);
			
		}

		for (int i = 0; i < wordsTitol.length; ++i){
			vocab.decrementarOcurrencia(wordsTitol[i]);
		}


		for (int i = 0; i < wordsContingut.size(); ++i){
			for (int j = 0; j < wordsContingut.get(i).length; ++j){
				vocab.decrementarOcurrencia(wordsContingut.get(i)[j]);
			}
		}
		
		System.gc();
		System.out.println("Document removed successfully!");

	}

	/** Mètode que canvia l'estat del booleà preferit d'un document donada una referència a aquest.
	 * 
	 * @param d Document en el qual volem fer toggle del booleà <i>isFav</i>.
	 */
	public void togglePreferit(Document d){

		// Fem el toggle
		d.setFavourite(!d.getFavourite());

		if (d.getFavourite()){
			// Si hem fet el toggle de 0 -> 1
			CP.afegirDocument(d);
		} else CP.eliminarDocument(d);

		return;
	}
}
