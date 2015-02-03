package com.crm.view;

import com.crm.main.UserAuthenticatedWebSession;
import com.crm.model.Customer;
import com.crm.service.CustomerService;
import org.apache.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import java.util.List;

/**
 * Customerpage
 */
public class CustomerPage extends BasePage {
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger( CustomerPage.class );

    @SpringBean
    protected CustomerService customerService;
    ModalWindow customerModal;
    final ListView<Customer> customersListView;
    LoadableDetachableModel<List<Customer>> customerListModel;
    Form<?> form;
    public CustomerPage() {
        customerListModel = new LoadableDetachableModel<List<Customer>>() {
            @Override
            protected List<Customer> load() {
                return customerService.getCustomers();
            }
        };

        form = new Form<Object>("form");
        this.add(form);
        customersListView = createListView();
        customersListView.setOutputMarkupId(true);
        form.add(customersListView);
        form.setOutputMarkupId(true);
        customerModal = createNewCustomerModal("customerModal",new Customer());
        this.add(customerModal);
        AjaxLink newButton= createNewButton(customerModal);
        this.add(newButton);
        checkRoles(newButton);
        this.add(createGoHomeButton());
    }

    public AjaxLink createNewButton(final ModalWindow customerModal){
        return new AjaxLink<Void>("showCustomerModal")
        {
            @Override
            public void onClick(AjaxRequestTarget target) {
                customerModal.show(target);
            }
        };
    }
    public AjaxLink createGoHomeButton(){
        return new AjaxLink<Void>("home")
        {
            @Override
            public void onClick(AjaxRequestTarget target) {
                this.setResponsePage(new HomePage());
            }
        };
    }
    public AjaxLink createDeleteButton(final Customer customer){
        return new AjaxLink<Void>("deleteCustomer")
        {
            @Override
            public void onClick(AjaxRequestTarget target) {
                customerService.delete(customer);
                target.add(form);
            }
        };
    }
    public PropertyListView createListView(){
        return new PropertyListView<Customer>("customers",customerListModel ) {
            protected void populateItem(ListItem<Customer> item) {
                final Customer customer = item.getModelObject();
                item.add(new Label("firstName"));
                item.add(new Label("lastName"));
                item.add(new Label("organization"));
                item.add(new Label("email"));
                ModalWindow editModal = createNewCustomerModal("editCustomerModal",customer);
                item.add(editModal);
                AjaxLink editButton= createNewButton(editModal);
                AjaxLink deleteButton= createDeleteButton(customer);
                item.add(editButton);
                item.add(deleteButton);
                checkRoles(editButton);
                checkRoles(deleteButton);
            }
        };
    }

    public ModalWindow createNewCustomerModal(String componentId, Customer customer){
        ModalWindow customerModal = new ModalWindow(componentId);
        customerModal.setContent(new NewCustomerPanel(customerModal.getContentId(),customer));
        customerModal.setCookieName("customer-modal");
        customerModal.setCloseButtonCallback(new ModalWindow.CloseButtonCallback() {
            @Override
            public boolean onCloseButtonClicked(AjaxRequestTarget target) {
                target.add(CustomerPage.this.form);
                return true;
            }
        });
        return customerModal;
    }
}