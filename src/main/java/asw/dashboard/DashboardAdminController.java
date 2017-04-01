package asw.dashboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

import asw.Application;
import asw.streamKafka.consumidor.listener.NewSuggestionListener.NewSuggestionEvent;
import asw.streamKafka.consumidor.listener.PositiveSuggestionListener.PositiveSuggestionEvent;

@Controller
public class DashboardAdminController {
	private List<SseEmitter> sseEmitters = Collections.synchronizedList(new ArrayList<>());

	@RequestMapping("/dashboardAdmin")
	public String landing() {
		return "dashboardAdmin";
	}

	@RequestMapping(value= "/newSuggestion")
	@EventListener
	public void newSuggestion(NewSuggestionEvent data) {
		SseEventBuilder event = SseEmitter.event().name("newSuggestion").data(data.getSuggestion().getIdentificador());
		sendData(event);
	}

	@RequestMapping(value = "/voteSuggestion")
	@EventListener
	public void voteSuggestion(PositiveSuggestionEvent data) {
		SseEventBuilder event = SseEmitter.event().name("voteSuggestion").data(data.getSuggestionId());
		sendData(event);
	}
	
	/************** METODOS AUXILIARES *************/

	@RequestMapping("/dashboardAdmin/updates")
	SseEmitter updateHTML() {
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
	
	void sendData(SseEventBuilder event) {
		synchronized (this.sseEmitters) {
			for (SseEmitter sseEmitter : this.sseEmitters) {
				try {
					sseEmitter.send(event);
				} catch (IOException e) {
					Application.logger.error("Se ha cerrado el navegador");
				}
			}
		}
	}
}