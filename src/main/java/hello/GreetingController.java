package hello;

import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import corp.entities.Account;
import corp.entities.User;
import corp.entities.UserRole;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @RequestMapping("/greeting")
    @ResponseBody
    public Greeting greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        
        Account a = new Account();
        a.setName(name);
        
        entityManager.persist(a);
        
        UserRole ur = new UserRole();
        ur.setAuthority("TEST");
        ur.setUser(null);
        ur.setUser_Role_Id(3L);
        
        User u = new User();
        u.setUserName("test");
        u.setPassword("name");
        u.setEnabled(true);
        u.setUser_Id(3);
        
        entityManager.persist(u);
        
        List<User> ulist = new ArrayList<User>();
        ulist.add(u);
        
        ur.setUser(ulist);
        
        
       
        entityManager.persist(ur);

        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }
    
    @Transactional
    @RequestMapping("/api")
    @ResponseBody
    public Greeting api(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        
        Account a = new Account();
        a.setName(name);
        
        entityManager.persist(a);

        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

}
