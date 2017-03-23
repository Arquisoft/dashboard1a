package asw.stream.productorPrueba;

import java.util.Date;

import javax.annotation.ManagedBean;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import asw.stream.MessageListener;

@ManagedBean
public class MessageProducer {
	private static final Logger logger = Logger.getLogger(MessageListener.class);

	@Autowired
	KafkaTemplate<String, String> template;

	// Para que se envie un mensaje cada 3 segundos
	@Scheduled(cron = "*/3 * * * * *")
	public void send() {
		logger.info("ENVIANDO MENSAJE...");
		template.send("exampleTopic", "mensaje prueba " + new Date());
	}
}
