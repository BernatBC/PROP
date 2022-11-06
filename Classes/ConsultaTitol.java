package Classes;
import java.util.HashMap;
import java.util.Set;
import Classes.Document;
import Classes.Frase;

/** Classe per a la consulta de documents per autor.
 * @author Bernat Borràs Civil (bernat.borras.civil@estudiantat.upc.edu)
 */
 public class ConsultaTitol {

    /** Conjunt de llibreries, una per a cada autor. */
    HashMap<String, Set<Document>>  coleccions;

    /** Constructora per defecte. */
    public ConsultaTitol() {
        coleccions = new HashMap<String, Set<Document>>();
    }

    /** Afegeix el document al conjunt corresponent.
     * @param d document que es vol afegir.
     */
    public void afegirDocument(Document d) {
        String autor = d.getAutor().toString();
        Set<Document> s = coleccions.get(autor);
        s.add(d);
        coleccions.put(autor, s);
    }

    /** Elimina el document del conjunt corresponent.
     * @param d document que es vol eliminar.
     */
    public void eliminarDocument(Document d) {
        String autor = d.getAutor().toString();
        Set<Document> s = coleccions.get(autor);
        s.remove(d);
        if (s.size() > 0) coleccions.put(autor, s);
        else coleccions.remove(autor);
    }

    /** Mou el document cap al conjunt de l'autor nou.
     * @param antic document que es vol afegir.
     * @param nou autor que passarà a tenir el document.
     */
    public void canviarAutor(Document antic, Document nou) {
        eliminarDocument(antic);
        afegirDocument(nou);
    }

    /** Retorna el conjunt de documents corresponent a l'autor.
     * @param autor autor dels documents retornats.
     * @return Set<Document> : conjunt de documents corresponent a l'autor.
     */
    public Set<Document> getDocAutor(Frase autor) {
        return coleccions.get(autor.toString());
    }
 }