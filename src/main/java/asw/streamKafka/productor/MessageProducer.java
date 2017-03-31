package asw.streamKafka.productor;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import asw.Application;

@ManagedBean
public class MessageProducer {
	public final static String NEW_SUGGESTION = "newSuggestion";
	public final static String NEW_COMMENT = "newComment";
	public final static String POSITIVE_COMMENT = "positiveComment";
	public final static String NEGATIVE_COMMENT = "negativeComment";
	public final static String POSITIVE_SUGGESTION = "positiveSuggestion";
	
	private int contador;

	@Autowired
	KafkaTemplate<String, String> template;

	@Scheduled(cron = "*/20 * * * * *")
	public void sendNewSuggestion() {
		contador++;
		// ID de la sugerencia, ID del participant que la propuso, minimo numero votos
		String message = "{ \"id\":\"" + contador + "\", \"participant\":\"" + contador + "\", \"minVotes\":\"" + (contador+10) + "\"}";
		send(NEW_SUGGESTION, message);
	}
	
	@Scheduled(cron = "*/15 * * * * *")
	public void sendNewComment() {
		contador++;
		// ID del comentario, ID del participant que lo escribio e ID de en que sugerencia lo puso
		String message = "{ \"id\":\"" + contador + "\", \"participant\":\"" + contador + "\", \"suggestion\":\"" + contador + "\"}";
		send(NEW_COMMENT, message);
	}
	
	@Scheduled(cron = "*/5 * * * * *")
	public void sendPositiveComment() {
		contador++;
		// ID del comentario, ID del participant que voto, ID de la sugerencia
		String message = "{ \"id\":\"" + contador + "\", \"participant\":\"" + contador + "\", \"suggestion\":\"" + contador + "\"}";
		send(POSITIVE_COMMENT, message);
	}
	
	@Scheduled(cron = "*/10 * * * * *")
	public void sendNegativeComment() {
		contador++;
		// ID del comentario, ID del participant que voto, ID de la sugerencia
		String message = "{ \"id\":\"" + contador + "\", \"participant\":\"" + contador + "\", \"suggestion\":\"" + contador + "\"}";
		send(NEGATIVE_COMMENT, message);
	}
	
	@Scheduled(cron = "*/7 * * * * *")
	public void sendPositiveSuggestion() {
		contador++;
		// ID de la sugerencia, ID del participant que voto
		String message = "{ \"id\":\"" + contador + "\", \"participant\":\"" + contador + "\"}";
		send(POSITIVE_SUGGESTION, message);
	}
	
	private void send(String topic, String message){
		ListenableFuture<SendResult<String, String>> future = template.send(topic, message);
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onSuccess(SendResult<String, String> result) {
				Application.logger.info("exito mensaje en " + topic);
			}
			@Override
			public void onFailure(Throwable ex) {
				Application.logger.error("error mensaje en " + topic);
			}
		});
	}
}
