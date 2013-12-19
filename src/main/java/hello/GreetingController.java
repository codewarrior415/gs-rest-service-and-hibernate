package hello;

import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jpa.config.java.Account;

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

        //Jedis jedis = new Jedis("localhost");
        //String value = jedis.get("foo");
        
        
        //Query query = entityManager.createQuery("from Account a where a.id=:id").setParameter("id", 1L);
        //Account a = (Account) query.getSingleResult();
        
        Account a = new Account();
        a.setName(name);
        
        entityManager.persist(a);

        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

}
