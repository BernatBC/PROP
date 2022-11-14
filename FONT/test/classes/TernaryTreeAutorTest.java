package test.classes;

import classes.TernaryTreeAutor;
import classes.Frase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.*;
import org.junit.jupiter.api.DisplayName;

/** Joc de proves per a la classe TernaryTreeAutor.
 * @author Bernat Borràs Civil (bernat.borras.civil@estudiantat.upc.edu)
 */
public class TernaryTreeAutorTest {
    
    @Before                                         
    public void setUp() {
        System.out.println("SetUp Completed!");
    }


    @Test
    @DisplayName("Un autor")   
    public void ConsultaUnAutor() {
        //Constructora de TernaryTreeAutor
        TernaryTreeAutor autors = new TernaryTreeAutor();

        //Crrem un autor
        Frase autor1 = new Frase("Aixo es un test");
        autors.inserirAutor(autor1, autor1.toString(), 0);

        Set<Frase> resultat_esperat = new HashSet<>();

        //No ens retorna cap autor
        assertEquals(null, autors.obtenirAutors("", 0));
        assertEquals(null, autors.obtenirAutors("Aixo no", 0));
        assertEquals(null, autors.obtenirAutors("Resultat buit", 0));
        assertEquals(null, autors.obtenirAutors("Aixo es un test ", 0));
        assertEquals(null, autors.obtenirAutors("Aixo es un test a", 0));

        //Comprovem que al afegir un autor, tots els prefixos d'aquest conté l'autor
        resultat_esperat.add(autor1);
        assertEquals(resultat_esperat, autors.obtenirAutors("A", 0));
        assertEquals(resultat_esperat, autors.obtenirAutors("Ai", 0));
        assertEquals(resultat_esperat, autors.obtenirAutors("Aix", 0));
        assertEquals(resultat_esperat, autors.obtenirAutors("Aixo", 0));
        assertEquals(resultat_esperat, autors.obtenirAutors("Aixo ", 0));
        assertEquals(resultat_esperat, autors.obtenirAutors("Aixo e", 0));
        assertEquals(resultat_esperat, autors.obtenirAutors("Aixo es", 0));
        assertEquals(resultat_esperat, autors.obtenirAutors("Aixo es ", 0));
        assertEquals(resultat_esperat, autors.obtenirAutors("Aixo es u", 0));
        assertEquals(resultat_esperat, autors.obtenirAutors("Aixo es un", 0));
        assertEquals(resultat_esperat, autors.obtenirAutors("Aixo es un ", 0));
        assertEquals(resultat_esperat, autors.obtenirAutors("Aixo es un t", 0));
        assertEquals(resultat_esperat, autors.obtenirAutors("Aixo es un te", 0));
        assertEquals(resultat_esperat, autors.obtenirAutors("Aixo es un tes", 0));
        assertEquals(resultat_esperat, autors.obtenirAutors("Aixo es un test", 0));

        //L'autor si el tornes a inserir et seguirà donant una sola instància
        autors.inserirAutor(autor1, autor1.toString(), 0);
        assertEquals(resultat_esperat, autors.obtenirAutors("Aixo es un test", 0));

        //Comrovem que un prefix buit, i prefixos innexistents no ens retorna cap autor.
        assertEquals(null, autors.obtenirAutors("", 0));
        assertEquals(null, autors.obtenirAutors("Resultat buit", 0));
        assertEquals(null, autors.obtenirAutors("Aixo es un test a", 0));
    }

    @Test
    @DisplayName("Varis autor")   
    public void ConsultaVarisAutors() {
        //Constructora de TernaryTree
        TernaryTreeAutor autors = new TernaryTreeAutor();

        //Creem autors diferents i els inserim
        Frase autor1 = new Frase("William Shakespeare");
        Frase autor2 = new Frase("Leo Tolstoy");
        Frase autor3 = new Frase("Leon Uris");
        Frase autor4 = new Frase("Jules Verne");
        Frase autor5 = new Frase("");
        Frase autor6 = new Frase("Jul");

        autors.inserirAutor(autor1, autor1.toString(), 0);
        autors.inserirAutor(autor2, autor2.toString(), 0);
        autors.inserirAutor(autor3, autor3.toString(), 0);
        autors.inserirAutor(autor4, autor4.toString(), 0);
        autors.inserirAutor(autor5, autor5.toString(), 0);
        autors.inserirAutor(autor6, autor6.toString(), 0);

        //L'autor buit no l'insereix
        assertEquals(null, autors.obtenirAutors("", 0));

        Set<Frase> resultat_esperat = new HashSet<>();
        resultat_esperat.add(autor2);
        resultat_esperat.add(autor3);

        //Retorna Leo Tolstoy i Leon Uris
        assertEquals(resultat_esperat, autors.obtenirAutors("L", 0));
        assertEquals(resultat_esperat, autors.obtenirAutors("Le", 0));
        assertEquals(resultat_esperat, autors.obtenirAutors("Leo", 0));

        //Retorna Leo Tolstoy
        resultat_esperat.remove(autor3);
        assertEquals(resultat_esperat, autors.obtenirAutors("Leo ", 0));
        assertEquals(resultat_esperat, autors.obtenirAutors("Leo Tol", 0));
        assertEquals(resultat_esperat, autors.obtenirAutors("Leo Tolstoy", 0));

        //Retorna Leon Uris
        resultat_esperat.add(autor3);
        resultat_esperat.remove(autor2);
        assertEquals(resultat_esperat, autors.obtenirAutors("Leon", 0));
        assertEquals(resultat_esperat, autors.obtenirAutors("Leon ", 0));
        assertEquals(resultat_esperat, autors.obtenirAutors("Leon Uris", 0));

        //Retorna Jules Verne
        resultat_esperat.remove(autor3);
        resultat_esperat.add(autor4);
        assertEquals(resultat_esperat, autors.obtenirAutors("Jules", 0));
        assertEquals(resultat_esperat, autors.obtenirAutors("Jules V", 0));
        assertEquals(resultat_esperat, autors.obtenirAutors("Jules Verne", 0));

        //Retorna Jules Verne i Jul
        resultat_esperat.add(autor6);
        assertEquals(resultat_esperat, autors.obtenirAutors("J", 0));
        assertEquals(resultat_esperat, autors.obtenirAutors("Jul", 0));

        //Retorna William Shakespeare
        resultat_esperat.remove(autor4);
        resultat_esperat.remove(autor6);
        resultat_esperat.add(autor1);
        assertEquals(resultat_esperat, autors.obtenirAutors("Will", 0));
        assertEquals(resultat_esperat, autors.obtenirAutors("William Shak", 0));

        //No retorna cap autor
        resultat_esperat.remove(autor1);
        assertEquals(null, autors.obtenirAutors("Lewis", 0));
        assertEquals(null, autors.obtenirAutors("Wilbur Smith", 0));
        assertEquals(null, autors.obtenirAutors("Jules Verne ", 0));
        assertEquals(null, autors.obtenirAutors("Anne", 0));
        assertEquals(null, autors.obtenirAutors("", 0));
    }
}
