package babywei.authserver.service;

import babywei.authserver.domain.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);
	@Autowired
	private MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.findOneByMobile(username);
		if (member == null) {
			log.error("用户不存在 username={}",username);
			throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
		}

		User principal = new User(member.getMobile(), member.getPassword(), true, true, true, true,member.getGrantedAuthority());
		return principal;
	}



}
