/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.Burgos.Service;

import cl.Burgos.DAO.DAOProductos;
import cl.Burgos.ENT.ClProductos;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author march
 */
@Stateless
@WebService(serviceName = "ProductosService")
public class ProductosService {
    @PersistenceContext
    EntityManager em;
    
    @WebMethod(action = "getListaPro")
    public List<ClProductos> getListaPro(){
        try {
            return DAOProductos.lista();
        } catch (Exception ex) {
            Logger.getLogger(ProductosService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
