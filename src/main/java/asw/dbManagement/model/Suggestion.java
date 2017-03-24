package asw.dbManagement.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TSuggestions")
public class Suggestion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String identificador;
	private String nombre;
	private String contenido;
	private int votosPositvos = 0;
	private int votosNegativos = 0;
	private int votosMinimos;

	@ManyToOne
	private Participant participant;
	@OneToMany(mappedBy = "suggestion")
	private Set<Commentary> commentaries = new HashSet<Commentary>();
	@OneToMany(mappedBy = "suggestion")
	private Set<VoteSuggestion> votesSuggestion = new HashSet<VoteSuggestion>();

	Suggestion() {
	}

	public Suggestion(String identificador) {
		super();
		this.identificador = identificador;
	}

	public Suggestion(String identificador, String nombre, String contenido, int votosMinimos) {
		this(identificador);
		this.nombre = nombre;
		this.contenido = contenido;
		this.votosMinimos = votosMinimos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public int getVotosPositvos() {
		return votosPositvos;
	}

	public void setVotosPositvos(int votosPositvos) {
		this.votosPositvos = votosPositvos;
	}

	public int getVotosNegativos() {
		return votosNegativos;
	}

	public void setVotosNegativos(int votosNegativos) {
		this.votosNegativos = votosNegativos;
	}

	public int getVotosMinimos() {
		return votosMinimos;
	}

	public void setVotosMinimos(int votosMinimos) {
		this.votosMinimos = votosMinimos;
	}

	public Participant getParticipant() {
		return participant;
	}

	void _setParticipant(Participant participant) {
		this.participant = participant;
	}

	public Long getId() {
		return id;
	}

	public Set<Commentary> getComentaries() {
		return new HashSet<Commentary>(commentaries);
	}

	Set<Commentary> _getCommentaries() {
		return commentaries;
	}
	
	public Set<VoteSuggestion> getVotesSuggestion() {
		return new HashSet<VoteSuggestion>(votesSuggestion);
	}

	Set<VoteSuggestion> _getVotesSuggestion() {
		return votesSuggestion;
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
		Suggestion other = (Suggestion) obj;
		if (identificador == null) {
			if (other.identificador != null)
				return false;
		} else if (!identificador.equals(other.identificador))
			return false;
		return true;
	}

}
