package mx.com.gm.peliculas.datos;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.com.gm.peliculas.domain.Pelicula;
import mx.com.gm.peliculas.excepeciones.*;

public class AccesoDatosImpl implements IAccesoDatos {

    @Override
    public boolean existe(String nombreRecurso) throws AccesoDatosEx {
        File archivo = new File(nombreRecurso);
        return archivo.exists();
    }

    @Override
    public List<Pelicula> listar(String nombreRecurso) throws LecturaDatosEx {
        var archivo = new File(nombreRecurso);
        List<Pelicula> peliculas = new ArrayList<>();
        try {
            var entrada = new BufferedReader(new FileReader(archivo));
            String linea = null;
            try {
                linea = entrada.readLine();
                while (linea != null) {
                    Pelicula pelicula = new Pelicula(linea);
                    peliculas.add(pelicula);
                    linea = entrada.readLine();
                }
                entrada.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                throw new LecturaDatosEx("Ecepcion al listar peliculas:" + ex.getMessage());
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            throw new LecturaDatosEx("Ecepcion al listar peliculas:" + ex.getMessage());
        }
        return peliculas;

    }

    @Override
    public void escribir(Pelicula pelicula, String nombreRecurso, boolean anexar) throws EscriturasDatosEx {
        var archivo = new File(nombreRecurso);
        try {
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            salida.println(pelicula.toString());
            salida.close();
            System.out.println("Se ha escrito información al archivo: " + pelicula);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new EscriturasDatosEx("Ecepcion al escribir peliculas:" + ex.getMessage());
        }
    }

    @Override
    public String buscar(String nombreRecurso, String buscar) throws LecturaDatosEx {
    }

    @Override
    public void crear(String nombreRecruso) throws AccesoDatosEx {
    }

    @Override
    public void borrar(String nombreRecurso) throws AccesoDatosEx {
    }

}
