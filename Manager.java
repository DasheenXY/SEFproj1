package market2;
import java.util.*;

public class Manager extends Employee{
	Scanner n = new Scanner(System.in);
	Manager (String name, String ID, String password){
		super(name,ID,password);
	}
	
	public void editPrice(Product p){
		System.out.println("Please enter the new price for item "+p.getName());
		double price=n.nextDouble();
		p.setItemPrice(price);
	}
	
	public void editRate(Product p){
		System.out.println("Please enter the new bulk rate for item "+p.getName());
		double disc=n.nextDouble();
		p.setDisc(disc);
	}
	
	public void setReplenishLevel(Product p){
		System.out.println("Please enter the replenish level for item "+p.getName());
		int qty=n.nextInt();
	}
	
}
