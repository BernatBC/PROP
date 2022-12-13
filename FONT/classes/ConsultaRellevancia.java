package classes;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;





/** Consulta utilitzada per obtenir els K documents més rellevants d'acord amb una query.
 * @author Oscar Ramos Nuñez (oscar.ramos.nunez@estudiantat.upc.edu)
 */
public class ConsultaRellevancia {
    
    /** Número de documents que es volen en la llibreria resultant. */
    private Integer k;

    /** Conjunt de paraules que formen la query. */
    private Paraula[] query;

    /** Conjunt de k documents més rellevants. */
    private Llibreria docs;


    /** Constructora empty
     * @return Consulta inicialitzada sense documents afegits.
     */
    public ConsultaRellevancia() {
        k = 0;
        query = null;
        docs = new Llibreria();
    }

    /** Constructora amb paràmetres.
     * @return ConsultaRellevancia on K=k_docs, query=words i frase es una frase que conté words, mode serveix per seleccionar quin dels dos mètodes de cerca es vol i documents és el conjunt de tots els documents actuals.
     * @param k_docs
     * @param words
     * @param frase
     * @param mode
     * @param documents
     */
    public ConsultaRellevancia(Integer k_docs, Paraula[] words, String frase, Integer mode, Llibreria documents) {
        k = k_docs;
        query = words;
        docs = new Llibreria();

        //hashmap de suma de ocurrèncias, document 
        TreeMap<Integer,ArrayList<Document>> ordenats = new TreeMap<>();

        if (mode == 1) {
            //mode 1 -> per cada document mirem el n_ocurrencies de cada paraula de la query. Els k documents que sumin més són els seleccionats
       
             for (int i = 0; i < documents.getNdocs(); ++i) {
                 //per cada document del sistema mirem les paraules
                Document doc = documents.getIessim(i);

                //hashMap de paraules id + ocurrencies
                HashMap<Integer, Integer> paraules = doc.getContingut().getWords();
                //System.out.println(paraules.keySet());
                int count = 0;

                 for (int j = 0; j < words.length; ++j) {
                   // System.out.println(j);
                    //System.out.println(query[j].getId());
                     //si la paraula està en el doc
                     if (paraules.containsKey(query[j].getId())) count += paraules.get(query[j].getId());
                     //System.out.print(count);
                }
                //afegim el document amb la suma d'aparicions, en negatiu per donar l'ordre correcte, i el propi document
                ArrayList<Document> temp = new ArrayList<Document>();
                //si ja n'hi havia algun document amb el mateix numero de aparicions l'afegim al ArrayList corresponent.
                if (ordenats.containsKey(-count)) temp = ordenats.get(-count);
                
                temp.add(doc);
                ordenats.put(-count, temp);
                //System.out.println(-count +" "+ doc.getTitol().toString());
            }
        
            //tenim a ordenats tots els documents ordenats de menor a major en base a la suma del nombre d'aparicions de les paraules de la query.
            for (ArrayList<Document> lista: ordenats.values()) {
                //lista = tots els documents que comparteixen suma
                for (int i = 0; i < lista.size() && docs.getNdocs() < k; ++i) {
                    docs.addDocument(lista.get(i));
                    //System.out.println(docs.getNdocs());
                   // if (docs.getNdocs() == k) break;
                }
            }
        }
        ///else metode 2

        else {

            Frase titol = new Frase(words, frase);
            Frase[] frases = {titol};
            Contingut contingut = new Contingut(frase,frases);
            Document auxiliar = new Document(titol, titol, false, null, LocalDate.now(),contingut);
            documents.addDocument(auxiliar);

            ArrayList<Pair<Double,Document>> result = ConsultaSemblant.executeQuery(documents, auxiliar, k, 0);

            documents.deleteDocument(auxiliar);

            for (Pair<Double,Document> p : result) {
                docs.addDocument(p.getR());
            }

        }
    }


    /** Retorna la llibreria construida a la constructora amb els k documents més rellevants.
     * @return Llibreria
    */
    public Llibreria getDocs() {
        return docs;
    }

    public static Llibreria ConsultaRellevancia(Integer k_docs, Paraula[] words, String frase, Integer mode, Llibreria documents) {
        Llibreria docs_consulta = new Llibreria();

        //hashmap de suma de ocurrèncias, document 
        TreeMap<Integer,ArrayList<Document>> ordenats = new TreeMap<>();

        if (mode == 1) {
            //mode 1 -> per cada document mirem el n_ocurrencies de cada paraula de la query. Els k documents que sumin més són els seleccionats
       
             for (int i = 0; i < documents.getNdocs(); ++i) {
                 //per cada document del sistema mirem les paraules
                Document doc = documents.getIessim(i);

                //hashMap de paraules id + ocurrencies
                HashMap<Integer, Integer> paraules = doc.getContingut().getWords();
                //System.out.println(paraules.keySet());
                int count = 0;

                 for (int j = 0; j < words.length; ++j) {
                   // System.out.println(j);
                    //System.out.println(query[j].getId());
                     //si la paraula està en el doc
                     if (paraules.containsKey(words[j].getId())) count += paraules.get(words[j].getId());
                     //System.out.print(count);
                }
                //afegim el document amb la suma d'aparicions, en negatiu per donar l'ordre correcte, i el propi document
                ArrayList<Document> temp = new ArrayList<Document>();
                //si ja n'hi havia algun document amb el mateix numero de aparicions l'afegim al ArrayList corresponent.
                if (ordenats.containsKey(-count)) temp = ordenats.get(-count);
                
                temp.add(doc);
                ordenats.put(-count, temp);
                //System.out.println(-count +" "+ doc.getTitol().toString());
            }
        
            //tenim a ordenats tots els documents ordenats de menor a major en base a la suma del nombre d'aparicions de les paraules de la query.
            for (ArrayList<Document> lista: ordenats.values()) {
                //lista = tots els documents que comparteixen suma
                for (int i = 0; i < lista.size() && docs_consulta.getNdocs() < k_docs; ++i) {
                    docs_consulta.addDocument(lista.get(i));
                    //System.out.println(docs.getNdocs());
                   // if (docs.getNdocs() == k) break;
                }
            }
        }
        ///else metode 2

        else {

            Frase titol = new Frase(words, frase);
            Frase[] frases = {titol};
            Contingut contingut = new Contingut(frase,frases);
            Document auxiliar = new Document(titol, titol, false, null, LocalDate.now(),contingut);
            documents.addDocument(auxiliar);

            ArrayList<Pair<Double,Document>> result = ConsultaSemblant.executeQuery(documents, auxiliar, k_docs, 0);

            documents.deleteDocument(auxiliar);

            for (Pair<Double,Document> p : result) {
                docs_consulta.addDocument(p.getR());
            }

        }
        return docs_consulta;
    }
}
