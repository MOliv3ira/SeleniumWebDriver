import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFramesEJanelas {

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
    public void testDeveInteragirComFrames() {

        driver.switchTo().frame("frame1");
        driver.findElement(By.id("frameButton")).click();
        Alert alert = driver.switchTo().alert();
        String message = alert.getText();
        Assert.assertEquals("Frame OK!", message);
        alert.accept();

        driver.switchTo().defaultContent(); //defaultContent() ESTE MÉTODO TRAZ O FOCO PARA A APLICAÇÃO PRINCIPAL
        driver.findElement(By.id("elementosForm:nome")).sendKeys(message);

    }

    @Test
    public void deveInteragirComJanelas(){

        driver.findElement(By.id("buttonPopUpEasy")).click();
        driver.switchTo().window("Popup");
        driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
        driver.close();
        driver.switchTo().window("");
        driver.findElement(By.tagName("textarea")).sendKeys("e agora?");

    }

    @Test
    public void deveInteragirComJanelasSemTitulo(){

        driver.findElement(By.id("buttonPopUpHard")).click();
        System.out.println(driver.getWindowHandle());
        System.out.println(driver.getWindowHandles());
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
        driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
        driver.findElement(By.tagName("textarea")).sendKeys("E agora?");

    }
}
