import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteAlert {

    private WebDriver driver;

    @Before
    public void inicializa() {
        driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
    }

    @After
    public void finalizar() {
        driver.quit();
    }

    @Test
    public void testDeveInteragirComAlertSimples() {

        driver.findElement(By.id("alert")).click();

        Alert alert = driver.switchTo().alert();
        String text = alert.getText();

        Assert.assertEquals("Alert Simples", text);
        alert.accept();

        driver.findElement(By.id("elementosForm:nome")).sendKeys(text);

    }

    @Test
    public void testDeveInteragirComAlertConfirm() {

        driver.findElement(By.id("confirm")).click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Confirm Simples", alert.getText());
        alert.accept();
        Assert.assertEquals("Confirmado", alert.getText());
        alert.accept();
    }

    @Test
    public void testDeveInteragirComAlertConfirmNegado() {
        driver.findElement(By.id("confirm")).click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Confirm Simples", alert.getText());
        alert.dismiss();
        Assert.assertEquals("Negado", alert.getText());
        alert.accept();

    }

    @Test
    public void testDeveInteragirComAlertPrompt() {

        driver.findElement(By.id("prompt")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Digite um numero", alert.getText());
        alert.sendKeys("12");
        alert.accept();

        Assert.assertEquals("Era 12?", alert.getText());
        alert.accept();

        Assert.assertEquals(":D", alert.getText());
        alert.accept();

    }
}
