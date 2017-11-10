/*
 * Henry O'Connor
 */

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author henoc
 */
@Named(value = "beanyBby")
@RequestScoped
public class TimeBean {

    /**
     * Creates a new instance of TimeBean
     */
    public TimeBean() {
    }
    
    public String getTime(){
        return new java.util.Date().toString();
    }
    
}
