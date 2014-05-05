package test;

import java.util.List;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import dao.DerbyDao;
import domainmodel.ExpenseData;
import domainmodel.Person;

public final class Main {
	DriverManagerDataSource dataSource = new DriverManagerDataSource();
	public static DriverManagerDataSource builtDatasource(DriverManagerDataSource dataSource){
		
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3399/expdb");
		dataSource.setUsername("abhijit");
		dataSource.setPassword("abhijit");
		return dataSource;
	}
	public static void main(String[] args) {
		DerbyDao dao = new DerbyDao();
		// Initialize the datasource, could /should be done of Spring
		// configuration
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		/*dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@172.20.5.15:1521:dwbisora");
		dataSource.setUsername("dwbisqajava");
		dataSource.setPassword("dwbisqajava");
		*/// Inject the datasource into the dao
		
		
		dao.setDataSource(builtDatasource(dataSource));

		//dao.create("Lars", "Vogel");
		//dao.create("Jim", "Knopf");
		//dao.create("Lars", "Man");
		//dao.create("Spider", "Man");
		System.out.println("Now select and list all persons");
		List<ExpenseData> list = dao.selectAll();
		for (ExpenseData exp : list) {
			System.out.print(exp.getCount() + " ");
			System.out.print(exp.getDate() + " ");
			System.out.print(exp.getItemList() + " ");
			System.out.print(exp.getPaidBy() + " ");
			System.out.print(exp.getPerHead() + " ");
			System.out.print(exp.getPrice() + " ");
			System.out.println(exp.getShareholder());
		}
		/*System.out.println("Now select and list all persons with have the firstname Lars and lastname Vogel");
		list = dao.select("Lars", "Vogel");
		for (Person myPerson : list) {
			System.out.print(myPerson.getFirstName() + " ");
			System.out.println(myPerson.getLastName());
		}

		// Clean-up
		dao.deleteAll();*/
	}
}

