package selenium;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

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

//@Clean posibles modificaciones en los id al tener prime faces
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTest {

	// NAVEGADOR INTERNO
	// private static WebDriver driver = new HtmlUnitDriver();
	// FIREFOX
	private static WebDriver driver = new FirefoxDriver();

	// private static WebDriver driver = getDriver();
	private static String URLInicio = "http://localhost:8090";

	/*
	 * Si alguno teneis un firefox portable y quereis lanzar ese mismo
	 * descomentar el driver comentado e importar las librerias reocordar que se
	 * puede definir un buscador por defecto en preferences web Browser
	 * 
	 */
	// public static WebDriver getDriver() {
	// File pathToBinary = new File("");// poner ruta al firefox portable/al
	// // firefox de nuestro ordenador
	//
	// FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
	// FirefoxProfile firefoxProfile = new FirefoxProfile();
	//
	// return driver = new FirefoxDriver(ffBinary, firefoxProfile);
	// }

	@Before
	public void setUp() {
		driver.navigate().to(URLInicio);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	@AfterClass
	static public void end() {
		// Espera para que la última prueba borre las cookies
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.quit();
	}

	/*
	 * ========= PRUEBAS =========
	 */

	// @Complete : añadir usuarios para poder probar la aplicacion
	@Test
	public void t1_testLoginPoliticoCorrecto() {
		// (1) comrpobamos que estamos en el login


		assertTrue("Titulo de pagina no coincide", driver.getTitle().equals("Login"));

		WebElement texto = driver.findElement(By.id("inputEmail"));
		assertTrue("placeholder no coincide", texto.getAttribute("placeholder").equals("Email address"));

		texto = driver.findElement(By.id("inputPassword"));
		assertTrue("placeholder no coincide", texto.getAttribute("placeholder").equals("Password"));

		texto = driver.findElement(By.id("boton_login"));
		assertTrue("texto del boton no coincide", texto.getText().equals("Sign in"));

		// (2) rellenamos el formulario correctamente

		new PO_LoginForm().completeForm(driver, "paco@hotmail.com", "123456");
		// (3) esperamos a que cargue la página principal del politico
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		// (4) comprobamos que estamos en la página principal de los politicos

	}

	@Test
	public void t2_testLoginPoliticoIncorrecto() {
		// (1) Comprobamos que estamos en la página de login

		assertTrue("Titulo de pagina no coincide", driver.getTitle().equals("Login"));

		WebElement texto = driver.findElement(By.id("inputEmail"));
		assertTrue("placeholder no coincide", texto.getAttribute("placeholder").equals("Email address"));

		texto = driver.findElement(By.id("inputPassword"));
		assertTrue("placeholder no coincide", texto.getAttribute("placeholder").equals("Password"));

		texto = driver.findElement(By.id("boton_login"));
		assertTrue("texto del boton no coincide", texto.getText().equals("Sign in"));

		// (2) rellenamos el formulario con datos no válidos
		new PO_LoginForm().completeForm(driver, "noexisto@hotmail.com", "asdfghjl");
		// (3) comprobamos que estemos en la página de error
		WebElement mensajeError = driver.findElement(By.id("error_div"));
		assertTrue("El mensaje de error no coincide",
				mensajeError.getText().equals("Ha ocurrido el siguiente error: User not found"));

	}

	@Test
	public void t3_testLoginFormatoEmailIncorrecto() {
		// (1) Comprobamos que estamos en la página de login

		assertTrue("Titulo de pagina no coincide", driver.getTitle().equals("Login"));

		WebElement texto = driver.findElement(By.id("inputEmail"));
		assertTrue("placeholder no coincide", texto.getAttribute("placeholder").equals("Email address"));

		texto = driver.findElement(By.id("inputPassword"));
		assertTrue("placeholder no coincide", texto.getAttribute("placeholder").equals("Password"));

		texto = driver.findElement(By.id("boton_login"));
		assertTrue("texto del boton no coincide", texto.getText().equals("Sign in"));

		// (2) rellenamos el formulario con un email no valido
		new PO_LoginForm().completeForm(driver, "noexistohotmailcom", "asdfghjl");
		
		
	}

}
