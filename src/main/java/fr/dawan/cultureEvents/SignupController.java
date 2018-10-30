package fr.dawan.cultureEvents;

import java.text.ParseException;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.dawan.cultureEvents.beans.User;
import fr.dawan.cultureEvents.beans.User.Gender;
import fr.dawan.cultureEvents.dao.UserDao;
import fr.dawan.cultureEvents.formbeans.SignUpForm;

@Controller
public class SignupController {

	@Autowired
	private UserDao userDao;

	@RequestMapping("/signup")
	public ModelAndView signup() {
		Map<String, Object> model = new HashMap<>();
		// récupération de l'objet SignUpForm
		SignUpForm form = new SignUpForm("", "M", "", "", "", 1, 1, 1970);
		model.put("signup-form", form);
		List<Integer> days = new ArrayList<>();
		for (int i = 1; i <= 31; i++) {
			days.add(i);
		}
		List<Integer> months = new ArrayList<>();
		for (int i = 1; i <= 12; i++) {
			months.add(i);
		}

		List<Integer> years = new ArrayList<>();
		for (int i = 1900; i <= 2018; i++) {
			years.add(i);
		}

		model.put("days", days);
		model.put("months", months);
		model.put("years", years);

		return new ModelAndView("signup", model);
	}

	@RequestMapping(value = "/validate-signup", method = RequestMethod.POST)
	public ModelAndView checkLogin(HttpServletRequest request, @Valid @ModelAttribute("signup-form") SignUpForm form,
			BindingResult result) {
		Map<String, Object> model = new HashMap<>();

		if (result.hasErrors()) {
			model.put("errors", result);
			model.put("signup-form", form);
			return new ModelAndView("signup", model);
		}

		User u = new User();
		u.setName(form.getName());
		u.setEmail(form.getEmail());
		u.setPassword(form.getPassword());
		u.setAddress(form.getAddress());
		u.setGender(Enum.valueOf(Gender.class, form.getGender()));

		// R�up�ration et transformtion de la date de naissance au format sql
		String date = form.getYear() + "-" + form.getMonth() + "-" + form.getDay();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date utilDate = new Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		u.setCreationDate(sqlDate);
		
		try {
			u.setDateOfBirth(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//on traite l'erreur retournée si l'adresse mail existe déjà en base de données
		String msg = null;
		try {
			userDao.save(u);
		} catch (Exception e) {
			msg="Cette adresse mail est déjà utilisée";
			model.put("msg", msg);
			return new ModelAndView("signup", model);
		}

		return new ModelAndView("client/account", model);
	}

}
