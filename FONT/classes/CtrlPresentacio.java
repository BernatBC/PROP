package classes;

import javax.swing.JFrame;
import java.util.ArrayList;
import java.time.LocalDate;
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

        CD.crearDocument(title, author, content, LocalDate.now(), false);

        System.out.println("\nDocument successfully added!");

    }

    public ArrayList<String> consultaAutor(String consulta){
        Set<String> mySet = CD.donaAutors(consulta);
        ArrayList<String> listauthors = new ArrayList<String>();
        for (String s : mySet) listauthors.add(s);

        return listauthors;
    }

    public ArrayList<String> consultaTit(String autor, int criteri){
        return CD.consultaTit(autor, criteri);
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