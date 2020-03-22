package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.View;

import operations.Logic;

public class Controller implements ActionListener{
	
	private View view;
	
	private Logic logic = new Logic();
	public Controller(View v){
		this.view = v;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == view.Addition()){
			String s = view.getFirstField().getText();
			String s2 = view.getSecondField().getText();
			if(s.compareTo("") == 0 || s2.compareTo("") == 0) {
				view.getOutField().setText("Wrong input values");
				return;
			}
			view.getOutField().setText(logic.Addition(s, s2));
		}
		if(source == view.Subtraction()){
			String s = view.getFirstField().getText();
			String s2 = view.getSecondField().getText();
			if(s.compareTo("") == 0 || s2.compareTo("") == 0) {
				view.getOutField().setText("Wrong input values");
				return;
			}
			view.getOutField().setText(logic.Subtraction(s, s2));
		}
		
		if(source == view.Multiplication()){
			String s = view.getFirstField().getText();
			String s2 = view.getSecondField().getText();
			if(s.compareTo("") == 0 || s2.compareTo("") == 0) {
				view.getOutField().setText("Wrong input values");
				return;
			}
			view.getOutField().setText(logic.Multiplication(s, s2));
		}
		
		if(source == view.Division()){
			String s = view.getFirstField().getText();
			String s2 = view.getSecondField().getText();
			if(s.compareTo("") == 0 || s2.compareTo("") == 0) {
				view.getOutField().setText("Wrong input values");
				return;
			}
			view.getOutField().setText(logic.Division(s, s2));
		}
		
		if(source == view.Integration()){
			String s = view.getFirstField().getText();
			String s2 = view.getSecondField().getText();
			if(s.compareTo("") == 0) {
				view.getOutField().setText("Wrong input values");
				return;
			}
			view.getOutField().setText(logic.Integration(s, s2));
		}
		
		if(source == view.Derivate()){
			String s = view.getFirstField().getText();
			String s2 = view.getSecondField().getText();
			if(s.compareTo("") == 0) {
				view.getOutField().setText("Wrong input values");
				return;
			}
			view.getOutField().setText(logic.Derivative(s, s2));
		}
	}

}
