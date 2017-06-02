package Modelo;

/**
 * Created by nosel_000 on 31/05/2017.
 */

public class Smartphone {

    private int id_smartphone;
    private String marca;
    private String modelo;
    private String descripcion;
    private String color;
    private int precio;
    private int cantidad;

    public Smartphone(){

    }


    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId_smartphone() {
        return id_smartphone;
    }

    public void setId_smartphone(int id_smartphone) {
        this.id_smartphone = id_smartphone;
    }
}
