package classes;


/** Paraula que pot ser continguda a algun document.
 * @author Bernat Borràs Civil (bernat.borras.civil@estudiantat.upc.edu)
 */
public class Paraula {

    /** Mot repressentat per la classe. */
    private String mot;

    /** Número d'ocurrencies. */
    private int ocurrencia;

    /** Número de documents on la paraula apareix. */
    private int nDocuments;

    /** Identificador de la paraula. */
    private int index;

    private static int proxim_index = 0;

    /** Constructora per defecte de paraula.
     *  @param p Paraula representada per la classe.
    */
    public Paraula(String p) {
        mot = p;
        ocurrencia = 1;
        nDocuments = 0;
        index = proxim_index;
        ++proxim_index;
    }

    /** Retorna el número de documents on la paraula apareix.
    * @return Double : Número de documents.
    */
    public int getNumDocuments() {
        return nDocuments;
    }

    /** Retorna el nombre d'ocurrencies total.
     * @return Int : Nombre d'ocurrencies.
     */
    public int getOcurrencia() {
        return ocurrencia;
    }

    /** Retorna la paraula representada per la classe.
     * @return String : Paraula.
     */
    public String getParaula() {
        return mot;
    }

    /** Assigna el mot repressentat per la classe.
     * @param p Paraula representada per la classe
     */
    public void setParaula(String p) {
        mot = p;
        ocurrencia = 1;
    }

    /** Assigna el nombre d'ocurrencies.
     * @param n Nombre d'ocurrencies.
     */
    public void setOcurrencia(int n) {
        ocurrencia = n;
    }

    /** Decrementa en n unitats el nombre d'ocurrencies.
     * @param n Nombre d'ocurrencies.
     */
    public void decrementarOcurrencia(int n) {
        ocurrencia -= n;
        if (n < 0) {
            n = 0;
            System.out.println("ERROR: Ocurrencies negatives a la paraula " + mot);
        }
    }

    /** Incrementa en n unitats el nombre d'ocurrencies.
     * @param n Nombre d'ocurrencies.
     */
    public void incrementarOcurrencia(int n) {
        ocurrencia += n;
    }

    /** Decrementa en una unitat el número de documents on la paraula apareix. */
    public void decrementarNumDocuments() {
        --nDocuments;
    }

    /** Incrementa en una unitat el número de documents on la paraula apareix. */
    public void incrementarNumDocuments() {
        ++nDocuments;
    }


    /** Retorna l'índex de la paraula.
     * @return int : índex de la paraula.
     */
    public int getId() {
        return index;
    }
}