package com.efm.crjj.ismo.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class MyButton extends JButton implements MouseListener{
	
	public MyButton(String title, String icon) {
		setText(title);
		setHorizontalAlignment(SwingConstants.LEFT);
		ImageIcon image6 = new ImageIcon(getClass().getResource(icon));
		setIcon(image6);
		setIconTextGap(30);
		addMouseListener(this);
		setForeground(new Color(255, 255, 255));
		setBackground(new Color(0, 0, 128));
		setPreferredSize(new Dimension(83, 40));
		setFont(new Font("Verdana", Font.BOLD, 14));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JButton b = (JButton) e.getSource();
		b.setBackground(new Color(255, 128, 0));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JButton b = (JButton) e.getSource();
		b.setBackground(new Color(0, 0, 128));
	}

}