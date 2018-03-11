package challenge.entities.mappers;

import challenge.entities.Messages;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessagesMapper implements RowMapper {

   @Override
   public Messages mapRow(ResultSet theResultSet, int theI) throws SQLException {
      Messages messages = new Messages();

      messages.setId(theResultSet.getInt("id"));
      messages.setContent(theResultSet.getString("content"));
      messages.setPersonId(theResultSet.getInt("person_id"));

      return messages;
   }
}
