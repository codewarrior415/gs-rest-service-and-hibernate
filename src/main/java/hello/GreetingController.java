package hello;

import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.config.java.Account;
import jpa.config.java.JpaConfig;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@ContextConfiguration(classes = JpaConfig.class)
@Controller
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping("/greeting")
    public @ResponseBody
    Greeting greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {

        //Jedis jedis = new Jedis("localhost");
        //String value = jedis.get("foo");
        
        
        //Query query = entityManager.createQuery("from Account a where a.id=:id").setParameter("id", 1L);
        //Account a = (Account) query.getSingleResult();
        
        Account a = new Account();
        a.setName("test");
        
        entityManager.persist(a);

        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

}
