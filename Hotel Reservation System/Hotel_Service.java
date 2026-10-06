package customerDataHandling;

import java.util.Scanner;
import java.sql.*;

public class HotelService {
	
	Scanner scn = new Scanner(System.in);
	
	void reserve(Connection con) {
		try {
			System.out.print("Enter Customer Name : ");
			String name = scn.nextLine();
			
			System.out.print("Enter Customer Room Number : ");
			int room = scn.nextInt();
			scn.nextLine();
			
			if(room <= 0 && room >= 16) {
				System.out.println("\\n<---------- Enter Valid Room Number ---------->\\n");
				return;
			}
			String query = "insert into customer_data(customer_name,room_No) values(?,?)";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(2, room);
			pst.setString(1, name);
			pst.executeUpdate();
			
			System.out.print("\n<---------- Reserved Successfully ---------->\n");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	void showAllCustomerDatas(Connection con) {
		try {
			Statement statement = con.createStatement();
			ResultSet set = statement.executeQuery("select * from customer_data");
			System.out.println("+-------------+---------------+----------------------+");
			System.out.println("| Customer Id | Customer Name | Customer Room Number |");
			System.out.println("+-------------+---------------+----------------------+");
			while(set.next()) {
					System.out.printf("| %-12d| %-14s| %-20d |\n",set.getInt(1),set.getString(2),set.getInt(3));
					System.out.println("+-------------+---------------+----------------------+");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	void getCustomerDetail(Connection con) {
		try {
			PreparedStatement st = con.prepareStatement("select * from customer_data where id = ?");
			System.out.print("Enter Room Id : ");
			int id = scn.nextInt();
			
			st.setInt(1,id);
			
			ResultSet result = st.executeQuery();
			
			if(result.next()) {
				System.out.println("+-------------+---------------+----------------------+");
				System.out.println("| Customer Id | Customer Name | Customer Room Number |");
				System.out.println("+-------------+---------------+----------------------+");
				System.out.printf("| %-12d| %-14s| %-20d |\n",result.getInt(1),result.getString(2),result.getInt(3));
				System.out.println("+-------------+---------------+----------------------+");
			}else {
				System.out.print("\n<---------- Invalid ID  ---------->\n");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	void updateCustomerDetail(Connection con) {
		try {
			System.out.print("Enter Id of the Customer : ");
			int id = scn.nextInt();
			
			PreparedStatement st = con.prepareStatement("select * from customer_data where id = ?");
			st.setInt(1, id);
			ResultSet set = st.executeQuery();
			
			if(!set.next()) {
				System.out.print("\n<---------- Invalid ID  ---------->\n");
				return;
			}
			
			System.out.print("Type 1 for Name Change | Type 2 for Room Change : ");
			int work = scn.nextInt();
			scn.nextLine();
			
			String newName = "";
			int newRoom = 0;
			
			if(work == 1) {
				System.out.print("Enter New Name : ");
				newName = scn.nextLine();
			}else if(work == 2) {
				System.out.print("Enter New Room Number : ");
				newRoom = scn.nextInt();
			}else {
				System.out.println("\n<---------- Enter Valid Input ---------->\n");
				return;
			}
			
			if(work == 1) {
				st = con.prepareStatement("update customer_data set customer_name = ? where id = ?");
				st.setString(1,newName);
				st.setInt(2, id);
				st.executeUpdate();
				System.out.println("\n<---------- Updated Successfully ---------->\n");
			}else {
				st = con.prepareStatement("update customer_data set room_No = ? where id = ?");
				st.setInt(1,newRoom);
				st.setInt(2, id);
				st.executeUpdate();
				System.out.println("\n<---------- Updated Successfully ---------->\n");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	void deleteCustomerData(Connection con) {
		try {
			System.out.print("Enter the Customer ID : ");
			int id = scn.nextInt();
			
			String query = "delete from customer_data where id = " + id;
			Statement st = con.createStatement();
			int row = st.executeUpdate(query);
			if(row <= 0) {
				System.out.println("\n<---------- Enter Valid Input ---------->\n");
				return;
			}
			
			System.out.print("\n<---------- Deleted Successfully ---------->\n");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
