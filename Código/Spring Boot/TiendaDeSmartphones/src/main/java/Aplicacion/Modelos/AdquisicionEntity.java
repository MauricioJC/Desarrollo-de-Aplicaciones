package Aplicacion.Modelos;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "adquisicion")
public class AdquisicionEntity {
    private Integer idAdquisicion;
    private String producto;
    private String proveedor;
    private Integer precio;
    private Integer cantidad;
    private Date fecha;

    @Id
    @Column(name = "id_adquisicion")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdAdquisicion() {
        return idAdquisicion;
    }

    public void setIdAdquisicion(Integer idAdquisicion) {
        this.idAdquisicion = idAdquisicion;
    }

    @Basic
    @Column(name = "producto")
    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    @Basic
    @Column(name = "proveedor")
    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
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

    @Basic
    @Column(name = "fecha")
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdquisicionEntity that = (AdquisicionEntity) o;

        if (idAdquisicion != null ? !idAdquisicion.equals(that.idAdquisicion) : that.idAdquisicion != null)
            return false;
        if (producto != null ? !producto.equals(that.producto) : that.producto != null) return false;
        if (proveedor != null ? !proveedor.equals(that.proveedor) : that.proveedor != null) return false;
        if (precio != null ? !precio.equals(that.precio) : that.precio != null) return false;
        if (cantidad != null ? !cantidad.equals(that.cantidad) : that.cantidad != null) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAdquisicion != null ? idAdquisicion.hashCode() : 0;
        result = 31 * result + (producto != null ? producto.hashCode() : 0);
        result = 31 * result + (proveedor != null ? proveedor.hashCode() : 0);
        result = 31 * result + (precio != null ? precio.hashCode() : 0);
        result = 31 * result + (cantidad != null ? cantidad.hashCode() : 0);
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        return result;
    }
}
