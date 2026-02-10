/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.Burgos.DAO;

import cl.Burgos.BD.BD;
import cl.Burgos.BD.Log;
import cl.Burgos.ENT.ClCompra;
import cl.Burgos.ENT.ClProductos;
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
public class DAOCompra {
    public static boolean sqlInsert(ClCompra cliente){
        try {
            String stSql  = "INSERT INTO compras(";
            stSql += "Login_idLogin, Productos_idProductos) VALUES (";
            stSql += "'" + cliente.getIdLo()+ "'";
            stSql += ",'" + cliente.getIdPro()+ "'";
            stSql += " )";
            return BD.getInstance().sqlEjecutar(stSql);
        } catch (SQLException ex) {
            Log.log(ex.getMessage());
            Logger.getLogger(DAOCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static List<ClProductos> listaCom(ClCompra c) throws Exception {
        List<ClProductos> productoList = new ArrayList<ClProductos>();
        String stSql ="SELECT p.idProductos as idProductos, p.nombre as nombre, p.descripcion as descripcion, p.valor as valor ";
            stSql += " FROM compras c INNER JOIN productos p ON c.Productos_idProductos=p.idProductos ";
            stSql +=" WHERE Login_idLogin=";
            stSql += "'" + c.getIdLo()+ "'";
            try {
                ResultSet result = BD.getInstance().sqlSelect(stSql);
                while (result.next()) {
                    productoList.add(new ClProductos(result.getInt("idProductos"),result.getString("nombre"), result.getString("descripcion"),
                    result.getInt("valor")));
                }
                return productoList;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return productoList;
    }
}
