package Serveur.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import Metier.Function;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Message extends JDialog {

	public JButton button_oui;
	public JButton button_Annuler;
	public int a;
	private JLabel lbl_question = new JLabel();
	JLabel image_Trash = new JLabel();
	private Function F = new Function();
	public Message() {
		setTitle("suppression");
		setVisible(true);
		setBounds(200, 200, 482, 294);
		getContentPane().setBackground(new Color(0, 168, 255));
		getContentPane().setLayout(null);
		
	    button_Annuler = new JButton("Annuler");
	  //si le serveur clique sur Annuler aucune opertion va etre effectuer
	    button_Annuler.addActionListener(new Fermer());
		button_Annuler.setBounds(239, 201, 121, 43);
		getContentPane().add(button_Annuler);
		
		button_oui = new JButton("Oui");
		//si le serveur clique sur oui une suppression de la reservation va etre effectuer
		button_oui.addActionListener(new Suppression());
				
		button_oui.setBounds(108, 201, 121, 43);
		getContentPane().add(button_oui);
		
		lbl_question.setFont(new Font("Microsoft Sans Serif", Font.BOLD | Font.ITALIC, 17));
		lbl_question.setText("Vous voulez vraiment supprimer cette reservation?");
		lbl_question.setForeground(Color.WHITE);
		lbl_question.setBounds(20, 142, 446, 43);
		getContentPane().add(lbl_question);
		image_Trash.setIcon(new ImageIcon(Message.class.getResource("/Serveur/Ressources/trash.png")));
		image_Trash.setBounds(166, 11, 128, 131);
		getContentPane().add(image_Trash);setBackground(new Color(0, 168, 255));
	}
	//Actions 
	class Fermer implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		//fermer le JDialog
    		dispose();
    	}
    }
	class Suppression implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				//connection avec la base de données
				Connection cnx = F.getConnection();
				Statement stmt3 = cnx.createStatement();
				//supprimer la reservation dans la table reservation
				String sql="delete from reservation where CodeRes="+a;
				PreparedStatement stmt = cnx.prepareStatement(sql);
				stmt.executeUpdate();
				dispose();
				cnx.close();
			}catch(Exception ee) {System.out.print(ee);}}
			}
}
