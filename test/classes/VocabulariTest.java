package test.classes;

import classes.Vocabulari;
import classes.Paraula;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.*;
import org.junit.jupiter.api.DisplayName;

/** Joc de proves per a la classe Vocabulari.
 * @author Bernat Borràs Civil (bernav.borras.civil@estudiantav.upc.edu)
 */
public class VocabulariTest {
    
    @Before                                         
    public void setUp() {
        System.out.println("SetUp Completed!");
    }


    @Test
    @DisplayName("Una paraula")   
    public void ConsultaUnaParaula() {
        Vocabulari v = new Vocabulari();

        assertEquals(null, v.obtenirParaula("Paraula"));
        Paraula p1 = v.inserirObtenirParaula("Paraula");
        assertEquals(p1, v.obtenirParaula("Paraula"));
        assertEquals(p1.getOcurrencia(), 1);

        v.esborrarParaula(p1);
        assertEquals(null, v.obtenirParaula("Paraula"));

        p1 = v.inserirObtenirParaula("Paraula");
        assertEquals(p1, v.obtenirParaula("Paraula"));
        assertEquals(null, v.obtenirParaula(""));
        assertEquals(null, v.obtenirParaula("Par"));
        assertEquals(null, v.obtenirParaula("Test"));

        assertEquals(p1, v.inserirObtenirParaula("Paraula"));
        assertEquals(p1.getOcurrencia(), 2);

        v.decrementarOcurrencia(p1);
        assertEquals(p1.getOcurrencia(), 1);
        v.decrementarOcurrencia(p1);
        assertEquals(p1.getOcurrencia(), 0);
        assertEquals(null, v.obtenirParaula("Paraula"));
    }

    @Test
    @DisplayName("Varies paraules")   
    public void ConsultaVariesParaules() {
        Vocabulari v = new Vocabulari();
        Paraula p1 = v.inserirObtenirParaula("Paraula");
        Paraula p2 = v.inserirObtenirParaula("Test");
        Paraula p3 = v.inserirObtenirParaula("Joan");
        Paraula p4 = v.inserirObtenirParaula("Document");
        Paraula p5 = v.inserirObtenirParaula("Parabola");
        assertEquals(null, v.inserirObtenirParaula(""));

        assertEquals(p1, v.obtenirParaula("Paraula"));
        assertEquals(p2, v.obtenirParaula("Test"));
        assertEquals(p3, v.obtenirParaula("Joan"));
        assertEquals(p4, v.obtenirParaula("Document"));
        assertEquals(p5, v.obtenirParaula("Parabola"));

        v.esborrarParaula(p1);
        assertEquals(null, v.obtenirParaula("Paraula"));
        assertEquals(p2, v.obtenirParaula("Test"));
        assertEquals(p3, v.obtenirParaula("Joan"));
        assertEquals(p4, v.obtenirParaula("Document"));
        assertEquals(p5, v.obtenirParaula("Parabola"));

        v.esborrarParaula(p3);
        assertEquals(null, v.obtenirParaula("Paraula"));
        assertEquals(p2, v.obtenirParaula("Test"));
        assertEquals(null, v.obtenirParaula("Joan"));
        assertEquals(p4, v.obtenirParaula("Document"));
        assertEquals(p5, v.obtenirParaula("Parabola"));

        assertEquals(p5, v.inserirObtenirParaula("Parabola"));
        assertEquals(p2.getOcurrencia(), 1);
        assertEquals(p4.getOcurrencia(), 1);
        assertEquals(p5.getOcurrencia(), 2);

        v.decrementarOcurrencia(p2);
        assertEquals(null, v.obtenirParaula("Test"));
        assertEquals(p4.getOcurrencia(), 1);
        assertEquals(p5.getOcurrencia(), 2);
    }
}
