package classes;
import java.time.LocalDate;
import java.util.ArrayList;


/** Consulta utilitzada per obtenir els documents que es troben "dintre" d'un interval de dues dates, segons el seu atribut data.
 * @author Oscar Ramos Nuñez (oscar.ramos.nunez@estudiantat.upc.edu)
 */
public class ConsultaData {

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
