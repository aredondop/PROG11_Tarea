/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.prog11.princ;

import java.util.*;
import com.prog11.bbdd.*;

/**
 *
 * @author Ángel Redondo
 */
public class PROG11_Tarea {
    
    static Scanner input = new Scanner(System.in); 

    public static void main(String[] args) {
        ConnectionDB con = new ConnectionDB();
        input.useDelimiter("\n");
        input.useLocale(Locale.US);

        boolean salir = false;
        while(salir == false){
            switch( Auxiliar.mostrarMenuPrincipal() ){
                case 1:
                    System.out.println("Vamos a insertar datos de prueba");
                    
                    PropietariosDAO.insertarPropietario( con, 1, "Antonio Vicente Mondéjar Soler", "00000001R");
                    VehiculosDAO.insertarNuevoVehiculo(con, "0000AAA", "Renault", 100000, 2500, "Renault Megane Rojo del año 2016 en excelente estado", 1);
                    VehiculosDAO.insertarNuevoVehiculo(con, "0000AAB", "Alpine", 1000, 55000, "Alpine/Renault A110 Azul del año 2022. Deportivo", 1);
                    
                    PropietariosDAO.insertarPropietario( con, 2, "Alfonso García Tolosa", "00000002W");
                    VehiculosDAO.insertarNuevoVehiculo(con, "0000AAC", "Toyota", 100000, 2500, "Toyota Avensis del 2008 blanco, diesel", 2);
                    VehiculosDAO.insertarNuevoVehiculo(con, "0000AAD", "Ford", 1000, 55000, "Ford Mustang del 69",2);
                    
                    PropietariosDAO.insertarPropietario( con, 3, "Guadalupe Montoya Julián", "00000003A");
                    VehiculosDAO.insertarNuevoVehiculo(con, "0000AAE", "Fiat", 100000, 2500, "Fiat Dobló Azul del 2005", 3);
                    VehiculosDAO.insertarNuevoVehiculo(con, "0000AAF", "Plymouth", 1000, 55000, "Plymouth Fury rojo del 59", 3);
                break;
                    
                case 2:
                    System.out.println("Lanzando pruebas solicitadas");
                                        
                    // 1. Insertar varios propietarios y vehiculos
                    System.out.println("Insertar varios propietarios y vehiculos.");

                    if(PropietariosDAO.insertarPropietario(con, 10, "propietario 10", "11111110V") == 0)
                        System.out.println("Creado el propietario 10");
                    else
                        System.out.println("Fallo al cerar el propietario 10");
                    
                    if(PropietariosDAO.insertarPropietario(con, 11, "propietario 11", "11111111H") == 0)
                        System.out.println("Creado el propietario 11");
                    else
                        System.out.println("Fallo al cerar el propietario 11");
                    
                    if (VehiculosDAO.insertarNuevoVehiculo(con, "9999AAA", "Lamborghini", 4200, 150000, "Lamborghini Countach Negro. Ideal Inversion", 10) == 0)
                        System.out.println("Creado el vehiculo 9999AAA");
                    else
                        System.out.println("No se ha podido crear el vehiculo 9999AAA");
                    
                    if (VehiculosDAO.insertarNuevoVehiculo(con, "9999AAB", "DeLorean", 15000, 450000, "DeLorean DMC12. Ideal Inversion", 11) == 0)
                        System.out.println("Creado el vehiculo 9999AAB");
                    else
                        System.out.println("No se ha podido crear el vehiculo 9999AAB");
                    
                    // 2. Listar todos los vehiculos
                    listarVehiculos();
                    
                    // 3. Actualizar propietario de vehiculo
                    System.out.println("Actualizar propietario de un vehiculo.");

                    if (VehiculosDAO.actualizarVehiculo(con, "9999AAA", 11) == 0)
                        System.out.println("Vehiculo con matricula 9999AAA ha cambiado al propietario con id 11");
                    else
                        System.out.println("No se ha podido cambiar el propietario");

                    // 4. Listar todos los Vehiculos
                    listarVehiculos();
                    
                    // 5. y 6. Eliminar un vehiculos que exista y uno que no exista
                    borrarVehiculos();
                    
                    // 7. Listar todos los Vehiculos
                    listarVehiculos();
                    
                    // 8. Listar todos los Vehiculos de una marca
                    listarVehiculosMarca( "Lamborghini");
                    
                    // 9. Listar todos los Vehiculos de un propietario
                    listarVehiculosPropietario( "11111110V");
                    
                    // 10. y 11. Eliminar un vehiculos que exista y uno que no exista
                    borrarPropietarios();
        
                break;
                    
                case 3:
                    System.out.println("Lanzando la GUI...");
                    Main.main(null);
                break;
                    
                default:
                case 4:
                    System.out.println("Hasta Pronto");
                    salir = true;
                break;
            }
        }
                    
                    
    }
    
    //Funciones auxiliares. En el main, por las dependencias
    private static void listarVehiculos(){
        System.out.println("Listar todos los vehículos");
        
        ConnectionDB con = new ConnectionDB();
        ArrayList<String> vehiculos = new ArrayList<>();
        vehiculos = VehiculosDAO.listarVehiculos(con);
        
        for (String v : vehiculos) {
            System.out.println(v);
        }
    }
    
    private static void listarVehiculosMarca( String marca){
        System.out.println("Listar todos los vehículos de la marca " + marca);
        
        ConnectionDB con = new ConnectionDB();
        ArrayList<String> vehiculos = new ArrayList<>();
        vehiculos = VehiculosDAO.listarVehiculosMarca(con, marca);
        
        for (String v : vehiculos) {
            System.out.println(v);
        }
    }
    
    private static void listarVehiculosPropietario( String dni){
        System.out.println("Listar todos los vehículos del dni " + dni);
        
        ConnectionDB con = new ConnectionDB();
        ArrayList<String> vehiculos = new ArrayList<>();
        vehiculos =PropietariosDAO.recuperarVehiculos(con, dni);
        
        for (String v : vehiculos) {
            System.out.println(v);
        }
        
    }
    
    private static void borrarPropietarios(){
        System.out.println("Eliminar un propietario con vehiculos y uno sin vehiculos");
        
        ConnectionDB con = new ConnectionDB();
        ArrayList<String> propietarios = new ArrayList<String>();

        propietarios.add("11111111H");
        propietarios.add("11111110V");
                    
        for (int i = 0; i < propietarios.size(); i++) {
                        
            if ( PropietariosDAO.eliminarPropietario(con, propietarios.get(i)) == 0)
                System.out.println("Se ha eliminado el propietario con dni "+ propietarios.get(i));
            else
                System.out.println("No se ha podido eliminar el propietario");
                        
        }                
    }
    
    private static void borrarVehiculos(){
            System.out.println("Eliminar un vehiculo que exista y uno que no exista");

            ConnectionDB con = new ConnectionDB();
            ArrayList<String> matriculas = new ArrayList<String>();

            matriculas.add("9999AAB");
            matriculas.add("9999ZZZ");

            for (int i = 0; i < matriculas.size(); i++) {

                if (VehiculosDAO.eliminarVehiculo(con, matriculas.get(i)) == 0)
                    System.out.println("Se ha eliminado el vehiculo con matricula "+ matriculas.get(i));
                else
                    System.out.println("No se ha podido eliminar el vehiculo");

            }

    
                    


    }
    
    
}
