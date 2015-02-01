package com.crm.view;

/**
 * Created by muhammad on 01.02.15.
 */
import java.util.Date;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

public class BasePage extends WebPage {
    public BasePage() {
        this(null);
    }

    public BasePage(IModel model) {
        super(model);
        Date now = new Date();
        NavigationPanel navigationPanel = new NavigationPanel("navigationPanel");
        this.add(navigationPanel);
        this.add(new Label("datetime", now.toString()));
    }
}
