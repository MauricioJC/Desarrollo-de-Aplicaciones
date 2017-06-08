package Aplicacion.Modelos;

import javax.persistence.*;
import java.sql.Date;

/**
 * Creado por Mauricio el 05/06/2017.
 **/
@Entity
@Table(name = "reservacion")
public class ReservacionEntity {

    private Integer idReservacion;
    private SmartphoneEntity smartphone;
    private UsuarioEntity usuario;
    private Date fecha;

    public ReservacionEntity() {
    }

    public ReservacionEntity(SmartphoneEntity smartphone, UsuarioEntity usuario, Date fecha) {
        this.smartphone = smartphone;
        this.usuario = usuario;
        this.fecha = fecha;
    }

    @Id
    @Column(name = "id_reservacion")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdReservacion() {
        return idReservacion;
    }

    public void setIdReservacion(Integer idReservacion) {
        this.idReservacion = idReservacion;
    }

    @JoinColumn(name = "id_smartphone", referencedColumnName = "id_smartphone")
    @ManyToOne
    public SmartphoneEntity getSmartphone() {
        return smartphone;
    }

    public void setSmartphone(SmartphoneEntity smartphone) {
        this.smartphone = smartphone;
    }

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
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
        ReservacionEntity that = (ReservacionEntity) o;
        if (idReservacion != null ? !idReservacion.equals(that.idReservacion) : that.idReservacion != null)
            return false;
        if (usuario != null ? !usuario.equals(that.usuario) : that.usuario != null) return false;
        if (smartphone != null ? !smartphone.equals(that.smartphone) : that.smartphone != null) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = idReservacion != null ? idReservacion.hashCode() : 0;
        result = 31 * result + (usuario != null ? usuario.hashCode() : 0);
        result = 31 * result + (smartphone != null ? smartphone.hashCode() : 0);
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        return result;
    }
}
