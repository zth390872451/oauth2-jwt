package babywei.authserver.conf;

import babywei.authserver.auth.JWTConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Created by Administrator on 2017/6/2.
 */
@Configuration
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
/*
    @Autowired
    private Http401UnauthorizedEntryPoint http401UnauthorizedEntryPoint;*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       /* http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().antMatchers( "/resources/**", "/login/**" ,"/check/**").permitAll()
//                .hasAnyAuthority("USER")
//                .anyRequest()
//                .authenticated()
//                .denyAll()
                .antMatchers("/admin/**").hasRole("ADMIN" )
                .antMatchers( "/member/**").access("hasRole('ADMIN') and hasRole('MEMBER')")
                .anyRequest().authenticated()
//                .and().formLogin()
                .and().headers().disable();*/
                http
                .csrf().disable()
                .cors().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/check/**","api/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/member/**").hasRole("MEMBER")
                .and()
                .httpBasic()
                .and().apply(securityConfigurerAdapter());
    }

    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer();
    }
}
