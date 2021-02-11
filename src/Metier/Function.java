package InterfaceClient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.*;

import InterfaceServeur.IsEmpty;
import InterfaceServeur.Message;

public class Function {
	public Connection getConnection(){
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return con;
	}
	public int passerReservation(JDateChooser dateReservation,JComboBox tableCombo){
		int CodeRes = 0;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (dateReservation.getDate().before(new Date()))
		{
			JOptionPane.showMessageDialog(null,"Choisir une date aprés aujourd'hui");
		}
		else {
		dateReservation.setDateFormatString("yyyy-MM-dd");
		Connection con = getConnection();
		Statement st = null;
		ResultSet rs = null;
		String query = "select IDSer from Serveur where actif = '+' ";
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			rs.next();
			String IDSer = rs.getString(1).toString();
			st = con.createStatement();
			query = "INSERT INTO `reservation`(`DateRes`, `NumTable`, `IDSer`) VALUES ('" + df.format(dateReservation.getDate()) +"'," + tableCombo.getSelectedItem().toString() + "," + IDSer + ")";
			PreparedStatement PT = con.prepareStatement(query);
			PT.execute();
			query = "select max(codeRes) from reservation";
			st = con.createStatement();
			rs = st.executeQuery(query);
			rs.next();
			CodeRes = Integer.parseInt(rs.getString(1).toString());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e);
		} finally {
    		try {
  			//fermer la connexion
    		con.close();
   			st.close();
   			rs.close();
    		} catch (SQLException ex) {
    			JOptionPane.showMessageDialog(null, ex);
    		}		
     	}
		}
		return CodeRes;
	}
	public void validerplate(JTextField QuantiteTxt,JComboBox PlateCombo,int CodeRes){
		Connection con = null;
		Statement st = null;
		if (QuantiteTxt.getText().isEmpty())
			JOptionPane.showMessageDialog(null, "Remplir tous les champs !!");
		else if (CodeRes == 0)
			JOptionPane.showMessageDialog(null, "Choisir tous D'abord une table");
		else {
		try {
			con = getConnection();
			st = con.createStatement();
			String query = "INSERT INTO `qtereservee`(`CodeRes`, `CodePlat`, `Qte`) VALUES ("+ CodeRes +","+ PlateCombo.getSelectedItem().toString() + "," + QuantiteTxt.getText() + ")";
			PreparedStatement PT = con.prepareStatement(query);
			PT.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e);
		} finally {
    		try {
  			//fermer la connexion
    		con.close();
   			st.close();
    		} catch (SQLException ex) {
    			JOptionPane.showMessageDialog(null, ex);
    		}		
     	}
		}
	}  
	public void CalculerPrixTotale(int CodeRes,JTextField PrixTotale){
		Connection con = null; 
   		Statement st = null;
    	String query = null;
   		ResultSet rs = null;		
    	try{
   			// connexion avec la base de donnes
    		con = getConnection();
    		st = con.createStatement();
    		query = "select sum(p.prixplate*qte) from plate p,qtereservee q where p.codeplat = q.codeplat and q.codeRes = " + CodeRes;
    		rs = st.executeQuery(query);
    		rs.next();
    		PrixTotale.setText(rs.getString(1));
    	} catch(Exception ex) {
    		JOptionPane.showMessageDialog(null, ex);
    	} finally {
    		try {
  			//fermer la connexion
    		con.close();
   			st.close();
    		rs.close();
    		} catch (SQLException ex) {
    			JOptionPane.showMessageDialog(null, ex);
    		}		
     	}
	}
	public JScrollPane RemplirData(String TableName){
		//pour connecter a la base de donnes 
		Connection con = null; 
		Statement st = null;
		String query = null;
		ResultSet rs = null;
		JScrollPane scrollPane=null;
		try{
			// connexion avec la base de donnes
			con = getConnection();
			st = con.createStatement();
			query = "select * from " + TableName;
			rs = st.executeQuery(query);
			ResultSetMetaData rsmt = rs.getMetaData();
			int l = 0,i=0;
			// l = nombre de lignes
			while (rs.next()) l++;
			// c = nombre de colonnes
			int c = rsmt.getColumnCount();
			// remplir autre fois ResultSet (il est devenue vide apres le count de nombre de lignes)
			rs = st.executeQuery(query);
			//tableau de donnes
			Object data[][] = new String[l][c];
			//nom de colonnes
			Object col[] = new String[c];
			for (int ii=1; ii<=c; ii++){
				col[ii-1]=rsmt.getColumnName(ii);
			}
			//remplir tableau
			while (rs.next()){
				for (int j=1; j<=c; j++){
					data[i][j-1] = rs.getString(j).toString();
				}
				i++;
			}
			//ajout des donnes dans PlateTable
			DefaultTableModel model = new DefaultTableModel(data,col);
			JTable Table = new JTable(model);
			scrollPane = new JScrollPane(Table);
		} catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
		finally {
			try {
				//fermer la connexion
				con.close();
				st.close();
				rs.close();
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, ex);
			}
			
		}
    return scrollPane;
	}
	public void RemplirCombo(String TableName,JComboBox<String> Combo){
    	//pour connecter a la base de donnes 
    	Connection con = null; 
   		Statement st = null;
    	String query = null;
   		ResultSet rs = null;
    			
    	try{
   			// connexion avec la base de donnes
    		con = getConnection();
    		st = con.createStatement();
    		if (TableName == "tables")
    			query = "select * from tables where NumTable not in (select Numtable from reservation)";
    		else query = "select * from " + TableName;
    		rs = st.executeQuery(query);
    		//remplir tableau
    		while (rs.next()){
    			Combo.addItem(rs.getString(1));
    		}
    	} catch(Exception ex) {
    		JOptionPane.showMessageDialog(null, ex);
    	} finally {
    		try {
  			//fermer la connexion
    		con.close();
   			st.close();
    		rs.close();
    		} catch (SQLException ex) {
    			JOptionPane.showMessageDialog(null, ex);
    		}		
     	}    	
    }
	public JScrollPane CalculerFacture(int CodeRes){
    	//pour connecter a la base de donnes 
    	Connection con = null; 
   		Statement st = null;
  		String query = null;
   		ResultSet rs = null;
  		JScrollPane scrollPane=null;
    			
   		try{
    		// connexion avec la base de donnes
    		con = getConnection();
			st = con.createStatement();
    		query = "select p.NomPlate,p.PrixPlate as PrixUnite,q.Qte,(p.prixPlate*q.Qte) as PrixTotale from plate p,qtereservee q where p.CodePlat = q.CodePlat and q.CodeRes = " + CodeRes;
    		rs = st.executeQuery(query);
    		ResultSetMetaData rsmt = rs.getMetaData();
   			int l = 0,i=0;
    		// l = nombre de lignes
    		while (rs.next()) l++;
    		// c = nombre de colonnes
   			int c = rsmt.getColumnCount();
    		// remplir autre fois ResultSet (il est devenue vide apres le count de nombre de lignes)
    		rs = st.executeQuery(query);
    		//tableau de donnes
    		Object data[][] = new String[l][c];
    		//nom de colonnes
   			Object col[] = new String[c];
    		for (int ii=1; ii<=c; ii++){
    			col[ii-1]=rsmt.getColumnName(ii);
   			}
    		//remplir tableau
   			while (rs.next()){
   				for (int j=1; j<=c; j++){
    				data[i][j-1] = rs.getString(j).toString();
  				}
    			i++;
   			}
    		//ajout des donnes dans PlateTable
    		DefaultTableModel model = new DefaultTableModel(data,col);
    		JTable Table = new JTable(model);
    		scrollPane = new JScrollPane(Table);
    		} catch(Exception ex) {
    			JOptionPane.showMessageDialog(null, ex);
    		}
    		finally {
    			try {
    				//fermer la connexion
    				con.close();
    				st.close();
    				rs.close();
    			} catch (SQLException ex) {
    				JOptionPane.showMessageDialog(null, ex);
   			} 				
    	}
     return scrollPane;
    }
	public boolean verification_existanceRes(JTextField textField)
	{ 
		try {
		Connection cnx = getConnection();
		Statement stmt = cnx.createStatement();
		String sql="select CodeRes from reservation where CodeRes="+Integer.parseInt(textField.getText());
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next())return true;
		else return false;
		}
	catch(Exception ee) {System.out.print(ee);}
		return false;
	}
	public void CalculerSupression(JTextField textField,JButton btn_calcul_supp,JLabel lblLePrix){
		if(textField.getText().isEmpty()) {
			//si le TextField est vide on va afficher un JDialog qui informe de remplir tous les champs vides
			IsEmpty i = new IsEmpty();
		}
		else if(verification_existanceRes(textField)==false)
		{
			IsEmpty i;
			i = new IsEmpty();
			i.lbl_Information.setText("Aucune réservation existe avec ce CodeRes!!");
		}
		else {
		// si le texte est : calculer alors la boutton va jouer le role de calculation 
		if("Calculer"==btn_calcul_supp.getText().toString())
			{
					try {
						//la connection avec la base de donnée
						Connection cnx = getConnection();
						Statement stmt = cnx.createStatement();
						lblLePrix.setVisible(true); 
						// selectionner la somme de quantite*prix des plas reserver dans une reservation donnée
						String sql="select sum(Qte*PrixPlate) from QteReservee Q,Plate P where Q.codePlat=P.codePlat and Q.CodeRes="+Integer.parseInt(textField.getText());
						ResultSet rs = stmt.executeQuery(sql);
						rs.next();
						//afficher le prix dans une label
						lblLePrix.setText(lblLePrix.getText()+" "+rs.getString("sum(Qte*PrixPlate)")+" DH");
						//fermeture de la connection
						cnx.close();
						//vider le TextField
						textField.setText("");
					}catch(Exception ee) {System.out.print(ee);}
			}
		// si le texte est : supprimer alors la boutton va jouer le role de suppression
		else if("Supprimer"==btn_calcul_supp.getText().toString()) {
			//afficher une fenetre pour verifier si le serveur veut vraiment supprimer la reservation
			Message j = new Message();
			j.a=Integer.parseInt(textField.getText());
			textField.setText("");
		
		}	
	}
	}
	public void Consulter(JTable table,String a){
		table.setModel(new DefaultTableModel(null,new String[]{"codeRes","numTable","CodePlat","qte"}));
		String[] columnNames = {"codeRes","numTable","CodePlat","qte"};
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.setColumnIdentifiers(columnNames);
		table.setVisible(true);
		try {
			//la connection avec labase de données
			Connection cnx = getConnection();
			Statement stmt = cnx.createStatement();
			//obtenir les reservation d'un serveur donné avec ses plats reservés
			String sql="select a.CodeRes,a.NumTable,b.CodePlat,b.Qte from reservation a,qtereservee b,serveur c where a.coderes=b.coderes and c.login='"+a+"' and a.IdSer=c.idser";
			ResultSet rs = stmt.executeQuery(sql);
			model.addRow(new Object[]{"           CodeRes","          NumTable","           CodePlat","          QTE"});
			//remplir le JTable
			while(rs.next()) {
	            String q = rs.getString("CodeRes");
	            String qq = rs.getString("NumTable");
	            String qqq = rs.getString("CodePlat");
	            String qqqq = rs.getString("Qte");
	            model.addRow(new Object[]{q,qq,qqq,qqqq});
			}
			//fermeture de la connection
			cnx.close();
		}catch(Exception e) {System.out.print(e);}
		table.setModel(model);
		model.fireTableDataChanged();
	}
}
