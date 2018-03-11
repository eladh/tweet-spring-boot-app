package challenge.entities.mappers;

import challenge.entities.Followers;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FollowersMapper implements RowMapper {

   @Override
   public Followers mapRow(ResultSet theResultSet, int theI) throws SQLException {
      Followers followers = new Followers();

      followers.setId(theResultSet.getInt("id"));
      followers.setFollowerPersonId(theResultSet.getInt("follower_person_id"));
      followers.setPersonId(theResultSet.getInt("person_id"));

      return followers;

   }
}
