package challenge.entities.mappers;

import challenge.entities.People;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PeopleMapper implements RowMapper {

   @Override
   public People mapRow(ResultSet theResultSet, int theI) throws SQLException {
      People people = new People();

      people.setId(theResultSet.getInt("id"));
      people.setHandle(theResultSet.getString("handle"));
      people.setName(theResultSet.getString("name"));
      people.setPassword(theResultSet.getString("password"));

      return people;

   }
}
