package org.verlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author verlet
 * @date 2018/2/16
 */
@WebFilter
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("TimeFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("TimeFilter start");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("TimeFilter stop");
    }

    @Override
    public void destroy() {
        System.out.println("TimeFilter destroy");
    }
}
