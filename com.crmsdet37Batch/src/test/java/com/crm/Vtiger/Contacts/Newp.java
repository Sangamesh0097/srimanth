package com.crm.Vtiger.Contacts;

import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Newp {
	
	WebDriver driver=null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Newp a1 = new Newp();
		a1.driver=WebDriverManager.chromedriver().create();
	}
}