package asw.streamKafka.consumidor.listener;

import java.util.Map;

import javax.annotation.ManagedBean;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.kafka.annotation.KafkaListener;

import asw.streamKafka.consumidor.KafkaListenerFactory;
import asw.streamKafka.productor.MessageProducer;

@ManagedBean
public class NewCommentListener implements ApplicationEventPublisherAware {
	private ApplicationEventPublisher publisher;

	@KafkaListener(topics = MessageProducer.NEW_COMMENT)
	public void newComment(String data) {
		publisher.publishEvent(new NewCommentEvent(data));
	}
	
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;
	}

	public class NewCommentEvent {
		private String commentId;
		private String suggestionId;

		public NewCommentEvent(String data) {
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
