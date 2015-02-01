package com.crm.view;

import com.crm.model.Customer;
import com.crm.service.CustomerService;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

/**
 * Customerpage
 */
public class CustomerPage extends BasePage {
	private static final long serialVersionUID = 1L;

	@SpringBean
	protected CustomerService customerService;

    public CustomerPage() {
//        add( new Label( "customer", customerService.getCustomers() ));
        this.add(createListView());
    }

    public PropertyListView createListView(){
        return new PropertyListView("customers", customerService.getCustomers() ) {
            protected void populateItem(ListItem item) {
                item.add(new Label("firstName"));
                item.add(new Label("lastName"));
                item.add(new Label("organization"));
                item.add(new Label("email"));

            }
        };
    }
}
