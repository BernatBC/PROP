package classes;

import javax.swing.JFrame;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Set;

/**
 * Classe que inicialitza i controla la capa de presentació.
 * @author Alexandre Ros i Roger (alexandre.ros.roger@estudiantat.upc.edu)
 */
public class CtrlPresentacio {

    CtrlDomini CD;
    esquema ESQ;
    PopupError ERR;

    private CtrlPresentacio(CtrlDomini cd, esquema esq, PopupError err)
    {
        CD = cd; ESQ = esq; ERR = err;
    }

    public void nouDocument(String title, String author, String contingut){

        if (CD.docExists(title, author)){
            mostraError("Document already exists!");
            return;
        }


        String[] contarray = contingut.split("\\p{Punct}");
        ArrayList<String> content = new ArrayList<String>(Arrays.asList(contarray));

        //for (int i = 0; i < content.size(); ++i){
        //    System.out.println(content.get(i));
        //}

        CD.crearDocument(title, author, content, contingut, LocalDate.now(), false);

        System.out.println("\nDocument successfully added!");

    }

    public void eliminarDoc(String titol, String autor){
        CD.eliminarDocument(titol, autor);
    }

    public void exporta(String titol, String autor, int ext, String fname){
        CD.exportarDocument(titol, autor, ext, fname);
    }

    public void importa(String path){
        CD.importFile(path);
    }

    public ArrayList<String> consultaSemb(String titol, String autor, String ndocs, int mode){

        Integer N;

        try {
            N = Integer.parseInt(ndocs);
        } catch (Exception e){
            System.out.println(e);
            return new ArrayList<>();
        }


        return CD.consultaSemb(titol, autor, N, mode);
    }

    public ArrayList<String> consultaDocument(String autor, String titol){
        // La "consultaDocument" retornarà:
        // Autor + Titol + Booleà ("Y"/"N") preferit + data en ISO + Contingut en string
        if (!CD.docExists(titol, autor)){
            mostraError("Document doesn't exist");
            return new ArrayList<>();
        }

        ArrayList<String> myList = new ArrayList<>();

        myList.add(autor); myList.add(titol);
        boolean isFav = CD.getFavourite(titol, autor);

        if (isFav) myList.add("Y");
        else myList.add("N");

        myList.add(CD.getData(titol, autor).toString());
        
        myList.add(CD.getContingut(titol, autor));

        return myList;
    }

    public ArrayList<String> consultaAutor(String consulta){
        Set<String> mySet = CD.donaAutors(consulta);
        ArrayList<String> listauthors = new ArrayList<String>();
        for (String s : mySet) listauthors.add(s);

        return listauthors;
    }

    public ArrayList<String> consultaAvancada(String query, int criteri){
        return CD.consultaSeq(query, criteri);
    }

    public ArrayList<String> consultaTit(String autor, int criteri){
        return CD.consultaTit(autor, criteri);
    }

    public ArrayList<String> consultaRell(String ndocs, String query, Boolean firstMode){
        Integer N;

        try {
            N = Integer.parseInt(ndocs);
        } catch (Exception e){
            System.out.println(e);
            return new ArrayList<>();
        }

        if (firstMode) return CD.consultaRell(query, N, 1);
        else return CD.consultaRell(query, N, 2);
    }

    public ArrayList<String> getAllDocs(){
        return CD.getAllDocs();
    }

    public void novaEB(String cos, String nom){
        CD.novaEB(nom, cos);
    }

    public String getCos(String nom){
        return CD.getCos(nom);
    }

    public void canviaEB(String nom, String noucos){
        CD.canviarEB(nom, noucos);
    }

    public void eliminaEB(String nom)
    {
        CD.eliminarEB(nom);
    }

    public ArrayList<String> consultaEB(String cos, String nom, int mode, int criteria)
    {
        return CD.consultaEB(cos, nom, mode, criteria);
    }

    public ArrayList<String> consultaData(String ant, String post, String option, int criteri){
        LocalDate before = LocalDate.MIN;
        LocalDate after  = LocalDate.MAX;
        
        if (option == "E" || option == "A"){
            try {after = LocalDate.parse(post);}
            catch (Exception e){
                System.out.println(e);
                return new ArrayList<>();
            }
        }

        if (option == "E" || option == "P"){
            try {before= LocalDate.parse(ant);}
            catch (Exception e){
                System.out.println(e);
                return new ArrayList<>();
            }
        }

        return CD.consultaData(before, after, criteri);
    }

    public ArrayList<String> getEBS(){

        return CD.getAllEBS();
    }

    public void modificar_general(String autor, String titol, String contingut, Boolean isFav, String date, String newautor, String newtitol){
        if (!CD.docExists(titol, autor)){
            mostraError("Document no existeix");
            return;
        }

        LocalDate dat = LocalDate.MIN;

        try {dat = LocalDate.parse(date);}
        catch (DateTimeParseException e){
            mostraError(date + " is not a valid date yyyy-mm-dd");
            return;
        }

        String oldcontent = CD.getContingut(titol, autor);

        if (!oldcontent.equals(contingut)){
            System.out.println("Has modificat el contingut");

            String[] contarray = contingut.split("\\p{Punct}");
            ArrayList<String> content = new ArrayList<String>(Arrays.asList(contarray));
    
            CD.modificarContingut(titol, autor, content, contingut);
        }

        CD.modificarData(titol, autor, dat);

        // Modificar preferit
        if (isFav != CD.getFavourite(titol, autor)) CD.togglePreferit(titol, autor);


        if (!autor.equals(newautor)){
            // S'ha modificat l'autor
            System.out.println("Modificació de l'autor");

            CD.modificarAutor(titol, autor, newautor);
        }

        if (!titol.equals(newtitol)){
            System.out.println("Modificació del titol.");
            CD.modificarTitol(titol, newautor, newtitol);
        }

        // Modificar data

        ESQ.update_document();

        System.out.println("Sha modificat tot correctament");
    }

    public ArrayList<String> consultaPref(int criteri){
        return CD.consultaPref(criteri);
    }

    public void mostraError(String missatge) {
        ERR.SetMissatge(missatge);
        ERR.setSize(800, 200);
        ERR.setVisible(true);
    }

    private void run(){
        ESQ.initComponents();
        ESQ.sendCP(this); // to be used with signals and slots
        JFrame mainframe = ESQ.retorna();
        mainframe.setSize(900,900);
        mainframe.setVisible(true);
        CD.importSaved();
        ESQ.update_doc_lists();
    }

    public static void main(String[] args){
        CtrlDomini CD = new CtrlDomini();
        esquema ESQ = new esquema();
        PopupError ERR = new PopupError();

        CtrlPresentacio CP = new CtrlPresentacio(CD, ESQ, ERR);
        CD.setControladorPresentacio(CP);
        CP.run();
    }

}