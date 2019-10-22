package mvc.view;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.interfaces.GameEngine;

public class Statusbar extends JPanel{

	//Creates the JLabel with the string values
	private JLabel statusLabelPoints = new JLabel("Point(s) : 0", JLabel.CENTER);
	private JLabel statusLabelBet = new JLabel("Point(s) bet : 0", JLabel.CENTER);
	private JLabel statusLabelBetType = new JLabel("Bet type : NO_BET", JLabel.CENTER);
	private JLabel statusLabelWinLoosePoints = new JLabel("Winning/Loosing Bet : 0", JLabel.CENTER);
	
	private JLabel statusLabelPlayerSpinning = new JLabel("Player spinning: ", JLabel.CENTER);
	private JLabel statusLabelCurrentPlayer = new JLabel("", JLabel.CENTER);
	
	private JLabel PlayerResultCoin1 = new JLabel("Player result Coin 1 : ", JLabel.CENTER);
	private JLabel PlayerResultCoin2 = new JLabel("Player result Coin 2 : ", JLabel.CENTER);
	private JLabel SpinnerResultCoin1 = new JLabel("Spinner result Coin 1 : ", JLabel.CENTER);
	private JLabel SpinnerResultCoin2 = new JLabel("Spinner result Coin 2 : ", JLabel.CENTER);
	
	public Statusbar(AppFrame view, GameEngine model) {
		//Sets layout for the Statusbar
		setLayout(new GridLayout(0, 4));
		
		//Creates a black border around the required JLabel
		statusLabelPoints.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		statusLabelBet.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		statusLabelBetType.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		statusLabelWinLoosePoints.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		//Adds the JLabel
		add(statusLabelPoints);
		add(statusLabelBet);
		add(statusLabelBetType);
		add(statusLabelWinLoosePoints);
		
		add(PlayerResultCoin1);
		add(PlayerResultCoin2);
		add(SpinnerResultCoin1);
		add(SpinnerResultCoin2);
		
		add(statusLabelPlayerSpinning);
		add(statusLabelCurrentPlayer);
		setVisible(true);
		
	}
	
	//Setters and getters for the required labels
	public void setStatusPoints(String text){
		statusLabelPoints.setText(text);
	}
	
	public void setStatusBet(String text){
		statusLabelBet.setText(text);
	}
	
	public void setStatusBetType(String text){
		statusLabelBetType.setText(text);
	}
	
	public void setStatusWinLoosePoints(String text){
		statusLabelWinLoosePoints.setText(text);
	}
	
	public void setStatusCurrentPlayer(String text){
		statusLabelCurrentPlayer.setText(text);
	}
	
	public void setPlayerResultCoin1(String text){
		PlayerResultCoin1.setText(text);
	}
	
	public void setPlayerResultCoin2(String text){
		PlayerResultCoin2.setText(text);
	}
	
	public void setSpinnerResultCoin1(String text){
		SpinnerResultCoin1.setText(text);
	}
	
	public void setSpinnerResultCoin2(String text){
		SpinnerResultCoin2.setText(text);
	}
	
	public JLabel getPlayerResultCoin1(){
		return PlayerResultCoin1;
	}
	
	public JLabel getPlayerResultCoin2(){
		return PlayerResultCoin2;
	}
	
	public JLabel getSpinnerResultCoin1(){
		return SpinnerResultCoin1;
	}
	
	public JLabel getSpinnerResultCoin2(){
		return SpinnerResultCoin2;
	}
}