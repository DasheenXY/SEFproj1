package market2;
import java.util.*;

public class Market {
	Scanner scan=new Scanner(System.in);
	private ArrayList<Customer> custs=new ArrayList<Customer>();
	private ArrayList<Employee> emps=new ArrayList<Employee>();
	private ArrayList<Product> prods=new ArrayList<Product>();
	private ArrayList<Sale> sales=new ArrayList<Sale>();
	private Employee employee=null;
	private Product product=null;
	private Customer customer=null;
	private Sale sale=null;
	private double max=0;
	
	Market(){
		custs.add(new Customer("Ashley",1,100));
		custs.add(new Customer("Oliver",2,0));
		emps.add(new Manager("Shaun","1","pass"));
		emps.add(new WarehouseStaff("Ray","007","pass"));
		emps.add(new SalesStaff("Bob","3","pass"));
		prods.add(new Product(1,"Apple",10000,2.0));
		prods.add(new Product(2,"Banana",10000,1.5));
		prods.add(new Product(3,"Coke",20000,1.8));
		for(int i=0;i<prods.size();i++){
			prods.get(i).setShelfQty(2000);
		}
	}
	
	public ArrayList<Customer> getCusts() {
		return custs;
	}

	public void setCusts(ArrayList<Customer> custs) {
		this.custs = custs;
	}

	public ArrayList<Employee> getEmps() {
		return emps;
	}

	public void setEmps(ArrayList<Employee> emps) {
		this.emps = emps;
	}

	public ArrayList<Product> getProds() {
		return prods;
	}

	public void setProds(ArrayList<Product> prods) {
		this.prods = prods;
	}

	public void menu(){
		System.out.println("\t===Mini Market Self-Checkout System===");
		System.out.println("\t1.Show All Products");
		System.out.println("\t2.Search Product");
		System.out.println("\t3.Search Unit Price In Bulk");
		System.out.println("\t4.Purchase Products(Shopping)");
		System.out.println("\t5.Show Shopping List");
		System.out.println("\t6.Customer Login");
		System.out.println("\t7.Staff Options");
		System.out.println("\t8.Confirm shopping");
		System.out.println("\t9.Show Your Current Points and Balance");
		System.out.println("\t0.Exit");
	}
	
	public void staffMenu(){
		System.out.println("\t===Staff Operation System===");
		System.out.println("\t1.Show Products' Quantity On Shelve");
		System.out.println("\t2.Show Products' Quantity In Warehouse");
		System.out.println("\t3.Remove Buying Items");
		System.out.println("\t4.Cancellation");
		System.out.println("\t5.Add Shelf Level");
		System.out.println("\t6.Set Replenish Stoke Level On Shelve");
		System.out.println("\t7.Print Current Sale Report");
		System.out.println("\t8.Print Most Revenue Sale");
		System.out.println("\t9.Edit Product Details");
		System.out.println("\t0.Back To Last Menu");
	}
	
	public void custInfo(){
		if(customer!=null){
			System.out.println("Your current points: "+customer.getValidPoint());
			System.out.println("Your current balance: "+customer.getBalance());
		}
		else System.out.println("Please login as customer first!");
	}
	
	public void recharge(){
			System.out.print("Please enter the amount you want to charge: ");
			double amt=scan.nextDouble();
			customer.setBalance(customer.getBalance()+amt);
	}
	
	public void showProduct(){
		System.out.println("\tProduct ID\tProduct Name\tUnit Price\tQuantity Left");
		for(int i=0;i<prods.size();i++){
			System.out.println("\t"+prods.get(i).getID()+"\t\t"+prods.get(i).getName()+"\t\t"+prods.get(i).getItemPrice()+"\t\t"+prods.get(i).getShelfQty());
		}
	}
	
	public void checkProduct(){
		System.out.println("\tProduct ID\tProduct Name\tUnit Price\tQuantity Left");
		for(int i=0;i<prods.size();i++){
			System.out.println("\t"+prods.get(i).getID()+"\t\t"+prods.get(i).getName()+"\t\t"+prods.get(i).getItemPrice()+"\t\t"+prods.get(i).getStockQty());
		}
	}
	
	public void searchProduct(){
		if(customer!=null){
			System.out.print("Which type do you want to search by?\n");
			System.out.print("1.Product ID\n");
			System.out.print("2.Product Name\n");
			System.out.print("Please enter your choice: ");
			int n=scan.nextInt();
			switch(n){
			case 1 : 
				System.out.print("Enter the product ID: ");
				int i=scan.nextInt();
				for(int j=0;j<prods.size();j++){
					if(prods.get(j).getID()==i){
						System.out.println("\tProduct ID\tProduct Name\tUnit Price\tQuantity Left");
						System.out.println("\t"+prods.get(i-1).getID()+"\t\t"+prods.get(i-1).getName()+"\t\t"+prods.get(i-1).getItemPrice()+"\t\t"+prods.get(i-1).getShelfQty());
					}
				}
				break;
			case 2 :
				System.out.print("Enter the product Name: ");
				String nm=scan.next();
				for(int j=0;j<prods.size();j++){
					if(prods.get(j).getName().compareTo(nm)==0){
						System.out.println("\tProduct ID\tProduct Name\tUnit Price\tQuantity Left");
						System.out.println("\t"+prods.get(j).getID()+"\t\t"+prods.get(j).getName()+"\t\t"+prods.get(j).getItemPrice()+"\t\t"+prods.get(j).getShelfQty());
					}
				}
				break;
				
			}
			
		}
		else System.out.println("Please Login in first dear customer.");
	}
	
	public void searchDiscount(){
		if(customer!=null){
			System.out.print("Enter the product ID: ");
			int i=scan.nextInt();
			for(int j=0;j<prods.size();j++){
				if(prods.get(j).getID()==i){
					System.out.print("If your purchasing quantities greater than 10 : ");
					System.out.print("The product "+prods.get(i-1).getName()+" will have a "+(float)(1-prods.get(i-1).getDisc())*100+"% discount.");
				}else if(i>prods.size()){
					System.out.println("Product ID "+(i-1)+" doesn't exsit. Please enter again.");
				}
			}
		}
		else System.out.println("Please Login in first dear customer.");
	}
	
	public void purchase(){
		if(customer!=null){
			sales.add(new Sale(customer,prods));
			for(int i=0;i<sales.size();i++)
				this.sale=sales.get(sales.size()-1);
		}
	}
	
	public void showShoppingList(){
		if(customer!=null){
			System.out.println("Customer "+customer.getName()+"'s list: ");
			for(int i=0;i<sales.size();i++){
				for(int j=0;j<sales.get(i).list.size();j++){
					sales.get(i).setID(i+1);
					System.out.print("Sale "+sales.get(i).getID());
					sales.get(i).list.get(j).print();
				}
			}
		}
	}
	
	public void addShelfLevel(){
		if(employee instanceof Manager || employee instanceof SalesStaff){
			System.out.print("Please enter the product id : ");
			int n=scan.nextInt();
			if(n<=prods.size()){
				System.out.print("Please enter the qty you want to add into shelf :");
				int m=scan.nextInt();
				if(m<=prods.get(n-1).getStockQty()){
					prods.get(n-1).setShelfQty(prods.get(n-1).getShelfQty()+m);
					prods.get(n-1).setStockQty(prods.get(n-1).getStockQty()-m);
				}
			}
		}
	}
	
	public void remove(){
		if(employee instanceof SalesStaff){
			System.out.print("Please enter the target sale ID: ");
			int i=scan.nextInt();
			System.out.print("Please enter the product name you want to remove from this shopping: ");
			String name=scan.next();
			if(i<sales.size()+1&&i>0)
			sales.get(i-1).remove(name);
			else{
				System.out.println("Invalid sale or name");
				remove();
			}
		}
	}
	
	public void confirm(){
		if(customer!=null&&sale!=null){
			System.out.println("Would you confirm to pay your order? Y/N :");
			String ch=scan.next();
			if(ch.compareTo("Y")==0||ch.compareTo("y")==0){
				this.sale.realTotalPrice(customer);
				if(customer.getBalance()>this.sale.realTP){
					System.out.println("Total price: "+sale.getTotalPrice());
					customer.setBalance(customer.getBalance()-this.sale.realTP);
				}
				else{
					System.out.println("Your balance is not enough, please recharge at first.");
				}
			}else{
				sales.remove(sales.get(sales.size()-1));
				this.sale=null;
			}
		}
	}
	
	public void cancel(){
		System.out.print("DO YOU WANT TO CANCEL YOUR ORDER? Y/N : ");
		String ch=scan.next();
			if(ch.compareTo("Y")==0||ch.compareTo("y")==0){
				for(int i=0;i<sale.list.size();i++){
					sale.list.get(i).getProduct().setShelfQty(sale.list.get(i).getProduct().getShelfQty()+sale.list.get(i).quantity);
				}
//				for(int i=0;i<sales.get(sales.size()-1).list.size();i++)
//					sales.get(sales.size()-1).list.get(i).getProduct().setShelfQty(sales.get(sales.size()-1).list.get(i).getProduct().getShelfQty()+sales.get(sales.size()-1).list.get(i).quantity);
				sales.remove(sales.size()-1);
				this.sale=null;
			}
		}
	
	public void addProduct(){
		boolean test;
		System.out.print("Please enter new item ID: ");
		int ID=scan.nextInt();
		System.out.print("Please enter new item name: ");
		String name=scan.next();
		System.out.print("Please enter new item quantity: ");
		int qty=scan.nextInt();
		System.out.print("Please enter new item unit price: ");
		double unitPrice=scan.nextDouble();
		do{
			for(int i=0;i<prods.size();i++){
				if(ID==prods.get(i).getID()){
					System.out.print("Product ID exsits, Please enter again: ");
					ID=scan.nextInt();
					test=false;
				}
			}
		}while(test=false);
		prods.add(new Product(ID,name,qty,unitPrice));
	}
	
	public void edit(){
		if(employee instanceof Manager){
			System.out.println("Please choose what you want to edit");
			System.out.println("1.Edit Product Item Price");
			System.out.println("2.Edit Product Discount Rate");
			System.out.println("3.Set Product Whole Sale Quantity");
			System.out.println("4.Set Product Whole Sale Price");
			System.out.print("Enter your choice");
			int a=scan.nextInt();
			switch (a){
			case 1 : 
				System.out.println("Please enter product name: ");
				String name=scan.next();
				for(int i=0;i<prods.size();i++){
					if(prods.get(i).getName().compareTo(name)==0){
						this.product=prods.get(i);
					}
				}
				((Manager) employee).editPrice(product);
				break;
			case 2 :
				System.out.println("Please enter product name: ");
				name=scan.next();
				for(int i=0;i<prods.size();i++){
					if(prods.get(i).getName().compareTo(name)==0){
						this.product=prods.get(i);
					}
				}
				((Manager) employee).editRate(product);
				break;
			case 3 :
				System.out.print("Please enter product name: ");
				name=scan.next();
				for(int i=0;i<prods.size();i++){
					if(prods.get(i).getName().compareTo(name)==0){
						this.product=prods.get(i);
					}
				}
				setWholeSaleQty();
				break;
			case 4 :
				System.out.print("Please enter product name: ");
				name=scan.next();
				for(int i=0;i<prods.size();i++){
					if(prods.get(i).getName().compareTo(name)==0){
						this.product=prods.get(i);
					}
				}
				setWholeSalePrice();
				break;
			}
		}
		else System.out.println("Not Login Yet Or Invalid User Level.");
	}
	
	public void setWholeSaleQty(){
		System.out.print("Please enter the whole sale quantity: ");
		int n=scan.nextInt();
		product.setWholesaleItemQty(n);
	}
	
	public void setWholeSalePrice(){
		System.out.print("Please enter the whole sale price: ");
		double n=scan.nextDouble();
		product.setWholesaleItemPrice(n);
	}
	
	public void findMostRevenue(){
		for(int i=0;i<prods.size();i++){
			if(prods.get(i).totalRevenue>=max)
			max=prods.get(i).totalRevenue;
		}
		for(int i=0;i<prods.size();i++){
			if(prods.get(i).totalRevenue==max){
					System.out.println("The product that generated most revenue is "+ prods.get(i).getName()+" and the total revenue is " +max);
			}
		}
	}
	
	public void salesReport(double startdate, double enddate){
		double partialRevenue=0;
		double salesnum=0;
		if ( sales.size() == 0)
	         System.out.println("No current orders");
	    else {
		for(int i=0;i<sales.size();i++){
		if((Math.floor(sales.get(i).ID/1000)>=startdate)&&(Math.floor(sales.get(i).ID)<=enddate))
		{
			System.out.println("Sale " + (i+1));
	          sales.get(i).print();
	         partialRevenue+=sales.get(i).realTP;
			salesnum+=1;
			 System.out.println("\nPress enter to continue");
	          scan.nextLine();
		}
		System.out.println("total number of sale during this period is "+salesnum);
		System.out.println("the total revenue during this period is "+partialRevenue);
		}
		}
	}
	public void supplyReport(){
		if ( prods.size() == 0)
	         System.out.println("No current products");
	    else {
	    	for(int i=0;i<prods.size();i++){
	    		System.out.println(prods.get(i).getName()+"   "+prods.get(i).totalOrder);
	    	}
	    }
	}
	
	public void custLogin(){
		int i;
		System.out.print("Please enter your name: ");
		String name=scan.next();
		System.out.print("Please enter your customer ID: ");
		int ID=scan.nextInt();
		for(i=0;i<custs.size();i++){
			if(custs.get(i).getName().compareTo(name)==0&&custs.get(i).getID()==ID){
				System.out.println("Welcome "+custs.get(i).getName()+"! Enjoy your shopping!");
				this.customer=custs.get(i);
				break;
			}
		}
		if(i==custs.size()){
			System.out.println("Username or ID Wrong!");
		}
		System.out.println("Please enter to continue.");
		scan.nextLine();
	}
	
	public void login(){
		int i;
		System.out.print("Please enter user name: ");
		String name=scan.next();
		System.out.print("Please enter user password: ");
		String password=scan.next();
		for(i=0;i<emps.size();i++){
			if(emps.get(i).getName().compareTo(name)==0&&emps.get(i).getPassword().compareTo(password)==0){
				System.out.println("Welcome "+emps.get(i).getName()+"! Current level is: "+emps.get(i).getClass());
				this.employee=emps.get(i);
				break;
			}
		}
		if(i==emps.size()){
			System.out.println("Username or Password Wrong!");
		}
		System.out.println("Please enter to continue.");
		scan.nextLine();
	}
	
	public void start(){
		int m,n;
		do{
			System.out.println();
			menu();
			System.out.print("Please enter your choice : ");
			m=scan.nextInt();
			switch(m){
			case 1 : showProduct();break;
			case 2 : searchProduct();break;
			case 3 : searchDiscount();break;
			case 4 : purchase();break;
			case 5 : showShoppingList();break;
			case 6 :
				custLogin();
				if(customer!=null){
					System.out.println("Your current balance is: $"+customer.getBalance());
					System.out.print("Do you want to charge your balance? Y/N :");
					String ch=scan.next();
					if(ch.compareTo("y")==0||ch.compareTo("Y")==0){
						recharge();
					}
				}
				break;
			case 7 : 
				do{
					System.out.println();
					if(employee==null){
						System.out.println("Please login as a staff!");
						login();
					}
					if(employee instanceof Manager || employee instanceof WarehouseStaff || employee instanceof SalesStaff)
						staffMenu();
					System.out.print("Please enter your choice : ");
					n=scan.nextInt();
					switch(n){
					case 1 : showProduct();break;
					case 2 : checkProduct();break;
					case 3 : remove();break;
					case 4 : cancel();break;
					case 5 : addShelfLevel();break;
					case 7 : 
						System.out.print("Enter startdate and enddate(DDMMYYYY DDMMYYYY):");
						double sd=scan.nextDouble();
						double ed=scan.nextDouble();
						salesReport(sd,ed);
						break;
					case 8 : findMostRevenue();break;
					case 9 : edit();break;
					case 0 : break;
					}
					System.out.println();
				}while(n!=0);
			case 8 : confirm();break;
			case 9 : custInfo();break;
				case 0 : break;
				}
			System.out.println();
		}while(m!=0);
	}
	
	public static void main(String[] args){
		Market s = new Market();
		s.start();
	}
}
