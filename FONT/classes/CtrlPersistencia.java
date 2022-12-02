package classes;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

/** Classe controladora de la capa de persistencia.
 * @author Bernat Borràs Civil (bernat.borras.civil@estudiantat.upc.edu)
 */
public class CtrlPersistencia {

    /**Controlador de domini */
    CtrlDomini domini;
    /**
     * Contructora per defecte
     * @param controlador controlador de domini.
     */
    public CtrlPersistencia(CtrlDomini controlador) {
        domini = controlador;
    }
    
    /**
     * Importa un fitxer a l'aplicació.
     * @param path path del document.
     */
    public void importFile(String path) {

        String extension = new String("");
        for (int i = path.length() - 1; i >= 0; --i) {
            char c = path.charAt(i);
            if (c == '.') break;
            extension = c + extension;
        }

        if (extension.equals("txt")) importTXT(path);
    }

    /**
     * Importa un fitxer TXT a l'aplicació.
     * @param path path del document.
     */
    private void importTXT(String path) {
        try {
            File f = new File(path);
            Scanner s = new Scanner(f);
            String title = s.nextLine();
            String author = s.nextLine();
            ArrayList<String> content = new ArrayList<>();
            while (s.hasNextLine()) content.add(s.nextLine());
            s.close();
            domini.crearDocument(title, author, content, LocalDate.now(), false);
        }
        catch(Exception e) {
            System.out.println("File not found!");
        }
    }
}
