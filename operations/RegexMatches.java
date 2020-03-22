package operations;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import datatype.Monomial;
import datatype.Polynomial;
public class RegexMatches {

   public String polynomialToString(Polynomial result) {
	   String output = new String();
	   output = "";
	   for(int i = 0; i < result.getList().size(); i++) {
			if(result.getList().get(i).getPower() != 0) {
				if(result.getList().get(i).getCoefficient() != 0)
					output = output.concat(String.format("%.2f", result.getList().get(i).getCoefficient()) + "*x^" + result.getList().get(i).getPower() + "+");
			}
			else
				if(result.getList().get(i).getPower() == 0 && result.getList().get(i).getCoefficient() != 0)
					output = output.concat(String.format("%.2f", result.getList().get(i).getCoefficient()) + "+");
		}
		output = output.replace("+-", "-");
		if(output == "")
			return "0.0";
		return output.substring(0, output.length() - 1);
   }
   public Polynomial stringToPolynomial(String s){
	   String aux = new String();
	   Polynomial polynomial = new Polynomial();
	   String pattern = "([\\+\\-]?[\\d]*\\.?[\\d]*\\*?[Xx0-9]\\^?[\\d]*)";
	   aux = s;
	   aux = aux.replace("-", "+-");
	   aux = aux.replace(" ", "");
	   double coeff = 1;
	   int power = 0;
	   if(aux.equals("x") || aux.equals("X")) {
		   power++;
		   Monomial mon = new Monomial(coeff, power);
		   polynomial.getList().add(mon);
		   return polynomial;
	   }
	   Pattern r = Pattern.compile(pattern);
	   String[] var = aux.split("[\\+]");
	   
	   for(String it: var) {
	    	  if(!it.equals("") && Pattern.matches(pattern, it) == false) {
	    		  System.out.println("Wrong input values! " + it);
	    		  return null;
	    	  }
	   }
	   Matcher m = r.matcher(aux);
	      while(m.find()) {
	    	  coeff = 1;
	    	  power = 0;  
	    	  String str = m.group(1);
	    	  Monomial monom;
	    	  if((str.contains("x") || str.contains("X")) && !str.contains("^")) {
	    		  power++;
	    		  if(str.equals("x") || str.equals("X")) {
	    			   //power++;
	    			   Monomial mon = new Monomial(coeff, power);
	    			   polynomial.getList().add(mon);
	    			   continue;
	    		  }
	    		  str = str.replace("x", "");
	    		  str = str.replace("X", "");
	    		  if(!str.contains("*")) {
		    		  str = str.replace("+", "1");
		    		  str = str.replace("-", "-1");
	    		  }
	    		  str = str.replace("*", "");
	    		  coeff = Double.parseDouble(str);
	    		  monom = new Monomial(coeff, power);
	    		  polynomial.getList().add(monom);
    			  continue;
	    	  }
	    	  if(!str.contains("x") && !str.contains("X")) {
	    		  coeff = Double.parseDouble(str);
	    		  power = 0;
	    		  monom = new Monomial(coeff, power);
	    		  polynomial.getList().add(monom);
    			  continue;
	    	  }
	    	  String[] digits = str.split("[xX]");
	    	  
	    	  for(String it: digits) {
	    		  
	    		  if(it.equals(""))
	    			  continue;
	    		  if(it.contains("*")) {
	    			  it = it.replace("*", "");
	    			  coeff = Double.parseDouble(it);
	    		  }
	    		  else
	    			  if(it.equals("-")) {
	 	    			 coeff = -coeff;
	 	    		  }
	    		  if(it.contains("^")) {
	    			  
	    			  it = it.replace("^", "");
	    			  power = Integer.parseInt(it);
	    			  monom = new Monomial(coeff, power);
	    			  polynomial.getList().add(monom);
	    		  }
	    		  
	    	  }
	      }
	   return polynomial;
   }
   public static void main( String args[] ) {
      String line = "-X^5+4*x^4-2*x^3+2*x^2+ x+3";
      String pattern = "([\\+\\-]?[\\d]*\\*?[Xx0-9]\\^?[\\d]?)";

      Pattern r = Pattern.compile(pattern);
      line = line.replace(" ", "");
      line = line.replace("-", "+-");
      System.out.println(line);
      String var[] = line.split("[\\+]");
      boolean OK = true;
      for(String aux: var) {
    	  if(!aux.equals("") && Pattern.matches(pattern, aux) == false) {
    		  OK = false;
    		  System.out.println(aux);
    		  break;
    	  }
      }
      if(OK == false) {
    	  System.err.println("Wrong input values");
    	  return;
      }
      Matcher m = r.matcher(line);
      while(m.find()) {
    	  String s = m.group(1);
    	  System.out.println(m.group(1));
    	  String[] digits = s.split("[Xx]");
    	  System.out.println("\nAnd the splits are:");
    	  for(String it: digits) {
    		  it = it.replace("*", "");
    		  it = it.replace("^", "");
    		  System.out.println(it);
    	  }
      }
   }
}