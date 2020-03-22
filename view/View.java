package view;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;

/**
 * 
 * Clasa de UI care contine atat definitia elementelor grafice
 * cat si definitia Listenerilor si tratarea lor independenta in clase anonime
 * 
 * echivalent View + controller pentru fiecare element grafic 
 * 
 *
 */

public class View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pane = new JPanel(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();
	private JButton addButton = new JButton("+");
	private JButton subButton = new JButton("-");
	private JButton mulButton = new JButton("*");
	private JButton divButton = new JButton("/");
	private JButton derivButton = new JButton("Deriv.");
	private JButton integButton = new JButton("Integrate");
	private JTextField text = new JTextField(20);
	private JTextField text2 = new JTextField(20);
	private JTextField output = new JTextField(20);
	private JLabel label = new JLabel("First polynomial");
	private JLabel label2 = new JLabel("Second polynomial");
	private JLabel result = new JLabel("Result");
	Controller controller = new Controller(this);

	public View(String name) {
		super(name);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 3;
		//c.weightx = 0.5;
		pane.add(addButton, c);
		addButton.addActionListener(controller);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 3;
		c.weightx = 0.5;
		pane.add(subButton, c);
		subButton.addActionListener(controller);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 5;
		c.gridy = 3;
		c.weightx = 0.5;
		pane.add(mulButton, c);
		mulButton.addActionListener(controller);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 6;
		c.gridy = 3;
		c.weightx = 0.5;
		pane.add(divButton, c);
		divButton.addActionListener(controller);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 7;
		c.gridy = 3;
		c.weightx = 0.5;
		pane.add(derivButton, c);
		derivButton.addActionListener(controller);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 8;
		c.gridy = 3;
		c.weightx = 0.5;
		pane.add(integButton, c);
		integButton.addActionListener(controller);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.gridx = 6;
		c.gridy = 0;
		c.gridwidth = 8;
		
		pane.add(text, c);

		c.gridx = 6;
		c.gridy = 1;
		c.gridwidth = 8;
		pane.add(text2, c);
		
		c.gridx = 6;
		c.gridy = 2;
		c.gridwidth = 8;
		output.setEditable(false);
		pane.add(output, c);
		
		c.gridx = 0;
		c.gridy = 0;
		pane.add(label, c);
		
		c.gridx = 0;
		c.gridy = 1;
		pane.add(label2, c);
		
		c.gridx = 0;
		c.gridy = 2;
		pane.add(result, c);
		
		this.add(pane);

	}

	public JButton Addition(){
		return addButton;
	}
	public JButton Subtraction(){
		return subButton;
	}
	public JButton Multiplication(){
		return mulButton;
	}
	public JButton Division(){
		return divButton;
	}
	public JButton Integration(){
		return integButton;
	}
	public JButton Derivate(){
		return derivButton;
	}
	public JTextField getFirstField(){
		return text;
	}
	
	public JTextField getSecondField(){
		return text2;
	}
	public JTextField getOutField() {
		return output;
	}
	public JLabel getResult(){
		return result;
	}

	public static void main(String args[]) {
		JFrame frame = new View("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}
