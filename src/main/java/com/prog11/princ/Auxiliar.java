/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prog11.princ;

import java.util.*;

/**
 *
 * @author Ángel Redondo
 * Para meter mis cosicas
 */
public class Auxiliar {
    
    //Para mostrar el menu principaldonde nos haga falta
    public static int mostrarMenuPrincipal(){
        Scanner input = new Scanner(System.in); 
        final int menuInicio = 1, menuFin = 6;
        int leerMenu;
        boolean menuOk = true;
        
        do{
            if( menuOk == false)
                System.out.println("Debes elegir un elemento que esté en el menu, entre "+ menuInicio + " y " +menuFin);
            
            System.out.println("########################################################");
            System.out.println("#          TAREA 11: Acceso a BBDD                     #");
            System.out.println("#              Pulsa un numero                         #");
            System.out.println("#   1. Insertar Propietarios y Vehiculos DEMO          #");
            System.out.println("#   2. Lanzar pruebas solicitadas                      #");
            System.out.println("#   3. Lanzar GUI                                      #");
            System.out.println("#   4. Salir                                           #");
            System.out.println("########################################################");
            
            leerMenu = input.nextInt();
            
            menuOk = (leerMenu>=menuInicio && leerMenu <=menuFin);
        }while( !menuOk );
        
        return leerMenu;
        
    }
    
}
