package ShoppingCart;

public class Pant implements Product{
	static int idGen = 1;
	
	private final int id;
	private String name;
	private double price;
	
	Pant(){
		this.id = idGen++;;
		name = "Pant";
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
