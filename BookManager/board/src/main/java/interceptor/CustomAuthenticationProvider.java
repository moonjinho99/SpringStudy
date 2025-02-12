package interceptor;

import java.sql.SQLException;

import org.mariadb.jdbc.internal.com.send.InterfaceAuthSwitchSendResponsePacket;
import org.mariadb.jdbc.internal.io.input.PacketInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		
		CustomUserDetails user = (CustomUserDetails)userDetailService.loadUserByUsername(username);

		System.out.println("사용자 정보 : " + user);
		
		if(!pwdEncoder.matches(password, user.getPassword()))
		{
			throw new BadCredentialsException("pw 안맞음");
		}
		if(pwdEncoder.matches(password, user.getPassword())){
			System.out.println("패스워드 일치");
		}
		
		System.out.println("넘어가는 화면 : "+new UsernamePasswordAuthenticationToken(username, password,user.getAuthorities()).toString());
		
		return new UsernamePasswordAuthenticationToken(user, password,user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	
	
}
