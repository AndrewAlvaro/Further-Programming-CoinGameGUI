package mvc.controller;


import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import mvc.view.AppFrame;
import mvc.view.ComboBox;
import mvc.view.Statusbar;
import mvc.view.Toolbar;

public class ComboBoxController implements ItemListener{
	private AppFrame view;
	private GameEngine model;
	ComboBox comboBox;
	Statusbar statusBar;
	Toolbar toolBar;
	
	
	
	public ComboBoxController(AppFrame view, GameEngine model) {
		this.view = view;
		this.model = model;

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		//Grabs the selected player in the combo box
		String playerName = e.getItem().toString();
		System.out.println("Selected:" + playerName);
		
		//Loops through the players in the current game engine
		for(Player player: model.getAllPlayers()) {
			
			//Checks if the player in game engine matches with the combo box player name
			if(player.getPlayerName().equalsIgnoreCase(playerName)) {
				System.out.println("Points available:" + player.getPoints());
				view.getstatusBar().setStatusPoints("Point(s) : " + player.getPoints());
				view.getstatusBar().setStatusBet("Point(s) bet : 0");
				view.getstatusBar().setStatusWinLoosePoints("Winning/Loosing Bet : 0");
				
				view.setPlayer(player);
				
				//Checks if game is spinning any coin
				if(!view.checkIfThreadExist("spin")){
					//Check if player bet is 0, if 0 set button to place bet only
					if(player.getBet() == 0) {
						view.getPlayer().resetBet();
						view.getdropdownMenu().gettoolBar().resetButton();
					}
					//Else sets the status bar with current player bet and bet type, set the button to spin and remove/cancel bet to be available
					else {
						view.getstatusBar().setStatusBet("Point(s) bet : " + player.getBet());
						view.getstatusBar().setStatusBetType("Bet type : " + player.getBetType());
						view.getdropdownMenu().gettoolBar().betSet();
					}
					try {
						//Checks if player previously played a game if not set the status to empty
						if(player.getResult() == null) {
							view.getstatusBar().setPlayerResultCoin1("Player result Coin 1 : ");
							view.getstatusBar().setPlayerResultCoin2("Player result Coin 2 : ");
						}
						else {
						//Grabs the player result and set the face of the coin label and set status result
						view.getCoinPanel().updateCoinResult(player.getResult().getCoin1().getFace(), player.getResult().getCoin2().getFace(), player, player.getPlayerId());
						}
					}
					
					catch(Exception e1) {
					}
					
					
				}
				
				else {
					view.getdropdownMenu().gettoolBar().spinningButton();
				}
				
				break;
				
				
			}
			//No player selected set statuses to none and all buttons unavailable
			else {
				view.getstatusBar().setStatusPoints("Point(s) : 0");
				view.getstatusBar().setStatusBet("Point(s) bet : 0");
				view.getstatusBar().setStatusBetType("Bet type : NO_BET");
				view.getdropdownMenu().gettoolBar().noPlayer();
			}
		}
		
	}
	

}