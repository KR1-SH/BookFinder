import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.ImageIcon;

public class frmHomePage extends JFrame implements ActionListener
{
  //this set of JLabel texts, create labels for each instance of text on the screen
  JLabel lblTitle;
  JLabel lblInformation;

  //this is a set of JButtons for the user to proceed with the program
  JButton btnAddBook;
  JButton btnRecommendation;
  JButton btnViewRatings;
  JButton btnRateBook;
  JButton btnDeleteUser;
  JButton btnLogOut;

  public frmHomePage()
  {
    //sets the dimension of the JFrame
    setLayout(null);

    //defining the background color for the gui
    Color myColor = new Color(100,149,237);
    //setting the background color to the gui
    getContentPane().setBackground(myColor);

    //setting up the labels to display information to the user
    lblTitle = new JLabel("<html><font size='6' color=white> Welcome " + frmLogin.CurrentUser.getName() + "!");
    lblTitle.setSize(500,75);
    lblTitle.setForeground(Color.BLACK);
    lblTitle.setLocation(150,0);
    add(lblTitle);

    lblInformation = new JLabel("Please click one of the following buttons to continue!");
    lblInformation.setForeground(Color.BLACK);
    lblInformation.setFont(new Font("SansSerif", Font.ITALIC, 15));
    lblInformation.setSize(500,110);
    lblInformation.setLocation(65,40);
    add(lblInformation);

    //setting up the buttons to be pressed by the user
    btnAddBook = new JButton("Add A Book");
    btnAddBook.setLocation(150,155);
    btnAddBook.setSize(200,35);
    btnAddBook.setActionCommand("Add A Book");
    btnAddBook.addActionListener(this);
    add(btnAddBook);

    btnRecommendation = new JButton("Get Recommendations");
    btnRecommendation.setLocation(150,215);
    btnRecommendation.setSize(200,35);
    btnRecommendation.setActionCommand("Get Recommendations");
    btnRecommendation.addActionListener(this);
    add(btnRecommendation);

    btnRateBook = new JButton("Rate A Book");
    btnRateBook.setLocation(150,275);
    btnRateBook.setSize(200,35);
    btnRateBook.setActionCommand("Rate A Book");
    btnRateBook.addActionListener(this);
    add(btnRateBook);

    btnViewRatings = new JButton("View Ratings");
    btnViewRatings.setLocation(150,335);
    btnViewRatings.setSize(200,35);
    btnViewRatings.setActionCommand("View Ratings");
    btnViewRatings.addActionListener(this);
    add(btnViewRatings);

    btnDeleteUser = new JButton("Delete User");
    btnDeleteUser.setLocation(150,395);
    btnDeleteUser.setSize(200,35);
    btnDeleteUser.setActionCommand("Delete User");
    btnDeleteUser.addActionListener(this);
    add(btnDeleteUser);

    btnLogOut = new JButton("Log Out");
    btnLogOut.setLocation(375,445);
    btnLogOut.setSize(120,30);
    btnLogOut.setActionCommand("Log Out");
    btnLogOut.addActionListener(this);
    add(btnLogOut);
  }

  public void actionPerformed(ActionEvent button) {
    //if the user wants to add a book, they are sent to that GUI page
    if(button.getActionCommand().equals("Add A Book"))
      {
        frmAddBook myFrame = new frmAddBook();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(500,500);
        myFrame.setVisible((true));
        this.dispose();
      }
    //if the user wants to get recommendations, they are sent to that GUI page
    else if(button.getActionCommand().equals("Get Recommendations"))
      {
        frmRecommendations myFrame = new frmRecommendations();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(500,500);
        myFrame.setVisible((true));
        this.dispose();
      }
    //if the user wants to rate a book, they are sent to that GUI page
    else if(button.getActionCommand().equals("Rate A Book"))
      {
        frmRateBook myFrame = new frmRateBook();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(500,500);
        myFrame.setVisible((true));
        this.dispose();
      }
    //if the user wants to view their, they are sent to that GUI page
    else if(button.getActionCommand().equals("View Ratings"))
      {
        frmViewRatings myFrame = new frmViewRatings();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(500,500);
        myFrame.setVisible((true));
        this.dispose();
      }
    //if the user wants to delete their account, they are sent to that GUI page
    else if(button.getActionCommand().equals("Delete User"))
      {
        frmDeleteUser myFrame = new frmDeleteUser();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(500,500);
        myFrame.setVisible((true));
        this.dispose();
      }
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
