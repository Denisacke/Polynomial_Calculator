package datatype;

public class Monomial {
	private double coefficient;
	private int power;
	
	public Monomial(double coeff, int pow){
		coefficient = coeff;
		power = pow;
	}
	public double getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	
}
