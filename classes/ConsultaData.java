package classes;
import java.time.LocalDate;
import java.util.ArrayList;


/** Dates.
 * @author Oscar Ramos Nuñez (oscar.ramos.nunez@estudiantat.upc.edu)
 */
public class ConsultaData {
    
    /** Data inicial de l'interval a buscar (possiblement NULL -> es busca des de la data 0) */
    private LocalDate anterior;

    /** Data final de l'interval a buscar (possiblement NULL -> es busca fins a la data maxima) */
    private LocalDate posterior;

    /** Llibreria dels Documents dintre de l'interval de dates ordenats per data */
    private ArrayList<Document> docs;

    /** Nombre de documents actual */
    private Integer n_docs;


    /** Constructora per defecte */
    public ConsultaData() {
        anterior = null;
        posterior = null;
        docs = null;
    }

    /** Afegeix un element de a la llista de documents ordenats per data mantenint l'ordre (en cas d'empatar es posa l'ultim dintre d'aquesta data). 
     * Returns: Void */
    public void addDoc(Document D) {
        int index = 0;
        for (int i = 0; i < n_docs; ++i) {
            if (docs.get(i).getData().isAfter(D.getData())) break;
        }
        docs.add(index,D);
    }

    /** Esborrar un element de la llista de documents ordenats per data. 
     * Returns: Void */
    public void deleteDoc(Document D) {
        if (docs.remove(D) == false);  //error
    }


    /** Consulta de l'interval. Ídem que la consulta sense paràmetres però amb la possibilitat de posar nous paràmetres en la pròpia crida. 
     * Returns: ArrayList */
    public ArrayList<Document> consulta(LocalDate ant, LocalDate pos) {
        anterior = ant; posterior = pos;
        return consulta();
    }


    /** Consulta de l'interval. Troba l'index inicial i final del nostre array de tots els documents ordenats i construeix un subarray amb els documents de l'interval
     * Returns: ArrayList*/
    public ArrayList<Document> consulta() {
        ArrayList<Document> interval = new ArrayList<>();
        Integer idxini = 0, idxfin = n_docs-1;

        while(docs.get(idxini).getData().isBefore(anterior)) ++idxini;

        while(docs.get(idxfin).getData().isAfter(posterior)) --idxini;

        for (int q = idxini; q <= idxfin; ++q) interval.add(docs.get(q));

        return interval;
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
