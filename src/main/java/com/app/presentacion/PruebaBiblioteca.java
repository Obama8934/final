
package com.app.presentacion;

import com.app.logica.Autor;
import com.app.logica.Libro;
import com.app.logica.ControladoraLogica;

public class PruebaBiblioteca {

    public static void main(String[] args) {

        ControladoraLogica control = new ControladoraLogica();

        try {

            // Creamos un autor
            Autor autor = new Autor(
                "Julio Cortázar",
                "Argentina"
            );

            control.crearAutor(autor);

            // Creamos un libro relacionado con ese autor
            Libro libro = new Libro(
                "Rayuela",
                "Novela",
                1963,
                null
            );

            control.crearLibro(libro, autor.getId());

            // Mostramos los resultados
            System.out.println("AUTORES:");

            for (Autor a : control.listarAutores()) {
                System.out.println(a);
            }

            System.out.println("LIBROS:");

            for (Libro l : control.listarLibros()) {
                System.out.println(l);
            }

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());

        }
    }
}
