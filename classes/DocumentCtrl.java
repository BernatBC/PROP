package classes;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.DateTimeException;
import java.time.LocalDate;

/** Classe controladora de mètodes relacionats amb documents.
 * @author Alexandre Ros i Roger (alexandre.ros.roger@estudiantat.upc.edu)
 */

class DocumentCtrl {

	private Vocabulari vocab;
	private Llibreria lib;
	private ConsultaData CD;
	private ConsultaTitol CT;
	private ConsultaPreferit CP;
	private ConsultaAutors CA;

	/** Constructora del Controlador de Document.
	 * 
	 * @param v El vocabulari de paraules.
	 * @param l La llibreria (cjt de documents).
	 * @param cd Una referència a la consultora per dates.
	 * @param ct Una referència a la consultora de títols.
	 * @param cp Una referència a la consultora per preferits.
	 * @param ca Una referència a la consultora d'autors.
	 */
	DocumentCtrl(Vocabulari v, Llibreria l, ConsultaData cd, ConsultaTitol ct, ConsultaPreferit cp, ConsultaAutors ca){
		vocab = v;
		lib = l;
		CP = cp;
		CT = ct;
		CD = cd;
		CA = ca;
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
	public void crearDocument(){
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter the title of the document: ");
		String title = in.nextLine();
		ArrayList<String> titledecomp = decomposeWords(title);
		
		System.out.print("Enter the author's name: ");
		String author = in.nextLine();
		ArrayList<String> authordecomp = decomposeWords(author);

		if (getDocument(author, title).getR()){
			System.out.println("Can't create the same document twice. Either modify it or delete it.");
			return;
		}
		
		System.out.println("\nPlease write the body of the document. Separate each phrase by a new line (ENTER). When you're done, press (ENTER) twice.\n");
		ArrayList<String> content = new ArrayList<String>();
		while (in.hasNextLine()){
			content.add(in.nextLine());
			if (content.get(content.size()-1).equals("")){
				content.remove(content.size()-1);
				break;
			}
		} 
		ArrayList<ArrayList<String>> contentdecomp = new ArrayList<ArrayList<String>>();

		for (int s = 0; s < content.size(); ++s){
			contentdecomp.add(decomposeWords(content.get(s)));
		}
		
		// Now create the words read, insert them in the Vocab...
		// Comencem per crear les paraules
		ArrayList<Paraula> arrWords = new ArrayList<Paraula>();
		for (int w = 0; w < titledecomp.size(); ++w){
			Paraula wd = vocab.inserirObtenirParaula(titledecomp.get(w));
			arrWords.add(wd);
		}

		Frase titlePhrase = new Frase(arrWords, title); 

		// Ara per l'autor
		arrWords.clear();
		for (int w = 0; w < authordecomp.size(); ++w){
			Paraula wd = vocab.inserirObtenirParaula(authordecomp.get(w));
			arrWords.add(wd);
		}

		Frase authorPhrase = new Frase(arrWords, author);

		CA.addAutor(authorPhrase);
		
		// Finalment pel contingut
		 
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
		
		// Finalment creem el document i el guardem

		Document doc = new Document(authorPhrase, titlePhrase, false, "NULL", LocalDate.now(), contentFinal);

		lib.addDocument(doc);
		CT.afegirDocument(doc);
		CD.addDoc(doc);

		System.out.println("Document added successfully!\n");
	}
	
	/**
	 *  Funció que interacciona amb l'usuari i modifica un document existent.
	 */
	public void modificarDocument(){
		Scanner in = new Scanner(System.in);

		System.out.print("\nEnter the name of the title: ");
		String title = in.nextLine();

		System.out.print("Enter the author's name: ");
		String author = in.nextLine();
		
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

			eliminarDocument(doc);
			crearDocument();

			break;

			default:

			System.out.println("Didn't recognize "+choice);
			 return;
		}
		
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
