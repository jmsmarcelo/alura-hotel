package com.github.jmsmarcelo.alurahotel.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.github.jmsmarcelo.alurahotel.jdbc.FactoryConnection;

public class MenuLogin extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JTextField txtUser;
	private JPasswordField txtPass;
	private String imgPath = "/com/github/jmsmarcelo/alurahotel/images/";
	private int xMouse, yMouse;
	
	public MenuLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuLogin.class
				.getResource(imgPath + "ah-40px.png")));
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 788, 527);
		setLocationRelativeTo(null);
		
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		contentPanel.setBackground(Color.WHITE);
		setContentPane(contentPanel);
	
		JLabel logo = new JLabel("");
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setIcon(new ImageIcon(MenuLogin.class.getResource(imgPath + "ah-50px.png")));
		logo.setBounds(210, 65, 48, 59);
		contentPanel.add(logo);
		
		JPanel aside = new JPanel();
		aside.setBackground(new Color(12, 138, 199));
		aside.setBounds(484, 0, 304, 527);
		aside.setLayout(null);
		contentPanel.add(aside);
		
		JPanel btnExit = new JPanel();
		btnExit.setLayout(null);
		btnExit.setBackground(new Color(12, 138, 199));
		btnExit.setBounds(251, 0, 53, 36);
		btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		aside.add(btnExit);
		
		JLabel lblExit = new JLabel("X");
		lblExit.setBounds(0, 0, 53, 36);
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblExit.setForeground(Color.white);
		btnExit.add(lblExit);
		
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnExit.setBackground(Color.red);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnExit.setBackground(new Color(12, 138, 199));
			}
		});
		
		JLabel imgHotel = new JLabel("");
		imgHotel.setBounds(0, 0, 304, 538);
		imgHotel.setIcon(new ImageIcon(MenuLogin.class.getResource(imgPath + "img-hotel-login.png")));
		aside.add(imgHotel);
		
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
		
		JLabel lblTitle = new JLabel("LOGIN");
		lblTitle.setForeground(SystemColor.textHighlight);
		lblTitle.setFont(new Font("Roboto Black", Font.PLAIN, 26));
		lblTitle.setBounds(196, 150, 89, 26);
		contentPanel.add(lblTitle);
		
		contentPanel.add(loginTitle("USUÁRIO", 219));
		
		txtUser = new JTextField();
		txtUser.setText("Digite seu nome de usuário");
		txtUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(txtUser.getText().equals("Digite seu nome de usuário")) {
					txtUser.setText("");
					txtUser.setForeground(Color.black);
				}
				if(String.valueOf(txtPass.getPassword()).isEmpty()) {
					txtPass.setText("********");
					txtPass.setForeground(Color.gray);
				}
			}
		});
		txtUser.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtUser.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtUser.setForeground(SystemColor.activeCaptionBorder);
		txtUser.setBounds(65, 256, 324, 32);
		txtUser.setColumns(10);
		contentPanel.add(txtUser);
		
		contentPanel.add(separator(292));

		contentPanel.add(loginTitle("SENHA", 316));
		
		txtPass = new JPasswordField();
		txtPass.setText("********");
		txtPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(String.valueOf(txtPass.getPassword()).equals("********")) {
					txtPass.setText("");
					txtPass.setForeground(Color.black);
				}
				if(txtUser.getText().isEmpty()) {
					txtUser.setText("Digite seu nome de usuário");
					txtUser.setForeground(Color.gray);
				}
			}
		});
		txtPass.setForeground(SystemColor.activeCaptionBorder);
		txtPass.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtPass.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtPass.setBounds(65, 353, 324, 32);
		contentPanel.add(txtPass);
		
		contentPanel.add(separator(393));
		
		JPanel btnLogin = new JPanel();
		btnLogin.setBackground(SystemColor.textHighlight);
		btnLogin.setBounds(180, 431, 122, 44);
		btnLogin.setLayout(null);
		btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPanel.add(btnLogin);
		
		JLabel lblEnter = new JLabel("ENTRAR");
		lblEnter.setBounds(0, 0, 122, 44);
		lblEnter.setForeground(SystemColor.controlLtHighlight);
		lblEnter.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnter.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnLogin.add(lblEnter);
		
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogin.setBackground(new Color(0, 156, 223));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnLogin.setBackground(SystemColor.textHighlight);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				FactoryConnection fc = new FactoryConnection(
						txtUser.getText(), new String(txtPass.getPassword()));
				try(Connection conn = fc.getConnection();) {
					MenuUser menuUser = new MenuUser();
					menuUser.setLocationRelativeTo(contentPanel);
					menuUser.setVisible(true);
					dispose();
				} catch (Exception se) {
					JOptionPane.showMessageDialog(contentPanel, se.getMessage());
				}
			}
		});
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
	private JSeparator separator(int y) {
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 120, 215));
		separator.setBounds(65, y, 324, 2);
		return separator;
	}
	private JLabel loginTitle(String t, int y) {
		JLabel lblUser = new JLabel(t);
		lblUser.setForeground(SystemColor.textInactiveText);
		lblUser.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		lblUser.setBounds(65, y, 107, 26);
		return lblUser;
	}
}
