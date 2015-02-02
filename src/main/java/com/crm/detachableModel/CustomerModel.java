package com.crm.detachableModel;

import com.crm.model.Customer;
import com.crm.service.CustomerService;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 * Created by muhammad on 02.02.15.
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
