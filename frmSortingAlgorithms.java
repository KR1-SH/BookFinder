import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.ImageIcon;
import java.util.ArrayList;

public class frmSortingAlgorithms extends JFrame implements ActionListener
{
  //this is essentially a selection sort for an array list, used for the dot product
  public static ArrayList <Integer> SelectionSort(ArrayList <Integer> arrDotProduct)
  {
    for(int i = 0; i <= arrDotProduct.size() - 2; i++)
    {
      for(int j = i + 1; j<= arrDotProduct.size() - 1; j++)
      {
        if(arrDotProduct.get(i) < arrDotProduct.get(j))
        {
          int strTemp = arrDotProduct.get(i);
          arrDotProduct.set(i, arrDotProduct.get(j));
          arrDotProduct.set(j, strTemp);
        }
      }
    } 
    //returns sorted arraylist
    return arrDotProduct;
  }

  //unnecessary functions
  public frmSortingAlgorithms(){}
  public void actionPerformed(ActionEvent button){}
}
