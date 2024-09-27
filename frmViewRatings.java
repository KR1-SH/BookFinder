import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class frmViewRatings extends JFrame implements ActionListener
{
  //this set of JLabel texts, create labels for each instance of text on the screen
  JLabel lblTitle;
  JLabel lblInformation;

  //this is a set of JButtons for the user to proceed with the program
  JButton btnViewRatings; 
  JButton btnBack;
  JButton btnLogOut;

  //this JTextArea text creates a text area where a message can be shown to the user
  JTextArea txtArea;

  public frmViewRatings()
  { 
    //sets the dimension of the JFrame
    setLayout(null);

    //defining the background color for the gui
    Color myColor = new Color(100,149,237);
    //setting the background color to the gui
    getContentPane().setBackground(myColor);

    //setting up the labels to display information to the user
    lblTitle = new JLabel("<html><font size='6' color=white> View Your Ratings");
    lblTitle.setSize(500,75);
    lblTitle.setForeground(Color.BLACK);
    lblTitle.setLocation(125,0);
    add(lblTitle);

    lblInformation = new JLabel("Press the button to view all your ratings.");
    lblInformation.setForeground(Color.BLACK);
    lblInformation.setFont(new Font("SansSerif", Font.ITALIC, 15));
    lblInformation.setSize(500,110);
    lblInformation.setLocation(90,15);
    add(lblInformation);

    //setting up the buttons to be pressed by the user
    btnViewRatings = new JButton("View Your Ratings");
    btnViewRatings.setLocation(140,95);
    btnViewRatings.setSize(200,30);
    btnViewRatings.setActionCommand("View Your Ratings");
    btnViewRatings.addActionListener(this);
    add(btnViewRatings);

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

    //setting up the text area to display the text to the user
    txtArea = new JTextArea();
    txtArea.setSize(455,300);
    txtArea.setLocation(20,135);
    add(txtArea);

    //this scroll pane is used to create a scroll on the txt area to be able to view all the ratings
    JScrollPane scrollPane = new JScrollPane(txtArea);
    scrollPane.setSize(455, 300);
    scrollPane.setLocation(20, 135);
    add(scrollPane);

  }

  public void actionPerformed(ActionEvent button) {
    //if the view ratings button is pressed, all the ratings of the user are shown
    if(button.getActionCommand().equals("View Your Ratings"))
    {
      //this string gets the name of the current user, allowing the program to update only that users rating
      String UserName = frmLogin.CurrentUser.getUserName();
      //these varibales are used to get the ratings in an arraylist
      ArrayList <Integer> intUserRatings = new ArrayList<Integer>();
      String strViewRatings = "";
      String strRating = "";
      for(User key : frmReadFiles.UserRatingMap.keySet())
        {
          //to show only the users ratings, the name is searched for in the hashmap
          if(UserName.equals(key.getUserName()))
          {
            //this arraylist and for loop add the book title and author to an arraylist
            ArrayList <String> strBookInfo = new ArrayList<String>();
            for(int i = 0; i < frmReadFiles.BookList.size(); i++)
              {
                String [] BookInformation = frmReadFiles.BookList.get(i).split(",");
                strBookInfo.add(BookInformation[0] + ", " + BookInformation[1]);
              }

            //gets the users ratings in an arraylist
            intUserRatings = frmReadFiles.UserRatingMap.get(key);
            //creates a string of all the ratings
            for(int i = 0; i < intUserRatings.size(); i++)
            {
              strRating += strBookInfo.get(i) + " • " + intUserRatings.get(i) + "\n";
            }
            strViewRatings= strRating;
          }
        }
      //outputs all the users ratings
      txtArea.setText(strViewRatings);

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
