package ShoppingCart;

public class Tshirt implements Product {
	
	static int idGen = 1;
	
	private String name;
	private double price;
	private final int id;
	
	Tshirt(){
		name = "TShirt";
		this.id = idGen++;
	}
	public String getName() {
		return name;
	}
	
	@Override
	public double getPrice() {
		return price;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getId() {
		return id;
	}
}
