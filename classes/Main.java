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

        imprimirComandes();

        Scanner in = new Scanner(System.in);
        String command;
        while(true){
            if (in.hasNextLine()) {
            command = in.nextLine();
            switch (command) {
                case "c":
                    controlador.crearDocument();
                break;
                case "m":
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
                case "h":
                    imprimirComandes();
                break;
            }
        }
        }
    }

    private static void imprimirComandes() {
        System.out.println("COMANDES:");
        System.out.println("c    Crear Document");
        System.out.println("m    Modificar Document");
        System.out.println("e    Eliminar Document");
        System.out.println("p    Marcar/Desmarcar document preferit");
        System.out.println("h    Mostar comandes disponibles");
        System.out.println("");
    }
}
