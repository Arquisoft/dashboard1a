package asw.streamKafka.consumidor.listener;

import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

public abstract class AbstractKafkaListener implements ApplicationEventPublisherAware {
	private ApplicationEventPublisher publisher;
	private JacksonJsonParser parser = new JacksonJsonParser();
	
	public JacksonJsonParser getParser() {
		return parser;
	}

	public ApplicationEventPublisher getPublisher() {
		return publisher;
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

}