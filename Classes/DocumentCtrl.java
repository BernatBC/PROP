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
		
		if (Character.isLetter(phr.charAt(--i))) listWords.add(currWord);
		
		return listWords;
	}
	
	public int crearDocument(){
		Scanner in = new Scanner(System.in);
		
		String title = in.nextLine();
		ArrayList<String> titledecomp = decomposeWords(title);
		
		String author = in.nextLine();
		ArrayList<String> authordecomp = decomposeWords(author);
		
		StringBuilder content = new StringBuilder("");
		while (in.hasNextLine()) content.append(in.nextLine() + " ");
		ArrayList<String> contentdecomp = decomposeWords(content);
		
		// Now create the words read, insert them in the Vocab...
	}
	
}
