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

    /**Path on es desen els documents entre sessions */
    final String PATH;

    /**
     * Contructora per defecte
     * @param controlador controlador de domini.
     */
    public CtrlPersistencia(CtrlDomini controlador) {
        domini = controlador;
        PATH = new String("../DATA/Documents/");
    }
    
    /**
     * Importa un fitxer a l'aplicació.
     * @param path path del document.
     */
    public void importFile(String path) {
        File f = new File(path);
        if (getExtension(path).equals("xml")) importXML(f);
        else if (getExtension(path).equals("yay")) importYAY(f);
        else importTXT(f);
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
     * @param f document.
     */
    private void importTXT(File f) {
        try {
            Scanner s = new Scanner(f);
            String title = s.nextLine();
            String author = s.nextLine();
            ArrayList<String> content = new ArrayList<>();
            while (s.hasNextLine()) content.add(s.nextLine());
            s.close();
            if(domini.getDocument(author, title).getR()) {
                System.out.println("A document with this title and author already exists!");
                return;
            }
            domini.crearDocument(title, author, content, LocalDate.now(), false);
        }
        catch(Exception e) {
            System.out.println("File not found!");
        }
    }

    /**
     * Importa un fitxer XML a l'aplicació.
     * @param f document.
     */
    private void importXML(File f) {
        try {
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
            if(domini.getDocument(author, title).getR()) {
                System.out.println("A document with this title and author already exists!");
                return;
            }
            domini.crearDocument(title, author, content, date, favourite);
        }
        catch(Exception e) {
            System.out.println("An error has ocurred while reading the xml file");
        }
    }

    private LocalDate stringToDate(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8, 10));
        try {
            return LocalDate.of(year, month, day);
        } catch (DateTimeException e){
            System.out.println("\nError when reading date");
        }
        return null;
    }

    /**
     * Importa un fitxer YAY a l'aplicació.
     * @param f document.
     */
    private void importYAY(File f) {
        try {
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
            if(domini.getDocument(author, title).getR()) {
                System.out.println("A document with this title and author already exists!");
                return;
            }
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
    public void export(String title, String author, String content, String data, Boolean preferit, String path) {
        if (getExtension(path).equals("xml")) exportXML(title, author, content, data, preferit, path);
        else if (getExtension(path).equals("yay")) exportYAY(title, author, content, data, preferit, path);
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
    private void exportXML(String title, String author, String content, String data, Boolean preferit, String path) {
        try {
            FileWriter f = new FileWriter(path);
            f.write("<title>" + title + "</title>\n");
            f.write("<author>" + author + "</author>\n");
            f.write("<date>" + data + "<date>");
            if (preferit) f.write("<bool name=\"favourite\">true</bool>");
            else f.write("<bool name=\"favourite\">true</bool>");
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
    private void exportYAY(String title, String author, String content, String data, Boolean preferit, String path) {
        try {
            FileWriter f = new FileWriter(path);
            f.write("#TITLE:" + title + "#\n");
            f.write("#AUTHOR:" + author + "#\n");
            f.write("#DATE:" + data + "#\n");
            if (preferit) f.write("#FAVOURITE:True#\n");
            else f.write("#FAVOURITE:False#\n");
            f.write("#CONTENT:\n");
            f.write(content + "\n");
            f.write("#\n");
            f.close();
        }
        catch(Exception e) {
            System.out.println("Error while exporting a txt file.");
        }
    }

    /**
     * Importa tots els documents i expressions booleanes desades a l'applicació.
     */
    public void importarDades() {
        File data = new File(PATH);
        File[] documents = data.listFiles();
        for (File d : documents) {
            importYAY(d);
        }
    }
}
