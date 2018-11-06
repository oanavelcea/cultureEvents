package fr.dawan.cultureEvents;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import fr.dawan.cultureEvents.beans.User;
import fr.dawan.cultureEvents.beans.User.Gender;
import fr.dawan.cultureEvents.dao.UserDao;
import fr.dawan.cultureEvents.formbeans.EditUserForm;

@Controller
public class AdminController {

	@Autowired
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@RequestMapping(value = "/admin/dashboard", method = RequestMethod.GET)
	public String showAdminDashboard() {
		return "admin/dashboard";
	}

	@RequestMapping(value = "/admin/liste-utilisateurs", method = RequestMethod.GET)
	public String listerUtilisateurs(@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "max", required = false) Integer max, Model model) {
		if (max == null || max == 0)
			max = 15;
		if (page == null || page == 0)
			page = 1;

		int start = (page - 1) * max;
		List<User> users = userDao.findAll(start, max);

		long nb = userDao.nbUsers();
		boolean suivExist = (page * max) < nb;

		model.addAttribute("users", users);
		model.addAttribute("suivExist", suivExist);
		model.addAttribute("page", page);

		return "admin/users";
	}

	@RequestMapping(value = "/admin/disconnect", method = RequestMethod.GET)
	public String disconnect(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "/admin/supprimer-utilisateur", method = RequestMethod.GET)
	public String deleteUser(@RequestParam(name = "id", required = false) long id, Model model) {
		try {
			userDao.delete(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "redirect:/admin/liste-utilisateurs";
	}

	// Préparation du model et redirection vers le formulaire de modification
	@RequestMapping(value = "/admin/modifier-utilisateur", method = RequestMethod.GET)
	public ModelAndView modifierUser(@RequestParam(name = "id", required = false) long id) {
		Map<String, Object> model = new HashMap<>();

		User user = userDao.findById(id);
		String dateBirthStr = new SimpleDateFormat("dd/MM/yyyy").format(user.getDateOfBirth());

		// R�cup�ration du jour, mois et ann�e pour le formulaire
		String[] dateSplit = dateBirthStr.split("/");
		int day = Integer.parseInt(dateSplit[0]);
		int month = Integer.parseInt(dateSplit[1]);
		int year = Integer.parseInt(dateSplit[2]);

		EditUserForm form = new EditUserForm(user.getName(), user.getGender().toString(), user.getEmail(),
				user.getPassword(), user.getAddress(), day, month, year, user.isAdmin());
		form.setId(user.getId());
		model.put("edit-user-form", form);
		model.put("user_name", user.getName());

		return new ModelAndView("admin/edituser", model);
	}

	// Controle des éléments issus du formulaire
	@RequestMapping(value = "/admin/save-user", method = RequestMethod.POST)
	public ModelAndView updateUser(HttpServletRequest request,
			@Valid @ModelAttribute("edit-user-form") EditUserForm form, BindingResult result)
			throws java.text.ParseException {
		Map<String, Object> model = new HashMap<>();


		if (result.hasErrors()) {
			model.put("errors", result);
			model.put("edit-user-form", form);
			
			if(result.getFieldError().getField().toString().equals("email")) {
				model.put("msg", "Errreur : le format de l'adresse email n'est pas correct !");
			}
			else {
				model.put("msg", "Errreur : au moins un des champs n'a pas été correctement rempli !");
			}
			return new ModelAndView("admin/edituser", model);
		}

		// enregistrement des données dans la BDD
		// long id =
		User user = userDao.findById(form.getId());
		user.setName(form.getName());
		user.setAdmin(form.isAdmin());
		user.setEmail(form.getEmail());
		user.setPassword(form.getPassword());
		user.setAddress(form.getAddress());
		user.setGender(Enum.valueOf(Gender.class, form.getGender()));

		// R�up�ration et transformtion de la date de naissance au format sql
		String date = form.getYear() + "-" + form.getMonth() + "-" + form.getDay();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date utilDate = new Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		user.setCreationDate(sqlDate);

		String msg = null;
		try {
			user.setDateOfBirth(sdf.parse(date));
			// sauvegarde des modifs en Bdd
			userDao.update(user);

		} catch (ParseException e) {
			e.printStackTrace();
			msg="Cette adresse mail est déjà utilisée";
			model.put("msg", msg);
			model.put("errors", result);
			model.put("user-form", form);
			return new ModelAndView("admin/edituser", model);
		}

		userDao.update(user);

		return new ModelAndView("admin/dashboard", model);

	}
}
