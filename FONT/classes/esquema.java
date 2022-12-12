package classes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.util.ArrayList;

import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Mon Dec 05 12:19:09 CET 2022
 */



/**
 * @author Oscar Ramos
 */
public class esquema  {

	CtrlPresentacio CP;

	public void updateJList(JList list, ArrayList<String> myArray){
		DefaultListModel model = new DefaultListModel();

		for (String s : myArray) model.addElement(s);
		list.setModel(model);
	}

	public JFrame retorna() {
		return frame1;
	}

	public void sendCP(CtrlPresentacio cp){
		CP = cp;

		textField2.setEditable(false);
		textField3.setEditable(false);
	}


	private void menuItem4MouseReleased(MouseEvent e) {
		// TODO add your code here
		tabbedPane1.setSelectedIndex(0);
	}

	private void menuItem5MouseReleased(MouseEvent e) {
		// TODO add your code here
		tabbedPane1.setSelectedIndex(1);
	}

	private void menuItem14MouseReleased(MouseEvent e) {
		// TODO add your code here
		tabbedPane1.setSelectedIndex(2);
	}

	private void menuItem15MouseReleased(MouseEvent e) {
		// TODO add your code here
		tabbedPane1.setSelectedIndex(3);
	}

	private void menuItem16MouseReleased(MouseEvent e) {
		// TODO add your code here
		//TANCAR DOCUMENT
	}

	private void menuItem18MouseReleased(MouseEvent e) {
		// TODO add your code here
		tabbedPane1.setSelectedIndex(4);
	}

	private void menuItem6MouseReleased(MouseEvent e) {
		// TODO add your code here
		tabbedPane1.setSelectedIndex(5);
	}

	private void menuItem7MouseReleased(MouseEvent e) {
		// TODO add your code here
		tabbedPane1.setSelectedIndex(6);
	}

	private void menuItem8MouseReleased(MouseEvent e) {
		// TODO add your code here
		tabbedPane1.setSelectedIndex(7);
	}

	private void menuItem9MouseReleased(MouseEvent e) {
		// TODO add your code here
		tabbedPane1.setSelectedIndex(8);
	}

	private void menuItem10MouseReleased(MouseEvent e) {
		// TODO add your code here
		tabbedPane1.setSelectedIndex(9);
	}

	private void menuItem11MouseReleased(MouseEvent e) {
		// TODO add your code here
		tabbedPane1.setSelectedIndex(10);
	}

	private void menuItem12MouseReleased(MouseEvent e) {
		// TODO add your code here
		tabbedPane1.setSelectedIndex(11);
	}

	private void menuItem13MouseReleased(MouseEvent e) {
		// TODO add your code here
		tabbedPane1.setSelectedIndex(12);
	}

	private void menuItem1MouseReleased(MouseEvent e) {
		// TODO add your code here
		tabbedPane1.setSelectedIndex(13);
	}

	private void menuItem2MouseReleased(MouseEvent e) {
		// TODO add your code here
		tabbedPane1.setSelectedIndex(14);
	}

	private void menuItem3MouseReleased(MouseEvent e) {
		// TODO add your code here
		tabbedPane1.setSelectedIndex(15);
	}

	private void button2MouseReleased(MouseEvent e) {
		String titol = textField7.getText();
		String autor = textField6.getText();
		String cont = textField1.getText();
		CP.nouDocument(titol, autor, ""); // A preguntar

		ArrayList<String> doc = CP.consultaDocument(autor, titol);

		textField3.setText(doc.get(0));
		textField2.setText(doc.get(1));
		checkBox1.setSelected(doc.get(2) == "Y");
		textField4.setText(doc.get(3));
		textField1.setText(doc.get(4));

		doc = CP.getAllDocs();
		updateJList(list7, doc);
		updateJList(list5, doc);
		updateJList(list10, doc);

	}

	private void ca_search_pressed(MouseEvent e) {
		String autor = textField9.getText();
		// Necessitem criteris d'ordenació!!
		ArrayList<String> myList = CP.consultaTit(autor, 0);
		updateJList(list8, myList);
	}

	private void consulta_prefix_autor_pressed(MouseEvent e) {
		String consulta = textField10.getText();
		ArrayList<String> mylist = CP.consultaAutor(consulta);
		updateJList(list9, mylist);
	}

	private void consulta_preferits_pressed(MouseEvent e) {
		// Necessitem criteris d'ordenació!!
		ArrayList<String> listOfDocs = CP.consultaPref(0);
		updateJList(list14, listOfDocs);
	}

	private void consulta_document_released(MouseEvent e) {
		String autor = textField11.getText();
		String titol = textField12.getText();
		ArrayList<String> doc = CP.consultaDocument(autor, titol);

		textField3.setText(doc.get(0));
		textField2.setText(doc.get(1));
		checkBox1.setSelected(doc.get(2) == "Y");
		textField4.setText(doc.get(3));
		textField1.setText(doc.get(4));
	}

	private void modifica_pressed(MouseEvent e) {
		// Editem contingut
		String autor = textField3.getText();
		String titol = textField2.getText();

		String content = textField1.getText();
		Boolean isFav = checkBox1.isSelected();
		String date = textField4.getText();

		CP.modificar_general(autor, titol, content, isFav, date);
	}

	private void consulta_rellevancia_pressed(MouseEvent e) {
		String NDocs = textField14.getText();
		String query = textField15.getText();

		Boolean firstSelected = radioButton1.isSelected();

		ArrayList<String> listOfDocs = CP.consultaRell(NDocs, query, firstSelected);
		updateJList(list13, listOfDocs);
	}

	private void consulta_data_presionat(MouseEvent e) {
		String dataAntStr = textField17.getText();
		String dataPostStr = textField18.getText();

		// Anterior = "A" Posterior = "P" Entre = "E"
		String select = "";
		if (radioButton3.isSelected()) select = "A";
		else if (radioButton4.isSelected()) select = "P";
		else select = "E";

		ArrayList<String> listOfDocs = CP.consultaData(dataAntStr, dataPostStr, select);
		if (listOfDocs.isEmpty()) listOfDocs.add("");
		updateJList(list15, listOfDocs);
	}

	private void consulta_avancada_pressed(MouseEvent e) {
		String query = textField16.getText();
		ArrayList<String> listOfDocs = CP.consultaAvancada(query);

		updateJList(list12, listOfDocs);
	}

	private void delete_pressed(MouseEvent e) {
		String doc = (String) list7.getSelectedValue();
		String[] titleautor = doc.split(" ~ ");

		CP.eliminarDoc(titleautor[0], titleautor[1]);

		updateJList(list7, CP.getAllDocs());
		updateJList(list5, CP.getAllDocs());
		updateJList(list10, CP.getAllDocs());

		// Resetegem els camps de la dreta
		textField3.setText("");
		textField2.setText("");
		checkBox1.setSelected(false);
		textField4.setText("");
		textField1.setText("");
	}

	private void obrir_pressed(MouseEvent e) {
		String doc = (String) list5.getSelectedValue();
		String[] titleautor = doc.split(" ~ ");

		ArrayList<String> repr = CP.consultaDocument(titleautor[1], titleautor[0]);

		textField3.setText(repr.get(0));
		textField2.setText(repr.get(1));
		checkBox1.setSelected(repr.get(2) == "Y");
		textField4.setText(repr.get(3));
		textField1.setText(repr.get(4));

	}

	private void consulta_semblant_pressed(MouseEvent e) {
		String NDocs = textField13.getText();
		String doc = (String) list10.getSelectedValue();
		String[] titleautor = doc.split(" ~ ");

		ArrayList<String> listOfDocs = CP.consultaSemb(titleautor[0], titleautor[1], NDocs);

		updateJList(list11, listOfDocs);
	}

	public void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		// Generated using JFormDesigner Evaluation license - Alex Ros
		frame1 = new JFrame();
		menuBar1 = new JMenuBar();
		menu1 = new JMenu();
		menuItem4 = new JMenuItem();
		menuItem5 = new JMenuItem();
		menuItem14 = new JMenuItem();
		menuItem15 = new JMenuItem();
		menuItem16 = new JMenuItem();
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
		label1 = new JLabel();
		textField3 = new JTextField();
		hSpacer5 = new JPanel(null);
		checkBox1 = new JCheckBox();
		hSpacer2 = new JPanel(null);
		label2 = new JLabel();
		textField2 = new JTextField();
		hSpacer4 = new JPanel(null);
		label3 = new JLabel();
		textField4 = new JTextField();
		hSpacer3 = new JPanel(null);
		tabbedPane1 = new JTabbedPane();
		panel1 = new JPanel();
		label4 = new JLabel();
		textField5 = new JTextField();
		label13 = new JLabel();
		list4 = new JList();
		button3 = new JButton();
		panel2 = new JPanel();
		label5 = new JLabel();
		scrollPane1 = new JScrollPane();
		list3 = new JList();
		button1 = new JButton();
		panel3 = new JPanel();
		label6 = new JLabel();
		textField6 = new JTextField();
		label7 = new JLabel();
		label8 = new JLabel();
		textField7 = new JTextField();
		label9 = new JLabel();
		button2 = new JButton();
		label11 = new JLabel();
		vSpacer4 = new JPanel(null);
		panel4 = new JPanel();
		label14 = new JLabel();
		scrollPane2 = new JScrollPane();
		list5 = new JList();
		button4 = new JButton();
		panel6 = new JPanel();
		label16 = new JLabel();
		scrollPane4 = new JScrollPane();
		list7 = new JList();
		button6 = new JButton();
		panel7 = new JPanel();
		label17 = new JLabel();
		scrollPane5 = new JScrollPane();
		textField9 = new JTextField();
		button7 = new JButton();
		label18 = new JLabel();
		scrollPane6 = new JScrollPane();
		list8 = new JList();
		panel8 = new JPanel();
		label19 = new JLabel();
		scrollPane7 = new JScrollPane();
		textField10 = new JTextField();
		button8 = new JButton();
		label20 = new JLabel();
		scrollPane8 = new JScrollPane();
		list9 = new JList();
		panel9 = new JPanel();
		label21 = new JLabel();
		scrollPane9 = new JScrollPane();
		textField11 = new JTextField();
		label23 = new JLabel();
		textField12 = new JTextField();
		button9 = new JButton();
		panel10 = new JPanel();
		label22 = new JLabel();
		scrollPane10 = new JScrollPane();
		textField13 = new JTextField();
		label24 = new JLabel();
		scrollPane11 = new JScrollPane();
		list10 = new JList();
		button10 = new JButton();
		label25 = new JLabel();
		scrollPane12 = new JScrollPane();
		list11 = new JList();
		panel11 = new JPanel();
		label26 = new JLabel();
		scrollPane13 = new JScrollPane();
		textField14 = new JTextField();
		label27 = new JLabel();
		textField15 = new JTextField();
		label29 = new JLabel();
		radioButton1 = new JRadioButton();
		radioButton2 = new JRadioButton();
		button11 = new JButton();
		label28 = new JLabel();
		scrollPane15 = new JScrollPane();
		list13 = new JList();
		panel12 = new JPanel();
		label30 = new JLabel();
		scrollPane14 = new JScrollPane();
		textField16 = new JTextField();
		button12 = new JButton();
		label31 = new JLabel();
		scrollPane16 = new JScrollPane();
		list12 = new JList();
		panel13 = new JPanel();
		button13 = new JButton();
		label32 = new JLabel();
		scrollPane17 = new JScrollPane();
		list14 = new JList();
		panel14 = new JPanel();
		label34 = new JLabel();
		radioButton3 = new JRadioButton();
		radioButton4 = new JRadioButton();
		radioButton5 = new JRadioButton();
		label37 = new JLabel();
		textField17 = new JTextField();
		label35 = new JLabel();
		textField18 = new JTextField();
		button14 = new JButton();
		label36 = new JLabel();
		scrollPane19 = new JScrollPane();
		list15 = new JList();
		panel15 = new JPanel();
		label33 = new JLabel();
		textField19 = new JTextField();
		label38 = new JLabel();
		label39 = new JLabel();
		textField20 = new JTextField();
		button15 = new JButton();
		panel16 = new JPanel();
		button16 = new JButton();
		label40 = new JLabel();
		scrollPane18 = new JScrollPane();
		list16 = new JList();
		label41 = new JLabel();
		label42 = new JLabel();
		textField21 = new JTextField();
		label43 = new JLabel();
		textField22 = new JTextField();
		button18 = new JButton();
		button19 = new JButton();
		button17 = new JButton();
		panel17 = new JPanel();
		label44 = new JLabel();
		scrollPane20 = new JScrollPane();
		textField23 = new JTextField();
		button20 = new JButton();
		label45 = new JLabel();
		scrollPane21 = new JScrollPane();
		list17 = new JList();
		scrollPane22 = new JScrollPane();
		textField1 = new JTextPane();
		button5 = new JButton();

		//======== frame1 ========
		{
			Container frame1ContentPane = frame1.getContentPane();
			frame1ContentPane.setLayout(new MigLayout(
				"fill,insets 0,hidemode 3,gap 5 5",
				// columns
				"[300:n:300,fill]" +
				"[50:n:50,fill]" +
				"[fill]" +
				"[fill]" +
				"[fill]" +
				"[10:n:10,fill]",
				// rows
				"[50:n:50]" +
				"[50:n:50]" +
				"[]" +
				"[]" +
				"[10:n:10]"));

			//======== menuBar1 ========
			{

				//======== menu1 ========
				{
					menu1.setText("Document");

					//---- menuItem4 ----
					menuItem4.setText("Importar Documents");
					menuItem4.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							menuItem4MouseReleased(e);
						}
					});

					menu1.add(menuItem4);

					//---- menuItem5 ----
					menuItem5.setText("Exportar Document");
					menuItem5.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							menuItem5MouseReleased(e);
						}
					});

					menu1.add(menuItem5);

					//---- menuItem14 ----
					menuItem14.setText("Crear Nou Document");
					menuItem14.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							menuItem14MouseReleased(e);
						}
					});

					menu1.add(menuItem14);

					//---- menuItem15 ----
					menuItem15.setText("Obrir Document");
					menuItem15.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							menuItem15MouseReleased(e);
						}
					});

					menu1.add(menuItem15);

					//---- menuItem16 ----
					menuItem16.setText("Tancar Document");
					menuItem16.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							menuItem16MouseReleased(e);
						}
					});

					menu1.add(menuItem16);

					//---- menuItem18 ----
					menuItem18.setText("Eliminar Document");
					menuItem18.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							menuItem18MouseReleased(e);
						}
					});

					menu1.add(menuItem18);
				}
				menuBar1.add(menu1);

				//======== menu2 ========
				{
					menu2.setText("Consulta");

					//---- menuItem6 ----
					menuItem6.setText("Consulta T\u00edtols d'un Autor");
					menuItem6.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							menuItem6MouseReleased(e);
						}
					});

					menu2.add(menuItem6);

					//---- menuItem7 ----
					menuItem7.setText("Consulta Autors per Prefix");
					menuItem7.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							menuItem7MouseReleased(e);
						}
					});

					menu2.add(menuItem7);

					//---- menuItem8 ----
					menuItem8.setText("Consulta Document");
					menuItem8.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							menuItem8MouseReleased(e);
						}
					});

					menu2.add(menuItem8);

					//---- menuItem9 ----
					menuItem9.setText("Consulta per Semblan\u00e7a");
					menuItem9.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							menuItem9MouseReleased(e);
						}
					});

					menu2.add(menuItem9);

					//---- menuItem10 ----
					menuItem10.setText("Consulta per Rellevancia");
					menuItem10.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							menuItem10MouseReleased(e);
						}
					});

					menu2.add(menuItem10);

					//---- menuItem11 ----
					menuItem11.setText("Consulta Avan\u00e7ada");
					menuItem11.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							menuItem11MouseReleased(e);
						}
					});

					menu2.add(menuItem11);

					//---- menuItem12 ----
					menuItem12.setText("Consulta Preferits");
					menuItem12.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							menuItem12MouseReleased(e);
						}
					});

					menu2.add(menuItem12);

					//---- menuItem13 ----
					menuItem13.setText("Consulta per Data");
					menuItem13.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							menuItem13MouseReleased(e);
						}
					});

					menu2.add(menuItem13);
				}
				menuBar1.add(menu2);

				//======== menu3 ========
				{
					menu3.setText("Expressi\u00f3  Booleana");

					//---- menuItem1 ----
					menuItem1.setText("Alta Expressi\u00f3");
					menuItem1.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							menuItem1MouseReleased(e);
						}
					});

					menu3.add(menuItem1);

					//---- menuItem2 ----
					menuItem2.setText("Gesti\u00f3 Expressions Guardades");
					menuItem2.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							menuItem2MouseReleased(e);
						}
					});

					menu3.add(menuItem2);

					//---- menuItem3 ----
					menuItem3.setText("Consulta Expressi\u00f3");
					menuItem3.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							menuItem3MouseReleased(e);
						}
					});

					menu3.add(menuItem3);
				}
				menuBar1.add(menu3);
			}
			frame1.setJMenuBar(menuBar1);

			//---- label1 ----
			label1.setText("Autor");
			frame1ContentPane.add(label1, "cell 2 0,hmax 50");
			frame1ContentPane.add(textField3, "cell 2 0,wmin 150,hmax 50");
			frame1ContentPane.add(hSpacer5, "cell 3 0");

			//---- checkBox1 ----
			checkBox1.setText("Preferit");
			frame1ContentPane.add(checkBox1, "cell 4 0,hmax 50");
			frame1ContentPane.add(hSpacer2, "cell 5 0");

			//---- label2 ----
			label2.setText("T\u00edtol");
			frame1ContentPane.add(label2, "cell 2 1,hmax 50");
			frame1ContentPane.add(textField2, "cell 2 1,wmin 150,hmax 50");
			frame1ContentPane.add(hSpacer4, "cell 3 1");

			//---- label3 ----
			label3.setText("Data");
			frame1ContentPane.add(label3, "cell 4 1,hmax 50");
			frame1ContentPane.add(textField4, "cell 4 1,width 120::120,hmax 50");
			frame1ContentPane.add(hSpacer3, "cell 5 1");

			//======== tabbedPane1 ========
			{
				tabbedPane1.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
				tabbedPane1.setUI(new BasicTabbedPaneUI() {
					@Override
					protected int calculateTabAreaHeight(int tab_placement, int run_count, int max_tab_height) {
						return 0;
					}
				});


				//======== panel1 ========
				{
					panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
					0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
					. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
					red) ,panel1. getBorder( )) ); panel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .
					beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
					panel1.setLayout(new MigLayout(
						"hidemode 3",
						// columns
						"[fill]",
						// rows
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]"));

					//---- label4 ----
					label4.setText("Seleccioni els documents a importar");
					panel1.add(label4, "cell 0 0");

					//---- textField5 ----
					textField5.setText("//browser d'arxius");
					panel1.add(textField5, "cell 0 1");

					//---- label13 ----
					label13.setText("Documents sleccionats");
					panel1.add(label13, "cell 0 2");
					panel1.add(list4, "cell 0 3,width 300::300,height 150::150");

					//---- button3 ----
					button3.setText("Importa");
					panel1.add(button3, "cell 0 5");
				}
				tabbedPane1.addTab("ID", panel1);

				//======== panel2 ========
				{
					panel2.setLayout(new MigLayout(
						"hidemode 3",
						// columns
						"[fill]",
						// rows
						"[]" +
						"[]" +
						"[]"));

					//---- label5 ----
					label5.setText("Seleccioni el document que vol exportar");
					panel2.add(label5, "cell 0 0");

					//======== scrollPane1 ========
					{
						scrollPane1.setViewportView(list3);
					}
					panel2.add(scrollPane1, "cell 0 1,growx,width 300::300");

					//---- button1 ----
					button1.setText("Exporta");
					panel2.add(button1, "cell 0 2");
				}
				tabbedPane1.addTab("ED", panel2);

				//======== panel3 ========
				{
					panel3.setLayout(new MigLayout(
						"filly,hidemode 3",
						// columns
						"[fill]" +
						"[fill]",
						// rows
						"[25:n:25]" +
						"[25:n:25]" +
						"[15:n:15]" +
						"[25:n:25]" +
						"[25:n:25]" +
						"[15:n:15]" +
						"[25:n:25]" +
						"[25:n:25]" +
						"[25:n:25]" +
						"[50:n:50]" +
						"[]"));

					//---- label6 ----
					label6.setText("Autor del nou Document");
					panel3.add(label6, "cell 0 0");
					panel3.add(textField6, "cell 0 1");

					//---- label7 ----
					label7.setText("(obligatori)");
					panel3.add(label7, "cell 1 1");

					//---- label8 ----
					label8.setText("T\u00edtol del Document");
					panel3.add(label8, "cell 0 3");
					panel3.add(textField7, "cell 0 4");

					//---- label9 ----
					label9.setText("(obligatori)");
					panel3.add(label9, "cell 1 4");

					//---- button2 ----
					button2.setText("Crea");
					button2.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							button2MouseReleased(e);
						}
					});
					panel3.add(button2, "cell 1 6");
					panel3.add(label11, "cell 1 7");
					panel3.add(vSpacer4, "cell 0 10");
				}
				tabbedPane1.addTab("CD", panel3);

				//======== panel4 ========
				{
					panel4.setLayout(new MigLayout(
						"hidemode 3",
						// columns
						"[fill]",
						// rows
						"[]" +
						"[]" +
						"[]"));

					//---- label14 ----
					label14.setText("Seleccioni el document que vol obrir");
					panel4.add(label14, "cell 0 0");

					//======== scrollPane2 ========
					{
						scrollPane2.setViewportView(list5);
					}
					panel4.add(scrollPane2, "cell 0 1,growx,width 300::300");

					//---- button4 ----
					button4.setText("Obre");
					button4.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							obrir_pressed(e);
						}
					});
					panel4.add(button4, "cell 0 2");
				}
				tabbedPane1.addTab("Obr", panel4);

				//======== panel6 ========
				{
					panel6.setLayout(new MigLayout(
						"hidemode 3",
						// columns
						"[fill]",
						// rows
						"[]" +
						"[]" +
						"[]"));

					//---- label16 ----
					label16.setText("Seleccioni el document que vol esborrar");
					panel6.add(label16, "cell 0 0");

					//======== scrollPane4 ========
					{
						scrollPane4.setViewportView(list7);
					}
					panel6.add(scrollPane4, "cell 0 1,growx,width 300::300");

					//---- button6 ----
					button6.setText("Delete");
					button6.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							delete_pressed(e);
						}
					});
					panel6.add(button6, "cell 0 2");
				}
				tabbedPane1.addTab("ED", panel6);

				//======== panel7 ========
				{
					panel7.setLayout(new MigLayout(
						"hidemode 3",
						// columns
						"[fill]",
						// rows
						"[]" +
						"[]" +
						"[::20]" +
						"[]" +
						"[30:n:30]" +
						"[]" +
						"[]"));

					//---- label17 ----
					label17.setText("Escriu l'autor desitjat");
					panel7.add(label17, "cell 0 0");

					//======== scrollPane5 ========
					{
						scrollPane5.setViewportView(textField9);
					}
					panel7.add(scrollPane5, "cell 0 1,growx,width 300::300");

					//---- button7 ----
					button7.setText("Search");
					button7.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							ca_search_pressed(e);
						}
					});
					panel7.add(button7, "cell 0 3");

					//---- label18 ----
					label18.setText("Documents trobats:");
					panel7.add(label18, "cell 0 5");

					//======== scrollPane6 ========
					{
						scrollPane6.setViewportView(list8);
					}
					panel7.add(scrollPane6, "cell 0 6,width 300::300,height 150::150");
				}
				tabbedPane1.addTab("CA", panel7);

				//======== panel8 ========
				{
					panel8.setLayout(new MigLayout(
						"hidemode 3",
						// columns
						"[fill]",
						// rows
						"[]" +
						"[]" +
						"[::20]" +
						"[]" +
						"[30:n:30]" +
						"[]" +
						"[]"));

					//---- label19 ----
					label19.setText("Escriu el prefix");
					panel8.add(label19, "cell 0 0");

					//======== scrollPane7 ========
					{
						scrollPane7.setViewportView(textField10);
					}
					panel8.add(scrollPane7, "cell 0 1,growx,width 300::300");

					//---- button8 ----
					button8.setText("Search");
					button8.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							consulta_prefix_autor_pressed(e);
						}
					});
					panel8.add(button8, "cell 0 3");

					//---- label20 ----
					label20.setText("Autors trobats:");
					panel8.add(label20, "cell 0 5");

					//======== scrollPane8 ========
					{
						scrollPane8.setViewportView(list9);
					}
					panel8.add(scrollPane8, "cell 0 6,width 300::300,height 150::150");
				}
				tabbedPane1.addTab("CP", panel8);

				//======== panel9 ========
				{
					panel9.setLayout(new MigLayout(
						"hidemode 3",
						// columns
						"[fill]",
						// rows
						"[]" +
						"[]" +
						"[::20]" +
						"[]" +
						"[30:n:30]" +
						"[]" +
						"[]" +
						"[]"));

					//---- label21 ----
					label21.setText("Escriu l'autor");
					panel9.add(label21, "cell 0 0");

					//======== scrollPane9 ========
					{
						scrollPane9.setViewportView(textField11);
					}
					panel9.add(scrollPane9, "cell 0 1,growx,width 300::300");

					//---- label23 ----
					label23.setText("Escriu el t\u00edtol");
					panel9.add(label23, "cell 0 2");
					panel9.add(textField12, "cell 0 3");

					//---- button9 ----
					button9.setText("Mostra");
					button9.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							consulta_document_released(e);
						}
					});
					panel9.add(button9, "cell 0 5");
				}
				tabbedPane1.addTab("ConD", panel9);

				//======== panel10 ========
				{
					panel10.setLayout(new MigLayout(
						"hidemode 3",
						// columns
						"[fill]",
						// rows
						"[]" +
						"[]" +
						"[::20]" +
						"[]" +
						"[30:n:30]" +
						"[]" +
						"[]" +
						"[]"));

					//---- label22 ----
					label22.setText("N\u00famero de Documents desitjats");
					panel10.add(label22, "cell 0 0");

					//======== scrollPane10 ========
					{
						scrollPane10.setViewportView(textField13);
					}
					panel10.add(scrollPane10, "cell 0 1,growx,width 300::300");

					//---- label24 ----
					label24.setText("Selecciona el document \"refer\u00e8ncia\"");
					panel10.add(label24, "cell 0 2");

					//======== scrollPane11 ========
					{
						scrollPane11.setViewportView(list10);
					}
					panel10.add(scrollPane11, "cell 0 3,width 300::300,height 150::150");

					//---- button10 ----
					button10.setText("Obtenir Documents");
					button10.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							consulta_semblant_pressed(e);
						}
					});
					panel10.add(button10, "cell 0 4");

					//---- label25 ----
					label25.setText("Els documents m\u00e9s semblants s\u00f3n:");
					panel10.add(label25, "cell 0 6");

					//======== scrollPane12 ========
					{
						scrollPane12.setViewportView(list11);
					}
					panel10.add(scrollPane12, "cell 0 7,width 300::300,height 150::150");
				}
				tabbedPane1.addTab("CS", panel10);

				//======== panel11 ========
				{
					panel11.setLayout(new MigLayout(
						"hidemode 3",
						// columns
						"[fill]",
						// rows
						"[]" +
						"[]" +
						"[::20]" +
						"[]" +
						"[30:n:30]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]"));

					//---- label26 ----
					label26.setText("N\u00famero de Documents desitjats");
					panel11.add(label26, "cell 0 0");

					//======== scrollPane13 ========
					{
						scrollPane13.setViewportView(textField14);
					}
					panel11.add(scrollPane13, "cell 0 1,growx,width 300::300");

					//---- label27 ----
					label27.setText("Escriu la query");
					panel11.add(label27, "cell 0 2");
					panel11.add(textField15, "cell 0 3");

					//---- label29 ----
					label29.setText("Selecciona el mode de cerca");
					panel11.add(label29, "cell 0 4");

					//---- radioButton1 ----
					radioButton1.setText("N\u00famero d'aparicions de les paraules");
					radioButton1.setSelected(true);
					panel11.add(radioButton1, "cell 0 5");

					//---- radioButton2 ----
					radioButton2.setText("Semblan\u00e7a a la query");
					panel11.add(radioButton2, "cell 0 6");

					//---- button11 ----
					button11.setText("Obtenir Documents");
					button11.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							consulta_rellevancia_pressed(e);
						}
					});
					panel11.add(button11, "cell 0 7");

					//---- label28 ----
					label28.setText("Els documents m\u00e9s rellevants s\u00f3n:");
					panel11.add(label28, "cell 0 8");

					//======== scrollPane15 ========
					{
						scrollPane15.setViewportView(list13);
					}
					panel11.add(scrollPane15, "cell 0 9,width 300::300,height 150::150");
				}
				tabbedPane1.addTab("CR", panel11);

				//======== panel12 ========
				{
					panel12.setLayout(new MigLayout(
						"hidemode 3",
						// columns
						"[fill]",
						// rows
						"[]" +
						"[]" +
						"[::20]" +
						"[]" +
						"[30:n:30]" +
						"[]" +
						"[]"));

					//---- label30 ----
					label30.setText("Escriu el string a cercar");
					panel12.add(label30, "cell 0 0");

					//======== scrollPane14 ========
					{
						scrollPane14.setViewportView(textField16);
					}
					panel12.add(scrollPane14, "cell 0 1,growx,width 300::300");

					//---- button12 ----
					button12.setText("Search");
					button12.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							consulta_avancada_pressed(e);
						}
					});
					panel12.add(button12, "cell 0 3");

					//---- label31 ----
					label31.setText("Documents on s'ha trobat el string:");
					panel12.add(label31, "cell 0 5");

					//======== scrollPane16 ========
					{
						scrollPane16.setViewportView(list12);
					}
					panel12.add(scrollPane16, "cell 0 6,width 300::300,height 150::150");
				}
				tabbedPane1.addTab("CAv", panel12);

				//======== panel13 ========
				{
					panel13.setLayout(new MigLayout(
						"hidemode 3",
						// columns
						"[fill]",
						// rows
						"[15:n:15]" +
						"[]" +
						"[20:n:20]" +
						"[]" +
						"[]"));

					//---- button13 ----
					button13.setText("Mostra");
					button13.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							consulta_preferits_pressed(e);
						}
					});
					panel13.add(button13, "cell 0 1");

					//---- label32 ----
					label32.setText("Documents preferits:");
					panel13.add(label32, "cell 0 3");

					//======== scrollPane17 ========
					{
						scrollPane17.setViewportView(list14);
					}
					panel13.add(scrollPane17, "cell 0 4,growx,width 300::300");
				}
				tabbedPane1.addTab("CP", panel13);

				//======== panel14 ========
				{
					panel14.setLayout(new MigLayout(
						"hidemode 3",
						// columns
						"[fill]",
						// rows
						"[15:n:15]" +
						"[]" +
						"[20:n:20]" +
						"[]" +
						"[20:n:20]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[25:n:25]" +
						"[]" +
						"[]"));

					//---- label34 ----
					label34.setText("Selecciona el modede cerca de Documents:");
					panel14.add(label34, "cell 0 0");

					//---- radioButton3 ----
					radioButton3.setText("Anteriors a una Data");
					radioButton3.setSelected(true);
					panel14.add(radioButton3, "cell 0 1");

					//---- radioButton4 ----
					radioButton4.setText("Posteriors a una Data");
					panel14.add(radioButton4, "cell 0 2");

					//---- radioButton5 ----
					radioButton5.setText("Entre dues Dates");
					panel14.add(radioButton5, "cell 0 3");

					//---- label37 ----
					label37.setText("Data anterior: (yyyy-mm-dd)");
					panel14.add(label37, "cell 0 5");
					panel14.add(textField17, "cell 0 6");

					//---- label35 ----
					label35.setText("Data posterior: (yyyy-mm-dd)");
					panel14.add(label35, "cell 0 7");
					panel14.add(textField18, "cell 0 8");

					//---- button14 ----
					button14.setText("Cerca");
					button14.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							consulta_data_presionat(e);
						}
					});
					panel14.add(button14, "cell 0 9");

					//---- label36 ----
					label36.setText("Els documents resultants s\u00f3n:");
					panel14.add(label36, "cell 0 11");

					//======== scrollPane19 ========
					{
						scrollPane19.setViewportView(list15);
					}
					panel14.add(scrollPane19, "cell 0 12");
				}
				tabbedPane1.addTab("CData", panel14);

				//======== panel15 ========
				{
					panel15.setLayout(new MigLayout(
						"hidemode 3",
						// columns
						"[fill]",
						// rows
						"[]" +
						"[]" +
						"[]" +
						"[25:n:25]" +
						"[]" +
						"[]" +
						"[30:n:30]" +
						"[]"));

					//---- label33 ----
					label33.setText("Introdueix l'expressi\u00f3 booleana que vol crear:");
					panel15.add(label33, "cell 0 0");
					panel15.add(textField19, "cell 0 1");

					//---- label38 ----
					label38.setText("(ha de ser sint\u00e0cticament correcta)");
					panel15.add(label38, "cell 0 2");

					//---- label39 ----
					label39.setText("Escriu el nom per guardar l'expressi\u00f3:");
					panel15.add(label39, "cell 0 4");
					panel15.add(textField20, "cell 0 5");

					//---- button15 ----
					button15.setText("Crea");
					panel15.add(button15, "cell 0 7");
				}
				tabbedPane1.addTab("AEB", panel15);

				//======== panel16 ========
				{
					panel16.setLayout(new MigLayout(
						"hidemode 3",
						// columns
						"[fill]",
						// rows
						"[15:n:15]" +
						"[]" +
						"[20:n:20]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]"));

					//---- button16 ----
					button16.setText("Mostra");
					panel16.add(button16, "cell 0 1");

					//---- label40 ----
					label40.setText("Expressions guardades:");
					panel16.add(label40, "cell 0 3");

					//======== scrollPane18 ========
					{
						scrollPane18.setViewportView(list16);
					}
					panel16.add(scrollPane18, "cell 0 4,growx,width 300::300");

					//---- label41 ----
					label41.setText("//Un cop seleccionada una de la llista");
					panel16.add(label41, "cell 0 5");

					//---- label42 ----
					label42.setText("Nom");
					panel16.add(label42, "cell 0 6");
					panel16.add(textField21, "cell 0 7");

					//---- label43 ----
					label43.setText("Expressi\u00f3");
					panel16.add(label43, "cell 0 8");
					panel16.add(textField22, "cell 0 9");

					//---- button18 ----
					button18.setText("Actualitza");
					panel16.add(button18, "cell 0 10");

					//---- button19 ----
					button19.setText("Cerca per aquesta expressi\u00f3");
					panel16.add(button19, "cell 0 11");

					//---- button17 ----
					button17.setText("Esborra");
					panel16.add(button17, "cell 0 12");
				}
				tabbedPane1.addTab("GEB", panel16);

				//======== panel17 ========
				{
					panel17.setLayout(new MigLayout(
						"hidemode 3",
						// columns
						"[fill]",
						// rows
						"[]" +
						"[]" +
						"[::20]" +
						"[]" +
						"[30:n:30]" +
						"[]" +
						"[]"));

					//---- label44 ----
					label44.setText("Escriu l'expressi\u00f3 per cercar");
					panel17.add(label44, "cell 0 0");

					//======== scrollPane20 ========
					{
						scrollPane20.setViewportView(textField23);
					}
					panel17.add(scrollPane20, "cell 0 1,growx,width 300::300");

					//---- button20 ----
					button20.setText("Search");
					panel17.add(button20, "cell 0 3");

					//---- label45 ----
					label45.setText("Documents que compleixen l'expressi\u00f3:");
					panel17.add(label45, "cell 0 5");

					//======== scrollPane21 ========
					{
						scrollPane21.setViewportView(list17);
					}
					panel17.add(scrollPane21, "cell 0 6,width 300::300,height 150::150");
				}
				tabbedPane1.addTab("CB", panel17);

				tabbedPane1.setSelectedIndex(0);
			}
			frame1ContentPane.add(tabbedPane1, "cell 0 0 1 3,grow");

			//======== scrollPane22 ========
			{

				//---- textField1 ----
				textField1.setText("Una vegada creat un document, escriu el contingut aqu\u00ed!");
				scrollPane22.setViewportView(textField1);
			}
			frame1ContentPane.add(scrollPane22, "cell 2 2 3 1,grow,wmin 200,hmin 200");

			//---- button5 ----
			button5.setText("Modifica");
			button5.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					modifica_pressed(e);
				}
			});
			frame1ContentPane.add(button5, "cell 3 3");
			frame1.pack();
			frame1.setLocationRelativeTo(frame1.getOwner());
		}

		//---- buttonGroup1 ----
		ButtonGroup buttonGroup1 = new ButtonGroup();
		buttonGroup1.add(radioButton1);
		buttonGroup1.add(radioButton2);

		//---- buttonGroup2 ----
		ButtonGroup buttonGroup2 = new ButtonGroup();
		buttonGroup2.add(radioButton3);
		buttonGroup2.add(radioButton4);
		buttonGroup2.add(radioButton5);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	// Generated using JFormDesigner Evaluation license - Alex Ros
	private JFrame frame1;
	private JMenuBar menuBar1;
	private JMenu menu1;
	private JMenuItem menuItem4;
	private JMenuItem menuItem5;
	private JMenuItem menuItem14;
	private JMenuItem menuItem15;
	private JMenuItem menuItem16;
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
	private JLabel label1;
	private JTextField textField3;
	private JPanel hSpacer5;
	private JCheckBox checkBox1;
	private JPanel hSpacer2;
	private JLabel label2;
	private JTextField textField2;
	private JPanel hSpacer4;
	private JLabel label3;
	private JTextField textField4;
	private JPanel hSpacer3;
	private JTabbedPane tabbedPane1;
	private JPanel panel1;
	private JLabel label4;
	private JTextField textField5;
	private JLabel label13;
	private JList list4;
	private JButton button3;
	private JPanel panel2;
	private JLabel label5;
	private JScrollPane scrollPane1;
	private JList list3;
	private JButton button1;
	private JPanel panel3;
	private JLabel label6;
	private JTextField textField6;
	private JLabel label7;
	private JLabel label8;
	private JTextField textField7;
	private JLabel label9;
	private JButton button2;
	private JLabel label11;
	private JPanel vSpacer4;
	private JPanel panel4;
	private JLabel label14;
	private JScrollPane scrollPane2;
	private JList list5;
	private JButton button4;
	private JPanel panel6;
	private JLabel label16;
	private JScrollPane scrollPane4;
	private JList list7;
	private JButton button6;
	private JPanel panel7;
	private JLabel label17;
	private JScrollPane scrollPane5;
	private JTextField textField9;
	private JButton button7;
	private JLabel label18;
	private JScrollPane scrollPane6;
	private JList list8;
	private JPanel panel8;
	private JLabel label19;
	private JScrollPane scrollPane7;
	private JTextField textField10;
	private JButton button8;
	private JLabel label20;
	private JScrollPane scrollPane8;
	private JList list9;
	private JPanel panel9;
	private JLabel label21;
	private JScrollPane scrollPane9;
	private JTextField textField11;
	private JLabel label23;
	private JTextField textField12;
	private JButton button9;
	private JPanel panel10;
	private JLabel label22;
	private JScrollPane scrollPane10;
	private JTextField textField13;
	private JLabel label24;
	private JScrollPane scrollPane11;
	private JList list10;
	private JButton button10;
	private JLabel label25;
	private JScrollPane scrollPane12;
	private JList list11;
	private JPanel panel11;
	private JLabel label26;
	private JScrollPane scrollPane13;
	private JTextField textField14;
	private JLabel label27;
	private JTextField textField15;
	private JLabel label29;
	private JRadioButton radioButton1;
	private JRadioButton radioButton2;
	private JButton button11;
	private JLabel label28;
	private JScrollPane scrollPane15;
	private JList list13;
	private JPanel panel12;
	private JLabel label30;
	private JScrollPane scrollPane14;
	private JTextField textField16;
	private JButton button12;
	private JLabel label31;
	private JScrollPane scrollPane16;
	private JList list12;
	private JPanel panel13;
	private JButton button13;
	private JLabel label32;
	private JScrollPane scrollPane17;
	private JList list14;
	private JPanel panel14;
	private JLabel label34;
	private JRadioButton radioButton3;
	private JRadioButton radioButton4;
	private JRadioButton radioButton5;
	private JLabel label37;
	private JTextField textField17;
	private JLabel label35;
	private JTextField textField18;
	private JButton button14;
	private JLabel label36;
	private JScrollPane scrollPane19;
	private JList list15;
	private JPanel panel15;
	private JLabel label33;
	private JTextField textField19;
	private JLabel label38;
	private JLabel label39;
	private JTextField textField20;
	private JButton button15;
	private JPanel panel16;
	private JButton button16;
	private JLabel label40;
	private JScrollPane scrollPane18;
	private JList list16;
	private JLabel label41;
	private JLabel label42;
	private JTextField textField21;
	private JLabel label43;
	private JTextField textField22;
	private JButton button18;
	private JButton button19;
	private JButton button17;
	private JPanel panel17;
	private JLabel label44;
	private JScrollPane scrollPane20;
	private JTextField textField23;
	private JButton button20;
	private JLabel label45;
	private JScrollPane scrollPane21;
	private JList list17;
	private JScrollPane scrollPane22;
	private JTextPane textField1;
	private JButton button5;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
