package test.classes;

import classes.ConsultaTitol;
import classes.Frase;
import classes.Contingut;
import classes.Document;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.*;
import org.junit.jupiter.api.DisplayName;

/** Joc de proves per a la classe ConsultaTitol.
 * @author Bernat Borràs Civil (bernat.borras.civil@estudiantat.upc.edu)
 */
public class ConsultaTitolTest {
    @Before                                         
    public void setUp() {
        System.out.println("SetUp Completed!");
    }

    @Test
    @DisplayName("Un sol autor")   
    public void ConsultaUnAutor() {
        // Init
        ConsultaTitol consulta = new ConsultaTitol();

        Frase test[] = new Frase[1];
        test[0] = new Frase("text inutil");
        Frase autor = new Frase("autor1");
        Contingut cont = new Contingut("text inutil", test);
        Document d1 = new Document(autor, new Frase("document1"), false, null, null, cont);
        Document d2 = new Document(autor, new Frase("document2"), false, null, null, cont);
        Document d3 = new Document(autor, new Frase("document3"), false, null, null, cont);
        Document d4 = new Document(autor, new Frase("document4"), false, null, null, cont);
        Document d5 = new Document(autor, new Frase("document5"), false, null, null, cont);

        Set<Document> esperat = new HashSet<>();

        assertEquals(null, consulta.getDocAutor(autor));

        consulta.afegirDocument(d1);
        esperat.add(d1);
        assertEquals(esperat, consulta.getDocAutor(autor));

        consulta.afegirDocument(d1);
        assertEquals(esperat, consulta.getDocAutor(autor));

        consulta.eliminarDocument(d1);
        esperat.remove(d1);
        assertEquals(null, consulta.getDocAutor(autor));

        consulta.eliminarDocument(d1);
        assertEquals(null, consulta.getDocAutor(autor));

        consulta.afegirDocument(d1);
        esperat.add(d1);
        assertEquals(esperat, consulta.getDocAutor(autor));

        consulta.afegirDocument(d2);
        esperat.add(d2);
        assertEquals(esperat, consulta.getDocAutor(autor));

        consulta.afegirDocument(d3);
        esperat.add(d3);
        assertEquals(esperat, consulta.getDocAutor(autor));

        consulta.afegirDocument(d5);
        esperat.add(d5);
        assertEquals(esperat, consulta.getDocAutor(autor));

        consulta.eliminarDocument(d3);
        esperat.remove(d3);
        assertEquals(esperat, consulta.getDocAutor(autor));

        consulta.eliminarDocument(d5);
        esperat.remove(d5);
        assertEquals(esperat, consulta.getDocAutor(autor));

        consulta.eliminarDocument(d2);
        esperat.remove(d2);
        assertEquals(esperat, consulta.getDocAutor(autor));

        consulta.afegirDocument(d4);
        esperat.add(d4);
        assertEquals(esperat, consulta.getDocAutor(autor));

        consulta.eliminarDocument(d1);
        esperat.remove(d1);
        assertEquals(esperat, consulta.getDocAutor(autor));

        consulta.eliminarDocument(d4);
        esperat.remove(d4);
        assertEquals(null, consulta.getDocAutor(autor));

    }

    @Test
    @DisplayName("Varis autors")   
    public void ConsultaVarisAutors() {
        // Init
        ConsultaTitol consulta = new ConsultaTitol();

        Frase test[] = new Frase[1];
        test[0] = new Frase("text inutil");
        Frase autor1 = new Frase("autor1");
        Frase autor2 = new Frase("autor2");
        Frase autor3 = new Frase("autor3");
        Contingut cont = new Contingut("text inutil", test);
        Document d1 = new Document(autor1, new Frase("document1"), false, null, null, cont);
        Document d2 = new Document(autor1, new Frase("document2"), false, null, null, cont);
        Document d3 = new Document(autor3, new Frase("document3"), false, null, null, cont);
        Document d4 = new Document(autor1, new Frase("document4"), false, null, null, cont);
        Document d5 = new Document(autor2, new Frase("document5"), false, null, null, cont);
        Document d6 = new Document(autor2, new Frase("document6"), false, null, null, cont);

        Set<Document> esperat_a1 = new HashSet<>();
        Set<Document> esperat_a2 = new HashSet<>();
        Set<Document> esperat_a3 = new HashSet<>();

        assertEquals(null, consulta.getDocAutor(autor1));
        assertEquals(null, consulta.getDocAutor(autor2));
        assertEquals(null, consulta.getDocAutor(autor3));

        consulta.afegirDocument(d1);
        esperat_a1.add(d1);
        assertEquals(esperat_a1, consulta.getDocAutor(autor1));
        assertEquals(null, consulta.getDocAutor(autor2));
        assertEquals(null, consulta.getDocAutor(autor3));

        consulta.afegirDocument(d3);
        esperat_a3.add(d3);
        assertEquals(esperat_a1, consulta.getDocAutor(autor1));
        assertEquals(null, consulta.getDocAutor(autor2));
        assertEquals(esperat_a3, consulta.getDocAutor(autor3));

        consulta.afegirDocument(d2);
        esperat_a1.add(d2);
        assertEquals(esperat_a1, consulta.getDocAutor(autor1));
        assertEquals(null, consulta.getDocAutor(autor2));
        assertEquals(esperat_a3, consulta.getDocAutor(autor3));

        consulta.afegirDocument(d4);
        esperat_a1.add(d4);
        assertEquals(esperat_a1, consulta.getDocAutor(autor1));
        assertEquals(null, consulta.getDocAutor(autor2));
        assertEquals(esperat_a3, consulta.getDocAutor(autor3));

        consulta.afegirDocument(d5);
        esperat_a2.add(d5);
        assertEquals(esperat_a1, consulta.getDocAutor(autor1));
        assertEquals(esperat_a2, consulta.getDocAutor(autor2));
        assertEquals(esperat_a3, consulta.getDocAutor(autor3));

        consulta.afegirDocument(d6);
        esperat_a2.add(d6);
        assertEquals(esperat_a1, consulta.getDocAutor(autor1));
        assertEquals(esperat_a2, consulta.getDocAutor(autor2));
        assertEquals(esperat_a3, consulta.getDocAutor(autor3));
        
        consulta.eliminarDocument(d5);
        esperat_a2.remove(d5);
        assertEquals(esperat_a1, consulta.getDocAutor(autor1));
        assertEquals(esperat_a2, consulta.getDocAutor(autor2));
        assertEquals(esperat_a3, consulta.getDocAutor(autor3));

        consulta.eliminarDocument(d5);
        assertEquals(esperat_a1, consulta.getDocAutor(autor1));
        assertEquals(esperat_a2, consulta.getDocAutor(autor2));
        assertEquals(esperat_a3, consulta.getDocAutor(autor3));

        consulta.afegirDocument(d5);
        esperat_a2.add(d5);
        assertEquals(esperat_a1, consulta.getDocAutor(autor1));
        assertEquals(esperat_a2, consulta.getDocAutor(autor2));
        assertEquals(esperat_a3, consulta.getDocAutor(autor3));

        consulta.afegirDocument(d5);
        assertEquals(esperat_a1, consulta.getDocAutor(autor1));
        assertEquals(esperat_a2, consulta.getDocAutor(autor2));
        assertEquals(esperat_a3, consulta.getDocAutor(autor3));

    }
}
