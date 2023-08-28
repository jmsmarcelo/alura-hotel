package com.github.jmsmarcelo.alurahotel.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.github.jmsmarcelo.alurahotel.jdbc.FactoryConnection;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class MenuReserve extends JFrame {
	private static JTextField txtValue;
	private static List<JDateChooser> dateChoosers = new ArrayList<>();
	private static JComboBox<String> txtPayMethod;
	private JPanel contentPanel;
	private JLabel lblValueSimbol;
	private String imgPath = "/com/github/jmsmarcelo/alurahotel/images/";
	private int xMouse, yMouse;
	
	public MenuReserve() {
		super("Reserva");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuMain.class
				.getResource(imgPath + "ah-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 560);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		contentPanel = new JPanel();
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		setContentPane(contentPanel);
		
		JLabel lblTitle = new JLabel("SISTEMA DE RESERVAS");
		lblTitle.setBounds(94, 60, 240, 42);
		lblTitle.setForeground(new Color(12, 138, 199));
		lblTitle.setFont(new Font("Roboto", Font.BOLD, 20));
		contentPanel.add(lblTitle);
		
		contentPanel.add(lblTitle("DATA DE CHECK IN", 136));
		
		contentPanel.add(dateChooser(0, 161));
		
		contentPanel.add(separator(196));
		
		contentPanel.add(lblTitle("DATA DE CHECK OUT", 221));
		
		contentPanel.add(dateChooser(1, 246));
		
		contentPanel.add(separator(281));
		
		contentPanel.add(lblTitle("VALOR DA RESERVA", 303));
		
		lblValueSimbol = new JLabel("R$");
		lblValueSimbol.setVisible(false);
		lblValueSimbol.setBounds(68, 332, 30, 25);
		lblValueSimbol.setForeground(SystemColor.textHighlight);
		lblValueSimbol.setFont(new Font("Roboto", Font.BOLD, 17));
		contentPanel.add(lblValueSimbol);
		
		txtValue = new JTextField();
		txtValue.setBackground(SystemColor.text);
		txtValue.setHorizontalAlignment(SwingConstants.CENTER);
		txtValue.setForeground(Color.black);
		txtValue.setBounds(90, 328, 90, 33);
		txtValue.setEditable(false);
		txtValue.setFont(new Font("Roboto Black", Font.BOLD, 17));
		txtValue.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtValue.setColumns(10);
		contentPanel.add(txtValue);
		
		contentPanel.add(separator(362));
		
		contentPanel.add(lblTitle("FORMA DE PAGAMENTO", 382));
		
		txtPayMethod = new JComboBox<String>();
		txtPayMethod.setBounds(68, 417, 289, 38);
		txtPayMethod.setBackground(SystemColor.text);
		txtPayMethod.setBorder(new LineBorder(Color.white, 1, true));
		txtPayMethod.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtPayMethod.setModel(new DefaultComboBoxModel<String>(
				new String[] {"Cartão de Crédito", "Cartão de Débito", "Dinheiro"}));
		contentPanel.add(txtPayMethod);
		
		contentPanel.add(separator(455));
		
		JPanel btnNext = new JPanel();
		btnNext.setLayout(null);
		btnNext.setBackground(SystemColor.textHighlight);
		btnNext.setBounds(238, 493, 122, 35);
		btnNext.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPanel.add(btnNext);
		
		JLabel lblNext = new JLabel("PRÓXIMO");
		lblNext.setHorizontalAlignment(SwingConstants.CENTER);
		lblNext.setForeground(Color.white);
		lblNext.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblNext.setBounds(0, 0, 122, 35);
		btnNext.add(lblNext);
		
		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(lblValueSimbol.isVisible()) {
						try {
							MenuGuestRecord record = new MenuGuestRecord();
							record.setLocationRelativeTo(contentPanel);
							record.setVisible(true);
							dispose();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
				} else {
					if(getDates().get(0).getDate() != null && getDates().get(1).getDate() != null)
						JOptionPane.showMessageDialog(
								contentPanel, "O CHECK IN deve ser anterior ao CHECK OUT");
					else
						JOptionPane.showMessageDialog(
								contentPanel, "Todos os campos devem ser preenchidos");
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNext.setBackground(new Color(12, 138, 199));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNext.setBackground(SystemColor.textHighlight);
			}
		});
		
		JPanel aside = new JPanel();
		aside.setBounds(428, 0, 482, 560);
		aside.setBackground(new Color(12, 138, 199));
		aside.setLayout(null);
		contentPanel.add(aside);
		
		JPanel btnExit = new JPanel();
		btnExit.setLayout(null);
		btnExit.setBackground(new Color(12, 138, 199));
		btnExit.setBounds(429, 0, 53, 36);
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
				MenuMain menuMain = new MenuMain();
				menuMain.setLocationRelativeTo(contentPanel);
				menuMain.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnExit.setBackground(Color.red);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnExit.setBackground(new Color(12, 139, 199));
			}
		});
		
		JLabel logo = new JLabel("");
		logo.setBounds(197, 68, 104, 107);
		logo.setIcon(new ImageIcon(MenuReserve.class.getResource(imgPath + "ah-100px.png")));
		aside.add(logo);
		
		JLabel imgBg = new JLabel("");
		imgBg.setBounds(0, 140, 500, 409);
		imgBg.setBackground(Color.white);
		imgBg.setIcon(new ImageIcon(MenuReserve.class.getResource(imgPath + "img-reservations.png")));
		aside.add(imgBg);
		
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
		
		JPanel btnBefore = new JPanel();
		btnBefore.setLayout(null);
		btnBefore.setBackground(Color.white);
		btnBefore.setBounds(0, 0, 53, 36);
		btnBefore.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		
		JLabel lblBefore = new JLabel("<");
		lblBefore.setBounds(0, 0, 53, 36);
		lblBefore.setHorizontalAlignment(SwingConstants.CENTER);
		lblBefore.setFont(new Font("Roboto", Font.PLAIN, 23));
		btnBefore.add(lblBefore);
		btnBefore.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUser beforeMenu = new MenuUser();
				beforeMenu.setLocationRelativeTo(contentPanel);
				beforeMenu.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBefore.setBackground(new Color(12, 138, 199));
				lblBefore.setForeground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnBefore.setBackground(Color.white);
				lblBefore.setForeground(Color.black);
			}
		});
		header.add(btnBefore);
	}
	public static JTextField getValue() {
		return txtValue;
	}
	public static List<JDateChooser> getDates() {
		return dateChoosers;
	}
	public static JComboBox<String> getPayMethod() {
		return txtPayMethod;
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
	private JLabel lblTitle(String txt, int y) {
		JLabel lblCheckIn = new JLabel(txt);
		lblCheckIn.setForeground(SystemColor.textInactiveText);
		lblCheckIn.setBounds(70, y, 250, 14);
		lblCheckIn.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		return lblCheckIn;
	}
	private JSeparator separator(int y) {
		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.textHighlight);
		separator.setBounds(68, y, 289, 2);
		separator.setBackground(SystemColor.textHighlight);
		return separator;
	}
	private JDateChooser dateChooser(int i, int y) {
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().setBackground(SystemColor.textHighlight);
		dateChooser.getCalendarButton().setIcon(new ImageIcon(MenuReserve.class
				.getResource(imgPath + "icon-reservation.png")));
		dateChooser.getCalendarButton().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		dateChooser.setBounds(68, y, 289, 35);
		dateChooser.setBorder(new LineBorder(SystemColor.window));
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setFont(new Font("Roboto", Font.PLAIN, 18));
		dateChooser.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(getDates().size() > 1 && getDates().get(0).getDate() != null
						&& getDates().get(1).getDate() != null) {
					long days = Duration.between(
							getDates().get(0).getDate().toInstant(),
							getDates().get(1).getDate().toInstant()).toDays();
					if(days > 0) {
						txtValue.setText(new BigDecimal(days)
								.multiply(getPrice()).setScale(2, RoundingMode.HALF_EVEN).toString());
						lblValueSimbol.setVisible(true);
					} else {
						txtValue.setText(null);
						lblValueSimbol.setVisible(false);
					}
				}
			}
		});
		if(dateChoosers.size() >= i + 1)
			dateChoosers.set(i, dateChooser);
		else
			dateChoosers.add(dateChooser);
		return dateChooser;
	}
	private BigDecimal getPrice() {
		BigDecimal value = new BigDecimal(0);
		FactoryConnection fc = new FactoryConnection();
		try(Connection conn = fc.getConnection()) {
			try(PreparedStatement ps = conn.prepareStatement("SELECT price FROM prices WHERE id='day'")) {
				ps.execute();
				try(ResultSet rs = ps.getResultSet()) {
					rs.next();
					value = rs.getBigDecimal("price");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return value;
	}
}
