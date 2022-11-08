package classes;
import java.util.Set;


/** Estructura de dades per contenir el diccionari de paraules.
 * @author Bernat Borràs Civil (bernat.borras.civil@estudiantat.upc.edu)
 */
public class TernaryTreeAutor {

    /**Node fill de l'esquerra. */
    TernaryTreeAutor esquerra;

    /**Node fill de la dreta. */
    TernaryTreeAutor dreta;

    /**Node fill del centre. */
    TernaryTreeAutor centre;

    /**Apuntador de la paraula que el node fa referència. */
    Set<Frase> autors;

    /** Lletra que conté el node. */
    char lletra;

    /**Constructora per defecte de TernaryTree. */
    public TernaryTreeAutor() {
        esquerra = null;
        dreta = null;
        centre = null;
        autors = null;
        lletra = ' ';
    }

    /**Constructora per defecte de TernaryTree.
     * @param c lletra que representa el node nou.
     */
    public TernaryTreeAutor(char c) {
        esquerra = null;
        dreta = null;
        centre = null;
        autors = null;
        lletra = c;
    }

    /** Insereix la paraula p al diccionari.
     * @param p paraula que es vol inserir.
     * @param s paraula que es vol inserir en format de string.
     * @param i índex de la paraula des d'on falta inserir.
     */
    public void inserirAutor(Frase a, String s, int i) {
        if (lletra == ' ') lletra = s.charAt(i);
        if (s.charAt(i) == lletra) {
            if (i == s.length() - 1) {
                autors.add(a);
                return;
            }
            if (centre == null) centre = new TernaryTreeAutor(s.charAt(i + 1));
            centre.inserirAutor(a, s, i + 1);
        }
        else if (s.charAt(i) > lletra) {
            if (dreta == null) dreta = new TernaryTreeAutor(s.charAt(i));
            dreta.inserirAutor(a, s, i);
        }
        else {
            if (esquerra == null) esquerra = new TernaryTreeAutor(s.charAt(i));
            esquerra.inserirAutor(a, s, i);
        }
    }

    /**Obtenir la classe Paraula que correspon a la seqüència s.
     * @param s seqüencia de caràcters que forma la paraula.
     * @param i index de la paraula des d'on falta fer la cerca.
     * @return Paraula : Classe paraula.
     */
    public Set<Frase> obtenirAutors(String s, int i) {
        if (s.charAt(i) > lletra && dreta != null) return dreta.obtenirAutors(s, i);
        if (s.charAt(i) < lletra && esquerra != null) return esquerra.obtenirAutors(s, i);
        if (s.charAt(i) == lletra) {
            if (i < s.length() - 1 && centre != null) return centre.obtenirAutors(s, i + 1);
            if (i == s.length() - 1) return autors;
        }
        System.out.println("Paraula " + s + " no trobada al diccionari.");
        return null;
    }

    /**Esborrar la classe Paraula que correspon a la seqüència s.
     * @param s seqüencia de caràcters que forma la paraula.
     * @param i index de la paraula des d'on falta fer la cerca.
     * @param esborrable últim node que pot esborrar un dels seus fills.
     * @param dir direcció del node fill que es pot esborrar.
     */
    /*public void esborrarAutor(String s, int i, TernaryTree esborrable, int dir) {

        if (s.charAt(i) > lletra && dreta != null) {
            if (dreta != null || esquerra != null || paraula != null) {
                esborrable = this;
                dir = 2;
            }
            dreta.esborrarAutor(s, i, esborrable, dir);
        }
        if (s.charAt(i) < lletra && esquerra != null) {
            if (dreta != null || esquerra != null || paraula != null) {
                esborrable = this;
                dir = 0;
            }
            esquerra.esborrarAutor(s, i, esborrable, dir);
        }
        if (s.charAt(i) == lletra) {
            if (i < s.length() - 1 && centre != null) {
                if (dreta != null || esquerra != null || paraula != null) {
                    esborrable = this;
                    dir = 0;
                }
                centre.esborrarAutor(s, i, esborrable, dir);
            }
            if (i == s.length() - 1 && paraula != null) {
                if (esborrable == null || centre != null) paraula = null;
                else {
                    if (dir == 0) esborrable.esquerra = null;
                    else if (dir == 1) esborrable.centre = null;
                    else esborrable.esquerra = null;
                }
            }
        }
    }*/

}
