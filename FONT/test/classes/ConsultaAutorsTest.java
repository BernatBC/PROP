package test.classes;

import classes.ConsultaAutors;
import classes.Frase;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.*;
import org.junit.jupiter.api.DisplayName;

import java.util.HashSet;
import java.util.Set;


/** Test per la classe ConsultaAutors.
 * @author Oscar Ramos Nuñez (oscar.ramos.nunez@estudiantat.upc.edu)
 */
public class ConsultaAutorsTest {
    
    @Before                                         
    public void setUp() {
        System.out.println("SetUp Completed!");
    }


    @Test
    @DisplayName("Test Autors")   
    public void ConsutaAutorsPerPrefix() {
        // Init
        ConsultaAutors autors = new ConsultaAutors();

        Frase q1 = new Frase("Oscar Ramos");
        Frase q2 = new Frase("Salvador Roura");
        Frase q3 = new Frase("Manel Macarra");
        Frase q4 = new Frase("Norman Norman");
        Frase q5 = new Frase("Pol Ramos");
        Frase q6 = new Frase("Manel Ramos");
        Frase q7 = new Frase("Tomas Perez Castany Ferri i Normal");
        autors.addAutor(q1);
        autors.addAutor(q2);
        autors.addAutor(q3);
        autors.addAutor(q4);
        autors.addAutor(q5);
        autors.addAutor(q6);
        autors.addAutor(q7);





        //ASSERT
        Set<Frase> result = new HashSet<>();
        result.add(q6); //Manel Ramos
        result.add(q3); //Manel Macarra
        //assertEquals(result.toString(), autors.donaAutors("Man").toString());

        /** Veiem que és case sensitive, els noms propis i cognoms tenen sempre la primera lletra Majúscula.  */
        assertEquals("[]", autors.donaAutors("man").toString());


        /** Provem ara pel cognom Ramos, nomes agafant Ra. */
        result = new HashSet<>();
        result.add(q6); //Oscar Ramos
        result.add(q5); //Pol Ramos
        result.add(q1); //Manel Ramos
        //assertEquals(result.toString(), autors.donaAutors("Ra").toString());

        /** Cas només R, resultat igual que l'anterior cas però ara s'afegeix Salvador Roura. (R_oura) */
        result.add(q2); //Salvador Roura
        //assertEquals(result.toString(), autors.donaAutors("R").toString());

        /** Cas prefix null o cas prefix espai. */
        assertEquals("[]", autors.donaAutors("").toString());
        assertEquals("[]", autors.donaAutors(" ").toString());

        /** Mirem la q7, que conté una "i" i més de 2 cognoms. */
        result = new HashSet<>();
        result.add(q7); //Tomas Perez Castany Ferri i Normal. */
        assertEquals(result.toString(), autors.donaAutors("i").toString());
        assertEquals(result.toString(), autors.donaAutors("i No").toString());
        assertEquals(result.toString(), autors.donaAutors("Ferri i").toString());

        /** Comprovem quan el prefix es troba al nom d'una persona i al cognom d'una altre. */
        result = new HashSet<>();
        result.add(q7); //Tomas Perez Castany Ferri i Normal
        result.add(q4); //Norman Norman
        //assertEquals(result.toString(), autors.donaAutors("N").toString());
        //assertEquals(result.toString(), autors.donaAutors("Norm").toString());
    }
}
