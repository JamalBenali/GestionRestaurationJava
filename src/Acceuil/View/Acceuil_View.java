package InterfaceClient;

import java.awt.EventQueue;

import javax.swing.*;

import Espaceadmin.EspaceAdmin;
import InterfaceServeur.Se_Connecter;
import InterfaceClient.Client_View;

import java.awt.*;
import java.awt.event.*;

public class Acceuil_View extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Acceuil_View frame = new Acceuil_View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	private JLabel Background;
	private JLabel Admin;
	private JLabel Client;
	private JLabel Serveur;
	
	public Acceuil_View() {
		initComponents();
	}
	
	public void initComponents(){
		setTitle("Restaurant");
		getContentPane().setBackground(new Color(232, 234, 233));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 70, 1067, 630);
		getContentPane().setLayout(null);
		
		Background = new JLabel();
		Background.setIcon(new ImageIcon(Acceuil_View.class.getResource("/image/Acceuil_Background.png")));
		Background.setBounds(0,0,533,600);
		
		Admin = new JLabel();
		Admin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Admin.setIcon(new ImageIcon(Acceuil_View.class.getResource("/image/E_Admin.png")));
		Admin.setBounds(600,41,400,170);
		Admin.addMouseListener(new LabelAction());
		
		Serveur = new JLabel();
		Serveur.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Serveur.setIcon(new ImageIcon(Acceuil_View.class.getResource("/image/E_Serveur.png")));
		Serveur.setBounds(600,207,400,170);
		Serveur.addMouseListener(new LabelAction());
		
		Client = new JLabel();
		Client.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Client.setIcon(new ImageIcon(Acceuil_View.class.getResource("/image/E_Client.png")));
		Client.setBounds(600,377,400,170);
		Client.addMouseListener(new LabelAction());
		
		getContentPane().add(Background);
		getContentPane().add(Admin);
		getContentPane().add(Serveur);
		getContentPane().add(Client);
	}
	public void HideFen(){
		this.hide();
	}

	public class LabelAction extends MouseAdapter{
		public void mouseEntered(MouseEvent e) {
			if (e.getSource() == Admin){
				Admin.setIcon(new ImageIcon(Acceuil_View.class.getResource("/image/E_Admin_Pressed.png")));
			}
			if (e.getSource() == Client){
				Client.setIcon(new ImageIcon(Acceuil_View.class.getResource("/image/E_Client_Pressed.png")));
			}
			if (e.getSource() == Serveur){
				Serveur.setIcon(new ImageIcon(Acceuil_View.class.getResource("/image/E_Serveur_Pressed.png")));
			}
		}

		public void mouseExited(MouseEvent e) {
			if (e.getSource() == Admin){
				Admin.setIcon(new ImageIcon(Acceuil_View.class.getResource("/image/E_Admin.png")));
			}
			if (e.getSource() == Client){
				Client.setIcon(new ImageIcon(Acceuil_View.class.getResource("/image/E_Client.png")));
			}
			if (e.getSource() == Serveur){
				Serveur.setIcon(new ImageIcon(Acceuil_View.class.getResource("/image/E_Serveur.png")));
			}
		}
		public void mouseClicked(MouseEvent e){
			if (e.getSource() == Client){
				Client_View CV = new Client_View();
				CV.show();
				HideFen();
			}
			if (e.getSource() == Serveur){
				Se_Connecter CV = new Se_Connecter();
				CV.show();
				HideFen();
			}
			if (e.getSource() == Admin){
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						//splashScreen();
						EspaceAdmin f = new EspaceAdmin();
					} catch (Exception e) {	
						e.printStackTrace();
					}
				}
			});
			}
		}
	}
}
