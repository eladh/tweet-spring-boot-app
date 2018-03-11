package challenge.controllers;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class RootController implements ErrorController {

   private static final Logger logger = Logger.getLogger(RootController.class.getName());

   private static final String PATH = "/error";

   @RequestMapping(value = PATH)
   public String error() {
      logger.log(Level.SEVERE , "got general error");
      return "Twitter API Server /error";
   }

   @Override
   public String getErrorPath() {
      return PATH;
   }
}