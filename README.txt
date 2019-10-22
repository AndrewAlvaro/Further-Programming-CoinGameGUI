---README---
*TEST with one player first and then with multiple players*
*BUILD path before running game from CoinGameGUI to CoinGame in Eclipse refer to: 
https://www.tutorialspoint.com/eclipse/eclipse_java_build_path.html*

1. Run program via GUIClient.java in /src/mcv/client/GUIClient.java

2. Select options and click Add player to add player
	- Add player rules 
	- name is a string accepts alphanumeric
	- Initial points cannot be greater than or be 0 
	- If adding another player with same name game assumes it is same player and 		  overrides the current player points with new one

3. Select options and click Remove player to remove player
	- Remove player rules
	- To remove player, player must exists in the game enter player name exactly if 	  there is spaces include spaces but it in not case sensitive

4. After add player, player able to bet or add more player. 
	- If betting, bet must be greater than 0 
	- Player must select bet type given in the combobox selection from "Coin 1, Coin 2 	  or both

5. Once player places bet the spin and cancel/remove bet button become available to only users who have placed a bet. Status bar updates the current bet of the player

6. If player remove bet the bet clears for that player and the bet type to no bet.

7. When spin button is pressed the player who has placed a bet will only spin. If multiple player places a bet the spin order will be from the first to last player added and after that the spinner will spin. Only display the player spinning while currently selected not hat player. Once a player finish spinning the result of their coin 1 and coin 2 will be displayed in the text, after all players finish spinning the spinner will spin and the result of the spinner coin 1 and coin 2 will be displayer in text also. The current player that is spinning is also displayed.

8. After spinning if currently on a player after the spinner finishes spinning and calculate result the Winning/Loosing Bet status bar will update. But when changing to different player the status bar for Winning/Loosing Bet will reset to 0.

9.If player after spin points reaches 0 or negative int they will be automatically be removed from the game.

10. Results calculated after end of all spin.
