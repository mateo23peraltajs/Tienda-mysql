/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.servicios;

import java.awt.BorderLayout;
import java.util.Scanner;
import tienda.entidades.Producto;
import tienda.persistencia.DAO_Producto;

/**
 *
 * @author Fede
 */
public class productoService {

    private DAO_Producto dao;

    public productoService() {
    }

    public productoService(DAO_Producto dao) {
        this.dao = dao;
    }

    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    
    public void crearProducto() throws Exception {

        try {

            DAO_Producto daoProd = new DAO_Producto();
            Producto prod = new Producto();

            String nombre;
            double precio;
            Integer codigoFab;
            Integer codigo = null;
            
            System.out.println("Quieres agregar codigo manualmente?");
            String respuesta = leer.nextLine();
            if(respuesta.toLowerCase().equals("si")){
                System.out.println("ingresa codigo");
                codigo = leer.nextInt();
                prod.setCodigo(codigo);
                if (daoProd.existeProd(codigo) != null) {
                throw new Exception("El codigo " + codigo + " ya esta registrado.");
                
            }
            }else{
                System.out.println("el codigo se asignara automaticamente");
            }            
            

            
            System.out.println("Indica un nombre.");
            nombre = leer.next();
            if (nombre == null) {
                throw new Exception("No ingresaste nombre");
            }
            prod.setNombre(nombre);

            System.out.println("Ingresa su precio.");
            precio = leer.nextDouble();
            if (String.valueOf(precio) == null) {
                throw new Exception("No pusiste precio");
            }
            prod.setPrecio(precio);

            System.out.println("Ingresa el codigo del fabricante.");
            codigoFab = leer.nextInt();
            if (codigoFab == null) {
                throw new Exception("No indicaste el codigo del fabricante");
            }
            prod.setCodigoFabricante(codigoFab);

            daoProd.guardarProducto(prod);

        } catch (Exception e) {
            throw e;
        }

    }

    
    DAO_Producto daoProducto = new DAO_Producto();

    public void ModificarProducto() throws Exception {

        try {

            daoProducto.ListaProducto();

            System.out.println("ingrese el codigo del producto que va a modificar");

            int num = leer.nextInt();

            Producto producto = daoProducto.CopiarProducto(num);

            System.out.println("desea cambiar el codigo? 1 para confirmar");

            if (leer.nextInt() == 1) {
                System.out.println("ingrese el nuevo codigo");
                int codigo = leer.nextInt();

                if (daoProducto.existeProd(codigo) != null) {
                    throw new Exception("El codigo " + codigo + " ya esta registrado.");
                } else {
                    producto.setCodigo(codigo);
                }
            }

            System.out.println("desea cambiar el nombre? 1 para confirmar");
            if (leer.nextInt() == 1) {
                producto.setNombre(leer.next());
            }
            System.out.println("desea cambiar el precio? 1 para confirmar");
            if (leer.nextInt() == 1) {
                producto.setPrecio(leer.nextDouble());
            }
            System.out.println("desea cambiar el codigo de fabricante? 1 para confirmar");
            if (leer.nextInt() == 1) {
                producto.setCodigoFabricante(leer.nextInt());
            }
            daoProducto.ModificarProducto(producto, num);

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public String toString() {
        return "productoService{" + "dao=" + dao + ", leer=" + leer + ", daoProducto=" + daoProducto + '}';
    }

}
