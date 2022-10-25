import java.io.*;
import java.lang.Math;
import Paraula.java;
import java.util.ArrayList;
import javafx.util.Pair;
import java.util.Map;
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

    /** Constructora per defecte. */
    public Frase(String frase) {
        text = frase;
        Oracio = new Paraula[0];
        Puntuacio = new ArrayList<String>();
        n_paraules = 0;

        int aux = frase.length();
        String a_insertP = "", a_insertW = "";
        char c; 
        for (int i = 0; i < aux; ++i) {
            c = frase.charAt(i);

            /**Mentre estiguem llegint una concatenació de caràcters d'una paraula no insertem la Paraula a Oració. 
             *Un cop llegim un signe de puntuació sí que insertem a_insertW (la paraula temporal construida pels chars llegits de la paraula).
             *El mateix per una concatenació de signes de puntuació (possible malgrat improbable). */

            if(isPuntuacio(c)) {
                if (!a_insertW.equals("")) {
                    //crea paraula amb mot a_insertW i a_insertW = NULL
                    a_insertW = "";
                    ++n_paraules;
                }
                a_insertP += c;
            }

            else {
                if (!a_insertP.equals("")) {
                    Puntuacio.add(a_insertP);
                    a_insertP = "";
                }
                a_insertW += c;
            }
        }
        //comprovem que no quedin paraules paraules per insertar (l'ultima)
        if (!a_insertW.equals("")) {
            //crea paraula amb mot a_insertW i a_insertW = NULL
            ++n_paraules;
        }

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


    public ArrayList<Pair<Integer, Integer>> donaWords() {
        ArrayList<Pair<Integer, Integer>> q = new ArrayList<Pair< Integer,Integer>>();
        //parella id-n_aparicions
        Map<Integer,Integer> map = new HashMap<Integer, Integer>();
        Paraula actual; int id;
        for (int i = 0; i < n_paraules; ++i) {
            actual = Oracio[i];
            id = actual.getIndex();
            if (map.containsKey(id)) map.put(id, map.get(id) +1);
            else map.put(id, 0);
            
        }
        //leemos todas las pairs

        for (Integer i : map.keySet()) {
            Integer nombre = map.get(i);
            Pair<Integer, Integer> par = new Pair<>(i,nombre);
            q.add(par);
        }
        return q;

    }
    

    private boolean isPuntuacio(char c) {
        return c == '.' || c == ',' || c == ';' || c == '?' || c == '¿' || c == '!' || c == '¡' || c == '(' || c == ')' ||
         c == '{' || c == '}' || c == '[' || c == ']' || c == ' ';
    }

}