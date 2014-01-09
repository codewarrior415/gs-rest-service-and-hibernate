/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 *
 * @author mmurillo
 */
@Entity
@Table(name = "USERS")
public class User extends AbstractEntity{


    
    private String userName;
    @Column(unique = true) 
    private String password;
    private Boolean enabled;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Boolean isEnabled() {
        return enabled;
    }


}
