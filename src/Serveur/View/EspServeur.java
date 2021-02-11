package Serveur.View;

import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JSeparator;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

import Metier.Function;


public class EspServeur extends JFrame {

	private JPanel contentPane;	
	private JTextField textField;
	private JTable table;
	public String a ;
	private JButton button_Supprimer = new JButton("",new ImageIcon(EspServeur.class.getResource("/Serveur/Ressources/butt_Supprimer.png")));
	private JButton Button_Consulter = new JButton("",new ImageIcon(EspServeur.class.getResource("/Serveur/Ressources/butt_Consulter.png")));
	private JPanel panel = new JPanel();
	private JSeparator separator = new JSeparator();
	private JSeparator separator_1 = new JSeparator();
	private JSeparator separator_2 = new JSeparator();
	private JLabel lblChoisissezUneOperation = new JLabel("      Choisissez une op\u00E9ration:");
	private JLabel lblSettings = new JLabel("  Settings");
	private JSeparator separator_3 = new JSeparator();
	private JButton btn_calcul_supp = new JButton("New button");
	private JLabel lblLePrix = new JLabel("Le prix :");
	private JLabel suppression = new JLabel("");
	private JLabel consulter = new JLabel("");
	private JLabel calcul = new JLabel("");
	private JLabel label = new JLabel("");
	private JButton button_Calculer = new JButton("",new ImageIcon(EspServeur.class.getResource("/Serveur/Ressources/butt_Calcule.png")));
	private JButton btn_deconnection = new JButton("");
	private Function F = new Function();
	public EspServeur() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 0, 965, 702);
		consulter.setVisible(false);
		calcul.setVisible(false);
		lblLePrix.setVisible(false);
		btn_calcul_supp.setVisible(false);
		lblLePrix.setVisible(false);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlightText);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		button_Supprimer.setBackground(SystemColor.textHighlightText);
		button_Supprimer.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		button_Supprimer.setBounds(581, 272, 276, 135);
		contentPane.add(button_Supprimer);
	
		Button_Consulter.setBackground(SystemColor.textHighlightText);
		Button_Consulter.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		Button_Consulter.setBounds(581, 99, 276, 135);
		contentPane.add(Button_Consulter);
		
		panel.setBackground(new Color(37, 204, 247));
		panel.setBounds(0, 0, 486, 663);
		contentPane.add(panel);
		panel.setLayout(null);
		
		separator.setBounds(387, 428, -317, -10);
		panel.add(separator);
		
		
		separator_1.setBounds(152, 451, 200, -4);
		panel.add(separator_1);
		
		
		separator_2.setBounds(102, 396, 291, 11);
		panel.add(separator_2);
		
		
		lblChoisissezUneOperation.setForeground(SystemColor.window);
		lblChoisissezUneOperation.setFont(new Font("Kristen ITC", Font.ITALIC, 28));
		lblChoisissezUneOperation.setBounds(10, 428, 486, 60);
		panel.add(lblChoisissezUneOperation);
		
		lblSettings.setFont(new Font("Tahoma", Font.ITALIC, 26));
		lblSettings.setForeground(SystemColor.window);
		lblSettings.setBounds(177, 396, 175, 32);
		panel.add(lblSettings);
		
		
		separator_3.setBounds(177, 428, 136, 12);
		panel.add(separator_3);
		
		textField = new JTextField();
		textField.setBounds(119, 487, 257, 32);
		panel.add(textField);
		textField.setColumns(10);
		
		btn_calcul_supp.setBounds(177, 528, 136, 42);
		panel.add(btn_calcul_supp);
		
		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setBackground(new Color(255, 255, 240));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"codeRes", "numTable", "CodePlat", "qte"
			}
		));
		table.setBounds(21, 439, 446, 187);
		panel.add(table);
		
		lblLePrix.setForeground(new Color(255, 255, 255));
		lblLePrix.setFont(new Font("MV Boli", Font.BOLD | Font.ITALIC, 30));
		lblLePrix.setBounds(76, 581, 347, 45);
		panel.add(lblLePrix);
		
		
		consulter.setIcon(new ImageIcon(EspServeur.class.getResource("/Serveur/Ressources/iconConsultation.png")));
		consulter.setBounds(134, 88, 289, 277);
		panel.add(consulter);
		
		
		calcul.setBounds(102, 44, 429, 321);
		panel.add(calcul);
		calcul.setIcon(new ImageIcon(EspServeur.class.getResource("/Serveur/Ressources/Icon_Calcul.png")));
		
		
		label.setBounds(10, -22, 461, 418);
		panel.add(label);
		label.setIcon(new ImageIcon(EspServeur.class.getResource("/Serveur/Ressources/Settings-icon.png")));
		suppression.setBounds(120, 68, 347, 309);
		panel.add(suppression);
		suppression.setVisible(false);
		
		
		suppression.setIcon(new ImageIcon(EspServeur.class.getResource("/Serveur/Ressources/iconDelete.png")));
		
		button_Calculer.setBounds(581, 460, 276, 135);
		contentPane.add(button_Calculer);
		button_Calculer.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		button_Calculer.setBackground(SystemColor.textHighlightText);
		
		
		btn_deconnection.addActionListener(new Fermer());
		btn_deconnection.setIcon(new ImageIcon(EspServeur.class.getResource("/Serveur/Ressources/butt_logOut.png")));
		btn_deconnection.setBackground(SystemColor.textHighlightText);
		btn_deconnection.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		btn_deconnection.setBounds(850, 11, 89, 62);
		contentPane.add(btn_deconnection);
		
		button_Calculer.addActionListener(new Calculer());
		//cette button vas nous permettre d'afficher les reservation d'un serveur
		Button_Consulter.addActionListener(new Consultation());
		button_Supprimer.addActionListener(new Suppression());
		//cette button va jouer deux role selon son texte
		btn_calcul_supp.addActionListener(new CalculSuppression());

		table.setVisible(false);		
		textField.setVisible(false);
		
	} 
	//---------------------------------------------------------Les actions-----------------------------------------------------------------
	class CalculSuppression implements  ActionListener {
		public void actionPerformed(ActionEvent e) {
			F.CalculerSupression(textField, btn_calcul_supp, lblLePrix);
		}
	}
	class Suppression implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			label.setVisible(false);
			consulter.setVisible(false);
			calcul.setVisible(false);
			suppression.setVisible(true);
			textField.setText("");
			lblChoisissezUneOperation.setVisible(true);
			table.setVisible(false);
			lblLePrix.setVisible(false);
			lblSettings.setText("Suppression");
			lblChoisissezUneOperation.setText("Entrez le code de la réservation:");
			textField.setVisible(true);
			btn_calcul_supp.setVisible(true);
			btn_calcul_supp.setText("Supprimer");
		}
	}
	class Consultation implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			label.setVisible(false);
			suppression.setVisible(false);
			calcul.setVisible(false);
			consulter.setVisible(true);
			table.setVisible(true);
			lblLePrix.setVisible(false);
			lblSettings.setText("Réservation");
			textField.setVisible(false);
			btn_calcul_supp.setVisible(false);
			lblChoisissezUneOperation.setVisible(false);
			F.Consulter(table, a);
		}
	}
	class Calculer implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			label.setVisible(false);
			consulter.setVisible(false);
			suppression.setVisible(false);
			calcul.setVisible(true);
			textField.setText("");
			lblChoisissezUneOperation.setVisible(true);
			table.setVisible(false);
			lblSettings.setText("     Tarif");
			lblChoisissezUneOperation.setText("Entrez le code de la réservation:");
			textField.setVisible(true);
			btn_calcul_supp.setVisible(true);
			btn_calcul_supp.setText("Calculer");
		}
	}
	class Fermer implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Se_Connecter c = new Se_Connecter();
			dispose();
		}
	}
}
