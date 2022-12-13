package classes;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/** Consulta utilitzada per obtenir els documents que es troben "dintre" d'un interval de dues dates, segons el seu atribut data.
 * @author Oscar Ramos Nuñez (oscar.ramos.nunez@estudiantat.upc.edu)
 */
public class ConsultaData {
    
    /** Data inicial de l'interval a buscar (possiblement NULL -> es busca des de la data mínima). */
    private LocalDate anterior;

    /** Data final de l'interval a buscar (possiblement NULL -> es busca fins a la data màxima). */
    private LocalDate posterior;

    /** Llibreria dels Documents dintre de l'interval de dates ordenats per data. */
    private ArrayList<Document> docs;

    /** Nombre de documents actual. */
    private Integer n_docs;


    /** Constructora per defecte.
     * @return Consulta sense documents on l'interval és el màxim possible que ens permet LocalDate (per tal d'obtenir tots els documents que s'insereixin, si no es canvien els paràmetres "anterior" i "posterior").
     */
    public ConsultaData() {
        anterior = LocalDate.MIN;
        posterior = LocalDate.MAX;
        docs = new ArrayList<>();
        n_docs = 0;
    }

    /** Constructora amb la possibilitat d'afegir els paràmetres limits de l'interval.
     * @return Consulta sense documents i l'interval de Dates de cerca acotat.
     * @param ant
     * @param post
     */
    public ConsultaData(LocalDate ant, LocalDate post) {
        anterior = ant;
        posterior = post;
        docs = new ArrayList<>();
        n_docs = 0;
    }

    /** Afegeix un element de a la llista de documents ordenats per data mantenint l'ordre cronològic (en cas d'empatar es posa segons ordre alfabètic d'entre els documents amb els que comparteix Data). 
     * @param D
    */
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
            if (!putted) docs.add(index,D);
        }
        
        else docs.add(index,D);
        
        ++n_docs;

    }

    /** Esborrar un element de la llista de documents ordenats per data. 
     * @param D
     */
    public void deleteDoc(Document D) {
        Boolean esborrat = docs.remove(D);
        if (esborrat) --n_docs;
    }

    /** Consulta anterior a una Data (i només 1).
     * @return ArrayList de documents els quals són anteriors a la Data max.
     * @param max
      */
    public ArrayList<Document> consultaAnterior(LocalDate max){
        anterior = LocalDate.MIN;
        posterior = max;
        return consulta();

    }

    /** Consulta posterior a una Data (i només 1).
     * @return ArrayList de documents els quals són posteriors a la Data min.
     * @param min
      */
    public ArrayList<Document> consultaPosterior(LocalDate min){
        anterior = min;
        posterior = LocalDate.MAX;
        return consulta();

    }


    /** Consulta per l'interval. Ídem que la consulta sense paràmetres però amb la possibilitat de posar nous paràmetres en la pròpia crida. 
     * @return ArrayList de documents els quals la seva data és igual o posterior a "anterior" i igual o anterior a "posterior".
     * @param ant
     * @param pos
     */
    public ArrayList<Document> consulta(LocalDate ant, LocalDate pos) {
        anterior = ant; posterior = pos;
        return consulta();
    }


    /** Consulta per l'interval. Troba l'índex inicial i final del nostre array de tots els documents ordenats i construeix un subarray amb els documents de l'interval.
     * @return ArrayList de documents els quals la seva data és igual o posterior a "anterior" i igual o anterior a "posterior".
     */
    public ArrayList<Document> consulta() {
        int i = 0;
        int j = n_docs - 1;

        while (i < n_docs && docs.get(i).getData().isBefore(anterior)) ++i;
        while (j >= 0 && docs.get(j).getData().isAfter(posterior)) --j;

        ArrayList<Document> interval = new ArrayList<>();

        for (int k = i; k <= j; ++k){
            interval.add(docs.get(k));
        }

        return interval;
    }


    /** Retorna la data inicial de l'interval de cerca.
     * @return: LocalDate "anterior"
     */
    public LocalDate getAnterior() {
        return anterior;
    }

    /** Retorna la data final de l'interval de cerca.
     * @return: LocalDate "posterior"
     */
    public LocalDate getPosterior() {
        return posterior;
    }


    /** Fixa la data inicial de l'interval de cerca. 
     * @param data
     */
    public void setAnterior(LocalDate data) {
        anterior = data;
    }

    /** Fixa la data final de l'interval de cerca. 
     * @param data
     */
    public void setPosterior(LocalDate data) {
        posterior = data;
    }



    public static ArrayList<Document> consultaAnterior(ArrayList<Document> documents, LocalDate max){
        return retornarDocs(documents, LocalDate.MIN, max);
    }

    /** Consulta posterior a una Data (i només 1).
     * @return ArrayList de documents els quals són posteriors a la Data min.
     * @param min
      */
    public static ArrayList<Document> consultaPosterior(ArrayList<Document> documents, LocalDate min){
        return retornarDocs(documents, min, LocalDate.MAX);

    }

    /** Consulta per l'interval. Ídem que la consulta sense paràmetres però amb la possibilitat de posar nous paràmetres en la pròpia crida. 
     * @return ArrayList de documents els quals la seva data és igual o posterior a "anterior" i igual o anterior a "posterior".
     * @param ant
     * @param pos
     */
    public static ArrayList<Document> consulta(ArrayList<Document> documents, LocalDate ant, LocalDate pos) {
        return retornarDocs(documents, ant, pos);
    }

    private static ArrayList<Document> retornarDocs(ArrayList<Document> documents, LocalDate anterior, LocalDate posetrior) {
        int i = 0;
        int j = documents.size() - 1;

        while (i < documents.size() && documents.get(i).getData().isBefore(anterior)) ++i;
        while (j >= 0 && documents.get(j).getData().isAfter(posetrior)) --j;

        ArrayList<Document> interval = new ArrayList<>();

        for (int k = i; k <= j; ++k){
            interval.add(documents.get(k));
        }

        return interval;
    }
}
