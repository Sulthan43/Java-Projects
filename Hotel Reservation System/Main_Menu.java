package customerDataHandling;

import java.util.*;

public class MainMenu {

	public static void main(String[] args) {
		HotelService service = new HotelService();
		Scanner scn = new Scanner(System.in);
		
		boolean run = true;
		
		while(run) {
			try {
				System.out.println("\n| Hotel Management System |\n");
				System.out.println("1. Reserve a room.");
				System.out.println("2. Show All Customer Details.");
				System.out.println("3. Get a Customer Detail.");
				System.out.println("4. Update a Customer Detail.");
				System.out.println("5. Delete a Customer Details.");
				System.out.println("0. Exit.");
					
				System.out.print("\nEnter the Operation Here: ");
				int ans = scn.nextInt();
				scn.nextLine();
					
				switch(ans) {
					case 1:
						service.reserve();
						break;
					case 2:
						service.showAllCustomerDatas();
						break;
					case 3 :
						service.getCustomerDetail();
						break;
					case 4:
						service.updateCustomerDetail();
						break;
					case 5:
						service.deleteCustomerData();
						break;
					case 0:
						run = false;
						scn.close();
						break;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		System.out.print("\n<---------- Exit Successfully ---------->\n");
	}
}
