import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * test class
 */
public class Test01 extends SelenideTestBase {

    private String google_id = "taeko.kazuo@gmail.com";
    private String google_pw = "Tobi0310";
    private String pic_path = "S:\\dev\\pic";

    /**
     * Test Method
     */
    @Test
    public void test_01() throws IOException {

        // make pic file list
        String str =  makeFileList(pic_path);
        if(str == "") {
            JOptionPane.showMessageDialog(null, "[err] there is no pic file.");
            return;
        }

        // open chrome browser
        open("https://photos.google.com/?pli=1");
        // waiting for stability
        waitUntilElementDisplayed("#js-hero-btn");

        // move login page
        $( "#js-hero-btn").click();
        // input id
        $( "#identifierId").setValue(google_id);
        // push button to move pw page
        $( "#identifierNext").click();
        // input password
        $( "input[type='password']").setValue(google_pw);
        // push button to move photo page
        $( "#passwordNext").click();
        // waiting for stability
        //waitUntilElementDisplayed("span[class='EIug8e']");

        // push upload button
        //$("div[class='gb_Nc']").click();
        $("div[class='m6aMje m10N5']").click();

        // copy filelists to clipboard
        StringSelection ss = new StringSelection("S:\\pic\\neko.jpg");  // now, support 1 file only.
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

        sleep(2000);

        // google photo main page
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch(AWTException e) {
        }

        // end.
        sleep(10000);
    }
}
