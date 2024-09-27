import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.io.*;

public class frmDeleteUser extends JFrame implements ActionListener
{
  //this set of JLabel texts, create labels for each instance of text on the screen
  JLabel lblTitle;
  JLabel lblUserName;
  JLabel lblPassword;

  //this is the text fields where the user can input text for the program to use
  JTextField txtUserName;
  JTextField txtPassword;

  //this is a set of JButtons for the user to proceed with the program
  JButton btnDeleteUser;
  JButton btnBack;
  JButton btnLogOut;

  public frmDeleteUser()
  {
    //sets the dimension of the JFrame
    setLayout(null);

    //defining the background color for the gui
    Color myColor = new Color(100,149,237);
    //setting the background color to the gui
    getContentPane().setBackground(myColor);

    //setting up the labels to display information to the user
    lblTitle = new JLabel("<html><font size='6' color=white> Delete User");
    lblTitle.setSize(500,75);
    lblTitle.setLocation(175, 10);
    add(lblTitle);

    lblUserName = new JLabel("Username: ");
    lblUserName.setSize(500,75);
    lblUserName.setLocation(85,75);
    add(lblUserName);

    lblPassword = new JLabel("Password: ");
    lblPassword.setSize(500,75);
    lblPassword.setLocation(85,160);
    add(lblPassword);

    //setting up the text fields for the user to input text
    txtUserName = new JTextField();
    txtUserName.setLocation(175,100);
    txtUserName.setSize(150,30);
    add(txtUserName);

    txtPassword = new JTextField();
    txtPassword.setLocation(175,185);
    txtPassword.setSize(150,30);
    add(txtPassword);

    //setting up the buttons to be pressed by the user
    btnDeleteUser = new JButton("Delete User");
    btnDeleteUser.setLocation(150,250);
    btnDeleteUser.setSize(200,30);
    btnDeleteUser.setActionCommand("Delete User");
    btnDeleteUser.addActionListener(this);
    add(btnDeleteUser);

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
  }

  public void actionPerformed(ActionEvent button) {
    //if the user wants to login, they have to pass certain conditions first
    if(button.getActionCommand().equals("Delete User"))
      {
        //this boolean is used to output the correct error message
        boolean isUsername = true;
        try
          {
            String UserName = txtUserName.getText();
            String Password = txtPassword.getText();
            //this for each loop checks if the username given is in the hashmap
            for(User key : frmReadFiles.UserRatingMap.keySet()){
              if(key.getUserName().equals(UserName)) {
                isUsername = false;
                //even if the username is found, the password is also checked to see if the password matches
                if(key.getPassword().equals(Password)) {
                  //as a user is being removed, the current user is set to null
                  frmLogin.CurrentUser = null;
                  frmReadFiles.UserRatingMap.remove(key);

                  frmStartPage myFrame = new frmStartPage();
                  myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                  myFrame.setSize(500,500);
                  myFrame.setVisible((true));
                  this.dispose();
                }
                //if the password doesnt match the usernmae, the appropriate error message is given
                else{
                  JOptionPane.showMessageDialog(null, "The password given didn't match the given username.");
                }  
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

            //if the username is not in the hashmap, the appropriate error message is given
            if(isUsername){
              JOptionPane.showMessageDialog(null, "The user was not found in the system.");
            }
        }
        //if an error is caused when reading, the appropriate error message is given
        catch(Exception e1){
          JOptionPane.showMessageDialog(null, "Please make sure the username and password are valid strings.");
      }
    }
    //if the user forgot their password, this method is run
    else if(button.getActionCommand().equals("Forgot Password"))
    {
      boolean isUsername = true;
      try
        {
          String UserName = txtUserName.getText();
          //this for each loop checks if the username given is in the hashmap
          for(User key : frmReadFiles.UserRatingMap.keySet())
          {
            //if the user is found in the hashmap, the password is given in a j option pane 
            if(key.getUserName().equals(UserName)) 
            {
              isUsername = false;
              String strPassword = key.getPassword();
              JOptionPane.showMessageDialog(null, "The password for this user is " + strPassword);
            }
          }
          //if the username is not in the hashmap, the appropriate error message is given
          if(isUsername)
          {
            JOptionPane.showMessageDialog(null, "The user was not found in the system.");
          }
        }
      //if an error is caused when reading, the appropriate error message is given
      catch(Exception e1)
        {
        JOptionPane.showMessageDialog(null, "Please make sure the username is a valid input.");
      }
    }
    //this allows the user to go back to the start page of the program
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
