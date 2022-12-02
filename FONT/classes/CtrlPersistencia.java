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
        if (getExtension(path).equals("txt")) importTXT(path);
    }

    /**
     * Retorna l'extensió d'un fitxer.
     * @param path path del document.
     * @return extensió del document.
     */
    private String getExtension(String path) {
        String extension = new String("");
        for (int i = path.length() - 1; i >= 0; --i) {
            char c = path.charAt(i);
            if (c == '.') break;
            extension = c + extension;
        }
        return extension;
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

    /**
     * Exporta un document.
     * @param title Títol a imprimir.
     * @param author Autor a imprimir.
     * @param content Contingut a imprimir.
     * @param path path del document.
     */
    public void export(String title, String author, ArrayList<String> content, String path) {
        if (getExtension(path).equals("txt")) exportTXT(title, author, content, path);
        else if (getExtension(path).equals("xml")) exportXML(title, author, content, path);
        else if (getExtension(path).equals("yay")) exportYAY(title, author, content, path);
    }

    /**
     * Exporta un document txt.
     * @param title Títol a imprimir.
     * @param author Autor a imprimir.
     * @param content Contingut a imprimir.
     * @param path path del document.
     */
    private void exportTXT(String title, String author, ArrayList<String> content, String path) {
        try {
            FileWriter f = new FileWriter(path);
            f.write(title + "\n");
            f.write(author + "\n");
            for (String s : content) f.write(s + "\n");
            f.close();
        }
        catch(Exception e) {
            System.out.println("Error while exporting a txt file.");
        }
    }

    /**
     * Exporta un document xml.
     * @param title Títol a imprimir.
     * @param author Autor a imprimir.
     * @param content Contingut a imprimir.
     * @param path path del document.
     */
    private void exportXML(String title, String author, ArrayList<String> content, String path) {
        try {
            FileWriter f = new FileWriter(path);
            f.write("<title>" + title + "</title>\n");
            f.write("<author>" + author + "</author>\n");
            f.write("<content>\n");
            for (String s : content) f.write(s + "\n");
            f.write("</content>\n");
            f.close();
        }
        catch(Exception e) {
            System.out.println("Error while exporting a txt file.");
        }
    }

    /**
     * Exporta un document yay (format propietari).
     * @param title Títol a imprimir.
     * @param author Autor a imprimir.
     * @param content Contingut a imprimir.
     * @param path path del document.
     */
    private void exportYAY(String title, String author, ArrayList<String> content, String path) {
        try {
            FileWriter f = new FileWriter(path);
            f.write("#Title:" + title + "\n");
            f.write("#Author:" + author + "\n");
            f.write("#Content:\n");
            for (String s : content) f.write(s + "\n");
            f.close();
        }
        catch(Exception e) {
            System.out.println("Error while exporting a txt file.");
        }
    }
}
