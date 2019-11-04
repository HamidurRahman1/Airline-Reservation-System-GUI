package com.hrs.test;

import com.hrs.view.models.Customer;

public class Tester
{
    public static void main(String[] args)
    {
        System.out.println(testCustomer());
    }
    
    public static Customer testCustomer()
    {
        return new Customer("Hamidur", "Rahman");
    }
}
