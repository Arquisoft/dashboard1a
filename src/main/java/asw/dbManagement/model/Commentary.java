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
@Table(name = "TCommentaries")
public class Commentary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String identificador;
	private String contenido;
	private int votosPositivos = 0;
	private int votosNegativos = 0;

	@ManyToOne
	private Participant participant;
	@ManyToOne
	private Suggestion suggestion;
	@OneToMany(mappedBy = "commentary")
	private Set<VoteCommentary> votesCommentaries = new HashSet<VoteCommentary>();

	Commentary() {
	}

	public Commentary(String identificador) {
		super();
		this.identificador = identificador;
	}

	public Commentary(String identificador, String contenido) {
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

	public Participant getParticipant() {
		return participant;
	}

	void _setParticipant(Participant participant) {
		this.participant = participant;
	}

	public Suggestion getSuggestion() {
		return suggestion;
	}

	void _setSuggestion(Suggestion suggestion) {
		this.suggestion = suggestion;
	}

	public Long getId() {
		return id;
	}
	
	public Set<VoteCommentary> getVotesCommentary() {
		return new HashSet<VoteCommentary>(votesCommentaries);
	}

	Set<VoteCommentary> _getVotesCommentary() {
		return votesCommentaries;
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
		Commentary other = (Commentary) obj;
		if (identificador == null) {
			if (other.identificador != null)
				return false;
		} else if (!identificador.equals(other.identificador))
			return false;
		return true;
	}
}
