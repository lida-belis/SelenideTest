import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class FirstTest {
    @Test
    public void userCanLoginByUsername() {
//       Default log4j configuration
//        org.apache.log4j.BasicConfigurator.configure();

//       Selenide configuration
        Configuration.baseUrl = "https://aqa5master.testrail.io";
//        Configuration.startMaximized = true;
//        Configuration.browser = "firefox";
//        Configuration.headless = true;
//        Configuration.fastSetValue = true;

        open("/");

        $(By.id("name")).setValue("atrostyanko+master@gmail.com");
        $(By.name("password")).setValue("QqtRK9elseEfAk6ilYcJ");
        $(By.id("button_primary")).click();

        $(".page_title").shouldBe(visible);
        $(".page_title").shouldHave(text("All Projects"));

        open("/index.php?/admin/projects/overview");
        $$(By.className("hoverSensitive")).shouldHaveSize(2).find(text("PR03")).find(By.tagName("a")).click();

        $(By.name("name")).shouldHave(value("PR03"));

//        $(".button-add").should(appear).click();
//        $(".button-add").shouldBe(disappear);

    }
}
