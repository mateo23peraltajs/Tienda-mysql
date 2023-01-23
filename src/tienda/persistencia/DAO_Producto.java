/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import tienda.entidades.Producto;

/**
 *
 * @author Fede
 */
final public class DAO_Producto extends DAO {

    
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    public void guardarProducto(Producto prod) throws Exception {
        try {
            if (prod == null) {
                throw new Exception("indique el producto a modificar");
            }

            String sql = "INSERT INTO producto (codigo, nombre, precio, codigo_fabricante)"
                    + "VALUES ('" + prod.getCodigo() + "' , '" + prod.getNombre() + "' , '" + prod.getPrecio() + "' , '" + prod.getCodigoFabricante() + "');";
            insertarModificarEliminar(sql);

        } catch (Exception e) {

            throw e;
        }
    }

    public void actualizarNombreProducto(Producto prod) throws Exception {

        try {
            if (prod == null) {
                throw new Exception("Indique producto a modificar");
            }

            //MISMO QUE EN EL INSERTAR, NADA MAS QUE ACA ACTUALIZO SEGUN EL CODIGO 
            String sql = "UPDATE producto SET "
                    + "nombre = '" + prod.getNombre() + "' WHERE codigo like " + prod.getCodigo() + ";";

            insertarModificarEliminar(sql);

        } catch (Exception e) {
            desconectarBase();
            throw e;
        }

    }

    public void actualizarPrecioProducto(Producto prod) throws Exception {

        try {
            if (prod == null) {
                throw new Exception("Indique producto a modificar");
            }

            //MISMO QUE EN EL INSERTAR, NADA MAS QUE ACA ACTUALIZO SEGUN EL CODIGO 
            String sql = "UPDATE producto SET "
                    + "precio = " + prod.getPrecio() + " WHERE codigo like " + prod.getCodigo() + ";";

            insertarModificarEliminar(sql);

        } catch (Exception e) {
            desconectarBase();
            throw e;
        }

    }

    public void actualizarCodigoFabProducto(Producto prod) throws Exception {

        try {
            if (prod == null) {
                throw new Exception("Indique producto a modificar");
            }

            //MISMO QUE EN EL INSERTAR, NADA MAS QUE ACA ACTUALIZO SEGUN EL CODIGO 
            String sql = "UPDATE producto SET "
                    + "codigo_fabricante = " + prod.getCodigoFabricante() + " WHERE codigo like " + prod.getCodigo() + ";";

            insertarModificarEliminar(sql);

        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

    public void eliminarProducto() throws Exception {
        
        System.out.println("Ingresa el codigo del producto que deseas eliminar.");
        Integer codigo = leer.nextInt();
        
        try {
            String sql = "DELETE FROM producto WHERE codigo = " + codigo + ";";

            insertarModificarEliminar(sql);

        } catch (Exception e) {
            desconectarBase();
            throw e;
        }

    }

    public Producto devolverProducto(Integer codigo) throws Exception {

        try {

            String sql = " SELECT * FROM producto WHERE codigo = " + codigo + ";";

            consultarBase(sql);

            Producto producto = null;

            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getInt(3));
                producto.setCodigoFabricante(resultado.getInt(4));
            }
            desconectarBase();
            return producto;

        } catch (Exception e) {
            desconectarBase();
            throw e;
        }

    }

    public Collection<Producto> listaProductoNombrePrecio() throws Exception {

        try {
            String sql = "SELECT nombre, precio FROM producto";

            consultarBase(sql);

            Producto producto = null;
            Collection<Producto> lista = new ArrayList();
            
            while (resultado.next()) {
                producto = new Producto();
                producto.setNombre(resultado.getString(1));
                producto.setPrecio(resultado.getInt(2));
                lista.add(producto);
            }
            
              for(Producto prod: lista){
                System.out.println(prod.getNombre());
                  System.out.println("$"+prod.getPrecio());
                System.out.println("");
            }
            desconectarBase();
            return lista;

        } catch (Exception e) {
            desconectarBase();
            throw e;
        }

    }

    public Collection<Producto> listaProductoNombre() throws Exception {

        try {
            String sql = "SELECT nombre FROM producto";

            consultarBase(sql);

            Producto producto = null;
            
            Collection<Producto> lista = new ArrayList();
            
            while (resultado.next()) {
                producto = new Producto();
                producto.setNombre(resultado.getString(1));
                lista.add(producto);
            }
            
            for(Producto prod: lista){
                System.out.println(prod.getNombre());
                System.out.println("");
            }
            
            desconectarBase();
            return lista;

        } catch (Exception e) {
            desconectarBase();
            throw e;
        }

    }

    public Collection<Producto> ListaPortatiles() throws Exception {
        try {
            String sql = "SELECT * FROM producto WHERE nombre LIKE '%Portátil%'";

            consultarBase(sql);

            Producto producto = null;
            Collection<Producto> lista = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(4);
                lista.add(producto);
            }
            for(Producto prod: lista){
                System.out.println(prod.getCodigo());
                System.out.println(prod.getNombre());
                System.out.println("$"+prod.getPrecio());
                 System.out.println(prod.getCodigoFabricante());
                System.out.println("");
            }
            return lista;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

    public Producto EncontrarMasBarato() throws Exception {
        try {
            String sql = "SELECT nombre, precio FROM producto" + " WHERE precio = " + "(SELECT MIN(precio) FROM producto)";
            consultarBase(sql);
            Producto producto = null;
            while (resultado.next()) {
                producto = new Producto();
                producto.setNombre(resultado.getString(1));
                producto.setPrecio(resultado.getDouble(2));
            }
            desconectarBase();
            System.out.println(producto.getNombre());
            System.out.println(producto.getPrecio());
                    
            return producto;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

    public Collection<Producto> EncontrarEntre() throws Exception {
        try {
            String sql = "SELECT * FROM producto WHERE precio BETWEEN 120 AND 202";
            consultarBase(sql);
            Producto producto = null;
            Collection<Producto> lista = new ArrayList();

            while (resultado.next()) {

                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getInt(3));
                producto.setCodigoFabricante(resultado.getInt(4));

                lista.add(producto);

            }
            
             for(Producto prod: lista){
                System.out.println(prod.getCodigo());
                System.out.println(prod.getNombre());
                System.out.println("$"+prod.getPrecio());
                 System.out.println(prod.getCodigoFabricante());
                System.out.println("");
            }
            
            desconectarBase();
            return lista;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

    public Integer existeProd(Integer codigo) throws Exception {

        try {

            String sql = " SELECT codigo FROM producto WHERE codigo = " + codigo;

            consultarBase(sql);
            Integer existe = null;

            while (resultado.next()) {
                existe = resultado.getInt(1);
            }
            desconectarBase();
            return existe;

        } catch (Exception e) {
            desconectarBase();
            throw e;
        }

    }

    public Collection<Producto> ListaProducto() throws Exception {

        try {
            String sql = "SELECT * FROM producto";

            consultarBase(sql);

            Producto producto = null;
            Collection<Producto> lista = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));
                lista.add(producto);
            }
            desconectarBase();
            return lista;

        } catch (Exception e) {
            desconectarBase();
            throw e;
        }

    }

    public Producto CopiarProducto(int codigo) throws Exception {

        try {

            String sql = " SELECT * FROM producto WHERE codigo = " + codigo + ";";

            consultarBase(sql);
            Producto producto = null;

            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));
            }
            desconectarBase();
            return producto;

        } catch (Exception e) {
            desconectarBase();
            throw e;
        }

    }

    public void ModificarProducto(Producto producto, int codigoViejo) throws Exception {
        try {
            conectarBase();
            if (producto == null) {
                throw new Exception("debe indicar el producto que desea modificar");
            }

            String sql = "UPDATE producto SET"
                    + " codigo = " + producto.getCodigo() + " ,nombre = '" + producto.getNombre() + "' , precio = " + producto.getPrecio() + " ,codigo_fabricante = " + producto.getCodigoFabricante()
                    + " WHERE codigo = " + codigoViejo;
            
            insertarModificarEliminar(sql);
            desconectarBase();
        } catch (Exception e) {
            desconectarBase();
            
            throw e;
        }
        
    }

    @Override
    public String toString() {
        return "DAO_Producto{" + '}';
    }

    
}
