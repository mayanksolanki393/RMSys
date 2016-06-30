package cyb.rms.spring.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import cyb.rms.daos.IUserDao;
import cyb.rms.enums.RmsEnums.Role;
import cyb.rms.exceptions.DaoException;

@Component(value="rmsUserDetailsService")
public class RMSUserDetailsService implements UserDetailsService {

	@Autowired
	IUserDao userDao;
	
	private static Logger LOG = Logger.getLogger(RMSUserDetailsService.class);
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		cyb.rms.entities.User user;
		try{
			user = userDao.getUserByUsername(username);
			LOG.info("User Details : "+user);
		}
		catch(DaoException ex){
			LOG.error(ex);
			throw new UsernameNotFoundException("User not found for username [ "+username+" ]");
		}
		List<GrantedAuthority> authorities = buildUserAuthority(user.getRole());
		User secUser = buildUserForAuthentication(user, authorities);
		LOG.info(secUser);
		return secUser;
	}
	
	private User buildUserForAuthentication(cyb.rms.entities.User user, List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Role userRole) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		setAuths.add(new SimpleGrantedAuthority(userRole.toString()));
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
		LOG.info("Authority Details : "+Result);
		return Result;
	}
}
