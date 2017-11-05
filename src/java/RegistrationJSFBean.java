/*
 * Henry O'Connor
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author henoc
 */
@Named(value = "registration")
@RequestScoped
public class RegistrationJSFBean {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String lastIP;


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
    
    /**
     * Creates a new instance of RegistrationJSFBean
     */
    public RegistrationJSFBean() {
    }
    
    public String getResponse(){
        if(firstName == null) return "test";
        else{
          
            return "<p style=\"color:red\">You entered <br />" + firstName +"</p>";
        }
    }
    
    public boolean validateName(String s){
        Pattern p = Pattern.compile("^[$");
        Matcher m = p.matcher(s);
        return m.matches();
    }
    
    public boolean validateEmail(String s){
        Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher m = p.matcher(s);
        return m.matches();
    }
    
    
    
}
