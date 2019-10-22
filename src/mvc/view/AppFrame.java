package mvc.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Set;
import javax.swing.JFrame;

import model.interfaces.GameEngine;
import model.interfaces.Player;

public class AppFrame extends JFrame{
	
	Set<Thread> activeThread = Thread.getAllStackTraces().keySet();
	
	ComboBox comboBox;
	Statusbar statusBar;
	CoinPanel coinPanel;
	DropdownMenu dropdownMenu;
	Toolbar toolBar;
	Player player;
	
	public AppFrame(GameEngine model)
	{	
		//Passing frame and model into the components 
		statusBar = new Statusbar(this, model);
		coinPanel = new CoinPanel(this, model);
		dropdownMenu = new DropdownMenu(this, model);
		this.addComponentListener(coinPanel);
		
		//Set dimension of the frame and set layout
		setBounds(100, 100, 825, 550);
		setLayout(new BorderLayout());
		
		//Add the components
		add(dropdownMenu, BorderLayout.NORTH);
		add(coinPanel, BorderLayout.CENTER);
		add(statusBar, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Set minimum allowed size of frame
		setMinimumSize(new Dimension(650, 425));
		setVisible(true);
		
	}
	
	public boolean checkIfThreadExist(String name){
		updateThreads();
		for (Thread thread: activeThread) {
			 if(thread.getName().equals(name)){
				 return true;
			 }
		}
		return false;
	}
	
	private void updateThreads(){
		activeThread = Thread.getAllStackTraces().keySet();
	}
	public Statusbar getstatusBar(){
		return statusBar;
	}
	public DropdownMenu getdropdownMenu() {
		return dropdownMenu;
	}
	public CoinPanel getCoinPanel() {
		return coinPanel;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Player getPlayer() {
		return player;
	}
	
}
