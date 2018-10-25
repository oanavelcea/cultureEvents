package fr.dawan.cultureEvents;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.dawan.cultureEvents.beans.User;
import fr.dawan.cultureEvents.dao.UserDao;
import fr.dawan.cultureEvents.formbeans.LoginForm;

@Controller
public class AdminController {

	@Autowired
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@RequestMapping(value="/admin/dashboard", method=RequestMethod.GET)
	public String showAdminDashboard() {
		return "admin/dashboard";
	}
	
	@RequestMapping(value = "/admin/liste-utilisateurs", method = RequestMethod.GET)
	public String listerUtilisateurs(@RequestParam("page") int page, @RequestParam("max") int max, Model model) {
		if(max==0) max=15;
		if(page==0) page =1;
		
		int start = (page-1)*max; 
		List<User> users = userDao.findAll(start, max);
		
		long nb = userDao.nbUsers();
		boolean suivExist = (page*max)<nb;
		
		model.addAttribute("users", users);
		model.addAttribute("suivExist", suivExist);
		model.addAttribute("page", page);
		
		return "admin/users";
	}
	
	@RequestMapping(value="/admin/disconnect", method=RequestMethod.GET)
	public String disconnect(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value = "/admin/modifier-utilisateur", method = RequestMethod.GET)
	public ModelAndView updateUser() {
			/*Map<String,	Object> model = new HashMap<>();
			ModifUserForm mf = new ModifUserForm("","");
			model.put("modifUser-form", mf);
			*/
			//return new ModelAndView("modifUser", model);
		return null;
	}

	
}
