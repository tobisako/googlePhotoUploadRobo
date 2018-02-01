import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.BeforeMethod;

import javax.annotation.processing.FilerException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Test Base Class
 */
public class SelenideTestBase {
    @BeforeMethod
    public static void testBefore() {
        // Chrome driver
        Configuration.browser = WebDriverRunner.CHROME;
        Configuration.startMaximized = false;
        Configuration.fastSetValue = true;
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver/chromedriver.exe");
    }

    /**
     * wait until Element visible.
     */
    protected void waitUntilElementDisplayed(SelenideElement elements) {
        while (true) {
            if (elements.isDisplayed()) {
                break;
            }
            sleep(500);
        }
    }
    protected void waitUntilElementDisplayed(String cssSelector) {
        waitUntilElementDisplayed($(cssSelector));
    }

    /**
     * make file list
     */
    protected String makeFileList(String path) {
        String str = "";
        File dir = new File(path);
        File[] files1 = dir.listFiles();
        if(files1 == null) {
            return str; // no dir.
        }
        for (int i = 0; i < files1.length; i++) {
            File file = files1[i];
            if (files1[i].isFile()) {
                //ファイル名表示
                str += "\"" + file + "\" ";
                System.out.println(file);
                //l.add(file)
            } else if (files1[i].isDirectory()) {
                //ディレクトリ名表示(※１)
                System.out.println(file);
            }
        }
        return str;
    }
}
