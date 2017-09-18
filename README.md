PLEASE NOTE: Before attempting to run the test please install the relevant Firefox webdriver from one of the below URLs:

Windows:
https://github.com/mozilla/geckodriver/releases/download/v0.18.0/geckodriver-v0.18.0-win64.zip
Mac:
https://github.com/mozilla/geckodriver/releases/download/v0.18.0/geckodriver-v0.18.0-macos.tar.gz
Linux:
https://github.com/mozilla/geckodriver/releases/download/v0.18.0/geckodriver-v0.18.0-linux64.tar.gz OR
https://github.com/mozilla/geckodriver/releases/download/v0.18.0/geckodriver-v0.18.0-linux32.tar.gz

Uncompress the download and you should end up with a file called geckodriver or geckodriver.exe. This needs to be copied to ./src/main/resources

(If you are using Windows you will need to add a .exe to line 15 of HoverflyTest)

To execute test without Hoverfly please execute the command:

./gradlew clean test -Dtest.hoverfly=false

You should see Firefox start and eventually a pop up appears into which an incorrect username/password combination is entered and the error message:

`Sorry, we could not authenticate you. Try again.`

appears as per below:

![Alt text](./src/main/resources/WeCouldNotAuthenticateYou.png?raw=true "Pop Up Correct Error")

Then try the version with Hoverfly by executing the command:

./gradlew clean test -Dtest.hoverfly=true

NB: I have tried to follow some instructions on opencredo.com [here](https://opencredo.com/simulating-github-oauth2-login-junit-webdriver-hoverfly/) which seem to imply that by telling Firefox to ACCEPT_SSL_CERTS that Hoverfly can still record.

When the test works properly you will see the error `Sorry, we could not authenticate you. Try again.` appear in the Popup

Eventually you should get a timeout from the Geb framework and an exception thrown like this:

`HoverflyTest > testHoverfly FAILED
     geb.waiting.WaitTimeoutException at HoverflyTest.java:33
         Caused by: geb.waiting.WaitTimeoutException at HoverflyTest.java:33
             Caused by: geb.waiting.WaitTimeoutException at HoverflyTest.java:33
                 Caused by: org.codehaus.groovy.runtime.powerassert.PowerAssertionError at HoverflyTest.java:33`

as the Geb framework is not able to access the popUp due to SSL issues?

My current issues:

1. When the pop up appears, Hoverfly is not able to record. I get the error:

`Oops! Error occurred!
invalid_request
Client id is required.`

![Alt text](./src/main/resources/PopUpError.png?raw=true "Pop Up Error")

2. If I comment out the line:

`cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true)`

then the original landing page doesn't even load correctly and I get this error:

`Your connection is not secure
The owner of tenant-picker-v1.us-east.stage.cf.yaas.io has configured their web site improperly. To protect your information from being stolen, Firefox has not connected to this web site.`

![Alt text](./src/main/resources/LandingPageError.png?raw=true "Landing Page Error")

