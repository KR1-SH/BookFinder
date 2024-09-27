import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.ImageIcon;

public class frmStartPage extends JFrame implements ActionListener
{
  //this set of JLabel texts, create labels for each instance of text on the screen
  JLabel lblTitle;
  JLabel lblInformation;
  JLabel lblInformation2;
  JLabel lblLoadData;

  //this is a set of JButtons for the user to proceed with the program
  JButton btnLogin;
  JButton btnAddMember;
  JButton btnReadFiles;
  JButton btnQuitProgram;

  //this method creates the gui program and formats each label, text field, button, etc. accordingly
  public frmStartPage()
  {
    //sets the dimension of the JFrame
    setLayout(null);

    //defining the background color for the gui
    Color myColor = new Color(100,149,237);
    //setting the background color to the gui
    getContentPane().setBackground(myColor);

    //setting up the labels to display information to the user
    lblTitle = new JLabel("<html><font size='6' color=white> Recommendation System");
    lblTitle.setSize(500,75);
    lblTitle.setForeground(Color.BLACK);
    lblTitle.setLocation(85,-15);
    add(lblTitle);

    lblInformation = new JLabel("Welcome to Krish's Recommendation System!");
    lblInformation.setForeground(Color.BLACK);
    lblInformation.setFont(new Font("SansSerif", Font.ITALIC, 15));
    lblInformation.setSize(500,75);
    lblInformation.setLocation(85,25);
    add(lblInformation);

    lblInformation2 = new JLabel("Please click one of the following buttons to continue!");
    lblInformation2.setForeground(Color.BLACK);
    lblInformation2.setFont(new Font("SansSerif", Font.ITALIC, 15));
    lblInformation2.setSize(500,110);
    lblInformation2.setLocation(65,40);
    add(lblInformation2);

    lblLoadData = new JLabel("Make Sure To Load Data Before Running The Program!");
    lblLoadData.setForeground(Color.RED);
    lblLoadData.setFont(new Font("SansSerif", Font.ITALIC, 14));
    lblLoadData.setSize(500,110);
    lblLoadData.setLocation(70,280);
    add(lblLoadData);

    //setting up the buttons to be pressed by the user
    btnLogin = new JButton("Login");
    btnLogin.setLocation(150,155);
    btnLogin.setSize(200,35);
    btnLogin.setActionCommand("Login");
    btnLogin.addActionListener(this);
    add(btnLogin);

    btnAddMember = new JButton("Sign Up");
    btnAddMember.setLocation(150,215);
    btnAddMember.setSize(200,35);
    btnAddMember.setActionCommand("Sign Up");
    btnAddMember.addActionListener(this);
    add(btnAddMember);

    btnReadFiles = new JButton("Read Files");
    btnReadFiles.setLocation(150,275);
    btnReadFiles.setSize(200,35);
    btnReadFiles.setActionCommand("Read Files");
    btnReadFiles.addActionListener(this);
    add(btnReadFiles);

    btnQuitProgram = new JButton("Quit Program");
    btnQuitProgram.setLocation(15,440);
    btnQuitProgram.setSize(150,30);
    btnQuitProgram.setActionCommand("Quit Program");
    btnQuitProgram.addActionListener(this);
    add(btnQuitProgram);
  }

    public void actionPerformed(ActionEvent button)
    {
      //if the user wants to login, they are sent to the login GUI page
      if(button.getActionCommand().equals("Login"))
      {
        frmLogin myFrame = new frmLogin();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(500,500);
        myFrame.setVisible((true));
        this.dispose();
      }

      //if the user wants to create a new user, they are sent to the new member GUI page
      else if(button.getActionCommand().equals("Sign Up"))
      {
        frmNewMember myFrame = new frmNewMember();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(500,500);
        myFrame.setVisible((true));
        this.dispose();
      }

      //if the user wants to read files, they are sent to the read files GUI page
      else if(button.getActionCommand().equals("Read Files"))
      {
        frmReadFiles myFrame = new frmReadFiles();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(500,500);
        myFrame.setVisible((true));
        this.dispose();
      }

      //if the user wants to quit the program, they are sent to the quit page
      else if(button.getActionCommand().equals("Quit Program"))
      {
        System.exit(0);
      }
    }
}
