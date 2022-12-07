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
        CtrlPersistencia persistencia = new CtrlPersistencia(controlador);

        persistencia.importarDades();

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

                    int choice = read.nextInt(); read.nextLine();

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
            
                        break;
                        
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

                        break;

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

                        break;

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
                case "ne":
                    System.out.println("Exemple d'expressió: ---  ( \"bon dia\" | p2 ) & ( p3 & ! { p4 p5 p6 } ) --- ");
                    System.out.print("Entra una expressió booleana: ");
                    String expressio = new String();
                    
                    while(expressio.isEmpty()) expressio = read.nextLine();
                    
                    System.out.print("Entra un nom per a l'expressió booleana: ");
                    String nom = read.nextLine();

                    controlador.novaEB(nom, expressio);
                break;
                case "me":
                    System.out.println("\nExemple d'expressió: ---  ( \"bon dia\" | p2 ) & ( p3 & ! { p4 p5 p6 } ) --- \n");
                    System.out.print("Entra el nom de l'expressió que vols modificar, si no existeix es crearà: ");
                    String nom_modificada = read.nextLine();
                    System.out.print("Entra una expressió booleana: ");
                    String expressio_modificada = read.nextLine();

                    controlador.canviarEB(nom_modificada, expressio_modificada);
                break;
                case "ee":
                    System.out.print("Entra un nom per a l'expressió booleana: ");
                    String nom_esborrar = read.nextLine();
                    controlador.eliminarEB(nom_esborrar);
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
                            break;
                        }
                        else {
                            ArrayList<String> listDocs = controlador.consultaEB(consultaTemporal, null, 1, getCriteris(read));
                            for (String s : listDocs) System.out.println(s);

                        }
                    }
                    else if (mode == 2){
        
                        if (controlador.numberOfEBS() <= 0){
                            System.out.println("No hi ha expressions booleanes guardades!");
                        }
                        else{
                            System.out.print("Entra el nom d'una expressió booleana: ");
                            String nom_consultar = read.nextLine();

                            if (!controlador.existsEB(nom_consultar)){
                                System.out.println("Error. L'expressió booleana "+nom_consultar+" no existeix.");
                                break;
                            }

                            ArrayList<String> listDocss = controlador.consultaEB(null, nom_consultar, 2, getCriteris(read));
                            for (String s : listDocss) System.out.println(s);

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
                    ArrayList<String> listOfDocs = controlador.consultaPref(getCriteris(read));

                    for (String s : listOfDocs) System.out.println(s);

                    break;
                case "cr1":
                    System.out.print("Entra un conjunt de paraules seperades per espais: ");
                    String s =  read.nextLine();
                    System.out.print("Entra un número de documents: ");
                    int k = read.nextInt();
                    
                    System.out.println(controlador.consultaRell(s, k, 1));

                break;
                case "cr2":
                    System.out.print("Entra un conjunt de paraules seperades per espais: ");
                    String s2 =  read.nextLine();
                    System.out.print("Entra un número de documents: ");
                    int k2 = read.nextInt();
                    
                    System.out.println(controlador.consultaRell(s2, k2, 2));
                
                    break;
                case "cs":
                    System.out.print("\nEnter the title of the document: ");
                    title = read.nextLine();
            
                    System.out.print("Enter the author of the document: ");
                    author = read.nextLine();
        
                    if (!controlador.docExists(title, author)){
                        System.out.println("The document does not exist.");
                        break;
                    }

                    System.out.print("Enter the number of documents: ");
                    int k3 = read.nextInt();
                    System.out.println("\nWhich weight assignment do you wish for words? ");
                    System.out.println("[0] TF-IDF");
                    System.out.println("[1] By ocurrences");
                    int k9  =read.nextInt();
                    
                    if (k9 != 0 && k9 != 1) System.out.println("\nOption "+k9+ " not found.");
                    else System.out.println(controlador.consultaSemb(title, author, k3, k9));
                    //else imprimirSemblant(ConsultaSemblant.executeQuery(l, l.getDocument(a, t).getL(), k3, k9));

                break;
                case "cseq":
                    System.out.print("Enter the sequence to search for: ");
                    String myseq = read.nextLine();

                    ArrayList<String> listOfDoc = controlador.consultaSeq(myseq, getCriteris(read));
                    for (String qp : listOfDoc) System.out.println(qp);

                break;
                case "ct":
                    System.out.print("Enter the author of the document: ");
                    String aut = read.nextLine();

                    ArrayList<String> myList = controlador.consultaTit(aut, getCriteris(read));
                    for (String qp : myList) System.out.println(qp);

                break;

                case "i":
                    System.out.println("Enter the path of the document: ");
                    String path = read.nextLine();
                    persistencia.importFile(path);
                break;
                case "x":
                    System.out.print("\nEnter the title of the document: ");
                    title = read.nextLine();
            
                    System.out.print("Enter the author of the document: ");
                    author = read.nextLine();
        
                    if (!controlador.docExists(title, author)){
                        System.out.println("The document does not exist.");
                        break;
                    }

                    System.out.println("Enter the path followed by the name and extension of the document: ");
                    path = read.nextLine();

                    persistencia.export(title, author, controlador.preview(title, author), "1999-04-12", true, path);


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
        System.out.println("|  I    Importar Document                              |");
        System.out.println("|  X    Exportar Document                              |");
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+");
        System.out.println("|  Q    Sortir de l'aplicació                          |");
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+--+-+-+-+-+-+-+\n");
    }
}
