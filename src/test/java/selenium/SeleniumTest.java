package selenium;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import selenium.page_objects.PO_LoginForm;
import utils.ThreadUtil;

//@Clean posibles modificaciones en los id al tener prime faces
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTest {

	// NAVEGADOR INTERNO
	// private static WebDriver driver = new HtmlUnitDriver();

	private static WebDriver driver = new FirefoxDriver();

	// private static WebDriver driver = getDriver();
	private static String URLInicio = "http://localhost:8090";

	/*
	 * Si alguno teneis un firefox portable y quereis lanzar ese mismo
	 * descomentar el driver comentado e importar las librerias reocordar que se
	 * puede definir un buscador por defecto en preferences web Browser
	 *
	 * public static WebDriver getDriver() { File pathToBinary = new
	 * File("");//poner ruta al firefox portable/al firefox de nuestro ordenador
	 * 
	 * FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary); FirefoxProfile
	 * firefoxProfile = new FirefoxProfile();
	 * 
	 * return driver = new FirefoxDriver(ffBinary, firefoxProfile); }
	 */

	@Before
	public void setUp() {
		driver.navigate().to(URLInicio);
	}

	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	@AfterClass
	static public void end() {
		// Espera para que la última prueba borre las cookies
		ThreadUtil.wait(2000);

		driver.quit();
	}

	/*
	 * ========= PRUEBAS =========
	 */

	// @Complete : añadir usuarios para poder probar la aplicacion
	@Test
	public void t1_testLoginPolitico() {
		// (1) comrpobamos que estamos en el login
		WebElement label = driver.findElement(By.id("email_label"));
		assertTrue("El texto no coincide", label.getText().equals("Usuario:"));
		label = driver.findElement(By.id("password_label"));
		assertTrue("El texto no coincide", label.getText().equals("Contraseña:"));
		// (2) partimos de la pantalla de login
		new PO_LoginForm().completeForm(driver, "paco@hotmail.com", "123456");
		// (3) esperamos a que cargue la página principal del politico
		ThreadUtil.wait(600);
		// (4) comprobamos que estamos en la página principal de los politicos

	}

	@Test
	public void t2_testEntrarEnTarea() {
		// assertTrue("Sin implemtenar", false);
	}

}
