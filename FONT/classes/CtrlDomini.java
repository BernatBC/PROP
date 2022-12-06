package classes;

import java.util.Set;
import java.util.ArrayList;
import java.util.Comparator;
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
	private ExpressioBooleanaCtrl EBC;

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
		EBC = new ExpressioBooleanaCtrl();
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

	/** Funció que retorna el booleà de preferit d'un document
	 * 
	 * @param title Títol del document a consultar.
	 * @param author Autor del docuemnt a consultar.
	 * @return Booleà <i>isFav</i>. Si el document no existeix retorna fals.
	 */
	public boolean getFavourite(String title, String author)
	{
		Pair<Document, Boolean> doc = getDocument(author, title);
		if (!doc.getR()) return false;

		return doc.getL().getFavourite();
	}	

	// PRECONDICIÓ: Existeix (title, author).
	public String preview(String title, String author){
		return getDocument(author, title).getL().toString();
	}

	public Set<String> donaAutors(String prefix){
		return CA.donaAutors(prefix);
	}

	public void modificarData(String title, String author, LocalDate dat){
		Document d = getDocument(author, title).getL();
		
		d.setData(dat);

		CD.deleteDoc(d);
		CD.addDoc(d);
	}

	public void modificarAutor(String title, String oldAuth, String newAuth){
		Document d = getDocument(oldAuth, title).getL();
		
		ArrayList<String> authordecomp = decomposeWords(newAuth);
		ArrayList<Paraula> arrWords = new ArrayList<Paraula>();

		for (int w = 0; w < authordecomp.size(); ++w){
			Paraula wd = vocab.inserirObtenirParaula(authordecomp.get(w));
			arrWords.add(wd);
		}

		Frase authorPhrase = new Frase(arrWords, newAuth);

		d.setAutor(authorPhrase);

		CA.addAutor(authorPhrase);
		
		// Potser es podria fer només en una instrucció
		CT.eliminarDocument(d);
		CT.afegirDocument(d);
	}

	public void modificarContingut(String title, String author, ArrayList<String> content){

		// Suposarem que d existeix.
		Document d = getDocument(author, title).getL();

		boolean isFav = d.getFavourite();
		LocalDate dat = d.getData();

		eliminarDocument(title, author);
		crearDocument(title, author, content, dat, isFav);
	}

	public void modificarTitol(String oldTitle, String author, String newTitle){
		Document d = getDocument(author, oldTitle).getL();
		
		ArrayList<String> titledecomp = decomposeWords(newTitle);
		ArrayList<Paraula> arrWords = new ArrayList<Paraula>();

		for (int w = 0; w < titledecomp.size(); ++w){
			Paraula wd = vocab.inserirObtenirParaula(titledecomp.get(w));
			arrWords.add(wd);
		}

		Frase titolFrase = new Frase(arrWords, newTitle);

		d.setTitol(titolFrase);
	}



	/**
	 * Mètode que interacciona amb l'usuari amb l'objectiu d'eliminar un document.
	 */
	public void eliminarDocument(String title, String author){
		Document doc = getDocument(author, title).getL();;
		
		// Rebaixar per 1 les ocurrències de cada paraula
		Frase authorFrase = doc.getAutor();
		Frase titleFrase  =doc.getTitol();
		Contingut content = doc.getContingut();

		Frase[] frasesContingut = content.getFrases();
		ArrayList<Paraula[]> wordsContingut = new ArrayList<>();

		for (int i = 0; i < frasesContingut.length; ++i){
			wordsContingut.add(frasesContingut[i].getOracio());
		}

		Paraula[] wordsAutor = authorFrase.getOracio();
		Paraula[] wordsTitol = titleFrase.getOracio();

		// Eliminem el document, paraules i frases eliminant les referències (després es crida al GC de Java)
		if (doc.getFavourite()) CP.eliminarDocument(doc);
		
		CT.eliminarDocument(doc);
		CD.deleteDoc(doc);
		lib.deleteDocument(doc);

		//// CONTEM AIXO?
		for (int i = 0; i < wordsAutor.length; ++i){
			vocab.decrementarOcurrencia(wordsAutor[i]);
			
		}

		// CONTEM AIXÒ?
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

	public ArrayList<String> consultaData(LocalDate ant, LocalDate post, int criteria)
	{
		ArrayList<Document> docs = CD.consulta(ant, post);
		docs = sortDocuments(docs, criteria);

		ArrayList<String> myList = new ArrayList<String>();

		for (Document d : docs){
			myList.add(d.toString());
		}

		return myList;
	}

	public ArrayList<String> consultaSeq(String seq, int criteria){
		Set<Document> setdocs = ConsultaAvancada.obtenirDocuments(lib, seq);
		ArrayList<Document> result = sortDocuments(setdocs, criteria);

		ArrayList<String> myList = new ArrayList<>();

		for (Document d : result){
			myList.add(d.toString());
		}

		return myList;
	}

	public ArrayList<String> consultaTit(String autor, int criteria){
		Set<Document> setdocs = CT.getDocAutor(new Frase(autor));
		ArrayList<Document> result = sortDocuments(setdocs, criteria);

		ArrayList<String> myList = new ArrayList<>();

		for (Document d : result){
			myList.add(d.toString());
		}

		return myList;

	}

	public String consultaSemb(String titol, String autor, int n, int mode){
		Document doc = getDocument(autor, titol).getL();

		ArrayList<Pair<Double, Document>> result = ConsultaSemblant.executeQuery(lib, doc, n, mode);

		StringBuilder strbld = new StringBuilder();
		
		for (Pair<Double, Document> d: result) {
            strbld.append("GRAU DE SEMBLANÇA: " + d.getL() + "\n");
            strbld.append(d.getR().toString() + "\n");
        }

		return strbld.toString();

	}

	public String consultaRell(String wordsSepBlank, int k, int modeConsulta)
	{
		String[] words = wordsSepBlank.split(" ");

		Paraula[] arrWords = new Paraula[words.length];
		for (int i = 0; i < words.length; ++i) arrWords[i] = vocab.inserirObtenirParaula(words[i]);

		ConsultaRellevancia CR;
		if (modeConsulta == 1) CR = new ConsultaRellevancia(k, arrWords, wordsSepBlank, 1, lib);
		else CR = new ConsultaRellevancia(k, arrWords, wordsSepBlank, 2, lib);

		return CR.getDocs().toString();
	}

	public ArrayList<String> consultaPref(int criteria){
		Set<Document> docSet = CP.getDocPreferit();
		ArrayList<Document> docs = sortDocuments(docSet, criteria);

		ArrayList<String> myList = new ArrayList<String>();

		for (Document d : docs){
			myList.add(d.toString());
		}

		return myList;

	}

	/** Mètode que canvia l'estat del booleà preferit d'un document.
	 * 
	 * @param title Títol en String del document en el qual volem fer toggle del booleà <i>isFav</i>.
	 * @param author Autor en String del document en el qual volem fer toggle del booleà <i>isFav</i>.
	 */
	public void togglePreferit(String title, String author){

		// Fem el toggle
		Document d = getDocument(author, title).getL();

		d.setFavourite(!d.getFavourite());

		if (d.getFavourite()){
			// Si hem fet el toggle de 0 -> 1
			CP.afegirDocument(d);
		} else CP.eliminarDocument(d);

		return;
	}

	public void novaEB(String nom, String cos){
		EBC.AddExpressioBooleana(nom, cos);
	}

	public void canviarEB(String nom, String noucos){
		EBC.SetExpressioBooleana(nom, noucos);
	}

	public void eliminarEB(String nom){
		EBC.DeleteExpressioBooleana(nom);
	}

	public int numberOfEBS(){
		return EBC.getNEBS();
	}

	public boolean existsEB(String name){
		return EBC.existsEB(name);
	}

	public ArrayList<String> consultaEB(String cos, String nom, int mode, int criteria)
	{
		Set<Document> setdoc;
		ExpressioBooleana eb;
		
		if (mode == 1) eb = EBC.ExpressioBooleanaTemporal(cos);
		else eb = EBC.GetExpressioBooleana(nom);

		setdoc = eb.getResultat(lib);

		ArrayList<Document> docs = sortDocuments(setdoc, criteria);

		ArrayList<String> myList = new ArrayList<String>();

		for (Document d : docs){
			myList.add(d.toString());
		}

		return myList;

	}

	public void exportarDocument(String titol, String autor, int ext, String fname){
		
	}
}
