package classes;
import java.util.Scanner;

/** Main.
 * @author Bernat Borràs Civil (bernat.borras.civil@estudiantat.upc.edu)
 */
public class Main {

    public static void main(String[] args) {
        Vocabulari v = new Vocabulari();
        Llibreria l = new Llibreria();
        ConsultaData cd = new ConsultaData();
        ConsultaTitol ct = new ConsultaTitol();
        ConsultaPreferit cp = new ConsultaPreferit();
        ConsultaAutors ca = new ConsultaAutors();
        DocumentCtrl controlador = new DocumentCtrl(v, l, cd, ct, cp, ca);
        
        /*while (in.hasNextLine()){
			content.add(in.nextLine());
			if (content.get(content.size()-1).equals("")){
				content.remove(content.size()-1);
				break;
			}
		}*/

        /*Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {
            System.out.println(in.nextLine());
        }

        in.close();*/

        /*imprimirComandes();
        Scanner in = new Scanner(System.in);
        while (true) {
            while (!in.hasNextLine());
            executeCommand(controlador, in);
        }*/

        imprimirComandes();
        Scanner read = new Scanner(System.in);

        while (read.hasNextLine()) {
            switch (read.nextLine()) {
                case "c":
                    controlador.crearDocument();
                break;
                case "m":
                    System.out.println("Modificant");
                    controlador.modificarDocument();
                break;
                case "e":
                System.out.println("C");
                    controlador.eliminarDocument();
                break;
            }
            imprimirComandes();
        }

        read.close();
        
        /*while(true){
            Scanner in = new Scanner(System.in);
            if (in.hasNextLine()) {
                String command = in.nextLine();
                System.out.println(command);
            switch (command) {
                case "c":
                    controlador.crearDocument();
                break;
                case "m":
                    System.out.println("Modificant");
                    controlador.modificarDocument();
                break;
                case "e":
                    controlador.eliminarDocument();
                break;
                case "p":
                    System.out.println("Enter the author's name: ");
                    String author = in.nextLine();
                    System.out.print("Enter the name of the title: ");
                    String title = in.nextLine();
                    controlador.togglePreferit(controlador.getDocument(author, title).getL());
                break;
            }
            imprimirComandes();
            }
            in.close();
        }*/
    }

    private static void executeCommand(DocumentCtrl controlador, Scanner in) {
            String command = in.nextLine();
            switch (command) {
                case "c":
                    controlador.crearDocument();
                break;
                case "m":
                    System.out.println("Modificant");
                    controlador.modificarDocument();
                break;
                case "e":
                    controlador.eliminarDocument();
                break;
                case "p":
                    System.out.println("Enter the author's name: ");
                    String author = in.nextLine();
                    System.out.print("Enter the name of the title: ");
                    String title = in.nextLine();
                    controlador.togglePreferit(controlador.getDocument(author, title).getL());
                break;
        }
        imprimirComandes();
    }

    private static void imprimirComandes() {
        System.out.println("COMANDES:");
        System.out.println("c    Crear Document");
        System.out.println("m    Modificar Document");
        System.out.println("e    Eliminar Document");
        System.out.println("p    Marcar/Desmarcar document preferit");
        System.out.println("");
    }
}
