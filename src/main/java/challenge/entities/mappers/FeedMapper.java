package challenge.entities.mappers;

import challenge.entities.Feed;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedMapper implements RowMapper {

   @Override
   public Feed mapRow(ResultSet theResultSet, int theI) throws SQLException {
      Feed feed = new Feed();
      feed.setName(theResultSet.getString("name"));
      feed.setTweet(theResultSet.getString("content"));

      return feed;
   }
}
