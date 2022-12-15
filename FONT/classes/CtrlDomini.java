package classes;

import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.time.LocalDate;

/** Classe controladora de les classes de domini.
 * @author Alexandre Ros i Roger (alexandre.ros.roger@estudiantat.upc.edu)
 */
public class CtrlDomini {

	private Vocabulari vocab;
	private Llibreria lib;
	private ExpressioBooleanaCtrl EBC;
	private CtrlPersistencia DISK;

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
		EBC = new ExpressioBooleanaCtrl();
		DISK = new CtrlPersistencia(this);
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
	
	public void crearDocument(String title, String author, ArrayList<String> content, String plaintext_cont, LocalDate dia, boolean isFav){
		
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

		Contingut contentFinal = new Contingut(plaintext_cont, cont);
		
		Document doc = new Document(authorPhrase, titlePhrase, isFav, "NULL", dia, contentFinal);

		lib.addDocument(doc);

		DISK.crearDocument(title, author, doc.getContingut().toString(), dia, isFav);
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

	/** Funció que retorna la Data d'un document
	 *
	 * @param title Títol del document a consultar
	 * @param author Autor del document a consultar
	 * @return LocalDate de creació del document.
	 */
	 public LocalDate getData(String title, String author)
	 {
		Pair<Document, Boolean> doc = getDocument(author, title);
		if (!doc.getR()) return LocalDate.MIN;

		return doc.getL().getData();
	 }	

    /** Funció que retorna el contingut d'un document
	 *
	 * @param title Títol del document a consultar
	 * @param author Autor del document a consultar
	 * @return String que representa el contingut del document.
	 */

	public String getContingut(String title, String author){
		Pair<Document, Boolean> doc = getDocument(author, title);
		if (!doc.getR()) return "";

		return doc.getL().getContingut().toString();
	}

	// PRECONDICIÓ: Existeix (title, author).
	public String preview(String title, String author){
		return getDocument(author, title).getL().toString();
	}

	public Set<String> donaAutors(String prefix){
		return ConsultaAutors.donaAutors(prefix, lib.getArbre());
	}

	public void modificarData(String title, String author, LocalDate dat){
		Document d = getDocument(author, title).getL();
		
		d.setData(dat);
		DISK.crearDocument(d.getTitol().toString(), d.getAutor().toString(), d.getContingut().toString(), d.getData(), d.getFavourite());
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

		HashMap<String, Document> documentsOldAuthor = lib.getArbre().obtenir(oldAuth, 0).getR();
		HashMap<String, Document> documentsNewAuthor = lib.getArbre().obtenir(newAuth, 0).getR();
		documentsOldAuthor.remove(title);
		documentsNewAuthor.put(title, d);
		DISK.esborrarDocument(title, oldAuth);
		DISK.crearDocument(d.getTitol().toString(), d.getAutor().toString(), d.getContingut().toString(), d.getData(), d.getFavourite());
	}

	public void modificarContingut(String title, String author, ArrayList<String> content, String plaintext_content){

		// Suposarem que d existeix.
		Document d = getDocument(author, title).getL();

		boolean isFav = d.getFavourite();
		LocalDate dat = d.getData();

		eliminarDocument(title, author);
		crearDocument(title, author, content, plaintext_content, dat, isFav);

		DISK.crearDocument(d.getTitol().toString(), d.getAutor().toString(), d.getContingut().toString(), d.getData(), d.getFavourite());
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

		HashMap<String, Document> documentsAutor = lib.getArbre().obtenir(author, 0).getR();
		documentsAutor.remove(oldTitle);
		documentsAutor.put(newTitle, d);

		DISK.esborrarDocument(oldTitle, author);
		DISK.crearDocument(d.getTitol().toString(), d.getAutor().toString(), d.getContingut().toString(), d.getData(), d.getFavourite());
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
		DISK.esborrarDocument(title, author);
	}

	public ArrayList<String> consultaData(LocalDate ant, LocalDate post, int criteria)
	{
		ArrayList<Document> docs = ConsultaData.consulta(lib.getDocArray(), ant, post);
		docs = sortDocuments(docs, criteria);

		ArrayList<String> myList = new ArrayList<String>();

		for (Document d : docs){
			myList.add(d.toString());
		}

		return myList;
	}

	public ArrayList<String> consultaSeq(String seq, int criteria){
		Set<Document> setdocs = ConsultaAvancada.obtenirDocuments(lib, seq);
		if (setdocs == null) return new ArrayList<>();
		ArrayList<Document> result = sortDocuments(setdocs, criteria);

		ArrayList<String> myList = new ArrayList<>();

		for (Document d : result){
			myList.add(d.toString());
		}

		return myList;
	}

	public ArrayList<String> getAllDocs(){
		return lib.toStringArray();
	}

	public ArrayList<String> consultaTit(String autor, int criteria){
		Set<Document> setdocs = ConsultaTitol.getDocAutor(new Frase(autor), lib.getArbre());
		if (setdocs == null) return new ArrayList<>();
		ArrayList<Document> result = sortDocuments(setdocs, criteria);

		ArrayList<String> myList = new ArrayList<>();

		for (Document d : result){
			myList.add(d.toString());
		}

		return myList;

	}

	public ArrayList<String> consultaSemb(String titol, String autor, int n, int mode){
		Document doc = getDocument(autor, titol).getL();

		ArrayList<Pair<Double, Document>> result = ConsultaSemblant.executeQuery(lib, doc, n, mode);

		ArrayList<String> stg = new ArrayList<>();
		
		for (Pair<Double, Document> d: result) {
			stg.add(d.getR().toString() + " (" + d.getL() + ")");
        }

		return stg;

	}

	public ArrayList<String> consultaRell(String wordsSepBlank, int k, int modeConsulta)
	{
		String[] words = wordsSepBlank.split(" ");

		Paraula[] arrWords = new Paraula[words.length];
		for (int i = 0; i < words.length; ++i) arrWords[i] = vocab.inserirObtenirParaula(words[i]);

		if (modeConsulta == 1) return ConsultaRellevancia.ConsultaPerRellevancia(k, arrWords, wordsSepBlank, 1, lib).toStringArray();
		else return ConsultaRellevancia.ConsultaPerRellevancia(k, arrWords, wordsSepBlank, 2, lib).toStringArray();
	}

	public ArrayList<String> consultaPref(int criteria){
		Set<Document> docSet = ConsultaPreferit.getDocPreferit(lib.getSetDocuments());
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
		DISK.crearDocument(d.getTitol().toString(), d.getAutor().toString(), d.getContingut().toString(), d.getData(), d.getFavourite());
		return;
	}

	public void novaEB(String nom, String cos){
		EBC.AddExpressioBooleana(nom, cos);
		DISK.crearExpressio(nom, cos);
	}

	public void canviarEB(String nom, String noucos){
		EBC.SetExpressioBooleana(nom, noucos);
		DISK.crearExpressio(nom, noucos);
	}

	public void eliminarEB(String nom){
		EBC.DeleteExpressioBooleana(nom);
		DISK.esborrarExpressio(nom);
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

	/** Mètode per a exportar un document.
	 *  @param titol El títol del document a exportar.
	 *  @param autor L'autor del document a exportar.
	 *  @param ext Extensió a emprar (0 = TXT 1 = XML 2 = YAY)
	 *  @param fname Nom del fitxer
	 */
	public void exportarDocument(String titol, String autor, int ext, String fname){
		if (!docExists(titol, autor)){
			System.out.println("No existeix el document que vols exportar.");
			return;
		}

		Pair<Document, Boolean> docboolean = getDocument(autor, titol);
		String content = docboolean.getL().getContingut().toString();
		LocalDate data = docboolean.getL().getData();
		boolean isFav = docboolean.getL().getFavourite();

		String extension = ".txt";

		switch(ext){
			case 0:
			extension = ".txt";
			break;
			case 1:
			extension = ".xml";
			break;
			case 2:
			extension = ".yay";
			break;
		}

		DISK.export(titol, autor, content, data, isFav, fname+extension);
	}

	/**
     * Importa un fitxer a l'aplicació.
     * @param path path del document.
     */
    public void importFile(String path) {
		DISK.importFile(path);
    }

	/**
	 * Importa les dades desades a l'aplicació.
	 */
	public void importSaved() {
		DISK.importarDades();
	}

}
