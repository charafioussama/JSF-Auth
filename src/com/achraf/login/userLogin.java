package com.achraf.login;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.*;

@ManagedBean(name="userLogin")
@SessionScoped
public class userLogin {
	private String nomutilisateur;
	private String motpasse;
    private String dbnomutilisateur;
    private String dbmotpasse;
	public String getnomutilisateur(){
		return nomutilisateur;
	}
	public String getmotpasse(){
		return motpasse;
	}
	public String getdbnomutilisateur() {
		return dbnomutilisateur;
	}
	public String getdbmotpasse() {
		return dbmotpasse;
	}
	public void setnomutilisateur(String nomutilisateur){
		this.nomutilisateur=nomutilisateur;
	}
	public void setmotpasse(String motpasse){
		this.motpasse=motpasse;
	}
	public void setdbnomutilisateur(String dbnomutilisateur) {
		this.dbnomutilisateur = dbnomutilisateur;
	}
	public void setdbmotpasse(String dbmotpasse) {
		this.dbmotpasse = dbmotpasse;
	}
	Connection conn = null;
	public void validate(String nom){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsfdb", "root", "");
			PreparedStatement ps = conn.prepareStatement("Select * from login where username like ('" + nom +"')");
			ResultSet rs = ps.executeQuery();
			rs.next();
			dbnomutilisateur = rs.getString(1).toString();
            dbmotpasse = rs.getString(2).toString();
            } catch (SQLException ex){
			ex.printStackTrace();
			} catch (ClassNotFoundException e) {
			e.printStackTrace();
		 } finally {
		    try {
		    	conn.close(); 
		    } catch (Exception e) {
		     System.out.println(e);
		    }
		}
	}
	public String login(){
		validate(nomutilisateur);
		if(nomutilisateur.equals(dbnomutilisateur)){
            if(motpasse.equals(dbmotpasse)){
                return "succes";
            }
            else{
            	return "echec";
            }
		}else{
            return "echec";
             }
	}
}
