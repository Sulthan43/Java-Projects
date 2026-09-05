package customerDataHandling;

class Customer{
	
	private static int idGen = 1;
	
	private final int id;
	private String name;
	private int roomNo;
	
	Customer(String name ,int roomNo){
		this.id = idGen++;
		this.name = name;
		this.roomNo = roomNo;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public String toString() {
		return "ID : "+ id + " \nName : " + name + " \nRoom No : " + roomNo;
	}
}

