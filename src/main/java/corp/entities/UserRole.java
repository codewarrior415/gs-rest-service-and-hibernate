/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package corp.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author mmurillo
 */
@Entity
@Table(name = "USER_ROLES")
public class UserRole {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long user_Role_Id;

    public Long getUser_Role_Id() {
        return user_Role_Id;
    }

    public void setUser_Role_Id(Long user_Role_Id) {
        this.user_Role_Id = user_Role_Id;
    }
    
    
    @OneToMany(fetch=FetchType.LAZY)
    private List<User> user;

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

   
    private  String authority;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    
    
    
}
