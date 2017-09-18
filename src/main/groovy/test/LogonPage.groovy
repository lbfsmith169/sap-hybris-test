package test

import geb.Page
import org.openqa.selenium.By

class LogonPage extends Page {

    static at = { success == true
        title == "YaaS: Log On" }

    static content = {
        emailField(required: true, wait: true) { $(By.xpath("//*[@id=\"j_username\"]")) }
        passwordField(required: true, wait: true) { $(By.xpath("//*[@id=\"j_password\"]")) }
        logOnButton(required: true, wait: true) { $(By.xpath("//*[@id=\"logOnFormSubmit\"]")) }
        success(wait: true) { emailField.isDisplayed() }
    }

}
