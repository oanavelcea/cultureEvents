package fr.dawan.cultureEvents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.dawan.cultureEvents.beans.User;
import fr.dawan.cultureEvents.dao.UserDao;

@Controller
public class UserDetailsController {

	@Autowired
	private UserDao userDao;

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

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
