package Modelo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nosel_000 on 31/05/2017.
 */

public class Smartphone implements Parcelable {

    private int id_smartphone;
    private String marca;
    private String modelo;
    private String descripcion;
    private String color;
    private int precio;
    private int cantidad;

    public Smartphone(){

    }

    public Smartphone(Parcel in) {
        this.id_smartphone = in.readInt();
        this.modelo = in.readString();
        this.marca = in.readString();
        this.color = in.readString();
        this.descripcion = in.readString();
        this.precio = in.readInt();
        this.cantidad = in.readInt();
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

    public void readFromParcel(Parcel in){
        id_smartphone = in.readInt();
        modelo = in.readString();
        marca = in.readString();
        color = in.readString();
        descripcion = in.readString();
        precio = in.readInt();
        cantidad = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_smartphone);
        dest.writeString(modelo);
        dest.writeString(marca);
        dest.writeString(color);
        dest.writeString(descripcion);
        dest.writeInt(precio);
        dest.writeInt(cantidad);
    }
    public static final Parcelable.Creator CREATOR = new Creator<Smartphone>() {
        @Override
        public Smartphone createFromParcel(Parcel source) {
            return new Smartphone(source);
        }

        @Override
        public Smartphone[] newArray(int size) {
            return new Smartphone[size];
        }
    };
}
