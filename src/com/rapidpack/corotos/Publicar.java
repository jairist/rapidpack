package com.rapidpack.corotos;

import static java.lang.Thread.sleep;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import com.rapidpack.entity.Publicacion;
import com.rapidpack.util.Utileria;

public class Publicar {

	WebDriver driver = null;

	public Publicar() {
		super();
		
		this.driver = new FirefoxDriver();
		// System.out.println(getClass().getName());
		// if you didn't update the Path system variable to add the full directory path
		// to the executable as above mentioned then doing this directly through code
		System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		// Now you can Initialize marionette driver to launch firefox
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);

	}

	public void login(String usuario, String pass) {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		try {

			driver.navigate().to("https://www.corotos.com.do/account/login");
			sleep(5);
			driver.findElement(By.id("email")).sendKeys(usuario);
			sleep(3);
			driver.findElement(By.id("passwd")).sendKeys(pass);
			sleep(3);
			driver.findElement(By.id("login")).click();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public void crearAnuncio(String TituloAnuncio, String descripcion, String precio, String rutaImagen) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		try {
			driver.findElement(By.id("header_ai_btn")).click();
			Select oSelect = new Select(driver.findElement(By.id("category_group")));
			oSelect.selectByVisibleText("Celulares");
			sleep(3);
			driver.findElement(By.id("subject")).sendKeys(TituloAnuncio);
			sleep(3);
			driver.findElement(By.id("body")).sendKeys(descripcion);
			sleep(3);
			driver.findElement(By.id("price")).sendKeys(precio);
			// sleep(3);
			// driver.findElement(By.className("imageupload")).click();
			sleep(3);
			driver.findElement(By.className("imageupload")).sendKeys(rutaImagen);

			sleep(10);
			driver.findElement(By.id("validate")).click();
			sleep(3);
			driver.findElement(By.id("create")).click();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public void cerrarSesion() {

		try {
			driver.findElement(By.id("btnLoggedUserDropdown")).click();
			sleep(3);
			driver.findElement(By.id("my_ads_navbar_logout")).click();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void main(String[] args) {

		Publicar publicar = new Publicar();
		ArrayList<Publicacion> publicaciones = new ArrayList<>();

		Utileria util = new Utileria();
		publicaciones = util.cargarPublicaciones();

		for (int i = 0; i <= publicaciones.size(); i++) {
			//borro la primera fila del arreglo ya que esta solo contiene los nombre de la conlumnas.
			publicaciones.remove(publicaciones.get(i));
			//iniciar publicacion.			
			publicar.login(publicaciones.get(i).getUsuario(), publicaciones.get(i).getPassword());
			publicar.crearAnuncio(publicaciones.get(i).getTitulo(), publicaciones.get(i).getDescripcion(), publicaciones.get(i).getPrecio(),
					publicaciones.get(i).getPrecio());
			//publicar.cerrarSesion();

		}
		

	}

}
