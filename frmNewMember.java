import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.ImageIcon;
import java.io.*;

public class frmNewMember extends JFrame implements ActionListener
{
  //this set of JLabel texts, create labels for each instance of text on the screen
  JLabel lblTitle;
  JLabel lblUserName;
  JLabel lblName;
  JLabel lblPassword;

  //this is the text fields where the user can input text for the program to use
  JTextField txtUserName;
  JTextField txtName;
  JTextField txtPassword;

  //this is a set of JButtons for the user to proceed with the program
  JButton btnLogin;
  JButton btnBack;

  public frmNewMember()
  {
    //sets the dimension of the JFrame
    setLayout(null);

    //defining the background color for the gui
    Color myColor = new Color(100,149,237);
    //setting the background color to the gui
    getContentPane().setBackground(myColor);

    //setting up the labels to display information to the user
    lblTitle = new JLabel("<html><font size='5' color=white> Create An Account");
    lblTitle.setSize(500,75);
    lblTitle.setLocation(155, 0);
    add(lblTitle);

    lblUserName = new JLabel("Username: ");
    lblUserName.setSize(500,75);
    lblUserName.setLocation(85,40);
    add(lblUserName);

    lblName = new JLabel("Name: ");
    lblName.setSize(500,75);
    lblName.setLocation(85,100);
    add(lblName);

    lblPassword = new JLabel("Password: ");
    lblPassword.setSize(500,75);
    lblPassword.setLocation(85,160);
    add(lblPassword);

    //setting up the text fields for the user to input text
    txtUserName = new JTextField();
    txtUserName.setLocation(175,65);
    txtUserName.setSize(150,30);
    add(txtUserName);

    txtName = new JTextField();
    txtName.setLocation(175,125);
    txtName.setSize(150,30);
    add(txtName);

    txtPassword = new JTextField();
    txtPassword.setLocation(175,185);
    txtPassword.setSize(150,30);
    add(txtPassword);

    //setting up the buttons to be pressed by the user
    btnLogin = new JButton("Add User");
    btnLogin.setLocation(150,250);
    btnLogin.setSize(200,30);
    btnLogin.setActionCommand("Add User");
    btnLogin.addActionListener(this);
    add(btnLogin);

    btnBack = new JButton("Back");
    btnBack.setLocation(5,445);
    btnBack.setSize(120,30);
    btnBack.setActionCommand("Back");
    btnBack.addActionListener(this);
    add(btnBack);
  }

  public void actionPerformed(ActionEvent button) {
    //if the user wants to add a user, their information has to meet certian conditions
    if(button.getActionCommand().equals("Add User"))
      {
        try
        {
          //all the user information is read, then checked to see if it is valid
          String UserName = txtUserName.getText();
          String Name = txtName.getText();
          String Password = txtPassword.getText();
          boolean isUser = true;
          //if any fields are left empty, an appropriate error message is displayed
          if(UserName.equals("") || Name.equals("") || Password.equals("")){
            JOptionPane.showMessageDialog(null, "Please make sure all fields are filled out before\nattmepting to add user.");
            isUser = false;
          }
          //this for each loop checks if the username is available or not
          for(User key : frmReadFiles.UserRatingMap.keySet())
          {
            if(key.getUserName().equals(UserName)) 
            {
              JOptionPane.showMessageDialog(null, "This username is taken, please try a different one.");
              isUser = false;
            }
          }
          //is the user is able to be added, it runs through this code
          if(isUser)
          {
            //the user is created as a new object
            User NewUser = new User(UserName, Name, Password);
            ArrayList<Integer> BookRatings = new ArrayList<Integer>();
            try
              {
                //the new information is added to the ratings file
                FileWriter fw = new FileWriter(frmReadFiles.RatingFileName, true);
                PrintWriter pw = new PrintWriter(fw);
                pw.println(NewUser.getUserName() + "," + NewUser.getName() + "," + NewUser.getPassword());
                for(int i = 0; i < frmReadFiles.intBooks; i++)
                  {
                    BookRatings.add(0);
                    pw.print(0 + " ");
                  }
                //writing the information to the ratings file
                frmReadFiles.UserRatingMap.put(NewUser, BookRatings);
                fw.close();
              }

            catch(IOException e){}
              JOptionPane.showMessageDialog(null, "User was successfully added.");
          }
        }
        //if an error occurs, an appropriate error message is displayed
        catch(Exception e1){
          JOptionPane.showMessageDialog(null, "Please make sure the username, name and password are all valid inputs.");
        }
      }
    //this button allows the user to go back to the start page
    else if(button.getActionCommand().equals("Back"))
    {
      frmStartPage myFrame = new frmStartPage();
      myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      myFrame.setSize(500,500);
      myFrame.setVisible((true));
      this.dispose();
    }
  }
}
