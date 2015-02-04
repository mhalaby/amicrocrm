package com.crm.view.customer;

import com.crm.model.Customer;
import com.crm.service.CustomerService;
import com.crm.view.BasePanel;
import com.crm.view.HomePage;
import com.crm.view.customer.CustomerEditForm;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

/**
 * Created by muhammad on 04.02.15.
 *
 * The table for views the customers list
 * The table is used for adding, editing and deleting customers
 *
 */
public class CustomerTable extends BasePanel {
    @SpringBean
    protected CustomerService customerService;
    ModalWindow customerModal;
    final ListView<Customer> customersListView;
    LoadableDetachableModel<List<Customer>> customerListModel;
    Form<?> form;

    /**
     * The constructor adds the components to the page and
     * wraps the customer model
     *
     * @param id
     *            The component id
     *
     */
    public CustomerTable(String id) {
        super(id);
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
    /**
     * create the New button for adding customers and bind it to the modal
     *
     *
     * @param customerModal
     *            modal to be binded
     *  @return AjaxLink
     */
    public AjaxLink createNewButton(final ModalWindow customerModal){
        return new AjaxLink<Void>("showCustomerModal")
        {
            @Override
            public void onClick(AjaxRequestTarget target) {
                customerModal.show(target);
            }
        };
    }
    /**
     * create the Go to home button for adding customers and bind it to the homepage
     *
     *
     *  @return AjaxLink
     */
    public AjaxLink createGoHomeButton(){
        return new AjaxLink<Void>("home")
        {
            @Override
            public void onClick(AjaxRequestTarget target) {
                this.setResponsePage(new HomePage());
            }
        };
    }
    /**
     * create the Delete button which deletes a customer
     *
     *
     * @param customer
     *            customer to be deleted
     *  @return AjaxLink
     */
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
    /**
     * create the list of customers. Also, attaches a modal to every edit button
     *
     *
     *  @return PropertyListView
     */
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
    /**
     * The method creates a Customer Modal and bind a customer to it.
     * It also updates the table when the Modal is closed
     *
     * @param componentId
     *            The component id
     *  @param customer
     *          customer binded to the modal
     *  @return ModalWindow
     */
    public ModalWindow createNewCustomerModal(String componentId, Customer customer){
        ModalWindow customerModal = new ModalWindow(componentId);
        customerModal.setContent(new CustomerEditForm(customerModal.getContentId(),customer));
        customerModal.setCookieName("customer-modal");
        customerModal.setCloseButtonCallback(new ModalWindow.CloseButtonCallback() {
            @Override
            public boolean onCloseButtonClicked(AjaxRequestTarget target) {
                target.add(form);
                return true;
            }
        });
        return customerModal;
    }
}
