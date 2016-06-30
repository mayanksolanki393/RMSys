package cyb.rms.spring.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import cyb.rms.enums.RmsEnums.Role;

@Component("authSuccessHandler")
public class RMSAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	Logger LOG = Logger.getLogger(RMSAuthenticationSuccessHandler.class);
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)throws IOException, ServletException {
		LOG.debug(auth.getPrincipal());
		if(auth.getAuthorities().contains(new SimpleGrantedAuthority(Role.ROLE_ADMIN.toString()))){
			response.sendRedirect("admin");
		}
		else{
			response.sendRedirect("user");
		}
		
	}

}
