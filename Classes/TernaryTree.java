import java.io.*;
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
    char lletra;

    /**Constructora per defecte de TernaryTree. */
    public TernaryTree() {
        esquerra = null;
        dreta = null;
        centre = null;
        paraula = null;
        lletra = null;
    }

    /**Constructora per defecte de TernaryTree.
     * @param c lletra que representa el node nou.
     */
    public TernaryTree(Char c) {
        esquerra = null;
        dreta = null;
        centre = null;
        paraula = null;
        lletra = c;
    }

    /** Insereix la paraula p al diccionari.
     * @param p paraula que es vol inserir.
     * @param s paraula que es vol inserir en format de string.
     * @param i índex de la paraula des d'on falta inserir.
     */
    public void inserirParaula(Paraula p, String s, int i) {
        if (lletra == null) lletra = s.charAt(i);
        if (s.charAt(i) == lletra) {
            if (i == s.length() - 1) {
                paraula = p;
                return;
            }
            if (centre == null) centre = new TernaryTree(s.charAt(i + 1));
            centre.inserirParaula(p, s, i + 1);
        }
        else if (s.charAt(i) > lletra) {
            if (dreta == null) dreta = new TernaryTree(s.charAt(i));
            dreta.inserirParaula(p, s, i);
        }
        else {
            if (esquerra == null) esquerra = new TernaryTree(s.charAt(i));
            esquerra.inserirParaula(p, s, i);
        }
    }

    /**Obtenir la classe Paraula que correspon a la seqüència s.
     * @param s seqüencia de caràcters que forma la paraula.
     * @param i index de la paraula des d'on falta fer la cerca.
     * @return Paraula : Classe paraula.
     */
    public Paraula obtenirParaula(String s, int i) {
        if (s.charAt(i) > lletra && dreta != null) return dreta.obtenirParaula(s, i);
        if (s.charAt(i) < lletra && esquerra != null) return esquerra.obtenirParaula(s, i);
        if (s.charAt(i) == lletra) {
            if (i < s.length() - 1 && centre != null) return centre.obtenirParaula(s, i + 1);
            if (i == s.length() - 1 && paraula != null) return paraula;
        }
        System.out.println("Paraula " + s + " no trobada al diccionari.");
        return null;
    }

    /**Obtenir la classe Paraula que correspon a la seqüència s, la crea si no existeix aquesta.
     * @param s seqüencia de caràcters que forma la paraula.
     * @param i index de la paraula des d'on falta fer la cerca.
     * @return Paraula : Classe paraula.
     */
    public Paraula inserirObtenirParaula(String s, int i) {
        if (lletra == null) lletra = s.charAt(i);
        if (s.charAt(i) == lletra) {
            if (i == s.length() - 1) {
                if (paraula == null) paraula = new Paraula(s);
                else paraula.incrementarOcurrencia(1);
                return paruala;
            }
            if (centre == null) centre = new TernaryTree(s.charAt(i + 1));
            return centre.inserirObtenirParaula(p, s, i + 1);
        }
        else if (s.charAt(i) > lletra) {
            if (dreta == null) dreta = new TernaryTree(s.charAt(i));
            return dreta.inserirObtenirParaula(p, s, i);
        }
        else {
            if (esquerra == null) esquerra = new TernaryTree(s.charAt(i));
            return esquerra.inserirObtenirParaula(p, s, i);
        }
    }

    /**Esborrar la classe Paraula que correspon a la seqüència s.
     * @param s seqüencia de caràcters que forma la paraula.
     * @param i index de la paraula des d'on falta fer la cerca.
     */
    public void esborrarParaula(String s, int i) {
        //TO DO: esborrar nodes innecessaris
        if (s.charAt(i) > lletra && dreta != null) dreta.esborrarParaula(s, i);
        if (s.charAt(i) < lletra && esquerra != null) esquerra.esborrarParaula(s, i);
        if (s.charAt(i) == lletra) {
            if (i < s.length() - 1 && centre != null) centre.esborrarParaula(s, i + 1);
            if (i == s.length() - 1 && paraula != null) paraula = null;
        }
    }

    public void decrementarOcurrencia(String s, int i) {
        //TO DO: esborrar nodes innecessaris
        if (s.charAt(i) > lletra && dreta != null) dreta.decrementarOcurrencia(s, i);
        if (s.charAt(i) < lletra && esquerra != null) esquerra.decrementarOcurrencia(s, i);
        if (s.charAt(i) == lletra) {
            if (i < s.length() - 1 && centre != null) centre.decrementarOcurrencia(s, i + 1);
            if (i == s.length() - 1 && paraula != null) {
                paraula.decrementarOcurrencia(1);
                if (paraula.getOcurrencia() <= 0) paraula = null;
            }
        }
    }

}
