package asw.streamKafka.consumidor.listener;

import org.springframework.kafka.annotation.KafkaListener;

import asw.streamKafka.productor.MessageProducer;

public class PositiveCommentListener extends AbstractKafkaListener {

	@KafkaListener(topics = MessageProducer.POSITIVE_COMMENT)
	public void positiveSuggestion(String data) {
		super.getPublisher().publishEvent(new PositiveCommentEvent());
	}

	public class PositiveCommentEvent {

	}
}