package com.tomhazell.csgo.ingametimer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.json.JSONObject;

public class Overlay implements IGameState, Runnable {

	JLabel timer;
	int BombTimer = -1;

	public static void main(String[] args){
		new Overlay();

	}
	
	@Override
	public void OnGameStateRecived(String GameState) {
		JSONObject Game = new JSONObject(GameState);
		JSONObject round = (JSONObject) Game.get("round");
		String BombState = round.getString("bomb");
		
		
		if (BombState.equals("exploded")) {
			BombTimer = -1;
		}
		
		if (BombState.equals("planted") && !BombState.equals("exploded") && BombTimer == -1) {
			BombTimer = 40;
			this.run();
		}
		
		if (BombState.equals("defused")) {
			BombTimer = -1;
		}
		
	}

	Overlay() {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Double width = screenSize.getWidth();
		Double height = screenSize.getHeight();
		
		JFrame frame = new JFrame("Transparent Window");
		frame.setUndecorated(true);
		frame.setBackground(new Color(0, 0, 0, 0));
		frame.setSize(width.intValue(), height.intValue());
		frame.setPreferredSize(new Dimension(width.intValue(), height.intValue()));
		frame.setAlwaysOnTop(true);

		frame.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", false);

		frame.setLayout(new BorderLayout());

		timer = new JLabel("");
		timer.setHorizontalAlignment(JLabel.CENTER);
		timer.setVerticalAlignment(JLabel.TOP);

		try {
			timer.setFont(Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/resources/Stratum2-Black.ttf")).deriveFont(30.0f));
		} catch (IOException e1) {
			errorBox("Have you modifyed the font? it cant be found. Full error: " + e1.toString());
			System.exit(0);
		} catch (FontFormatException e) {
			errorBox("Have you modifyed the font? Its the wrong format. Full error: " + e.toString());
			System.exit(0);
		}
		
		timer.setForeground(Color.RED);
		timer.setBorder(new EmptyBorder(2, 4, 0, 0));
		frame.add(timer, BorderLayout.CENTER);

		frame.setVisible(true);
		frame.pack();

		GameStateChangeLisener state = new GameStateChangeLisener(this);
		try {
			state.Start();
		} catch (IOException e) {
			errorBox("IO Exeption, have you ran this twice? full error: " + e.toString());
			System.exit(0);
		}
		
	}
	
	

	@Override
	public void run() {
		while (BombTimer > -1) {
			timer.setText(Integer.toString(BombTimer));
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.err.println(e.toString());
			}
			BombTimer = BombTimer - 1;
		}
		timer.setText("");
	}
	
	public static void errorBox(String Message)
    {
        JOptionPane.showMessageDialog(null, Message, "ERROR", JOptionPane.INFORMATION_MESSAGE);
    }
}