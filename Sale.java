package market2;
import java.util.*;
class Sale 
{
   protected int ID;
   private double totalPrice=0; 
   public double realTP;
   protected Customer c;
   public ArrayList<SalesLineItem> list = new ArrayList<SalesLineItem>();
   private Scanner scan = new Scanner(System.in);

   public int getID(){ 
	   return ID; 
   }
   
   public void setID(int id){
	   this.ID=id;
   }

   public double getRealTP(){
	   return realTP;
   }

   public ArrayList<SalesLineItem> getArrayList(){
	   return list;
   }

   public void print()
   {
//      computeTotalPrice();
      System.out.printf("%-20s %-20s\n","ID",ID);
      System.out.printf("%-20s %-20s\n","Customer",c.getName());
      for (int i=0; i<list.size(); i++)        
    	  list.get(i).print();
      System.out.printf("\n%-20s %-20s\n","total price",realTP);
   }
   
   public Sale(int ID,Customer c,Product ps[],int qtys[]) //input are arrays, create array first before  creating sale 
   {
       this.ID = ID;
       this.c = c; 
       for (int i=0;i<ps.length;i++)
       {
          list.add(new SalesLineItem(ps[i],qtys[i],this));
       }
   }

   public Sale(Customer c, ArrayList<Product> pList)
   {    
	    this.c=c;
        int i;  
        String response;       
        do{
        	System.out.println("\tProduct ID\tProduct Name\tUnit Price\tQuantity Left");
    		for(i=0;i<pList.size();i++){
    			System.out.println("\t"+pList.get(i).getID()+"\t\t"+pList.get(i).getName()+"\t\t"+pList.get(i).getItemPrice()+"\t\t"+pList.get(i).getShelfQty());
    		}
        	do{      
        		System.out.print("Enter Product ID : ");
        		i=scan.nextInt();
           }while(i<1||i>pList.size());
           System.out.print("Enter qty : ");
           int qty=scan.nextInt();
           if(qty<=pList.get(i-1).getShelfQty()){
           list.add(new SalesLineItem(pList.get(i-1),qty,this));
           } else{
        	   	  System.out.println("Not enough quantitie left, try to buy everthing.");
        	   	  qty=pList.get(i-1).getShelfQty();
        	   	  list.add(new SalesLineItem(pList.get(i-1),qty,this));
           		 }
           pList.get(i-1).setLeftShelfQty(qty);
           scan.nextLine();
           System.out.print("Any more items ? : Y/N");
           response = scan.nextLine();
        } while(response.compareTo("Y")==0||response.compareTo("y")==0);       
   }//adding new sales, each salesLineItem includes same sale

   public void computeTotalPrice() // calculate the total price
   {
           for(int i=0;i<list.size();i++){
        	   totalPrice+=list.get(i).getPrice();
        	   list.get(i).getProduct().totalRevenue+=list.get(i).getPrice();
//        	   list.get(i).getProduct().setLeftQuantity(this);
           }
           
//add price*unitPrice: product.getPrice(); will calculate wholesale price itself
//           this.totalPrice = getDisVal(totalPrice); //
   }
   public void realTotalPrice(Customer c){
	   int validPoint=c.getValidPoint();
	   computeTotalPrice();
	   this.realTP=this.totalPrice-validPoint/4;
//	   double LP2=c.getLoyaltyPoint(c);
//	   
   }
   
   public double getTotalPrice() {
		return totalPrice;
	}

	public void remove(int ID){
		for(int i=0;i<list.size();i++){
			if(list.get(i).getProduct().getID()==ID){
				list.remove(i);
			}				
		}
	}
	
	public void remove(String name){
		for(int i=0;i<list.size();i++){
			if(list.get(i).getProduct().getName().compareTo(name)==0){
				list.remove(i);
			}				
		}
	}
}

