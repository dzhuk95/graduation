package graduation.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {
/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/api/signUp", "/api/registration").permitAll()
                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage("/login").usernameParameter("email").passwordParameter("password")
//                .loginProcessingUrl("/spring_security_check")
//                .defaultSuccessUrl("/project", true)
//                .permitAll()
                .and()
                .logout().logoutUrl("/logout").permitAll()
                .and()
                .addFilterAfter(new WebFilter(), UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable().headers().frameOptions().disable().and().rememberMe();
    }

    */
}
