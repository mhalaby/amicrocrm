package com.crm.view;

import com.crm.main.UserAuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.crm.service.CustomerService;

/**
 * Homepage
 */

public class HomePage extends BasePage {
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	protected CustomerService customerService;

    /**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public HomePage() {
        UserAuthenticatedWebSession session=(UserAuthenticatedWebSession)getSession();

        add( new Label( "welcome", "Welcome " + session.getUsername()) );

    }
}
