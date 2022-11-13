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

        //Constructora de Vocabulari
        Vocabulari v = new Vocabulari();

        //No conté la paraula
        assertEquals(null, v.obtenirParaula("Paraula"));

        //Afegim la paraula i comprovem obtenirParaula 
        Paraula p1 = v.inserirObtenirParaula("Paraula");
        assertEquals(p1, v.obtenirParaula("Paraula"));
        assertEquals(p1.getOcurrencia(), 1);

        //Esborrem la paraula, i no ens la retorna
        v.esborrarParaula(p1);
        assertEquals(null, v.obtenirParaula("Paraula"));

        //Insereix la paraula sense problemes després d'haverla esborrat.
        p1 = v.inserirObtenirParaula("Paraula");
        assertEquals(p1, v.obtenirParaula("Paraula"));
        assertEquals(null, v.obtenirParaula(""));
        assertEquals(null, v.obtenirParaula("Par"));
        assertEquals(null, v.obtenirParaula("Test"));

        //Comprovem que s'actualitza el nombre d'ocurrència de la paraula.
        assertEquals(p1, v.inserirObtenirParaula("Paraula"));
        assertEquals(p1.getOcurrencia(), 2);

        //Comprovem que la funció decrementarOcurrencia ens esborra la paraula quan el nombre d'courrència arriba a 0.
        v.decrementarOcurrencia(p1);
        assertEquals(p1.getOcurrencia(), 1);
        v.decrementarOcurrencia(p1);
        assertEquals(p1.getOcurrencia(), 0);
        assertEquals(null, v.obtenirParaula("Paraula"));
    }

    @Test
    @DisplayName("Varies paraules")   
    public void ConsultaVariesParaules() {

        //Constructora de Vocabulari
        Vocabulari v = new Vocabulari();

        //Creem múltiples paraules
        Paraula p1 = v.inserirObtenirParaula("Paraula");
        Paraula p2 = v.inserirObtenirParaula("Test");
        Paraula p3 = v.inserirObtenirParaula("Joan");
        Paraula p4 = v.inserirObtenirParaula("Document");
        Paraula p5 = v.inserirObtenirParaula("Parabola");

        //Comprovem que no podem inserir la paraula buida
        assertEquals(null, v.inserirObtenirParaula(""));

        //Inserim les 5 paraules
        assertEquals(p1, v.obtenirParaula("Paraula"));
        assertEquals(p2, v.obtenirParaula("Test"));
        assertEquals(p3, v.obtenirParaula("Joan"));
        assertEquals(p4, v.obtenirParaula("Document"));
        assertEquals(p5, v.obtenirParaula("Parabola"));

        //Comprovem que esborrar algunes paraules, la consulta retorna les paraules no esborrades correctament.
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

        //Podem tornar a afegir una paraula sense problemes.
        p1 = v.inserirObtenirParaula("Paraula");
        assertEquals(p1, v.obtenirParaula("Paraula"));
        assertEquals(p2, v.obtenirParaula("Test"));
        assertEquals(null, v.obtenirParaula("Joan"));
        assertEquals(p4, v.obtenirParaula("Document"));
        assertEquals(p5, v.obtenirParaula("Parabola"));

        //Tornant a inserir la paraula ens actualitza ocurrencies d'aquella paraula
        assertEquals(p5, v.inserirObtenirParaula("Parabola"));
        assertEquals(p1.getOcurrencia(), 1);
        assertEquals(p2.getOcurrencia(), 1);
        assertEquals(p4.getOcurrencia(), 1);
        assertEquals(p5.getOcurrencia(), 2);

        //Decrementar el nombre d'ocurrencia només afecta a la paraula on hem aplicat la funció, i comprovem que ha esborrat la paraula Test al arribar a 0 ocurrències.
        v.decrementarOcurrencia(p2);
        assertEquals(p1.getOcurrencia(), 1);
        assertEquals(null, v.obtenirParaula("Test"));
        assertEquals(p4.getOcurrencia(), 1);
        assertEquals(p5.getOcurrencia(), 2);
    }
}
