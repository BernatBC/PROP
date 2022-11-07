package classes;


/** Paraula que pot ser continguda a algun document.
 * @author Bernat Borràs Civil (bernat.borras.civil@estudiantat.upc.edu)
 */
public class Paraula {

    /** Mot repressentat per la classe. */
    private String mot;

    /** Número d'ocurrencies. */
    private int ocurrencia;

    /** Identificador de la paraula. */
    private int index;

    private static int proxim_index = 0;

    /** Constructora per defecte de paraula.
     *  @param p Paraula representada per la classe.
    */
    public Paraula(String p) {
        mot = p;
        ocurrencia = 1;
        index = proxim_index;
        ++proxim_index;
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

    /** Retorna l'índex de la paraula.
     * @return int : índex de la paraula.
     */
    public int getId() {
        return index;
    }
}