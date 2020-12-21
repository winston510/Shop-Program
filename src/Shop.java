import java.util.Scanner;
import java.util.ArrayList;
public class Shop {
	
	//Shows the introduction method as well as take in the user input for which one they want
	public static void intro() {
		int userInput;
		System.out.println("This program supports 4 functions: ");
		System.out.println("1. Setup shop");
		System.out.println("2. Buy");
		System.out.println("3. List Items");
		System.out.println("4. Checkout");
	}
	
	//checks to see if the arrays are empty
	//public static 
	
	//returns users option for which function
	public static int functionType(Scanner input) {
		int userInput;
		System.out.println("Please type the function you want: ");
		userInput = input.nextInt();
		return userInput;
	}
	
	//creates string arrays
	public static String[] createStringArray(int arraySize) {
		String[] array = new String[arraySize];
		return array;
	}
	
	//creates double arrays
	public static double[] createDoubleArray(int arraySize) {
		double[] array = new double[arraySize];
		return array;
	}
	
	//creates integer arrays
	public static int[] createIntArray(int arraySize) {
		int[] array = new int[arraySize];
		return array;
	}
	
	//checks if the array is all zeros
	public static int checkArray(int[] array) {
		int addedTotalInArray = 0;
		if(array == null) {
			return addedTotalInArray;
		}
		else {
			for(int i = 0; i < array.length; i++) {
				addedTotalInArray += array[i];
			}
			return addedTotalInArray;
		}
	}
	
	//takes the user input for the product name and returns the name for the array
	public static String setProductName(Scanner input, int i) {
		String userInput;
		System.out.println("Enter the name of the " + i + "st product: ");
		userInput = input.next();
		return userInput;
	}
	
	//takes the users input for the price per package and returns the value for the array
	public static double setPackagePrice(Scanner input, String productNames) {
		double userInput;
		System.out.println("Enter the per package price of " + productNames + ": ");
		userInput = input.nextDouble();
		
		//makes sure that the value entered by user for the price of the product is not less than 0
		while(userInput < 0) {
			System.out.println("Enter a number >= 0: ");
			userInput = input.nextDouble();
			if(userInput >= 0) {
				break;
			}
		}
		return userInput;
	}
	
	//takes the user input for special discount and returns the value for the array
	public static int setPackageAmountDiscount(Scanner input) {
		int userInput;
		System.out.println("Enter the number of packages ('x') to qualify for Special Discount (buy 'x' get 1 free) for alpha, or 0 if no Special Discount offered: ");
		userInput = input.nextInt();
		
		//makes sure that the value entered by user for the special discount is a while number that is not less than 0
		while(userInput < 0) {
			System.out.println("Enter a whole number >= 0");
			userInput = input.nextInt();
			if(userInput >= 0) {
				break;
			}
		}
		return userInput;
	}
	
	//takes the user input for the dollar amount for additional discount and returns value
	public static int dollarAmountAdditonalDiscount(Scanner input) {
		int userInput;
		System.out.println("Enter the dollar amount to qualify for Additional Discount (or 0 if none offered): ");
		userInput = input.nextInt();
		return userInput;
	}
	
	//takes the user input for additional discount rate and returns value
	public static double additionalDiscountRate(Scanner input) {
		double userInput;
		System.out.println("Enter the Additional Discount Rate (e.g., 0.1 for 10%): ");
		userInput = input.nextDouble();
		
		//makes sure that the number entered by user for the additional discount rate is greater than 0 and less than or equal to 0.5
		while(userInput < 0 || userInput >= 0.5) {
			System.out.println("Invalid input. Enter a value > 0 and <= 0.5");
			userInput = input.nextDouble();
			if(userInput > 0 && userInput <= 0.5) {
				break;
			}
		}
		return userInput;
	}
	
	//takes user input for packages they want to buy
	public static int setPackagesToBuy(Scanner input, String productName) {
		int userInput;
		System.out.println("Enter the number of " + productName + " packages to buy: ");
		userInput = input.nextInt();
		while(userInput < 0) {
			System.out.println("Invalid input. Enter a value >= 0");
			userInput = input.nextInt();
			if(userInput >= 0) {
				break;
			}
		}
		return userInput;
	}
	
	//this prints the list items as well as returns the total for each item
	public static double listItems(int packageAmount, String productName, double pricePerProduct) {
		double addPricePerItem;
		addPricePerItem = packageAmount * pricePerProduct;
		
		//only shows the items that the user purchased
		if(packageAmount > 0) {
			System.out.println(packageAmount + " packages of " + productName + " @ $" + String.format("%.2f", pricePerProduct) + " per pkg = $" + String.format("%.2f", addPricePerItem));
		}
		return addPricePerItem;
	}
	
	//adds up to get original sub total
	public static double originalSubTotal(int[] packageAmountPerProduct, double[] pricePerProduct, int arraySize) {
		double originalSubTotal = 0;
		
		//calculates the price and package amount per item and adds up the total and stores it into a variable
		for(int i = 0; i < arraySize; i ++) {
			originalSubTotal = originalSubTotal + (packageAmountPerProduct[i] * pricePerProduct[i]);
		}
		return originalSubTotal;
	}
	
	//calculates the total for special discounts
	public static double specialDiscount(int[] packageSpecialDiscount, int[] packagesBuy, double [] packagePrice,int arraySize) {
		double specialDiscountTotal = 0;
		
		//adds up all of the special discount amount per product and stores it into one variable
		for(int i = 0; i < arraySize; i++) {
			//checks if there are any packages and if there is a discount
			if(packagesBuy[i] > 0 && packageSpecialDiscount[i] > 0) {
				//you add 1 to special discount because you want to group the amount for example if it is by 4 get 1 free you need to group 5 packages not 4.
					specialDiscountTotal = specialDiscountTotal + (int)(packagesBuy[i] / (packageSpecialDiscount[i] + 1)) * packagePrice[i];
			}
		}
		return specialDiscountTotal;
	}
	
	//calculates and returns the new Sub Total
	public static double newSubtTotal(double originalSubTotal, double specialDiscount) {
		return originalSubTotal - specialDiscount;
	}
	
	// calculates and returns the additional discount total
	public static double additonalDiscount(double newSubTotal, double additionalDiscountRate) {
		double additionalDiscountTotal;
		additionalDiscountTotal = newSubTotal * additionalDiscountRate;
		return additionalDiscountTotal;
	}
	
	//prints the checkout list
	public static void checkout(double originalSubTotal, double specialDiscount, double newSubTotal, int dollarAmountAdditionalDiscount, double additionalDiscountRate , double additionalDiscount, double finalTotal) { 
		System.out.print("Original Sub Total: $");
		System.out.printf("%.2f", originalSubTotal);
		System.out.println();
		if(specialDiscount > 0) {
			System.out.print("Special Discounts: -$");
			System.out.printf("%.2f", specialDiscount);
			System.out.println();
		}
		else {
			System.out.println("No Special Discounts applied");
		}
		System.out.print("New Sub Total: $");
		System.out.printf("%.2f", newSubTotal);
		System.out.println();
		
		//checks if the new sub total is greater than the additional discount amount to determine if it will be displayed
		if(newSubTotal >= dollarAmountAdditionalDiscount && dollarAmountAdditionalDiscount != 0) {
			System.out.print("Additional " + (int)(additionalDiscountRate * 100) + "% Discount: -$");
			System.out.printf("%.2f", additionalDiscount);
			System.out.println();
		}
		else {
			System.out.println("You did not qualify for an Additional Discount");
		}
		System.out.print("Final Sub Total: $");
		System.out.printf("%.2f", finalTotal);
		System.out.println();
		System.out.println();
		System.out.println("Thanks for coming!");
	}
	
	//checks if user wants to rerun
	public static int rerun(Scanner input) {
		int userInput;
		System.out.println("-------------------------------------------------");
		System.out.println("Would you like to re-run (1 for yes, 0 for no)?");
		System.out.println("-------------------------------------------------");
		userInput = input.nextInt();
		return userInput;
	}
	
	
	
	public static void main(String []args) {
		
		//holds users option for introduction
		int userOption = 0;
		
		//holds the dollar amount for additional discount
		int dollarAmountAdditionalDiscount = 0;
		
		//holds the additional discount rate
		double additionalDiscountRate = 0;
		
		//this holds the array size amount depending on what the user inputs
		int arraySize = 0;
		
		//determines if user wants to re-run
		int rerun = 1;
		
		//array for the products names
		String[] productNames = null;
		
		//array for the package price of the products
		double [] packagePrice = null;
		
		//array for package amount for special discount
		int[] packageSpecialDiscount = null;
		
		//array for number of packages users buy for the product name
		int[] packagesBuy = null;
		
		//array for the added prices per item
		double[] addPricePerItem = null;
		
		//determines if function 2 has been run
		int messageSwitch = 0;
		
		Scanner input = new Scanner(System.in);
		
		//makes sure that the programs runs 
		while(rerun == 1) {
			
			//gets the program started
			if(userOption == 0) {
				intro();
				System.out.println("Please type the function you want: ");
				userOption = input.nextInt();
			}
		
			//function 1 (set up shop)
			if(userOption == 1) {
				
				System.out.println("Please enter the number of items to set up shop: ");
				arraySize = input.nextInt();
				
				//creates the arrays by calling the methods
				productNames = createStringArray(arraySize);
				packagePrice = createDoubleArray(arraySize);
				packageSpecialDiscount = createIntArray(arraySize);
				packagesBuy = createIntArray(arraySize);
				addPricePerItem = createDoubleArray(arraySize);
				
				//fills in the arrays with the help of methods for the product names, price of product and special discount for the product
				for(int i = 0; i < arraySize; i++) {
					productNames[i] = setProductName(input, i+1);
					packagePrice[i] = setPackagePrice(input, productNames[i]);
					packageSpecialDiscount[i] = setPackageAmountDiscount(input);
				}
				
				//fills in the variables with the help of methods for the dollar amount to qualify for discount and the additional discount rate
				dollarAmountAdditionalDiscount = dollarAmountAdditonalDiscount(input);
				if(dollarAmountAdditionalDiscount != 0) {
					additionalDiscountRate = additionalDiscountRate(input);
				}
				System.out.println();
				intro();
				System.out.println("Please type the function you want: ");
				userOption = input.nextInt();
			}
		
			//function 2 (buy)
			if(userOption == 2) {
				
				//checks if any of the arrays are empty to determine if this function should run
				if(productNames == null || packagePrice == null || packageSpecialDiscount == null || packagesBuy == null || addPricePerItem == null) {
					
					//runs until the user proceeds with the setup option
					while(productNames == null || packagePrice == null || packageSpecialDiscount == null || packagesBuy == null || addPricePerItem == null) {
						System.out.println("The shop has not been set up yet!");
						System.out.println();
						intro();
						System.out.println("Please type the function you want: ");
						userOption = input.nextInt();
						
						//leaves the loop when the user chooses the setup option
						if(userOption == 1) {
							break;
						}
					}
					System.out.println();
				}
				else {
					
					messageSwitch = 1;
					
					//fills in the array with the help of method for the package amount user wants to buy per product
					for(int i = 0; i < arraySize; i++) {
						packagesBuy[i] = setPackagesToBuy(input, productNames[i]);
					}
					System.out.println();
					intro();
					System.out.println("Please type the function you want: ");
					userOption = input.nextInt();
				}
			}
			
			//function 3 (list items)
			if(userOption == 3) {
				
				int packagesBuyTotal;
				packagesBuyTotal = checkArray(packagesBuy);
				
				
				//checks if any of the arrays are empty to determine if this function should run
				if(productNames == null || packagePrice == null || packageSpecialDiscount == null || packagesBuy == null || addPricePerItem == null) {
					while(productNames == null || packagePrice == null || packageSpecialDiscount == null || packagesBuy == null || addPricePerItem == null) {
						System.out.println("The shop has not been set up yet!");
						System.out.println();
						intro();
						System.out.println("Please type the function you want: ");
						userOption = input.nextInt();
						if(userOption == 1) {
							break;
						}
					}
				}
				
				else if(packagesBuyTotal == 0) {
					while(packagesBuyTotal == 0) {
						if(checkArray(packagesBuy) == 0) {
							System.out.println("No items were purchased.");
							System.out.println();
						}
						if(messageSwitch == 0) {
							System.out.println("You have not bought anything!");
							System.out.println();
						}
						intro();
						System.out.println("Please type the function you want: ");
						userOption = input.nextInt();
						if(userOption == 2) {
							break;
						}
					}
				}
				
				else {
					
					//displays the packages bought, product name and package price by going through the array with the help of methods
					for(int i = 0; i < arraySize; i++) {
						addPricePerItem[i] = listItems(packagesBuy[i], productNames[i], packagePrice[i]);
					}
					System.out.println();
					intro();
					System.out.println("Please type the function you want: ");
					userOption = input.nextInt();
				}
			}
			
			//function 4 (checkout)
			if(userOption == 4) {
				
				//checks if any of the arrays are empty to determine if this function should run
				if(productNames == null || packagePrice == null || packageSpecialDiscount == null || packagesBuy == null || addPricePerItem == null) {
					while(productNames == null || packagePrice == null || packageSpecialDiscount == null || packagesBuy == null || addPricePerItem == null) {
						System.out.println("The shop has not been set up yet!");
						System.out.println();
						intro();
						System.out.println("Please type the function you want: ");
						userOption = input.nextInt();
						if(userOption == 1) {
							break;
						}
					}
				}
				else {
					
					//stores the original sub total amount returned from the method
					double originalSubTotal;
					originalSubTotal = originalSubTotal(packagesBuy, packagePrice, arraySize);
					
					//stores the special discount amount returned from the method
					double specialDiscount;
					specialDiscount = specialDiscount(packageSpecialDiscount, packagesBuy, packagePrice, arraySize);
					
					//stores the new sub total amount returned from the method
					double newSubTotal;
					newSubTotal = newSubtTotal(originalSubTotal, specialDiscount);
					
					//stores the additional discount amount returned from the method
					double additionalDiscount;
					additionalDiscount = additonalDiscount(newSubTotal, additionalDiscountRate);
					
					//stores the final total amount subtracted from the variables newSubTotal and additionalDiscount
					double finalTotal;
					finalTotal = newSubTotal - additionalDiscount;
					
					//calls the checkout() method to print out the checkout list
					checkout(originalSubTotal, specialDiscount, newSubTotal, dollarAmountAdditionalDiscount, additionalDiscountRate, additionalDiscount, finalTotal);
					
					System.out.println();
					
					//calls the method to ask the user if they want to re-run the program
					rerun = rerun(input);
					
					//checks if does not want to re-run program so the program can stop
					if(rerun == 1) {
						
						//brings the user back to the introduction menu
						userOption = 0;
						
						//resets the arrays
						productNames = null;
						packagePrice = null;
						packageSpecialDiscount = null;
						packagesBuy = null;
						addPricePerItem = null;
					}
					if(rerun == 0) {
						break;
					}
				}		
			}
		}
	}
}
