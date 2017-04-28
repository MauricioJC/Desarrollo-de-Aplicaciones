package Aplicacion.Modelos;

/**
 * Creado por Mauricio el 27/04/2017.
 **/

import javax.persistence.*;

@Entity
@Table(name = "smartphone")
public class SmartphoneEntity {
    private Integer idSmartphone;
    private String marca;
    private String modelo;
    private String descripcion;
    private String color;
    private Integer precio;
    private Integer cantidad;

    @Id
    @Column(name = "id_smartphone")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdSmartphone() {
        return idSmartphone;
    }

    public void setIdSmartphone(Integer idSmartphone) {
        this.idSmartphone = idSmartphone;
    }

    @Basic
    @Column(name = "marca")
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Basic
    @Column(name = "modelo")
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Basic
    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Basic
    @Column(name = "precio")
    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    @Basic
    @Column(name = "cantidad")
    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SmartphoneEntity that = (SmartphoneEntity) o;

        if (idSmartphone != null ? !idSmartphone.equals(that.idSmartphone) : that.idSmartphone != null) return false;
        if (marca != null ? !marca.equals(that.marca) : that.marca != null) return false;
        if (modelo != null ? !modelo.equals(that.modelo) : that.modelo != null) return false;
        if (descripcion != null ? !descripcion.equals(that.descripcion) : that.descripcion != null) return false;
        if (color != null ? !color.equals(that.color) : that.color != null) return false;
        if (precio != null ? !precio.equals(that.precio) : that.precio != null) return false;
        if (cantidad != null ? !cantidad.equals(that.cantidad) : that.cantidad != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSmartphone != null ? idSmartphone.hashCode() : 0;
        result = 31 * result + (marca != null ? marca.hashCode() : 0);
        result = 31 * result + (modelo != null ? modelo.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (precio != null ? precio.hashCode() : 0);
        result = 31 * result + (cantidad != null ? cantidad.hashCode() : 0);
        return result;
    }
}

