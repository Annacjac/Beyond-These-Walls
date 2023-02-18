import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

import javax.swing.*;

public class Dialogue {

	GameManager gm;
	String[] scene1Lines = new String[26];
	String[] scene2Lines = new String[5];
	String[] scene3Lines = new String[8];
	String[] scene4Lines = new String[4];
	String[] scene5Lines = new String[7];
	String[] scene6Lines = new String[9];
	String[] scene7Lines = new String[16];
	String[] scene8aLines = new String[8];
	String[] scene8bLines = new String[3];
	Stream<String> scene1;
	Stream<String> scene2;
	Stream<String> scene3;
	Stream<String> scene4;
	Stream<String> scene5;
	Stream<String> scene6;
	Stream<String> scene7;
	Stream<String> scene8a;
	File[] scenes = new File[9];
	private int lineCounter = 0;
	
	public Dialogue(GameManager gm) {
		this.gm = gm;
	}
	
	public void writeText(String line) {
		gm.gameText.setText(line);
		System.out.println(line);
		lineCounter++;
	}
	
	public String getText() {
		return gm.gameText.getText();
	}
	
	public void readLines() {
		try {
			scenes[0] = new File("assets/dialogue/Scene1.txt");
			Scanner scan1 = new Scanner(scenes[0]);
			int n = 0;
			while(scan1.hasNextLine()) {
				scene1Lines[n] = scan1.nextLine();
				n++;
			}
			
			n = 0;
			scenes[1] = new File("assets/dialogue/Scene2.txt");
			Scanner scan2 = new Scanner(scenes[1]);
			while(scan2.hasNextLine()) {
				scene2Lines[n] = scan2.nextLine();
				n++;
			}
			
			n = 0;
			scenes[2] = new File("assets/dialogue/Scene3.txt");
			Scanner scan3 = new Scanner(scenes[2]);
			while(scan3.hasNextLine()) {
				scene3Lines[n] = scan3.nextLine();
				n++;
			}
				
			n = 0;
			scenes[3] = new File("assets/dialogue/Scene4.txt");
			Scanner scan4 = new Scanner(scenes[3]);
			while(scan4.hasNextLine()) {
				scene4Lines[n] = scan4.nextLine();
				n++;
			}
			n = 0;
			scenes[4] = new File("assets/dialogue/Scene5.txt");
			Scanner scan5 = new Scanner(scenes[4]);
			while(scan5.hasNextLine()) {
				scene5Lines[n] = scan5.nextLine();
				n++;
			}
			n = 0;
			scenes[5] = new File("assets/dialogue/Scene6.txt");
			Scanner scan6 = new Scanner(scenes[5]);
			while(scan6.hasNextLine()) {
				scene6Lines[n] = scan6.nextLine();
				n++;
			}
			
			n = 0;
			scenes[6] = new File("assets/dialogue/Scene7.txt");
			Scanner scan7 = new Scanner(scenes[6]);
			while(scan7.hasNextLine()) {
				scene7Lines[n] = scan7.nextLine();
				n++;
			}
			
			n = 0;
			scenes[7] = new File("assets/dialogue/Scene8a.txt");
			Scanner scan8a = new Scanner(scenes[7]);
			while(scan8a.hasNextLine()) {
				scene8aLines[n] = scan8a.nextLine();
				n++;
			}
			
			n = 0;
			scenes[8] = new File("assets/dialogue/Scene8b.txt");
			Scanner scan8b = new Scanner(scenes[8]);
			while(scan8b.hasNextLine()) {
				scene8bLines[n] = scan8b.nextLine();
				n++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
	}
	
	public void resetCounter() {
		lineCounter = 0;
	}
	
	public int getLineCounter() {
		return lineCounter;
	}
	
	public void setLineCounter(int number) {
		lineCounter = number;
	}
}
