package asw.streamKafka.consumidor.listener;

import javax.annotation.ManagedBean;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.kafka.annotation.KafkaListener;

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

		public NewCommentEvent(String commentId) {
			this.commentId = commentId;
		}

		public String getCommentId() {
			return commentId;
		}

	}
}
