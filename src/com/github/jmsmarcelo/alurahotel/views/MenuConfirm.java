package com.github.jmsmarcelo.alurahotel.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MenuConfirm extends JDialog {
	private JPanel contentPanel;
	private String imgPath = "/com/github/jmsmarcelo/alurahotel/images/";
	
	public MenuConfirm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuMain.class
				.getResource(imgPath + "ah-40px.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 394, 226);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		
		contentPanel = new JPanel();
		contentPanel.setBackground(SystemColor.control);
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel logo = new JLabel("");
		logo.setBounds(123, 11, 100, 100);
		logo.setIcon(new ImageIcon(MenuConfirm.class.getResource(imgPath + "ah-100px.png")));
		contentPanel.add(logo);
		
		JLabel lblMsg = new JLabel("Registro adicionado com sucesso");
		lblMsg.setBounds(27, 122, 322, 21);
		lblMsg.setForeground(new Color(12, 138, 199));
		lblMsg.setFont(new Font("Arial", Font.BOLD, 18));
		contentPanel.add(lblMsg);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(btnPanel, BorderLayout.SOUTH);
		
		JButton btnOk = new JButton("Confirmar");
		btnOk.setActionCommand("OK");
		btnPanel.add(btnOk);
		getRootPane().setDefaultButton(btnOk);
		
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuUser menuUser = new MenuUser();
				menuUser.setLocationRelativeTo(contentPanel);
				menuUser.setVisible(true);
				dispose();
			}
		});
		
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.setActionCommand("Cancel");
		btnPanel.add(btnCancel);
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuMain menuMain = new MenuMain();
				menuMain.setLocationRelativeTo(contentPanel);
				menuMain.setVisible(true);
				dispose();
			}
		});
	}
}
