package asw.streamKafka.consumidor.listener;

import javax.annotation.ManagedBean;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.kafka.annotation.KafkaListener;

import asw.Application;
import asw.streamKafka.productor.MessageProducer;

@ManagedBean
public class PositiveSuggestionListener implements ApplicationEventPublisherAware {
	private ApplicationEventPublisher publisher;

	@KafkaListener(topics = MessageProducer.POSITIVE_SUGGESTION)
	public void positiveSuggestion(String data) {
		Application.logger.info("Mensaje recibido en positiveSuggestion");
		publisher.publishEvent(new PositiveSuggestionEvent(data));
	}
	
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;
	}

	public class PositiveSuggestionEvent {
		private String suggestionId;

		public PositiveSuggestionEvent(String suggestionId) {
			this.suggestionId = suggestionId;
		}

		public String getSuggestionId() {
			return suggestionId;
		}

	}
}