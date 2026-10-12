import java.io.*;
import java.util.*;
public class Main{
public static void main(String[] args){
Scanner sc=new Scanner(System.in);
ArrayList<Expense> expenses=new ArrayList<>();
loadExpenses(expenses);
int choice =0;
while(choice!=6){
System.out.println("\n======Personal Expense Tracker=======");
System.out.println("1.Add Expense");
System.out.println("2. View Expense");
System.out.println("3.view Total Expenses");
System.out.println("4.delete expense");
System.out.println("5.Edit Expense");
System.out.println("6.Exit");
System.out.print("Enter your choice");
choice=sc.nextInt();
sc.nextLine();
switch(choice){
case 1:
System.out.println("Enter cateogry");
String cateogry=sc.nextLine();
System.out.println("Enter amount");
double amount=sc.nextDouble();
sc.nextLine();
System.out.println("Enter the description");
String description = sc.nextLine();
System.out.println("Enter Date(DD-MM-YYY)");
String date=sc.nextLine();
expenses.add(new Expense(cateogry,amount,description,date));
saveExpenses(expenses);
System.out.println("Expense added successfully");
break;
case 2:
if(expenses.isEmpty()){
    System.out.println("No expenses recorded");
}else{
    for(int i=0;i<expenses.size();i++){
        System.out.println("Cateogry: "+expenses.get(i).category);
        System.out.println("Amount spent:"+expenses.get(i).amount);
        System.out.println("Description: "+expenses.get(i).description);
        System.out.println("Date: "+expenses.get(i).date);
    }
}
break;
case 3:
double total=0;
if(expenses.isEmpty()){
    System.out.println("No expenses recorded");
}else{
    for(int i=0; i<expenses.size();i++){
        total=total+expenses.get(i).amount;
    }
    System.out.println("Total Expenses:"+total);
}
break;
case 4:
if(expenses.isEmpty()){
    System.out.println("No expenses recorded");
}else{
    for(int i=0;i<expenses.size();i++){
        System.out.println(i+"."+expenses.get(i).category+"-Rs"+expenses.get(i).amount+"-"+expenses.get(i).description);
    }
System.out.println("Enter expense number to be deleted: ");
int index=sc.nextInt();
sc.nextLine();
if(index>=0&&index<expenses.size()){
    expenses.remove(index);
    saveExpenses(expenses);
    System.out.println("Expense deleted successsfully!!!");
}else{
    System.out.println("Invalid expense number.");
}
}
break;
case 5:
    if(expenses.isEmpty()){
        System.out.println("No expenses to edit");
    }else{
    for(int i=0;i<expenses.size();i++){
        System.out.println(i+"."+expenses.get(i).category+"-Rs"+expenses.get(i).amount+"-"+expenses.get(i).description);
    }
System.out.println("Enter expense number to be edit: ");
int index=sc.nextInt();
sc.nextLine();
if(index>=0&&index<expenses.size()){
    Expense e= expenses.get(index);
    System.out.println("Enter new Category");
    e.category= sc.nextLine();
    System.out.println("Enter new Amount");
    e.amount=sc.nextDouble();
    sc.nextLine();
    System.out.println("Enter new Description");
    e.description =sc.nextLine();
    System.out.println("Enter new Date (DD-MM-YYYY)");
    e.date = sc.nextLine();
    saveExpenses(expenses);
    System.out.println("Expense updated successsfully!!!");
}else{
    System.out.println("Invalid expense number.");
}
}
 break;
 case 6:
    System.out.println("---Exiting----");
    break;
default:
System.out.println("Invalid Choice");
break;
}
}
sc.close();
}
public static void saveExpenses(ArrayList<Expense> expenses){
    try{
        FileWriter writer= new FileWriter("expenses.txt");
        for(int i=0; i<expenses.size();i++){
            Expense e=expenses.get(i);
            writer.write(
                e.category+"|"+e.amount+"|"+e.description+"|"+e.date+"\n"
            );
        }
        writer.close();
        System.out.println("Expenses currently in list:"+expenses.size());
    }catch(Exception e){
            System.out.println("error saving expenses");
        }
}
public static void loadExpenses(ArrayList<Expense> expenses){
        try {
            File file = new File ("expenses.txt");
            if(!file.exists()){
                return;
            }
            BufferedReader reader =new BufferedReader(new FileReader(file));
            String line;
            while((line=reader.readLine())!=null){
                String[] parts=line.split("\\|",4);
                if(parts.length==3){
                    String category =parts[0];
                    double amount=Double.parseDouble(parts[1]);
                    String description =parts[2];
                    String date="Not specified";
                    expenses.add(new Expense(category,amount,description,date));
                }else if(parts.length==4){
                    String category=parts[0];
                    double amount=Double.parseDouble(parts[1]);
                    String description =parts[2];
                    String date=parts[3];
                    expenses.add(new Expense(category,amount,description,date));
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error loading expenses");
        }
}
}
