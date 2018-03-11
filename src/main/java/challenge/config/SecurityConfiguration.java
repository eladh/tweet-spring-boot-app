package challenge.config;

import com.google.common.collect.Maps;
import challenge.dao.TwitterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

   private final TwitterDao myTwitterServices;
   private Map<String ,Integer> userName2Id = Maps.newHashMap();

   @Autowired
   public SecurityConfiguration(TwitterDao myTwitterServices) {

      this.myTwitterServices = myTwitterServices;
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
            .antMatchers("/h2-console/*").permitAll()
            .anyRequest().authenticated()
            .and().httpBasic();

      http.csrf().disable();
      http.headers().frameOptions().disable();
   }

   @Autowired
   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
      auth.inMemoryAuthentication().withUser("admin").password("password").roles("USER");

      myTwitterServices.getAllUsers().forEach(user -> {
         try {
            userName2Id.put(user.getHandle() ,user.getId());
            auth.inMemoryAuthentication().withUser(user.getHandle()).password(user.getPassword()).roles("USER");
         } catch (Exception theE) {
            // log + skip user
         }
      });
   }

   public Integer getActiveUserId() {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      return userName2Id.get(authentication.getName());
   }
}