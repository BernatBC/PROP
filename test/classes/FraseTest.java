package test.classes;

import classes.Frase;


import static org.junit.jupiter.api.Assertions.assertEquals;



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
}
