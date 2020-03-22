package datatype;

import java.util.LinkedList;
import java.util.TreeSet;

public class Polynomial {
	private LinkedList<Monomial> list;

	public Polynomial(){
		list = new LinkedList<Monomial>();
	}
	public LinkedList<Monomial> getList() {
		return list;
	}

	public void setList(LinkedList<Monomial> list) {
		this.list = list;
	}
	
	public void sortList() {
		Monomial[] array = new Monomial[list.size()];
		for(int i = 0; i < array.length; i++) {
			array[i] = new Monomial(list.get(i).getCoefficient(), list.get(i).getPower());
		}
		for(int i = 0; i < array.length - 1; i++)
			for(int j = i + 1; j < array.length; j++) {
				if(array[i].getPower() > array[j].getPower()) {
					Monomial aux = array[i];
					array[i] = array[j];
					array[j] = aux;
				}
			}
		LinkedList<Monomial> auxList = new LinkedList<Monomial>();
		for(Monomial elem: array) {
			auxList.add(elem);
		}
		list = auxList;
	}
	
	public void copyList(LinkedList<Monomial> auxList) {
		list.clear();
		for(Monomial monom : auxList) {
			Monomial aux = new Monomial(monom.getCoefficient(), monom.getPower());
			list.add(aux);
		}
	}
	
	public void printList() {
		for(Monomial e: list) {
			System.out.println("Coefficient: " + e.getCoefficient() + " Power: " + e.getPower());
		}
	}
}
