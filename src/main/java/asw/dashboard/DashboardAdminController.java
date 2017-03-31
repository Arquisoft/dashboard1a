package asw.dashboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import asw.Application;
import asw.streamKafka.consumidor.listener.PositiveSuggestionListener.PositiveSuggestionEvent;

@Controller
public class DashboardAdminController {
	private List<SseEmitter> sseEmitters = Collections.synchronizedList(new ArrayList<>());

	@RequestMapping("/dashboardAdmin")
	public String handleRequest() {
		return "dashboardAdmin";
	}

	@RequestMapping(path = "/dashboardAdmin", method = RequestMethod.POST)
	@EventListener
	public String updatePositiveSuggestion(PositiveSuggestionEvent message) {
		sendData("");
		return "";
	}
	
	@RequestMapping("/dashboardAdmin/updatePositiveSuggestion")
	SseEmitter subscribeUpdates() {
		return updateHTML();
	}

	/************** METODOS AUXILIARES *************/
	
	void sendData(String data) {
		synchronized (this.sseEmitters) {
			for (SseEmitter sseEmitter : this.sseEmitters) {
				try {
					sseEmitter.send("");
				} catch (IOException e) {
					Application.logger.error("Se ha cerrado el navegador");
				}
			}
		}
	}
	
	SseEmitter updateHTML(){
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
}