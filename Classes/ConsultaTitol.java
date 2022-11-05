package Classes;
import java.util.HashMap;
import Classes.Llibreria;
import Classes.Document;
import Classes.Frase;

/** Classe per a la consulta de documents per autor.
 * @author Bernat Borràs Civil (bernat.borras.civil@estudiantat.upc.edu)
 */
 public class ConsultaTitol {

    /** Conjunt de llibreries, una per a cada autor. */
    HashMap<String, Llibreria>  coleccions;

    /** Constructora per defecte. */
    public ConsultaTitol() {
        coleccions = new HashMap<String, Llibreria>();
    }

    /** Afegeix el document a la llibreria corresponent.
     * @param d document que es vol afegir.
     */
    public void afegirDocument(Document d) {
        Frase autor = d.getAutor().enString();
        Llibreria l = coleccions.get(autor);
        if (l == null) l = new Llibreria();
        l.addDocument(d);
        coleccions.put(autor, l);
    }

    /** Elimina el document de la llibreria corresponent.
     * @param d document que es vol eliminar.
     */
    public void eliminarDocument(Document d) {
        Frase autor = d.getAutor().enString();
        Llibreria l = coleccions.get(autor);
        l.deleteDocument(d);
        if (l.getNDocuments() != 0) coleccions.put(autor, l);
        else coleccions.remove(autor);
    }

    /** Mou el document cap a la llibreria de l'autor nou.
     * @param antic document que es vol afegir.
     * @param nou autor que passarà a tenir el document.
     */
    public void canviarAutor(Document antic, Document nou) {
        eliminarDocument(antic);
        afegirDocument(nou);
    }

    /** Retorna la llibreria corresponent a l'autor.
     * @param autor autor dels documents retornats.
     * @return Llibreria : llibreria corresponent a l'autor.
     */
    public Llibreria getDocAutor(Frase autor) {
        return coleccions.get(autor.enString());
    }
 }