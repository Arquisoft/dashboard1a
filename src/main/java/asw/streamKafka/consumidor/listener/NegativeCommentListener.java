package asw.streamKafka.consumidor.listener;

import java.util.Map;

import javax.annotation.ManagedBean;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.kafka.annotation.KafkaListener;

import asw.streamKafka.consumidor.KafkaListenerFactory;
import asw.streamKafka.productor.MessageProducer;

@ManagedBean
public class NegativeCommentListener implements ApplicationEventPublisherAware {
	private ApplicationEventPublisher publisher;

	@KafkaListener(topics = MessageProducer.NEGATIVE_COMMENT)
	public void negativeComment(String data) {
		publisher.publishEvent(new NegativeCommentEvent(data));
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;
	}

	public class NegativeCommentEvent {
		private String commentId;
		private String suggestionId;

		public NegativeCommentEvent(String data) {
			Map<String, Object> mapa = KafkaListenerFactory.parser.parseMap(data);
			commentId = (String) mapa.get("comment");
			suggestionId = (String) mapa.get("suggestion");
		}

		public String getCommentId() {
			return commentId;
		}

		public String getSuggestionId() {
			return suggestionId;
		}

	}
}