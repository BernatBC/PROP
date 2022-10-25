import java.time.LocalDate;
import java.util.HashMap;
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

    /** Array dels Documents que compleixen l'interval */
    private ArrayList<Document> docs;

    /** Constructora default, a l'interficie una casella deixada en blanc -> Null -> valor maxim o minim depenent de la casella */
    public ConsultaData(LocalDate ant, LocalDate post) {
        anterior = ant;
        posterior = post;
        docs = new ArrayList<Document>();
//algo aixi
        for (int i = 0; i < Llibreria.getNdocs()) {
            Document d = Llibreria[i].first();
            LocalDate aux = d.getData();
            if ((aux == anterior) || (aux == posterior) || (aux.isAfter(anterior) && aux.isBefore(posterior))) docs.add(d);
        }
    }

    public LocalDate getAnterior() {
        return anterior;
    }

    public LocalDate getPosterior() {
        return posterior;
    }





}
