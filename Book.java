//this object is for books
public class Book {
    //there are 4 variables that each book has
    private String Title;
    private String Author;
    private int Year;
    private String ISBN;
  
    //the book object is called with all parameters
    Book(String Author, String Title, int Year, String ISBN)
    {
      this.Author = Author;
      this.Title = Title;
      this.Year = Year;
      this.ISBN = ISBN;
    }
    //the book object is called without year
    Book(String Author, String Title, String ISBN)
    {
      this.Author = Author;
      this.Title = Title;
      this.Year = 0;
      this.ISBN = ISBN;
    }
    //this string method returns the title
    public String getTitle()
    {
      return Title;
    }
    //this string method returns the author
    public String getAuthor()
      {
        return Author;
      }
    //this integer method returns the year
    public int getYear()
    {
      return Year;
    }
    //this string method returns the ISBN number
    public String getISBN()
      {
        int intISBNLength = ISBN.length();
        String strZero = "";
        for(int i = intISBNLength; i < 10; i++)
        {
          strZero += "0";
        }
        return strZero + ISBN;
      }
  }
  