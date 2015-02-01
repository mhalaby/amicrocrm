package com.crm.view;

import com.crm.service.CustomerService;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * Customerpage
 */
public class CustomerPage extends BasePage {
	private static final long serialVersionUID = 1L;

	@SpringBean
	protected CustomerService customerService;

    public CustomerPage(final PageParameters parameters) {
        add( new Label( "customer", customerService.getCustomers() ) );
    }
}
