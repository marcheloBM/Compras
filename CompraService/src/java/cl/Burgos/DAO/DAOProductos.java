/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.Burgos.DAO;

import cl.Burgos.BD.BD;
import cl.Burgos.ENT.ClLogin;
import cl.Burgos.ENT.ClProductos;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javafx.scene.image.Image;

/**
 *
 * @author march
 */
public class DAOProductos {
    public static List<ClProductos> lista() throws Exception {
        List<ClProductos> productoList = new ArrayList<ClProductos>();
        String stSql ="SELECT idProductos,nombre,descripcion,valor,imagene";
            stSql += " FROM productos ";
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
