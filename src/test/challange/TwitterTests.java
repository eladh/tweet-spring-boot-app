package challenge;

import challenge.dao.TwitterDao;
import challenge.entities.Feed;
import challenge.entities.People;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ChallengeApplication.class)
public class TwitterTests {


   @Autowired
   private TwitterDao myTwitterServices;

   private static final Integer BATMAN = 1;
   private static final Integer SUPERMAN = 2;

   @Test
   public void retrieveBatmanMessages() {
      List<Feed> messages = myTwitterServices.findUserMessages(BATMAN, "amet");
      assertTrue(messages.size() == 1);
   }

   @Test
   public void followSuperman() {
      Integer result = myTwitterServices.followAnotherUser(BATMAN, SUPERMAN);
      assertTrue(result == 1);
   }

   @Test
   public void unfollowSuperman() {
      followSuperman();
      Integer result = myTwitterServices.unfollowAnotherUser(BATMAN, SUPERMAN);
      assertTrue(result == 1);
   }

   @Test
   public void findWhoBatmanFollows() {
      followSuperman();
      List<People> result = myTwitterServices.findUserFollowing(BATMAN);
      assertTrue(result.size() == 7);
   }

   @Test
   public void findWhoFollowsBatman() {
      List<People> result = myTwitterServices.findUserFollowers(BATMAN);
      assertTrue(result.size() == 5);
   }

}
