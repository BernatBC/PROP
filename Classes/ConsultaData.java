import java.time.LocalDate;
import java.util.HashMap;
import java.util.Vector;

import Llibreria.java;
import Document.java;
import Frase.java;
import java.util.ArrayList;

/** Dates.
 * @author Oscar Ramos Nuñez (oscar.ramos.nunez@estudiantat.upc.edu)
 */
public class ConsultaData {
    
    /** Data inicial de l'interval a buscar (possiblement NULL -> es busca des de la data 0) */
    private LocalDate anterior;

    /** Data final de l'interval a buscar (possiblement NULL -> es busca fins a la data maxima) */
    private LocalDate posterior;

    /** Llibreria dels Documents ordenats per data */
    private Llibreria docs;


    /** Constructora empty */
    public ConsultaData() {
        anterior = null;
        posterior = null;
        docs = new Llibreria();
    }

    /** Constructora, a l'interficie una casella deixada en blanc -> Null -> valor maxim o minim depenent de la casella */
    public ConsultaData(LocalDate ant, LocalDate pos,Llibreria documents) {
        anterior = ant;
        posterior = pos;
        //vector de N_documents posicions
        docs = new Llibreria();
        
        for (int i = 0; i < documents.getNdocs(); ++i) {
            //per cada document del sistema mirem les dates
            Document doc = documents.getIessim(i);
            LocalDate data = doc.getData();
            if ( (data.isAfter(anterior) || data.isEqual(anterior)) &&  (data.isAfter(posterior) || data.isEqual(posterior))) docs.addDocument(doc);
        }
        

    }

    /** Retorna la data inicial de l'interval de cerca */
    /** Returns: LocalDate */
    public LocalDate getAnterior() {
        return anterior;
    }

    /** Retorna la data final de l'interval de cerca */
    /** Returns: LocalDate */
    public LocalDate getPosterior() {
        return posterior;
    }


    /** Fixa la data inicial de l'interval de cerca  
     * Returns: void */
    public void setAnterior(LocalDate data) {
        anterior = data;
    }

    /** Fixa la data final de l'interval de cerca  
     * Returns: void */
    public void setPosterior(LocalDate data) {
        posterior = data;
    }





}
