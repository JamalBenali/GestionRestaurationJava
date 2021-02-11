package InterfaceClient;

import java.awt.*;
import java.awt.event.*;


import javax.swing.*;
import javax.swing.border.EmptyBorder;

import InterfaceClient.Acceuil_View;
import InterfaceClient.Function;

import com.toedter.calendar.JDateChooser;


public class Client_View extends JFrame {
	
	private JLabel Background,btnMenu,btnTable,btnReserver,btnFacture,ReserverImage,btnHome;
	private JPanel contentPane,MenuPanel,ReserverPanel,TablePanel,FacturePanel;
	private JTextField QuantiteTxt,PrixTotale;
	private JComboBox<String> PlateCombo,tableCombo;
	private JDateChooser dateReservation;
	private JButton btnPasser,btnValider;
	private JScrollPane PlatescrollPane,FacturescrollPane,TablescrollPane;
	private static int CodeRes = 0;
	Function F = new Function();
	

	public Client_View() {
		initComponents();
	}
	public void initComponents(){
		//Creation de JFrame
		setTitle("Espace Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 70, 1067, 630);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("CheckBox.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//image Backround
		Background = new JLabel();
		Background.setBounds(0,0,290,600);
		Background.setIcon(new ImageIcon(Acceuil_View.class.getResource("/image/Choix.png")));
		
		//ajout Btn Menu
		btnMenu = new JLabel();
		btnMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMenu.setBounds(0,103,290,100);
		btnMenu.setIcon(new ImageIcon(Acceuil_View.class.getResource("/image/btnMenu.png")));
		btnMenu.addMouseListener(new LabelAction());
		contentPane.add(btnMenu);
		
		//ajout Btn Table
		btnTable = new JLabel();
		btnTable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTable.setBounds(0,203,290,100);
		btnTable.setIcon(new ImageIcon(Acceuil_View.class.getResource("/image/btnTable.png")));
		btnTable.addMouseListener(new LabelAction());
		contentPane.add(btnTable);
		
		//ajout BtnReserver
		btnReserver = new JLabel();
		btnReserver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReserver.setBounds(0,303,290,100);
		btnReserver.setIcon(new ImageIcon(Acceuil_View.class.getResource("/image/btnReserver.png")));
		btnReserver.addMouseListener(new LabelAction());
		contentPane.add(btnReserver);
		
		//ajout BtnFacture
		btnFacture = new JLabel();
		btnFacture.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFacture.setBounds(0,403,290,100);
		btnFacture.setIcon(new ImageIcon(Acceuil_View.class.getResource("/image/btnFacture.png")));
		btnFacture.addMouseListener(new LabelAction());
		contentPane.add(btnFacture);
		
		contentPane.add(Background);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//ajout btn Home
		btnHome = new JLabel();
		btnHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHome.setBounds(985, 530, 56, 51);
		btnHome.setIcon(new ImageIcon(Acceuil_View.class.getResource("/image/Home.png")));
		btnHome.addMouseListener(new LabelAction());
		contentPane.add(btnHome);
		
		//Facture Design
		FacturePanel = new JPanel();
		FacturePanel.setBounds(290, 103, 761, 412);
		contentPane.add(FacturePanel);
		FacturePanel.setVisible(false);
		FacturePanel.setLayout(null);
		
		//calculer prix facture
		JLabel lblPrixTotal = new JLabel("Prix Total : ");
		lblPrixTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrixTotal.setBounds(448, 268, 82, 25);
		FacturePanel.add(lblPrixTotal);
		
		PrixTotale = new JTextField();
		PrixTotale.setBounds(523, 268, 94, 25);
		FacturePanel.add(PrixTotale);
		PrixTotale.setColumns(10);
		
		JButton btnCalculer = new JButton("Calculer");
		btnCalculer.setForeground(new Color(255, 255, 255));
		btnCalculer.setBackground(new Color(255, 140, 0));
		btnCalculer.setBounds(642, 271, 89, 23);
		btnCalculer.addActionListener(new CalculerPrixAction());
		FacturePanel.add(btnCalculer);
		
		//Reservation Design
		ReserverPanel = new JPanel();
		ReserverPanel.setBounds(290, 103, 761, 412);
		contentPane.add(ReserverPanel);
		ReserverPanel.setVisible(false);
		ReserverPanel.setLayout(null);
		
		//Reserver table
		JLabel lblReservertable = new JLabel("1\\   Reserver Table");
		lblReservertable.setForeground(new Color(255, 153, 0));
		lblReservertable.setFont(new Font("Tw Cen MT", Font.PLAIN, 28));
		lblReservertable.setVisible(true);
		lblReservertable.setBounds(35, 23, 239, 37);
		ReserverPanel.add(lblReservertable);
		
		JLabel lblTable = new JLabel("Table : ");
		lblTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTable.setBounds(87, 78, 50, 31);
		ReserverPanel.add(lblTable);
		
		tableCombo = new JComboBox();
		tableCombo.setBounds(161, 85, 127, 20);
		F.RemplirCombo("tables",tableCombo);
		ReserverPanel.add(tableCombo);
		
		JLabel lblDate = new JLabel("Date : ");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDate.setBounds(87, 129, 46, 14);
		ReserverPanel.add(lblDate);
		
		dateReservation = new JDateChooser();
		dateReservation.setBounds(161, 129, 127, 20);
		ReserverPanel.add(dateReservation);
		
		btnPasser = new JButton("Passer");
		btnPasser.setBackground(new Color(255, 140, 0));
		btnPasser.setForeground(Color.WHITE);
		btnPasser.setBounds(187, 169, 89, 23);
		btnPasser.addActionListener(new PasserAction());
		ReserverPanel.add(btnPasser);
		
		//Reserver Plate
		JLabel lblReserverPlate = new JLabel("2\\   Reserver Plate");
		lblReserverPlate.setForeground(new Color(255, 153, 0));
		lblReserverPlate.setFont(new Font("Tw Cen MT", Font.PLAIN, 28));
		lblReserverPlate.setBounds(35, 233, 239, 37);
		ReserverPanel.add(lblReserverPlate);
		
		JLabel lblPlate = new JLabel("Plate : ");
		lblPlate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPlate.setBounds(87, 281, 46, 14);
		ReserverPanel.add(lblPlate);
		
		PlateCombo = new JComboBox<String>();
		PlateCombo.setBounds(161, 281, 127, 20);
		F.RemplirCombo("Plate",PlateCombo);
		ReserverPanel.add(PlateCombo);
		
		JLabel lblQuantite = new JLabel("Quantite : ");
		lblQuantite.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuantite.setBounds(87, 321, 65, 20);
		ReserverPanel.add(lblQuantite);
		
		QuantiteTxt = new JTextField();
		QuantiteTxt.setBounds(161, 323, 127, 20);
		ReserverPanel.add(QuantiteTxt);
		QuantiteTxt.setColumns(10);
		
		btnValider = new JButton("Valider");
		btnValider.setBackground(new Color(255, 140, 0));
		btnValider.setForeground(new Color(255, 255, 255));
		btnValider.setBounds(187, 364, 87, 23);
		btnValider.addActionListener(new validerAction());
		ReserverPanel.add(btnValider);
		
		ReserverImage = new JLabel();
		ReserverImage.setBounds(386,11,305,450);
		ReserverImage.setIcon(new ImageIcon(Acceuil_View.class.getResource("/image/ReserverImage.png")));
		ReserverPanel.add(ReserverImage);
		
		//Table Design
		TablePanel = new JPanel();
		TablePanel.setBounds(290, 103, 761, 412);
		contentPane.add(TablePanel);
		TablePanel.setVisible(false);
		TablePanel.setLayout(null);
				
		//Ajout des Donnees de la base de donnes 
		TablescrollPane = F.RemplirData("tables");
		TablescrollPane.setBounds(25, 22, 706, 379);
		TablePanel.add(TablescrollPane);
		
		//Menu Design
		MenuPanel = new JPanel();
		MenuPanel.setBounds(290, 103, 761, 412);
		contentPane.add(MenuPanel);
		MenuPanel.setVisible(false);
		MenuPanel.setLayout(null);
		
		//Ajout des Donnees de la base de donnes 
		PlatescrollPane = F.RemplirData("Plate");
		PlatescrollPane.setBounds(25, 22, 706, 379);
		MenuPanel.add(PlatescrollPane);
	
		///////////////////////////////////////////////////////////////////////////////////////////////////
		JLabel lblBienvenueDansVotre = new JLabel("Bienvenue Sur Votre Espace Client");
		lblBienvenueDansVotre.setForeground(new Color(255, 140, 0));
		lblBienvenueDansVotre.setFont(new Font("Times New Roman", Font.ITALIC, 47));
		lblBienvenueDansVotre.setBounds(310, 28, 717, 64);
		contentPane.add(lblBienvenueDansVotre);
	}
	//Les methodes de la classe
	public void HideFen(){
		this.hide();
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	class LabelAction extends MouseAdapter{
		public void mouseEntered(MouseEvent e) {
			if (e.getSource() == btnMenu)
				btnMenu.setIcon(new ImageIcon(Acceuil_View.class.getResource("/image/btnMenu_Pressed.png")));
			if (e.getSource() == btnTable)
				btnTable.setIcon(new ImageIcon(Acceuil_View.class.getResource("/image/btnTable_Pressed.png")));
			if (e.getSource() == btnReserver)
				btnReserver.setIcon(new ImageIcon(Acceuil_View.class.getResource("/image/btnReserver_Pressed.png")));
			if (e.getSource() == btnFacture)
				btnFacture.setIcon(new ImageIcon(Acceuil_View.class.getResource("/image/btnFacture_Pressed.png")));
			if (e.getSource() == btnHome)
				btnHome.setIcon(new ImageIcon(Acceuil_View.class.getResource("/image/Home_Pressed.png")));
		}
		public void mouseExited(MouseEvent e) {
			if (e.getSource() == btnMenu)
				btnMenu.setIcon(new ImageIcon(Acceuil_View.class.getResource("/image/btnMenu.png")));
			if (e.getSource() == btnTable)
				btnTable.setIcon(new ImageIcon(Acceuil_View.class.getResource("/image/btnTable.png")));
			if (e.getSource() == btnReserver)
				btnReserver.setIcon(new ImageIcon(Acceuil_View.class.getResource("/image/btnReserver.png")));
			if (e.getSource() == btnFacture)
				btnFacture.setIcon(new ImageIcon(Acceuil_View.class.getResource("/image/btnFacture.png")));
			if (e.getSource() == btnHome)
				btnHome.setIcon(new ImageIcon(Acceuil_View.class.getResource("/image/Home.png")));
		}
		public void mouseClicked(MouseEvent e){
			if (e.getSource() == btnMenu){
				MenuPanel.setVisible(true);
				TablePanel.setVisible(false);
				ReserverPanel.setVisible(false);
				FacturePanel.setVisible(false);
			}
			if (e.getSource() == btnTable){
				TablePanel.setVisible(true);
				MenuPanel.setVisible(false);
				ReserverPanel.setVisible(false);
				FacturePanel.setVisible(false);
			}
			if (e.getSource() == btnReserver){
				ReserverPanel.setVisible(true);
				MenuPanel.setVisible(false);
				TablePanel.setVisible(false);
				FacturePanel.setVisible(false);
			}
			if (e.getSource() == btnFacture){
				FacturePanel.setVisible(true);
				MenuPanel.setVisible(false);
				TablePanel.setVisible(false);
				ReserverPanel.setVisible(false);
				FacturescrollPane = F.CalculerFacture(CodeRes);
				FacturescrollPane.setBounds(25, 22, 706, 200);
				FacturePanel.add(FacturescrollPane);
			}
			if (e.getSource() == btnHome){
				HideFen();
				Acceuil_View A = new Acceuil_View();
				A.show();
			}
		}
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////
    //Passer Reservation
	public class PasserAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			CodeRes = F.passerReservation(dateReservation,tableCombo);
		}	
	}
    //PasserPlate
	public class validerAction implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			F.validerplate(QuantiteTxt,PlateCombo,CodeRes);	
		}	
	}
    //Calculer Prix totale facture	
	public class CalculerPrixAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			F.CalculerPrixTotale(CodeRes, PrixTotale);
		}
	}
	
}
