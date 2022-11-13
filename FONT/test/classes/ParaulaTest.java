package test.classes;
import classes.Paraula;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.*;
import org.junit.jupiter.api.DisplayName;

/** Joc de proves per a la classe Paraula.
 * @author Bernat Borràs Civil (bernat.borras.civil@estudiantat.upc.edu)
 */
public class ParaulaTest {
    
    @Before                                         
    public void setUp() {
        System.out.println("SetUp Completed!");
    }
    /*IMPORTANT

    Executar testos de manera individual.

    Els testos en aquesta classe pot donar incorrecte si s'executen tots a la vegada. Això és degut a que JUnit no executa els testos de manera independent. La classe paraula asigna un identificador començant pel 0, i executant primer els altres testos fa que l'index assignat sigui major al esperat.
     */
    @Test
    @DisplayName("Test 1 paraula")   
    public void UnaParaulaTest() {
        
        //Comprovem que la constructora inicialitza la paraula correctament.
        Paraula p1 = new Paraula("test");
        assertEquals("test", p1.getParaula());
        assertEquals(1, p1.getOcurrencia());
        assertEquals(0, p1.getId());

        //Comprovem la fucnió incrementarOcurrencia
        p1.incrementarOcurrencia();
        assertEquals("test", p1.getParaula());
        assertEquals(2, p1.getOcurrencia());
        assertEquals(0, p1.getId());

        //Decerementem l'ocurrència vàries vegades
        p1.decrementarOcurrencia();
        assertEquals("test", p1.getParaula());
        assertEquals(1, p1.getOcurrencia());
        assertEquals(0, p1.getId());

        //Tornem a incrementar el número d'ocurrencia.
        p1.incrementarOcurrencia();
        assertEquals("test", p1.getParaula());
        assertEquals(2, p1.getOcurrencia());
        assertEquals(0, p1.getId());

        p1.decrementarOcurrencia();
        assertEquals("test", p1.getParaula());
        assertEquals(1, p1.getOcurrencia());
        assertEquals(0, p1.getId());

        p1.decrementarOcurrencia();
        assertEquals("test", p1.getParaula());
        assertEquals(0, p1.getOcurrencia());
        assertEquals(0, p1.getId());

        //No hi poden haver una ocurrència negativa
        p1.decrementarOcurrencia();
        assertEquals("test", p1.getParaula());
        assertEquals(0, p1.getOcurrencia());
        assertEquals(0, p1.getId());

    }

    @Test
    @DisplayName("Test varies paraula")   
    public void VariesParaulaTest() {
        
        //Creem dues paraules i comprovem que l'índex assignat és l'esperat
        Paraula p1 = new Paraula("test");
        assertEquals("test", p1.getParaula());
        assertEquals(1, p1.getOcurrencia());
        assertEquals(0, p1.getId());

        Paraula p2 = new Paraula("projecte");
        assertEquals("test", p1.getParaula());
        assertEquals(1, p1.getOcurrencia());
        assertEquals(0, p1.getId());
        assertEquals("projecte", p2.getParaula());
        assertEquals(1, p2.getOcurrencia());
        assertEquals(1, p2.getId());

        //Incrementem i decrementem ocurrències per a veure que no afecten a les altres paraules.
        p2.incrementarOcurrencia();
        assertEquals("test", p1.getParaula());
        assertEquals(1, p1.getOcurrencia());
        assertEquals(0, p1.getId());
        assertEquals("projecte", p2.getParaula());
        assertEquals(2, p2.getOcurrencia());
        assertEquals(1, p2.getId());

        p2.decrementarOcurrencia();
        assertEquals("test", p1.getParaula());
        assertEquals(1, p1.getOcurrencia());
        assertEquals(0, p1.getId());
        assertEquals("projecte", p2.getParaula());
        assertEquals(1, p2.getOcurrencia());
        assertEquals(1, p2.getId());

    }
}