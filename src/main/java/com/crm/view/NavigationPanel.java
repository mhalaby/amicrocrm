package com.crm.view;

import com.crm.main.UserAuthenticatedWebSession;
import com.crm.model.Customer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

/**
 * Created by muhammad on 01.02.15.
 */
public class NavigationPanel extends Panel {
    public NavigationPanel(String id) {
        super(id);
        final UserAuthenticatedWebSession session=(UserAuthenticatedWebSession)getSession();

        Link homePageLink = new Link("home") {
            @Override
            public void onClick() {
                this.setResponsePage(new HomePage());
            }
        };
        this.add(homePageLink);

        Link customersLink = new Link("customers") {
            @Override
            public void onClick() {
                this.setResponsePage(new CustomerPage());
            }
        };
        this.add(customersLink);
        Link sigoutLink = new Link("signout") {
            @Override
            public void onClick() {
                session.invalidateNow();
                this.getRequestCycle();
                this.setResponsePage(new LoginPage());
            }
        };
        this.add(sigoutLink);
        this.add(new Label("username", session.getUsername()) );

    }
}
