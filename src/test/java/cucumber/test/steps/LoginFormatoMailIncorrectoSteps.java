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

	private WebDriver driver = DriverGenerator.generarDriverFirefoxPortable();
	
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
		assertTrue("Titulo de pagina no coincide", driver.getTitle().equals("Login"));

		WebElement texto = driver.findElement(By.id("inputEmail"));
		assertTrue("placeholder no coincide", texto.getAttribute("placeholder").equals("Email address"));

		texto = driver.findElement(By.id("inputPassword"));
		assertTrue("placeholder no coincide", texto.getAttribute("placeholder").equals("Password"));

		texto = driver.findElement(By.id("boton_login"));
		assertTrue("texto del boton no coincide", texto.getText().equals("Sign in"));
		
		ThreadUtil.wait(500);
		driver.quit();
	}
	
	
}
