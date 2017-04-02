package asw.streamKafka.productor;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import asw.Application;
import asw.dbManagement.model.Commentary;
import asw.dbManagement.model.Participant;
import asw.dbManagement.model.Suggestion;
import asw.dbManagement.model.types.VoteType;
import asw.dbManagement.repository.CommentaryRepository;
import asw.dbManagement.repository.ParticipantRepository;
import asw.dbManagement.repository.SuggestionRepository;

@ManagedBean
public class MessageProducer {
	public final static String NEW_SUGGESTION = "newSuggestion";
	public final static String ALERT_SUGGESTION = "alertSuggestion";
	public final static String NEW_COMMENT = "newComment";
	public final static String POSITIVE_COMMENT = "positiveComment";
	public final static String NEGATIVE_COMMENT = "negativeComment";
	public final static String POSITIVE_SUGGESTION = "positiveSuggestion";

	// Para generar codigos aleatorios alfanumericos
	private SecureRandom random = new SecureRandom();

	@Autowired
	KafkaTemplate<String, String> template;

	@Autowired
	SuggestionRepository suggestionRepository;

	@Autowired
	CommentaryRepository commentaryRepository;

	@Autowired
	ParticipantRepository participantRepository;

	@Scheduled(cron = "*/5 * * * * *")
	public void sendNewSuggestion() {
		Participant p = participantRandom();
		String id = nextId();
		
		suggestionRepository.save(new Suggestion(id, "prueba", "prueba de sugerencia", 1, p));
		
		// Identificador de la sugerencia
		send(NEW_SUGGESTION, "{ \"suggestion\":\"" + id + "\"}");
	}

	@Scheduled(cron = "*/15 * * * * *")
	public void sendPositiveSuggestion() {
		Suggestion s = suggestionRandom();
		s.incrementarNumeroVotos(VoteType.POSITIVE);
		suggestionRepository.save(s);
		
		// Identificador de la sugerencia
		send(POSITIVE_SUGGESTION, "{ \"suggestion\":\"" + s.getIdentificador() + "\"}");

		if (s.getVotosMinimos() == s.getVotosPositivos() && !s.isAlert()) {
			s.setAlert(true);
			suggestionRepository.save(s);
			
			send(ALERT_SUGGESTION, "{ \"suggestion\":\"" + s.getIdentificador() + "\"}");
			Application.logger.info("Alerta a" + s.getIdentificador());
		}
		
		Application.logger
		.info("Voto a " + s.getIdentificador() + ", nº votos " + s.getVotosPositivos());
	}

	@Scheduled(cron = "*/20 * * * * *")
	public void sendNewComment() {
		Suggestion s = suggestionRandom();
		Participant p = participantRandom();
		String id = nextId();

		commentaryRepository.save(new Commentary(id, "prueba", p, s));
		suggestionRepository.save(s);

		// Identificador del comentario y de la sugerencia
		String message = "{ \"comment\":\"" + id + "\", \"suggestion\":\"" + s.getIdentificador()
				+ "\"}";
		send(NEW_COMMENT, message);
		
		Application.logger.info(
				"Comentario en " + s.getIdentificador() + ", nº comentarios " + s.getNumComments());
	}
	//
	// @Scheduled(cron = "*/5 * * * * *")
	// public void sendPositiveComment() {
	// contador++;
	// // Identificador comentario y sugerencia
	// String message = "{ \"comment\":\"C" + contador + "\",
	// \"suggestion\":\"S" + contador
	// + "\"}";
	// send(POSITIVE_COMMENT, message);
	// }
	//
	// @Scheduled(cron = "*/10 * * * * *")
	// public void sendNegativeComment() {
	// contador++;
	// // Identificador comentario y sugerencia
	// String message = "{ \"comment\":\"C" + contador + "\",
	// \"suggestion\":\"S" + contador
	// + "\"}";
	// send(NEGATIVE_COMMENT, message);
	// }
	//

	private void send(String topic, String message) {
		ListenableFuture<SendResult<String, String>> future = template.send(topic, message);
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onSuccess(SendResult<String, String> result) {
				Application.logger.info("...enviando mensaje " + topic);
			}

			@Override
			public void onFailure(Throwable ex) {
				Application.logger.error("ERROR: enviando en " + topic);
			}
		});
	}

	/********************* METODOS AUXILIARES ***********************/

	private String nextId() {
		return new BigInteger(130, random).toString(32);
	}

	private Suggestion suggestionRandom() {
		int s = randomInt(0, suggestionRepository.findAll().size() - 1);
		return suggestionRepository.findAll().get(s);
	}

	private Participant participantRandom() {
		int s = randomInt(0, participantRepository.findAll().size() - 1);
		return participantRepository.findAll().get(s);
	}

	private int randomInt(int min, int max) {
		Random rn = new Random();
		return rn.nextInt(max - min + 1) + min;
	}
}
