package com.apodoba.shop.client;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.servlet.http.HttpServletRequest;

import com.apodoba.shop.beans.PurchaseBean;
import com.apodoba.shop.domain.shop.User;
import com.apodoba.shop.jmx.JMXExampleImplMBean;
import com.apodoba.shop.jmx.JMXExampleMBean;

@SessionScoped
@ManagedBean(name="userMB")
public class UserMB {
	
	@EJB
	private PurchaseBean purchaseBean;
	
	private User user;
 
    public User getUser(){
        if(user == null){
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            String userEmail = context.getUserPrincipal().getName();
            user = purchaseBean.getUserByEmail(userEmail);
        }
 
        return user;
    }
    
    public void register() throws MalformedObjectNameException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException{
    	MBeanServer mbeanServer = java.lang.management.ManagementFactory.getPlatformMBeanServer();
    	ObjectName objectName = new ObjectName("com.apodoba.shop.jmx:service=MBeanExample");
    	JMXExampleMBean bean = new JMXExampleImplMBean("EJB Client");
    	try{
    		mbeanServer.registerMBean(bean, objectName);
    		Util.sendInfoMessageToUser("MBeanExample registered", FacesContext.getCurrentInstance());
    	} catch (InstanceAlreadyExistsException e) {
    		Util.sendInfoMessageToUser("MBeanExample already registered", FacesContext.getCurrentInstance());
    		System.out.println("REGISTERED");
    	}
    }
 
    public String logOut(){
        getRequest().getSession().invalidate();
        return "logout";
    }
    
    private HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
}
