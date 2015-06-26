package com.apodoba.shop.client;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.apodoba.shop.beans.PurchaseBean;
import com.apodoba.shop.domain.shop.User;

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
 
    public String logOut(){
        getRequest().getSession().invalidate();
        return "logout";
    }
 
    private HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
}
