package fr.dawan.cultureEvents;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.dawan.cultureEvents.beans.Event;
import fr.dawan.cultureEvents.dao.JsonTools;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		//logger.info("Welcome home! The client locale is {}.", locale);
		
		//Appel du WS et affichage des events
		List<Event> events;
		try {
			events = JsonTools.importAllEventsFromJson();
			model.addAttribute("events", events);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg","Erreur de connexion au Web Service de Lille Métropole ("+ e.getMessage() + ")");
		}
	
		
		return "home";
	}
	
}
