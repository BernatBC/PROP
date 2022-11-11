package classes;
import java.util.Set;
import java.util.HashSet;

/** Consulta documents que conte la seqüència indicada.
 * @author Bernat Borràs Civil (bernat.borras.civil@estudiantat.upc.edu)
 */
public class ConsultaAvancada {

    /**
     * Consturctora de la consulta avançada.
     * @param l conjunt de documents del sistema.
     * @param s seqüència que es vol cercar.
     */
    public static Set<Document> obtenirDocuments(Llibreria l, String s) {
        Set<Document> documents = l.getSetDocuments();
        Set<Document> docs = new HashSet<>();
        for (Document d : documents) {
            if (d.conteSequencia(s)) docs.add(d);
        }
        return docs;
    }
}
