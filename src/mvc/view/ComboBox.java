package mvc.view;


import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import mvc.controller.ComboBoxController;

public class ComboBox extends JToolBar{

    private JComboBox<String> PlayerList;

    ComboBoxController comboboxController;

    //Creates an array of players
    ArrayList<String> players = new ArrayList<String>();
    
    public ComboBox(AppFrame view, GameEngine model){
    	
    	//Passing frame and model into the controller
    	comboboxController = new ComboBoxController(view, model);
    	
    	//Creates a combo box for list of players, adds to controller
		PlayerList = new JComboBox<String>();
		PlayerList.addItemListener(comboboxController);
		PlayerList.addItem("---Select Player---");
        add(PlayerList);
        
    }

	public void addPlayer(SimplePlayer player) {
		//Adds new player to the combo box by grabbing the name
		final int playerArraySize = 1;
		System.out.println(player.getPlayerName());
		players.add(player.getPlayerName());
		player.resetBet();
		PlayerList.addItem(players.get(players.size() - playerArraySize));
		
	}

	public void removePlayer(Player player) {
		//Removes player from the combo box by grabbing the name
		try {
			PlayerList.removeItem(player.getPlayerName());
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "Player name doesn't exists", "Alert", JOptionPane.WARNING_MESSAGE);
		}
	}

	
    
}