package main_interface;

import java.sql.*;
import java.util.*;

public class HotelReservationSystem {
	
	private static final String url = "jdbc:mysql://localhost:3306/db_hotel_booking";
	
	private static final String username = "root";
	
	private static final String password = "mysql@6196";

	public static void main(String[] args) throws ClassNotFoundException , SQLException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e){
			System.out.println(e.getMessage());
		}
		
		try {
			Connection con = DriverManager.getConnection(url,username,password);
			while(true) {
				Scanner scanner = new Scanner(System.in);
				
				System.out.println("\n HOTEL MANAGEMENT SYSTEM \n");
				System.out.println("1. Reserve a Room");
				System.out.println("2. View Reservations");
				System.out.println("3. Get Room Number");
				System.out.println("4. Update Reservations");
				System.out.println("5. Delete Reservations");
				System.out.println("0. Exit");
				System.out.print("Choose an Option: ");
				
				int choice = scanner.nextInt();
				switch(choice) {
					case 1:
						reserveRoom(con,scanner);
						break;
					case 2:
						viewReservations(con);
						break;
					case 3:
						getRoomNumber(con,scanner);
						break;
					case 4:
						updateReservation(con,scanner);
						break;
					case 5:
						deleteReservation(con,scanner);
						break;
					case 0:
						exit();
						scanner.close();
						return;
					default:
						System.out.println("Invalid choice. Try again.");
				}
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}catch(InterruptedException e) {
			throw new RuntimeException(e);
		}
	
	}
	private static void reserveRoom(Connection con,Scanner scanner) {
		try {
			System.out.print("Enter Guest Name: ");
			String guestName = scanner.next();
			scanner.nextLine();
			System.out.print("Enter Room Number: ");
			int roomNumber = scanner.nextInt();
			System.out.print("Enter Contact Number: ");
			String contactNumber = scanner.next();
			
			String sql = "INSERT INTO reservations (guest_name,room_number,contact_number)"
					+ "VALUES ('" + guestName + "', " + roomNumber + ",'" + contactNumber + "')";
			
			try(Statement statement = con.createStatement()){
					int affectedRows = statement.executeUpdate(sql);
				
					if(affectedRows > 0) {
						System.out.println("Reservated Successful!");
					}
					else {
						System.out.println("Reservation failed.");
					}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	private static void viewReservations(Connection con) throws SQLException {
		String sql = "SELECT reservation_id,guest_name,room_number,contact_number,reservation_date from Reservations";
		
		try(Statement statement = con.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)){
			System.out.println("Current Reservation:");
			System.out.println("+----------------+----------------+-------------+-------------------+-------------------------+");
			System.out.println("| Reservation ID | Guest Name     | Room Number | Contact Number    | Reserved Date           |");
			System.out.println("+----------------+----------------+-------------+-------------------+-------------------------+");
			
			while(resultSet.next()) {
				int reservationId = resultSet.getInt("reservation_id");
				String guestName = resultSet.getString("guest_name");
				int roomNumber = resultSet.getInt("room_number");
				String contactNumber = resultSet.getString("contact_number");
				String reservationDate = resultSet.getTimestamp("reservation_date").toString();
				
				System.out.printf("| %-14d | %-14s | %-11d | %-17s | %-23s |\n",reservationId,guestName,roomNumber,contactNumber,reservationDate);
			}
			System.out.println("+----------------+----------------+-------------+-------------------+-------------------------+");
			
		}
	}
	private static void getRoomNumber(Connection con,Scanner scanner) {
		try {
			System.out.print("Enter Reservation ID: ");
			int reservationId = scanner.nextInt();
			System.out.print("Enter Guest Name: ");
			String guestName = scanner.next();
			
			String sql = "Select room_number FROM Reservations WHERE reservation_id = "+reservationId+
					" AND guest_name = '"+guestName+"'";
			
			try(Statement statement = con.createStatement();
					ResultSet resultSet = statement.executeQuery(sql)){
				if(resultSet.next()) {
					int roomNumber = resultSet.getInt("room_number");
					System.out.println("Room Number for Reservation ID "+ reservationId +
							" and Guest " + guestName + " is: " + roomNumber);
				}
				else {
					System.out.println("Reservation not found for the given ID and Guest Name.");
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	private static void updateReservation(Connection con,Scanner scanner) {
		try {
			System.out.print("Enter Reservation ID to Update: ");
			int reservationId = scanner.nextInt();
			scanner.nextLine();
			
			if(!reservationExists(con,reservationId)) {
				System.out.println("Reservation not Found for the given ID.");
				return;
			}
			
			System.out.print("Enter new Guest Name: ");
			String newGuestName = scanner.nextLine();
			System.out.print("Enter new Room Number: ");
			int newRoomNumber = scanner.nextInt();
			System.out.print("Enter new Contact Number: ");
			String newContactNumber = scanner.next();
			
			String sql = "UPDATE Reservations SET guest_name = '" + newGuestName +"', " +
						"room_number = " + newRoomNumber + ", " +
						"contact_number = '" + newContactNumber + "' " +
						"WHERE reservation_id = " + reservationId;
			
			try(Statement statement = con.createStatement()){
				int affectedRows = statement.executeUpdate(sql);
				
				if(affectedRows > 0) {
					System.out.println("Reservation Updated Successfully!");
				}else {
					System.out.println("Reservation Updated Failed.");
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	private static void deleteReservation(Connection con,Scanner scanner) {
		try {
			System.out.print("Enter reservation ID to delete: ");
			int reservationId = scanner.nextInt();
			
			if(!reservationExists(con,reservationId)) {
				System.out.println("Reservation not Found for the given ID.");
				return;
			}
			
			String sql = "DELETE FROM Reservations WHERE reservation_id = " + reservationId;
			
			try(Statement statement = con.createStatement()){
				int affectedRows = statement.executeUpdate(sql);
				
				if(affectedRows > 0) {
					System.out.println("Reservation Deleted Successfully!");
				}else {
					System.out.println("Reservation Deletion Failed.");
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	private static boolean reservationExists(Connection con,int reservationId) {
		try {
			String sql = "SELECT reservation_id FROM Reservations WHERE reservation_id = " + reservationId;
			try(Statement statement = con.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)){
				return resultSet.next();
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static void exit() throws InterruptedException{
		System.out.print("Exiting System");
		int G = 5;
		while(G!=0) {
			System.out.print(".");
			Thread.sleep(1000);
			G--;
		}
		System.out.println();
		System.out.println("Thanks For Using Hotel Reservation System !!!");
	}
}
