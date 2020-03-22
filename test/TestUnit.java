package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import datatype.Monomial;
import datatype.Polynomial;
import operations.Logic;
import operations.RegexMatches;
class TestUnit {

	@Test
	void test() {
		//fail("Not yet implemented");
		Polynomial testPolynomial = new Polynomial();
		RegexMatches reg = new RegexMatches();
		Logic logic = new Logic();
		Monomial monom = new Monomial(2, 5); 								//added 2*x^5
		testPolynomial.getList().add(monom);
		assertEquals("2.00*x^5", reg.polynomialToString(testPolynomial));
		monom = new Monomial(2, 5); 										//added 2*x^5 again
		testPolynomial.getList().add(monom);
		logic.mergeDuplicates(testPolynomial);
		assertEquals("4.00*x^5", reg.polynomialToString(testPolynomial));
		monom = new Monomial(-1, 3);										//added -1*x^3
		testPolynomial.getList().add(monom);
		assertEquals("4.00*x^5-1.00*x^3", reg.polynomialToString(testPolynomial));
		monom = new Monomial(0, 1);											//added 0*x^1, so nothing should change from previous step
		testPolynomial.getList().add(monom);
		assertEquals("4.00*x^5-1.00*x^3", reg.polynomialToString(testPolynomial));
		
		//ADDITION
		Polynomial a = new Polynomial();
		Polynomial b = new Polynomial();
		a.copyList(testPolynomial.getList());								//should have copied the previous polynomial
		assertEquals("4.00*x^5-1.00*x^3", reg.polynomialToString(a));
		monom = new Monomial(4, 1);	
		b.getList().add(monom);												//added 4*x^1 to 2nd polynomial
		String result = logic.Addition(reg.polynomialToString(a), reg.polynomialToString(b)); //All operations sort the monomials in a polynomial in increasing order
		assertEquals("4.00*x^1-1.00*x^3+4.00*x^5", result);
		monom = new Monomial(-4, 0);											//added -4 to 2nd polynomial
		b.getList().add(monom);	
		result = logic.Addition(reg.polynomialToString(a), reg.polynomialToString(b));
		assertEquals("-4.00+4.00*x^1-1.00*x^3+4.00*x^5", result);
		
		//SUBTRACTION
		assertEquals("4.00*x^5-1.00*x^3", reg.polynomialToString(a));
		assertEquals("4.00*x^1-4.00", reg.polynomialToString(b));
		result = logic.Subtraction(reg.polynomialToString(a), reg.polynomialToString(b)); //simply did the subtraction of previous polynomials
		assertEquals("4.00-4.00*x^1-1.00*x^3+4.00*x^5", result);
		
		//MULTIPLICATION
		assertEquals("4.00*x^5-1.00*x^3", reg.polynomialToString(a));
		assertEquals("4.00*x^1-4.00", reg.polynomialToString(b));
		result = logic.Multiplication(reg.polynomialToString(a), reg.polynomialToString(b)); //simply did the multiplication of previous polynomials
		assertEquals("4.00*x^3-4.00*x^4-16.00*x^5+16.00*x^6", result);
		
		//DIVISION
		assertEquals("4.00*x^5-1.00*x^3", reg.polynomialToString(a));
		assertEquals("4.00*x^1-4.00", reg.polynomialToString(b));
		result = logic.Division(reg.polynomialToString(a), reg.polynomialToString(b)); 		//simply did the division of previous polynomials
		assertEquals("0.75+0.75*x^1+0.75*x^2+1.00*x^3+1.00*x^4;remainder: 3.00", result);
		result = logic.Division("3", "2");													//did this to show that it works with everything
		assertEquals("1.50;remainder: 0.0", result);
		result = logic.Division("1", "0");													//checked if there's a division by zero
		assertEquals("Division by zero not allowed", result);
		
		//INTEGRATION
		assertEquals("4.00*x^5-1.00*x^3", reg.polynomialToString(a));
		assertEquals("4.00*x^1-4.00", reg.polynomialToString(b));
		result = logic.Integration(reg.polynomialToString(a), reg.polynomialToString(b));   //integrated the 1st polynomial
		assertEquals("-0.25*x^4+0.67*x^6", result);
		
		//DERIVATION
		assertEquals("4.00*x^5-1.00*x^3", reg.polynomialToString(a));
		assertEquals("4.00*x^1-4.00", reg.polynomialToString(b));
		result = logic.Derivative(reg.polynomialToString(a), reg.polynomialToString(b));    //integrated the 2nd polynomial
		assertEquals("-3.00*x^2+20.00*x^4", result);
		
		//WRONG INPUT
		a = reg.stringToPolynomial("x%2");
		assertEquals(null, a);
		a = reg.stringToPolynomial("y^2+x");
		assertEquals(null, a);
		a = reg.stringToPolynomial("xx^2");
		assertEquals(null, a);
	}

}
