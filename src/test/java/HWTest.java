import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class HWTest {
    String email = "atrostyanko+master@gmail.com";
    String password = "QqtRK9elseEfAk6ilYcJ";
    String namePageProject = "All Projects";
    String nameProject  = "PR02";
    String namePageAddProject = "Add Project";



    private By EMAILSELECTOR = By.id("name");
    private By PASSWORDSELECTOR = By.name("password");
    private By LOGINBUTTONSELECTOR = By.id("button_primary");
    private By NAMEPROGECTSELECTOR = By.id("name");
    private By PROJECTSELECTOR = By.className("hoverSensitive");
    private By ADDPROJECTBUTTON = By.cssSelector(".button-add");

    @Test
    public void login() {
        Configuration.baseUrl = "https://aqa5master.testrail.io";
        Configuration.startMaximized = true;
       // Configuration.headless = true;
       // Configuration.fastSetValue = true;
        open("/");

        $(EMAILSELECTOR).setValue(email);
        $(PASSWORDSELECTOR).setValue(password);
        $(LOGINBUTTONSELECTOR).submit();

        $(".page_title").shouldHave(text(namePageProject));
    }

    @Test(dependsOnMethods = "login")
    public void checkingTheProject() {
        open("/index.php?/admin/projects/overview");

        $$(PROJECTSELECTOR).shouldHaveSize(3).find(text(nameProject)).find(By.tagName("a")).click();
        $(NAMEPROGECTSELECTOR).shouldHave(value(nameProject));
    }

    @Test(dependsOnMethods = "checkingTheProject")
    public void addProject() {
        open("/index.php?/admin/projects/overview");

        $(ADDPROJECTBUTTON).click();
        $(".page_title").shouldHave(text(namePageAddProject));
    }
}
