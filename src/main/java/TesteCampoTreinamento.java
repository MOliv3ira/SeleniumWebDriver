import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TesteCampoTreinamento {

    private WebDriver driver;

    @Before
    public void inicializa() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        Thread.sleep(4000);
    }

    @After
    public void finalizar() throws InterruptedException {
        driver.quit();
        Thread.sleep(4000);
    }


    @Test
    public void testTextField() {

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
        Assert.assertEquals("Teste de escrita", driver.findElement(By.id("elementosForm:nome"))
                .getAttribute("value"));

    }

    @Test
    public void testTextArea() {
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste");
        Assert.assertEquals("Teste", driver.findElement(By.id("elementosForm:sugestoes"))
                .getAttribute("value"));


    }

    @Test
    public void testRadioButton() {
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());


    }

    @Test
    public void testCheckBox() {
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());


    }

    @Test
    public void testCombo() {
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
//        combo.selectByIndex(2);
//        combo.selectByValue("1grauincomp");
        combo.selectByVisibleText("Superior");
        Assert.assertEquals("Superior", combo.getFirstSelectedOption().getText());


    }

    @Test
    public void testDeveVerificarValoresCombo() {
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        Assert.assertEquals(8, options.size());

        Boolean encontrou = false;
        for (WebElement option : options) {
            if (option.getText().equals("Mestrado")) {
                encontrou = true;
                break;
            }
        }
        Assert.assertTrue(encontrou);

    }

    @Test
    public void testDeveVerificarValoresComboMultiplo() {
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);

        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("Corrida");
        combo.selectByVisibleText("O que eh esporte?");

        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(3, allSelectedOptions.size());

        combo.deselectByVisibleText("Corrida");
        allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(2, allSelectedOptions.size());


    }

    @Test
    public void testDeveInteragirComBotoes() {
        WebElement botao = driver.findElement(By.id("buttonSimple"));
        botao.click();

        Assert.assertEquals("Obrigado!", botao.getAttribute("value"));

    }

    @Test
    public void testDeveInteragirComLinks() {
        driver.findElement(By.linkText("Voltar")).click();
        Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());
//        Assert.fail();

    }

    @Test
    public void testDeveBuscarTextosNaPagina() {
        Assert.assertEquals("Campo de Treinamento",
                driver.findElement(By.tagName("h3")).getText());

        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",
                driver.findElement(By.className("facilAchar")).getText());


    }

}
