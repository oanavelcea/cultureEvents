package fr.dawan.cultureEvents;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import fr.dawan.cultureEvents.beans.User;
import fr.dawan.cultureEvents.beans.User.Gender;
import fr.dawan.cultureEvents.dao.UserDao;
import fr.dawan.cultureEvents.formbeans.EditUserForm;

@Controller
public class ClientController {

	@Autowired
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@RequestMapping(value = "/client/account", method = RequestMethod.GET)
	public String showAccount() {
		return "client/account";
	}

	@RequestMapping(value = "/client/disconnect", method = RequestMethod.GET)
	public String disconnect(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}

	// Affichage du formulaire des coordonnÃ©es rempli
	@RequestMapping("/client/coordonnees-utilisateur")
	public String displayUserDetails(@RequestParam("id") long id, Model model) {
		User user = userDao.findById(id);
		String dateBirthStr = new SimpleDateFormat("dd/MM/yyyy").format(user.getDateOfBirth());

		// Récupération du jour, mois et année pour le formulaire
		String[] dateSplit = dateBirthStr.split("/");
		int day = Integer.parseInt(dateSplit[0]);
		int month = Integer.parseInt(dateSplit[1]);
		int year = Integer.parseInt(dateSplit[2]);

		List days = new ArrayList();
		for (int i = 1; i <= 31; i++) {
			days.add(i);
		}
		List months = new ArrayList<>();
		for (int i = 1; i <= 12; i++) {
			months.add(i);
		}

		List years = new ArrayList<>();
		for (int i = 1900; i <= 2018; i++) {
			years.add(i);
		}

		EditUserForm form = new EditUserForm(user.getName(), user.getGender().toString(), user.getEmail(),
				user.getPassword(), user.getAddress(), day, month, year, user.isAdmin());
		model.addAttribute("user-form", form);
		model.addAttribute("days", days);
		model.addAttribute("months", months);
		model.addAttribute("years", years);

		return "client/details";
	}

//Sauevgarde des modifications sur les coordonnÃ©es
	@RequestMapping(value = "/client/sauvegarde-coordonnees", method = RequestMethod.POST)
	public String saveUserDetails(HttpServletRequest request, @Valid @ModelAttribute("user-form") EditUserForm form,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
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

		// Réupération et transformtion de la date de naissance au format sql
		String date = form.getYear() + "-" + form.getMonth() + "-" + form.getDay();
		System.out.println("date = " + date);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

//		Date utilDate = new Date();
//		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//		user.setCreationDate(sqlDate);

		try {
			user.setDateOfBirth(sdf.parse(date));
			// sauvegarde des modifs en Bdd
			userDao.update(user);

		} catch (ParseException e) {
			e.printStackTrace();
			model.addAttribute("errors", result);
			model.addAttribute("user-form", form);
			return "client/details";
		}
		return "redirect:/client/account";
	}

}
