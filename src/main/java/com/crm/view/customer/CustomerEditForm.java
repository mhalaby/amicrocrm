package com.crm.view.customer;

import com.crm.detachableModel.CustomerModel;
import com.crm.model.Customer;
import com.crm.service.CustomerService;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.*;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * Created by muhammad on 02.02.15.
 *
 * The form for editing customers
 */
public class CustomerEditForm extends Panel {
    @SpringBean
    private CustomerService customerService;
    /**
     * The constrictors adds the components to the page and
     * wraps the customer model
     *
     * @param contentId
     *            The component id
     *@param customer
     *          the customer to be edited
     */
    public CustomerEditForm(String contentId, final Customer customer) {
        super(contentId);

        final IModel<Customer> customerModel = new CustomerModel(customerService, customer);

        final FeedbackPanel feedback = new FeedbackPanel("feedback");
        feedback.setOutputMarkupId(true);

        Form<Customer> form = new Form<Customer>("form",
                new CompoundPropertyModel<Customer>(customerModel));
        this.add(form);
        form.add(feedback);

        form.add(new TextField<Customer>("firstName").setRequired(true));
        form.add(new TextField<Customer>("lastName").setRequired(true));
        form.add(new TextField<Customer>("organization").setRequired(true));
        form.add(new TextField<Customer>("email").setRequired(true));

        form.add(new AjaxSubmitLink("submit") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                customerService.save(customerModel.getObject());
                info("Customer was saved");
                target.appendJavaScript(
                        "$(\".w_close\").click();");
                target.add(feedback);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.add(feedback);
            }
        });
        form.setOutputMarkupId(true);

    }
}
