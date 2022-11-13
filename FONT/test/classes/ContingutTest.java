package test.classes;

import classes.Frase;
import classes.Paraula;
import classes.Contingut;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

//import javax.naming.InitialContext;
//import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

import org.junit.*;
import org.junit.jupiter.api.DisplayName;

/** Classe de proves d'un Contingut.
 * @author Alexandre Ros i Roger (alexandre.ros.roger@estudiantat.upc.edu)
 */
public class ContingutTest {
    
    @Before                                         
    public void setUp() {
        System.out.println("SetUp Completed!");
    }


    @Test
    @DisplayName("Test Contingut #1")   
    public void contingutTest() {
        // Init
        Paraula p1 = new Paraula("bruh");
        Paraula p2 = new Paraula("hola");
        Paraula p3 = new Paraula("pep");
        Paraula p4 = new Paraula("cotxe");
        Paraula p5 = new Paraula("FIB");
        Paraula p6 = new Paraula("prop");

        ArrayList<Paraula> a1 = new ArrayList<>();
        ArrayList<Paraula> a2 = new ArrayList<>();
        ArrayList<Paraula> a3 = new ArrayList<>();
        ArrayList<Paraula> a4 = new ArrayList<>();

        a1.add(p1); a1.add(p2); a1.add(p2);
        a2.add(p4);
        a3.add(p1); a3.add(p2); a3.add(p3); a3.add(p4); a3.add(p5); a3.add(p6); 

        Frase f1 = new Frase(a1, "bruh hola hola");
        Frase f2 = new Frase(a2, "cotxe");
        Frase f3 = new Frase(a3, "bruh hola pep cotxe FIB prop");
        Frase f4 = new Frase(a4, "");

        Frase[] phraseArray = {f1, f2, f3, f4};

        Contingut cont = new Contingut("bruh hola hola\ncotxe\nbruh hola pep cotxe FIB prop\n", phraseArray);

        assertEquals(0.2, cont.getTFofWord(0));
        assertEquals("{0=0.2, 1=0.3, 2=0.1, 3=0.2, 4=0.1, 5=0.1}", cont.getTF().toString());
        assertEquals("{0=2, 1=3, 2=1, 3=2, 4=1, 5=1}",cont.getWords().toString());
        assertEquals("bruh hola hola\ncotxe\nbruh hola pep cotxe FIB prop\n", cont.toString());

        // Now checking phrases
        assertEquals(f1, cont.getFrases()[0]);
        assertEquals(f3, cont.getFrases()[2]);

        // Conte Seqüència
        assertEquals(true, cont.conteSequencia("bru"));
        assertEquals(true, cont.conteSequencia("cotxe"));
        assertEquals(true, cont.conteSequencia("hola h"));
        assertEquals(false, cont.conteSequencia("hla"));
        assertEquals(true, cont.conteSequencia(" "));
        // La seqüència buida suposem sempre que no es conté enlloc i avisa d'això 
        assertEquals(false, cont.conteSequencia(""));
        // Les sequüéncies no són reconegudes si salten de línia sense indicar-ho
        assertEquals(false, cont.conteSequencia("hola cotxe"));
        assertEquals(true, cont.conteSequencia("hola\ncotxe"));
        assertEquals(true, cont.conteSequencia("B prop"));

    }
}
