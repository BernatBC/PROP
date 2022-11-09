package test.classes;

import classes.TernaryTree;
import classes.Paraula;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.*;
import org.junit.jupiter.api.DisplayName;

/** Joc de proves per a la classe TernaryTree.
 * @author Bernat Borràs Civil (bernat.borras.civil@estudiantat.upc.edu)
 */
public class TernaryTreeTest {
    
    @Before                                         
    public void setUp() {
        System.out.println("SetUp Completed!");
    }


    @Test
    @DisplayName("Una paraula")   
    public void ConsultaUnaParaula() {
        TernaryTree t = new TernaryTree();

        assertEquals(null, t.obtenirParaula("Paraula", 0));
        Paraula p1 = t.inserirObtenirParaula("Paraula", 0);
        assertEquals(p1, t.obtenirParaula("Paraula", 0));
        assertEquals(p1.getOcurrencia(), 1);

        t.esborrarParaula("Paraula", 0, t, 1);
        assertEquals(null, t.obtenirParaula("Paraula", 0));

        p1 = t.inserirObtenirParaula("Paraula", 0);
        assertEquals(p1, t.obtenirParaula("Paraula", 0));
        assertEquals(null, t.obtenirParaula("", 0));
        assertEquals(null, t.obtenirParaula("Par", 0));
        assertEquals(null, t.obtenirParaula("Test", 0));

        assertEquals(p1, t.inserirObtenirParaula("Paraula", 0));
        assertEquals(p1.getOcurrencia(), 2);

        assertEquals(1, t.decrementarOcurrencia("Paraula", 0));
        assertEquals(0, t.decrementarOcurrencia("Paraula", 0));
        assertEquals(0, t.decrementarOcurrencia("Paraula", 0));
        t.esborrarParaula("Paraula", 0, t, 1);
        assertEquals(-1, t.decrementarOcurrencia("Paraula", 0));
    }

    @Test
    @DisplayName("Varies paraules")   
    public void ConsultaVariesParaules() {
        TernaryTree t = new TernaryTree();
        Paraula p1 = t.inserirObtenirParaula("Paraula", 0);
        Paraula p2 = t.inserirObtenirParaula("Test", 0);
        Paraula p3 = t.inserirObtenirParaula("Joan", 0);
        Paraula p4 = t.inserirObtenirParaula("Document", 0);
        Paraula p5 = t.inserirObtenirParaula("Parabola", 0);
        assertEquals(null, t.inserirObtenirParaula("", 0));

        assertEquals(p1, t.obtenirParaula("Paraula", 0));
        assertEquals(p2, t.obtenirParaula("Test", 0));
        assertEquals(p3, t.obtenirParaula("Joan", 0));
        assertEquals(p4, t.obtenirParaula("Document", 0));
        assertEquals(p5, t.obtenirParaula("Parabola", 0));

        t.esborrarParaula("Paraula", 0, t, 1);
        assertEquals(null, t.obtenirParaula("Paraula", 0));
        assertEquals(p2, t.obtenirParaula("Test", 0));
        assertEquals(p3, t.obtenirParaula("Joan", 0));
        assertEquals(p4, t.obtenirParaula("Document", 0));
        assertEquals(p5, t.obtenirParaula("Parabola", 0));

        t.esborrarParaula("Joan", 0, t, 1);
        assertEquals(null, t.obtenirParaula("Paraula", 0));
        assertEquals(p2, t.obtenirParaula("Test", 0));
        assertEquals(null, t.obtenirParaula("Joan", 0));
        assertEquals(p4, t.obtenirParaula("Document", 0));
        assertEquals(p5, t.obtenirParaula("Parabola", 0));

        assertEquals(p5, t.inserirObtenirParaula("Parabola", 0));
        assertEquals(p2.getOcurrencia(), 1);
        assertEquals(p4.getOcurrencia(), 1);
        assertEquals(p5.getOcurrencia(), 2);

        assertEquals(0, t.decrementarOcurrencia("Test", 0));

        assertEquals(p2.getOcurrencia(), 0);
        assertEquals(p4.getOcurrencia(), 1);
        assertEquals(p5.getOcurrencia(), 2);
    }
}
