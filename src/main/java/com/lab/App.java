package com.lab;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.lab.database.DAO;

/**
 * Hello world!
 *
 */
public class App 
{
	private DAO dao = new DAO();
	public void printTableNames() {
		try {
			System.out.println("Список таблиць бази даних: ");
			ArrayList<String> list =  (ArrayList<String>) dao.getLibraryTables();
			for (String string : list) {
				System.out.println(string);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void printTableStructure() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Введіть назву таблиці: ");
        String tableName = scanner.nextLine();
		System.out.println("Структура таблиці " + tableName + ":");
		ArrayList<String> list = null;
		try {
			list = (ArrayList<String>) dao.getTableStructure(tableName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (String string : list) {
			System.out.println(string);
		}
	}
	
	public void printNumOfBooks() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Введіть номер квитка: ");
        int reader = scanner.nextInt();
		System.out.println(dao.getNumberOfBooksTakenByReader(reader));
	}
	
	
	public void SetNewBook() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Введіть автора: ");
        String author = scanner.nextLine();

        System.out.println("Введіть назву: ");
        String title = scanner.nextLine();

        System.out.println("Введіть видавця: ");
        String publisher = scanner.nextLine();
        System.out.println("Введіть рік видання: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Введіть тип: ");
        String type = scanner.nextLine();
        System.out.println("Введіть кількість сторінок: ");
        int numOfPages = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Введіть тему: ");
        String theme = scanner.nextLine();
        System.out.println("Введіть ціну: ");
        int price = scanner.nextInt();
        scanner.nextLine();
		dao.setBook(author, title, publisher, year,  type, numOfPages, theme, price);
		
	}
	
	public void printBooksWithMorePages() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Введіть кількість сторінок: ");
        int numOfPages = scanner.nextInt();
        scanner.nextLine();
		ArrayList<String> list = null;
		list = (ArrayList<String>) dao.getBooksByPagesCount(numOfPages);
		for (String string : list) {
			System.out.println(string);
		}
	}
	
	public void printBooksPublishedAfterYear() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Введіть рік видання: ");
        int year = scanner.nextInt();
        scanner.nextLine();
		ArrayList<String> list = null;
		list = (ArrayList<String>) dao.findBookPublishedAfterYear(year);
		for (String string : list) {
			System.out.println(string);
		}
	}
	
	
	
	public void updateReaderPhoneNumber() {
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("Введіть номер квитка: ");
	    int reader = scanner.nextInt();
	    scanner.nextLine();

	    System.out.println("Введіть новий номер телефону: ");
	    String phone = scanner.nextLine();
	    dao.updateReaderPhoneNumber(reader, phone);
	}
	
	
	public void changeStatus() {
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("бібліотечний код: ");
	    int reader = scanner.nextInt();
	    scanner.nextLine();

	}
	
	
	
	
    public static void main( String[] args ) throws SQLException
    { 
    	App app = new App();
    	Scanner scanner = new Scanner(System.in);

    	
    	while (true) {
            System.out.println("Введіть команду: ");
            System.out.println("Показати список таблиць бази даних: 1");
            System.out.println("Показати структуру таблиці: 2");
            System.out.println("Показати кількість книг, взятих читачем за номером квитка: 3");
            System.out.println("Додати нову книгу до бібліотеки: 4");
            System.out.println("Показати список книг з більшою кількістю сторінок, ніж введено: 5");
            System.out.println("Показати список книг, опублікованих після введеного року: 6");
            System.out.println("Оновити номер телефону читача за номером квитка: 7");
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    app.printTableNames();
                    break;
                case "2":
                    app.printTableStructure();
                    break;
                case "3":
                    app.printNumOfBooks();
                    break;
                case "4":
                    app.SetNewBook();
                    break;
                case "5":
                    app.printBooksWithMorePages();
                    break;
                case "6":
                    app.printBooksPublishedAfterYear();
                    break;
                case "7":
                    app.updateReaderPhoneNumber();
                    break;
                case "8":
                    app.updateReaderPhoneNumber();
                    break;
                default:
                    System.out.println("Невідома команда, спробуйте ще раз");
                    break;
            }
        }
    }
}
