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
        anterior = LocalDate.MIN;
        posterior = LocalDate.MAX;
        docs = new ArrayList<>();
        n_docs = 0;
    }

    /** Constructora amb la possibilitat d'afegir els dos parametres limits de l'interval.
     * 
     * @param ant
     * @param post
     */
    public ConsultaData(LocalDate ant, LocalDate post) {
        anterior = ant;
        posterior = post;
        docs = new ArrayList<>();
        n_docs = 0;
    }

    /** Afegeix un element de a la llista de documents ordenats per data mantenint l'ordre (en cas d'empatar es posa segons ordre alfabetic). 
     * Returns: Void */
    public void addDoc(Document D) {
        int index = 0; boolean tie = false, putted = false;
        for (int i = 0; i < n_docs; ++i) {
            if (docs.get(i).getData().isAfter(D.getData())) break;
            else if (docs.get(i).getData().isEqual(D.getData())) tie = true;
            else ++index;
        }
        if (tie) {
            //n'hi ha minim 1 document amb la mateixa data -> s'inserta per ordre alfabètic de títol
            while (index < n_docs && docs.get(index).getData().isEqual(D.getData())) {
                if (docs.get(index).getTitol().toString().compareTo(D.getTitol().toString()) < 0) ++index;
                else {
                    docs.add(index,D); 
                    putted = true;
                    break;
                }
            }
            if (index < n_docs && !putted) docs.add(index,D);
        }
        
        else docs.add(index,D);
        //System.out.println("putted " + docs.get(index).getTitol().toString());
        //System.out.println(D.getTitol().toString());
        
        ++n_docs;
        //for (int i = 0; i < n_docs; ++i) System.out.println(docs.get(i).getTitol().toString());  //testing
        //System.out.println("/////////////////");   //testing
    }

    /** Esborrar un element de la llista de documents ordenats per data. 
     * Returns: Void */
    public void deleteDoc(Document D) {
        Boolean esborrat = docs.remove(D);
        if (esborrat) --n_docs;
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

        while(idxini < n_docs && docs.get(idxini).getData().isBefore(anterior)) ++idxini;

        while(idxfin >= 0 && docs.get(idxfin).getData().isAfter(posterior)) --idxfin;

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
