/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.Burgos.Service;

import cl.Burgos.DAO.DAOCompra;
import cl.Burgos.ENT.ClCompra;
import cl.Burgos.ENT.ClProductos;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author march
 */
@WebService(serviceName = "CompraService")
public class CompraService {

    @PersistenceContext
    EntityManager em;
    
        @WebMethod(action = "crearCompra")
    public boolean crearCompra(@WebParam(name = "idLo") int id, @WebParam(name = "idPro") int idPro){
        ClCompra c= new ClCompra();
        c.setIdLo(id);
        c.setIdPro(idPro);
        return DAOCompra.sqlInsert(c);
    }
    
    @WebMethod(action = "getListaCom")
    public List<ClProductos> getListaCom(@WebParam(name = "idLo") int id){
        try {
            ClCompra c = new ClCompra();
            c.setIdLo(id);
            return DAOCompra.listaCom(c);
        } catch (Exception ex) {
            Logger.getLogger(CompraService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
