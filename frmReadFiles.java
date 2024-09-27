import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.ImageIcon;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class frmReadFiles extends JFrame implements ActionListener
{
  //these static variables are created to be used in other files 
  public static Map<User, ArrayList<Integer>> UserRatingMap = new HashMap<User, ArrayList<Integer>>();
  public static int intBooks = 0;
  public static String RatingFileName = "";
  public static String BookFileName = "";
  public static ArrayList<String> BookList = new ArrayList<String>();
  public static int intLine;
  public static ArrayList<String> UserInformation = new ArrayList<String>();
  public static ArrayList<String> UserRatings = new ArrayList<String>();

  //this arraylist string method is used to get the user ratings in a string array
  public static ArrayList<String> strUserRatings(String RatingFileName)
  {
    boolean nameOrRate = true;
    ArrayList<String> UserRatings = new ArrayList<String>();
    try
      {
        //this reads the ratings file
        FileReader fr = new FileReader(RatingFileName);
        BufferedReader br = new BufferedReader(fr);
        String strLine = br.readLine();
        //while the rating file still has information, it is read
        while(strLine != null){
          //in this method, the user information is not needed so nothing is read
          if(nameOrRate) {}
          //the ratings are added to an arraylist
          else if (!nameOrRate) {
            UserRatings.add(strLine);
          }
          //allows the files to be continously read
          strLine = br.readLine();
          nameOrRate = !nameOrRate;

        }
        fr.close();
      }

    catch(IOException e){}
    //returns the arraylist of ratings
    return UserRatings;
  }

  //this method takes the name of the files, and stores the user and their ratings in a hashmap
  public static void ReadFiles(String RatingFileName, String BookFileName){
    boolean nameOrRate = true; //true = name, false = rate

    //this try and catch statement reads the rating file and stores the information in a hashmap
    try
      {
        FileReader fr = new FileReader(RatingFileName);
        BufferedReader br = new BufferedReader(fr);
        String strLine = br.readLine();
        //this user variable is used to assign a new user
        User ReadUser = null;
        //continously reads until the line is empty
        while(strLine != null){
          //for lines with names, the names are split, then added to the hashmap depending on the information given
          if(nameOrRate)
          {
            String [] strUserInfo = strLine.split(",");
            //the user object is only called with the username
            if(strUserInfo.length == 1){
              ReadUser = new User(strLine);
            }
            //the user object is called with all 3 parameters
            else{
              ReadUser = new User(strUserInfo[0], strUserInfo[1], strUserInfo[2]);
            }
            //the user information is added to an arraylist for the hashmap
            UserInformation.add(ReadUser.getUserName() + "," + ReadUser.getName() + "," + ReadUser.getPassword());
          }
          //for reading lines with user ratings
          else if (!nameOrRate) {
            ArrayList<Integer> RatingList = new ArrayList<Integer>();
            //the user ratings are split, then added to an arraylist
            for(int i = 0; i < strLine.split(" ").length; i++){
              RatingList.add(Integer.parseInt(strLine.split(" ")[i]));
            }
            //the ratings are added to an arraylist
            UserRatings.add(strLine);
            //the user and their rating is added to a hashmap
            UserRatingMap.put(ReadUser, RatingList);

          }
          //allows for the file to be continously read
          strLine = br.readLine();
          nameOrRate = !nameOrRate;

        }
        fr.close();
      }

    catch(IOException e){}

    //this try and catch statement writes the new information back to the rating file
    try
      {
        FileWriter fw = new FileWriter(RatingFileName);
        PrintWriter pw = new PrintWriter(fw);
        for(int i = 0; i < UserInformation.size(); i++)
        {
          //writes the user information, followed by their ratings
          pw.println(UserInformation.get(i));
          pw.println(UserRatings.get(i));
        }

        fw.close();
      }

    catch(IOException e){}

    //this try and catch statement reads the book file and stores the information in an arraylist
    try
      {
        FileReader fr = new FileReader(BookFileName);
        BufferedReader br = new BufferedReader(fr);
        Book ReadBook = null;
        String strLine = br.readLine();
        //these 2 variables are used for the ISBN number and finding how many books there are
        intLine++;
        intBooks++;
        while(strLine != null)
        {
          //the line is split through "," making an array of the book info
          String [] strBookInfo = strLine.split(",");
          //the book object is called with 3 parameters
          if(strBookInfo.length == 2){
            ReadBook = new Book(strBookInfo[0], strBookInfo[1], "" + intLine);
          }
          //the book object is called with all parameters
          else{
            ReadBook = new Book(strBookInfo[0], strBookInfo[1], Integer.parseInt(strBookInfo[2]), strBookInfo[3]);
          }
          //the books are added to an arraylist of all books and their information
          BookList.add(ReadBook.getAuthor() + "," + ReadBook.getTitle() + "," + ReadBook.getYear() + "," + ReadBook.getISBN());
          strLine = br.readLine();

          intLine++;
          intBooks++;
        }
        fr.close();
      }
    catch(IOException e){}

    //this try and catch statement writes the new information back to the book file    
    try
      {
        FileWriter fw = new FileWriter(BookFileName);
        PrintWriter pw = new PrintWriter(fw);
        for(int i = 0; i < BookList.size(); i++)
        {
          //the books are printed line by line with all their information
          pw.println(BookList.get(i));
        }

        fw.close();
      }

    catch(IOException e){}
  }

  //this set of JLabel texts, create labels for each instance of text on the screen
  JLabel lblTitle;
  JLabel lblFileName;
  JLabel lblFileName2;
  JLabel lblReadFile;
  JLabel lblReadFile2;

  //this is a set of JButtons for the user to proceed with the program
  JButton btnReadNewFiles;
  JButton btnReadFiles;
  JButton btnBack;

  //this JTextField is used to get the input from the user
  JTextField txtRatingName;
  JTextField txtBookName;

  public frmReadFiles()
  {
    //sets the dimension of the JFrame
    setLayout(null);

    //defining the background color for the gui
    Color myColor = new Color(100,149,237);
    //setting the background color to the gui
    getContentPane().setBackground(myColor);

    //setting up the labels to display information to the user
    lblTitle = new JLabel("<html><font size='5' color=white> File Reading");
    lblTitle.setSize(500,75);
    lblTitle.setForeground(Color.BLACK);
    lblTitle.setLocation(180,0);
    add(lblTitle);

    lblFileName = new JLabel("Rating File Name: ");
    lblFileName.setSize(500,75);
    lblFileName.setLocation(70,80);
    add(lblFileName);

    lblFileName2 = new JLabel("Book File Name: ");
    lblFileName2.setSize(500,75);
    lblFileName2.setLocation(295,80);
    add(lblFileName2);

    lblReadFile = new JLabel("Only use this feature if a new file is to be read.");
    lblReadFile.setForeground(Color.BLACK);
    lblReadFile.setFont(new Font("SansSerif", Font.ITALIC, 12));
    lblReadFile.setSize(500,75);
    lblReadFile.setLocation(105,45);
    add(lblReadFile);

    lblReadFile2 = new JLabel("Otherwise, just use this read files button.");
    lblReadFile2.setForeground(Color.RED);
    lblReadFile2.setFont(new Font("SansSerif", Font.ITALIC, 12));
    lblReadFile2.setSize(500,75);
    lblReadFile2.setLocation(115,215);
    add(lblReadFile2);

    //setting up the buttons to be pressed by the user
    btnReadNewFiles = new JButton("Read New Files");
    btnReadNewFiles.setLocation(150,175);
    btnReadNewFiles.setSize(200,30);
    btnReadNewFiles.setActionCommand("Read New Files");
    btnReadNewFiles.addActionListener(this);
    add(btnReadNewFiles);

    btnReadFiles = new JButton("Read Files");
    btnReadFiles.setLocation(150,275);
    btnReadFiles.setSize(200,30);
    btnReadFiles.setActionCommand("Read Files");
    btnReadFiles.addActionListener(this);
    add(btnReadFiles);

    btnBack = new JButton("Back");
    btnBack.setLocation(5,445);
    btnBack.setSize(120,30);
    btnBack.setActionCommand("Back");
    btnBack.addActionListener(this);
    add(btnBack);

    //setting up the text fields for the user to input text
    txtRatingName = new JTextField();
    txtRatingName.setLocation(60,130);
    txtRatingName.setSize(150,30);
    add(txtRatingName);

    txtBookName = new JTextField();
    txtBookName.setLocation(280,130);
    txtBookName.setSize(150,30);
    add(txtBookName);

  }

  public void actionPerformed(ActionEvent button) {
    //runs a method using the new file names, which gets read and stored in a hashmap
    if(button.getActionCommand().equals("Read New Files"))
    {
      //checks if the user input is valid for the file names
      try
        {
          RatingFileName = txtRatingName.getText();
          BookFileName = txtBookName.getText();
          boolean isFileValid = true;
          //if empty strings are entered, relevant error messages are outputed
          if(RatingFileName.equals("") || BookFileName.equals(""))
          {
            JOptionPane.showMessageDialog(null, "Please make sure all fields are filled out before\nattmepting to read new files.");
            isFileValid = false;
          }

          if(isFileValid)
          {
            ReadFiles(RatingFileName, BookFileName);
          }
        }
      //if an error is found when reading, a relevant error messages are outputed
      catch(Exception e1){
        JOptionPane.showMessageDialog(null, "Please make sure the 2 rating files are filled in,\nwith appropriate text (ex. ratings.txt).");
      }

    }
    //runs a method using the regular file names, which gets read and stored in a hashmap
    else if(button.getActionCommand().equals("Read Files"))
    {
      RatingFileName = "ratings.txt";
      BookFileName = "books.txt";
      ReadFiles(RatingFileName, BookFileName);
    }

    //allows the user to go back to the previous page, which in this case would be the start page
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
