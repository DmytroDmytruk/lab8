package com.lab.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.query.Query;

import com.lab.database.entity.BookFund;
import com.lab.database.entity.BookLendingJournal;
import com.lab.database.entity.Catalog;
import com.lab.database.entity.LendingPlans;
import com.lab.database.entity.ReaderArchive;
import com.mysql.cj.jdbc.DatabaseMetaData;

import jakarta.persistence.metamodel.Metamodel;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.cfg.Configuration;

public class DAO {
	Session session;
	MetadataSources metadataSources;
	Metadata metadata;
	Connection connection;
	public DAO() {
   		try {
   			connection = HibernateUtil.getSessionFactory().
   					getSessionFactoryOptions().getServiceRegistry().
   					getService(ConnectionProvider.class).getConnection();
   			DatabaseMetaData metadata = (DatabaseMetaData) connection.getMetaData();
            System.out.println("Database name: " + metadata.getDatabaseProductName());
            System.out.println("Database version: " + metadata.getDatabaseProductVersion());
            String[] types = {"TABLE"};
            ResultSet tables = metadata.getTables(null, null, "%", types);
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println("Table name: " + tableName);
            }	
   		} catch (Exception e) {
   			e.printStackTrace();
		}
   	}
   	
   	
   	
   	protected void finalize() throws Throwable {
   		
   	}
}
