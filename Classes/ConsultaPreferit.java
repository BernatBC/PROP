package Classes;
import java.util.Set;

/** Consulta documents preferits.
 * @author Bernat Borràs Civil (bernat.borras.civil@estudiantat.upc.edu)
 */
 public class ConsultaPreferit {

    /** Conjunt de documents preferits. */
    Set<Document> preferits;

    /** Constructora per defecte. */
    public ConsultaPreferit() {
        
    }

    /** Afegeix el document a la llibreria.
     * @param d document que es vol afegir.
     */
    public void afegirDocument(Document d) {
        preferits.add(d);
    }

    /** Elimina el document del conjunt.
     * @param d document que es vol eliminar.
     */
    public void eliminarDocument(Document d) {
        preferits.remove(d);
    }

    /** Retorna el conjunt de documents preferits.
     * @return Set<Document> : conjunt dels documents preferits.
     */
    public Set<Document> getDocPreferit() {
        return preferits;
    }
 }