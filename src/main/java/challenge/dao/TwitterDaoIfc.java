package challenge.dao;

import challenge.entities.Feed;
import challenge.entities.Followers;
import challenge.entities.People;

import java.util.List;

public interface TwitterDaoIfc {

   List<People> findUserFollowers(Integer userId);
   List<People> findUserFollowing(Integer userId);
   List<Feed> findUserMessages(Integer userId , String msgFilter);
   int followAnotherUser(Integer userId ,Integer userId2Follow);
   int unfollowAnotherUser(Integer userId ,Integer followedId);
   List<People> getAllUsers();
   List<Followers> getAllFollowers();
}
