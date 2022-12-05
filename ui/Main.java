import javax.swing.JFrame;


public class Main {

    public static void main(String[] args) {
    esquema ui = new esquema();
    ui.initComponents();
    JFrame frame = ui.retorna();
    frame.show();
    }
}
