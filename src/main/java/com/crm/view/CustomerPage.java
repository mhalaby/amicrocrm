package com.crm.view;

import com.crm.model.Customer;
import com.crm.service.CustomerService;
import org.apache.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
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
    final ModalWindow customerModal;
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
        customerModal = createNewCustomerModal();
        this.add(customerModal);
        this.add(createNewButton(customerModal));
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
    public PropertyListView createListView(){
        return new PropertyListView<Customer>("customers",customerListModel ) {
            protected void populateItem(ListItem<Customer> item) {
                final Customer customer = item.getModelObject();
                item.add(new Label("firstName"));
                item.add(new Label("lastName"));
                item.add(new Label("organization"));
                item.add(new Label("email"));
            }
        };
    }

    public ModalWindow createNewCustomerModal(){
        final ModalWindow customerModal = new ModalWindow("customerModal");
        customerModal.setContent(new NewCustomerPanel(customerModal.getContentId(),new Customer()));
        customerModal.setCookieName("customer-modal");
        customerModal.setCloseButtonCallback(new ModalWindow.CloseButtonCallback() {

            @Override
            public boolean onCloseButtonClicked(AjaxRequestTarget ajaxRequestTarget) {
                ajaxRequestTarget.add(CustomerPage.this.form);
                return true;
            }
        });
        return customerModal;
    }
}
