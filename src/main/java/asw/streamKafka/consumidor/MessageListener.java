package asw.streamKafka.consumidor;

import javax.annotation.ManagedBean;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.kafka.annotation.KafkaListener;

@ManagedBean
public class MessageListener implements ApplicationEventPublisherAware{
	private static final Logger logger = Logger.getLogger(MessageListener.class);
	 private ApplicationEventPublisher publisher;

	@KafkaListener(topics = "exampleTopic")
	public void listen(String data) {
		// Publicamos un evento para actualizar el html
		publisher.publishEvent(new MessageListenerEvent(this, data));
		
		logger.info("NUEVO MENSAJE RECIBIDO: \"" + data + "\"");
	}
	
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	/**
	 * Clase que representa el evento de que se ha recibido un nuevo mensaje
	 */
	public class MessageListenerEvent extends ApplicationEvent {
		private static final long serialVersionUID = 1L;
		private String message;

		public MessageListenerEvent(Object source, String message) {
			super(source);
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
}