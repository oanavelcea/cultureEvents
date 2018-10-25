package fr.dawan.cultureEvents;

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

@Controller
public class ClientController {
	
	@Autowired
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@RequestMapping(value="/client/account", method=RequestMethod.GET)
	public String showAccount() {
		return "client/account";
	}
	
	@RequestMapping(value="/client/disconnect", method=RequestMethod.GET)
	public String disconnect(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/client/coordonnees-utilisateur")
	public String listerCoordonnees(@RequestParam("id") long id, Model model) {
		User user = userDao.findById(id);
		model.addAttribute("user", user);
		return "client/details";
	}
	
	@RequestMapping(value="client/modify-details")
	public ModelAndView updateDetails() {
		
		return null;
	}

	
	
	
}
