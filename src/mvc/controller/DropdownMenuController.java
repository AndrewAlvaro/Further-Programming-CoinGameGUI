package mvc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.SimplePlayer;
import model.interfaces.Player;
import model.interfaces.GameEngine;
import mvc.view.AppFrame;
import mvc.view.ComboBox;

public class DropdownMenuController implements ActionListener{
	private AppFrame view;
	private GameEngine model;
	private int playerID;
	ComboBox comboBox;
	
	public DropdownMenuController(AppFrame view, GameEngine model) {
		this.view = view;
		this.model = model;
		playerID = model.getAllPlayers().size()+1;
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//Grabs the menu selected
		AbstractButton abstractButton = (AbstractButton)e.getSource();
		
		switch(abstractButton.getName()){
		//Checks which menu is selected by the name
			case "addplayer":
				addPlayer();
				System.out.println("Option 0");
				break;
			case "removeplayer":
				removePlayer();
				System.out.println("Option 1");
				break;
			case "quit":
				System.exit(0);
				System.out.println("Option 2");
				break;
			default:
				break;
		}
	}
	
	private void addPlayer(){
		//Ask player to input name
		String name = JOptionPane.showInputDialog("Enter Player Name :");
		if(name == null){
		}
		//Validate if player input is empty
		else if(name.trim().isEmpty()){
			JOptionPane.showMessageDialog(new JFrame(), "Please enter a name", "Alert", JOptionPane.WARNING_MESSAGE);
		}
		
		else{
			//Ask player to enter initial points
			String points = JOptionPane.showInputDialog("Enter Initial Points :");

			if(points == null){
			}
			//Validated if player input is empty
			else if(points.trim().isEmpty()){
				JOptionPane.showMessageDialog(new JFrame(), "Please enter a value", "Alert", JOptionPane.WARNING_MESSAGE);
			}
			//Validate points cannot be a negative number
			else if(Integer.parseInt(points.trim()) <= 0) {
				JOptionPane.showMessageDialog(new JFrame(), "Initial points cannot be a negative number", "Alert", JOptionPane.WARNING_MESSAGE);

			}
			
			else{
				//Adds player into the game in the model and the combo box 
				try {
					SimplePlayer player = new SimplePlayer(Integer.toString(playerID), name, Integer.parseInt(points.trim()));
					view.getdropdownMenu().gettoolBar().getcomboBox().addPlayer(player);
					model.addPlayer(player);
					playerID++;
					
				} 
				catch (Exception e) {
					JOptionPane.showMessageDialog(new JFrame(), "Please enter a valid number", "Alert", JOptionPane.WARNING_MESSAGE);
				}

			}
		}
	}
	
	private void removePlayer(){
		//Ask player for the player name
		String id = null;
		String name = JOptionPane.showInputDialog("Enter player name to remove :");
		
		if(name == null) {

		}
		//Validates if player input is empty
		else if(name.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(new JFrame(), "Player name cannot be empty!", "Alert", JOptionPane.WARNING_MESSAGE);
		}
		else {
			//Loops through the game engine to find the user with match name to grab player ID
			for(Player player: model.getAllPlayers()) {
				if(player.getPlayerName().equalsIgnoreCase(name)) {
					id = player.getPlayerId();
				}
			}
			//Remove player name from the model and combo box by the ID
			view.getdropdownMenu().gettoolBar().getcomboBox().removePlayer(model.getPlayer(id));
			model.removePlayer(model.getPlayer(id));
		}
	
	
	}

	
}