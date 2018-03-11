package challenge.entities;

public class People {

   private Integer id;
   private String handle;
   private String name;
   private String password;

   public Integer getId() {
      return id;
   }

   public void setId(Integer theId) {
      id = theId;
   }

   public String getHandle() {
      return handle;
   }

   public void setHandle(String theHandle) {
      handle = theHandle;
   }

   public String getName() {
      return name;
   }

   public void setName(String theName) {
      name = theName;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String thePassword) {
      password = thePassword;
   }
}
