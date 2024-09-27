import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.ImageIcon;
import java.util.ArrayList;

public class frmRecommendations extends frmSortingAlgorithms
{
  public int DotProduct(ArrayList <Integer> CurrentUser, ArrayList <Integer> OtherUser)
  {
    int DotProduct = 0;

    for(int i = 0; i < CurrentUser.size(); i++)
    {
      DotProduct += CurrentUser.get(i) * OtherUser.get(i);
    }

    return DotProduct;
  }


  //this set of JLabel texts, create labels for each instance of text on the screen
  JLabel lblTitle;
  JLabel lblInformation;

  //this is a set of JButtons for the user to proceed with the program
  JButton btnGetRecommendations; 
  JButton btnBack;
  JButton btnLogOut;

  //this JTextArea text creates a text area where a message can be shown to the user
  JTextArea txtArea;

  public frmRecommendations()
  { 
    //sets the dimension of the JFrame
    setLayout(null);

    //defining the background color for the gui
    Color myColor = new Color(100,149,237);
    //setting the background color to the gui
    getContentPane().setBackground(myColor);

    //setting up the labels to display information to the user
    lblTitle = new JLabel("<html><font size='6' color=white> Recommendations");
    lblTitle.setSize(500,75);
    lblTitle.setForeground(Color.BLACK);
    lblTitle.setLocation(125,0);
    add(lblTitle);

    lblInformation = new JLabel("Press the button to get recommendations of books.");
    lblInformation.setForeground(Color.BLACK);
    lblInformation.setFont(new Font("SansSerif", Font.ITALIC, 15));
    lblInformation.setSize(500,110);
    lblInformation.setLocation(65,15);
    add(lblInformation);

    //setting up the buttons to be pressed by the user
    btnGetRecommendations = new JButton("Get Recommendations");
    btnGetRecommendations.setLocation(145,95);
    btnGetRecommendations.setSize(200,30);
    btnGetRecommendations.setActionCommand("Get Recommendations");
    btnGetRecommendations.addActionListener(this);
    add(btnGetRecommendations);

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
  }

  public void actionPerformed(ActionEvent button) {
    //if the get recommendations button is pressed, the user is prompted with book recommendations
    if(button.getActionCommand().equals("Get Recommendations"))
    {
      //this first set of code adds all the dot products to an arraylist
      ArrayList <Integer> arrDotProduct = new ArrayList <Integer>();
      String UserName = frmLogin.CurrentUser.getUserName();
      //this array list is the raings of the current user
      ArrayList <Integer> arrCurrentUser = new ArrayList <Integer>();
      arrCurrentUser = frmReadFiles.UserRatingMap.get(frmLogin.CurrentUser);
      //this for each loop checks each key in the hashmap to compare the dot product
      for(User key : frmReadFiles.UserRatingMap.keySet())
        {
          String CompareUserName = key.getUserName();
          if(UserName != CompareUserName)
          {
            //this array list is used to calculate dot product
            ArrayList <Integer> arrCompareUser = new ArrayList <Integer>();
            arrCompareUser = frmReadFiles.UserRatingMap.get(key);
            //this method is used to calculate dot product
            arrDotProduct.add(DotProduct(arrCurrentUser, arrCompareUser));
          }
        }

      //the same set of code is run again to find the specific user that is the best match
      arrDotProduct = SelectionSort(arrDotProduct);
      User userBestMatch = null;
      //this for each loop checks each key in the hashmap to compare the dot product
      for(User key : frmReadFiles.UserRatingMap.keySet())
        {
          String CompareUserName = key.getUserName();
          if(UserName != CompareUserName)
          {
            //these array lists are used to calculate dot product
            ArrayList <Integer> arrCompareUser = new ArrayList <Integer>();
            arrCompareUser = frmReadFiles.UserRatingMap.get(key);
            //the dot product is compared to the arraylist, and if it is the same, the best match is found
            if(DotProduct(arrCurrentUser, arrCompareUser) == arrDotProduct.get(0))
            {
              userBestMatch = key;
            }
          }
        }

      //these arraylists are used to store the data from the hashmap into a string arraylist
      ArrayList <Integer> intUserRatings = new ArrayList<Integer>();
      String strRating = "";
      intUserRatings = frmReadFiles.UserRatingMap.get(userBestMatch);
      //this for loop checks through the user ratings
      for(int i = 0; i < intUserRatings.size(); i++)
      {
        //if the user rating is greater than or equal to 3, the book is added to be returned as a recommendation 
        if(intUserRatings.get(i) >= 3)
        {
          String [] BookInformation = frmReadFiles.BookList.get(i).split(",");
          strRating += BookInformation[0] + ", " + BookInformation[1] + "\n";
        }
      }
      //the recommendations are outputted to the user
      txtArea.setText(strRating);
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
