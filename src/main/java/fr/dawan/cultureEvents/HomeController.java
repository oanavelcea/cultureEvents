package fr.dawan.cultureEvents;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;

import org.apache.catalina.connector.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.dawan.cultureEvents.beans.Event;
import fr.dawan.cultureEvents.tools.EmailTools;
import fr.dawan.cultureEvents.tools.JsonTools;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		//Appel du WS et affichage des events
		List<Event> events;
		try {
			events = JsonTools.importAllEventsFromJson(null);
			model.addAttribute("events", events);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg","Erreur de connexion au Web Service de Lille Métropole ("+ e.getMessage() + ")");
		}
	
		
		return "home";
	}
	
	@RequestMapping(value = {"/evenements", "/client/evenements", "/admin/evenements"}, method = RequestMethod.GET)
	public String home(@QueryParam("tag") String tag, Model model, HttpServletRequest request) {
		//Appel du WS et affichage des events
		List<Event> events;
		try {
			events = JsonTools.importAllEventsFromJson(tag);
			model.addAttribute("events", events);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg","Erreur de connexion au Web Service de Lille Métropole ("+ e.getMessage() + ")");
		}
		
		
	if(request.getSession().getAttribute("user_id")!=null) {
		return "client/accueil";
	}
	if( request.getSession().getAttribute("user_admin")!=null) {
		return "admin/accueil";
	}
		
		return "home";
	}
	
	
	
}