package cucumber.test.steps;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Entonces;
import cucumber.util.DriverGenerator;
import selenium.page_objects.PO_LoginForm;
import utils.ThreadUtil;

public class LoginPoliticoSteps {

	//private WebDriver driver = DriverGenerator.generarDriverFirefoxPortable();
	private WebDriver driver = DriverGenerator.generarDriverFirefox();
	//private WebDriver driver = DriverGenerator.generarDriverHTML();
	
	@Cuando("^el administrador se encuentra en la pagina de login$")
	public void el_administrador_se_encuentra_en_la_pagina_de_login() {
		driver.navigate().to("http://localhost:8090/");
		assertTrue("titulo coincide",driver.getTitle().equals("Login"));
	}

	@Entonces("^inserta su mail \"(.+)\" y su password \"(.+)\"$")
	public void inserta_su_mail_y_su_password(String name, String password) {
		new PO_LoginForm().completeForm(driver, name, password);

	}

	@Entonces("^se logea de manera correcta$")
	public void se_logea_de_manera_correcta() {
		assertTrue("Titulo no se corresponde", driver.getTitle().equals("Dashboard"));
		assertTrue("Subtitulo no tiene el mismo texto",
				driver.findElement(By.cssSelector("h2.sub-header")).getText().equals("Suggestions"));

		ThreadUtil.wait(500);
		driver.quit();
	}

}
