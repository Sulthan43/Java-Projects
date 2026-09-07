package ShoppingCart;

public class Shirt implements Product{
	
	static int idGen = 1;
	
	private final int id;
	private String name;
	private double price;
	
	Shirt(){
		this.id = idGen++;
		name = "Shirt";
	}
	public String getName() {
		return name;
	}
	@Override
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
}
