import TernaryTree.java;

/** Diccionari de les paraules.
 * @author Bernat Borràs Civil (bernat.borras.civil@estudiantat.upc.edu)
 */
public class Vocabulari {

    /**Node arrel de l'arbre. */
    TernaryTree arrel;

    /**Constructora per defecte de Vocabulari. */
    public Vocabulari() {
        arrel = new TernaryTree();
    }

    /**Insereix una paraula al diccionari.
     * @param p paraula que es vol inserir.
     */
    public void inserirParaula(Paraula p) {
        String s = p.getParaula();
        if (s.length() > 0) arrel.inserirParaula(p, s, 0);
    }
}
