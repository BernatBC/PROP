package classes;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.DateTimeException;

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
        if (getExtension(path).equals("xml")) importXML(path);
        else if (getExtension(path).equals("yay")) importYAY(path);
        else importTXT(path);
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
     * Importa un fitxer XML a l'aplicació.
     * @param path path del document.
     */
    private void importXML(String path) {
        try {
            File f = new File(path);
            Scanner s = new Scanner(f);
            String file = new String("");
            while (s.hasNextLine()) file += s.nextLine();
            s.close();

            String author = new String("");
            String title = new String("");
            LocalDate date = LocalDate.now();
            ArrayList<String> content = new ArrayList<>();
            Boolean favourite = false;
            int i = 0;
            while (i < file.length()) {
                if (file.charAt(i) != '<') {
                    ++i;
                    continue;
                }
                //Llegir tag
                String tag = new String("");
                ++i;
                while (file.charAt(i) != '>') {
                    tag += file.charAt(i);
                    ++i;
                }
                ++i;
                //Llegir string contingut
                String contingut = new String("");
                while (file.charAt(i) != '<') {
                    contingut += file.charAt(i);
                    ++i;
                }

                while (file.charAt(i) != '>') ++i;
                
                if (tag.equals("title")) title = contingut;
                else if (tag.equals("author")) author = contingut;
                else if (tag.equals("content")) content.add(contingut);
                else if (tag.equals("date")) date = stringToDate(contingut);
                else if (tag.equals("bool name=\"favourite\"") && contingut == "true") favourite = true;
            }
            domini.crearDocument(title, author, content, date, favourite);
        }
        catch(Exception e) {
            System.out.println("An error has ocurred while reading the xml file");
        }
    }

    private LocalDate stringToDate(String date) {
        int year = Integer.parseInt(date.substring(0, 3));
        int month = Integer.parseInt(date.substring(5, 6));
        int day = Integer.parseInt(date.substring(8, 9));
        try {
            return LocalDate.of(year, month, day);
        } catch (DateTimeException e){
            System.out.println("\nError when reading date");
        }
        return null;
    }

    /**
     * Importa un fitxer YAY a l'aplicació.
     * @param path path del document.
     */
    private void importYAY(String path) {
        try {
            File f = new File(path);
            Scanner s = new Scanner(f);
            String file = new String("");
            while (s.hasNextLine()) file += s.nextLine();
            s.close();
            
            String author = new String("");
            String title = new String("");
            ArrayList<String> content = new ArrayList<>();
            LocalDate date = LocalDate.now();
            Boolean favourite = false;
            int i = 0;
            while (i < file.length()) {
                System.out.println(file.charAt(i));
                if (file.charAt(i) != '#') {
                    ++i;
                    continue;
                }
                //Llegir tag
                String tag = new String("");
                ++i;
                while (file.charAt(i) != ':') {
                    tag += file.charAt(i);
                    ++i;
                }
                ++i;
                //Llegir string contingut
                String contingut = new String("");
                while (file.charAt(i) != '#') {
                    contingut += file.charAt(i);
                    ++i;
                }
                ++i;
                if (tag.equals("TITLE")) title = contingut;
                else if (tag.equals("AUTHOR")) author = contingut;
                else if (tag.equals("CONTENT")) content.add(contingut);
                else if (tag.equals("DATE")) date = stringToDate(contingut);
                else if (tag.equals("FAVOURITE") && contingut == "True") favourite = true;
            }
            System.out.println(title);
            System.out.println(author);
            System.out.println(content);
            domini.crearDocument(title, author, content, date, favourite);
        }
        catch(Exception e) {
            System.out.println("An error has ocurred while reading the yay file");
        }
    }

    /**
     * Exporta un document.
     * @param title Títol a imprimir.
     * @param author Autor a imprimir.
     * @param content Contingut a imprimir.
     * @param path path del document.
     */
    public void export(String title, String author, String content, String path) {
        if (getExtension(path).equals("xml")) exportXML(title, author, content, path);
        else if (getExtension(path).equals("yay")) exportYAY(title, author, content, path);
        else exportTXT(title, author, content, path);
    }

    /**
     * Exporta un document txt.
     * @param title Títol a imprimir.
     * @param author Autor a imprimir.
     * @param content Contingut a imprimir.
     * @param path path del document.
     */
    private void exportTXT(String title, String author, String content, String path) {
        try {
            FileWriter f = new FileWriter(path);
            f.write(title + "\n");
            f.write(author + "\n");
            f.write(content + "\n");
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
    private void exportXML(String title, String author, String content, String path) {
        try {
            FileWriter f = new FileWriter(path);
            f.write("<title>" + title + "</title>\n");
            f.write("<author>" + author + "</author>\n");
            f.write("<content>\n");
            f.write(content + "\n");
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
    private void exportYAY(String title, String author, String content, String path) {
        try {
            FileWriter f = new FileWriter(path);
            f.write("#TITLE:" + title + "#\n");
            f.write("#AUTHOR:" + author + "#\n");
            f.write("#CONTENT:\n");
            f.write(content + "\n");
            f.write("#\n");
            f.close();
        }
        catch(Exception e) {
            System.out.println("Error while exporting a txt file.");
        }
    }
}
