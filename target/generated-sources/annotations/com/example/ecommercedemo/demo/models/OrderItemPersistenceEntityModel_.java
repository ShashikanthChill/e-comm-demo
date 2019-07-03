package com.example.ecommercedemo.demo.models;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderItemPersistenceEntityModel.class)
public abstract class OrderItemPersistenceEntityModel_ {

	public static volatile SingularAttribute<OrderItemPersistenceEntityModel, Integer> quantity;
	public static volatile SingularAttribute<OrderItemPersistenceEntityModel, Integer> productId;
	public static volatile SingularAttribute<OrderItemPersistenceEntityModel, BigDecimal> price;
	public static volatile SingularAttribute<OrderItemPersistenceEntityModel, Integer> id;
	public static volatile SingularAttribute<OrderItemPersistenceEntityModel, OrderPersistenceEntityModel> order;

	public static final String QUANTITY = "quantity";
	public static final String PRODUCT_ID = "productId";
	public static final String PRICE = "price";
	public static final String ID = "id";
	public static final String ORDER = "order";

}

