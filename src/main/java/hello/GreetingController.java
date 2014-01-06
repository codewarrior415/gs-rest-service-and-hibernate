package hello;

import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import corp.entities.Account;

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
