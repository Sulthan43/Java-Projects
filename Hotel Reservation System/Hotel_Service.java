package customerDataHandling;

import java.util.HashMap;
import java.util.Scanner;

public class HotelService {
	
	static HashMap<Integer,Customer> table = new HashMap<>();
	Scanner scn = new Scanner(System.in);
	
	void reserve() {
		try {
			System.out.print("Enter Customer Name : ");
			String name = scn.nextLine();
			
			System.out.print("Enter Customer Room Number : ");
			int room = scn.nextInt();
			scn.nextLine();
			
			Customer cust = new Customer(name,room);
			table.put(cust.getId(),cust); 
			
			System.out.print("\n<---------- Reserved Successfully ---------->\n");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	void showAllCustomerDatas() {
		try {
			System.out.println("+-------------+---------------+----------------------+");
			System.out.println("| Customer Id | Customer Name | Customer Room Number |");
			System.out.println("+-------------+---------------+----------------------+");
			for(Customer cust : table.values()) {
					System.out.printf("| %-12d| %-14s| %-20d |\n",cust.getId(),cust.getName(),cust.getRoomNo());
					System.out.println("+-------------+---------------+----------------------+");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	void getCustomerDetail() {
		try {
			System.out.print("Enter Room Id : ");
			int id = scn.nextInt();
			
			Customer cust = table.get(id);
			
			if(cust == null) {
				System.out.print("\n<---------- No Such a Data Exists ---------->\n");
				return;
			}
			
			System.out.println("+-------------+---------------+----------------------+");
			System.out.println("| Customer Id | Customer Name | Customer Room Number |");
			System.out.println("+-------------+---------------+----------------------+");
			System.out.printf("| %-12d| %-14s| %-20d |\n",cust.getId(),cust.getName(),cust.getRoomNo());
			System.out.println("+-------------+---------------+----------------------+");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	void updateCustomerDetail() {
		try {
			System.out.print("Enter Id of the Customer : ");
			int id = scn.nextInt();
			
			if(!table.containsKey(id)){
				System.out.println("\n<---------- Id Does not exist ---------->\n");
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
			Customer cust = table.get(id);
			
			if(work == 1) {
				cust.setName(newName);
				System.out.println("\n<---------- Updated Successfully ---------->\n");
				return;
			}else {
				cust.setRoomNo(newRoom);
				System.out.println("\n<---------- Updated Successfully ---------->\n");
				return;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	void deleteCustomerData() {
		try {
			
			System.out.print("Enter the Customer ID : ");
			int id = scn.nextInt();
			
			Customer cust = table.get(id);
			
			if(cust == null) {
				System.out.print("\n<---------- No Such a Data Exists ---------->\n");
				return;
			}
			table.remove(id);
			System.out.print("\n<---------- Deleted Successfully ---------->\n");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
