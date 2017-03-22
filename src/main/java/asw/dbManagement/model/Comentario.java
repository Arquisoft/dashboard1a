package asw.dbManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TComentario")
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String identificador;
	private String contenido;
	private int votosPositivos = 0;
	private int votosNegativos = 0;

	@ManyToOne
	private Propuesta propuesta;

	Comentario() {
	}

	public Comentario(String identificador) {
		super();
		this.identificador = identificador;
	}

	public Comentario(String identificador, String contenido) {
		this(identificador);
		this.contenido = contenido;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public int getVotosPositivos() {
		return votosPositivos;
	}

	public void setVotosPositivos(int votosPositivos) {
		this.votosPositivos = votosPositivos;
	}

	public int getVotosNegativos() {
		return votosNegativos;
	}

	public void setVotosNegativos(int votosNegativos) {
		this.votosNegativos = votosNegativos;
	}

	public Propuesta getPropuesta() {
		return propuesta;
	}

	void _setPropuesta(Propuesta propuesta) {
		this.propuesta = propuesta;
	}

	public String getIdentificador() {
		return identificador;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identificador == null) ? 0 : identificador.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comentario other = (Comentario) obj;
		if (identificador == null) {
			if (other.identificador != null)
				return false;
		} else if (!identificador.equals(other.identificador))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comentario [id=" + id + ", identificador=" + identificador + ", contenido=" + contenido
				+ ", votosPositivos=" + votosPositivos + ", votosNegativos=" + votosNegativos + ", propuesta="
				+ propuesta + "]";
	}
}
