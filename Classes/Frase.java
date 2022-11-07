package Classes;
import java.util.ArrayList;
import java.util.HashMap;;



/** Classe que representa un conjunt de paraules.
 * @author Oscar Ramos Nuñez (oscar.ramos.nunez@estudiantat.upc.edu)
 */
public class Frase {

    /** Conjunt de paraules de la frase. */
    private Paraula[] Oracio;

    /** Conjunt de caràcters (espai, exclamació, punt, coma, etc) que separen cada parella de paraules de la frase */
    private ArrayList<String> Puntuacio;

    /** Número de paraules de la frase */
    private int n_paraules;


    /** Frase en string */
    private String text;

    /** Constructora per defecte. 
     * Returns: Frase inicialitzada amb els paràmetres words (pels atributs Oracio i n_paraules) i frase (pels atributs text i Puntuacio). */
    public Frase(ArrayList<Paraula> words, String frase) {
        //paràmetre
        text = frase;
        
        Oracio = new Paraula[0];
        //posem les words
        for (int j = 0; j < words.size(); ++j) Oracio[j] = words.get(j);

        Puntuacio = new ArrayList<String>();
        n_paraules = words.size();

        int aux = frase.length();
        String a_insertP = "";
        char c; 
        for (int i = 0; i < aux; ++i) {
            c = frase.charAt(i);

            /**Mentre estiguem llegint una concatenació de signes de puntuació no els insertem (possible malgrat improbable)
             * Quan llegim una lletra sabem que hem passat a analitzar una paraula (que al estar guardada ja en la línia 36 no fa falta "trackejarla").
             */

            if(isPuntuacio(c)) a_insertP += c;

            else if (!a_insertP.equals("")) {
                    Puntuacio.add(a_insertP);
                    a_insertP = "";
                }
         }

        //comprovem que no quedin paraules signes per insertar (l'ultim punt, per exemple)
        if (!a_insertP.equals("")) Puntuacio.add(a_insertP);

    }

    /** Retorna un vector de les paraules que formen la frase */
    /** Returns: String[] */
    public Paraula[] getOracio() {
        return Oracio;
    }

    /** Retorna un vector de les puntuacions que separen les paraules que formen la frase */
    /** Returns: String[] */
    public ArrayList<String> getPuntuacio() {
        return Puntuacio;
    }

    /** Retorna true si la frase conté les paraules "Paraules" concatenades */
    /** Returns: bool */
    public boolean conteSequencia(String[] Paraules) {
        boolean trobat = false;
        int it = 0;
        int tamany_sequencia = Paraules.length;
        for (int i = 0; i < n_paraules && !trobat; ++i) {
            if (Oracio[i].getParaula() == Paraules[it]) {
                /* Hem trobat una word de Paraules en Oracio */
                ++it;
                /* Si era l'ultima ja hem acabat */
                if (it == tamany_sequencia) trobat = true;
            }
            else {
                /* Es trenca la sequencia. Cal mirar, però, si es reseteja a 0 o a 1 */
                if (Oracio[i].getParaula() == Paraules[0]) it = 1;
                else it = 0;
                
            }
        }
        return trobat;
    }


    /** Retorna true si la frase conté la paraula passada per paràmetre */
    /** Returns: bool */
    public boolean conteParaula(String Paraula) {
        boolean trobat = false;
        int it = 0;
        while (!trobat && it < n_paraules) {
            if (Oracio[it].getParaula() == Paraula) trobat = true;
        }
        return trobat;

    }

    /** Retorna la frase en  format string */
    /** Returns: String */
    public String toString() {
        return text;
    }

    /** Retorna la frase original en format string */
    /** Returns: String[] 
    public String[] enString() {
        String[] frase = new String[2*n_paraules+1];
        int it_ora = 0; int it_punt = 0;
        for (int i = 0; i < 2*n_paraules+1; ++i) {
            if (i % 2 == 0) {
                frase[i] = Puntuacio[it_punt];
                ++it_punt;
            }
            else {
                frase[i] = Oracio[it_ora].getParaula();
                ++it_ora;
            }
        }
        return frase;
    }*/

    /** Retorna una HashMap<Integer,Integer>, amt tants elements com a paraules diferents té la frase; la key correspón amb l'index de la paraula i el value amb el número d'ocurrències d'aquesta en la frase */
    /** Returns: HashMap */
    public HashMap<Integer, Integer> donaWords() {
        HashMap<Integer, Integer> q = new HashMap<Integer, Integer>();
        //parella id-n_aparicions
        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
        Paraula actual; int id;
        for (int i = 0; i < n_paraules; ++i) {
            actual = Oracio[i];
            id = actual.getId();
            if (map.containsKey(id)) map.put(id, map.get(id) +1);
            else map.put(id, 0);
            
        }
        //leemos todas las pairs

        for (Integer i : map.keySet()) {
            Integer nombre = map.get(i);
            q.put(i,nombre);
        }
        return q;

    }


    /** Retorna el nombre de paraules de la frase.
     * Returns: int */
    public int getNparaules() {
        return n_paraules;
    }

    /** Retorna un Map de parelles int int, per cada parella el primer element correspon a la id d'una paraula i el segon al seu IDF.
     * Per cada Paraula retorna una parella.
     * Returns: HashMap 
    public HashMap<Integer, Double> getIdfs() {
        HashMap<Integer, Double> idfs = new HashMap<Integer, Double>();

        for (int i = 0; i < n_paraules; ++i) {
            Paraula p = Oracio[i];
            Double idf = p.getNumDocuments() / ;
            idfs.put(p.getId(),idf);
        }

        return idfs;
    }*/

    public HashMap<Integer, Integer> getNdocs() {
        HashMap<Integer, Integer> idfs = new HashMap<Integer, Integer>();

        for (int i = 0; i < n_paraules; ++i) {
            Paraula p = Oracio[i];
            idfs.put(p.getId(),p.getNumDocuments());
        }

        return idfs;
    }



    
    /** Retorna true si el char paràmetre és un signe de puntuació */
    /** Returns: bool */
    private boolean isPuntuacio(char c) {
        return c == '.' || c == ',' || c == ';' || c == '?' || c == '¿' || c == '!' || c == '¡' || c == '(' || c == ')' ||
         c == '{' || c == '}' || c == '[' || c == ']' || c == ' ';
    }

}