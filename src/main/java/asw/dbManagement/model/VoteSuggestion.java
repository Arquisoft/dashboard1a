package asw.dbManagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import asw.dbManagement.model.types.VoteSuggestionKey;

@Entity
@IdClass(VoteSuggestionKey.class)
@Table(name = "TVoteSuggestion")
public class VoteSuggestion {

	@Id
	@ManyToOne
	@JoinColumn(name = "PARTICIPANT_ID", referencedColumnName = "ID")
	private Participant participant;

	@Id
	@ManyToOne
	@JoinColumn(name = "SUGGESTION_ID", referencedColumnName = "ID")
	private Suggestion suggestion;

	VoteSuggestion() {
	}

	public VoteSuggestion(Participant participant, Suggestion suggestion) {
		// TODO Auto-generated constructor stub
		Association.VotarSuggestion.link(participant, this, suggestion);
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
	public void deleteVoteSuggestion(){
		Association.VotarSuggestion.unlink(this);
	}

	@Override
	public String toString() {
		return "VoteSuggestion [participant=" + participant + ", suggestion=" + suggestion + "]";
	}
}
