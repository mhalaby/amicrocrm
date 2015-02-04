package com.crm.view;

import java.util.Date;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
/**
 * Created by muhammad on 01.02.15.
 * This page provides a template for other pages
 */
public class BasePage extends WebPage {
    /**
     *Constructs a page with date as a footer
     * and navigation panel.
     *
     */
    public BasePage() {
        Date now = new Date();
        NavigationPanel navigationPanel = new NavigationPanel("navigationPanel");
        this.add(navigationPanel);
        this.add(new Label("datetime", now.toString()));
    }

}
