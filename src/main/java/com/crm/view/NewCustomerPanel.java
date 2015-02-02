package com.crm.view;

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
 */
public class NewCustomerPanel extends Panel {
    @SpringBean
    private CustomerService customerService;

    public NewCustomerPanel(String contentId,final Customer customer) {
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
//        form.add(new Button("submit") {
//            @Override
//            public void onSubmit() {
//                if(Session.get().getFeedbackMessages().size() == 0) {
//                    customerService.save(customerModel.getObject());
//                    info("Customer was saved");
//                }
//            }
//        });
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
