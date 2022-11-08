package classes;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;





/** k Documents més rellevants a una query p.
 * @author Oscar Ramos Nuñez (oscar.ramos.nunez@estudiantat.upc.edu)
 */
public class ConsultaRellevancia {
    
    /** Número de documents que es volen en la llibreria resultant */
    private Integer k;

    /** Conjunt de paraules que formen la query, passada per un filtre on s'eliminen les que no estàn a cap document */
    private Paraula[] query;

    /** Conjunt de k documents més rellevants */
    private Llibreria docs;


    /** Constructora empty */
    public ConsultaRellevancia() {
        k = 0;
        query = null;
        docs = new Llibreria();
    }

    /** Constructora per defecte. Li arriba un conjunt de paraules pertanyents al vocabulari (si s'haguessin possat a la query paraules que no pertanyen
     * al vocabulari el controlador les hauria filtrat), un mode per els diversos mètodes de seleccionar elements i la llibreria amb tots els documents */
    public ConsultaRellevancia(Paraula[] words, String frase, Integer mode, Llibreria documents) {
        k = words.length;
        query = words;
        docs = new Llibreria();

        //hashmap de suma de ocurrèncias, document 
        HashMap<Integer,ArrayList<Document>> ordenats = new HashMap<>();

        if (mode == 1) {
            //mode 1 -> per cada document mirem el n_ocurrencies de cada paraula de la query. Els k documents que sumin més són els seleccionats
       
             for (int i = 0; i < documents.getNdocs(); ++i) {
                 //per cada document del sistema mirem les paraules
                Document doc = documents.getIessim(i);

                //hashMap de paraules id + ocurrencies
                HashMap<Integer, Integer> paraules = doc.getContingut().getWords();
                int count = 0;

                 for (int j = 0; j < k; ++j) {

                     //si la paraula està en el doc
                     if (paraules.containsKey(query[0].getId())) count += paraules.get(query[0].getId());
                }
                //afegim el document amb la suma d'aparicions, en negatiu per donar l'ordre correcte, i el propi document
                ArrayList<Document> temp = new ArrayList<Document>();
                if (ordenats.containsKey(-count)) temp = ordenats.get(-count);
                
                temp.add(doc);
                ordenats.put(-count, temp);
            }
        
            //tenim a ordenats tots els documents ordenats de menor a major en base a la suma del nombre d'aparicions de les paraules de la query.
            int items = 0;
            for (ArrayList<Document> lista: ordenats.values()) {
                //lista = tots els documents que comparteixen suma
                for (int i = 0; i < lista.size(); ++i) {
                    docs.addDocument(lista.get(i));
                    ++items;
                    if (items == k) break;
                }
            }
        }
        ///else metode 2

        else {

            Frase titol = new Frase(words, frase);
            Frase[] frases = {titol};
            Contingut contingut = new Contingut(frase,frases);
            Document auxiliar = new Document(titol, titol, false, null, LocalDate.now(),contingut);

            docs = getNdocs(auxiliar,k);

        }
    }


    /** Retorna la llibreria construida a la constructora 
     * Returns: Llibreria.
    */
    public Llibreria getDocs() {
        return docs;
    }
}
