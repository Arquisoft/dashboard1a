package cucumber.test.steps;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Entonces;
import cucumber.util.DriverGenerator;
import selenium.page_objects.PO_LoginForm;
import utils.ThreadUtil;

public class LoginFormatoMailIncorrectoSteps {

	//private WebDriver driver = DriverGenerator.generarDriverFirefoxPortable();
	//private WebDriver driver = DriverGenerator.generarDriverFirefox();
	private WebDriver driver = DriverGenerator.generarDriverHTML();
	
	@Cuando("^el usuario se encuentra en la pagina de login$")
	public void el_usuario_se_encuentra_en_la_pagina_de_login() {
		driver.navigate().to("http://localhost:8090/");
	}

	@Entonces("^inserta su mail incorrecto \"([^\"]*)\" y su password \"([^\"]*)\"$")
	public void inserta_su_mail_incorrecto_y_su_password(String name, String password) throws Throwable {
		new PO_LoginForm().completeForm(driver, name, password);
	}

	@Entonces("^se logea de manera incorrecta$")
	public void se_logea_de_manera_incorrecta() {
		//assertTrue("Titulo de pagina no coincide", driver.getTitle().equals("Login"));

		
		
		ThreadUtil.wait(500);
		driver.quit();
	}
	
	
}
