package com.example.ecommercedemo.demo.models;

import java.math.BigDecimal;
import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderPersistenceEntityModel.class)
public abstract class OrderPersistenceEntityModel_ {

	public static volatile SingularAttribute<OrderPersistenceEntityModel, String> shippingCity;
	public static volatile SingularAttribute<OrderPersistenceEntityModel, String> shippingAddress;
	public static volatile SingularAttribute<OrderPersistenceEntityModel, String> shippingState;
	public static volatile SingularAttribute<OrderPersistenceEntityModel, Integer> id;
	public static volatile SingularAttribute<OrderPersistenceEntityModel, Integer> userId;
	public static volatile ListAttribute<OrderPersistenceEntityModel, OrderItemPersistenceEntityModel> items;
	public static volatile SingularAttribute<OrderPersistenceEntityModel, Date> orderDate;
	public static volatile SingularAttribute<OrderPersistenceEntityModel, BigDecimal> orderTotal;
	public static volatile SingularAttribute<OrderPersistenceEntityModel, String> shippingPincode;

	public static final String SHIPPING_CITY = "shippingCity";
	public static final String SHIPPING_ADDRESS = "shippingAddress";
	public static final String SHIPPING_STATE = "shippingState";
	public static final String ID = "id";
	public static final String USER_ID = "userId";
	public static final String ITEMS = "items";
	public static final String ORDER_DATE = "orderDate";
	public static final String ORDER_TOTAL = "orderTotal";
	public static final String SHIPPING_PINCODE = "shippingPincode";

}

