package test.classes;

import classes.Frase;
import classes.Paraula;


import static org.junit.jupiter.api.Assertions.assertEquals;

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


    
}
