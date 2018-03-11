package challenge.entities;

public class Followers {
   private Integer id;
   private Integer personId;
   private Integer followerPersonId;


   public Integer getId() {
      return id;
   }

   public void setId(Integer theId) {
      id = theId;
   }

   public Integer getPersonId() {
      return personId;
   }

   public void setPersonId(Integer thePersonId) {
      personId = thePersonId;
   }

   public Integer getFollowerPersonId() {
      return followerPersonId;
   }

   public void setFollowerPersonId(Integer theFollowerPersonId) {
      followerPersonId = theFollowerPersonId;
   }
}
