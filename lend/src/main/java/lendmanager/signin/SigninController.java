package lendmanager.signin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;

@Controller
public class SigninController {

	@RequestMapping(value = "signinbyfacebook")
	public void signinByFacebook(HttpServletRequest request, HttpServletResponse response) throws FacebookException {
		Facebook facebook = new FacebookFactory().getInstance();
		facebook.setOAuthAppId("1382220198743129", "b1ee17e63ca293addfb93659d5afa781");
		facebook.setOAuthPermissions("email,user_friends");
		request.getSession().setAttribute("facebook", facebook);
		try {
			response.sendRedirect(facebook.getOAuthAuthorizationURL("http://localhost:8080/lend/callback"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "callback")
	public String callback(Model model, HttpServletRequest request, HttpServletResponse response) {
		Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
		String oauthCode = request.getParameter("code");
		try {
			facebook.getOAuthAccessToken(oauthCode);
			model.addAttribute("name", facebook.getName());
		} catch (FacebookException e) {
			// TODO - logging
			e.printStackTrace();
		}
		// TODO - create Account and Person for this facebook user!
		// TODO - redirect to /home
		return "signin/callback";
	}

	@RequestMapping(value = "signin")
	public String signin() {
		return "signin/signin";
	}

}
