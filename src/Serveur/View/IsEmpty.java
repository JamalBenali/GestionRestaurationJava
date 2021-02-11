package Serveur.View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class IsEmpty extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JLabel lbl_Information = new JLabel("Il faut d'abord remplir tous les champs vides!!");
	JButton btn_Ok = new JButton("Ok");
	public IsEmpty() {
		setTitle("Attention!!");
		setVisible(true);
		setBounds(300, 300, 450, 172);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 191, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		lbl_Information.setForeground(new Color(255, 255, 255));
		lbl_Information.setBackground(new Color(230, 230, 250));
		
		
		lbl_Information.setFont(new Font("Kristen ITC", Font.BOLD | Font.ITALIC, 16));
		lbl_Information.setBounds(10, 27, 414, 39);
		contentPanel.add(lbl_Information);
		
		
		btn_Ok.addActionListener(new Fermer());
		btn_Ok.setBounds(177, 77, 83, 39);
		contentPanel.add(btn_Ok);
	}
	class Fermer implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		//fermer le JDialog
    		dispose();
    	}
    }
}
