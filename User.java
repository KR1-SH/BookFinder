//this object is for books
public class User {
    //this object has 3 variables for each user
    private String UserName;
    private String Name;
    private String Password;
  
    //the object is called with only the username, so the name and password are auto assigned
    public User(String UserName)
    {
      this.UserName = UserName;
      this.Name = UserName;
      this.Password = "password";
    }
    //the object is called with 2 variables, auto assigning the name
    public User(String UserName, String Password)
      {
        this.UserName = UserName;
        this.Name = UserName;
        this.Password = Password;
      }
    //the object is called with all 3 parameters
    public User(String UserName, String Name, String Password)
      {
        this.UserName = UserName;
        this.Name = Name;
        this.Password = Password;
      }
    //this string method returns the user name of the user
    public String getUserName()
    {
      return UserName;
    }
    //this string method returns the name of the user
    public String getName()
      {
        return Name;
      }
    //this string method returns the password of the user
    public String getPassword()
      {
        return Password;
      }
  
  }
  