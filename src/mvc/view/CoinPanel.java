package mvc.view;

import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.enumeration.CoinFace;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class CoinPanel extends JPanel implements ComponentListener{
	private JLabel label1, label2;
	private ImageIcon image, image1;
	private AppFrame view;
	private GameEngine model;
	private int coinWidth = 250, coinHeight = 250;
	GridBagConstraints gbc = new GridBagConstraints();
	
	@Override
	//Component listener to get AppFrame current size
	public void componentResized(ComponentEvent e) {
		final int widthScale = 525;
		final int heightScale = 290;
		
		try {
			//Grabs the AppFrame current width and height deduct it by the scales to get coin height and width
			coinWidth = e.getComponent().getWidth() - widthScale;
			coinHeight = e.getComponent().getHeight() - heightScale;
			
			if(!view.checkIfThreadExist("spin")){
				label1.setIcon(resizeImage("img/heads.png", coinWidth, coinHeight));
				label2.setIcon(resizeImage("img/tails.png", coinWidth, coinHeight));
			}
			else {
				
			}
			
		}
		catch (Exception e1) {
			
		}
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	public CoinPanel(AppFrame view, GameEngine model) {
		this.view = view;
		this.model = model;
		
		//Set the layout for the CoinPanel
		setLayout(new GridLayout(0, 2));
		
		//Create new JLabel to add the coin1 
		label1 = new JLabel();
		label1.setIcon(resizeImage("img/heads.png", coinWidth, coinHeight));
		label1.setHorizontalAlignment(label1.CENTER);
		add(label1);
		
		//Create new JLabel to add the coin2
		label2 = new JLabel();
		label2.setIcon(resizeImage("img/tails.png", coinWidth, coinHeight));
		label2.setHorizontalAlignment(label2.CENTER);
		add(label2);
		
	}
	
	public ImageIcon resizeImage(String storedImage, int coinWidth, int coinHeight){
		//Creates a new ImageIcon with the given image location and current width, height
		image1 = new ImageIcon(storedImage);
		
		BufferedImage resizedImg = new BufferedImage(coinWidth, coinHeight, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();
	    image = new ImageIcon(resizedImg);
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(image1.getImage(), 0, 0, coinWidth, coinHeight, null);
	    g2.dispose();
	    
		return image;
	}
	
	private void updateCoinPlayerResult(CoinFace face, String coinNumber, JLabel label, JLabel resultPlayer){
		//Sets the coins result for the current player passing the coin face and number
		//and setting the label and status for the coin1 and coin2 
		switch(face){
		case HEADS:
			label.setIcon(resizeImage("img/heads.png", coinWidth, coinHeight));
			resultPlayer.setText("Player coin " + coinNumber + " result: " + face);
			break;
		case TAILS:
			label.setIcon(resizeImage("img/tails.png", coinWidth, coinHeight));
			resultPlayer.setText("Player coin "+ coinNumber + " result: " + face);
			break;
		default:
			break;
		}	
	}
	
	private void updateCoinSpinnerResult(CoinFace face, String coinNumber, JLabel label, JLabel resultSpinner){
		//Sets the coins result for the spinner passing the coin face and number
		//and setting the label and status for the coin1 and coin2 
		view.getstatusBar().setStatusCurrentPlayer("Spinner");
		switch(face){
		case HEADS:
			label.setIcon(resizeImage("img/heads.png", coinWidth, coinHeight));
			resultSpinner.setText("Spinner coin " + coinNumber + " result: " + face);
			break;
		case TAILS:
			label.setIcon(resizeImage("img/tails.png", coinWidth, coinHeight));
			resultSpinner.setText("Spinner coin "+ coinNumber + " result: " + face);
			break;
		default:
			break;
		}	
	}
	
	private void updateCoinSpinning(Player player, CoinFace face, String coinNumber, JLabel label, JLabel result){
		//Updates the coins when spinning passing the coin face and number
		//and setting the label and status for the coin1 and coin2 
		view.getstatusBar().setStatusCurrentPlayer(view.getPlayer().getPlayerName());
		switch(face){
		case HEADS:
			label.setIcon(resizeImage("img/heads.png", coinWidth, coinHeight));
			result.setText("Player coin " + coinNumber + " result: " + face);
			break;
		case TAILS:
			label.setIcon(resizeImage("img/tails.png", coinWidth, coinHeight));
			result.setText("Player coin "+ coinNumber + " result: " + face);
			break;
		default:
			break;
		}	
	}

	public void updateCoinResult(CoinFace coin1, CoinFace coin2, Player player, String playerID){
		//Coordinates which coin is being updated and updates the correct label 
		if(player == null || player.getPlayerId() == playerID){
			updateCoinPlayerResult(coin1, "1", label1, view.getstatusBar().getPlayerResultCoin1());
			updateCoinPlayerResult(coin2, "2", label2, view.getstatusBar().getPlayerResultCoin2());
		}
	}
	
	public void flippingCoin1Player(Player player, CoinFace coin1) {
		//Updates the coin face for coin1 when spinning for current player
		if(player == view.getPlayer()) {
			updateCoinSpinning(player, coin1, "1", label1, view.getstatusBar().getPlayerResultCoin1());
		}

	}
	
	public void flippingCoin2Player(Player player, CoinFace coin2) {
		//Updates the coin face for coin2 when spinning for current player
		if(player == view.getPlayer()) {
			updateCoinSpinning(player, coin2, "2", label2, view.getstatusBar().getPlayerResultCoin2());
		}
	}

	public void flippingCoin1Spinner(CoinFace face) {
		//Updates the coin face for coin1 when spinning for spinner
		updateCoinSpinnerResult(face, "1", label1,  view.getstatusBar().getSpinnerResultCoin1());
		
	}

	public void flippingCoin2Spinner(CoinFace face) {
		//Updates the coin face for coin1 when spinning for spinner
		updateCoinSpinnerResult(face, "2", label2, view.getstatusBar().getSpinnerResultCoin2());
		
	}
	
}
