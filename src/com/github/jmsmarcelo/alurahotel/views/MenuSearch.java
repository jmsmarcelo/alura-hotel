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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.github.jmsmarcelo.alurahotel.jdbc.Controller;
import com.github.jmsmarcelo.alurahotel.jdbc.Guest;
import com.github.jmsmarcelo.alurahotel.jdbc.Reserve;

@SuppressWarnings("serial")
public class MenuSearch extends JFrame {
	private JPanel contentPanel;
	private String imgPath = "/com/github/jmsmarcelo/alurahotel/images/";
	private JTable tbGuests;
	private DefaultTableModel tbGuestsModel;
	private DefaultTableModel tbReservationsModel;
	private List<Guest> guests;
	private List<Reserve> reservations = new ArrayList<Reserve>();
	private int xMouse, yMouse;
	
	public MenuSearch() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuMain.class
				.getResource(imgPath + "ah-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setResizable(false);
		
		contentPanel = new JPanel();
		contentPanel.setBackground(Color.white);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		setContentPane(contentPanel);
		
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
		header.add(btnBefore);
		
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
		
		JLabel logo = new JLabel("");
		logo.setBounds(56, 51, 104, 107);
		logo.setIcon(new ImageIcon(MenuSearch.class.getResource(imgPath + "ah-100px.png")));
		contentPanel.add(logo);
		
		JLabel lblTitle = new JLabel("SISTEMA DE BUSCA");
		lblTitle.setBounds(331, 62, 280, 42);
		lblTitle.setForeground(new Color(12, 138, 199));
		lblTitle.setFont(new Font("Roboto Black", Font.BOLD, 24));
		contentPanel.add(lblTitle);
		
		JTextField txtSearch = new JTextField();
		txtSearch.setBounds(536, 127, 193, 31);
		txtSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtSearch.setColumns(10);
		contentPanel.add(txtSearch);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(539, 159, 193, 2);
		separator.setForeground(new Color(12, 138, 199));
		separator.setBackground(new Color(12, 138, 199));
		contentPanel.add(separator);
		
		JPanel btnSearch = new JPanel();
		btnSearch.setBounds(748, 125, 122, 35);
		btnSearch.setBackground(new Color(12, 138, 199));
		btnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPanel.add(btnSearch);
		
		JLabel lblSearch = new JLabel("BUSCAR");
		lblSearch.setBounds(0, 0, 122, 35);
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearch.setForeground(Color.white);
		lblSearch.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnSearch.add(lblSearch);
		
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Controller controller = new Controller();
					guests = controller.search(txtSearch.getText());
					for(Guest g: guests)
						reservations.add(g.getReserve());
					setTableSearch();
					controller.getConn().close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSearch.setBackground(SystemColor.textHighlight);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSearch.setBackground(new Color(12, 138, 199));
			}
		});
		
		JTabbedPane tabPanel = new JTabbedPane(JTabbedPane.TOP);
		tabPanel.setBounds(20, 169, 865, 328);
		tabPanel.setBackground(new Color(12, 138, 199));
		tabPanel.setFont(new Font("Roboto", Font.PLAIN, 16));
		contentPanel.add(tabPanel);
		
		tbReservationsModel = new DefaultTableModel(new Object[] {
				"ID da Reserva", "Data de Check In",
				"Data de Check Out", "Valor", "Forma de Pagamento"}, 0);
		JTable tbReservations = new JTable(tbReservationsModel);
		tbReservations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservations.setFont(new Font("Roboto", Font.PLAIN, 16));
		tabPanel.addTab("Reservas",
				new ImageIcon(MenuSearch.class.getResource(imgPath + "icon-reserved.png")),
				new JScrollPane(tbReservations), null);
		
		tbGuestsModel = new DefaultTableModel(new String[] {
				"ID do hóspede", "Nome", "Sobrenome",
				"Data de Nascimento", "País", "Telefone", "ID da reserva"}, 0);
		tbGuests = new JTable(tbGuestsModel);
		tbGuests.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbGuests.setFont(new Font("Roboto", Font.PLAIN, 16));
		tabPanel.addTab("Hóspedes",
				new ImageIcon(MenuSearch.class.getResource(imgPath + "icon-people.png")),
				new JScrollPane(tbGuests), null);
		
		JPanel btnEdit = new JPanel();
		btnEdit.setBounds(635, 508, 122 , 35);
		btnEdit.setBackground(new Color(12, 138, 199));
		btnEdit.setLayout(null);
		btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPanel.add(btnEdit);
		
		JLabel lblEdit = new JLabel("EDITAR");
		lblEdit.setBounds(0, 0, 122, 35);
		lblEdit.setHorizontalAlignment(SwingConstants.CENTER);
		lblEdit.setForeground(Color.white);
		lblEdit.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnEdit.add(lblEdit);
		
		btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if(guests.size() == tbGuestsModel.getRowCount()) {
						if(JOptionPane.showConfirmDialog(contentPanel, "Deseja salvar as alterações?") == 0) {
							for(int i = 0; i < guests.size(); i++) {
								guests.get(i).update(
										tbGuestsModel.getValueAt(i, tbGuestsModel.findColumn("Nome")).toString(), 
										tbGuestsModel.getValueAt(i, tbGuestsModel.findColumn("Sobrenome")).toString(), 
										java.sql.Date.valueOf(tbGuestsModel.getValueAt(
												i, tbGuestsModel.findColumn("Data de Nascimento")).toString()), 
										tbGuestsModel.getValueAt(i, tbGuestsModel.findColumn("País")).toString(), 
										tbGuestsModel.getValueAt(i, tbGuestsModel.findColumn("Telefone"))
												.toString().replaceAll("[^0-9\\.]", ""));
								reservations.get(i).update(
										java.sql.Date.valueOf(tbReservationsModel.getValueAt(
												i, tbReservationsModel.findColumn("Data de Check In")).toString()),
										java.sql.Date.valueOf(tbReservationsModel.getValueAt(
												i, tbReservationsModel.findColumn("Data de Check Out")).toString()),
										new BigDecimal(tbReservationsModel.getValueAt(
												i, tbReservationsModel.findColumn("Valor"))
												.toString().replaceAll("[^0-9\\.]", "")),
										tbReservationsModel.getValueAt(
												i, tbReservationsModel.findColumn("Forma de Pagamento")).toString());
							}
							try {
								Controller controller = new Controller();
								controller.update(guests, reservations);
								setTableSearch();
								controller.getConn().close();
								JOptionPane.showMessageDialog(contentPanel, "Registros editados com sucesso");
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(contentPanel, e1.getMessage());
							}
						}
					}
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(contentPanel, "Não há registros para editar");
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEdit.setBackground(SystemColor.textHighlight);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnEdit.setBackground(new Color(12, 138, 199));
			}
		});
		
		JPanel btnDelete = new JPanel();
		btnDelete.setBounds(767, 508, 122, 35);
		btnDelete.setLayout(null);
		btnDelete.setBackground(new Color(12, 138, 199));
		btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPanel.add(btnDelete);
		
		JLabel lblDelete = new JLabel("DELETAR");
		lblDelete.setBounds(0, 0, 122, 35);
		lblDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblDelete.setForeground(Color.white);
		lblDelete.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnDelete.add(lblDelete);
		
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int target = -1;
				if(tabPanel.getSelectedIndex() == 1)
					target = tbGuests.getSelectedRow();
				if(target == -1)
					target = tbReservations.getSelectedRow();
				if(target != -1) {
					if(JOptionPane.showConfirmDialog(contentPanel, "Deseja deletar o registro?") == 0)
						try {
							Controller controller = new Controller();
							controller.delete(guests.get(target));
							guests.remove(target);
							reservations.remove(target);
							setTableSearch();
							controller.getConn().close();
							JOptionPane.showMessageDialog(contentPanel, "Registro deletado com sucesso");
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(contentPanel, "Não foi possível deletar o registro");
						}
				}
				else {
					JOptionPane.showMessageDialog(contentPanel, "Não há registro selecionado");
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnDelete.setBackground(SystemColor.textHighlight);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnDelete.setBackground(new Color(12, 138, 199));
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
	private void setTableSearch() {
		tbGuestsModel.setRowCount(0);
		tbReservationsModel.setRowCount(0);
		for(int i = 0; i < guests.size(); i ++) {
			tbGuestsModel.addRow(new Object[] {
					guests.get(i).getId(),
					guests.get(i).getFirstName(),
					guests.get(i).getLastName(),
					guests.get(i).getBirthDate().toString(),
					guests.get(i).getCountry(),
					guests.get(i).getPhone()
						.replaceAll("(\\d{2})(\\d)(\\d{4})(\\d{4})$", "$1 $2 $3-$4"),
					String.valueOf(guests.get(i).getReserveId())
			});
			tbReservationsModel.addRow(new Object[] {
					reservations.get(i).getId(),
					reservations.get(i).getCheckIn(),
					reservations.get(i).getCheckOut(),
					new DecimalFormat("R$  ###,###,###.00")
						.format(reservations.get(i).getPrice()),
					reservations.get(i).getPayMethod()
			});
		}
	}
}
