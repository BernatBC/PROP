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
        // Init
        TernaryTreeAutor autors = new TernaryTreeAutor();

        Frase autor1 = new Frase("Aixo es un test");

        autors.inserirAutor(autor1, autor1.toString(), 0);

        Set<Frase> resultat_esperat = new HashSet<>();
        //ASSERT
        
        assertEquals(null, autors.obtenirAutors("Aixo no", 0));
        assertEquals(null, autors.obtenirAutors("Resultat buit", 0));
        assertEquals(null, autors.obtenirAutors("Aixo es un test ", 0));
        assertEquals(null, autors.obtenirAutors("Aixo es un test a", 0));

        resultat_esperat.add(autor1);
        assertEquals(resultat_esperat, autors.obtenirAutors("", 0));
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
    }
}
