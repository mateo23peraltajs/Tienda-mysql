/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.servicios;

import java.sql.SQLException;
import java.util.Scanner;
import tienda.entidades.Fabricante;
import tienda.persistencia.DAO_Fabricante;

/**
 *
 * @author Fede
 */
public class fabricanteService {

    private DAO_Fabricante dao = new DAO_Fabricante();

    public void crearFabricante() throws Exception {

        Integer codigo;
        String nombre;

        Scanner leer = new Scanner(System.in);
        try {
            System.out.println("Ingresa el codigo del fabricante.");
            codigo = leer.nextInt();

            if (codigo == null) {
                throw new Exception("No pusiste codigo");
            }
            if (dao.existeCod(codigo) != null) {
                throw new Exception("Un fabricante ya posee ese codigo.");
            }

            System.out.println("Ingrese el nombre de la compañia");
            nombre = leer.next();

            if (dao.existeFab(nombre) != null) {
                throw new Exception("Ya existe fabricante con ese nombre");
            }
            if (nombre == null) {
                throw new Exception("No pusiste nombre.");
            }

        } catch (Exception e) {

            throw e;
        }
        Fabricante fabricante = new Fabricante(codigo, nombre);
        dao.guardarFabricante(fabricante);
    }
}
