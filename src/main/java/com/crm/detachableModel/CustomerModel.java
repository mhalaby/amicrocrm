package com.crm.detachableModel;

import com.crm.model.Customer;
import com.crm.service.CustomerService;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 * Created by muhammad on 02.02.15.
 *
 * The class serves as a wrapper model for the customer
 * loadableDetachableModel holds a temporary, transient model object,
 * that is set when #getObject(Component) is called by calling abstract method 'load',
 * and that will be reset/ set to null on detach()
 */
public class CustomerModel  extends LoadableDetachableModel<Customer> {
    private static final long serialVersionUID = -6158545408575645211L;

    private CustomerService service;

    private Long id;
    public CustomerModel(CustomerService service, Customer customer) {
        super(customer);
        id = customer.getId();
        this.service = service;
        this.setObject(customer);
    }

    @Override
    protected Customer load() {
        if (id == null) {
            return new Customer();
        } else {
            return service.fetch(id);
        }
    }
}
