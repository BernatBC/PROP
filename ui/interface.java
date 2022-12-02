import java.awt.*;
import javax.swing.*;
import info.clearthought.layout.*;
import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Fri Dec 02 11:14:30 CET 2022
 */



/**
 * @author Oscar Ramos
 */
public class interface  {

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		// Generated using JFormDesigner Evaluation license - Oscar Ramos
		frame1 = new JFrame();
		menuBar1 = new JMenuBar();
		menu1 = new JMenu();
		menuItem4 = new JMenuItem();
		menuItem5 = new JMenuItem();
		menuItem14 = new JMenuItem();
		menuItem15 = new JMenuItem();
		menuItem16 = new JMenuItem();
		menuItem17 = new JMenuItem();
		menuItem18 = new JMenuItem();
		menu2 = new JMenu();
		menuItem6 = new JMenuItem();
		menuItem7 = new JMenuItem();
		menuItem8 = new JMenuItem();
		menuItem9 = new JMenuItem();
		menuItem10 = new JMenuItem();
		menuItem11 = new JMenuItem();
		menuItem12 = new JMenuItem();
		menuItem13 = new JMenuItem();
		menu3 = new JMenu();
		menuItem1 = new JMenuItem();
		menuItem2 = new JMenuItem();
		menuItem3 = new JMenuItem();
		tabbedPane1 = new JTabbedPane();
		panel1 = new JPanel();
		label4 = new JLabel();
		panel2 = new JPanel();
		label5 = new JLabel();
		panel3 = new JPanel();
		label6 = new JLabel();
		label1 = new JLabel();
		textField3 = new JTextField();
		hSpacer5 = new JPanel(null);
		checkBox1 = new JCheckBox();
		hSpacer2 = new JPanel(null);
		hSpacer1 = new JPanel(null);
		label2 = new JLabel();
		textField2 = new JTextField();
		hSpacer4 = new JPanel(null);
		label3 = new JLabel();
		textField4 = new JTextField();
		hSpacer3 = new JPanel(null);
		textField1 = new JTextField();

		//======== frame1 ========
		{
			Container frame1ContentPane = frame1.getContentPane();
			frame1ContentPane.setLayout(new MigLayout(
				"fill,insets 0,hidemode 3,gap 5 5",
				// columns
				"[fill]" +
				"[fill]" +
				"[fill]" +
				"[fill]" +
				"[fill]" +
				"[10:n:10,fill]",
				// rows
				"[50:n:50]" +
				"[50:n:50]" +
				"[]" +
				"[10:n:10]"));

			//======== menuBar1 ========
			{

				//======== menu1 ========
				{
					menu1.setText("Document");

					//---- menuItem4 ----
					menuItem4.setText("Importar Documents");
					menu1.add(menuItem4);

					//---- menuItem5 ----
					menuItem5.setText("Exportar Document");
					menu1.add(menuItem5);

					//---- menuItem14 ----
					menuItem14.setText("Crear Nou Document");
					menu1.add(menuItem14);

					//---- menuItem15 ----
					menuItem15.setText("Obrir Document");
					menu1.add(menuItem15);

					//---- menuItem16 ----
					menuItem16.setText("Tancar Document");
					menu1.add(menuItem16);

					//---- menuItem17 ----
					menuItem17.setText("Modificar Document");
					menu1.add(menuItem17);

					//---- menuItem18 ----
					menuItem18.setText("Eliminar Document");
					menu1.add(menuItem18);
				}
				menuBar1.add(menu1);

				//======== menu2 ========
				{
					menu2.setText("Consulta");

					//---- menuItem6 ----
					menuItem6.setText("Consulta T\u00edtols d'un Autor");
					menu2.add(menuItem6);

					//---- menuItem7 ----
					menuItem7.setText("Consulta Autors per Prefix");
					menu2.add(menuItem7);

					//---- menuItem8 ----
					menuItem8.setText("Consulta Document");
					menu2.add(menuItem8);

					//---- menuItem9 ----
					menuItem9.setText("Consulta per Semblan\u00e7a");
					menu2.add(menuItem9);

					//---- menuItem10 ----
					menuItem10.setText("Consulta per Rellevancia");
					menu2.add(menuItem10);

					//---- menuItem11 ----
					menuItem11.setText("Consulta Avan\u00e7ada");
					menu2.add(menuItem11);

					//---- menuItem12 ----
					menuItem12.setText("Consulta Preferits");
					menu2.add(menuItem12);

					//---- menuItem13 ----
					menuItem13.setText("Consulta per Data");
					menu2.add(menuItem13);
				}
				menuBar1.add(menu2);

				//======== menu3 ========
				{
					menu3.setText("Expressi\u00f3  Booleana");

					//---- menuItem1 ----
					menuItem1.setText("Alta Expressi\u00f3");
					menu3.add(menuItem1);

					//---- menuItem2 ----
					menuItem2.setText("Gesti\u00f3 Expressions Guardades");
					menu3.add(menuItem2);

					//---- menuItem3 ----
					menuItem3.setText("Consulta Expressi\u00f3");
					menu3.add(menuItem3);
				}
				menuBar1.add(menu3);
			}
			frame1.setJMenuBar(menuBar1);

			//======== tabbedPane1 ========
			{

				//======== panel1 ========
				{
					panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing
					.border.EmptyBorder(0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax.swing.border.TitledBorder
					.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("D\u0069alog",java.
					awt.Font.BOLD,12),java.awt.Color.red),panel1. getBorder()))
					;panel1. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e
					){if("\u0062order".equals(e.getPropertyName()))throw new RuntimeException();}})
					;

					//---- label4 ----
					label4.setText("Importar Documents");
					panel1.add(label4);
				}
				tabbedPane1.addTab("text", panel1);

				//======== panel2 ========
				{

					//---- label5 ----
					label5.setText("Exportar Document");
					panel2.add(label5);
				}
				tabbedPane1.addTab("text", panel2);

				//======== panel3 ========
				{

					//---- label6 ----
					label6.setText("Obrir Document");
					panel3.add(label6);
				}
				tabbedPane1.addTab("text", panel3);
			}
			frame1ContentPane.add(tabbedPane1, "cell 0 0 1 4,aligny top,growy 0");

			//---- label1 ----
			label1.setText("Autor");
			frame1ContentPane.add(label1, "cell 2 0,hmax 50");
			frame1ContentPane.add(textField3, "cell 2 0,hmax 50");
			frame1ContentPane.add(hSpacer5, "cell 3 0");

			//---- checkBox1 ----
			checkBox1.setText("Preferit");
			frame1ContentPane.add(checkBox1, "cell 4 0,hmax 50");
			frame1ContentPane.add(hSpacer2, "cell 5 0");
			frame1ContentPane.add(hSpacer1, "cell 1 1");

			//---- label2 ----
			label2.setText("T\u00edtol");
			frame1ContentPane.add(label2, "cell 2 1,hmax 50");
			frame1ContentPane.add(textField2, "cell 2 1,hmax 50");
			frame1ContentPane.add(hSpacer4, "cell 3 1");

			//---- label3 ----
			label3.setText("Data");
			frame1ContentPane.add(label3, "cell 4 1,hmax 50");
			frame1ContentPane.add(textField4, "cell 4 1,hmax 50");
			frame1ContentPane.add(hSpacer3, "cell 5 1");
			frame1ContentPane.add(textField1, "cell 2 2 3 1,grow,wmin 200,hmin 200");
			frame1.pack();
			frame1.setLocationRelativeTo(frame1.getOwner());
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	// Generated using JFormDesigner Evaluation license - Oscar Ramos
	private JFrame frame1;
	private JMenuBar menuBar1;
	private JMenu menu1;
	private JMenuItem menuItem4;
	private JMenuItem menuItem5;
	private JMenuItem menuItem14;
	private JMenuItem menuItem15;
	private JMenuItem menuItem16;
	private JMenuItem menuItem17;
	private JMenuItem menuItem18;
	private JMenu menu2;
	private JMenuItem menuItem6;
	private JMenuItem menuItem7;
	private JMenuItem menuItem8;
	private JMenuItem menuItem9;
	private JMenuItem menuItem10;
	private JMenuItem menuItem11;
	private JMenuItem menuItem12;
	private JMenuItem menuItem13;
	private JMenu menu3;
	private JMenuItem menuItem1;
	private JMenuItem menuItem2;
	private JMenuItem menuItem3;
	private JTabbedPane tabbedPane1;
	private JPanel panel1;
	private JLabel label4;
	private JPanel panel2;
	private JLabel label5;
	private JPanel panel3;
	private JLabel label6;
	private JLabel label1;
	private JTextField textField3;
	private JPanel hSpacer5;
	private JCheckBox checkBox1;
	private JPanel hSpacer2;
	private JPanel hSpacer1;
	private JLabel label2;
	private JTextField textField2;
	private JPanel hSpacer4;
	private JLabel label3;
	private JTextField textField4;
	private JPanel hSpacer3;
	private JTextField textField1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
