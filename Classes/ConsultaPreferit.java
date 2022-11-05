package Classes;
import Classes.Llibreria.java;
import Classes.Document.java;

/** Diccionari de les paraules.
 * @author Bernat Borràs Civil (bernat.borras.civil@estudiantat.upc.edu)
 */
 public class ConsultaPreferit {

    /** Conjunt de llibreries, una per a cada autor. */
    Llibreria preferits;

    /** Constructora per defecte. */
    public ConsultaPreferit() {
        preferits = new Llibreria();
    }

    /** Afegeix el document a la llibreria.
     * @param d document que es vol afegir.
     */
    public void afegirDocument(Document d) {
        preferits.addDocument(d);
    }

    /** Elimina el document de la llibreria.
     * @param d document que es vol eliminar.
     */
    public void eliminarDocument(Document d) {
        preferits.deleteDocument(d);
    }

    /** Retorna la llibreria de preferits.
     * @return Llibreria : llibreria dels documents preferits.
     */
    public Llibreria getDocPreferit() {
        return preferits;
    }
 }