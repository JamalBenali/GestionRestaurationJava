package Serveur.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

import javax.swing.*;

import Acceuil.View.Acceuil_View;

import java.awt.Font;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.Cursor;

public class Se_Connecter extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton Se_connecter;
	private JLabel lbl_login;
	private JLabel lbl_MotDePasse;
	private JSeparator separator;
	private JSeparator separator_1;
	private JLabel imageSeveur;
	private JLabel lblBienvenuSurVotre = new JLabel("Bienvenue sur votre espace serveur");
	private JLabel label_1 = new JLabel("");
	private JLabel lblSeConnecter = new JLabel("Se connecter :");
	private JLabel lblL = new JLabel("");
	private JButton btnHome;


	public Se_Connecter() {
		setTitle("se connecter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 70, 830, 466);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Se_connecter = new JButton("Se Connecter",new ImageIcon(Se_Connecter.class.getResource("/Serveur/Ressources/iconLogin.png")));
		Se_connecter.setBackground(SystemColor.menu);
		Se_connecter.setBounds(522, 282, 187, 44);
		contentPane.add(Se_connecter);
		
		textField = new JTextField();
		textField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		textField.setBackground(new Color(255, 255, 255));
		textField.setBounds(511, 164, 221, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lbl_login = new JLabel("Login");
		lbl_login.setBounds(511, 148, 46, 14);
		contentPane.add(lbl_login);
		
		lbl_MotDePasse = new JLabel("Password");
		lbl_MotDePasse.setBounds(511, 208, 70, 14);
		contentPane.add(lbl_MotDePasse);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(255, 255, 255));
		passwordField.setBounds(511, 224, 221, 28);
		passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(passwordField);
		
		separator = new JSeparator();
		separator.setBounds(511, 194, 221, 2);
		contentPane.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(511, 255, 221, 2);
		contentPane.add(separator_1);
		
		imageSeveur = new JLabel("");
		imageSeveur.setBounds(116, 8, 241, 258);
		contentPane.add(imageSeveur);
		imageSeveur.setVerticalAlignment(SwingConstants.TOP);
		imageSeveur.setIcon(new ImageIcon(Se_Connecter.class.getResource("/Serveur/Ressources/Serveur icon.png")));
		 
		
		lblBienvenuSurVotre.setBounds(41, 277, 403, 18);
		contentPane.add(lblBienvenuSurVotre);
		lblBienvenuSurVotre.setForeground(new Color(255, 250, 250));
		lblBienvenuSurVotre.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 18));
		
		label_1.setIcon(new ImageIcon(Se_Connecter.class.getResource("/Serveur/Ressources/ArrierePlan.PNG")));
		label_1.setBounds(0, 0, 501, 434);
		contentPane.add(label_1);
		
		lblSeConnecter.setFont(new Font("Lucida Fax", Font.PLAIN, 24));
		lblSeConnecter.setBounds(511, 81, 187, 44);
		contentPane.add(lblSeConnecter);
		
		lblL.setBounds(514, 262, 210, 14);
		contentPane.add(lblL);
		
		btnHome = new JButton("");
		btnHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHome.setIcon(new ImageIcon(Se_Connecter.class.getResource("/Serveur/Ressources/Home.png")));
		btnHome.setBounds(740, 353, 64, 64);
		btnHome.setBackground(SystemColor.textHighlightText);
		btnHome.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		btnHome.addActionListener(new ActionHome());
		contentPane.add(btnHome);
		//button se connecter :
		Se_connecter.addActionListener(new seConnecter());
		setVisible(true);
	}
	//Fonction qui retourne la connection 
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost/restaurant","root","");
		return cnx;
	}
	//l'action de la button se Connecter
	public class seConnecter implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				//la connection avec la base de donnée
				Connection cnx = getConnection();
				Statement stmt = cnx.createStatement();
				String sql="select * from serveur where login='"+textField.getText().toString()+"' and password='"+passwordField.getText().toString()+"'";
				ResultSet rs = stmt.executeQuery(sql);
				EspServeur f;
				if(rs.next()) {
					dispose();
					//si le login et mot de passe existent on va ouvrir une nouvelle fenetre qui porte dans son titre le login du serveur
					f= new EspServeur();
					f.setTitle(textField.getText().toString());
					f.a=textField.getText().toString();}
				//si nn on va afficher que lelogin et mot de passe sont non valide
				else lblL.setText("login ou mot de passe non valide!!");
				//fermer la connection:
				cnx.close();
				rs.close();
			}catch(Exception e) {System.out.print(e);}
		}
	}
	public class ActionHome implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			HideFen();
			Acceuil_View A = new Acceuil_View();
			A.show();
		}
	}
	public void HideFen(){
		this.hide();
	}
}
