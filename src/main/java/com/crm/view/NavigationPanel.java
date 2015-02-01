package com.crm.view;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * Created by muhammad on 01.02.15.
 */
public class NavigationPanel extends Panel {
    public NavigationPanel(String id) {
        super(id);

        Link homePageLink = new Link("home") {
            @Override
            public void onClick() {
                this.setResponsePage(new HomePage(null));
            }
        };
        this.add(homePageLink);

        Link customersLink = new Link("customers") {
            @Override
            public void onClick() {
//                this.setResponsePage(new MovieListPage());
            }
        };
        this.add(customersLink);
    }
}
