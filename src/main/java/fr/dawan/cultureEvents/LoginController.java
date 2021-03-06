package fr.dawan.cultureEvents;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.dawan.cultureEvents.beans.User;
import fr.dawan.cultureEvents.dao.UserDao;
import fr.dawan.cultureEvents.formbeans.LoginForm;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@Autowired
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@RequestMapping("/authenticate")
	public ModelAndView showLogin(@RequestParam("contact") boolean contact) {
		Map<String, Object> model = new HashMap<>();
		LoginForm lf = new LoginForm("", "", contact);
		model.put("login-form", lf);
		return new ModelAndView("login", model);
	}

	@RequestMapping(value = "/check-login", method = RequestMethod.POST)
	public String checkLogin(HttpServletRequest request, @Valid @ModelAttribute("login-form") LoginForm form,
								BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("errors", result);
			model.addAttribute("login-form", form);
			return "login";
		}

		User u = userDao.findByEmail(form.getUsername());
		
		if (u != null && u.getPassword().equals(form.getPassword())) {
			request.getSession().setAttribute("user_id", u.getId());
			request.getSession().setAttribute("user_name", u.getName());
			request.getSession().setAttribute("user_email", u.getEmail());
			request.getSession().setAttribute("user_admin", u.isAdmin());
			
			if (u.isAdmin())
				return "redirect:/admin/dashboard";
			
			else if(form.isContact()) {
				return "redirect:/client/contact";
			}
			
			else
				return "redirect:/client/account";
		} else {
			model.addAttribute("login-form", form);
			model.addAttribute("msg", "Error : incorrect login or password !");
			return "login";
		}
	}

}
