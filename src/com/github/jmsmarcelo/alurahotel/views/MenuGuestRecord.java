package com.github.jmsmarcelo.alurahotel.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

import com.github.jmsmarcelo.alurahotel.jdbc.Controller;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class MenuGuestRecord extends JFrame {
	private JPanel contentPanel;
	private String imgPath = "/com/github/jmsmarcelo/alurahotel/images/";
	private int xMouse, yMouse;
	private Controller controller = new Controller();
	
	public MenuGuestRecord() throws SQLException {
		controller.getConn().setAutoCommit(false);
		controller.setReserve(
				new java.sql.Date(MenuReserve.getDates().get(0).getDate().getTime()),
				new java.sql.Date(MenuReserve.getDates().get(1).getDate().getTime()),
				new BigDecimal(MenuReserve.getValue().getText()),
				MenuReserve.getPayMethod().getSelectedItem().toString());
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuMain.class
				.getResource(imgPath + "ah-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 634);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		contentPanel = new JPanel();
		contentPanel.setBackground(SystemColor.text);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		setContentPane(contentPanel);
		
		JPanel aside = new JPanel();
		aside.setBounds(0, 0, 489, 634);
		aside.setBackground(new Color(12, 138, 199));
		aside.setLayout(null);
		contentPanel.add(aside);
		
		JPanel btnBefore = new JPanel();
		btnBefore.setLayout(null);
		btnBefore.setBackground(new Color(12, 138, 199));
		btnBefore.setBounds(0, 0, 53, 36);
		btnBefore.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		aside.add(btnBefore);
		
		JLabel lblBefore = new JLabel("<");
		lblBefore.setBounds(0, 0, 53, 36);
		lblBefore.setHorizontalAlignment(SwingConstants.CENTER);
		lblBefore.setForeground(Color.white);
		lblBefore.setFont(new Font("Roboto", Font.PLAIN, 23));
		btnBefore.add(lblBefore);
		btnBefore.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					controller.getConn().rollback();
					controller.getConn().commit();
					controller.getConn().close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				MenuReserve beforeMenu = new MenuReserve();
				beforeMenu.setLocationRelativeTo(contentPanel);
				beforeMenu.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBefore.setBackground(Color.white);
				lblBefore.setForeground(Color.black);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnBefore.setBackground(new Color(12, 138, 199));
				lblBefore.setForeground(Color.white);
			}
		});
		
		JLabel logo = new JLabel("");
		logo.setBounds(194, 39, 104, 107);
		logo.setIcon(new ImageIcon(MenuGuestRecord.class.getResource(imgPath + "ah-100px.png")));
		aside.add(logo);
		
		JLabel imgBg = new JLabel("");
		imgBg.setBounds(0, 121, 479, 502);
		imgBg.setIcon(new ImageIcon(MenuGuestRecord.class.getResource(imgPath + "img-record.png")));
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
		lblExit.setForeground(Color.black);
		btnExit.add(lblExit);
		
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					controller.getConn().rollback();
					controller.getConn().commit();
					controller.getConn().close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
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
		
		JLabel lblTitle = new JLabel("REGISTRAR HÓSPEDE");
		lblTitle.setBounds(575, 55, 280, 42);
		lblTitle.setForeground(new Color(12, 138, 199));
		lblTitle.setFont(new Font("Roboto Black", Font.PLAIN, 23));
		contentPanel.add(lblTitle);
		
		contentPanel.add(setLblTitle("NOME", 119));
		
		JTextField txtFirstName = new JTextField();
		txtFirstName.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtFirstName.setBounds(560, 135, 285, 33);
		txtFirstName.setBackground(Color.white);
		txtFirstName.setColumns(10);
		txtFirstName.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPanel.add(txtFirstName);
		
		contentPanel.add(setSeparator(170));
		
		contentPanel.add(setLblTitle("SOBRENOME", 189));
		
		JTextField txtLastName = new JTextField();
		txtLastName.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtLastName.setBounds(560, 206, 285, 33);
		txtLastName.setBackground(Color.white);
		txtLastName.setColumns(10);
		txtLastName.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPanel.add(txtLastName);
		
		contentPanel.add(setSeparator(240));
		
		contentPanel.add(setLblTitle("DATA DE NASCIMENTO", 256));
		
		JDateChooser txtBirthDate = new JDateChooser();
		txtBirthDate.setBounds(560, 278, 289, 36);
		txtBirthDate.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtBirthDate.getCalendarButton().setIcon(new ImageIcon(MenuReserve.class
				.getResource(imgPath + "icon-reservation.png")));
		txtBirthDate.getCalendarButton().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		txtBirthDate.setBorder(new LineBorder(SystemColor.window));
		txtBirthDate.setDateFormatString("yyyy-MM-dd");
		txtBirthDate.setFont(new Font("Roboto", Font.PLAIN, 16));
		contentPanel.add(txtBirthDate);
		
		contentPanel.add(setSeparator(314));
		
		contentPanel.add(setLblTitle("PAÍS", 326));
		
		List<String> countries = new ArrayList<String>();
		for(Locale l: Locale.getAvailableLocales())
			if(l.getCountry() != "")
			countries.add(l.getDisplayCountry());

		JComboBox<String> txtCountries = new JComboBox<String>();
		txtCountries.setBounds(560, 350, 289, 36);
		txtCountries.setBackground(SystemColor.text);
		txtCountries.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtCountries.setModel(new DefaultComboBoxModel<String>(
				countries.toArray(new String[0])
		));
		txtCountries.setSelectedItem(Locale.getDefault().getDisplayCountry());
		contentPanel.add(txtCountries);
		
		contentPanel.add(setSeparator(387));
		
		contentPanel.add(setLblTitle("TELEFONE", 406));
		
		JTextField txtPhone = new JTextField();
		txtPhone.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtPhone.setBounds(560, 424, 289, 33);
		txtPhone.setColumns(10);
		txtPhone.setBackground(Color.white);
		txtPhone.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPanel.add(txtPhone);
		
		contentPanel.add(setSeparator(457));
		
		contentPanel.add(setLblTitle("REGISTRO DA RESERVA", 474));
		
		JTextField txtReserveId = new JTextField();
		txtReserveId.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtReserveId.setBounds(560, 495, 289, 33);
		txtReserveId.setColumns(10);
		txtReserveId.setBackground(Color.white);
		txtReserveId.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtReserveId.setText(controller.getReserveId());
		txtReserveId.setEditable(false);
		contentPanel.add(txtReserveId);
		
		contentPanel.add(setSeparator(529));
		
		JPanel btnSave = new JPanel();
		btnSave.setBounds(723, 560, 122, 35);
		btnSave.setLayout(null);
		btnSave.setBackground(new Color(12, 138, 199));
		btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPanel.add(btnSave);
		
		JLabel lblSave = new JLabel("SALVAR");
		lblSave.setBounds(0, 0, 122, 35);
		lblSave.setHorizontalAlignment(SwingConstants.CENTER);
		lblSave.setForeground(Color.white);
		lblSave.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnSave.add(lblSave);
		
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if(txtFirstName.getText().isBlank() || txtLastName.getText().isBlank()
							|| txtPhone.getText().isBlank())
						throw new RuntimeException("todos os campos devem ser preenchidos");
					
					controller.setGuest(
							txtFirstName.getText(), 
							txtLastName.getText(), 
							new java.sql.Date(txtBirthDate.getDate().getTime()), 
							txtCountries.getSelectedItem().toString(), 
							txtPhone.getText());
					controller.getConn().commit();
					controller.getConn().close();
					MenuConfirm menuConfirm = new MenuConfirm();
					menuConfirm.setLocationRelativeTo(contentPanel);
					menuConfirm.setVisible(true);
					dispose();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(contentPanel, "Todos os campos devem ser preenchidos corretamente");
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSave.setBackground(SystemColor.textHighlight);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSave.setBackground(new Color(12, 138, 199));
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
	private JLabel setLblTitle(String txt, int y) {
		JLabel lblTitle = new JLabel(txt);
		lblTitle.setBounds(560, y, 255, 18);
		lblTitle.setForeground(SystemColor.textInactiveText);
		lblTitle.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		return lblTitle;
	}	
	private JSeparator setSeparator(int y) {
		JSeparator separator = new JSeparator();
		separator.setBounds(560, y, 289, 2);
		separator.setForeground(new Color(12, 138, 199));
		separator.setBackground(new Color(12, 138, 199));
		return separator;
	}
}
