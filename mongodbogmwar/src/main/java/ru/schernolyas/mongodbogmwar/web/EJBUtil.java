/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.schernolyas.mongodbogmwar.web;

import java.text.MessageFormat;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import ru.schernolyas.mongodbogmwar.ejb.PersonFacade;

/**
 *
 * @author ChernolyasS
 * 
*/
public class EJBUtil {

    

    private static final String LOCAL_FORMAT = "java:module/{0}";

    private EJBUtil() {

    }

    public static EJBUtil getInstance() {
        return EJBUtilHolder.INSTANCE;
    }

    private static class EJBUtilHolder {

        private static final EJBUtil INSTANCE = new EJBUtil();
    }

    private String getJndiPath(Class ejbClass) {
        return MessageFormat.format(LOCAL_FORMAT, new Object[]{ejbClass.getSimpleName()});
    }

    public PersonFacade findPersonFacade() throws NamingException {
        String jndiPath = getJndiPath(PersonFacade.class);
        return (PersonFacade) (new InitialContext()).lookup(jndiPath);
    }
   
    
}
