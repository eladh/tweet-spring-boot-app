package challenge.dao;

import challenge.entities.Feed;
import challenge.entities.Followers;
import challenge.entities.People;
import challenge.entities.mappers.FeedMapper;
import challenge.entities.mappers.FollowersMapper;
import challenge.entities.mappers.PeopleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class TwitterDao implements TwitterDaoIfc {

   private final String FIND_MESSAGES = "select messages.*, people.* from messages, people " +
         "where messages.content like :msgFilter and messages.person_id = people.id and (" +
         " people.id = :id or people.id " +
         " in (select follower_person_id from followers where person_id = :id))";


   private final String FIND_USER_FOLLOWING = "select followers.*, people.* from followers ,people " +
         "where people.id = followers.follower_person_id and followers.person_id = :id ";


   private final String FIND_USER_FOLLOWERS = "select followers.* ,people.* from followers ,people " +
         "where people.id = followers.person_id and followers.follower_person_id = :id";


   private final String FOLLOW_USER = "insert into followers(person_id, follower_person_id) values(:person_id,:follower_person_id)";


   private final String UNFOLLOW_USER = "delete from followers where followers.person_id = :person_id "+
         "and followers.follower_person_id = :follower_person_id";


   private final String GET_ALL_USERS = "select * from people";

   private final String GET_ALL_FOLLLOWERS = "select * from followers";


   private final NamedParameterJdbcTemplate jdbcTemplate;


   @Autowired
   public TwitterDao(NamedParameterJdbcTemplate engine) {
      this.jdbcTemplate = engine;
   }


   @Override
   public List<People> getAllUsers() {
      return jdbcTemplate.query(GET_ALL_USERS , new PeopleMapper());
   }

   @Override
   public List<Followers> getAllFollowers() {
      return jdbcTemplate.query(GET_ALL_FOLLLOWERS , new FollowersMapper());
   }

   @Override
   public List<Feed> findUserMessages(Integer userId , String msgFilter) {
      SqlParameterSource parameters = new MapSqlParameterSource()
            .addValue("id" , userId)
            .addValue("msgFilter" ,msgFilter+"%");

      return jdbcTemplate.query(FIND_MESSAGES, parameters, new FeedMapper());
   }

   @Override
   public List<People> findUserFollowing(Integer userId) {
      SqlParameterSource parameters = new MapSqlParameterSource()
            .addValue("id" , userId);

      return jdbcTemplate.query(FIND_USER_FOLLOWING , parameters, new PeopleMapper());

   }

   @Override
   public List<People> findUserFollowers(Integer userId) {
      SqlParameterSource parameters = new MapSqlParameterSource()
            .addValue("id" , userId);

      return jdbcTemplate.query(FIND_USER_FOLLOWERS , parameters, new PeopleMapper());
   }

   @Override
   public int followAnotherUser(Integer userId ,Integer userId2Follow) {
      KeyHolder holder = new GeneratedKeyHolder();

      SqlParameterSource parameters = new MapSqlParameterSource()
            .addValue("person_id", userId)
            .addValue("follower_person_id", userId2Follow);

      return jdbcTemplate.update(FOLLOW_USER, parameters, holder);
   }

   @Override
   public int unfollowAnotherUser(Integer userId ,Integer followedId) {
      SqlParameterSource parameters = new MapSqlParameterSource()
            .addValue("person_id", userId)
            .addValue("follower_person_id", followedId);

      return jdbcTemplate.update(UNFOLLOW_USER ,parameters);
   }

}
