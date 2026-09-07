package ShoppingCart;

import java.util.ArrayList;
import java.util.Scanner;


public class ShoppingCart {
	static ArrayList<Product> table = new ArrayList<>();

	public static void main(String[] args) {
		
		ObjectHelper helper = new ObjectHelper();
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			try {
				System.out.println("\n| Shopping Cart System |\n");
				System.out.println("1. Add Cart ");
				System.out.println("2. Display All Item ");
				System.out.println("3. Update an Item ");
				System.out.println("4. Remove an Item ");
				System.out.println("5. Update offer for a product ");
				System.out.println("6. Exit");
				
				System.out.print("Enter the Operation : ");
				int work = scanner.nextInt();
				scanner.nextLine();
				
				switch(work) {
					case 1:
						System.out.print("Enter Product Name: ");
						String prodName = scanner.nextLine().toLowerCase();
						Product prod = helper.createObj(prodName);
						if(prod == null) {
							System.out.println("\nEnter Valid Product Name.");
							break;
						}
						System.out.print("Enter Product Price : ");
						double price = scanner.nextDouble();
						scanner.nextLine();
						prod.setPrice(price);
						table.add(prod);
						System.out.println("\nItem added successfully");
						break;
					case 2:
						System.out.println("+------------+--------------+----------------+");
						System.out.println("| Product ID | Product Name | Product Price  |");
						System.out.println("+------------+--------------+----------------+");
						for(Product p : table) {
							System.out.printf("| %-10d | %-12s | %-14f |\n",p.getId(),p.getName(),p.getPrice());
							System.out.println("+------------+--------------+----------------+");
						}
						break;
					case 3:
						System.out.print("Enter Name of the Product : ");
						String prdName = scanner.nextLine().toLowerCase();
						System.out.print("Enter Product Id : ");
						int prdId = scanner.nextInt();
						
						Product prd = helper.getObjByIdAndName(prdName,prdId);
						if(prd == null) {
							System.out.println("\nEnter Valid Input\n.");
							break;
						}
						System.out.print("Enter new Price for product : ");
						double prdPrice = scanner.nextDouble();
						prd.setPrice(prdPrice);
						System.out.println("\nUpdated Successfully");
						break;
					case 4:
						System.out.print("Enter Name of the Product : ");
						String prdName1 = scanner.nextLine().toLowerCase();
						System.out.print("Enter Product Id : ");
						int prdId1 = scanner.nextInt();
						
						if(!helper.checkObjByIdAndName(prdName1,prdId1)) {
							System.out.println("\nEnter Valid Input\n.");
							break;
						}
						helper.removeObjByIdAndName(prdName1, prdId1);
						System.out.println("\nItem Removed successfully.");
						break;
					case 5:
						System.out.print("Enter Name of the Product : ");
						String prdName2 = scanner.nextLine().toLowerCase();
						System.out.print("Enter Product Id : ");
						int prdId2 = scanner.nextInt();
						System.out.print("Enter 5% or 10% : ");
						int offer = scanner.nextInt();
						Product p = helper.getObjByIdAndName(prdName2,prdId2);
						Offers off = helper.getOffObj(offer,p);
						if(off == null) {
							System.out.println("\nEnter Valid Input\n.");
							break;
						}
						p.setPrice(off.getPrice());
						System.out.println("\nUpdated Successfully");
						break;
					case 6:
						scanner.close();
						System.out.println("\nExit Successfully");
						return;
					default:
						System.out.println("\nEnter Valid Operation");
						return;
				}
			}catch(Exception e) {
				e.printStackTrace();			
			}
		}
	}

}
