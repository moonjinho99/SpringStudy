package interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tck.board.mapper.MemberMapper;

public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		CustomUserDetails user = memberMapper.loginID(username);
		
		UserDetails loginuser = null;
		
		
		if(username == null){
			throw new UsernameNotFoundException(username);
		}
		
		
		
		System.out.println("CustomUserDetailsService 접근");
		
		return user;
	}

}
