package com.crm.view;

import com.crm.detachableModel.CustomerModel;
import com.crm.model.Customer;
import com.crm.service.CustomerService;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.*;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * Created by muhammad on 02.02.15.
 */
public class NewCustomerPanel extends Panel {
    @SpringBean
    private CustomerService customerService;

    public NewCustomerPanel(String contentId,final Customer customer) {
        super(contentId);
        final IModel<Customer> customerModel = new CustomerModel(customerService, customer);

        Form<Customer> form = new Form<Customer>("form",
                new CompoundPropertyModel<Customer>(customerModel));
        this.add(form);

        form.add(new TextField<Customer>("firstName"));
        form.add(new TextField<Customer>("lastName"));
        form.add(new TextField<Customer>("organization"));
        form.add(new TextField<Customer>("email"));
        form.add(new Button("submit") {
            @Override
            public void onSubmit() {
                customerService.save(customerModel.getObject());
                info("Customer was saved");
            }
        });

    }
}
