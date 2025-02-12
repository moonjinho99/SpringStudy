package interceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication auth) throws IOException, ServletException {

				CustomUserDetails cud = (CustomUserDetails) auth.getPrincipal();
				
				System.out.println("인증 후  : "+cud);
				
				String url ="/";
				
				Collection<GrantedAuthority> authlist = (Collection<GrantedAuthority>) cud.getAuthorities();
				Iterator<GrantedAuthority> authlist_it  = authlist.iterator();
				
				while(authlist_it.hasNext()){
					GrantedAuthority authority = authlist_it.next();
					
					if(authority.getAuthority().equals("ROLE_MEMBER"))
					{
						url = "/book/list";
					}
				}
				
				System.out.println("성공");
				request.getSession().setAttribute("member",cud);
				response.sendRedirect(url);
	}

}
