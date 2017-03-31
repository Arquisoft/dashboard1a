package asw.streamKafka.consumidor.listener;

import org.springframework.kafka.annotation.KafkaListener;

import asw.streamKafka.productor.MessageProducer;

public class NegativeCommentListener extends AbstractKafkaListener {

	@KafkaListener(topics = MessageProducer.NEGATIVE_COMMENT)
	public void negativeComment(String data) {
		super.getPublisher().publishEvent(new NegativeCommentEvent());
	}

	public class NegativeCommentEvent {

	}
}