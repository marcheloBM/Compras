/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.Burgos.DAO;

import cl.Burgos.BD.BD;
import cl.Burgos.BD.Log;
import cl.Burgos.ENT.ClLogin;
import cl.Burgos.Entity.Login;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author march
 */
public class DAOLogin {
    
    public static boolean sqlValidar(ClLogin login) {
        String stSql ="SELECT idLogin, user, pass";
            stSql += " FROM Login WHERE ";
            stSql += "user='" + login.getUser()+ "'";
            stSql += " AND pass='" + login.getPass()+ "'";
            
        try {
            ResultSet rs = BD.getInstance().sqlSelect(stSql);
            if(rs==null || !rs.next())return false;
            login.setId(rs.getInt("idLogin")) ;
            login.setUser(rs.getString("user")) ;
            login.setPass(rs.getString("pass")) ;
            return true;
        } catch (Exception e) {
             Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, e);
            Log.log(e.getMessage());
        }
         return false;   
    }
    
    public static String sqlId(ClLogin login){
        String stSql ="SELECT idLogin, user, pass";
            stSql += " FROM Login WHERE ";
            stSql += "user='" + login.getUser()+ "'";
            String id=null;
        try {
            ResultSet rs = BD.getInstance().sqlSelect(stSql);
            if(rs==null || !rs.next())return null;
            id =rs.getString("idLogin");
            return id;
        } catch (Exception e) {
             Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, e);
            Log.log(e.getMessage());
        }
         return id;   
    }
    
    public static String[] listPeopleNames() {
       String stSql ="SELECT idLogin, user, pass";
            stSql += " FROM Login ";
            try {
                ResultSet result = BD.getInstance().sqlSelect(stSql);
                ArrayList list = new ArrayList();
                while (result.next()) {
                    list.add(result.getString("user"));
                    list.add(result.getString("pass"));
                }
                return (String[])list.toArray(new String[list.size()]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        
        return null;
    }
    
    public static List<ClLogin> Test() throws Exception {
        List<ClLogin> personaList = new ArrayList<ClLogin>();
        String stSql ="SELECT idLogin, user, pass";
            stSql += " FROM Login ";
            try {
                ResultSet result = BD.getInstance().sqlSelect(stSql);
                while (result.next()) {
                    personaList.add(new ClLogin(result.getInt("idLogin"),result.getString("user"), result.getString("pass")));
                }
                return personaList;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return personaList;
    }
    
    public static ClLogin[] listaLogin() {
       String stSql ="SELECT idLogin, user, pass";
            stSql += " FROM Login ";
            try {
                ResultSet result = BD.getInstance().sqlSelect(stSql);
                ArrayList list = new ArrayList();
                while (result.next()) {
                    list.add(new ClLogin(result.getString("user"), result.getString("pass")));
                }
                return (ClLogin[]) list.toArray();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        
        return null;
    }
}
