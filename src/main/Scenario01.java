package main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Scenario01 {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	@BeforeEach
	public void setUp() {
		ChromeOptions options = new ChromeOptions();
		
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("profile.password_manager_leak_detection", false);
		
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("--disable-features=PasswordLeakDetection");
		
		
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		driver.get("https://www.saucedemo.com/");
	}
	
	@Test
	public void testLoginAndVerifyProductDetail() {
		WebElement userInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
		userInput.sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		
		wait.until(ExpectedConditions.urlContains("inventory.html"));
		assertTrue(driver.getCurrentUrl().contains("inventory.html"), "User seharusnya masuk ke halaman inventory");
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("item_4_title_link"))).click();
		
		WebElement pTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".inventory_details_name.large_size")));
		WebElement pDesc = driver.findElement(By.cssSelector(".inventory_details_desc.large_size"));
		
		assertEquals("Sauce Labs Backpack", pTitle.getText());
		assertTrue(!pDesc.getText().isEmpty(), "Deskripsi produk tidak boleh kosong");
	}
	
	@AfterEach
	public void tearDown() {
		if(driver != null) {
			driver.quit();
		}
	}
}
