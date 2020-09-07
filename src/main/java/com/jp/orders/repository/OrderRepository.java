package com.jp.orders.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jp.orders.entity.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, String> {

    List<Order> findByEmail(String email);

    List<Order> findByEmailAndBillingAddress_Zipcode(String email, String zipcode);

}
