package challenge;


import challenge.services.RelationServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ChallengeApplication.class)
public class RelationTests {

   @Autowired
   private RelationServices relationServices;


   @Test
   public void batmanRelationHopWithAlfred() {
      Integer shortestPath = relationServices.findShortestPath(1, 5);
      assertTrue(shortestPath == 1);
   }


}
