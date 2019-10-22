package mvc.view;


import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JToolBar;

import model.interfaces.GameEngine;
import mvc.controller.ToolbarController;


public class Toolbar extends JToolBar{
	JButton[] buttons = new JButton[3];
	ToolbarController controller;
	private ComboBox comboBox;
	
	public Toolbar(AppFrame view, GameEngine model) {
		
		//Passing frame, model and button into the controller
		controller = new ToolbarController(view, model, buttons);
		
		//Creates button
		setVisible(true);		
		buttons[0] = new JButton("Spin");
		buttons[0].setName("Spin");
		
		buttons[1] = new JButton("Place bet");
		buttons[1].setName("Place bet");
		
		buttons[2] = new JButton("Cancel/remove bet");
		buttons[2].setName("Cancel/remove bet");
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//Adds the combo box in the within the Toolbar frame
		comboBox = new ComboBox(view, model);
		add(comboBox);
		
		for(int i = 0 ; i < buttons.length ; i++) {
			//Adds individual buttons and set size and controller
			buttons[i].setPreferredSize(new Dimension(150, 20));
			buttons[i].setFocusPainted(false);
			add(buttons[i]);
			buttons[i].addActionListener(controller);
			addSeparator();
		}
		noPlayer();
	}
	
	//Method to enable and disable buttons during different scenarios
	public void resetButton() {
		buttons[0].setEnabled(false);
		buttons[1].setEnabled(true);
		buttons[2].setEnabled(false);
	}
	
	public void noPlayer() {
		buttons[0].setEnabled(false);
		buttons[1].setEnabled(false);
		buttons[2].setEnabled(false);
	}
	
	public void betSet() {
		buttons[0].setEnabled(true);
		buttons[1].setEnabled(false);
		buttons[2].setEnabled(true);
	}
	
	public void spinningButton() {
		buttons[0].setEnabled(false);
		buttons[1].setEnabled(false);
		buttons[2].setEnabled(false);
	}
	public ComboBox getcomboBox(){
		return comboBox;
	}
}
