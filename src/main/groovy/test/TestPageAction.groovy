package test

import com.google.gson.JsonObject
import geb.Browser
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities

/**
 * Created by Luke on 18/09/2017.
 */
class TestPageAction {

    void logInNoHoverfly() {
        Browser browser = new Browser()
        Browser.drive(browser) {
            to TestPage

            withNewWindow({ logInButton.click() }, wait: 10, page: LogonPage, close: false) {
                emailField.value("bob")
                passwordField.value("bob")
                logOnButton.click()
            }
        }
    }

    void logInWithHoverfly(int portNumber) {
        Browser browser = new Browser()
        JsonObject json = new JsonObject()
        json.addProperty("proxyType", "manual")
        //Commented out as application is HTTPS
        //json.addProperty("httpProxy", "localhost")
        //json.addProperty("httpProxyPort", portNumber)
        json.addProperty("sslProxy", "localhost")
        json.addProperty("sslProxyPort", portNumber)

        DesiredCapabilities cap = new DesiredCapabilities()
        cap.setCapability("proxy", json)
        cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true)
        //This line is commented out as it appears to do nothing
        //cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true)
        browser.driver = new FirefoxDriver(cap)
        Browser.drive(browser) {
            to TestPage

            withNewWindow({ logInButton.click() }, wait: 10, page: LogonPage, close: false) {
                emailField.value("bob")
                passwordField.value("bob")
                logOnButton.click()
            }
        }

    }
}
