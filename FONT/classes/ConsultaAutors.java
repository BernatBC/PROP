package classes;

import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;

/** Consulta utilitzada per obtenir els noms dels Autors que començen per un prefix (ja sigui el nom o un cognom).
 * @author Oscar Ramos Nuñez (oscar.ramos.nunez@estudiantat.upc.edu)
 */
public class ConsultaAutors {
    
    /** Arbre de cerca que emmagatzema els autors. */
    private TernaryTree<Set<Frase>> autors;

    /** COnstructora per defecte.
     * @return Consultora Autors empty
     */
    public ConsultaAutors() {
        autors = new TernaryTree<Set<Frase>>();
    }

    /** Insereix l'autor en l'arbre de cerca per prefix. Per cada autor insereix el nom i cada cognom (per tal de poder fer també la cerca per un prefix d'un cognom).
     * @param autor
     */
    public void addAutor(Frase autor) {
        String nom_cognom = autor.toString();
        
        while (nom_cognom.length() > 0) {
            //inserim l'autor
            
            Set<Frase> conjunt = autors.obtenir(nom_cognom, 0);
            if (conjunt == null) {
                Set<Frase> aux = new HashSet<Frase>();
                aux.add(autor);
                autors.inserirObtenir(nom_cognom, 0,aux);
            }
            else {
                conjunt.add(autor);
                //autors.inserirObtenir(nom_cognom, 0,conjunt);
            }
            
            
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
        Set<Set<Frase>> frasesSetSet = autors.obtenirPerPrefix(prefix);
        Set<String> ret = new HashSet<String>();
        for (Set<Frase> s: frasesSetSet ) {
            for (Frase f : s) ret.add(f.toString());
        }

        return ret;
    }

    public static Set<String> donaAutors(String prefix, TernaryTree<Pair<Frase, HashMap<Frase, Document>>> autor_documents) {
        Set<Pair<Frase, HashMap<Frase, Document>>> autors = autor_documents.obtenirPerPrefix(prefix);
        Set<String> set_autors = new HashSet<>();
        for (Pair<Frase, HashMap<Frase, Document>> autor : autors) set_autors.add(autor.getL().toString());
        return set_autors;
    }
}
