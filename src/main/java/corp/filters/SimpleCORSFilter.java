/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corp.filters;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

/**
 *
 * @author mmurillo
 */
@Component
public class SimpleCORSFilter implements Filter {

    private Properties prop = new Properties();

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        InputStream in = getClass().getResourceAsStream("/META-INF/app.properties");
        prop.load(in);
        in.close();

        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        Set<String> allowedOrigins = new HashSet<String>(Arrays.asList(prop.getProperty("allowed.origins").split(",")));

        //if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
            String originHeader = request.getHeader("Origin");

            if (allowedOrigins.contains(request.getHeader("Origin"))) {
                response.addHeader("Access-Control-Allow-Origin", originHeader);
            }

            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with");

        //}

      
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }

}
