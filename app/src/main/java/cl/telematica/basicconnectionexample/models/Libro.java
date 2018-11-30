package cl.telematica.basicconnectionexample.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Libro {

    private int id;
    private String nombre;
    private String editorial;
    private String genero;
    private String url_image;
    private int autor;

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String get_url_image() {
        return url_image;
    }

    public void set_url_image(String url) {
        this.url_image = url;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAutor() {
        return autor;
    }

    public void setAutor(int autor) {
        this.autor = autor;
    }

    public static List<Libro> getLibros(String result) {
        List<Libro> lista = new ArrayList<>();

        try {
            JSONArray listaArray = new JSONArray(result);
            int size = listaArray.length();

            for(int i = 0; i < size; i++) {
                Libro libro = new Libro();
                JSONObject obj = listaArray.getJSONObject(i);

                libro.setId(obj.getInt("id"));
                libro.setNombre(obj.getString("nombre"));
                libro.setEditorial(obj.getString("editorial"));
                libro.setGenero(obj.getString("genero"));
                libro.setAutor(obj.getInt("autor"));
                libro.set_url_image(obj.getString("photo"));

                lista.add(libro);
            }
            return lista;
        } catch (JSONException e) {
            e.printStackTrace();
            return lista;
        }
    }
}
