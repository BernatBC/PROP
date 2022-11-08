package classes;
import java.util.Set;


/** Estructura de dades per contenir l'arbre de prefixos dels autors.
 * @author Bernat Borràs Civil (bernat.borras.civil@estudiantat.upc.edu)
 */
public class TernaryTreeAutor {

    /**Node fill de l'esquerra. */
    TernaryTreeAutor esquerra;

    /**Node fill de la dreta. */
    TernaryTreeAutor dreta;

    /**Node fill del centre. */
    TernaryTreeAutor centre;

    /**Conjunt d'autors que contenen el prefix d'aquell node. */
    Set<Frase> autors;

    /** Lletra que conté el node. */
    char lletra;

    /**Constructora per defecte de TernaryTreeAutor. */
    public TernaryTreeAutor() {
        esquerra = null;
        dreta = null;
        centre = null;
        autors = null;
        lletra = ' ';
    }

    /**Constructora per defecte de TernaryTreeAutor.
     * @param c lletra que representa el node nou.
     */
    public TernaryTreeAutor(char c) {
        esquerra = null;
        dreta = null;
        centre = null;
        autors = null;
        lletra = c;
    }

    /** Insereix l'autor a a l'arbre.
     * @param a autor que es vol inserir.
     * @param s autor que es vol inserir en format de string.
     * @param i índex de l'autor des d'on falta inserir.
     */
    public void inserirAutor(Frase a, String s, int i) {
        autors.add(a);
        if (lletra == ' ') lletra = s.charAt(i);
        if (s.charAt(i) == lletra) {
            if (i == s.length() - 1) return;
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

    /**Obtenir els conjunt d'autors que conten el prefix s.
     * @param s seqüencia de caràcters que forma el prefix.
     * @param i index del prefix des d'on falta fer la cerca.
     * @return Set<Frase> : Conjunt d'autors.
     */
    public Set<Frase> obtenirAutors(String s, int i) {
        if (s.charAt(i) > lletra && dreta != null) return dreta.obtenirAutors(s, i);
        if (s.charAt(i) < lletra && esquerra != null) return esquerra.obtenirAutors(s, i);
        if (s.charAt(i) == lletra) {
            if (i < s.length() - 1 && centre != null) return centre.obtenirAutors(s, i + 1);
            if (i == s.length() - 1) return autors;
        }
        return null;
    }

    /**Esborrar l'autor de l'arbre
     * @param s seqüencia de caràcters que forma l'autor.
     * @param i index de l'autor des d'on falta fer la cerca.
     * @param a autor a esborrar.
     */
    public void esborrarAutor(String s, int i, Frase a) {
        autors.remove(a);
        if (s.charAt(i) > lletra && dreta != null) dreta.esborrarAutor(s, i, a);
        if (s.charAt(i) < lletra && esquerra != null) esquerra.esborrarAutor(s, i, a);
        if (s.charAt(i) == lletra) {
            if (i < s.length() - 1 && centre != null) centre.esborrarAutor(s, i + 1, a);
            if (i == s.length() - 1) return;
        }
        return;
    }

}
