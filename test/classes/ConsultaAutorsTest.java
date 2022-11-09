package test.classes;

import classes.ConsultaAutors;
import classes.Frase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

//import javax.naming.InitialContext;
//import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

import org.junit.*;
import org.junit.jupiter.api.DisplayName;

/** Classe de proves d'un Contingut.
 * @author Alexandre Ros i Roger (alexandre.ros.roger@estudiantat.upc.edu)
 */
public class ConsultaAutorsTest {
    
    @Before                                         
    public void setUp() {
        System.out.println("SetUp Completed!");
    }


    @Test
    @DisplayName("Test Autors")   
    public void COnsutaAutorsPerPrefix() {
        // Init
        ConsultaAutors autors = new ConsultaAutors();

        autors.addAutor(new Frase("Oscar Ramos"));
        autors.addAutor(new Frase("Salvador Roura"));
        autors.addAutor(new Frase("Manel Macarra"));
        autors.addAutor(new Frase("Norman Norman"));
        autors.addAutor(new Frase("Pol Ramos"));
        autors.addAutor(new Frase("Manel Ramos"));




        //ASSERT
        System.out.println(autors.donaAutors("nor"));
    }
}
