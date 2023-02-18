import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.*;

public class GUI {

	GameManager gm;
	JFrame window;
	public JButton testButton;
	public JButton testButton2;
	public JButton start;
	public JButton instructions;
	public JButton back;
	public JButton dContinue;
	public JButton restart;
	public JPanel bgPanel;
	public JLabel bgLabel;
	public JLabel mapLabel;
	public JLabel objectLabel;
	public JLabel charLabel;
	public String itemName;
	public boolean character = false;
	JTextArea gameText;
	
	public GUI() {
		createMainField();
		titlePage();
		window.setVisible(true);
	}
	
	public void createMainField() {
		window = new JFrame();
		window.setSize(1200,900);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Beyond These Walls");
		window.getContentPane().setBackground(Color.black);
		window.setLayout(null);
		
	}

	public void titlePage() {
		
		JPanel titlePanel;
		JLabel[] titleText;
		
		titlePanel = new JPanel();
		titlePanel.setBackground(Color.black);
		titlePanel.setBounds(0,0,window.getWidth(), window.getHeight());
		titlePanel.setLayout(null);
		window.add(titlePanel);
		
		titleText = new JLabel[2];
		titleText[0] = new JLabel("Beyond These Walls");
		titleText[0].setForeground(Color.white);
		titleText[0].setFont(new Font("Serif", Font.ITALIC, 80));
		titleText[0].setBounds(250,-150,1000,1000);
		titleText[1] = new JLabel("By Anna Jacobson");
		titleText[1].setForeground(Color.white);
		titleText[1].setFont(new Font("Serif", Font.ITALIC, 40));
		titleText[1].setBounds(425,-80,1000,1000);
		
		start = new JButton("START");
		start.setBackground(Color.black);
		start.setForeground(Color.white);
		start.setFont(new Font("Sans Serif", Font.PLAIN, 40));
		start.setBounds(475, 550, 200, 50);
		start.setBorderPainted(false);
		instructions = new JButton("HOW TO PLAY");
		instructions.setBackground(Color.black);
		instructions.setForeground(Color.white);
		instructions.setFont(new Font("Sans Serif", Font.PLAIN, 40));
		instructions.setBounds(400, 625, 350, 50);
		instructions.setBorderPainted(false);
		
		titlePanel.add(titleText[0]);
		titlePanel.add(titleText[1]);
		titlePanel.add(start);
		titlePanel.add(instructions);
	
	}
	
	public void createBGPanel() {
		
		bgPanel = new JPanel();
		bgPanel.setBackground(Color.black);
		bgPanel.setLayout(null);
		bgPanel.setBounds(10,10, 1170, 600);
		window.add(bgPanel);
	}
	
	public void createBG(String imageName) {

		dContinue = new JButton("Continue");
		dContinue.setBounds(940, 800, 200, 40);
		dContinue.setFont(new Font("San Serif", Font.PLAIN, 20));
		dContinue.setBackground(Color.black);
		dContinue.setForeground(Color.white);
		window.add(dContinue);
		
		inventory();
		
		gameText = new JTextArea("Sample Text");
		gameText.setBounds(10, 625, 1170, 250);
		gameText.setBackground(Color.black);
		gameText.setForeground(Color.white);
		gameText.setEditable(false);
		gameText.setLineWrap(true);
		gameText.setWrapStyleWord(true);
		gameText.setFont(new Font("Book Antiqua", Font.PLAIN, 30));
		window.add(gameText);
		
		
		bgLabel = new JLabel();
		ImageIcon bgIcon = new ImageIcon("assets/bg/" + imageName);
		bgLabel.setBounds(0, 50, bgIcon.getIconWidth(), bgIcon.getIconHeight());
		bgLabel.setIcon(bgIcon);
		bgPanel.add(bgLabel);
		
		
	}

	public void inventory() {
		
		JTextArea inv = new JTextArea("Inventory:");
		inv.setBounds(10,0,150,50);
		inv.setFont(new Font("Serif", Font.PLAIN, 30));
		inv.setBackground(Color.black);
		inv.setForeground(Color.white);
		inv.setEditable(false);
		bgPanel.add(inv);
	}
	
	public void createCharacter(String imageName, int x) {
		
		charLabel = new JLabel();
		ImageIcon charIcon = new ImageIcon("assets/character/" + imageName);
		charLabel.setBounds(x, 0, charIcon.getIconWidth(), charIcon.getIconHeight());
		charLabel.setIcon(charIcon);
		bgPanel.add(charLabel);
		
		character = true;
	}
	
	public void howToPlay() {
		
		JTextArea instructions = new JTextArea();
		JPanel iPanel = new JPanel();
		JLabel howToLabel = new JLabel("How to Play");
		back = new JButton("Back to Menu");
		
		iPanel.setBounds(0,0,window.getWidth(), window.getHeight());
		iPanel.setBackground(Color.black);
		iPanel.setLayout(null);
		window.add(iPanel);
		
		howToLabel.setBackground(Color.black);
		howToLabel.setForeground(Color.white);
		howToLabel.setFont(new Font("Serif", Font.ITALIC, 40));
		howToLabel.setBounds(25, 10, 300, 50);
		
		instructions.setLineWrap(true);
		instructions.setWrapStyleWord(true);
		instructions.setBackground(Color.black);
		instructions.setForeground(Color.white);
		instructions.setFont(new Font("Serif", Font.PLAIN, 20));
		instructions.setText("1. Dialogue will appear on the screen. Click continue to read the next line."
				+ "\n2. To toggle between different locations in a scene, click the buttons on the left and "
				+ "right side of the screen, when available."
				+ "\n3. To pick up objects, click on them to add them to your inventory."
				+ "\n4. To use an object, click on it in your inventory at the top of the screen and click on the place"
				+ " you want to use it.");
		instructions.setBounds(25,75, iPanel.getWidth(), iPanel.getHeight()-200);
		instructions.setEditable(false);
		
		back.setBackground(Color.black);
		back.setForeground(Color.white);
		back.setFont(new Font("San Serif", Font.PLAIN, 40));
		back.setBounds(25, iPanel.getHeight()-100, 300, 50);
		back.setBorderPainted(false);
		
		iPanel.add(howToLabel);
		iPanel.add(instructions);
		iPanel.add(back);
		
	}
	
	public void createObject(String imageName, int x, int y) {
		
		objectLabel = new JLabel();
		
		ImageIcon objectIcon = new ImageIcon("assets/items/" + imageName);
		objectLabel.setBounds(x,y,objectIcon.getIconWidth(), objectIcon.getIconWidth());
		objectLabel.setIcon(objectIcon);
		
		itemName = imageName;
		
		bgPanel.add(objectLabel);
	
	}
	
	public void removeObject() {
		bgPanel.remove(objectLabel);
	}
	
	public void showMap() {
		mapLabel = new JLabel();
		ImageIcon mapIcon = new ImageIcon("assets/items/map full.png");
		mapLabel.setBounds(300,60,mapIcon.getIconWidth(),mapIcon.getIconHeight());
		mapLabel.setIcon(mapIcon);
		
		bgPanel.remove(bgLabel);
		bgPanel.add(mapLabel);
		bgPanel.add(bgLabel);
		window.repaint();
		window.revalidate();
		
	}
	
	public void loseScreen(String cause) {
		JPanel losePanel = new JPanel();
		losePanel.setBounds(0,0,window.getWidth(), window.getHeight());
		losePanel.setBackground(Color.black);
		losePanel.setLayout(null);
		window.add(losePanel);
		
		JTextArea caught = new JTextArea("You've been caught. " + cause);
		caught.setBackground(Color.black);
		caught.setForeground(Color.white);
		caught.setFont(new Font("Serif", Font.PLAIN, 50));
		caught.setBounds(100, 300, 1000, 200);
		caught.setEditable(false);
		caught.setWrapStyleWord(true);
		caught.setLineWrap(true);
		losePanel.add(caught);
		
		restart = new JButton("Try again");
		restart.setBackground(Color.black);
		restart.setForeground(Color.white);
		restart.setFont(new Font("San Serif", Font.PLAIN, 30));
		restart.setBounds(475,600,200,50);
		losePanel.add(restart);
		
	}
	
	public void winScreen() {
		JPanel winPanel = new JPanel();
		winPanel.setBounds(0,0,window.getWidth(), window.getHeight());
		winPanel.setBackground(Color.black);
		winPanel.setLayout(null);
		window.add(winPanel);
		
		JTextArea win = new JTextArea("You won.");
		win.setBackground(Color.black);
		win.setForeground(Color.white);
		win.setFont(new Font("Serif", Font.PLAIN, 50));
		win.setBounds(100, 350, 1000, 70);
		win.setEditable(false);
		winPanel.add(win);
		
		restart = new JButton("Play again");
		restart.setBackground(Color.black);
		restart.setForeground(Color.white);
		restart.setFont(new Font("San Serif", Font.PLAIN, 30));
		restart.setBounds(475,600,200,50);
		winPanel.add(restart);
		
	}
	
	public void clearFrame() {
		window.getContentPane().removeAll();
	}
	
	public void drawElements() {
		window.repaint();
		window.revalidate();
	}

}
