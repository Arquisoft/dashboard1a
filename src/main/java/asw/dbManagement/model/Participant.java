package asw.dbManagement.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TParticipant")
public class Participant {

	// Id generado automáticamente para diferenciar cada uno (para mapear)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Atributos del participante
	private String nombre;
	private String apellidos;
	private String password;
	private Date fechaNacimiento;
	@Column(unique = true)
	private String email;
	@Column(unique = true)
	private String dni;
	private String direccion;
	private String nacionalidad;

	@OneToMany(mappedBy = "participant")
	private Set<Propuesta> propuestas = new HashSet<Propuesta>();

	/**
	 * Constructor vacío (ya que es para mapear)
	 */
	Participant() {
	}

	public Participant(String dni) {
		super();
		this.dni = dni;
	}

	/**
	 * Constructor
	 * 
	 * @param nombre
	 * @param apellidos
	 * @param password
	 * @param fechaNacimiento
	 * @param email
	 * @param dni
	 * @param direccion
	 * @param nacionalidad
	 */
	public Participant(String nombre, String apellidos, String password, Date fechaNacimiento, String email, String dni,
			String direccion, String nacionalidad) {
		this(dni);
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;
		this.direccion = direccion;
		this.nacionalidad = nacionalidad;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDNI() {
		return dni;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public Set<Propuesta> getPropuestas() {
		return new HashSet<Propuesta>(propuestas);
	}

	Set<Propuesta> _getPropuestas() {
		return propuestas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
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
		Participant other = (Participant) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Participant [nombre=" + nombre + ", apellidos=" + apellidos + ", fechaNacimiento=" + fechaNacimiento
				+ ", email=" + email + ", DNI=" + dni + ", direccion=" + direccion + ", nacionalidad=" + nacionalidad
				+ "]";
	}
}
