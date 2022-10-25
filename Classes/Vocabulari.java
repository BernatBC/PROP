import java.io.*;
import TernaryTree.java;
import Paraula.java;

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
        if (s.length() > 0) arrel.inserirParaula(p, p.getParaula(), 0);
    }

    /**Obtenir la classe Paraula que correspon a la seqüència s.
     * @param s seqüencia de caràcters que forma la paraula.
     * @return Paraula : Classe paraula.
     */
    public Paraula obtenirParaula(String s) {
        if (s.length() > 0) return arrel.obtenirParaula(s, 0);
        System.out.println("Paraula " + s + " no trobada al diccionari.");
        return NULL;
    }

    /**Obtenir la classe Paraula que correspon a la seqüència s, la crea si no existeix aquesta.
     * @param p paraula a inserir/obtenir.
     * @return Paraula : Classe paraula.
     */
    public Paraula inserirObtenirParaula(Paraula p) {
        return arrel.inserirObtenirParaula(p, p.getParaula(), 0);
    }

    /**Esborra una paraula del diccionari.
     * @param p paraula que es vol esborrar.
     */
    public void esborrarParaula(Paraula p) {
        arrel.esborrarParaula(p.getParaula(), 0);
    }
}
