package cucumber.util;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class DriverGenerator {

	
	public static WebDriver  generarDriverFirefoxPortable(){
		 File pathToBinary = new File("D:\\firefox\\FirefoxPortable.exe");// poner ruta al firefox portable/al
		 // firefox de nuestro ordenador
		
		 FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		 FirefoxProfile firefoxProfile = new FirefoxProfile();
		
		 return  new FirefoxDriver(ffBinary, firefoxProfile);
	}

	public static WebDriver generarDriverFirefox() {
		return new FirefoxDriver();
	}

	public static WebDriver generarDriverHTML() {
		return new HtmlUnitDriver();
	}
	
	

}
