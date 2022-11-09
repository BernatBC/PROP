package classes;

import java.util.Set;

/** Prefixos.
 * @author Oscar Ramos Nuñez (oscar.ramos.nunez@estudiantat.upc.edu)
 */
public class ConsultaAutors {
    
    /** Arbre de cerca */
    private TernaryTreeAutor autors;


    public ConsultaAutors() {
        autors = new TernaryTreeAutor();
    }

    /** Insereix l'autor en l'arbre de cerca per prefix
     * @param autor
     */
    public void addAutor(Frase autor) {
        String nom_cognom = autor.toString();
       
        while (nom_cognom.length() > 0) {
            //inserim l'autor
            System.out.println("arribo");
            autors.inserirAutor(autor, nom_cognom, 0);
            
            //esborrem la primera paraula de l'estring
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

    /** Funcio consulta. Retorna els autors els quals compleixen el prefix
     * @param prefix
     */
    public Set<Frase> donaAutors(String prefix) {
        //retorna el Set d'autors que compleixen el prefix
        Set<Frase> result = autors.obtenirAutors(prefix, 0);
        return result;
    }
}
