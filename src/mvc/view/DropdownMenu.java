package mvc.view;

import java.awt.FlowLayout;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import model.interfaces.GameEngine;
import mvc.controller.DropdownMenuController;
import mvc.view.Toolbar;

 
public class DropdownMenu extends JMenuBar{

	private Toolbar button;
	DropdownMenuController controller;
	
	public DropdownMenu(AppFrame view, GameEngine model) {
		
		//Passing frame and model into the controller
		controller = new DropdownMenuController(view, model);
		setVisible(true);		
		
		//Creates new JMenu
		JMenu optionsMenu = new JMenu("Options");
		
		//Creates the JMenuItem in the JMenu
		JMenuItem addPlayer = new JMenuItem("Add Player");
		addPlayer.setName("addplayer");
		JMenuItem removePlayer = new JMenuItem("Remove Player");
		removePlayer.setName("removeplayer");
		JMenuItem quit = new JMenuItem("Quit");
		quit.setName("quit");
		
		addPlayer = optionsMenu.add(addPlayer);
		removePlayer = optionsMenu.add(removePlayer);
		
		optionsMenu.addSeparator();
		
		quit = optionsMenu.add(quit);
		
		add(optionsMenu);
		
		//Adds the button from Toolbar class DropdownMenu layout
		button = new Toolbar(view, model);
		add(button);
		
		//Adds the controller into the JMenu
		addPlayer.addActionListener(controller);
		removePlayer.addActionListener(controller);
		quit.addActionListener(controller);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		
	}
	public Toolbar gettoolBar() {
		return button;
	}
}
