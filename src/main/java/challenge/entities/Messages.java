package challenge.entities;

public class Messages {

   private Integer id;
   private Integer personId;
   private String content;

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

   public String getContent() {
      return content;
   }

   public void setContent(String theContent) {
      content = theContent;
   }
}
