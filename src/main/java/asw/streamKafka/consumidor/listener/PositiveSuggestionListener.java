package asw.streamKafka.consumidor.listener;

import org.springframework.kafka.annotation.KafkaListener;

import asw.streamKafka.productor.MessageProducer;

public class PositiveSuggestionListener extends AbstractKafkaListener {

	@KafkaListener(topics = MessageProducer.POSITIVE_SUGGESTION)
	public void positiveSuggestion(String data) {
		super.getPublisher().publishEvent(new PositiveSuggestionEvent());
	}

	public class PositiveSuggestionEvent {

	}
}