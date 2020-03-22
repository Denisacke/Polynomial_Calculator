package operations;

import java.util.Iterator;
import java.util.regex.Pattern;

import datatype.Monomial;
import datatype.Polynomial;

public class Logic {
	
	public Polynomial MergeAdd(Polynomial a, Polynomial b) {
		Polynomial result = new Polynomial();
		int aIterate = 0, bIterate = 0;
		while(aIterate != a.getList().size() && bIterate != b.getList().size()) {
			if(a.getList().get(aIterate).getPower() < b.getList().get(bIterate).getPower()) {
				Monomial aux = new Monomial(a.getList().get(aIterate).getCoefficient(), a.getList().get(aIterate).getPower());
				result.getList().add(aux);
				aIterate++;
			}
			else
				if(a.getList().get(aIterate).getPower() > b.getList().get(bIterate).getPower()) {
					Monomial aux = new Monomial(b.getList().get(bIterate).getCoefficient(), b.getList().get(bIterate).getPower());
					result.getList().add(aux);
					bIterate++;
				}
				else 
					if(a.getList().get(aIterate).getPower() == b.getList().get(bIterate).getPower()){
						Monomial aux = new Monomial(a.getList().get(aIterate).getCoefficient() + b.getList().get(bIterate).getCoefficient(), a.getList().get(aIterate).getPower());
						result.getList().add(aux);
						aIterate++;
						bIterate++;
				}
		}
		while(aIterate != a.getList().size()) {
			Monomial aux = new Monomial(a.getList().get(aIterate).getCoefficient(), a.getList().get(aIterate).getPower());
			result.getList().add(aux);
			aIterate++;
		}
		while(bIterate != b.getList().size()) {
			Monomial aux = new Monomial(b.getList().get(bIterate).getCoefficient(), b.getList().get(bIterate).getPower());
			result.getList().add(aux);
			bIterate++;
		}
		return result;
	}
	
	public Polynomial MergeSub(Polynomial a, Polynomial b) {
		Polynomial result = new Polynomial();
		int aIterate = 0, bIterate = 0;
		
		while(aIterate != a.getList().size() && bIterate != b.getList().size()) {
			if(a.getList().get(aIterate).getPower() < b.getList().get(bIterate).getPower()) {
				Monomial aux = new Monomial(a.getList().get(aIterate).getCoefficient(), a.getList().get(aIterate).getPower());
				result.getList().add(aux);
				aIterate++;
			}
			else
				if(a.getList().get(aIterate).getPower() > b.getList().get(bIterate).getPower()) {
					Monomial aux = new Monomial(-b.getList().get(bIterate).getCoefficient(), b.getList().get(bIterate).getPower());
					result.getList().add(aux);
					bIterate++;
				}
				else 
					if(a.getList().get(aIterate).getPower() == b.getList().get(bIterate).getPower()){
						Monomial aux = new Monomial(a.getList().get(aIterate).getCoefficient() - b.getList().get(bIterate).getCoefficient(), a.getList().get(aIterate).getPower());
						result.getList().add(aux);
						aIterate++;
						bIterate++;
				}
		}
		while(aIterate != a.getList().size()) {
			Monomial aux = new Monomial(a.getList().get(aIterate).getCoefficient(), a.getList().get(aIterate).getPower());
			result.getList().add(aux);
			aIterate++;
		}
		while(bIterate != b.getList().size()) {
			Monomial aux = new Monomial(-b.getList().get(bIterate).getCoefficient(), b.getList().get(bIterate).getPower());
			result.getList().add(aux);
			bIterate++;
		}
		return result;
	}
	
	public void mergeDuplicates(Polynomial a) {
		for(int i = 0; i < a.getList().size() - 1; i++) {
			for(int j = i + 1; j < a.getList().size(); j++) {
				if(a.getList().get(i).getPower() == a.getList().get(j).getPower()) {
					a.getList().get(i).setCoefficient(a.getList().get(i).getCoefficient() + a.getList().get(j).getCoefficient());
					a.getList().remove(j);
					j--;
				}
			}
		}
	}
	
	public String Addition(String first, String second){
		Polynomial a = new Polynomial();
		Polynomial b = new Polynomial();
		RegexMatches r = new RegexMatches();
		a = r.stringToPolynomial(first);
		b = r.stringToPolynomial(second);
		if(a == null || b == null) {
			return "Wrong input values";
		}
		a.sortList();
		b.sortList();
		Polynomial result = new Polynomial();
		mergeDuplicates(a);
		mergeDuplicates(b);
		result = MergeAdd(a, b);
		return r.polynomialToString(result);
	}
	
	public String Subtraction(String first, String second){
		Polynomial a = new Polynomial();
		Polynomial b = new Polynomial();
		RegexMatches r = new RegexMatches();
		a = r.stringToPolynomial(first);
		b = r.stringToPolynomial(second);
		
		if(a == null || b == null) {
			return "Wrong input values";
		}
		a.sortList();
		b.sortList();
		mergeDuplicates(a);
		mergeDuplicates(b);
		Polynomial result = new Polynomial();
		result = MergeSub(a, b);
		return r.polynomialToString(result);
	}
	
	public String Division(String first, String second){
		Polynomial a = new Polynomial();
		Polynomial b = new Polynomial();
		RegexMatches r = new RegexMatches();
		a = r.stringToPolynomial(first);
		b = r.stringToPolynomial(second);
		if(second.equals("0")) {
			return "Division by zero not allowed";
		}
		if(a == null || b == null) {
			return "Wrong input values";
		}
		a.sortList();
		b.sortList();
		mergeDuplicates(a);
		mergeDuplicates(b);
		Polynomial result = new Polynomial();
		int aIterate = a.getList().size() - 1;
		Polynomial aux = new Polynomial();
		int term = b.getList().get(b.getList().size() - 1).getPower();
		while(aIterate != -1) {
			if(a.getList().get(aIterate).getCoefficient() != 0) {
				if(a.getList().get(aIterate).getPower() >= term) {
					aux.copyList(b.getList());
					Double coeff = a.getList().get(aIterate).getCoefficient() / aux.getList().get(aux.getList().size() - 1).getCoefficient();
					int power = a.getList().get(aIterate).getPower() - aux.getList().get(aux.getList().size() - 1).getPower();
					Monomial monom = new Monomial(coeff, power);
					result.getList().add(monom);
					for(int i = 0; i < aux.getList().size(); i++) {
						aux.getList().get(i).setCoefficient(aux.getList().get(i).getCoefficient() * coeff);
						aux.getList().get(i).setPower(aux.getList().get(i).getPower() + power);
					}
					String subtract = Subtraction(r.polynomialToString(a), r.polynomialToString(aux));
					a = r.stringToPolynomial(subtract);
					aIterate = a.getList().size() - 1;
				}
				else
					aIterate--;
			}
			else {
				aIterate--;
			}
				
		}
		result.sortList();
		a.sortList();
		return r.polynomialToString(result) + ";remainder: " + r.polynomialToString(a);
	}
	
	public String Multiplication(String first, String second){
		Polynomial a = new Polynomial();
		Polynomial b = new Polynomial();
		RegexMatches r = new RegexMatches();
		a = r.stringToPolynomial(first);
		b = r.stringToPolynomial(second);
		
		if(a == null || b == null) {
			return "Wrong input values";
		}
		a.sortList();
		b.sortList();
		Polynomial result = new Polynomial();
		mergeDuplicates(a);
		mergeDuplicates(b);
		int aIterate = 0;
		while(aIterate != a.getList().size()) {
			for(int i = 0; i < b.getList().size(); i++) {
				Monomial aux = new Monomial(a.getList().get(aIterate).getCoefficient() * b.getList().get(i).getCoefficient(), 
						a.getList().get(aIterate).getPower() + b.getList().get(i).getPower());
				result.getList().add(aux);
			}
			aIterate++;
		}
		mergeDuplicates(result);
		return r.polynomialToString(result);
	}
	
	public String Integration(String first, String second) {
		Polynomial a = new Polynomial();
		Polynomial b = new Polynomial();
		RegexMatches r = new RegexMatches();
		a = r.stringToPolynomial(first);
		//b = r.stringToPolynomial(second);
		if(a == null) {
			return "Wrong input values";
		}
		Polynomial result = new Polynomial();
		mergeDuplicates(a);
		//mergeDuplicates(b);
		
		a.sortList();
		for(Monomial monom : a.getList()) {
			Monomial aux = new Monomial(monom.getCoefficient()/(monom.getPower() + 1), monom.getPower() + 1);
			result.getList().add(aux);
		}
		
		mergeDuplicates(result);
		return r.polynomialToString(result);
	}
	public String Derivative(String first, String second) {
		Polynomial a = new Polynomial();
		Polynomial b = new Polynomial();
		RegexMatches r = new RegexMatches();
		a = r.stringToPolynomial(first);
		if(a == null) {
			return "Wrong input values";
		}
		Polynomial result = new Polynomial();
		mergeDuplicates(a);
		//mergeDuplicates(b);
		
		a.sortList();
		for(Monomial monom : a.getList()) {
			Monomial aux = new Monomial(monom.getCoefficient()*(monom.getPower()), monom.getPower() - 1);
			result.getList().add(aux);
		}
		
		mergeDuplicates(result);
		return r.polynomialToString(result);
	}
}
