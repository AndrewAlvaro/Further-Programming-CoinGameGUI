package mvc.model;

import javax.swing.SwingUtilities;

import model.GameEngineImpl;
import model.interfaces.Coin;
import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import mvc.view.AppFrame;
import view.GameEngineCallbackImpl;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback{
	private static GameEngine model = new GameEngineImpl();
	private AppFrame view;
	
	
	public GameEngineCallbackGUI(final GameEngine model)
	{
		
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				
				view = new AppFrame(model);
			}
		});
		
	}
	
	public static void run()
	{
		//Add the game engine callbackImpl and callbackGUI
		model.addGameEngineCallback(new GameEngineCallbackImpl());
		model.addGameEngineCallback(new GameEngineCallbackGUI(model));
	}
	
	@Override
	public void playerCoinUpdate(Player player, Coin coin, GameEngine model) {
		//Method to flip player coin when spinning
		if(view != null) {
			if(coin.getNumber() == 1) {
				view.getCoinPanel().flippingCoin1Player(player, coin.getFace());
			}
			else if(coin.getNumber() == 2){
				view.getCoinPanel().flippingCoin2Player(player, coin.getFace());
			}
			else {
				
			}
		}
	}
	
	@Override
	public void spinnerCoinUpdate(Coin coin, GameEngine engine) {
		//Method to flip spinner coin when spinning
		if(view != null) {
			if(coin.getNumber() == 1) {
				view.getCoinPanel().flippingCoin1Spinner(coin.getFace());
			}
			else if(coin.getNumber() == 2){
				view.getCoinPanel().flippingCoin2Spinner(coin.getFace());
			}
			else {
				
			}
			
		}
	}

	@Override
	public void playerResult(Player player, CoinPair coinPair, GameEngine engine) {
		// TODO Auto-generated method stub
	}

	@Override
	public void spinnerResult(CoinPair coinPair, GameEngine engine) {
		// TODO Auto-generated method stub
		
	}
}

