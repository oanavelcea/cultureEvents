package fr.dawan.cultureEvents;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//récupérer l'urlDemandee
		String requestedURI = request.getRequestURI();
		//si l'url demandée est /achats alors
		if(requestedURI.contains("/client/") || requestedURI.contains("/admin/") ) {
			//récupérer la session
			HttpSession session = request.getSession();
			//tester la présence de isConnected
			if(session.getAttribute("user_id")==null) {
				request.getRequestDispatcher("authenticate").forward(request, response);
			}
		
		} //fin si
		
		return true;
	}

	
	
}
