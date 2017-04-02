package asw.dashboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

import asw.Application;
import asw.dbManagement.repository.SuggestionRepository;
import asw.streamKafka.productor.MessageProducer;

@Controller
public class DashboardAdminController {
	@Autowired
	private SuggestionRepository suggestionRepository;
	
	private List<SseEmitter> sseEmitters = Collections.synchronizedList(new ArrayList<>());

	@RequestMapping("/dashboardAdmin")
	public String landing(Model model) {
		// Muestra todas las sugerencias de la base de datos
		model.addAttribute("allSuggestions", suggestionRepository.findAll());
		return "dashboardAdmin";
	}

	@RequestMapping(value= "/newSuggestion")
	@KafkaListener(topics = MessageProducer.NEW_SUGGESTION)
	public void newSuggestion(String data) {
		SseEventBuilder event = SseEmitter.event().name("newSuggestion").data(data);
		sendData(event);
	}
	
	@RequestMapping(value = "/alertSuggestion")
	@KafkaListener(topics = MessageProducer.ALERT_SUGGESTION)
	public void alertSuggestion(String data) {
		SseEventBuilder event = SseEmitter.event().name("alertSuggestion").data(data);
		sendData(event);
	}

	@RequestMapping(value = "/voteSuggestion")
	@KafkaListener(topics = MessageProducer.POSITIVE_SUGGESTION)
	public void voteSuggestion(String data) {
		SseEventBuilder event = SseEmitter.event().name("voteSuggestion").data(data);
		sendData(event);
	}
	
	@RequestMapping(value = "/newComment")
	@KafkaListener(topics = MessageProducer.NEW_COMMENT)
	public void newComment(String data) {
		SseEventBuilder event = SseEmitter.event().name("newComment").data(data);
		sendData(event);
	}
	
	// Quedaria otra pantalla para los comentarios por sugerencia
	
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