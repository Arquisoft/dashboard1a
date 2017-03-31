package asw.streamKafka.consumidor.listener;

import org.springframework.kafka.annotation.KafkaListener;

import asw.streamKafka.productor.MessageProducer;

public class NewSuggestionListener extends AbstractKafkaListener {

	@KafkaListener(topics = MessageProducer.NEW_SUGGESTION)
	public void newSuggestion(String data) {
		super.getPublisher().publishEvent(new NewTopicEvent());
	}

	public class NewTopicEvent {

	}
}
