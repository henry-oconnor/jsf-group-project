/*
 * Henry O'Connor
 */

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author henoc
 */
@Named(value = "registration")
@SessionScoped
public class RegistrationJSFBean implements Serializable{
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String lastIP;
    private PreparedStatement statement;
    private Connection connection;
    private ArrayList<String> userList;
    
    public String clearFields(){
        return "/page.xhtml?faces-redirect=true";
    }
    
    public String printResults(){
        if(firstName == null) return "";
        else{
            return "Registration submitted";
        }
    }
    
    public String grabIP(){
        HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        this.lastIP = req.getRemoteAddr();
        return lastIP;
    }
    
    /**
     * Inserts data from form into database table
     * @throws SQLException 
     */
    public void submitData() throws SQLException{
        statement = connection.prepareStatement("insert into users(username,"
                + "firstname, lastname, pass, email, lastIP) values (?,?,?,?,?,?)");
        statement.setString(1, username);
        statement.setString(2, firstName);
        statement.setString(3, lastName);
        statement.setString(4, password);
        statement.setString(5, email);
        statement.setString(6, grabIP());
        statement.executeUpdate();
    }
    
    /**
     * returns Users table from database in the form off an Arraylist
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ResultSet getUsers()throws SQLException, ClassNotFoundException{
        PreparedStatement statement = connection.prepareStatement("select username, firstname,"
                + "lastname, email, lastIP from users");

        return statement.executeQuery();
//        ResultSet resultSet = statement.executeQuery();
//
//        userList = new ArrayList<>();
//
//        while(resultSet.next()){
//            userList.add(resultSet.getString(1));
//            userList.add(resultSet.getString(2));
//            userList.add(resultSet.getString(3));
//            userList.add(resultSet.getString(4));
//            userList.add(resultSet.getString(5));
//        }
    }
    
    /**
     * Initialize a database connection with an instance of JDBC driver
     */
    public void initializeJdbc(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
            
            connection = DriverManager.getConnection(
            "jdbc:mysql://localhost/registration", "root", "yourSequelRoot");
        }
        catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Can't connect");
        }
    }
    
    /**
     * Open database connection
     */
    public RegistrationJSFBean() {
        initializeJdbc();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String u) {
        this.username = u;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastIP() {
        return lastIP;
    }

    public void setLastIP(String lastIP) {
        this.lastIP = lastIP;
    }

}
