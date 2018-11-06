package fr.dawan.cultureEvents;

import java.lang.ProcessBuilder.Redirect;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.eclipse.jdt.internal.compiler.ast.ForeachStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.dawan.cultureEvents.beans.Event;
import fr.dawan.cultureEvents.beans.User;
import fr.dawan.cultureEvents.beans.User.Gender;
import fr.dawan.cultureEvents.dao.UserDao;
import fr.dawan.cultureEvents.formbeans.ContactForm;
import fr.dawan.cultureEvents.formbeans.EditUserForm;
import fr.dawan.cultureEvents.tools.EmailTools;
import fr.dawan.cultureEvents.tools.JsonTools;

@Controller
public class ClientController {

	@Autowired
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@RequestMapping(value = "/client/account", method = RequestMethod.GET)
	public String showAccount(HttpServletRequest request, Model model) {

		Long user_id = (Long) request.getSession().getAttribute("user_id");
		User u = userDao.findById(user_id);
		StringBuilder eventsJSON = new StringBuilder();
		List<Integer> eventsIds = u.getEventsId();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int j = 0; j < eventsIds.size(); j++) {

			try {
				Event evt = JsonTools.findEventByIdFromWS(eventsIds.get(j));

				eventsJSON.append("{title : \"" + evt.getTitle() + "\",").append("url : \"" + evt.getLink() + "\",")
						.append("start : \"" + sdf.format(evt.getDateStart()) + "\",")
						.append("end : \"" + sdf.format(evt.getDateEnd()) + "\"").append("}");
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (j < eventsIds.size() - 1)
				eventsJSON.append(",");
		}
		// System.out.println("EVENTS JSON = " + eventsJSON.toString());

		model.addAttribute("eventsJSON", eventsJSON.toString());
		/*
		 * { title : 'Click for Google', url : 'http://google.com/', start :
		 * '2018-03-28' }
		 */
		return "client/account";
	}

	@RequestMapping(value = "/client/disconnect", method = RequestMethod.GET)
	public String disconnect(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}

	// Affichage du formulaire des coordonnées rempli
	@RequestMapping("/client/coordonnees-utilisateur")
	public String displayUserDetails(@RequestParam("id") long id, Model model) {
		User user = userDao.findById(id);
		String dateBirthStr = new SimpleDateFormat("dd/MM/yyyy").format(user.getDateOfBirth());

		// R�cup�ration du jour, mois et ann�e pour le formulaire
		String[] dateSplit = dateBirthStr.split("/");
		int day = Integer.parseInt(dateSplit[0]);
		int month = Integer.parseInt(dateSplit[1]);
		int year = Integer.parseInt(dateSplit[2]);

		EditUserForm form = new EditUserForm(user.getName(), user.getGender().toString(), user.getEmail(),
				user.getPassword(), user.getAddress(), day, month, year, user.isAdmin());
		model.addAttribute("user-form", form);

		return "client/details";
	}

//Sauevgarde des modifications sur les coordonnées
	@RequestMapping(value = "/client/sauvegarde-coordonnees", method = RequestMethod.POST)
	public String saveUserDetails(HttpServletRequest request, @Valid @ModelAttribute("user-form") EditUserForm form,
			BindingResult result, Model model) {
		if (result.hasErrors() ) {
			if(result.getFieldError().getField().toString().equals("email")) {
				model.addAttribute("msg", "Errreur : le format de l'adresse email n'est pas correct !");
			}
			else {
				model.addAttribute("msg", "Errreur : au moins un des champs n'a pas été correctement rempli !");
			}
			model.addAttribute("errors", result);

			model.addAttribute("user-form", form);
			return "client/details";
		}

		long id = (Long) request.getSession().getAttribute("user_id");
		User user = userDao.findById(id);
		user.setName(form.getName());
		user.setAdmin(false);
		user.setEmail(form.getEmail());
		user.setPassword(form.getPassword());
		user.setAddress(form.getAddress());
		user.setGender(Enum.valueOf(Gender.class, form.getGender()));

		// R�up�ration et transformtion de la date de naissance au format sql
		String date = form.getYear() + "-" + form.getMonth() + "-" + form.getDay();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String msgMail = null;

		try {
			user.setDateOfBirth(sdf.parse(date));
			// sauvegarde des modifs en Bdd
			userDao.update(user);

		} catch (Exception e) {
			msgMail="Cette adresse mail est déjà utilisée";
			model.addAttribute("msgMail", msgMail);
//			return "client/details";
			displayUserDetails(form.getId(), model);
		}

						
		
		return "redirect:/client/account";
	}


	@RequestMapping(value = "client/ajouter-event-agenda", method = RequestMethod.GET)
	public String ajouterAgenda(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("eventId") int eventId, @RequestParam boolean contact, Model model) {
		if (request.getSession().getAttribute("user_id") == null) {
			return "redirect:/authenticate?contact=false";
		}
		Long user_id = (Long) request.getSession().getAttribute("user_id");

		User u = userDao.findById(user_id);
		int pos = u.getEventsId().indexOf(eventId);
		if (pos == -1) {
			u.getEventsId().add(eventId);
		}

		userDao.update(u);
		model.addAttribute("eventsId", u.getEventsId());

		return "redirect:/client/account";
	}

	@RequestMapping(value = {"/client/accueil", "/admin/accueil"}, method = RequestMethod.GET)
	public String accueil(Model model) {
		// Appel du WS et affichage des events
		List<Event> events;
		try {
			events = JsonTools.importAllEventsFromJson(null);
			model.addAttribute("events", events);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "Erreur de connexion au Web Service de Lille Métropole (" + e.getMessage() + ")");
		}

		return "client/accueil";
	}
	
	@RequestMapping("/client/contact") // @requestMapping(value="/autenticate", method=RequestMethod.GET)
	public ModelAndView showContact(HttpServletRequest request) {
		Map<String, Object> model = new HashMap<>();
		
		String email = request.getSession().getAttribute("user_email").toString();
		
		ContactForm cf = new ContactForm(email, "", "");
		model.put("contact-form", cf);
		return new ModelAndView("client/contact", model);
	}
	
	@RequestMapping(value = "/client/envoyer-message", method = RequestMethod.POST)
	public String sendMessage(HttpServletRequest request, @Valid @ModelAttribute("contact-form") ContactForm form,
			BindingResult result, Model model) {
		String messageErreur = null;
		String messageSuccess = null;
		
		if (result.hasErrors()) {
			model.addAttribute("errors", result);
			model.addAttribute("contact-form", form);
			model.addAttribute("msg", "Assurez-vous que les champs soient correctement remplis");
			
			return "client/contact";
		}
		
		String from = form.getEmail();
		String subject = form.getSubject();
		String message = form.getMessage();

		try {
			messageErreur = EmailTools.sendEmailToAdmin(from, subject, message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(messageErreur != null) {
			model.addAttribute("messageErreur", messageErreur);
			return "client/contact";
		}
		else {
			messageSuccess = "Votre message a bien �t� envoy�";
			model.addAttribute("messageSuccess", messageSuccess);
		}
		
		accueil(model);
		return "client/accueil";
		
	}
	
	

}
