package com.crm.view;

import com.crm.main.UserAuthenticatedWebSession;
import com.crm.view.customer.CustomerPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * Created by muhammad on 01.02.15.
 *
 * Navigation panel which is on the top of all the pages
 */
public class NavigationPanel extends BasePanel {
    public NavigationPanel(String id) {
        super(id);

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
        Link signoutLink = new Link("signout") {
            @Override
            public void onClick() {
                session.invalidateNow();
                this.setResponsePage(new LoginPage());
            }
        };
        this.add(signoutLink);
        this.add(new Label("username", session.getUsername()) );

    }
}
