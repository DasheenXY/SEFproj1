//package market2;
//import java.util.*;
//
//public class Product {
//	private int ID;
//	private String name;
//	private int Wqty;
//	private int Sqty;
//	private ArrayList<SalesLineItem> salesLines = new ArrayList<SalesLineItem>();
//	private double unitPrice;
//	private double rate;
//	private double wholesaleItemPrice;
//	private int wholesaleItemQty;
//	
//	Product(int ID,String name,int wqty,double price){
//		this.ID=ID;
//		this.name=name;
//		this.Wqty=wqty;
//		this.unitPrice=price;
//		this.rate=0.9;
//		this.Sqty=100;
//	}
//	public int getWholeQty(){
//		return Wqty+Sqty;
//	}
//	
//	public void setWholeQty(int qty){
//		
//	}
//	
//	public int getID() {
//		return ID;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public int getWQty() {
//		return Wqty;
//	}
//
//	public void setWQty(int qty) {
//		this.Wqty = qty;
//	}
//
//	public int getSQty() {
//		return Sqty;
//	}
//
//	public void setSQty(int qty) {
//		this.Sqty = qty;
//	}
//	public double getUnitPrice() {
//		return unitPrice;
//	}
//
//	public void setUnitPrice(double unitPrice) {
//		this.unitPrice = unitPrice;
//	}
//	
//	public void setRate(double rate){
//		this.rate=rate;
//	}
//	
//	public double getRate(){
//		return rate;
//	}
//	
//	public int stock(){
//		int stock=Wqty-Sqty;
//		return stock;
//	}
//	
//	public void replenshLevel(){
//		if(stock()>100){
//			
//		}
//	}
//
//	public void addSalesLineItem(SalesLineItem saleLine) {
//		salesLines.add(saleLine);
//	}
//	
//	public double price(double amt) {
//		if (amt >= wholesaleItemQty)
//			return wholesaleItemPrice * amt * rate;
//		else
//			return unitPrice * amt * rate;
//	}
//}
package market2;
import java.util.*;

public class Product {
	private int ID;
	private String name;
	private int shelfQty;
	private int stockQty;
	private int replenishLevel=30;
	private double itemPrice;
	private double disc;
	public int totalOrder=0;
	private ArrayList<SalesLineItem> salesLines = new ArrayList<SalesLineItem>();
	private double wholesaleItemPrice;
	private int wholesaleItemQty;
	public double totalRevenue;

	public Product(int id,String name, double itemPrice, int quantity1, int quantity2, double wholesaleItemPrice,
			int wholesaleItemQty) {
		this.ID=id;
		this.name = name;
		this.itemPrice = itemPrice;
		this.shelfQty = quantity1;
		this.stockQty = quantity2;
		this.wholesaleItemPrice = wholesaleItemPrice;
		this.wholesaleItemQty = wholesaleItemQty;
		this.disc = 1;
	}

	Product(int id,String name,int qty,double price){
		this.ID=id;
		this.name=name;
		this.itemPrice=price;
		this.shelfQty=0;
		this.stockQty=qty;
		this.wholesaleItemPrice=itemPrice;
		this.wholesaleItemQty=shelfQty;
		this.disc = 1;
	}
	
	public int setReplenishLevel(int qty){
		this.replenishLevel=qty;
		return replenishLevel;
	}
	public void setStock(int amount1){
		{
			double amount=amount1;
			Scanner reader=new Scanner(System.in);
			do{     
				System.out.println("Not enough items on shelf, current total amount is "+ this.shelfQty);
				amount=reader.nextDouble();
//			else{	
//			System.out.println("There are not enough items left, try to buy all items");
//			shelfQty=0;
			}while(amount>shelfQty);
			this.shelfQty -= amount;
			if(this.shelfQty==0){
				this.shelfQty=100;
				this.stockQty-=100;
			}
			if(this.stockQty<=200){
				System.out.println("stock level low, reorder now, current stock amount is "+ this.stockQty);
				this.reorder(800);//automatically reorder if below 200 and replenish the stock to 1000
			}
		}
	}

	
	public void generateQty(){
		if(shelfQty<replenishLevel&&stockQty+shelfQty>=replenishLevel){
		this.shelfQty=this.replenishLevel;
		this.stockQty-=(this.replenishLevel-this.shelfQty);
		}
		else if(shelfQty<replenishLevel&&stockQty+shelfQty<replenishLevel){
			this.shelfQty+=stockQty;
			this.stockQty=0;
		}
	}
	
	public int getID(){
		return ID;
	}
	
	public double getDisc() {
		return disc;
	}

	public void setDisc(double disc) {
		this.disc = disc;
	}

	public int getShelfQty() {
		return shelfQty;
	}

	public void setShelfQty(int shelfQty) {
		this.shelfQty = shelfQty;
	}

	public int getStockQty() {
		return stockQty;
	}

	public void setStockQty(int stockQty) {
		this.stockQty = stockQty;
	}

	public double getWholesaleItemPrice() {
		return wholesaleItemPrice;
	}

	public void setWholesaleItemPrice(double wholesaleItemPrice) {
		this.wholesaleItemPrice = wholesaleItemPrice;
	}

	public int getWholesaleItemQty() {
		return wholesaleItemQty;
	}

	public void setWholesaleItemQty(int wholesaleItemQty) {
		this.wholesaleItemQty = wholesaleItemQty;
	}

	public String getName() {
		return name;
	}

	public void setQuantity(int quantity) {
		this.stockQty = quantity;
	}
	
	public double getItemPrice() {
		return itemPrice;
	}

	public double setItemPrice(double price) {
		return this.itemPrice = price;
	}

	public ArrayList<SalesLineItem> getSalesLines() {
		return salesLines;
	}

	public void addSalesLineItem(SalesLineItem saleLine) {
		salesLines.add(saleLine);
	}
	
	public void reorder(double newSupp){
		this.stockQty+=newSupp;
		this.totalOrder+=newSupp;
	}

	public double price(double amt) {
		if (amt >= wholesaleItemQty)
			return wholesaleItemPrice * amt * disc;
		else
			return itemPrice*amt*disc;
	}
	
	public void setStock(double amount1){
		{
			double amount=amount1;
			Scanner reader=new Scanner(System.in);
			do{     
				System.out.println("Not enough items on shelf, current total amount is "+ this.shelfQty);
				amount=reader.nextDouble();
//			else{	
//			System.out.println("There are not enough items left, try to buy all items");
//			shelfQty=0;
			}while(amount>shelfQty);
			this.shelfQty -= amount;
			if(this.shelfQty==0){
				this.shelfQty=100;
				this.stockQty-=100;
			}
			if(this.stockQty<=200){
				System.out.println("stock level low, reorder now, current stock amount is "+ this.stockQty);
				this.reorder(800);//automatically reorder if below 200 and replenish the stock to 1000
			}
		}
	}

	public void setLeftShelfQty(int amount){
		if(this.shelfQty>=amount)
		this.shelfQty-=amount;
	}

//	public void setLeftQuantity(Sale s){
//		double amount;
//		for (int i=0; i<s.list.size(); i++){
//			if(s.list.get(i).getProduct().getID() == ID)
//				{amount=s.list.get(i).quantity;
//				this.setStock(amount);
//				}
//		}
//	}
}
