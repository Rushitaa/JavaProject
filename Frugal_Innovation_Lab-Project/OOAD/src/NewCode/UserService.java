/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NewCode;

/**
 *
 * @author Mallika
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import ooad.User;

/**
 *
 * @author jas
 */
public class UserService  implements Serializable {

     @SuppressWarnings("FieldMayBeFinal")
	 private EntityManager manager;
	 
     public UserService(EntityManager manager) {
	 this.manager = manager;
    }
	 
    // method to create a new record
    public User createUser(int userId, String userName, String password) {
            User user = new User();
 	    user.setUserId(userId);
            user.setUserName(userName);
            user.setPassword(password);
            
 	    manager.persist(user);
 	    return user;
     }
    
    // method to read a record
     public User readUser(int userId) {
    	 User user = manager.find(User.class,userId);
    	 return user;   	 
     }

     // method to read all records
     public List<User> readAll() {
    	 TypedQuery<User> query = manager.createQuery("SELECT e FROM user e", User.class);
    	 List<User> result =  query.getResultList();

    	 return result;   	 
     }
     
    // method to update a record
     public User updateUser(int userId, String userName, String password) {
    	 User user = manager.find(User.class, userId);
    	 if (user != null) {
    		 //user.setUserId(userId);
                 user.setUserName(userName);
                 user.setPassword(password);
                 
    	 }
    	 return user;
     }

    // method to delete a record
    public void deleteUser(int userId) {
    	 User user = manager.find(User.class, userId);
    	 if (user != null) {
    		 manager.remove(user);
    	 }
    }
    
}

