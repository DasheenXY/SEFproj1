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
	
	Market(){
		custs.add(new Customer("Ashley",1,100));
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
		System.out.println("\t5.Customer Login");
		System.out.println("\t6.Staff Options");
		System.out.println("\t0.Exit");
	}
	
	public void staffMenu(){
		System.out.println("\t===Staff Operation System===");
		System.out.println("\t1.Show Products' Number On Shelve");
		System.out.println("\t2.Show Products' Number In Warehouse");
		System.out.println("\t3.Remove Items");
		System.out.println("\t4.Cancellation");
		System.out.println("\t5.Add Shelf Level");
		System.out.println("\t6.Set Replenish Stoke Level On Shelve");
		System.out.println("\t7.Print Current Sale Report");
		System.out.println("\t0.Back To Last Menu");
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
			System.out.print("Enter the product ID: ");
			int i=scan.nextInt();
			for(int j=0;j<prods.size();j++){
				if(prods.get(j).getID()==i){
					System.out.println("\tProduct ID\tProduct Name\tUnit Price\tQuantity Left");
					System.out.println("\t"+prods.get(i-1).getID()+"\t\t"+prods.get(i-1).getName()+"\t\t"+prods.get(i-1).getItemPrice()+"\t\t"+prods.get(i-1).getShelfQty());
				}
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
			System.out.print("Enter the product ID you want to purchse : ");
//			int i=scan.nextInt();
//			for(int j=0;j<prods.size();j++){
//				if(i<=prods.size()&&i!=0){
//					this.product=prods.get(i-1);
//				}else System.out.println("Invalid Product ID, Please Enter Again.");
//			}
//			if(product!=null){
				this.sale=new Sale(customer,prods);
//				this.sale.realTotalPrice(customer);
//		           pList.get(i-1).setLeftQuantity(this.sale);
//				System.out.println("Enter the quantity you want to purchase: ");
//				int q=scan.nextInt();
//				if(q<=product.getShelfQty()){
//				}
//			}
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
	
	public void confirm(){
		if(customer!=null&&sale!=null){
			
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
			System.out.println("1.Edit Product Price");
			System.out.println("2.Edit Product Discount Rate");
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
			}
		}
		else System.out.println("Not Login Yet Or Invalid User Level.");
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
			}
			else System.out.println("Customer name or ID wrong!");
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
			}
			else System.out.println("User name or password wrong!");
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
			case 5 : custLogin();break;
			case 6 : 
				do{
					if(employee==null){
						System.out.println("Please login as a staff!");
						login();
					}
					if(employee instanceof Manager || employee instanceof WarehouseStaff || employee instanceof SalesStaff)
						staffMenu();
					n=scan.nextInt();
					switch(n){
					case 1 : showProduct();break;
					case 2 : checkProduct();break;
					case 5 : addShelfLevel();break;
					case 0 : break;
					}
				}while(n!=0);
				case 0 : break;
				}
		}while(m!=0);
	}
	
	public static void main(String[] args){
		Market s = new Market();
		s.start();
	}
}
