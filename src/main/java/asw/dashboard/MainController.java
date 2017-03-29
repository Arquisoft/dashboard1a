package asw.dashboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import asw.streamKafka.consumidor.MessageListener.MessageListenerEvent;

@Controller
public class MainController {

	private static final Logger logger = Logger.getLogger(MainController.class);
	private List<SseEmitter> sseEmitters = Collections.synchronizedList(new ArrayList<>());
	private JacksonJsonParser parser = new JacksonJsonParser();

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
	@EventListener
	public String showMessage(MessageListenerEvent message) {
		String data = message.getMessage();
		synchronized (this.sseEmitters) {
			for (SseEmitter sseEmitter : this.sseEmitters) {
				try {
					sseEmitter.send(parser.parseMap(data).get("message"));
				} catch (IOException e) {
					logger.error("Se ha cerrado el navegador");
				}
			}
		}
		return data;
	}
}