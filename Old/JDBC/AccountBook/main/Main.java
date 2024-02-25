package main;

import db.DBconnection;
import view.loginView;

public class Main {

	public static void main(String[] args) {
		DBconnection.initConnection();
		
		new loginView();
		
	}

}
