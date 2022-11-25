package classes;

import java.util.HashSet;
import java.util.Set;

/** Consulta utilitzada per obtenir els noms dels Autors que començen per un prefix (ja sigui el nom o un cognom).
 * @author Oscar Ramos Nuñez (oscar.ramos.nunez@estudiantat.upc.edu)
 */
public class ConsultaAutors {
    
    /** Arbre de cerca que emmagatzema els autors. */
    private TernaryTree<Frase> autors;

    /** COnstructora per defecte.
     * @return Consultora Autors empty
     */
    public ConsultaAutors() {
        autors = new TernaryTree<Frase>();
    }

    /** Insereix l'autor en l'arbre de cerca per prefix. Per cada autor insereix el nom i cada cognom (per tal de poder fer també la cerca per un prefix d'un cognom).
     * @param autor
     */
    public void addAutor(Frase autor) {
        String nom_cognom = autor.toString();

        while (nom_cognom.length() > 0) {
            //inserim l'autor
            autors.inserirObtenir(nom_cognom, 0, new Frase(nom_cognom));
            
            //esborrem la primera paraula de el String
            int ini = 0;
            int length = nom_cognom.length();
            while (ini < length && Character.isLetter(nom_cognom.charAt(ini))) ++ini;
            if (ini >= length) break;
            else {
                //vol dir que hem trobat un espai
                ++ini;
                nom_cognom = nom_cognom.substring(ini, length);
            }
        }
    }

    /** Funció consulta. Retorna els autors els quals compleixen el prefix.
     * @return Set de Frases on cada frase és un autor.
     * @param prefix
     */
    public Set<String> donaAutors(String prefix) {
        //retorna el Set d'autors que compleixen el prefix
        Set<Frase> frasesSet = autors.obtenirPerPrefix(prefix);
        Set<String> ret = new HashSet<String>();

        for (Frase f : frasesSet) ret.add(f.toString());

        return ret;
    }
}
