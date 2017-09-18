package com.exp.cemk.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.MDC;

import domainmodel.Person;

/**
 * Servlet Filter implementation class DebugFilter
 */
public class LogFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LogFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try{
			HttpServletRequest req=(HttpServletRequest) request;
			
			HttpSession session=req.getSession();
			
			
	        Person up=(Person)session.getAttribute(SessionUtil.LOGGED_IN_USER);
	        if(up !=null){
	        	MDC.put("userName",up.getUserName());
	        	MDC.put("userId",up.getUserId());

	        	
	        }
	        MDC.put("ip",request.getRemoteAddr());
			chain.doFilter(request, response);
		} finally {   
			MDC.remove("userName");
			MDC.remove("userId");
			MDC.remove("ip");
			}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
