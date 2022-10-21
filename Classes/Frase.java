import java.io.*;
import java.lang.Math;



/** Classe que representa un conjunt de paraules.
 * @author Oscar Ramos Nuñez (oscar.ramos.nunez@estudiantat.upc.edu)
 */
public class Frase {

    /** Conjunt de paraules de la frase. */
    private String[] Oracio;

    /** Conjunt de caràcters (espai, exclamació, punt, coma, etc) que separen cada parella de paraules de la frase */
    private String[] Puntuacio;

    /** Número de paraules de la frase */
    private int n_paraules;


    /** Constructora per defecte. */
    public Frase() {
        n_paraules = 0;
        Oracio = new String[n_paraules];
        Puntuacio = new String[n_paraules];
    }

    /** Retorna un vector de les paraules que formen la frase */
    /** Returns: String[] */
    public String[] getOracio() {
        return Oracio;
    }

    /** Retorna un vector de les puntuacions que separen les paraules que formen la frase */
    /** Returns: String[] */
    public String[] getPuntuacio() {
        return Puntuacio;
    }

    /** Retorna true si la frase conté les paraules "Paraules" concatenades */
    /** Returns: bool */
    public boolean conteSequencia(String[] Paraules) {
        boolean trobat = false;
        int it = 0;
        int tamany_sequencia = Paraules.length;
        for (int i = 0; i < n_paraules && !trobat; ++i) {
            if (Oracio[i] == Paraules[it]) {
                /* Hem trobat una word de Paraules en Oracio */
                ++it;
                /* Si era l'ultima ja hem acabat */
                if (it == tamany_sequencia) trobat = true;
            }
            else {
                /* Es trenca la sequencia. Cal mirar, però, si es reseteja a 0 o a 1 */
                if (Oracio[i] == Paraules[0]) it = 1;
                else it = 0;
                
            }
        }
        return trobat;
    }


    /** Retorna la frase original en format string */
    /** Returns: String[] */
    public String[] enString() {
        String[] frase = new String[2*n_paraules+1];
        int it_ora = 0; int it_punt = 0;
        for (int i = 0; i < 2*n_paraules+1; ++i) {
            if (i % 2 == 0) {
                frase[i] = Puntuacio[it_punt];
                ++it_punt;
            }
            else {
                frase[i] = Oracio[it_ora];
                ++it_ora;
            }
        }
        return frase;
    }
    

}