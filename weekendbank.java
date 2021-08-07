package Sql;
import java.sql.*;
import java.util.Scanner;
class con1
{
	String sql;
	void create(Connection c, Statement s) throws SQLException
	{
		sql = "create table customer_details"
				+ "(cus_id int auto_increment,"
				+ "cus_name varchar(50) not null,"
				+ "cus_age int not null,"
				+ "primary key(cus_id)"
				+ ");";
		s.executeUpdate(sql);
		System.out.println("table Created .....");
	}	
	
	
	
	void display(Connection c1, Statement s1) throws SQLException
	{
		sql = "Select * from customer_details";
		ResultSet rs=s1.executeQuery(sql);
		while(rs.next())
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3));
		c1.close();
		System.out.println("task completed ....");
	}
	void insert(Connection c, PreparedStatement ps,String cus_name,int cus_age) throws SQLException
	{
		ps.setString(1, cus_name);
		ps.setInt(2, cus_age);
		
		ps.executeUpdate();
		
	}
	
	
}



public class weekendbank extends con1 {

	public static void main(String[] args) throws SQLException {
		Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/fed","root","root");
		Statement s=c.createStatement();
		String sql1 = "insert into customer_details(cus_name,cus_age)" + " values (?, ?)";
		PreparedStatement ps=c.prepareStatement(sql1);
		
		
		weekendbank obj = new weekendbank();
		obj.create(c, s);
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter your name: ");
		String cus_name = scanner.nextLine();
		
		System.out.print("Enter your age ");
		int cus_age = scanner.nextInt();
		
		obj.insert(c, ps,cus_name,cus_age);
		obj.display(c, s);
		

		

	}

}
