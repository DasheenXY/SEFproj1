package market2;

public class SalesLineItem {
	private Product product;
	public int quantity;
	private Sale sale;
	
	public SalesLineItem(Product p, int quantity, Sale s){
		this.product = p;
		p.addSalesLineItem(this);
		this.quantity = quantity; 
		this.sale = s;
	}
	
	public Product getProduct(){
		return product;
	}
	public Sale getSale(){
		return sale;
	}
	
	public double getPrice(){
		double val = product.price(quantity);
		return val;
	}

	public void print(){
		System.out.println("\tProduct name:"+product.getName()+"\tQuantity :"+quantity);  
	}
}
