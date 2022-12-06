package classes;

import javax.swing.JFrame;
import java.util.ArrayList;
import java.time.LocalDate;

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

        ArrayList<String> content = new ArrayList<String>();

        // Només de moment
        content.add(contingut);

        CD.crearDocument(title, author, content, LocalDate.now(), false);

        System.out.println("\nDocument successfully added!");

    }

    private void run(){
        ESQ.initComponents();
        ESQ.sendCP(this); // to be used with signals and slots
        JFrame mainframe = ESQ.retorna();
        mainframe.setSize(500,500);
        mainframe.show();
    
    }

    public static void main(String[] args){
        CtrlDomini CD = new CtrlDomini();
        esquema ESQ = new esquema();

        CtrlPresentacio CP = new CtrlPresentacio(CD, ESQ);
        CP.run();
    }

}