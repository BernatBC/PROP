import java.util.Scanner;
import java.util.ArrayList;

class DocumentCtrl {

	private Vocabulari vocab;
	private Llibreria lib;

	// Classe Controlador que implementa mètodes del projecte
	DocumentCtrl(){};
	
	private ArrayList<String> decomposeWords(String phr){
		int i = 0;
		ArrayList<String> listWords = new ArrayList<String>();
		StringBuilder currWord = new StringBuilder("");
		
		while (!Character.isLetter(phr.charAt(i))) ++i;
		
		while (i < phr.length()){
			while (Character.isLetter(phr.charAt(i))){
				currWord.append(phr.charAt(i++));
			} 
			
			listWords.add(currWord);
			currWord = new StringBuilder("");
			
			while (!Character.isLetter(phr.charAt(i))) ++i;
		}
		
		if (Character.isLetter(phr.charAt(--i))) listWords.add(currWord.toString());
		
		return listWords;
	}
	
	public int crearDocument(){
		Scanner in = new Scanner(System.in);
		
		String title = in.nextLine();
		ArrayList<String> titledecomp = decomposeWords(title);
		
		String author = in.nextLine();
		ArrayList<String> authordecomp = decomposeWords(author);
		
		ArrayList<String> content = new ArrayList<String>();
		while (in.hasNextLine()) content.add(in.nextLine());
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
		
		// Finalment pel contingut
		/* 
		arrWords.clear();
		for (int w = 0; w < contentdecomp.size(); ++w){
			Paraula wd = vocab.inserirObtenirParaula(contentdecomp.get(w));
			arrWords.add(wd);
		}

		Frase content = new Frase(arrWords, author);
		*/


		return 0;
	}
	
}
