package test.classes;
import classes.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.time.LocalDate;
import java.time.DateTimeException;

/** Main.
 * @author Bernat Borràs Civil (bernat.borras.civil@estudiantat.upc.edu)
 */
public class DriverControlador {

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
                case "n":
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
                case "q":
                    return;
                case "cd":
                    System.out.println("Selecciona Mode:");
                    System.out.println("1) Documents entre dues dates");
                    System.out.println("2) Documents anteriors a una data");
                    System.out.println("3) Documents posteriors a una data");
                    int tria = read.nextInt();
                    if (tria == 1) {
                        System.out.println("Enter de lower bound (YYYY MM DD): ");
                        int year = read.nextInt(); int month = read.nextInt(); int day = read.nextInt();
                        System.out.println("Enter the higher bound (YYYY MM DD): ");
                        int year2 = read.nextInt(); int month2 = read.nextInt(); int day2 = read.nextInt();
                        try {
                            LocalDate ant = LocalDate.of(year, month, day);
                            LocalDate post = LocalDate.of(year2, month2, day2);
                            imprimirArray(cd.consulta(ant, post));
                        } catch (DateTimeException e){
                            System.out.println("\nPlease enter a valid date in the format YYYY MM DD next time :)");
                        }
                    }
                    else if (tria == 2) {
                        System.out.println("Enter the higher bound (YYYY MM DD): ");
                        int year = read.nextInt(); int month = read.nextInt(); int day = read.nextInt();
                        try {
                            LocalDate d = LocalDate.of(year, month, day);
                            imprimirArray(cd.consultaAnterior(d));
                        } catch (DateTimeException e){
                            System.out.println("\nPlease enter a valid date in the format YYYY MM DD next time :)");
                        }
                    }
                    else if (tria == 3) {
                        System.out.println("Enter the lower bound (YYYY MM DD): ");
                        int year = read.nextInt(); int month = read.nextInt(); int day = read.nextInt();
                        try {
                            LocalDate d = LocalDate.of(year, month, day);
                            imprimirArray(cd.consultaPosterior(d));
                        } catch (DateTimeException e){
                            System.out.println("\nPlease enter a valid date in the format YYYY MM DD next time :)");
                        }
                    }
                    
                break;
                case "cc":
                    // Consulta contingut
                    System.out.print("Entra el títol del document: ");
                    String tit = read.nextLine();
                    System.out.print("Entra l'autor del document: ");
                    String auth = read.nextLine();
                    Pair<Document, Boolean> p = controlador.getDocument(auth, tit);
                    
                    if (!p.getR()) System.out.println("\nNo s'ha trobat un document amb aquest títol i autor");
                    else System.out.println(p.getL());

                break;
                case "cp":
                    imprimirDocuments(cp.getDocPreferit());
                break;
                case "cr1":
                    System.out.print("Entra un conjunt de paraules seperades per espais: ");
                    String s =  read.nextLine();
                    System.out.print("Entra un número de documents: ");
                    int k = read.nextInt();
                    ConsultaRellevancia cs = new ConsultaRellevancia(k, (new Frase(s)).getOracio(), s, 1, l);
                    System.out.println(cs.getDocs().toString());
                break;
                case "cr2":
                    System.out.print("Entra un conjunt de paraules seperades per espais: ");
                    String s2 =  read.nextLine();
                    System.out.print("Entra un número de documents: ");
                    int k2 = read.nextInt();
                    ConsultaRellevancia cs2 = new ConsultaRellevancia(k2, (new Frase(s2)).getOracio(), s2, 2, l);
                    System.out.println(cs2.getDocs().toString());
                break;
                case "cs":
                    System.out.print("Enter the title of the document: ");
                    String t = read.nextLine();
                    System.out.print("Enter the author of the document: ");
                    String a = read.nextLine();
                    System.out.print("Enter the number of documents: ");
                    int k3 = read.nextInt();
                    imprimirSemblant(ConsultaSemblant.executeQuery(l, l.getDocument(a, t).getL(), k3));
                break;
                case "cseq":
                    System.out.print("Enter the sequence: ");
                    String s3 = read.nextLine();imprimirDocuments(ConsultaAvancada.obtenirDocuments(l, s3));
                break;
                case "ct":
                    System.out.print("Enter the author of the document: ");
                    String aut = read.nextLine();
                    imprimirDocuments(ct.getDocAutor(new Frase(aut)));
                break;
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

    private static void imprimirDocuments(Set<Document> documents) {
        if (documents == null) return;
        for (Document d: documents) {
            System.out.println(d.toString());
        }
    }

    private static void imprimirArray(ArrayList<Document> documents) {
        if (documents == null) return;
        for (Document d: documents) {
            System.out.println(d.toString());
        }
    }

    private static void imprimirSemblant(ArrayList<Pair<Double,Document>> documents) {
        if (documents == null) return;
        for (Pair<Double, Document> d: documents) {
            System.out.println(d.getL() + " " + d.getR().toString());
        }
    }

    private static void imprimirComandes() {

        System.out.println("\n+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+");
        System.out.println("|  COMANDES:                                           |");
        System.out.println("|  N    Nou   Document                                 |");
        System.out.println("|  M    Modificar Document                             |");
        System.out.println("|  E    Eliminar Document                              |");
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-|");
        System.out.println("|  CA   Consulta d'Autors per prefix                   |");
        System.out.println("|  CSEQ Consulta de Documents per sequencia            |");
        System.out.println("|  CC   Consulta del Contingut d'un document           |");
        System.out.println("|  CD   Consulta de Documents per data                 |");
        System.out.println("|  CP   Consulta de Documents preferits                |");
        System.out.println("|  CR1  Consulta de Documents per rellevancia mètode 1 |");
        System.out.println("|  CR2  Consulta de Documents per rellevancia mètode 2 |");
        System.out.println("|  CS   Consulta de Documents per semblança            |");
        System.out.println("|  CT   Consulta de Documents per autors               |");
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+");
        System.out.println("|  Q    Sortir de l'aplicació                          |");
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+\n");
    }
}
