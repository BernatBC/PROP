package classes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


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
        if (s == null) s = new HashSet<>();
        s.add(d);
        coleccions.put(autor, s);
    }

    /** Elimina el document del conjunt corresponent.
     * @param d document que es vol eliminar.
     */
    public void eliminarDocument(Document d) {
        String autor = d.getAutor().toString();
        Set<Document> s = coleccions.get(autor);
        if (s == null) return;
        s.remove(d);
        if (s.size() > 0) coleccions.put(autor, s);
        else coleccions.remove(autor);
    }

    /** Retorna el conjunt de documents corresponent a l'autor.
     * @param autor autor dels documents retornats.
     * @return Set<Document> : conjunt de documents corresponent a l'autor.
     */
    public Set<Document> getDocAutor(Frase autor) {
        return coleccions.get(autor.toString());
    }
 }