package fr.dawan.cultureEvents;

import java.time.LocalDate;
import java.util.HashMap;
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
import fr.dawan.cultureEvents.dao.DateUtils;
import fr.dawan.cultureEvents.dao.UserDao;
import fr.dawan.cultureEvents.formbeans.SignUpForm;

@Controller
public class SignupController {
	
	@Autowired
	private UserDao userDao;

	@RequestMapping("/signup")
	public ModelAndView signup() {
			Map<String,	Object> model = new HashMap<>();
			//récupération de l'objet SignUpForm
			SignUpForm form = new SignUpForm("", Gender.M, "", "", "", DateUtils.asDate(LocalDate.now()));
			model.put("signup-form", form);
			return new ModelAndView("signup", model);
	}
	
	
	//
	@RequestMapping(value="/validate-signup", method=RequestMethod.POST)
	public ModelAndView checkLogin(HttpServletRequest request,
			@Valid @ModelAttribute("signup-form")SignUpForm form, BindingResult result) {
		Map<String,	Object> model = new HashMap<>();
		
		if(result.hasErrors()) {
			model.put("errors",result);
			model.put("signup-form", form);
			return new ModelAndView("signup",model);
		}
		
		//enregistrement des données dans la BDD
		User u = new User();
		u.setName(form.getName());
		u.setEmail(form.getEmail());
		u.setPassword(form.getPassword());
		u.setAddress(form.getAddress());
		u.setGender(form.getGender());
		u.setDateOfBirth(form.getDateOfBirth());
		userDao.save(u);
	
		return new ModelAndView("client/accueilClient",model);
	}
	
}
