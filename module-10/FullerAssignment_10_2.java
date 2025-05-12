//package com.mycompany.mysqltest;
//I commented out the package because I did not want to include all of the 
//package data in the submission but if I did it wrong I can provide it. 
//The only thing included in the package not here shouldbe the JDBC Driver.


//Brett Fuller
//CSD-420 Assignment 10.2
//5/11/2025

//This program demonstrates the ability to connect a GUI with a mySQL database
//pull and update records.
//I chose to use swing not because I wanted to, doing so forced me to learn swing
//I wanted to use JavaFX but I was unable to get JavaFX working on the computer that 
//mySQL is installed on and I was having issues with remote connections on the computer
//where JavaFX was working.  I spend an embarrasing amount of time trying to get this to
//work. I blame my cold. Anyway I was able to get swing working on the computer that
//had mySQL installed so I made that work.


/*
 * Utilized and heavily modified some of the code from the examples provided by
 * Professor Darrell Payne
 * Bellevue University
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class FullerAssignment_10_2 extends JFrame{
  //Code to interface with SQL and get fans data as an object.
  SelectFans teamFan = new SelectFans();
//Code to build out the interface in Swing I need to create buttons, labels and 
//Text fields. Then add them to rectangles and display them in the Frame  
  
  private final JButton btnDisplay;
  private final JButton btnUpdate;

  private JTextField txtID;
  private JTextField txtFirstName;
  private JTextField txtLastName;
  private JTextField txtFavoriteTeam;

  private final JLabel lblID;
  private final JLabel lblFirstName;
  private final JLabel lblLastName;
  private final JLabel lblFavoriteTeam;
  private final JLabel lblIDFixed;
  private final JLabel lblIDFixedDisplay;

//Code to detect if a button is clicked and then perform an action
  private ActionListener buttonListener = new ActionListener(){

    public void actionPerformed(ActionEvent e){

      String buttonSelected = ((JButton)e.getSource()).getText();
      //Sends the ID from the ID text field to the getById functionand and then calls the update
      //function which updates the fixedTxtdisplay label and textfields.

      if(buttonSelected.equals("Display")){
          int ID = Integer.parseInt(txtID.getText()); 
          
          update(teamFan.getById(ID));
      }
      //this function gets the data from all text fields and the fixedID and
      //then sends that data to the updateDB() method to update the database
      //note I chose to make the id fixed because I did not think the ID should be 
      //somethnga user could change. imlementing the ability to change that would be
      //trivial but I did chose not to allow it.
      if(buttonSelected.equals("Update")){
          int ID = Integer.parseInt(lblIDFixedDisplay.getText());
          String firstName = txtFirstName.getText();
          String lastName = txtLastName.getText();
          String favoriteTeam = txtFavoriteTeam.getText();
          teamFan.updateDB(ID, firstName, lastName, favoriteTeam);
      }    
    }
  };

  public FullerAssignment_10_2(){

    //Create initial state of buttons labels and text fields
    btnDisplay = new JButton("Display");
    btnUpdate = new JButton("Update");
    txtID = new JTextField("");
    txtFirstName = new JTextField("");
    txtLastName = new JTextField("");
    txtFavoriteTeam = new JTextField("");    
    lblID = new JLabel("Look up fan by ID");
    lblFirstName = new JLabel("First Name");
    lblLastName = new JLabel("Last Name");
    lblFavoriteTeam = new JLabel("Favorite Team");
    lblIDFixed = new JLabel("ID");
    lblIDFixedDisplay = new JLabel(""); 
    //define fields that can be edited
    txtID.setEditable(true);
    txtFirstName.setEditable(true);
    txtLastName.setEditable(true);
    txtFavoriteTeam.setEditable(true);    
  }

  public void launchJFrame(){

    // Defines the Simple UI for this app
    setSize(500, 300);
    getContentPane().setLayout(null);
    getContentPane().setBackground(Color.white);

    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    getContentPane().add(btnDisplay);
    getContentPane().add(btnUpdate);
    getContentPane().add(txtID);
    getContentPane().add(txtFirstName);
    getContentPane().add(txtLastName);
    getContentPane().add(txtFavoriteTeam);
    getContentPane().add(lblIDFixed);
    getContentPane().add(lblID);
    getContentPane().add(lblFirstName);
    getContentPane().add(lblLastName);
    getContentPane().add(lblFavoriteTeam);
    getContentPane().add(lblIDFixedDisplay);

    //placement and size of buttons, labels and textfields
    lblIDFixed.setBounds(new Rectangle(65, 10, 100, 30));
    lblFirstName.setBounds(new Rectangle(65, 45, 100, 30));
    lblLastName.setBounds(new Rectangle(65, 80, 100, 30));
    lblFavoriteTeam.setBounds(new Rectangle(65, 115, 100, 30));

    txtID.setBounds(new Rectangle(210, 10, 100, 30));
    lblIDFixedDisplay.setBounds(new Rectangle(210, 10, 100, 30));
    txtFirstName.setBounds(new Rectangle(210, 45, 100, 30));
    txtLastName.setBounds(new Rectangle(210, 80, 100, 30));
    txtFavoriteTeam.setBounds(new Rectangle(210, 115, 100, 30));


    lblID.setBounds(new Rectangle(65, 200, 180, 30));
    txtID.setBounds(new Rectangle(210, 200, 100, 30));
    btnDisplay.setBounds(new Rectangle(320, 200, 90, 30));
    btnUpdate.setBounds(new Rectangle(210, 150, 100, 30));

    setVisible(true);
    //action that happens when the window is closed, closes db connections and
    //exits the application.
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
          teamFan.close();
        System.exit(0);
      }
    });
    //listeners for button clicks
    btnDisplay.addActionListener(buttonListener);
    btnUpdate.addActionListener(buttonListener);
  }

  //code that updates the text in the GUI with the fan object
  private void update(FanRecord record){

    txtFavoriteTeam.setText(record.getFavoriteTeam());
    txtID.setText(record.getID());
    txtFirstName.setText(record.getFirstName());
    txtLastName.setText(record.getLastName());
    lblIDFixedDisplay.setText(record.getID());
  }
//main method launches the GUI
  public static void main(String[] args){

    FullerAssignment_10_2 layout = new FullerAssignment_10_2();
    layout.launchJFrame();
    
  }
}

//this is all the database work
class SelectFans{

  Connection con;

  Statement stmt;

  ResultSet resultSet;
//Initiates a connection to the database
  public SelectFans(){

    try{

      Class.forName("com.mysql.cj.jdbc.Driver");
      //Cannot use pass for password at home on my version of mysql so I included
      //the request version in the submission but kept mine in for my testing
      //con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/databasedb", 
              //"student1", "#8aSCTRBsr!A");
      
      con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/databasedb", 
              "student1", "pass");
      stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }
    catch(Exception e){

      System.out.println("Error connection to database.");
      System.exit(0);
    }
  }
  //queries the database for a record in the fans database with the id sent with the call
  public FanRecord getById(int ID){
      //creates a fanRecord object.
    FanRecord fanRecord = new FanRecord();
    //queries database and hopefully gets a resultset.
    try{    
      String query = "SELECT * FROM fans Where ID = ?";
      PreparedStatement preparedStatement = con.prepareStatement(query);
      preparedStatement.setInt(1,ID);
      resultSet = preparedStatement.executeQuery();
    }
    catch(SQLException e){

      System.out.println(e);
      System.out.println("Result Request Failed");
    }
    
    try{
        
      //get to the result of the resultSet 
      resultSet.next();

      //add data from resultset to the fanRecord created earlier
      fanRecord.setID(resultSet.getString(resultSet.findColumn("ID")));
      fanRecord.setFirstName(resultSet.getString(resultSet.findColumn("firstname")));
      fanRecord.setLastName(resultSet.getString(resultSet.findColumn("lastname")));
      fanRecord.setFavoriteTeam(resultSet.getString(resultSet.findColumn("favoriteteam")));
    }
    catch(Exception e){

      System.out.println(e);
    }
    //returns the fanRecord we created and populated with data
    return fanRecord;
  }
  //method for updating the data in the database
  //in this method we get the fields from the GUI and get the original record
  //if any of the fields are different between the two sets of data and the value
  //from the gui is not null we update the database with that data leveraging
  //prepared statements
  public void updateDB(int ID, String firstName, String lastName, String favoriteTeam){
      FanRecord fanRecord = getById(ID);
      try{
      resultSet.next();
    }
    catch(Exception e){

      System.out.println(e);
    }
    if (!fanRecord.getFirstName().equals(firstName) && firstName != null){
        try{
      String query = "UPDATE fans SET firstname = ? Where ID = ?";
      PreparedStatement preparedStatement = con.prepareStatement(query);
      preparedStatement.setString(1,firstName);
      preparedStatement.setInt(2, ID);
      preparedStatement.executeUpdate();
    }
    catch(SQLException e){

      System.out.println(e);
      System.out.println("Result Request Failed");
    }
       
    }
    if(!fanRecord.getLastName().equals(lastName) && lastName != null){
        try{
        
      String query = "UPDATE fans SET lastName = ? Where ID = ?";
      PreparedStatement preparedStatement = con.prepareStatement(query);
      preparedStatement.setString(1,lastName);
      preparedStatement.setInt(2, ID);
      preparedStatement.executeUpdate();
    }
    catch(SQLException e){

      System.out.println(e);
      System.out.println("Result Request Failed");
    }
    }
    if(!fanRecord.getFavoriteTeam().equals(favoriteTeam) && favoriteTeam != null){
        try{
        
      String query = "UPDATE fans SET favoriteTeam = ? Where ID = ?";
      PreparedStatement preparedStatement = con.prepareStatement(query);
      preparedStatement.setString(1,favoriteTeam);
      preparedStatement.setInt(2, ID);
      preparedStatement.executeUpdate();
    }
    catch(SQLException e){

      System.out.println(e);
      System.out.println("Result Request Failed");
    }
    }      
  }
  //method to close the database connections
  public void close(){

    try{
      stmt.close();
      con.close();
    }
    catch(SQLException e){

      System.out.println("Connection close failed");
    }
  }
}
//This class creates the object that we use to update the gui and compare data.
class FanRecord{

  String favoriteTeam;
  String ID;
  String firstName;
  String lastName;

  //we create getters and setters for each of the fields in the object.
  public String getFavoriteTeam(){

    return favoriteTeam;
  }

  public void setFavoriteTeam(String favoriteTeam){

    this.favoriteTeam = favoriteTeam;
  }

  public String getID(){

    return ID;
  }

  public void setID(String ID){

    this.ID = ID;
  }

  public String getFirstName(){

    return firstName;
  }
  
  public void setFirstName(String firstName){

    this.firstName = firstName;
  }

  public String getLastName(){

    return lastName;
  }

  public void setLastName(String lastName){

    this.lastName = lastName;
  }
}