package asw.streamKafka.consumidor.listener;

import org.springframework.kafka.annotation.KafkaListener;

import asw.streamKafka.productor.MessageProducer;

public class NewCommentListener extends AbstractKafkaListener {

	@KafkaListener(topics = MessageProducer.NEW_COMMENT)
	public void newComment(String data) {
		super.getPublisher().publishEvent(new NewCommentEvent());
	}

	public class NewCommentEvent {

	}
}

