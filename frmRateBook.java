import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.io.*;

public class frmRateBook extends JFrame implements ActionListener
{
  //this method returns the index at where the book was found
  public int BookIndex(String BookTitle)
  {
    int intBook = 0;
    //this for loop checks if the title inputed is equal to any of the titles in the book arraylist
    for(int i = 0; i < frmReadFiles.BookList.size(); i++)
    {
      String [] BookInformation = frmReadFiles.BookList.get(i).split(",");
      if(BookTitle.equals(BookInformation[1])){
        intBook = i;
      }
    }
    //it returns the index at which the book title was found
    return intBook;
  }

  //this method takes the rating inputed by the user and adds the rating to their ratings
  public void RateBook(String BookTitle, String BookAuthor, int intRating, int intBookIndex)
  {
    //if the book title was not found, the rest of the metod doesnt run
    boolean isRate = true;
    if(intBookIndex == 0)
    {
      JOptionPane.showMessageDialog(null, "The book was not found in the system.");
      isRate = false;
    }

    //if the title is found then the method updates the rating for the user
    if(isRate)
    {
      //this string gets the name of the current user, allowing the program to update only that users rating
      String UserName = frmLogin.CurrentUser.getUserName();
      //this arraylist is used to combine all the rating to then put back to the ratings.txt file
      ArrayList <String> strUserRating = new ArrayList<String>();
      //this for each loop runs through each user and checks if the username is equal to the key name
      for(User key : frmReadFiles.UserRatingMap.keySet())
      {
        if(UserName.equals(key.getUserName()))
        {
          //once the users name matches the name of the key, the program runs to change the rating
          ArrayList <Integer> intRatings = new ArrayList<Integer>();
          //this for loop adds the i value to the ratings files, to then be added to the hashmap
          for(int i = 0; i < frmReadFiles.UserRatingMap.get(key).size(); i++)
          {
            intRatings.add(frmReadFiles.UserRatingMap.get(key).get(i));
            strUserRating.add(frmReadFiles.UserRatingMap.get(key).get(i) + " ");
          }
          //the new ratings are set to the hashmap and ratings
          intRatings.set(intBookIndex, intRating);
          strUserRating.set(intBookIndex, intRating + "");
          //the new ratings are added to the hashmap
          frmReadFiles.UserRatingMap.put(key, intRatings);
          JOptionPane.showMessageDialog(null, "The book rating was added successfully.");
        }
      }

      //these arraylists are used to store the data from the hashmap into a string arraylist
      ArrayList <Integer> intUserRatings = new ArrayList<Integer>();
      ArrayList <String> strUserRatings = new ArrayList<String>();
      //this for puts the ratings from the hashmap into a string arraylist
      for(User key : frmReadFiles.UserRatingMap.keySet())
        {
          String strRating = "";
          intUserRatings = frmReadFiles.UserRatingMap.get(key);
          for(int i = 0; i < intUserRatings.size(); i++)
          {
            strRating += intUserRatings.get(i) + " ";
          }
          strUserRatings.add(strRating);
        }

      //the new ratings are then put onto the txt file to be save for future use
      try
        {
          FileWriter fw = new FileWriter(frmReadFiles.RatingFileName);
          PrintWriter pw = new PrintWriter(fw);
          //this for loop prints the user and their rating on the txt file
          for(int i = 0; i < frmReadFiles.UserInformation.size(); i++)
            {
              pw.println(frmReadFiles.UserInformation.get(i));
              pw.println(strUserRatings.get(i));
            }

          fw.close();
        }
      catch(IOException e){}
      }
    }


  //this method checks if the input of the title and author are valid
  public boolean isRate(String BookTitle, String BookAuthor){
    Boolean isRate = true;
    //by checking the inputs for the boot title and author, we can declare if the text is usable or not
    try
      {
        String Title = BookTitle;
        String Author = BookAuthor;

        //this if statement checks if the text fields are blank, making the method return false if true
        if(Title.equals("") || Author.equals(""))
        {
          isRate = false;
        }
      }

    //if an error is found, the method returns false
    catch(Exception e1){
      isRate = false;
      }

    return isRate;
  }

  //this set of JLabel texts, create labels for each instance of text on the screen
  JLabel lblTitle;
  JLabel lblBookName;
  JLabel lblBookAuthor;
  JLabel lblInformation;
  JLabel lblInformation2;

  //this is a set of JButtons for the user to proceed with the program
  JButton btnHatedIt; //-5
  JButton btnDidntLikeIt; //-3
  JButton btnOk; //1
  JButton btnLikedIt; //3
  JButton btnReallyLikedIt; //5
  JButton btnBack;
  JButton btnLogOut;

  //this JTextField is used to get the input from the user
  JTextField txtBookTitle;
  JTextField txtBookAuthor;

  public frmRateBook()
  { 
    //sets the dimension of the JFrame
    setLayout(null);

    //defining the background color for the gui
    Color myColor = new Color(100,149,237);
    //setting the background color to the gui
    getContentPane().setBackground(myColor);

    //setting up the labels to display information to the user
    lblTitle = new JLabel("<html><font size='6' color=white> Rate A Book");
    lblTitle.setSize(500,75);
    lblTitle.setForeground(Color.BLACK);
    lblTitle.setLocation(175,0);
    add(lblTitle);

    lblBookName = new JLabel("Book Title: ");
    lblBookName.setSize(500,75);
    lblBookName.setLocation(90,50);
    add(lblBookName);

    lblBookAuthor = new JLabel("Author: ");
    lblBookAuthor.setSize(500,75);
    lblBookAuthor.setLocation(90,110);
    add(lblBookAuthor);

    lblInformation = new JLabel("Enter the book title and author, then select a button");
    lblInformation.setForeground(Color.BLACK);
    lblInformation.setFont(new Font("SansSerif", Font.ITALIC, 15));
    lblInformation.setSize(500,110);
    lblInformation.setLocation(75,275);
    add(lblInformation);

    lblInformation2 = new JLabel("depending on your rating.");
    lblInformation2.setForeground(Color.BLACK);
    lblInformation2.setFont(new Font("SansSerif", Font.ITALIC, 15));
    lblInformation2.setSize(500,110);
    lblInformation2.setLocation(160,300);
    add(lblInformation2);

    //setting up the buttons to be pressed by the user
    btnHatedIt = new JButton("Hated It! (-5)");
    btnHatedIt.setLocation(15,200);
    btnHatedIt.setSize(150,35);
    btnHatedIt.setActionCommand("Hated It! (-5)");
    btnHatedIt.addActionListener(this);
    add(btnHatedIt);

    btnDidntLikeIt = new JButton("Didn't Like It! (-3)");
    btnDidntLikeIt.setLocation(175,200);
    btnDidntLikeIt.setSize(155,35);
    btnDidntLikeIt.setActionCommand("Didn't Like It! (-3)");
    btnDidntLikeIt.addActionListener(this);
    add(btnDidntLikeIt);

    btnOk = new JButton("OK! (1)");
    btnOk.setLocation(340,200);
    btnOk.setSize(150,35);
    btnOk.setActionCommand("OK! (1)");
    btnOk.addActionListener(this);
    add(btnOk);

    btnLikedIt = new JButton("Liked It! (3)");
    btnLikedIt.setLocation(85,250);
    btnLikedIt.setSize(150,35);
    btnLikedIt.setActionCommand("Liked It! (3)");
    btnLikedIt.addActionListener(this);
    add(btnLikedIt);

    btnReallyLikedIt = new JButton("Really Liked It! (5)");
    btnReallyLikedIt.setLocation(260,250);
    btnReallyLikedIt.setSize(160,35);
    btnReallyLikedIt.setActionCommand("Really Liked It! (5)");
    btnReallyLikedIt.addActionListener(this);
    add(btnReallyLikedIt);

    btnBack = new JButton("Back");
    btnBack.setLocation(5,445);
    btnBack.setSize(120,30);
    btnBack.setActionCommand("Back");
    btnBack.addActionListener(this);
    add(btnBack);

    btnLogOut = new JButton("Log Out");
    btnLogOut.setLocation(375,445);
    btnLogOut.setSize(120,30);
    btnLogOut.setActionCommand("Log Out");
    btnLogOut.addActionListener(this);
    add(btnLogOut);

    //setting up the text fields for the user to input text
    txtBookTitle = new JTextField();
    txtBookTitle.setLocation(180,70);
    txtBookTitle.setSize(150,30);
    add(txtBookTitle);

    txtBookAuthor = new JTextField();
    txtBookAuthor.setLocation(180,130);
    txtBookAuthor.setSize(150,30);
    add(txtBookAuthor);
  }

  public void actionPerformed(ActionEvent button) {
    String BookTitle = txtBookTitle.getText();
    String BookAuthor = txtBookAuthor.getText();

    //if -5 is the rating given, the program runs the methods to add that rating to the user
    if(button.getActionCommand().equals("Hated It! (-5)"))
    {
      if(isRate(BookTitle, BookAuthor))
      {
        RateBook(BookTitle, BookAuthor, -5, BookIndex(BookTitle));
      }
      else
      {
        JOptionPane.showMessageDialog(null, "Please make sure all fields are filled out before and\nhave valid book credentials before attmepting to rate a book.");
      }

    }
    //if -3 is the rating given, the program runs the methods to add that rating to the user
    else if(button.getActionCommand().equals("Didn't Like It! (-3)"))
    {
      if(isRate(BookTitle, BookAuthor))
        {
          RateBook(BookTitle, BookAuthor, -3, BookIndex(BookTitle));
        }
        else
        {
          JOptionPane.showMessageDialog(null, "Please make sure all fields are filled out before and\nhave valid book credentials before attmepting to rate a book.");
        }
    }
    //if 1 is the rating given, the program runs the methods to add that rating to the user
    else if(button.getActionCommand().equals("OK! (1)"))
    {
      if(isRate(BookTitle, BookAuthor))
        {
          RateBook(BookTitle, BookAuthor, 1, BookIndex(BookTitle));
        }
        else
        {
          JOptionPane.showMessageDialog(null, "Please make sure all fields are filled out before and\nhave valid book credentials before attmepting to rate a book.");
        }
    }
    //if 3 is the rating given, the program runs the methods to add that rating to the user
    else if(button.getActionCommand().equals("Liked It! (3)"))
    {
      if(isRate(BookTitle, BookAuthor))
        {
          RateBook(BookTitle, BookAuthor, 3, BookIndex(BookTitle));
        }
        else
        {
          JOptionPane.showMessageDialog(null, "Please make sure all fields are filled out before and\nhave valid book credentials before attmepting to rate a book.");
        }
    }
    //if 5 is the rating given, the program runs the methods to add that rating to the user
    else if(button.getActionCommand().equals("Really Liked It! (5)"))
    {
      if(isRate(BookTitle, BookAuthor))
        {
          RateBook(BookTitle, BookAuthor, 5, BookIndex(BookTitle));
        }
        else
        {
          JOptionPane.showMessageDialog(null, "Please make sure all fields are filled out before and\nhave valid book credentials before attmepting to rate a book.");
        }
    }
    //this allows the user to go back to the home page of the program
    else if(button.getActionCommand().equals("Back"))
    {
      frmHomePage myFrame = new frmHomePage();

      myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      myFrame.setSize(500,500);
      myFrame.setVisible((true));
      this.dispose();
    }
    //this allows the user to log out of the program, going back to the start page
    else if(button.getActionCommand().equals("Log Out"))
    {
      frmStartPage myFrame = new frmStartPage();
      myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      myFrame.setSize(500,500);
      myFrame.setVisible((true));
      this.dispose();
      frmLogin.CurrentUser = null;
    }
  }
}
