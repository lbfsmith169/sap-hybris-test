package test

import geb.Page
import org.openqa.selenium.By

/**
 * Created by i331123 (Luke) on 18/09/2017.
 */
class TestPage extends Page {

    static url = "https://tenant-picker-v1.us-east.stage.cf.yaas.io"

    static at = { waitFor(message:"Did not reach Page") {success == true}
        waitFor(message:"Title not right") {title == "Conversion Authentication" }}

    static content = {
        logInButton(required: true, wait: true) { $("[data-ui-name='login']") }
        sitesContainer(required: true, wait: true) { $(By.xpath("/html/body/app-root/ng-component/cs-tenant-picker/div"))}
        success(wait: true) { sitesContainer.isDisplayed() }
    }
}
