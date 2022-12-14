package classes;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.DateTimeException;

/** Classe controladora de la capa de persistencia.
 * @author Bernat Borràs Civil (bernat.borras.civil@estudiantat.upc.edu)
 */
public class CtrlPersistencia {

    /**Controlador de domini. */
    CtrlDomini domini;

    /**Path del directori DATA. */
    final String PATH;

    /**
     * Constructora per defecte.
     * @param controladorDomini Controlador de domini.
     */
    public CtrlPersistencia(CtrlDomini controladorDomini) {
        domini = controladorDomini;
        PATH = new String("../DATA/");
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
            String plain_content = new String();
            while (s.hasNextLine()) {
                String line = s.nextLine();
                content.add(line);
                plain_content = plain_content + "\n" + line;
            }
            s.close();
            if(domini.getDocument(author, title).getR()) {
                System.out.println("A document with this title and author already exists!");
                return;
            }
            domini.crearDocument(title, author, content, plain_content, LocalDate.now(), false);
        }
        catch(Exception e) {
            System.out.println("File not found!");
        }
    }

    /**
     * Transforma una string a un arraylist delimitats per salts de línia.
     * @param s contingut a separar.
     * @return : ArrayList<String> contingut separat per salts de línia
     */
    private ArrayList<String> stringToArrayList(String s) {
        return new ArrayList<>(Arrays.asList(s.split("\n")));
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
            String plain_content = new String();
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
                if (tag.equals("document") || tag.equals("/document")) continue;

                //Llegir string contingut
                String contingut = new String("");
                while (file.charAt(i) != '<') {
                    contingut += file.charAt(i);
                    ++i;
                }

                while (file.charAt(i) != '>') ++i;
                
                if (tag.equals("title")) title = contingut;
                else if (tag.equals("author")) author = contingut;
                else if (tag.equals("content")) plain_content = new String(contingut);
                else if (tag.equals("date")) date = stringToDate(contingut);
                else if (tag.equals("bool name=\"favourite\"") && contingut == "true") favourite = true;
            }
            if(domini.getDocument(author, title).getR()) {
                System.out.println("A document with this title and author already exists!");
                return;
            }
            domini.crearDocument(title, author, stringToArrayList(plain_content), plain_content, date, favourite);
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
            String plain_content = new String();
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
                else if (tag.equals("CONTENT")) plain_content = new String(contingut);
                else if (tag.equals("DATE")) date = stringToDate(contingut);
                else if (tag.equals("FAVOURITE") && contingut == "True") favourite = true;
            }
            if(domini.getDocument(author, title).getR()) {
                System.out.println("A document with this title and author already exists!");
                return;
            }
            domini.crearDocument(title, author, stringToArrayList(plain_content), plain_content, date, favourite);
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
     * @param date Data a imprimir.
     * @param preferit Marcat com a preferit.
     * @param path path del document.
     */
    public void export(String title, String author, String content, LocalDate date, boolean preferit, String path) {
        Thread thread1 = new Thread();
        thread1.start();
        if (getExtension(path).equals("xml")) exportXML(title, author, content, date, preferit, path);
        else if (getExtension(path).equals("yay")) exportYAY(title, author, content, date, preferit, path);
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
            System.out.println("Error while exporting a txt file." + e);
        }
    }

    /**
     * Exporta un document xml.
     * @param title Títol a imprimir.
     * @param author Autor a imprimir.
     * @param content Contingut a imprimir.
     * @param date Data a imprimir.
     * @param preferit Marcat com a preferit.
     * @param path path del document.
     */
    private void exportXML(String title, String author, String content, LocalDate date, Boolean preferit, String path) {
        try {
            FileWriter f = new FileWriter(path);
            f.write("<document>\n");
            f.write("<title>" + title + "</title>\n");
            f.write("<author>" + author + "</author>\n");
            f.write("<date>" + date + "</date>\n");
            if (preferit) f.write("<bool name=\"favourite\">true</bool>\n");
            else f.write("<bool name=\"favourite\">false</bool>\n");
            f.write("<content>\n");
            f.write(content + "\n");
            f.write("</content>\n");
            f.write("</document>\n");
            f.close();
        }
        catch(Exception e) {
            System.out.println("Error while exporting a xml file.");
        }
    }

    /**
     * Exporta un document yay (format propietari).
     * @param title Títol a imprimir.
     * @param author Autor a imprimir.
     * @param content Contingut a imprimir.
     * @param date Data a imprimir.
     * @param preferit Marcat com a preferit.
     * @param path path del document.
     */
    private void exportYAY(String title, String author, String content, LocalDate date, Boolean preferit, String path) {
        try {
            FileWriter f = new FileWriter(path);
            f.write("#TITLE:" + title + "#\n");
            f.write("#AUTHOR:" + author + "#\n");
            f.write("#DATE:" + date + "#\n");
            if (preferit) f.write("#FAVOURITE:True#\n");
            else f.write("#FAVOURITE:False#\n");
            f.write("#CONTENT:\n");
            f.write(content + "\n");
            f.write("#\n");
            f.close();
        }
        catch(Exception e) {
            System.out.println("Error while exporting a yay file.");
        }
    }

    /**
     * Importa una expressió booleana a l'applicació.
     * @param f fitxer de l'expressió.
     */
    private void importarExpressio(File f) {
        try {
            Scanner s = new Scanner(f);
            String file = new String("");
            while (s.hasNextLine()) file += s.nextLine();
            s.close();
            
            String name = new String("");
            String expression = new String("");
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
                if (tag.equals("NAME")) name = contingut;
                else if (tag.equals("EXPRESSION")) expression = contingut;
            }
            domini.novaEB(name, expression);
        }
        catch(Exception e) {
            System.out.println("An error has ocurred while reading the boolean expression file");
        }
    }

    /**
     * Importa tots els documents i expressions booleanes desades a l'applicació.
     */
    public void importarDades() {
        File doc_folder = new File(PATH + "Documents/");
        File[] documents = doc_folder.listFiles();
        for (File d : documents) {
            importYAY(d);
        }

        File exp_folder = new File(PATH + "Expressions/");
        File[] expressions = exp_folder.listFiles();
        for (File e : expressions) {
            importarExpressio(e);
        }
    }
    /**
     * Retorna el path del document.
     * @param title Titol del document.
     * @param author Autor del document
     * @return String : Path del document.
     */
    private String getPathDoc(String title, String author) {
        return new String(PATH + "Documents/" + title + "_" + author + ".yay");
    }

    /**
     * Retorna el path de l'expressio booleana.
     * @param nom_expressio Nom de l'expressió boobleana.
     * @return String : Path de l'expressió.
     */
    private String getPathExp(String nom_expressio) {
        return new String(PATH + "Expressions/" + nom_expressio + ".yae");
    }

    /**
     * Crea un fitxer nou per al document creeat.
     * @param title Títol.
     * @param author Autor.
     * @param content Contingut.
     * @param date Data.
     * @param preferit Marcat com a preferit.
     */
    public void crearDocument(String title, String author, String content, LocalDate date, Boolean preferit) {
        exportYAY(title, author, content, date, preferit, getPathDoc(title, author));
    }

    /**
     * Esborra el document.
     * @param title Títol.
     * @param author Autor.
     */
    public void esborrarDocument(String title, String author) {
        File f = new File(getPathDoc(title, author));
        f.delete();
    }

    /**
     * Crea un fitxer nou per a l'expressió creada.
     * @param nom Nom de l'expressió boobleana.
     * @param expressio Expressió booleana
     */
    public void crearExpressio(String nom, String expressio) {
        try {
            FileWriter f = new FileWriter(getPathExp(expressio));
            f.write("#NAME:" + nom + "#\n");
            f.write("#EXPRESSION:" + expressio + "#\n");
            f.close();
        }
        catch(Exception e) {
            System.out.println("Error while exporting a yae file.");
        }
    }

    /**
     * Esborra el fitxer de l'expressió creada.
     * @param nom Nom de l'expressió boobleana.
     */
    public void esborrarExpressio(String nom) {
        File f = new File(getPathExp(nom));
        f.delete();
    }
}
