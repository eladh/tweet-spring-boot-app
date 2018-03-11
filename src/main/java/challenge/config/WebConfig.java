package challenge.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;


@Configuration
public class WebConfig {

   @Component
   public class RestResponseStatusExceptionResolver extends AbstractHandlerExceptionResolver {

      private final Logger logger = Logger.getLogger(RestResponseStatusExceptionResolver.class.getName());

      @Override
      protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
         logger.log(Level.SEVERE ,"exceptionHandler - ",ex);
         return null;
      }
   }
}