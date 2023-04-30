/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prog11.bbdd;

//import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ángel Redondo
 */
public class ConnectionDB {
    private Connection conexion;
    
    String servidorBD = "localhost", puertoBD="3306", usuarioBD="root", passwordBD="", esquemaBD="concesionario";
    
    public void openConnection() throws SQLException{
        
        /**
         * Esta parte sería perfectamente cambiable por datos de persistencia
         */
        this.conexion = DriverManager.getConnection("jdbc:mariadb://"+servidorBD+":"+puertoBD+"/"+esquemaBD, usuarioBD, passwordBD);
        //this.conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/concesionario", "root", "");
    }
    
    public void closeConnection() throws SQLException{
        this.conexion.close();
    }
    
    public Connection getConnection(){
        return this.conexion;
    }
    
}
