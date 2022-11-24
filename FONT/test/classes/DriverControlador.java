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
        String title, author;
        ArrayList<String> content;

        CtrlDomini controlador = new CtrlDomini();

        imprimirComandes();
        Scanner read = new Scanner(System.in);

        while (read.hasNextLine()) {
            switch (read.nextLine().toLowerCase()) {
                case "n":
                    System.out.print("Enter the title of the document: ");
                    title = read.nextLine();
                    System.out.print("Enter the author of the document: ");
                    author = read.nextLine();

                    if (controlador.docExists(title, author)){
                        System.out.println("Document already exists!");
                        break;
                    }

                    System.out.println("\nPlease write the body of the document. Separate each phrase by a new line (ENTER). When you're done, press (ENTER) twice.\n");
                    content = new ArrayList<String>();
            
                    while (read.hasNextLine()){
                        content.add(read.nextLine());
                        if (content.get(content.size()-1).equals("")){
                            content.remove(content.size()-1);
                            break;
                        }
                    }
            
                    controlador.crearDocument(title, author, content, LocalDate.now(), false);

                    System.out.println("\nDocument successfully added!");

                break;
                case "m":

                    System.out.print("\nEnter the title of the document: ");
                    title = read.nextLine();
            
                    System.out.print("Enter the author of the document: ");
                    author = read.nextLine();
        
                    if (!controlador.docExists(title, author)){
                        System.out.println("The document does not exist.");
                        break;
                    }

                    System.out.println("\nPreview of the document: \n\n"+controlador.preview(title, author));

                    System.out.println("What do you wish to modify from the document (1..3)?\n");
                    System.out.println("[1] The date of creation");
                    System.out.println("[2] I want to toggle the document's favourite status");
                    System.out.println("[3] I want to modify the name of the author");
                    System.out.println("[4] I want to modify the title of the document");
                    System.out.println("[5] I want to modify the content of the document\n");

                    int choice = read.nextInt();

                    switch(choice){
                        
                        case 1:
                        System.out.print("New date (YYYY MM DD): ");
                        int year = read.nextInt(); int month = read.nextInt(); int day = read.nextInt();
            
                        try {

                            LocalDate newDate = LocalDate.of(year, month, day);

                            controlador.modificarData(title, author, newDate);

                            break;
            
                        } catch (DateTimeException e){
                            System.out.println("\nPlease enter a valid date in the format YYYY MM DD next time.");
                            
                            break;
                        }
            
                        case 2:
            
                        while (true){
                            System.out.print("Do you want to toggle the favourite status (Y/N)? ");
                            String yn = read.nextLine();
    
                            if (yn.toLowerCase().equals("y")){
                                controlador.togglePreferit(title, author);
                                System.out.println("New favourite status is "+controlador.getFavourite(title, author));
                                break;
                            } 
                            else if (yn.toLowerCase().equals("n")) break;
                            else System.out.println("Sorry, "+yn+" not understood. Please answer with Y or N.");
    
                        }
            
                        return;
                        
                        case 3:

                        // Modificació autor
                        System.out.print("Please enter the new author for the document: ");
                        String newAuthor = read.nextLine();

                        controlador.modificarAutor(title, author, newAuthor);

                        System.out.println("Author modified correctly. ");
                        
                        break;

                        case 4:

                        // Modificació títol
                        System.out.print("Please enter the new title for the document: ");
                        String newTitle = read.nextLine();

                        controlador.modificarTitol(title, author, newTitle);
            
                        System.out.println("Title modified correctly. ");

                        case 5:

                        // Modificació del contingut
                        System.out.println("\nPlease write the body of the document. Separate each phrase by a new line (ENTER). When you're done, press (ENTER) twice.\n");
                        content = new ArrayList<String>();
                
                        while (read.hasNextLine()){
                            content.add(read.nextLine());
                            if (content.get(content.size()-1).equals("")){
                                content.remove(content.size()-1);
                                break;
                            }
                        }
                
                        controlador.modificarContingut(title, author, content);

                        System.out.println("Content modified correctly. ");

                        default:
            
                        System.out.println("Didn't recognize "+choice);
                   
                    } // Final del switch(choice)
            
                break;
                case "e":

                    System.out.print("\nEnter the title of the document: ");
                    title = read.nextLine();
            
                    System.out.print("Enter the author of the document: ");
                    author = read.nextLine();
        
                    if (!controlador.docExists(title, author)){
                        System.out.println("The document does not exist.");
                        break;
                    }
                    
                    System.out.println("\nPreview of the document: \n\n"+controlador.preview(title, author));

                    while (true){
                        System.out.print("Are you sure you want to delete the document (Y/N)? ");
                        String yn = read.nextLine();

                        if (yn.toLowerCase().equals("y")){
                            controlador.eliminarDocument(title, author);
                            System.out.println("Document successfully deleted.");
                            break;
                        } 
                        else if (yn.toLowerCase().equals("n")) break;
                        else System.out.println("Sorry, "+yn+" not understood. Please answer with Y or N.");

                    }
                
                break;
                // No ho toco de moment
                case "ne":
                    System.out.println("Exemple d'expressió: ---  ( \"bon dia\" | p2 ) & ( p3 & ! { p4 p5 p6 } ) --- ");
                    System.out.print("Entra una expressió booleana: ");
                    String expressio = new String();
                    
                    while(expressio.isEmpty()){
                        expressio = read.nextLine();}
                    
                    System.out.print("Entra un nom per a l'expressió booleana: ");
                    String nom = read.nextLine();
                    controladorb.ExpressioBooleanaCtrl(nom, expressio);
                break;
                // No ho toco de moment
                case "me":
                    System.out.println("\nExemple d'expressió: ---  ( \"bon dia\" | p2 ) & ( p3 & ! { p4 p5 p6 } ) --- \n");
                    System.out.print("Entra el nom de l'expressió que vols modificar, si no existeix es crearà: ");
                    String nom_modificada = read.nextLine();
                    System.out.print("Entra una expressió booleana: ");
                    String expressio_modificada = read.nextLine();
                    controladorb.SetExpressioBooleana(nom_modificada, expressio_modificada);
                break;
                // No ho toco de moment
                case "ee":
                    System.out.print("Entra un nom per a l'expressió booleana: ");
                    String nom_esborrar = read.nextLine();
                    controladorb.DeleteExpressioBooleana(nom_esborrar);
                break;
                case "ca":

                    System.out.print("Enter the author's prefix: ");
		            author = read.nextLine();

                    printSet(controlador.donaAutors(author));

                break;
                // No ho toco de moment
                case "cb":
                    System.out.println("Selecciona Mode:");
                    System.out.println("[1] Consulta directa");
                    System.out.println("[2] Expressions guardades");
                    int mode = read.nextInt();
                    read.nextLine();
                    if (mode == 1){
                        System.out.println("Exemple d'expressió: ---  ( \"bon dia\" | p2 ) & ( p3 & ! { p4 p5 p6 } ) --- ");
                        System.out.print("Entra una expressió booleana: ");
                        String consultaTemporal = read.nextLine();
                        if (consultaTemporal.isEmpty()){
                            System.out.print ("No pots entrar una expressió buida");
                        }
                        else {
                            ExpressioBooleana e = controladorb.ExpressioBooleanaTemporal(consultaTemporal);
                            imprimirArray(DocumentCtrl.sortDocuments(e.getResultat(l), getCriteris(read)));
                        }
                    }
                    else if (mode == 2){
                        
                        if (controladorb.isEmpty()){
                            System.out.print("No hi ha expressions booleanes guardades!");
                        }
                        else{
                            Set<String> expressions = controladorb.GetNomExpressions();
                            System.out.print("Les expressions que hi ha guardades son: ");
                            System.out.print(expressions);
                            String nom_consultar = read.nextLine();
                            ExpressioBooleana e = controladorb.GetExpressioBooleana(nom_consultar);
                            imprimirArray(DocumentCtrl.sortDocuments(e.getResultat(l), getCriteris(read)));
                        }
                    
                    }
                break;
                case "q":
                    return;
                case "cd":
                    int year, month, day;

                    System.out.println("Selecciona Mode:");
                    System.out.println("[1] Documents entre dues dates");
                    System.out.println("[2] Documents anteriors a una data");
                    System.out.println("[3] Documents posteriors a una data");

                    int tria = read.nextInt();

                    switch (tria){

                        case 1:
                        
                        System.out.println("Enter de lower bound (YYYY MM DD): ");
                        year = read.nextInt(); month = read.nextInt(); day = read.nextInt();
                        System.out.println("Enter the higher bound (YYYY MM DD): ");
                        int year2 = read.nextInt(); int month2 = read.nextInt(); int day2 = read.nextInt();

                        try { 
                            LocalDate ant = LocalDate.of(year, month, day);
                            LocalDate post = LocalDate.of(year2, month2, day2);

                            ArrayList<String> listOfDocs = controlador.consultaData(ant, post, getCriteris(read));

                            for (String s : listOfDocs) System.out.println(s);

                        } catch (DateTimeException e){
                            System.out.println("\nPlease enter a valid date in the format YYYY MM DD next time :)");
                        }

                        break;

                        case 2:

                        System.out.println("Enter the higher bound (YYYY MM DD): ");
                        year = read.nextInt(); month = read.nextInt(); day = read.nextInt();

                        try {
                            LocalDate dat = LocalDate.of(year, month, day);

                            ArrayList<String> listOfDocs = controlador.consultaData(LocalDate.MIN, dat, getCriteris(read));

                            for (String s : listOfDocs) System.out.println(s);
                        } catch (DateTimeException e){
                            System.out.println("\nPlease enter a valid date in the format YYYY MM DD next time :)");
                        }
                        break;
                        case 3:

                        System.out.println("Enter the lower bound (YYYY MM DD): ");
                        year = read.nextInt(); month = read.nextInt(); day = read.nextInt();

                        try {
                            LocalDate dat = LocalDate.of(year, month, day);

                            ArrayList<String> listOfDocs = controlador.consultaData(dat, LocalDate.MAX, getCriteris(read));

                            for (String s : listOfDocs) System.out.println(s);
                        } catch (DateTimeException e){
                            System.out.println("\nPlease enter a valid date in the format YYYY MM DD next time :)");
                        }
                        break;

                    }

                    
                break;
                case "cc":
                    System.out.print("\nEnter the title of the document: ");
                    title = read.nextLine();
            
                    System.out.print("Enter the author of the document: ");
                    author = read.nextLine();
        
                    if (!controlador.docExists(title, author)){
                        System.out.println("The document does not exist.");
                        break;
                    }
                    
                    System.out.println("\nPreview of the document: \n\n"+controlador.preview(title, author));


                break;
                case "cp":
                    ArrayList<String> listOfDocs = controlador.consultaPreferit(getCriteris(read));

                    for (String s : listOfDocs) System.out.println(s);

                    break;
                case "cr1":
                    System.out.print("Entra un conjunt de paraules seperades per espais: ");
                    String s =  read.nextLine();
                    System.out.print("Entra un número de documents: ");
                    int k = read.nextInt();
                    
                    String[] str_s = s.split(" ");
                    Paraula[] arrp = new Paraula[str_s.length];

                    for (int ij = 0; ij < str_s.length; ++ij) arrp[ij] = v.inserirObtenirParaula(str_s[ij]);

                    ConsultaRellevancia cs = new ConsultaRellevancia(k, arrp, s, 1, l);
                    System.out.println(cs.getDocs().toString());
                break;
                case "cr2":
                    System.out.print("Entra un conjunt de paraules seperades per espais: ");
                    String s2 =  read.nextLine();
                    System.out.print("Entra un número de documents: ");
                    int k2 = read.nextInt();

                    String[] str_s2 = s2.split(" ");
                    Paraula[] arrp2 = new Paraula[str_s2.length];

                    for (int ij = 0; ij < str_s2.length; ++ij) arrp2[ij] = v.inserirObtenirParaula(str_s2[ij]);

                    ConsultaRellevancia cs2 = new ConsultaRellevancia(k2, arrp2, s2, 2, l);
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

    private static void printSet(Set<String> mySet){
        for (String s : mySet){
            System.out.println("- "+s);
        }
        System.out.println();
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
        read.nextLine();

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
