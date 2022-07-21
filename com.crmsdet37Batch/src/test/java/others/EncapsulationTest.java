package others;

import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EncapsulationTest {
	
	WebDriver driver=null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EncapsulationTest example = new EncapsulationTest();
	    example.driver=WebDriverManager.chromedriver().create();
	}
}