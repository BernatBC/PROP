package test.classes;

import classes.Frase;
import classes.Paraula;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

//import javax.naming.InitialContext;
//import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

import org.junit.*;
import org.junit.jupiter.api.DisplayName;

public class FraseTest {
    
    @Before                                         
    public void setUp() {
        System.out.println("SetUp Completed!");
    }

    @Test
    @DisplayName("Test frase conte paraula")   
    public void fraseConteParaula() {
        // Init
        Frase frase = new Frase("No vull fer testos a prop.");

        // Assert
        assertEquals(true, frase.conteParaula("no"));
    }



    @Test
    @DisplayName("Test frase donawords")   
    public void fraseDonaWords() {
        // Init
        ArrayList<Paraula> q = new ArrayList<>();
        q.add(new Paraula("hello"));
        q.add(new Paraula("world"));
        q.add(q.get(0));
        Frase frase2 = new Frase(q, "hello world hello");

        assertEquals(2, frase2.donaWords().get(0));

        assertEquals(1,frase2.donaWords().get(1));

        
    }


    @Test                                               
    @DisplayName("Test frase conte sequencia")   
    public void fraseConteSequencia() {
        // Init
        Frase frase = new Frase("No vull fer testos a prop.");
        String[] paraules = {"no", "vull", "fer"};
        // Assert
        assertEquals(true, frase.conteSequencia(paraules));
        String[] paraules2 = {"no", "vull", "fer", "testos", "a", "prop"};
        assertEquals(true, frase.conteSequencia(paraules2));
        String[] paraules3 = {"no", "no", "fer"};
        assertEquals(false, frase.conteSequencia(paraules3));
        String[] paraules4 = {"", "", ""};
        assertEquals(false, frase.conteSequencia(paraules4));
        String[] paraules5 = {""};
        assertEquals(false, frase.conteSequencia(paraules5));

    }


    @Test                                               
    @DisplayName("Test frase conte caracters")   
    public void fraseConteCaractersEnOrdre() {
        // Init
        Frase fraseC = new Frase("Vull buscar-ne lletres aqui.");
        String lletres = "-ne lletres";
        // Assert
        assertTrue(fraseC.conteCaracters(lletres));
        //ultima lletra.
        assertTrue(fraseC.conteCaracters("i"));
        //Mirem que la funcio conteCaracters diferencia correctament les majuscules i les minuscules.
        assertFalse(fraseC.conteCaracters("vull"));
        assertTrue(fraseC.conteCaracters("Vull"));
        //canviem nomes una lletra
        assertFalse(fraseC.conteCaracters("Vull buscar-ne lletra"));
        //Si passem un string sense caràcters sempre retornarà true ja que una subword de qualsevol paraula pot ser "".
        assertTrue(fraseC.conteCaracters(""));
        //Provem de buscar un espai en blanc.
        assertTrue(fraseC.conteCaracters(" "));


        
    }



    


    
}
