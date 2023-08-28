package com.github.jmsmarcelo.alurahotel;

import java.awt.EventQueue;

import com.github.jmsmarcelo.alurahotel.views.MenuMain;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuMain main = new MenuMain();
					main.setVisible(true);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
