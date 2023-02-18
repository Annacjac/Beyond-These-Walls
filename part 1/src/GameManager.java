import java.awt.event.*;

public class GameManager extends GUI implements ActionListener, MouseListener{

	Scenes s;
	Dialogue d;
	Inventory key = new Inventory(this);
	Inventory lantern = new Inventory(this);
	Inventory map = new Inventory(this);
	boolean hasKey = false;
	boolean houseUnlocked = false;
	boolean hasMap = false;
	boolean showMap = false;
	boolean hasLantern = false;
	boolean lanternOn = false;
	boolean hide = false;
	boolean hideLanternOn = false;
	boolean run = false;
	int lanternClickCount = 0;
	int mapClickCount = 0;
	int clickCount = 0;
	int num = (int)(Math.random()*4) + 1;
	
	
	public static void main(String[] args) {

		new GameManager();
		
	}
	
	public GameManager() {
		s = new Scenes(this);
		d = new Dialogue(this);
		buttons();
	}
	
	public void buttons() {
		start.addActionListener(this);
		instructions.addActionListener(this);
		window.addMouseListener(this);
	}

	public void actionPerformed(ActionEvent e) {

		String button = e.getActionCommand();
		if(button.equals("START")) {
			clearFrame();
			s.villageDay();
			d.readLines();
			d.writeText(d.scene1Lines[0]);
			drawElements();
			//s.right.addActionListener(this);
			dContinue.addActionListener(this);
			
		}
		if(button.equalsIgnoreCase("Continue")) {
			//runs through scene 1 lines
			if(d.getLineCounter() < d.scene1Lines.length && s.currentScene.equals("Village Day")) {
				d.writeText(d.scene1Lines[d.getLineCounter()]);
				//draws Amy on the screen at a certain time
				if(d.getLineCounter() == 7) {
					window.getContentPane().remove(bgLabel);
					createCharacter("happy.png", 0);
					bgPanel.add(bgLabel);
					drawElements();
				}
				else if(d.getLineCounter() == 9) {
					window.getContentPane().remove(bgLabel);
					bgPanel.remove(charLabel);
					createCharacter("normal.png", 0);
					bgPanel.add(bgLabel);
					drawElements();
				}
				else if(d.getLineCounter() == 22) {
					window.getContentPane().remove(bgLabel);
					bgPanel.remove(charLabel);
					createCharacter("scared.png", 0);
					bgPanel.add(bgLabel);
					drawElements();
				}
				else if(d.getLineCounter() == 24) {
					window.getContentPane().remove(bgLabel);
					bgPanel.remove(charLabel);
					createCharacter("normal.png", 0);
					bgPanel.add(bgLabel);
					drawElements();
				}
				
			}
			//changes to scene 2 after scene 1 is complete
			else if(d.getLineCounter() >= d.scene1Lines.length && s.currentScene.equals("Village Day")){
				d.resetCounter();
				clearFrame();
				s.bedroom();
				drawElements();
				dContinue.addActionListener(this);
			}
			//runs through scene 2 lines
			if(d.getLineCounter() < d.scene2Lines.length && s.currentScene.equals("Bedroom")) {
				d.writeText(d.scene2Lines[d.getLineCounter()]);
			}
			//changes to scene 3 after scene 2 is complete
			else if(d.getLineCounter() >= d.scene2Lines.length && s.currentScene.equals("Bedroom")) {
				d.resetCounter();
				clearFrame();
				s.villageNight3();
				drawElements();
				s.right.setEnabled(false);
				dContinue.addActionListener(this);
			}
			//runs through scene 3 lines
			if(d.getLineCounter() < d.scene3Lines.length && s.currentScene.equals("Village Night 2") && s.sceneNumber == 3) {
				d.writeText(d.scene3Lines[d.getLineCounter()]);
				//draws Amy on the screen at a certain time
				if(d.getLineCounter() == 2) {
					window.getContentPane().remove(bgLabel);
					createCharacter("normal.png",0);
					bgPanel.add(bgLabel);
					drawElements();
					
				}
				
			}
			//enables the buttons to toggle between different locations after lines have been run through
			else if(d.getLineCounter() >= d.scene3Lines.length && s.sceneNumber == 3){
				s.right.setEnabled(true);
				s.right.addActionListener(this);
				dContinue.setEnabled(false);
			}
			//run through scene 4 lines
			if(d.getLineCounter() > 0 && d.getLineCounter() < d.scene4Lines.length && s.currentScene.equals("Village Night 1") && s.sceneNumber == 4) {
				d.writeText(d.scene4Lines[d.getLineCounter()]);
				if(d.getLineCounter() == 2) {
					dContinue.setEnabled((false));
					d.setLineCounter(2);
				}
				else if(d.getLineCounter() == 3) {
					window.getContentPane().remove(bgLabel);
					key.addInventory("key.png", 1);
					key.itemButton.addActionListener(this);
					bgPanel.add(bgLabel);
					drawElements();
					d.resetCounter();
				}
			}
			
			//takes player into abandoned house if they used the key to unlock it.
			if(houseUnlocked && s.currentScene.equals("Village Night 1")) {
				d.resetCounter();
				clearFrame();
				s.house();
				window.getContentPane().remove(bgLabel);
				createCharacter("normal.png", 0);
				createObject("map.png", 600, 535);
				key.addInventory("key.png", 1);
				bgPanel.add(bgLabel);
				drawElements();
				dContinue.addActionListener(this);
				System.out.println("WORKING");
				
			}
			//runs through scene 5 lines
			if(d.getLineCounter() < d.scene5Lines.length && s.currentScene.equals("House")) {
				d.writeText(d.scene5Lines[d.getLineCounter()]);
				character = true;
				if(d.getLineCounter()==3) {
					dContinue.setEnabled(false);
				}
				else if(d.getLineCounter() == d.scene5Lines.length) {
					d.setLineCounter(8);
				}
			}
			
			//allows the player to leave if they've obtained the map.
			if(d.getLineCounter() > d.scene5Lines.length && hasMap && s.currentScene.equals("House")) {
				d.resetCounter();
				clearFrame();
				s.villageNight1();
				window.getContentPane().remove(bgLabel);
				map.addInventory("map.png",2);
				key.addInventory("key.png", 1);
				d.writeText("Looks like you have everything you need here. Time to go back to the main square.");
				bgPanel.add(bgLabel);
				drawElements();
				s.left.addActionListener(this);
				map.itemButton.addActionListener(this);
				character = false;
			}
			//runs through scene 6 lines
			if(d.getLineCounter() < d.scene6Lines.length && s.currentScene.equals("Village Night 2") && hasMap) {
				d.writeText(d.scene6Lines[d.getLineCounter()]);
				map.itemButton.addActionListener(this);
				if(d.getLineCounter() == 6) {
					window.getContentPane().remove(bgLabel);
					lantern.addInventory("lantern.png", 3);
					createCharacter("Normal.png", 0);
					bgPanel.add(bgLabel);
					drawElements();
				}
				character = true;
			}
			//takes the player to the forest after the conversation in the village
			if(hasMap && s.currentScene.equals("Village Night 2") && d.getLineCounter() == d.scene6Lines.length) {
				d.resetCounter();
				clearFrame();
				s.forest();
				bgPanel.remove(bgLabel);
				key.addInventory("key.png", 1);
				map.addInventory("map.png", 2);
				lantern.addInventory("lantern.png", 3);
				d.writeText(d.scene7Lines[d.getLineCounter()]);
				drawElements();
				dContinue.addActionListener(this);
				map.itemButton.addActionListener(this);
				lantern.itemButton.addActionListener(this);
				dContinue.setEnabled(false);
			}
			//runs through scene 7 lines
			if(s.currentScene.equals("Forest") && d.getLineCounter() < 13 && !hide && !run) {
				if(lanternOn) {
					d.writeText(d.scene7Lines[d.getLineCounter()]);
					if(d.getLineCounter() == 5 || d.getLineCounter() == 12) {
						window.getContentPane().remove(bgLabel);
						bgPanel.remove(charLabel);
						createCharacter("sad.png",0);
						bgPanel.add(bgLabel);
						drawElements();
					}
					else if(d.getLineCounter() == 9) {
						window.getContentPane().remove(bgLabel);
						bgPanel.remove(charLabel);
						createCharacter("normal.png",0);
						bgPanel.add(bgLabel);
						drawElements();
					}
					else if(d.getLineCounter() == 11) {
						window.getContentPane().remove(bgLabel);
						bgPanel.remove(charLabel);
						createCharacter("scared.png",0);
						bgPanel.add(bgLabel);
						drawElements();
					}
					if(d.getLineCounter() == 13) {
						window.getContentPane().remove(bgLabel);
						bgPanel.remove(charLabel);
						bgPanel.add(s.right);
						s.right.addActionListener(this);
						bgPanel.add(s.left);
						s.left.addActionListener(this);
						createCharacter("sad.png",0);
						bgPanel.add(bgLabel);
						drawElements();
						dContinue.setEnabled(false);
					}
				}
				else if(!lanternOn && d.getLineCounter() != 13) {
					d.writeText("Lantern is off. You cannot see where you're going. Click the lantern in your inventory to turn it on.");
				}
				
			}
			
			if(s.currentScene.equals("Tree") && d.getLineCounter() < d.scene8aLines.length && (hide || hideLanternOn)) {
				d.writeText(d.scene8aLines[d.getLineCounter()]);
				
				if((num == 2 && d.getLineCounter() == 2 && hide) || (d.getLineCounter() == 2 && hideLanternOn)) {
					d.setLineCounter(6);
				}
				//Random 1 out of 4 chance of getting caught
				else if((num == 2 && d.getLineCounter() == 9 && hide) || (d.getLineCounter() == 9 && hideLanternOn)) {
					clearFrame();
					if(!lanternOn)
						s.lose("Since you couldn't see where you were, your spot ended up being in plain sight.");
					else if(lanternOn)
						s.lose("You didn't turn off your lantern, so you completely gave your hiding spot away.");
					drawElements();
					restart.addActionListener(this);
				}
				else if(num != 2 && d.getLineCounter() == 4 && hide) {
					dContinue.setEnabled(false);
					map.itemButton.setEnabled(true);
					lantern.itemButton.setEnabled(true);
				}
				else if(num != 2 && d.getLineCounter() == 5 && hide) {
					window.getContentPane().remove(bgLabel);
					createCharacter("sad.png", 500);
					bgPanel.add(bgLabel);
					drawElements();
				}
				else if(num != 2 && d.getLineCounter() == 6 && hide){
					dContinue.setEnabled(false);
				}
			}
			else if(s.currentScene.equals("Forest") && d.getLineCounter() < d.scene8bLines.length && run) {
				d.writeText(d.scene8bLines[d.getLineCounter()]);
			}
			
			System.out.println("Line number: " + d.getLineCounter());
			System.out.println("Scene number: " + s.sceneNumber);
			
		}
		//goes to the instructions page
		if(button.equalsIgnoreCase("HOW TO PLAY")) {
			clearFrame();
			howToPlay();
			drawElements();
			back.addActionListener(this);
		}
		//goes to the title page
		if(button.equalsIgnoreCase("BACK TO MENU")) {
			clearFrame();
			titlePage();
			drawElements();
			start.addActionListener(this);
			instructions.addActionListener(this);
		}
		//toggles to the abandoned house village section
		if(button.equalsIgnoreCase("Abandoned House >")) {
			clearFrame();
			d.setLineCounter(0);
			s.villageNight1();
			if(!hasMap && d.getLineCounter() == 0) {
				System.out.println("Scene Number: " +s.sceneNumber);
				System.out.println("Line Number: " +d.getLineCounter());
				dContinue.setEnabled(false);
				window.getContentPane().remove(bgLabel);
				d.writeText(d.scene4Lines[0]);
				createCharacter("normal.png", 500);
				bgPanel.add(bgLabel);
				drawElements();
				//dContinue.setEnabled(false);
				s.left.setEnabled(false);
				
			}
			/*else {
				d.writeText("Seems like you've found what you need here. Time to go back to the main square.");
				s.left.setEnabled(true);
			}
			window.getContentPane().remove(bgLabel);
			/*if(hasMap) {
				map.addInventory("map.png", 2);
				map.itemButton.addActionListener(this);
			}
			if(hasKey) {
				key.addInventory("key.png", 1);
				key.itemButton.addActionListener(this);
			}
			if(hasLantern) {
				lantern.addInventory("lantern.png", 3);
			}*/
			bgPanel.add(bgLabel);
			drawElements();
			//s.left.addActionListener(this);
			dContinue.addActionListener(this);
			dContinue.setEnabled(true);
		}
		//toggles to the village main square section
		if(button.equalsIgnoreCase("< Main Square")) {
			clearFrame();
			s.villageNight3();
			window.getContentPane().remove(bgLabel);
			if(hasMap && s.sceneNumber == 7) {
				map.addInventory("map.png", 2);
				key.addInventory("key.png", 1);
				window.getContentPane().remove(bgLabel);
				createCharacter("normal.png",0);
				d.writeText(d.scene6Lines[0]);
				bgPanel.add(bgLabel);
				System.out.println("working");
				System.out.println(s.sceneNumber);	
				s.right.setEnabled(false);
			}
			bgPanel.add(bgLabel);
			drawElements();
			s.right.addActionListener(this);
			//key.itemButton.addActionListener(this);
			//map.itemButton.addActionListener(this);
			dContinue.addActionListener(this);
		}
		if(button.equals("Run")) {
			run = true;
			bgPanel.remove(s.right);
			bgPanel.remove(s.left);
			d.writeText(d.scene7Lines[15]);
			dContinue.setEnabled(true);
			d.resetCounter();
		}
		
		if(button.equals("Hide")) {
			
			clearFrame();
			s.tree();
			key.addInventory("key.png", 1);
			map.addInventory("map.png", 2);
			map.itemButton.addActionListener(this);
			lantern.addInventory("lantern.png", 3);
			lantern.itemButton.addActionListener(this);
			dContinue.addActionListener(this);
			bgPanel.remove(s.right);
			bgPanel.remove(s.left);
			d.resetCounter();
			if(!lanternOn) {
				hide = true;
				bgPanel.remove(bgLabel);
				bgPanel.remove(charLabel);
				d.writeText(d.scene7Lines[13]);
				map.itemButton.setEnabled(false);
				lantern.itemButton.setEnabled(false);
				drawElements();
				dContinue.setEnabled(false);
			}
			else if(lanternOn) {
				hideLanternOn = true;
				bgPanel.remove(bgLabel);
				createCharacter("sad.png", 500);
				bgPanel.add(bgLabel);
				d.writeText(d.scene7Lines[14]);
				drawElements();
			}
		}
		//takes player back to title screen and resets game if player loses
		if(button.equalsIgnoreCase("Try Again") || button.equals("Play Again")) {
			clearFrame();
			titlePage();
			drawElements();
			start.addActionListener(this);
			instructions.addActionListener(this);
			
		}
		//sets the key's use to true so the player can unlock the door
		if(button.equals("1")) {
			key.setUse(true);
		}
		
		if(button.equals("2")) {
			mapClickCount++;
			if(mapClickCount%2 == 1) {
				window.getContentPane().remove(bgLabel);
				if(character == true) {
					bgPanel.remove(charLabel);
				}
				showMap();
				if(character == true) {
					bgPanel.add(charLabel);
				}
				bgPanel.add(bgLabel);
				drawElements();
				showMap = true;
				System.out.println("Map shown.");
			}
			else {
				bgPanel.remove(mapLabel);
				showMap = false;
				drawElements();
			}
		}
		if(button.equals("3")) {
			lanternClickCount++;
			if(lanternClickCount%2 == 1) {
				bgPanel.add(bgLabel);
				d.writeText("Lantern is on! You can now see.");
				drawElements();
				lanternOn = true;
				dContinue.setEnabled(true);
				d.setLineCounter(d.getLineCounter()-1);
			
			}
			else {
				bgPanel.remove(bgLabel);
				bgPanel.remove(charLabel);
				drawElements();
				lanternOn = false;
				dContinue.setEnabled(false);
			}
		}
		
	}

	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getX() + ", " + e.getY());
		//enables the player to enter the house after unlocking the door
		if(key.isInUse()==true && s.currentScene.equals("Village Night 1")) {
			if(e.getX() > 346 && e.getY() > 210 && e.getX() < 548 && e.getY() < 570) {
				d.writeText("House unlocked! Click continue if you're ready to go inside.");
				houseUnlocked = true;
				key.setUse(false);
			}
		}
		//prevents the player from entering the house if they haven't unlocked it.
		else if(key.isInUse()==false && s.currentScene.equals("Village Night 1")) {
			if(e.getX() > 346 && e.getY() > 210 && e.getX() < 548 && e.getY() < 570) {
				d.writeText("The house is locked.");
				dContinue.addActionListener(this);
				dContinue.setEnabled(true);
				d.setLineCounter(2);
			}
			System.out.println("Scene Number: " +s.sceneNumber);
			System.out.println("Line Number: " +d.getLineCounter());
		}
		//adds map to inventory after clicking on it.
		if(s.currentScene.equalsIgnoreCase("house") && e.getX() > 617 && e.getY() > 563 && e.getX() < 668 && e.getY() < 604 && d.getLineCounter() == 3) {
			removeObject();
			drawElements();
			map.addInventory("map.png", 2);
			map.itemButton.addActionListener(this);
			hasMap = true;
			dContinue.setEnabled(true);
		}
		
		if(showMap == true) {
			bgPanel.remove(mapLabel);
			showMap = false;
			drawElements();
		}
		
		if(!lanternOn && hide) {
				d.writeText("You feel around until you find what feels like a tree and crouch behind it.");
				System.out.println(num);
				dContinue.setEnabled(true);
				d.resetCounter();
				s.currentScene = "Tree";
		}
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
