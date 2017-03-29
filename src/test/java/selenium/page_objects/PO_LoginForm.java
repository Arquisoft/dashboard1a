package selenium.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.ThreadUtil;

public class PO_LoginForm {
	
	/*
	 * @Clean : posibles incosistencias en los id , ya que se va a utilizar prime faces
	 * 
	 * Clase creada para rellenar logins y así separa el codigo
	 */
	
	public void completeForm(WebDriver driver, String loginValue,String passwordValue){
		//(1) encontramos el input para el correo y lo rellenamos
		WebElement login = driver.findElement(By.id("email"));
		
		login.click();
		login.clear();
		login.sendKeys(loginValue);
		
		ThreadUtil.wait(500);
		//(2) encontramos el input para la password y lo rellenamos
		WebElement password = driver.findElement(By.id("password"));
		
		password.click();
		password.clear();
		password.sendKeys(passwordValue);
		
		ThreadUtil.wait(500);
		//(3) encontramos el boton de login y clicamos en el
		driver.findElement(By.id("boton_login")).click();
		
		ThreadUtil.wait(700);
		
	}

}
