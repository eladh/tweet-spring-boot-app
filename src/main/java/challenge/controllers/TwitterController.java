package challenge.controllers;

import challenge.config.SecurityConfiguration;
import challenge.entities.Feed;
import challenge.entities.People;
import challenge.services.RelationServices;
import challenge.dao.TwitterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api/twitter")
public class TwitterController {

   private final SecurityConfiguration mySecurityConfiguration;

   private final TwitterDao twitterDao;

   private final RelationServices relationServices;

   // TODO:  separate DAO from Controller
   // TODO:  activeUserId shouldn't be exposed directly


   @Autowired
   public TwitterController(SecurityConfiguration mySecurityConfiguration, TwitterDao myTwitterServices, RelationServices relationServices) {
      this.mySecurityConfiguration = mySecurityConfiguration;
      this.twitterDao = myTwitterServices;
      this.relationServices = relationServices;
   }

   @RequestMapping(value = "/messages/{filter}", method = GET)
   public List<Feed> getMessages(@PathVariable("filter") String filter) {
      return twitterDao.findUserMessages(mySecurityConfiguration.getActiveUserId() ,filter);
   }

   @RequestMapping(value = "/messages", method = GET)
   public List<Feed> getMessages()  {
      return twitterDao.findUserMessages(mySecurityConfiguration.getActiveUserId() ,"");
   }

   @RequestMapping(value = "/followers", method = GET)
   public List<People> getFollowers() {
      return twitterDao.findUserFollowers(mySecurityConfiguration.getActiveUserId());
   }

   @RequestMapping(value = "/following", method = GET)
   public List<People> getFollowing() {
      return twitterDao.findUserFollowing(mySecurityConfiguration.getActiveUserId());
   }

   @RequestMapping(value = "/shortestPath/{userId}", method = GET)
   public int shortestPath(@PathVariable("userId") Integer followId) {
      Integer activeUserId = mySecurityConfiguration.getActiveUserId();
      return relationServices.findShortestPath(activeUserId ,followId);
   }

   @RequestMapping(value = "/follow/{userId}", method = POST)
   public int follow(@PathVariable("userId") Integer followId) {
      Integer activeUserId = mySecurityConfiguration.getActiveUserId();
      int result = twitterDao.followAnotherUser(activeUserId, followId);
      if (result == 1) { relationServices.addFollower(activeUserId , followId); }

      return result;
   }

   @RequestMapping(value = "/remove-follow/{userId}", method = POST)
   public int removeFollow(@PathVariable("userId") Integer followId) {
      Integer activeUserId = mySecurityConfiguration.getActiveUserId();
      int result = twitterDao.unfollowAnotherUser(activeUserId, followId);
      if (result == 1) { relationServices.removeFollower(activeUserId , followId); }

      return result;
   }


}