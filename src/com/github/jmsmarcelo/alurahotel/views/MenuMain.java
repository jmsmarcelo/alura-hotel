package com.github.jmsmarcelo.alurahotel.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MenuMain extends JFrame {
	private JPanel contentPanel;
	private String imgPath = "/com/github/jmsmarcelo/alurahotel/images/";
	private int xMouse, yMouse;
	
	public MenuMain() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuMain.class
				.getResource(imgPath + "ah-40px.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 910, 537);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		contentPanel.setBackground(SystemColor.window);
		setContentPane(contentPanel);
				
		JLabel imgBG = new JLabel("");
		imgBG.setBounds(-50, 0, 732, 501);
		imgBG.setIcon(new ImageIcon(MenuMain.class
				.getResource(imgPath + "img-menu.png")));
		contentPanel.add(imgBG);
		
		JLabel logo = new JLabel("");
		logo.setBounds(722, 80, 150, 156);
		logo.setIcon(new ImageIcon(MenuMain.class
				.getResource(imgPath + "ah-150px.png")));
		contentPanel.add(logo);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPanel.add(header);
		
		JPanel btnExit = new JPanel();
		btnExit.setLayout(null);
		btnExit.setBackground(Color.white);
		btnExit.setBounds(857, 0, 53, 36);
		btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		header.add(btnExit);
		
		JLabel lblExit = new JLabel("X");
		lblExit.setBounds(0, 0, 53, 36);
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnExit.add(lblExit);
		
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnExit.setBackground(Color.red);
				lblExit.setForeground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnExit.setBackground(Color.white);
				lblExit.setForeground(Color.black);
			}
		});
		
		JLabel lblTitle = new JLabel("LOGIN");
		lblTitle.setBounds(754, 265, 83, 24);
		lblTitle.setBackground(SystemColor.window);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(SystemColor.textHighlight);
		lblTitle.setFont(new Font("Roboto Light", Font.PLAIN, 20));
		contentPanel.add(lblTitle);
		
		JPanel btnLogin = new JPanel();
		btnLogin.setBounds(754, 300, 82, 72);
		btnLogin.setLayout(null);
		btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnLogin.setBackground(SystemColor.window);
		contentPanel.add(btnLogin);
		
		JLabel imgLogin = new JLabel("");
		imgLogin.setBounds(2, 2, 80, 70);
		imgLogin.setHorizontalAlignment(SwingConstants.CENTER);
		imgLogin.setIcon(new ImageIcon(MenuMain.class.getResource(imgPath + "icon-login.png")));
		btnLogin.add(imgLogin);
		
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuLogin login = new MenuLogin();
				login.setLocationRelativeTo(contentPanel);
				login.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				imgLogin.setIcon(new ImageIcon(
						new ImageIcon(MenuMain.class.getResource(imgPath + "icon-login.png"))
						.getImage().getScaledInstance(68, 68, Image.SCALE_SMOOTH)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				imgLogin.setIcon(new ImageIcon(MenuMain.class.getResource(imgPath + "icon-login.png")));
			}
		});
		
		JPanel footer = new JPanel();
		footer.setBounds(0, 500, 910, 37);
		footer.setBackground(new Color(12, 138, 199));
		footer.setLayout(null);
		contentPanel.add(footer);
		
		JLabel lblCopyR = new JLabel("Desenvolvido por Jose Marcelo © 2023");
		lblCopyR.setBounds(315, 11, 301, 19);
		lblCopyR.setForeground(new Color(240, 248, 255));
		lblCopyR.setFont(new Font("Roboto", Font.PLAIN, 16));
		footer.add(lblCopyR);
	}
	
	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}
}
