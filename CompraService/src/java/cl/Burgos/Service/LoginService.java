/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.Burgos.Service;

import cl.Burgos.Entity.Login;
import cl.Burgos.DAO.DAOLogin;
import cl.Burgos.ENT.ClLogin;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author march
 */
@Stateless
@WebService(serviceName = "LoginService")
public class LoginService {
    @PersistenceContext
    EntityManager em;
    
    @WebMethod(action = "getLogin")
    public boolean getLogin(@WebParam(name = "user") String user,@WebParam(name = "pass") String pass){
       ClLogin l = new ClLogin(user, pass);
       return DAOLogin.sqlValidar(l);
    }
    @WebMethod(action = "getIdLogin")
    public String getIdLogin(@WebParam(name = "user") String user){
       ClLogin l = new ClLogin(user);
       return DAOLogin.sqlId(l);
    }
    @WebMethod(action = "getLista2")
    public List<ClLogin> getLista2(){
        try {
            return DAOLogin.Test();
        } catch (Exception ex) {
            Logger.getLogger(LoginService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
