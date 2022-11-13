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
        ExpressioBooleanaCtrl controladorb = new ExpressioBooleanaCtrl();
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
                case "ne":
                    System.out.println("Exemple d'expressió: ---  ( \"bon dia\" | p2 ) & ( p3 & ! { p4 p5 p6 } ) --- \n");
                    System.out.print("Entra una expressió booleana: ");
                    String expressio = read.nextLine();
                    System.out.print("Entra un nom per a l'expressió booleana: ");
                    String nom = read.nextLine();
                    controladorb.CreaExpressioBooleana(nom, expressio);
                break;
                case "me":
                    System.out.println("Exemple d'expressió: ---  ( \"bon dia\" | p2 ) & ( p3 & ! { p4 p5 p6 } ) --- \n");
                    System.out.print("Entra una expressió booleana: ");
                    String expressio_modificada = read.nextLine();
                    System.out.print("Entra un nom per a l'expressió booleana: ");
                    String nom_modificada = read.nextLine();
                    controladorb.SetExpressioBooleana(nom_modificada, expressio_modificada);
                break;
                case "ee":
                    System.out.print("Entra un nom per a l'expressió booleana: ");
                    String nom_esborrar = read.nextLine();
                    controladorb.DeleteExpressioBooleana(nom_esborrar);
                break;
                case "ca":
                    System.out.print("Enter the author's prefix: ");
		            String author = read.nextLine();
                    imprimirFrases(ca.donaAutors(author));
                break;
                case "cb":
                    System.out.print("Entra un nom per a l'expressió booleana: ");
                    String nom_consultar = read.nextLine();
                    ExpressioBooleana exp = controladorb.GetExpressioBooleana(nom_consultar);
                    imprimirArray(DocumentCtrl.sortDocuments(exp.getResultat(l), getCriteris(read)));
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
                            imprimirArray(DocumentCtrl.sortDocuments(cd.consulta(ant, post), getCriteris(read)));
                        } catch (DateTimeException e){
                            System.out.println("\nPlease enter a valid date in the format YYYY MM DD next time :)");
                        }
                    }
                    else if (tria == 2) {
                        System.out.println("Enter the higher bound (YYYY MM DD): ");
                        int year = read.nextInt(); int month = read.nextInt(); int day = read.nextInt();
                        try {
                            LocalDate d = LocalDate.of(year, month, day);
                            imprimirArray(DocumentCtrl.sortDocuments(cd.consultaAnterior(d), getCriteris(read)));
                        } catch (DateTimeException e){
                            System.out.println("\nPlease enter a valid date in the format YYYY MM DD next time :)");
                        }
                    }
                    else if (tria == 3) {
                        System.out.println("Enter the lower bound (YYYY MM DD): ");
                        int year = read.nextInt(); int month = read.nextInt(); int day = read.nextInt();
                        try {
                            LocalDate d = LocalDate.of(year, month, day);
                            imprimirArray(DocumentCtrl.sortDocuments(cd.consultaPosterior(d), getCriteris(read)));
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
                    imprimirArray(DocumentCtrl.sortDocuments(cp.getDocPreferit(), getCriteris(read)));
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
                    System.out.print("Entra el títol del document: ");
                    String t = read.nextLine();
                    System.out.print("Entra l'autor del document: ");
                    String a = read.nextLine();
                    System.out.print("Entra el nombre de documents: ");
                    int k3 = read.nextInt();
                    System.out.println("\nQuina assignació de pesos prefereix? ");
                    System.out.println("[0] TF-IDF");
                    System.out.println("[1] Per ocurrències");
                    int k9  =read.nextInt();
                    if (k9 != 0 && k9 != 1) System.out.println("\nElecció "+k9+ " no reconeguda.");
                    else imprimirSemblant(ConsultaSemblant.executeQuery(l, l.getDocument(a, t).getL(), k3, k9));

                break;
                case "cseq":
                    System.out.print("Enter the sequence: ");
                    String s3 = read.nextLine();
                    Set<Document> results = ConsultaAvancada.obtenirDocuments(l, s3);

                    imprimirArray(DocumentCtrl.sortDocuments(results, getCriteris(read)));

                break;
                case "ct":
                    System.out.print("Enter the author of the document: ");
                    String aut = read.nextLine();

                    imprimirArray(DocumentCtrl.sortDocuments(ct.getDocAutor(new Frase(aut)), getCriteris(read)));

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

    private static void imprimirArray(ArrayList<Document> documents) {
        if (documents == null) return;
        for (Document d: documents) {
            System.out.println(d.toString());
        }
    }

    private static void imprimirSemblant(ArrayList<Pair<Double,Document>> documents) {
        if (documents == null) return;
        for (Pair<Double, Document> d: documents) {
            System.out.println("GRAU DE SEMBLANÇA: " + d.getL());
            System.out.println(d.getR().toString());
        }
    }

    private static int getCriteris(Scanner read){
        System.out.println("\nPer quin criteri d'ordenació vols ordenar els documents? (0..3)");
        System.out.println("\n[0] Per les dates de creació ascendentment");
        System.out.println("[1] Primer els documents preferits, llavors els no-preferits.");
        System.out.println("[2] Per ordre alfabètic dels autors.");
        System.out.println("[3] Per ordre alfabètic dels títols dels documents");
    
        int crit = read.nextInt();

        if (crit < 0 || crit > 4){
            System.out.println("Ho sentim, "+crit+ " no és un criteri vàlid d'ordenació");
            System.out.println("Els ordenarem per data per defecte.");
            crit = 0;
        }

        return crit;
    }

    private static void imprimirComandes() {

        System.out.println("\n+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+");
        System.out.println("|  COMANDES:                                           |");
        System.out.println("|  N    Nou   Document                                 |");
        System.out.println("|  M    Modificar Document                             |");
        System.out.println("|  E    Eliminar Document                              |");
        System.out.println("|  NE   Nova Expresió Booleana                         |");
        System.out.println("|  ME   Modificar Expressió Booleana                   |");
        System.out.println("|  EE   Eliminar Expresió Booleana                     |");
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-|");
        System.out.println("|  CA   Consulta d'Autors per prefix                   |");
        System.out.println("|  CB   Consulta de Documents per expressio booleana   |");
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
