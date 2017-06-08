package Aplicacion.Modelos;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Mauricio on 25/04/2017.
 */
@Entity
@Table(name = "traspaso")
public class TraspasoEntity {
    private Integer idTraspaso;
    private SmartphoneEntity smartphone;
    private String tipo;
    private Date fecha;

    public TraspasoEntity() {
    }

    public TraspasoEntity(SmartphoneEntity smartphone, String tipo, Date fecha) {
        this.smartphone = smartphone;
        this.tipo = tipo;
        this.fecha = fecha;
    }

    @Id
    @Column(name = "id_traspaso")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdTraspaso() {
        return idTraspaso;
    }

    public void setIdTraspaso(Integer idTraspaso) {
        this.idTraspaso = idTraspaso;
    }

    @JoinColumn(name = "id_smartphone", referencedColumnName = "id_smartphone")
    @ManyToOne
    public SmartphoneEntity getSmartphone() {
        return smartphone;
    }

    public void setSmartphone(SmartphoneEntity smartphone) {
        this.smartphone = smartphone;
    }

    @Basic
    @Column(name = "tipo")
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        TraspasoEntity that = (TraspasoEntity) o;
        if (idTraspaso != null ? !idTraspaso.equals(that.idTraspaso) : that.idTraspaso != null) return false;
        if (smartphone != null ? !smartphone.equals(that.smartphone) : that.smartphone != null) return false;
        if (tipo != null ? !tipo.equals(that.tipo) : that.tipo != null) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = idTraspaso != null ? idTraspaso.hashCode() : 0;
        result = 31 * result + (smartphone != null ? smartphone.hashCode() : 0);
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        return result;
    }
}
