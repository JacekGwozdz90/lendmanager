package lendmanager.signin;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class SigninController {

    @RequestMapping(value = "signinbyfacebook")
    public String signinByFacebook(HttpServletRequest request, HttpServletResponse response) {
        Facebook facebook = new FacebookFactory().getInstance();
        facebook.setOAuthAppId("1382220198743129", "b1ee17e63ca293addfb93659d5afa781");
        facebook.setOAuthPermissions("email,user_friends");
        //  facebook.setOAuthAccessToken(new AccessToken(accessToken, null));

        request.getSession().setAttribute("facebook", facebook);
        StringBuffer callbackURL = request.getRequestURL();
        System.out.println(callbackURL.toString());
        int index = callbackURL.lastIndexOf("/");
        callbackURL.replace(index, callbackURL.length(), "").append("/callback");
        try {
            response.sendRedirect(facebook.getOAuthAuthorizationURL(callbackURL.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "signin/callback";
    }

    @RequestMapping(value = "callback")
    public String callback() {
        System.out.println("Callback method!");
        return "signin/callback";
    }

    @RequestMapping(value = "signin")
    public String signin() {
        return "signin/signin";
    }


}
