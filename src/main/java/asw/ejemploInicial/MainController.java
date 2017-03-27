package asw.ejemploInicial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
public class MainController {

	private static final Logger logger = Logger.getLogger(MainController.class);
	private List<SseEmitter> sseEmitters = Collections.synchronizedList(new ArrayList<>());
	// private SseEmitter emitter = new SseEmitter();

	@RequestMapping("/")
	public String handleRequest() {
		return "index";
	}

	@RequestMapping("/updates")
	SseEmitter subscribeUpdates() {
		SseEmitter sseEmitter = new SseEmitter();
		synchronized (this.sseEmitters) {
			this.sseEmitters.add(sseEmitter);
			sseEmitter.onCompletion(() -> {
				synchronized (this.sseEmitters) {
					this.sseEmitters.remove(sseEmitter);
				}
			});
		}
		return sseEmitter;
	}

	@RequestMapping(path = "/", method = RequestMethod.POST)
	@KafkaListener(topics="exampleTopic")
	public String showMessage(String data) {
		synchronized (this.sseEmitters) {
			for (SseEmitter sseEmitter : this.sseEmitters) {
				try {
					sseEmitter.send(data);
				} catch (Exception e) {
					logger.error("Se ha cerrado el navegador");
				}
			}
		}
		return data;
	}
	
	// Va mostrando los mensajes por pantalla, solo con un SseEmitter
	// @RequestMapping("/emitter")
	// @KafkaListener(topics = "exampleTopic")
	// public SseEmitter showMessages(String data) {
	// try {
	// emitter.send(data);
	// } catch (Exception e) {
	// logger.error("Se ha cerrado el navegador");
	// }
	// return emitter;
	// }
}