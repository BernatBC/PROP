package classes;
import java.util.Scanner;
import java.util.Set;

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
        Scanner read = new Scanner(System.in);

        while (read.hasNextLine()) {
            switch (read.nextLine().toLowerCase()) {
                case "c":
                    controlador.crearDocument();
                break;
                case "m":
                    controlador.modificarDocument();
                break;
                case "e":
                    controlador.eliminarDocument();
                break;
                case "ca":
                    System.out.print("Enter the author's prefix: ");
		            String author = read.nextLine();
                    imprimirFrases(ca.donaAutors(author));
                break;
                default:
                    System.out.println("Command not found. Please enter a valid command.");
            }
            imprimirComandes();
        }

        read.close();
        
    }

    private static void imprimirFrases(Set<Frase> frases) {
        if (frases == null) {
            System.out.println("\nNo s'ha trobat cap autor.");
            return;
        }
        for (Frase f: frases) {
            System.out.println(f.toString());
        }
    }

    private static void imprimirComandes() {
        System.out.println("\n+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-");
        System.out.println("|  COMANDES:                                        |");
        System.out.println("|  C    Crear Document                              |");
        System.out.println("|  M    Modificar Document                          |");
        System.out.println("|  E    Eliminar Document                           |");
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+|");
        System.out.println("|  CA   Consulta d'Autors per prefix                |");
        System.out.println("|  CSEQ Consulta de Documents per sequencia         |");
        System.out.println("|  CD   Consulta de Documents per data              |");
        System.out.println("|  CP   Consulta de Documents preferits             |");
        System.out.println("|  CR   Consulta de Documents per rellevancia       |");
        System.out.println("|  CS   Consulta de Documents per semblança         |");
        System.out.println("|  CT   Consulta de Documents per autors            |");
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-\n");
    }
}
