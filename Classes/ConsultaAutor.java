import java.util.HashMap;
import Llibreria.java;
import Document.java;
import Frase.java;

/** Diccionari de les paraules.
 * @author Bernat Borràs Civil (bernat.borras.civil@estudiantat.upc.edu)
 */
 public class ConsultaAutor {

    /** Conjunt de llibreries, una per a cada autor. */
    HashMap<String, Llibreria>  coleccions;

    /** Constructora per defecte. */
    public ConsultaAutor() {
        coleccions = new HashMap<String, Llibreria>();
    }

    /** Afegeix el document a la llibreria corresponent.
     * @param d document que es vol afegir.
     */
    public void afegirDocument(Document d) {

    }

    /** Elimina el document de la llibreria corresponent.
     * @param d document que es vol afegir.
     */
    public void eliminarDocument(Document d) {

    }

    /** Mou el document cap a la llibreria de l'autor nou.
     * @param d document que es vol afegir.
     * @param nou_autor autor que passarà a tenir el document.
     */
    public void canviarAutor(Document d, Frase nou_autor) {

    }
 }