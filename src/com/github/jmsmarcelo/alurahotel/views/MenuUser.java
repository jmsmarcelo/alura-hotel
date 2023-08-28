package com.github.jmsmarcelo.alurahotel.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MenuUser extends JFrame {
	private JPanel contentPanel;
	private String imgPath = "/com/github/jmsmarcelo/alurahotel/images/";
	private int xMouse, yMouse;
	
	public MenuUser() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuUser.class
				.getResource(imgPath + "ah-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 944, 609);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		contentPanel = new JPanel();
		contentPanel.setBackground(Color.white);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		setContentPane(contentPanel);
		
		JPanel aside = new JPanel();
		aside.setBackground(new Color(12, 138, 199));
		aside.setBounds(0, 0, 257, 609);
		aside.setLayout(null);
		contentPanel.add(aside);
		
		JLabel logo = new JLabel("");
		logo.setBounds(50, 58, 150, 150);
		logo.setIcon(new ImageIcon(MenuUser.class.getResource(imgPath + "ah-150px.png")));
		aside.add(logo);
		
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
		header.setBounds(0, 0, 944, 36);
		contentPanel.add(header);
		
		JPanel btnExit = new JPanel();
		btnExit.setLayout(null);
		btnExit.setBackground(Color.white);
		btnExit.setBounds(891, 0, 53, 36);
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
				MenuMain menuMain = new MenuMain();
				menuMain.setLocationRelativeTo(contentPanel);
				menuMain.setVisible(true);
				dispose();
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
		
		JSeparator separator = new JSeparator();
		separator.setBounds(26, 219, 201, 2);
		aside.add(separator);
		
		JPanel btnRecord = new JPanel();
		btnRecord.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuReserve reservations = new MenuReserve();
				reservations.setLocationRelativeTo(contentPanel);
				reservations.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRecord.setBackground(new Color(118, 187, 223));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnRecord.setBackground(new Color(12, 138, 199));
			}
		});
		btnRecord.setBounds(0, 255, 257, 56);
		btnRecord.setBackground(new Color(12, 138, 199));
		btnRecord.setLayout(null);
		btnRecord.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		aside.add(btnRecord);
		
		JLabel lblRecord = new JLabel("Registrar Reserva");
		lblRecord.setIcon(new ImageIcon(MenuUser.class.getResource(imgPath + "icon-reserved.png")));
		lblRecord.setForeground(SystemColor.text);
		lblRecord.setBounds(25, 11, 205, 34);
		lblRecord.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblRecord.setHorizontalAlignment(SwingConstants.LEFT);
		btnRecord.add(lblRecord);
		
		JPanel btnSearch = new JPanel();
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuSearch search = new MenuSearch();
				search.setLocationRelativeTo(contentPanel);
				search.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSearch.setBackground(new Color(118, 187, 223));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSearch.setBackground(new Color(12, 138, 199));
			}
		});
		
		btnSearch.setBounds(0, 312, 257, 56);
		btnSearch.setBackground(new Color(12, 138, 199));
		btnSearch.setLayout(null);
		btnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		aside.add(btnSearch);
		
		JLabel lblSearch = new JLabel("Buscar");
		lblSearch.setIcon(new ImageIcon(MenuUser.class.getResource(imgPath + "icon-people.png")));
		lblSearch.setBounds(30, 11, 200, 34);
		lblSearch.setHorizontalAlignment(SwingConstants.LEFT);
		lblSearch.setForeground(Color.white);
		lblSearch.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnSearch.add(lblSearch);
		
		JPanel section = new JPanel();
		section.setBackground(new Color(118, 187, 223));
		section.setBounds(256, 84, 688, 121);
		section.setLayout(null);
		contentPanel.add(section);
		
		JLabel lblMain = new JLabel("Sistema de reservas Hotel Alura");
		lblMain.setBounds(180, 11, 356, 42);
		lblMain.setForeground(Color.WHITE);
		lblMain.setFont(new Font("Roboto", Font.PLAIN, 24));
		section.add(lblMain);
		
		JLabel lblSection = new JLabel("New label");
		lblSection.setBounds(35, 64, 294, 36);
		lblSection.setForeground(Color.WHITE);
		lblSection.setFont(new Font("Roboto", Font.PLAIN, 33));
		lblSection.setText("Hoje é " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		section.add(lblSection);
		
		JLabel lblTitle = new JLabel("Bem-vindo");
		lblTitle.setFont(new Font("Roboto", Font.BOLD, 24));
		lblTitle.setBounds(302, 234, 147, 46);
		contentPanel.add(lblTitle);
		
		contentPanel.add(lblDescription(
				"<html><body>Sistema de reservas de hotéis. Controle e gerencie de forma otimizada e fácil<br>"
				+ "o fluxo de reservas e hóspedes do hotel</body></html>", 291, 598, 66));
		contentPanel.add(lblDescription(
				"<html><body>Esta ferramenta permitirá que você mantenha um contro completo "
				+ "e detalhado de suas reservas e hóspedes, você terá acesso a ferramentas "
				+ "especiais para tarefas como:</body></html>", 345, 569, 88));
		contentPanel.add(lblDescription(
				" - Registro de Reservas e Hóspedes", 444, 295, 27));
		contentPanel.add(lblDescription(
				" - Edição de Reservas e Hóspedes existentes", 482, 355, 27));
		contentPanel.add(lblDescription(
				" - Excluir todos os tipos de registros", 520, 295, 27));
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
	private JLabel lblDescription(String txt, int y, int w, int h) {
		JLabel lblDescription = new JLabel(txt);
		lblDescription.setFont(new Font("Roboto", Font.PLAIN, 17));
		lblDescription.setBounds(312, y, w, h);
		return lblDescription;
	}
}
