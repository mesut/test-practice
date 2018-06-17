package com.mstzn.testpractice.e2e;

import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductGetE2ESeleniumTest {

    private WebDriver driver;

    @LocalServerPort
    private int port;

    @BeforeClass
    public static void setUpClass() {
        FirefoxDriverManager.getInstance().setup();
    }

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
    }
    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void it_should_get_empyt_response_when_product_not_foundl() {
        driver.navigate().to(String.format("http://localhost:%s/product/elbise", port));

        WebElement body = driver.findElement(By.tagName("body"));

        assertThat(body.getText()).isEmpty();
    }
}
