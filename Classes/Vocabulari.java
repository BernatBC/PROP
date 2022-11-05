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
        return null;
    }

    /**Obtenir la classe Paraula que correspon a la seqüència s, la crea si no existeix aquesta.
     * @param s paraula a inserir/obtenir.
     * @return Paraula : Classe paraula.
     */
    public Paraula inserirObtenirParaula(String s) {
        return arrel.inserirObtenirParaula(s, 0);
    }

    /**Esborra una paraula del diccionari.
     * @param p paraula que es vol esborrar.
     */
    public void esborrarParaula(Paraula p) {
        arrel.esborrarParaula(p.getParaula(), 0);
    }

    /**Decrementa en una unitat el nombre d'ocurrencies de la paraula p. En el cas que posteriorment el número sigui 0, s'esborra la paraula.
     * @param p paraula que es vol decrementar el número d'ocurrències.
     */
    public void decrementarOcurrencia(Paraula P) {
        arrel.decrementarOcurrencia(p.getParaula(), 0);
    }
}
