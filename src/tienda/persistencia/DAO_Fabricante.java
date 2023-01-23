/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.persistencia;

import java.util.Scanner;
import tienda.entidades.Fabricante;

/**
 *
 * @author Fede
 */
final public class DAO_Fabricante extends DAO {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void guardarFabricante(Fabricante fab) throws Exception {

        try {
            if (fab == null) {
                throw new Exception("No indicó fabricante");
            }

            String sql = "INSERT INTO fabricante (codigo, nombre)"
                    + "VALUES ('" + fab.getCodigo() + "' , '" + fab.getNombre() + "' );";

            // OBTENGO EL NOMBRE Y EL CODIGO DE EL FABRICANTE, Y LO PLASMO EN UNA QUERY/CONSULTA 
            // APLICA PARA CUALQUIER OBJETO, DESPUES SE PASA AL METODO DEL DAO PARA MODIFICAR LA TABLA 
            insertarModificarEliminar(sql);
            //ahora actaualice la query 

        } catch (Exception e) {
            throw e;
        }

    }

    public void actualizarNombreFabricante(Fabricante fab) throws Exception {

        try {
            if (fab == null) {
                throw new Exception("Indique fabricante a modificar");
            }

            //MISMO QUE EN EL INSERTAR, NADA MAS QUE ACA ACTUALIZO SEGUN EL CODIGO 
            String sql = "UPDATE fabricante SET "
                    + "nombre = '" + fab.getNombre() + "' WHERE codigo like '" + fab.getCodigo() + "';";

            insertarModificarEliminar(sql);

        } catch (Exception e) {
            desconectarBase();
            throw e;
        }

    }

    public void actualizarCodigoFabricante(Fabricante fab) throws Exception {

        try {
            if (fab == null) {
                throw new Exception("Indique fabricante a modificar");
            }

            //MISMO QUE EN EL INSERTAR, NADA MAS QUE ACA ACTUALIZO SEGUN EL CODIGO 
            String sql = "UPDATE fabricante SET "
                    + "codigo = '" + fab.getCodigo() + "' WHERE nombre like '" + fab.getNombre() + "';";

            insertarModificarEliminar(sql);

        } catch (Exception e) {
            desconectarBase();
            throw e;
        }

    }

    public void eliminarFabricante() throws Exception {
        try {
            System.out.println("ingresa el codigo del fabricante que deseas eliminar");

            Integer codigo = leer.nextInt();

            String sql = "DELETE FROM fabricante WHERE codigo = " + codigo;

            insertarModificarEliminar(sql);

        } catch (Exception e) {
            desconectarBase();
            throw e;
        }

    }

    public String existeFab(String nombre) throws Exception {

        try {

            String sql = " SELECT nombre FROM fabricante WHERE nombre = '" + nombre + "'";

            consultarBase(sql);
            String existe = null;

            while (resultado.next()) {
                existe = resultado.getString(1);
            }
            desconectarBase();
            return existe;

        } catch (Exception e) {
            desconectarBase();
            throw e;
        }

    }

    public Integer existeCod(Integer codigo) throws Exception {

        try {
            String sql = " SELECT codigo FROM fabricante WHERE codigo = " + codigo;

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
}
