package ShoppingCart;

public abstract class Offers implements Product{
	Product prod;
	
	Offers(Product prod){
		this.prod = prod;
	}
}
