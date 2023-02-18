import java.awt.*;

import javax.swing.*;

public class Scenes extends JFrame{

	GameManager gm;
	public JButton right;
	public JButton left;
	public String currentScene;
	public int sceneNumber = 0;
	
	public Scenes(GameManager gm) {
		this.gm = gm;
	}
	
	public void villageDay() {
		sceneNumber++;
		
		gm.createBGPanel();
		gm.createBG("VillageBG3.jpg");
		
		currentScene = "Village Day";
	}
	
	public void villageNight1() {
		sceneNumber++;
		
		gm.createBGPanel();
		left = new JButton("< Main Square");
		left.setBounds(20, 550, 200, 40);
		left.setBackground(Color.black);
		left.setForeground(Color.white);
		left.setFont(new Font("San Serif", Font.PLAIN, 20));
		gm.bgPanel.add(left);
		gm.createBG("VillageBG1.jpg");
		
		currentScene = "Village Night 1";
	}
	
	public void villageNight3() {
		sceneNumber++;
		
		gm.createBGPanel();
		right = new JButton("Abandoned House >");
		right.setBounds(900, 550, 250, 40);
		right.setBackground(Color.black);
		right.setForeground(Color.white);
		right.setFont(new Font("San Serif", Font.PLAIN, 20));
		gm.bgPanel.add(right);
		gm.createBG("VillageBG4.jpg");
		
		currentScene = "Village Night 2";
	}
	
	public void house() {
		sceneNumber++;
		
		gm.createBGPanel();
		gm.createBG("HouseBG1.jpg");
		
		currentScene = "House";
	}
	
	public void bedroom() {
		sceneNumber++;
		
		gm.createBGPanel();
		gm.createBG("BedroomBG.jpg");
		
		currentScene = "Bedroom";
	}
	
	public void forest() {
		sceneNumber++;
		
		gm.createBGPanel();
		left = new JButton("Hide");
		left.setBounds(20, 550, 200, 40);
		left.setBackground(Color.black);
		left.setForeground(Color.white);
		left.setFont(new Font("San Serif", Font.PLAIN, 20));
		right = new JButton("Run");
		right.setBounds(900, 550, 250, 40);
		right.setBackground(Color.black);
		right.setForeground(Color.white);
		right.setFont(new Font("San Serif", Font.PLAIN, 20));
		gm.createBG("ForestBG1.jpg");
		
		currentScene = "Forest";
	}

	public void tree() {
		sceneNumber++;
		
		gm.createBGPanel();
		gm.createBG("ForestBG2.jpg");
		
		currentScene = "Tree";
	}
	
	public void land() {
		sceneNumber++;
		
		gm.createBGPanel();
		gm.createBG("LandBG1.jpg");
		
		currentScene = "Land";
	}
	
	public void river() {
		sceneNumber++;
		
		gm.createBGPanel();
		gm.createBG("RiverBG1.jpg");
		
		currentScene = "River";
	}
	
	public void wall() {
		sceneNumber++;
		
		gm.createBGPanel();
		gm.createBG("WallBG1.jpg");
		
		currentScene = "Wall";
	}
	
	public void ending() {
		sceneNumber++;
		
		gm.createBGPanel();
		gm.createBG("end.jpg");
		
		currentScene = "End";
	}
	
	public void winScreen() {
		gm.winScreen();
		gm.d.resetCounter();
		gm.hasKey = false;
		gm.houseUnlocked = false;
		gm.hasMap = false;
		gm.hasLantern = false;
		gm.lanternOn = false;
		gm.hide = false;
		gm.run = false;
		gm.lanternClickCount = 0;
		gm.mapClickCount = 0;
		gm.clickCount = 0;
		sceneNumber = 0;
		gm.num = (int)(Math.random()*4) + 1;
	}
	
	public void lose(String cause) {
		gm.loseScreen(cause);
		gm.d.resetCounter();
		gm.hasKey = false;
		gm.houseUnlocked = false;
		gm.hasMap = false;
		gm.hasLantern = false;
		gm.lanternOn = false;
		gm.hide = false;
		gm.run = false;
		gm.lanternClickCount = 0;
		gm.mapClickCount = 0;
		gm.clickCount = 0;
		sceneNumber = 0;
		gm.num = (int)(Math.random()*4) + 1;
	}
	
	public void test() {
		System.out.println("test");
	}
}
