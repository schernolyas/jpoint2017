/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.schernolyas.mongodbogmwar.web;

import com.vaadin.annotations.Title;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import javax.naming.NamingException;
import ru.schernolyas.mongodbogmwar.jpa.Person;

/**
 *
 * @author Сергей
 */
@Title(value = "MongoDB Example")
public class MondoDbUI extends UI {

    @Override
    protected void init(VaadinRequest request) {

        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);

        BeanItemContainer<Person> persons = new BeanItemContainer<>(Person.class);
        try {
        persons.addAll(EJBUtil.getInstance().findPersonFacade().findAll());
        }catch (NamingException ne) {
            throw new RuntimeException(ne);
        }

        Grid grid = new Grid("Persons", persons);
        grid.setColumns("id", "name");
        grid.setWidth(100, Unit.PERCENTAGE);
        layout.addComponent(grid);

        setContent(layout);
    }

}
