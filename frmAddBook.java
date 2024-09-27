import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.ImageIcon;
import java.io.*;
import java.util.ArrayList;

public class frmAddBook extends JFrame implements ActionListener
{
  //this set of JLabel texts, create labels for each instance of text on the screen
  JLabel lblTitle;
  JLabel lblBookName;
  JLabel lblBookAuthor;
  JLabel lblBookYear;

  //this is a set of JButtons for the user to proceed with the program
  JButton btnAddBook;
  JButton btnBack;
  JButton btnLogOut;

  //this JTextField is used to get the input from the user
  JTextField txtBookTitle;
  JTextField txtBookAuthor;
  JTextField txtBookYear;

  public frmAddBook()
  { 
    //sets the dimension of the JFrame
    setLayout(null);

    //defining the background color for the gui
    Color myColor = new Color(100,149,237);
    //setting the background color to the gui
    getContentPane().setBackground(myColor);

    //setting up the labels to display information to the user
    lblTitle = new JLabel("<html><font size='6' color=white> Add A Book");
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
    lblBookAuthor.setLocation(90,125);
    add(lblBookAuthor);

    lblBookYear = new JLabel("Year of Publication: ");
    lblBookYear.setSize(500,75);
    lblBookYear.setLocation(90,200);
    add(lblBookYear);

    //setting up the buttons to be pressed by the user
    btnAddBook = new JButton("Add The Book");
    btnAddBook.setLocation(150,300);
    btnAddBook.setSize(200,35);
    btnAddBook.setActionCommand("Add The Book");
    btnAddBook.addActionListener(this);
    add(btnAddBook);

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
    txtBookAuthor.setLocation(170,145);
    txtBookAuthor.setSize(150,30);
    add(txtBookAuthor);

    txtBookYear = new JTextField();
    txtBookYear.setLocation(245,220);
    txtBookYear.setSize(150,30);
    add(txtBookYear);

  }

  public void actionPerformed(ActionEvent button) {
    //this allows the user to add a book to the system, if they meet the conditions
    if(button.getActionCommand().equals("Add The Book"))
    {
      //this try and catch statement checks if the book can be added
      try
      {
        String BookTitle = txtBookTitle.getText();
        String BookAuthor = txtBookAuthor.getText();
        int BookYear = Integer.parseInt(txtBookYear.getText());
        boolean isBookValid = true;
        //if any of the text fields are blank an appropriate error message is given
        if(BookTitle.equals("") || BookAuthor.equals("") || txtBookYear.getText().equals(""))
        {
          JOptionPane.showMessageDialog(null, "Please make sure all fields are filled out before\nattmepting to add a book.");
          isBookValid = false;
        }
        //the book is split to get its information, then checked to see if the book is already in the database or not
        for(int i = 0; i < frmReadFiles.BookList.size(); i++){
          String BookInformation = frmReadFiles.BookList.get(i);
          String [] BookInfo = BookInformation.split(",");
          if(BookTitle.compareTo(BookInfo[1]) == 0 && BookAuthor.compareTo(BookInfo[0]) == 0){
            JOptionPane.showMessageDialog(null, "This book is already in the system, try adding a different book");
            isBookValid = false;
          }
        }
        //if the book is able to be added, it runs through this set of code
        if(isBookValid)
        {
          //a new book object is created, then added to an arraylist
          Book NewBook = new Book(BookTitle, BookAuthor, BookYear, "" + frmReadFiles.intLine);
          frmReadFiles.intLine++;
          frmReadFiles.BookList.add(NewBook.getTitle() + "," + NewBook.getAuthor() + "," + NewBook.getYear() + "," + NewBook.getISBN());
          JOptionPane.showMessageDialog(null, "Book added successfully.");

          ArrayList <String> strUserRatings = new ArrayList<String>();
          String strRatings = "";
          //updates the hashmap to add a 0 for the new book added
          for(User key : frmReadFiles.UserRatingMap.keySet())
          {
            ArrayList <Integer> Ratings = new ArrayList<Integer>();
            strRatings = "";
            //reads the ratings to add it to the hashmap and txt file
            for(int i = 0; i < frmReadFiles.UserRatingMap.get(key).size(); i++){
              Ratings.add(frmReadFiles.UserRatingMap.get(key).get(i));
              strRatings += frmReadFiles.UserRatingMap.get(key).get(i) + " ";
            } 
            Ratings.add(0);
            strUserRatings.add(strRatings + "0");
            //the hashmap is updated
            frmReadFiles.UserRatingMap.put(key, Ratings);
          }

          //updates the ratings.txt file to add a 0 for the new book added
          try
            {
              FileWriter fbw = new FileWriter(frmReadFiles.BookFileName, true);
              FileWriter frw = new FileWriter(frmReadFiles.RatingFileName);
              PrintWriter pbw = new PrintWriter(fbw);
              PrintWriter prw = new PrintWriter(frw);
              pbw.println(NewBook.getTitle() + "," + NewBook.getAuthor() + "," + NewBook.getYear() + "," + NewBook.getISBN());
              for(int i = 0; i < frmReadFiles.UserInformation.size(); i++)
                {
                  //the book is added to the books file and the ratings are updated
                  prw.println(frmReadFiles.UserInformation.get(i));
                  prw.println(strUserRatings.get(i));
                }

              fbw.close();
              frw.close();
            }
          catch(IOException e){}

        }
      }
      //if an error is found when reading, an appropriate error message is given
      catch(Exception e1){
        JOptionPane.showMessageDialog(null, "Please make sure the all fields are valid inputs \n(ex. Year should be a year or at least a number).");
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
