package asw.streamKafka.consumidor.listener;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.kafka.annotation.KafkaListener;

import asw.Application;
import asw.dbManagement.model.Suggestion;
import asw.dbManagement.repository.SuggestionRepository;
import asw.streamKafka.productor.MessageProducer;

@ManagedBean
public class NewSuggestionListener implements ApplicationEventPublisherAware {
	private ApplicationEventPublisher publisher;
	@Autowired
	private SuggestionRepository suggestionRepository;

	@KafkaListener(topics = MessageProducer.NEW_SUGGESTION)
	public void newSuggestion(String data) {
		Application.logger.info("Mensaje recibido en newSuggestion");
		publisher.publishEvent(new NewSuggestionEvent(data));
	}
	
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;
	}

	public class NewSuggestionEvent {
		private Suggestion suggestion;

		public NewSuggestionEvent(String identificador) {
			suggestion = suggestionRepository.findByIdentificador(identificador);
		}

		public Suggestion getSuggestion() {
			return suggestion;
		}

	}
}
