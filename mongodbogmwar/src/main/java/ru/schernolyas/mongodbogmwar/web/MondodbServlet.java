/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.schernolyas.mongodbogmwar.web;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author Sergey Chernolyas
 */
@WebServlet(asyncSupported = true,value = "/*",description = "MondoDB OGM Example",loadOnStartup = 1)
@VaadinServletConfiguration(productionMode = false,ui = MondoDbUI.class)
public class MondodbServlet extends VaadinServlet {
    

}
