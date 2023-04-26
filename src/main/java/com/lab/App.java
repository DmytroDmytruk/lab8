package com.lab;

import java.sql.SQLException;

import com.lab.database.DAO;

/**
 * Hello world!
 *
 */
public class App 
{
	
	
	
    public static void main( String[] args ) throws SQLException
    {
    	DAO dao = new DAO();
        System.out.println( "Hello World!" );
    }
}
