import java.awt.*;
import java.util.*;

import javax.swing.*;

public class Inventory extends JFrame{

	GameManager gm;
	private String item;
	private int itemNumber;
	private boolean inUse;
	JButton itemButton;
	
	public Inventory(GameManager gm) {
		this.gm = gm;
	}
	
	public void addInventory(String item, int itemNumber) {
		this.item = item;
		this.itemNumber = itemNumber;
		this.inUse = false;
		itemButton = new JButton(""+itemNumber);
		itemButton.setBackground(Color.black);
		itemButton.setForeground(Color.black);
		itemButton.setFont(new Font("Serif", Font.PLAIN, 1));
		itemButton.setBorderPainted(false);
		ImageIcon itemIcon = new ImageIcon("assets/items/" + item);
		itemButton.setBounds(150 + (itemNumber*65), 0, itemIcon.getIconWidth(), itemIcon.getIconHeight());
		itemButton.setIcon(itemIcon);
		gm.bgPanel.add(itemButton);
	}
	
	public String getItemName() {
		return item;
	}
	
	public int getItemNumber() {
		return itemNumber;
	}
	
	public boolean isInUse() {
		return inUse;
	}
	
	public void setUse(boolean use) {
		inUse = use;
	}
	
}
