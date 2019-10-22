package mvc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import mvc.view.AppFrame;



public class ToolbarController implements ActionListener{
	private AppFrame view;
	private GameEngine model;
	
	public ToolbarController(AppFrame view, GameEngine model, JButton[] buttons) {
		this.view = view;
		this.model = model;
	}
	
	public void actionPerformed(ActionEvent e) {
		//Grabs the button pressed
		AbstractButton abstractButton = (AbstractButton)e.getSource();
		switch(abstractButton.getName()){
		
		//Checks which button is pressed by the name
		case "Spin":
			System.out.println("Button Spin");
			//Checks if the spin thread exists if not create the new thread
			if(!view.checkIfThreadExist("spin")){
				new Thread("spin"){
					@Override
					public void run(){
						//Grabs the current player points before spin
						int currentPoints = view.getPlayer().getPoints();
						view.getdropdownMenu().gettoolBar().spinningButton();
						view.getstatusBar().setSpinnerResultCoin1("Spinner result Coin 1 : ");
						view.getstatusBar().setSpinnerResultCoin2("Spinner result Coin 2 : ");
						
						//Checks if player has placed a bet, if not don't spin the player
						for (Player player : model.getAllPlayers()){		
							if(player.getBet() == 0 && player.getBetType() == BetType.NO_BET) {
								player.resetBet();
								
							}
							else {
								model.spinPlayer(player, 100,1000,100,50,500,50);
							}
						}
						
						//Waits 3 seconds before spinning the spinner coins
						try {
							Thread.sleep(3000);
								model.spinSpinner(100,1000,100,50,500,50);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}

						//Reset all the bet after spin for all players
						for (Player player : model.getAllPlayers()){	
							player.resetBet();
						}
					
						try {
							
							for (Player player : model.getAllPlayers()){
								//Checks if player has reached 0 or negative number, if has player is automatically removed from game
								if(player.getPoints() <= 0) {
									view.getdropdownMenu().gettoolBar().getcomboBox().removePlayer(model.getPlayer(player.getPlayerId()));
									model.removePlayer(model.getPlayer(player.getPlayerId()));
									JOptionPane.showMessageDialog(new JFrame(), "Player " + player.getPlayerName() + " have no more points, player have been removed from the game!", "Alert", JOptionPane.WARNING_MESSAGE);
	
								}
								else {
								}
							}
						}
						catch(Exception e) {
							e.printStackTrace();
						}
						try {
							//Updates all the status label required
							Thread.sleep(3000);
							int newPoints = view.getPlayer().getPoints();
							view.getstatusBar().setStatusPoints("Points(s) : " + view.getPlayer().getPoints());
							view.getstatusBar().setStatusWinLoosePoints("Winning/Loosing Bet : " + (newPoints - currentPoints));
							view.getdropdownMenu().gettoolBar().resetButton();
							view.getstatusBar().setStatusCurrentPlayer("");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
				.start();
				
				view.getstatusBar().setStatusBet("Point(s) bet : 0");
				view.getstatusBar().setStatusBetType("Bet type : NO_BET");
				view.repaint();
				}

			
			break;
			
		case "Place bet":
			//Calls player bet method
			System.out.println("Button Place bet");
			placeBet();
			view.repaint();
			break;
			
		case "Cancel/remove bet":
			//Resets the bet and places the player bet to 0 and bet type to no bet
			System.out.println("Button Cancel/remove bet");
			
			view.getPlayer().resetBet();
			model.placeBet(view.getPlayer(), 0, BetType.valueOf("NO_BET"));
			view.getstatusBar().setStatusBet("Point(s) bet : " + view.getPlayer().getBet());
			view.getstatusBar().setStatusBetType("Bet type : " + view.getPlayer().getBetType());
			view.getdropdownMenu().gettoolBar().resetButton();
			view.repaint();
			break;
		
		default:
			break;
		}
	}
	

	public void placeBet() {
		//Ask player to enter a bet
		String bet = JOptionPane.showInputDialog("Please enter a bet:");
		
		if(bet == null){
			
		}
		else{
			//Ask player to select bet type
			String[] betTypeChoices = {"COIN1", "COIN2", "BOTH"};
			String betTypeSelected = (String) JOptionPane.showInputDialog(null, "Select bet type:",
			        "BetType", JOptionPane.QUESTION_MESSAGE, null, betTypeChoices, betTypeChoices[0]); 
			try {
				//Checks if valid number was placed
				if(Integer.parseInt(bet) <= 0){
					
					JOptionPane.showMessageDialog(new JFrame(), "Please enter a valid number", "Alert", JOptionPane.WARNING_MESSAGE);
				
				}
				//Checks if player has enough points to place bet
				else if(Integer.parseInt(bet) > view.getPlayer().getPoints()) {
					JOptionPane.showMessageDialog(new JFrame(), "Not Enough Points!", "Alert", JOptionPane.WARNING_MESSAGE);

				}
				//Place bet and the bet type player chose
				else if(model.placeBet(view.getPlayer(), Integer.parseInt(bet), BetType.valueOf(betTypeSelected))){
					view.getstatusBar().setStatusBet("Point(s) bet : " + bet);
					view.getstatusBar().setStatusBetType("Bet type : " + BetType.valueOf(betTypeSelected));
					model.getPlayer(view.getPlayer().getPlayerId()).setResult(null);
					view.getdropdownMenu().gettoolBar().betSet();				
				}
				else{
					JOptionPane.showMessageDialog(new JFrame(), "Not Enough Points!", "Alert", JOptionPane.WARNING_MESSAGE);	
				}
				
				} 
			catch (Exception e) {
				JOptionPane.showMessageDialog(new JFrame(), "Bet canceled or invalid bet was placed", "Alert", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
}
