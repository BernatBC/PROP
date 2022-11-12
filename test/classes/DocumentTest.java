package test.classes;

import classes.Frase;
import classes.Paraula;
import classes.Contingut;
import classes.Document;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

//import javax.naming.InitialContext;
//import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

import org.junit.*;
import org.junit.jupiter.api.DisplayName;

/** Classe de proves d'un Document.
 * @author Alexandre Ros i Roger (alexandre.ros.roger@estudiantat.upc.edu)
 */
public class DocumentTest {
    
    Paraula p1, p2, p3, p4, p5, p6;
    Frase f1, f2, f3, f4, autor, titol;
    Contingut cont;
    Document doc;

    @Before                                        
    public void setUp() {
        p1 = new Paraula("bruh");
        p2 = new Paraula("hola");
        p3 = new Paraula("pep");
        p4 = new Paraula("cotxe");
        p5 = new Paraula("FIB");
        p6 = new Paraula("prop");

        ArrayList<Paraula> a1 = new ArrayList<>();
        ArrayList<Paraula> a2 = new ArrayList<>();
        ArrayList<Paraula> a3 = new ArrayList<>();
        ArrayList<Paraula> a4 = new ArrayList<>();

        a1.add(p1); a1.add(p2); a1.add(p2);
        a2.add(p4);
        a3.add(p1); a3.add(p2); a3.add(p3); a3.add(p4); a3.add(p5); a3.add(p6); 

        f1 = new Frase(a1, "bruh hola hola");
        f2 = new Frase(a2, "cotxe");
        f3 = new Frase(a3, "bruh hola pep cotxe FIB prop");
        f4 = new Frase(a4, "");

        autor = new Frase("Alexandre Ros Roger");
        titol = new Frase("Títol del Document");

        Frase[] phraseArray = {f1, f2, f3, f4};

        cont = new Contingut("bruh hola hola\ncotxe\nbruh hola pep cotxe FIB prop\n", phraseArray);

        doc = new Document(autor, titol, false, "/home/link/Desktop", LocalDate.now(), cont);

        System.out.println("SetUp Completed!");
    }


    @Test
    @DisplayName("Test Getters i Setters")   
    public void contingutTest() {
        // Init
        assertEquals(false, doc.getFavourite());

        doc.setFavourite(true);

        assertEquals(true, doc.getFavourite());

        assertEquals(cont, doc.getContingut());
        assertEquals(autor, doc.getAutor());
        assertEquals(titol, doc.getTitol());

        doc.setData(LocalDate.of(2002, 11, 22));
        assertEquals(true, doc.getData().isBefore(LocalDate.now()));
    }

    @Test 
    @DisplayName("Test Term Frequency and conté Seqüència")
    public void testTF(){
        
        assertEquals(3.0 / 10.0, doc.getTFofWord(p2.getId()));
        assertEquals(1.0 / 10.0, doc.getTFofWord(p5.getId()));

        assertEquals(true, doc.conteSequencia("bru"));
        assertEquals(true, doc.conteSequencia("bruh"));
        // Case sensitive
        assertEquals(false, doc.conteSequencia("fib"));
        assertEquals(false, doc.conteSequencia("cotxe bruh"));
        assertEquals(true, doc.conteSequencia("cotxe\nbruh"));
        // Està en el autor
        assertEquals(true, doc.conteSequencia("A"));
        // Està en el títol
        assertEquals(true, doc.conteSequencia("del Doc"));
    }
    
}
