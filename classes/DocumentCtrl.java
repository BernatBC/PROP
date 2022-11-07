package classes;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;

import java.util.HashMap;

/** Classe controladora de mètodes relacionats amb documents.
 * @author Alexandre Ros i Roger (alexandre.ros.roger@estudiantat.upc.edu)
 */

class DocumentCtrl {

	private Vocabulari vocab;
	private Llibreria lib;

	DocumentCtrl(Vocabulari v, Llibreria l){
		vocab = v;
		lib = l;
	};
	
	private ArrayList<String> decomposeWords(String phr){

		if (phr.length() <= 0) return new ArrayList<String>();

		int i = 0;
		ArrayList<String> listWords = new ArrayList<String>();
		StringBuilder currWord = new StringBuilder("");
		
		while (i < phr.length() && !Character.isLetter(phr.charAt(i))) ++i;
		
		while (i < phr.length()){
			while (Character.isLetter(phr.charAt(i))){
				currWord.append(phr.charAt(i++));
				if (i >= phr.length()) break;
			} 
			
			listWords.add(currWord.toString());
			currWord = new StringBuilder("");
			
			while (i < phr.length() && !Character.isLetter(phr.charAt(i))) ++i;
		}
		
		if (i == phr.length() && Character.isLetter(phr.charAt(--i))) listWords.add(currWord.toString());
		
		return listWords;
	}

	public Pair<Document, Boolean> getDocument(String nomAutor, String nomTitol){
		return lib.getDocument(nomAutor, nomTitol);
	}
	
	public void crearDocument(){
		Scanner in = new Scanner(System.in);
		
		String title = in.nextLine();
		ArrayList<String> titledecomp = decomposeWords(title);
		
		String author = in.nextLine();
		ArrayList<String> authordecomp = decomposeWords(author);
		
		ArrayList<String> content = new ArrayList<String>();
		while (in.hasNextLine()) content.add(in.nextLine());
		ArrayList<ArrayList<String>> contentdecomp = new ArrayList<ArrayList<String>>();

		in.close();

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
	}
	
	public void eliminarDocument(){
		Scanner in = new Scanner(System.in);

		String author = in.nextLine();
		String title = in.nextLine();

		in.close();

		Pair<Document, Boolean> docboolean = lib.getDocument(author, title);
		
		if (!docboolean.getR()){
			System.out.println("Document with such author and title not found!");
			return;
		}

		// Rebaixar per 1 les ocurrències de cada paraula
		Frase authorFrase = docboolean.getL().getAutor();
		Frase titleFrase  =docboolean.getL().getTitol();
		Contingut content = docboolean.getL().getContingut();

		// Paraula[] wordsContingut = content.();
		Paraula[] wordsAutor = authorFrase.getOracio();
		Paraula[] wordsTitol = titleFrase.getOracio();

		// Eliminem el document, paraules i frases eliminant les referències (després es crida al GC de Java)
		lib.deleteDocument(docboolean.getL());

		for (int i = 0; i < wordsAutor.length; ++i){
			vocab.decrementarOcurrencia(wordsAutor[i]);
			
		}

		for (int i = 0; i < wordsTitol.length; ++i){
			vocab.decrementarOcurrencia(wordsTitol[i]);
		}


		for (int w : wordsContingut.keySet()){
			// Ídem
		}
		
		System.gc();
	}
}
