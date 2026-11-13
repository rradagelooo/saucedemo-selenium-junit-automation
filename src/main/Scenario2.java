package main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.latest.preload.model.PrefetchStatus;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Scenario2 {
	
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
	public void testCheckoutUntilComplete() {
		WebElement userInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
		userInput.sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		wait.until(ExpectedConditions.urlContains("inventory.html"));
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack"))).click();
		driver.findElement(By.className("shopping_cart_link")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name"))).sendKeys("Dyah");
		driver.findElement(By.id("last-name")).sendKeys("Ayu");
		driver.findElement(By.id("postal-code")).sendKeys("12345");
		driver.findElement(By.id("continue")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("finish"))).click();
		
		WebElement completeHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));
		assertTrue(driver.getCurrentUrl().contains("checkout-complete.html"), "URL harus mengarah ke checkout-complete");
		assertEquals("Thank you for your order!", completeHeader.getText());
	}
	@AfterEach
	public void tearDown() {
		if(driver != null) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			driver.quit();
		}
	}
}
