package ShoppingCart;

import java.util.Iterator;

public class ObjectHelper {
	
	public Offers getOffObj(int offer , Product p) {
		if(offer == 5) {
			return new FivePercent(p);
		}else if(offer == 10) {
			return new TenPercent(p);
		}
		return null;
	}
	
	public boolean checkObjByIdAndName(String name , int id) {
		for(Product p : ShoppingCart.table) {
			if(p.getId() == id && p.getName().toLowerCase().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public void removeObjByIdAndName(String name , int id) {
		Iterator<Product> it = ShoppingCart.table.iterator();
		while(it.hasNext()) {
			Product p = it.next();
			if(p.getId() == id && p.getName().toLowerCase().equals(name)) {
				it.remove();
			}
		}
		/*for(Product p : ShoppingCart.table) {
			if(p.getId() == id && p.getName().toLowerCase().equals(name)) {
				ShoppingCart.table.remove(p);
			}
		}*/
	}
	
	public Product getObjByIdAndName(String name , int id) {
		
		for(Product p : ShoppingCart.table) {
			if(p.getId() == id && p.getName().toLowerCase().equals(name)) {
				return p;
			}
		}
		
		return null;
	}
	
	public Product createObj(String name) {
		
		if(name.equals("shirt")) {
			return new Shirt();
		}else if(name.equals("tshirt")) {
			return new Tshirt();
		}else if(name.equals("pant")) {
			return new Pant();
		}
		return null;
	}
}
