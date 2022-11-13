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
        //Constructora de TernaryTree
        TernaryTree t = new TernaryTree();
        
        //Comprovem que no ens retorna la paraula.
        assertEquals(null, t.obtenirParaula("Paraula", 0));

        //Afegim la paraula i comprovem obtenirParaula 
        Paraula p1 = t.inserirObtenirParaula("Paraula", 0);
        assertEquals(p1, t.obtenirParaula("Paraula", 0));
        assertEquals(p1.getOcurrencia(), 1);

        //Esborrem la paraula, i no ens la retorna
        t.esborrarParaula("Paraula", 0, t, 1);
        assertEquals(null, t.obtenirParaula("Paraula", 0));

        //Insereix la paraula sense problemes després d'haverla esborrat.
        p1 = t.inserirObtenirParaula("Paraula", 0);
        assertEquals(p1, t.obtenirParaula("Paraula", 0));
        assertEquals(null, t.obtenirParaula("", 0));
        assertEquals(null, t.obtenirParaula("Par", 0));
        assertEquals(null, t.obtenirParaula("Paraula2", 0));
        assertEquals(null, t.obtenirParaula("Test", 0));

        //Comprovem que s'actualitza el nombre d'ocurrència de la paraula.
        assertEquals(p1, t.inserirObtenirParaula("Paraula", 0));
        assertEquals(p1.getOcurrencia(), 2);

        //Comprovem la funció decrementarOcurrencia
        assertEquals(1, t.decrementarOcurrencia("Paraula", 0));
        assertEquals(0, t.decrementarOcurrencia("Paraula", 0));
        assertEquals(0, t.decrementarOcurrencia("Paraula", 0));
        t.esborrarParaula("Paraula", 0, t, 1);
        assertEquals(-1, t.decrementarOcurrencia("Paraula", 0));
        assertEquals(-1, t.decrementarOcurrencia("", 0));
        assertEquals(-1, t.decrementarOcurrencia("Par", 0));
        assertEquals(-1, t.decrementarOcurrencia("Paraula2", 0));
        assertEquals(-1, t.decrementarOcurrencia("Test", 0));
    }

    @Test
    @DisplayName("Varies paraules")   
    public void ConsultaVariesParaules() {
        //Constructora de TernaryTree
        TernaryTree t = new TernaryTree();

        //Creem múltiples paraules
        Paraula p1 = t.inserirObtenirParaula("Paraula", 0);
        Paraula p2 = t.inserirObtenirParaula("Test", 0);
        Paraula p3 = t.inserirObtenirParaula("Joan", 0);
        Paraula p4 = t.inserirObtenirParaula("Document", 0);
        Paraula p5 = t.inserirObtenirParaula("Parabola", 0);

        //Comprovem que no podem inserir la paraula buida
        assertEquals(null, t.inserirObtenirParaula("", 0));

        //Inserim les 5 paraules
        assertEquals(p1, t.obtenirParaula("Paraula", 0));
        assertEquals(p2, t.obtenirParaula("Test", 0));
        assertEquals(p3, t.obtenirParaula("Joan", 0));
        assertEquals(p4, t.obtenirParaula("Document", 0));
        assertEquals(p5, t.obtenirParaula("Parabola", 0));

        //Comprovem que esborrar algunes paraules, la consulta retorna les paraules no esborrades correctament.
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

        //Podem tornar a afegir una paraula sense problemes.
        p1 = t.inserirObtenirParaula("Paraula", 0);
        assertEquals(p1, t.obtenirParaula("Paraula", 0));
        assertEquals(p2, t.obtenirParaula("Test", 0));
        assertEquals(null, t.obtenirParaula("Joan", 0));
        assertEquals(p4, t.obtenirParaula("Document", 0));
        assertEquals(p5, t.obtenirParaula("Parabola", 0));

        //Tornant a inserir la paraula ens actualitza ocurrencies d'aquella paraula
        assertEquals(p5, t.inserirObtenirParaula("Parabola", 0));
        assertEquals(p1.getOcurrencia(), 1);
        assertEquals(p2.getOcurrencia(), 1);
        assertEquals(p4.getOcurrencia(), 1);
        assertEquals(p5.getOcurrencia(), 2);

        //Decrementar el nombre d'ocurrencia només afecta a la paraula on hem aplicat la funció
        assertEquals(0, t.decrementarOcurrencia("Test", 0));
        assertEquals(p1.getOcurrencia(), 1);
        assertEquals(p2.getOcurrencia(), 0);
        assertEquals(p4.getOcurrencia(), 1);
        assertEquals(p5.getOcurrencia(), 2);
    }
}
