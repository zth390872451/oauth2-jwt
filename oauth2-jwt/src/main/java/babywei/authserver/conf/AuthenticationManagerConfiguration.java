package babywei.authserver.conf;

import babywei.authserver.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.sql.DataSource;

/**
 * 授权服务器bean初始化顺序问题，在低版本spring boot下，会导致 Token 由内存生成，而不是Redis，尽管配置按照Redis配置。
 * https://github.com/spring-projects/spring-security-oauth/issues/215
 * https://github.com/spring-projects/spring-security-oauth/issues/222
 */
@Configuration
@EnableWebSecurity(debug = true)
public class AuthenticationManagerConfiguration extends GlobalAuthenticationConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)/*.passwordEncoder(new BCryptPasswordEncoder())*/;
    }
}
