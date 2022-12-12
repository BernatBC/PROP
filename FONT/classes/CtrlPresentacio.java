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

    private CtrlPresentacio(CtrlDomini cd, esquema esq)
    {
        CD = cd; ESQ = esq;        
    }

    public void nouDocument(String title, String author, String contingut){

        if (CD.docExists(title, author)){
            System.out.println("Document already exists!");
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

    public ArrayList<String> consultaSemb(String titol, String autor, String ndocs){

        Integer N;

        try {
            N = Integer.parseInt(ndocs);
        } catch (Exception e){
            System.out.println(e);
            return new ArrayList<>();
        }


        return CD.consultaSemb(titol, autor, N, 0);
    }

    public ArrayList<String> consultaDocument(String autor, String titol){
        // La "consultaDocument" retornarà:
        // Autor + Titol + Booleà ("Y"/"N") preferit + data en ISO + Contingut en string
        if (!CD.docExists(titol, autor)){
            System.out.println("Document doesn't exist");
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

    public ArrayList<String> consultaAvancada(String query){
        return CD.consultaSeq(query, 0);
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

    public ArrayList<String> consultaData(String ant, String post, String option){
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

        return CD.consultaData(before, after, 0);
    }



    public void modificar_general(String autor, String titol, String contingut, Boolean isFav, String date){
        if (!CD.docExists(titol, autor)){
            System.out.println("Document no existeix");
            return;
        }

        LocalDate dat = LocalDate.MIN;

        try {dat = LocalDate.parse(date);}
        catch (DateTimeParseException e){
            System.out.println(date + " is not a valid date yyyy-mm-dd");
            return;
        }

        String oldcontent = CD.getContingut(titol, autor);

        if (!oldcontent.equals(contingut)){
            System.out.println("Has modificat el contingut");

            String[] contarray = contingut.split("\\p{Punct}");
            ArrayList<String> content = new ArrayList<String>(Arrays.asList(contarray));
    
            CD.modificarContingut(titol, autor, content, contingut);
        }

        // Modificar data
        CD.modificarData(titol, autor, dat);

        // Modificar preferit
        if (isFav != CD.getFavourite(titol, autor)) CD.togglePreferit(titol, autor);

        System.out.println("Sha modificat tot correctament");
    }

    public ArrayList<String> consultaPref(int criteri){
        return CD.consultaPref(criteri);
    }

    private void run(){
        ESQ.initComponents();
        ESQ.sendCP(this); // to be used with signals and slots
        JFrame mainframe = ESQ.retorna();
        mainframe.setSize(700,600);
        mainframe.show();
    
    }

    public static void main(String[] args){
        CtrlDomini CD = new CtrlDomini();
        esquema ESQ = new esquema();

        CtrlPresentacio CP = new CtrlPresentacio(CD, ESQ);
        CP.run();
    }

}