package com.crm.view;

import com.crm.main.UserAuthenticatedWebSession;
import org.apache.wicket.markup.html.basic.Label;


/**
 * Homepage
 */

public class HomePage extends BasePage {
	private static final long serialVersionUID = 1L;
    /**
	 * Constructor that is invoked when page is invoked without a session.
	 *
	 */
    public HomePage() {
        UserAuthenticatedWebSession session=(UserAuthenticatedWebSession)getSession();

        add( new Label( "welcome", "Welcome " + session.getUsername()) );

    }
}
