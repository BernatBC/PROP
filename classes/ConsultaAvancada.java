package classes;
import java.util.Set;

/** Consulta documents que conte la seqüència indicada.
 * @author Bernat Borràs Civil (bernat.borras.civil@estudiantat.upc.edu)
 */
public class ConsultaAvancada {

    Set<Document> docs;

    /**
     * Consturctora de la consulta avançada.
     * @param l conjunt de documents del sistema.
     * @param s seqüència que es vol cercar.
     */
    public ConsultaAvancada(Llibreria l, String s) {
        Set<Document> documents = l.getSetDocuments();
        for (Document d : documents) {
            if (d.conteSequencia(s)) docs.add(d);
        }
    }

    /**
     * Obté els documents de la seqüència de la constructora.
     * @return
     */
    public Set<Document> obtenirDocuments() {
        return docs;
    }
}
