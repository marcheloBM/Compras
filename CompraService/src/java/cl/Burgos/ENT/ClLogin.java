/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.Burgos.ENT;

/**
 *
 * @author march
 */
public class ClLogin {
    private int id;
    private String user;
    private String pass;

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public ClLogin(String user, String pass) {
        setUser(user);
        setPass(pass);
    }

    public ClLogin() {
    }

    public ClLogin(int id, String user, String pass) {
        setId(id);
        setUser(user);
        setPass(pass);
    }

    public ClLogin(String user) {
        setUser(user);
    }
    
    
    
    
}
