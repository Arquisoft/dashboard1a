package asw.streamKafka.productor;

import java.math.BigInteger;
import java.security.SecureRandom;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import asw.Application;
import asw.dbManagement.model.Suggestion;
import asw.dbManagement.repository.SuggestionRepository;

@ManagedBean
public class MessageProducer {
	public final static String NEW_SUGGESTION = "newSuggestion";
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

	@Scheduled(cron = "*/5 * * * * *")
	public void sendNewSuggestion() {
		String id = nextId();
		suggestionRepository.save(new Suggestion(id));

		// Identificador de la sugerencia
		send(NEW_SUGGESTION, id);
	}

	// @Scheduled(cron = "*/15 * * * * *")
	// public void sendNewComment() {
	// contador++;
	// // Identificador del comentario
	// String message = "C" + contador;
	// send(NEW_COMMENT, message);
	// }
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

	@Scheduled(cron = "*/15 * * * * *")
	public void sendPositiveSuggestion() {
		Application.logger
				.info("Voto a " + suggestionRepository.findAll().get(1).getIdentificador());
		// Identificador sugerencia
		send(POSITIVE_SUGGESTION, suggestionRepository.findAll().get(1).getIdentificador());
	}

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

	public String nextId() {
		return new BigInteger(130, random).toString(32);
	}
}
