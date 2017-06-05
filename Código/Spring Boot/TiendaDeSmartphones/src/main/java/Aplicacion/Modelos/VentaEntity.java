package Aplicacion.Modelos;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Mauricio on 25/04/2017.
 */

@Entity
@Table(name = "venta")
public class VentaEntity {
    private Integer idVenta;
    private SmartphoneEntity Smartphone;
    private UsuarioEntity Usuario;
    private Date fecha;

    public VentaEntity() {
    }

    public VentaEntity(SmartphoneEntity smartphone, UsuarioEntity usuario, Date fecha) {
        Smartphone = smartphone;
        Usuario = usuario;
        this.fecha = fecha;
    }

    @Id
    @Column(name = "id_venta")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    @JoinColumn(name = "id_smartphone", referencedColumnName = "id_smartphone")
    @ManyToOne
    public SmartphoneEntity getSmartphone() {
        return Smartphone;
    }

    public void setSmartphone(SmartphoneEntity smartphone) {
        Smartphone = smartphone;
    }

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    public UsuarioEntity getUsuario() {
        return Usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        Usuario = usuario;
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
        VentaEntity that = (VentaEntity) o;
        if (idVenta != null ? !idVenta.equals(that.idVenta) : that.idVenta != null) return false;
        if (Smartphone != null ? !Smartphone.equals(that.Smartphone) : that.Smartphone != null) return false;
        if (Usuario != null ? !Usuario.equals(that.Usuario) : that.Usuario != null) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = idVenta != null ? idVenta.hashCode() : 0;
        result = 31 * result + (Smartphone != null ? Smartphone.hashCode() : 0);
        result = 31 * result + (Usuario != null ? Usuario.hashCode() : 0);
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        return result;
    }
}
