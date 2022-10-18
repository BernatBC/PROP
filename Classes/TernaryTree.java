import Paraula.java;

/** Estructura de dades per contenir el diccionari de paraules.
 * @author Bernat Borràs Civil (bernat.borras.civil@estudiantat.upc.edu)
 */
public class TernaryTree {

    /**Node fill de l'esquerra. */
    TernaryTree esquerra;

    /**Node fill de la dreta. */
    TernaryTree dreta;

    /**Node fill del centre. */
    TernaryTree centre;

    /**Apuntador de la paraula que el node fa referència. */
    Paraula paraula;

    /** Lletra que conté el node. */
    Char lletra;

    /**Constructora per defecte de TernaryTree. */
    public TernaryTree() {
        esquerra = NULL;
        dreta = NULL;
        centre = NULL;
        paraula = NULL;
        lletra = NULL;
    }

    /**Constructora per defecte de TernaryTree.
     * @param c lletra que representa el node nou.
     */
    public TernaryTree(Char c) {
        esquerra = NULL;
        dreta = NULL;
        centre = NULL;
        paraula = NULL;
        lletra = c;
    }

    /** Insereix la paraula p al diccionari.
     * @param p paraula que es vol inserir.
     * @param s paraula que es vol inserir en format de string.
     * @param i índex de la paraula des d'on falta inserir.
     */
    public void inserirParaula(Paraula p, String s, Int i) {
        if (lletra == NULL) lletra = s.charAt(i);
        if (s.charAt(i) == lletra) {
            if (i == s.length() - 1) {
                paraula = p;
                return;
            }
            if (centre == NULL) centre = new TernaryTree(s.charAt(i + 1));
            centre.inserirParaula(p, s, i + 1);
        }
        else if (s.charAt(i) > lletra) {
            if (dreta == NULL) dreta = new TernaryTree(s.charAt(i));
            dreta.inserirParaula(p, s, i);
        }
        else {
            if (esquerra == NULL) esquerra = new TernaryTree(s.charAt(i));
            esquerra.inserirParaula(p, s, i);
        }
    }

}
