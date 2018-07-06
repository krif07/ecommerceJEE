/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.cbg.ecjee.model;

import com.co.cbg.ecjee.dao.Categoria;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author krif
 */
public class CategoriaModel {
    
    public static ArrayList<Categoria> listar(){
        String sql = "{call sp_listarCategoriaSuperior()}";
                
        try {
            Connection c = Conexion.conectar();
            CallableStatement sentencia = (CallableStatement) c.prepareCall(sql);
            ResultSet resultado = sentencia.executeQuery();
            
            ArrayList<Categoria> lista = new ArrayList<>();
            
            while(resultado.next()){
                Categoria categoria = new Categoria();
                categoria.setCodigo(resultado.getInt("codigo"));
                categoria.setNombre(resultado.getString("nombre"));
                //categoria.setVisible(resultado.getBoolean("visble"));
                lista.add(categoria);
            }
            
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
