package ShoppingCart;

public class FivePercent extends Offers{

	FivePercent(Product prod) {
		super(prod);
	}

	@Override
	public double getPrice() {
		
		return prod.getPrice() - (5 * prod.getPrice() / 100);
	}

	@Override
	public void setPrice(double price) {
		super.prod.setPrice(price);
	}

	@Override
	public String getName() {
		return super.prod.getName();
	}
	@Override
	public int getId() {
		return super.prod.getId();
	}
}
