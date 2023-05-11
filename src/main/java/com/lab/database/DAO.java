package com.lab.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.boot.MetadataSources;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

import com.lab.database.entity.BookFund;
import com.lab.database.entity.BookLendingJournal;
import com.lab.database.entity.Catalog;
import com.lab.database.entity.LendingPlans;
import com.lab.database.entity.ReaderArchive;
import com.mysql.cj.jdbc.DatabaseMetaData;


public class DAO {
	Session session;
	MetadataSources metadataSources;
	SessionFactory sessionFactory;
	Connection connection;
	DatabaseMetaData metadata;
	public DAO() {
   		try {
   			sessionFactory = new Configuration().configure().buildSessionFactory();	
   			session = HibernateUtil.getSessionFactory().getCurrentSession();
   			connection = HibernateUtil.getSessionFactory().
   					getSessionFactoryOptions().getServiceRegistry().
   					getService(ConnectionProvider.class).getConnection();
   			metadata = (DatabaseMetaData) connection.getMetaData();
   		} catch (Exception e) {
   			e.printStackTrace();
		}
   	}
   	
	public List<String> getLibraryTables() throws SQLException{
		ResultSet tables = null;
		try {
			tables = metadata.getTables("library", null, null, new String[]{"TABLE"});
		} catch (Exception e) {
			e.printStackTrace();
		}
		 List<String> result = new ArrayList<String>();
		    while (tables.next()) {
		    	String tableName = tables.getString("TABLE_NAME");
		    	result.add(tableName);
		    }
		return result;
	}

	
	
	public List<String> getTableStructure(String tableName) throws SQLException{
		ArrayList<String> result = new ArrayList<String>();
	    try {
	        ResultSet columns = metadata.getColumns(null, null, tableName, null);
	        
	        while (columns.next()) {
	            String columnName = columns.getString("COLUMN_NAME");
	            String columnType = columns.getString("TYPE_NAME");
	            result.add(columnName + " " + columnType);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}
	
	
	public Long getNumberOfBooksTakenByReader(int readerCardNumber) {
		session = sessionFactory.getCurrentSession();
		Long result = null;
		Transaction transaction = session.beginTransaction();
		try {
			result = session.createQuery("select count(*) from BookLendingJournal where"
					+ " readerArchive.readerCardNumber = :readerCardNumber", Long.class)
			.setParameter("readerCardNumber", readerCardNumber)
			.uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}
		return result;
	}
   	
	
	public List<String> getBooksByPagesCount(int pageCount) {
		session = sessionFactory.getCurrentSession();
	    Stream<?> stream;
		try{
	        Transaction transaction = session.beginTransaction();
	        Collection<Catalog> result = session.createQuery("FROM Catalog WHERE numberOfPages > :pageCount", Catalog.class)
	                .setParameter("pageCount", pageCount)
	                .getResultList();
	        transaction.commit();
	        
	        List<String> resStrings = result.stream()
	        		.map(catalog -> catalog.getAuthor() + " : " + catalog.getTitle())
	        		.collect(Collectors.toList());
	       
	        return resStrings;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	
	public List<String> findBookPublishedAfterYear(int year) {
		session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<String> resStrings = new ArrayList<String>();
		List<Catalog> resultList = null;
		try {
			String hql = "SELECT c FROM BookFund bf " +
	                 "JOIN bf.catalog c " +
	                 "WHERE c.year > :year AND bf.status = 'yes'";

		resultList = session.createQuery(hql, Catalog.class).setParameter("year", year).getResultList();
	    for (Catalog catalog : resultList) {
			resStrings.add(catalog.getAuthor() + " : " + catalog.getTitle());
		}
	    
	    transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}
	    return resStrings;
	}
	
	
	public void setBook(String author, String title, String publisher, int year, String type, int numOfPages, String Theme, int price) {
		session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			Catalog book = new Catalog(author, title, publisher, year, type, numOfPages, Theme, price);
			session.persist(book);
			BookFund fund = new BookFund(book, "yes");	
			session.persist(fund);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}	
	}
	
	public void setFund(Catalog book) {
		session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
	}
	
	public void updateReaderPhoneNumber(int readerCardNumber, String newPhoneNumber) {
		session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			ReaderArchive reader = session.find(ReaderArchive.class, readerCardNumber);
		    reader.setPhoneNumber(newPhoneNumber);
		    session.merge(reader);
		    transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}  
	}
	
	
	public void ChangeStatus(int inventory_number) {
		session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try {
			BookFund bookCopy = session.find(BookFund.class, inventory_number);	
			BookLendingJournal journal = session.find(BookLendingJournal.class, inventory_number);
			LendingPlans plans = session.find(LendingPlans.class, inventory_number);
			String status =  bookCopy.getStatus() == "yes" ? "no": "yes";
			bookCopy.setStatus(status);
			if(status.equals("yes")) {
				session.remove(journal);
				session.remove(plans);
			}
		    session.merge(bookCopy);
		    transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}	    
	}
}
