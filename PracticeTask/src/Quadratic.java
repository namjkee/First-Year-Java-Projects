/**
 * This program asks users to input a integer value into the coefficients
 * of a quadratic equation to then be used to calculate what type of roots
 * the quadratic equation has and what are the values of the roots
 * 
 * 
 *@date 2014.04.10
 *@author Clifford PHAN
 */
import java.util.Scanner;
public class Quadratic {

	public static void main(String[] args) {
		
		Scanner coefficients = new Scanner(System.in);	
		System.out.println("Enter the coefficient of x^2 (a), integer number: ");
		int a = coefficients.nextInt();
		
		System.out.println("Enter the coefficient of x (b), integer number: ");
		int b = coefficients.nextInt();
		
		System.out.println("Enter the constant (c), integer number: ");
		int c = coefficients.nextInt();
		System.out.println();
			coefficients.close();						
		
		
		double Determinants = Math.pow(b,2) - 4 * a * c;
		
		double Quadraticplus = (- b + Math.sqrt(Math.pow(b,2) - 4 * a * c))/ (2 * a); 
		double Quadraticminus = (- b - Math.sqrt(Math.pow(b,2) - 4 * a * c))/(2 * a);
		
		
		if (Determinants < 0) {
			System.out.println("The roots of this equation are complex numbers.");
		}
		else if (Determinants == 0) {
			System.out.println("There is one root: " + Quadraticplus);
		}
		else if (Determinants == 0) {
			System.out.println("There is one root: " + Quadraticminus);
		}
		else if (Determinants > 0) {
			System.out.print("There are two roots: " + Quadraticplus + " and " + Quadraticminus);
		}
		
	}
}