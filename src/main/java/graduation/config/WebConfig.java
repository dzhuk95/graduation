package graduation.config;

import graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Bean
    UserDetailsService authenticationProvider() {
        return userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.userDetailsService(authenticationProvider()).authorizeRequests().antMatchers("/api/registration").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/").loginProcessingUrl("/auth").usernameParameter("username")
                .and()
                .logout().logoutUrl("logout")
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .csrf().disable().headers().frameOptions().disable().and().rememberMe();
    }

}
