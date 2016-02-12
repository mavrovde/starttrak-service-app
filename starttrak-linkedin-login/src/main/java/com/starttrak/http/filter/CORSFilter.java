package com.starttrak.http.filter;

import org.jboss.logging.Logger;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;

/**
 * @author serg.mavrov@gmail.com
 */
public class CORSFilter implements Filter {

    private final static Logger logger = Logger.getLogger(CORSFilter.class);

    public CORSFilter() {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,HEAD,DELETE,OPTIONS");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Headers", "Content-Type");
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
        logger.info("CORS filter initiated");
    }

}