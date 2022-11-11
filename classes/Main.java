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
            switch (read.nextLine()) {
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
            }
            imprimirComandes();
        }

        read.close();
        
    }

    private static void imprimirFrases(Set<Frase> frases) {
        if (frases == null) {
            System.out.println("No s'ha trobat cap autor.");
            return;
        }
        for (Frase f: frases) {
            System.out.println(f.toString());
        }
    }

    private static void imprimirComandes() {
        System.out.println("COMANDES:");
        System.out.println("c    Crear Document");
        System.out.println("m    Modificar Document");
        System.out.println("e    Eliminar Document");
        System.out.println("");
        System.out.println("ca   Consulta d'Autors per prefix");
        System.out.println("cseq Consulta de Documents per sequencia");
        System.out.println("cd   Consulta de Documents per data");
        System.out.println("cp   Consulta de Documents preferits");
        System.out.println("cr   Consulta de Documents per rellevancia");
        System.out.println("cs   Consulta de Documents semblants");
        System.out.println("ct   Consulta de Documents per autors");
        System.out.println("");
    }
}
