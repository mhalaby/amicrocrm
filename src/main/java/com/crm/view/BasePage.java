package com.crm.view;

/**
 * Created by muhammad on 01.02.15.
 */
import java.util.Date;

import com.crm.main.UserAuthenticatedWebSession;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

public class BasePage extends WebPage {


    public BasePage() {
        Date now = new Date();
        NavigationPanel navigationPanel = new NavigationPanel("navigationPanel");
        this.add(navigationPanel);
        this.add(new Label("datetime", now.toString()));
    }
    protected void checkRoles(Component component) {
        UserAuthenticatedWebSession session=(UserAuthenticatedWebSession)getSession();
        if(!session.getRoles().contains("ROLE_ADMIN")){
            component.setEnabled(false);
        }
    }
}
