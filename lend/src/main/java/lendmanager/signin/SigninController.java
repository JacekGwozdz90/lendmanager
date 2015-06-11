package lendmanager.signin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lendmanager.account.Account;
import lendmanager.account.AccountRepository;
import lendmanager.account.Role;
import lendmanager.account.UserService;
import lendmanager.person.Person;
import lendmanager.person.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Friend;
import facebook4j.Reading;
import facebook4j.User;

@Controller
public class SigninController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	
	@Autowired
	private UserService userService;

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
			List<Person> friends = new ArrayList<Person>();		
			for(Friend f : facebook.getFriends()){
				Person p = new Person();
				p.setFacebookId(f.getId());
				p.setFirstName(f.getName().split(" ")[0]);
				p.setLastName(f.getName().split(" ")[f.getName().split(" ").length-1]);
				friends.add(p);
			}
			personRepository.save(friends);
			Account account = new Account();
			account.setId(facebook.getId());
			User user = facebook.getUser(facebook.getId(), new Reading().fields("email"));
			account.setEmail(user.getEmail());
			account.setPasswordHash(user.getEmail()+user.getEmail());
			account.setRole( Role.ROLE_USER);
			accountRepository.save(account);
			Person person = new Person();
			person.setFacebookId(facebook.getId());
			person.setFirstName(facebook.getName().split(" ")[0]);
			person.setLastName(facebook.getName().split(" ")[facebook.getName().split(" ").length-1]);
			personRepository.save(person);
			userService.signin(account);

		} catch (FacebookException e) {
			// TODO - logging
			e.printStackTrace();
		}
		
		// TODO - create Account and Person for this facebook user!
		// TODO - redirect to /home
		return "redirect:/";
	}

	@RequestMapping(value = "signin")
	public String signin() {
		return "signin/signin";
	}

}
