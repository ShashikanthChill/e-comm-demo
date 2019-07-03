package com.example.ecommercedemo.demo.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserCartPersistenceEntityModel.class)
public abstract class UserCartPersistenceEntityModel_ {

	public static volatile SingularAttribute<UserCartPersistenceEntityModel, Integer> quantity;
	public static volatile SingularAttribute<UserCartPersistenceEntityModel, Integer> productId;
	public static volatile SingularAttribute<UserCartPersistenceEntityModel, Integer> id;
	public static volatile SingularAttribute<UserCartPersistenceEntityModel, Integer> userId;

	public static final String QUANTITY = "quantity";
	public static final String PRODUCT_ID = "productId";
	public static final String ID = "id";
	public static final String USER_ID = "userId";

}

