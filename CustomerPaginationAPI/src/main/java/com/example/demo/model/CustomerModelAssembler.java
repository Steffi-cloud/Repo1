package com.example.demo.model;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.demo.controller.CustomerController;
@Component
public class CustomerModelAssembler extends RepresentationModelAssemblerSupport<Customer, CustomerModel> {
    public CustomerModelAssembler() {
        super(CustomerController.class, CustomerModel.class);
    }

    @Override
    public CustomerModel toModel(Customer entity) {
        CustomerModel model = new CustomerModel();
        // Both CustomerModel and Customer have the same property names. So copy the values from the Entity to the Model
        BeanUtils.copyProperties(entity, model);
        return model;
    }
    
}
