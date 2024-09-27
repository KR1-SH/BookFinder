/*
Author: Krish Patel (26)
Date: 15/06/2024
Description: This is a recommendation system that has many features to create the best user interface. This program is also ICS4U Culminating.
*/

import javax.swing.*;

public class Main 
{
  public static void main(String[] args) 
  {
    //this calls the first page in the program, the Start Page
    frmStartPage myFrame = new frmStartPage();
    myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    myFrame.setSize(500,500);
    myFrame.setVisible((true));
  }

}
