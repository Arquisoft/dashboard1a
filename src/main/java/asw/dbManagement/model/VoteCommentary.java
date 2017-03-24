package asw.dbManagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import asw.dbManagement.model.types.VoteCommentaryKey;

@Entity
@IdClass(VoteCommentaryKey.class)
@Table(name = "TVoteCommentary")
public class VoteCommentary {

	@Id
	@ManyToOne
	@JoinColumn(name = "PARTICIPANT_ID", referencedColumnName = "ID")
	private Participant participant;

	@Id
	@ManyToOne
	@JoinColumn(name = "COMMENTARY_ID", referencedColumnName = "ID")
	private Commentary commentary;

	VoteCommentary() {
	}

	public VoteCommentary(Participant participant, Commentary commentary) {
		// TODO Auto-generated constructor stub
		Association.VotarCommentary.link(participant, this, commentary);
	}

	public Participant getParticipant() {
		return participant;
	}

	void _setParticipant(Participant participant) {
		this.participant = participant;
	}

	public Commentary getCommentary() {
		return commentary;
	}

	void _setComentary(Commentary commentary) {
		this.commentary = commentary;
	}

	@Override
	public String toString() {
		return "VoteCommentary [participant=" + participant + ", comentary=" + commentary + "]";
	}
}
