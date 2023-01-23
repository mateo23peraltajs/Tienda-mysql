/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda;

import java.util.Scanner;
import tienda.persistencia.DAO_Fabricante;
import tienda.persistencia.DAO_Producto;
import tienda.servicios.fabricanteService;
import tienda.servicios.productoService;

/**
 *
 * @author Fede
 */
public class Tienda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        DAO_Producto DaoP = new DAO_Producto();
        DAO_Fabricante DaoF = new DAO_Fabricante();
        fabricanteService servF = new fabricanteService();
        productoService servP = new productoService();
        
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        System.out.println("ingresando a la tabla de la tienda");

        bucleMenu:
        do {
            System.out.println("ingrese que desea hacer");
            System.out.println
                     ("1)listado de todos los productos por nombre" + "\n"
                    + "2)nombres y precios de todos los productos" + "\n"
                    + "3)productos con un precio entre 120 y 202" + "\n"
                    + "4)portatiles en la tabla producto" + "\n"
                    + "5)nombre y precio del producto mas barato" + "\n"
                    + "6)ingresar un producto a la base de datos" + "\n"
                    + "7)ingresar un fabricante a la base de datos" + "\n"
                    + "8)editar un producto" + "\n"
                    + "9)Eliminar producto" + "\n"
                    + "10)Eliminar fabricante" + "\n"
                    + "11) finalizar operacion");
            switch (leer.nextInt()) {
                case 1:
                    DaoP.listaProductoNombre();
                    break;
                case 2:
                     DaoP.listaProductoNombrePrecio();
                    break;
                case 3:
                    System.out.println("");
                    DaoP.EncontrarEntre();
                    break;
                case 4:
                    DaoP.ListaPortatiles();
                    break;
                case 5:
                    DaoP.EncontrarMasBarato();
                    break;
                case 6:
                   servP.crearProducto();
                    break;
                case 7:
                    servF.crearFabricante();
                    break;
                case 8:
                    servP.ModificarProducto();
                    break;
                case 9:
                    DaoP.eliminarProducto();
                    break;
                case 10:
                    DaoF.eliminarFabricante();
                    break;
                case 11:
                    break bucleMenu;
                default:
                    System.out.println("ingrese un dato valido");

            }
            System.out.println("");
            System.out.println("Gracias por usar nuestro servicio.");
            
        } while (true);
        
    }

}
