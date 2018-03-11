package challenge.services;

import challenge.dao.TwitterDao;
import org.jgrapht.DirectedGraph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class RelationServices {

   private final TwitterDao myTwitterDao;

   private DirectedGraph<String, DefaultEdge> twitterRelations;

   @Autowired
   public RelationServices(TwitterDao myTwitterDao) {
      this.myTwitterDao = myTwitterDao;
   }

   @PostConstruct
   public void init() {
      twitterRelations = new DefaultDirectedGraph<>(DefaultEdge.class);
      myTwitterDao.getAllUsers().forEach(person -> twitterRelations.addVertex(String.valueOf(person.getId())));
      myTwitterDao.getAllFollowers().forEach(follower -> twitterRelations.addEdge(
            String.valueOf(follower.getPersonId()) ,
            String.valueOf(follower.getFollowerPersonId())));
   }

   public void addFollower(Integer personId ,Integer followerId) {
      twitterRelations.addEdge(String.valueOf(personId) , String.valueOf(followerId));
   }

   public void removeFollower(Integer personId ,Integer followerId) {
      twitterRelations.removeEdge(String.valueOf(personId) , String.valueOf(followerId));
   }

   public Integer findShortestPath(Integer personId ,Integer followerId) {
      ShortestPathAlgorithm.SingleSourcePaths<String, DefaultEdge> iPaths =
            new DijkstraShortestPath<>(twitterRelations).
                  getPaths(String.valueOf(personId));

      GraphPath<String, DefaultEdge> path = iPaths.getPath(String.valueOf(followerId));
      return path != null ? path.getLength() : -1;
   }
}
