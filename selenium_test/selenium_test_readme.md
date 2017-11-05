# Selenium Testing Project
This project is one of several demo code that I use regularly my
Agile Engineering Practice (AEP) class. This is a 3-day class that is
a partial fulfillment of becoming a Certified Scrum Developer (CSD) by
the Scrum Alliance.

## Introduction
The purpose of this project is to demonstrate a basic capability that
an engineer can use to develop test automation for a web application
using a popular chrome web driver.

I am using the following software:

+ Ubuntu LTS 16.4
+ Jetbrains IntelliJ CE for Ubuntu
+ Java 8 JDK from Oracle
+ Selenium for Java version 3.7.0
+ JUnit 4.12
+ Chrome driver for Ubuntu version 2.33.506092 - I use the following
script to download and install the chromedriver at /usr/local/bin
folder see notes at the end.

## How it works:
I use JUnit test to write automated test procedures using Selenium web
driver. In order to run this, you must provide the following JVM's
arguments:

    -Dwebdriver.driver=chrome -Dwebdriver.chrome.driver=/usr/local/bin/chromedriver

Start with this test method:

    AdoptingOnePetTest.adoptingOnePetTest()

In this method, I set up a behavior-driven test like this:

    given_I_am_at_Puppy_Adoption_Agency_website();
        visit(url)
    when_I_adopt_and_pay_for_a_pet();
        I_click_on_view_details_for_a_pet(petName);
        I_click_on_adopt_me_button();
        I_click_on_complete_the_adoption_button();
        I_fill_out_the_payment_detail("John Doe", "123 Main Street, New York, NY 10001", "jdoe@mail.com", "Check");
        I_click_on_place_order_button();
    then_I_should_see_a_thank_you_message()
        getNotice();
        assertEquals(expectedNotice, actualNotice)

Each of these three methods consists of multiple steps to perform the
test by interacting with the Selenium web driver (i.e., Chrome Driver).

## Miscellaneous
The following bash scripts can be found here: https://gist.github.com/ziadoz/3e8ab7e944d02fe872c3454d17af31a5

+ install.sh

```bash
#!/usr/bin/env bash
# https://developers.supportbee.com/blog/setting-up-cucumber-to-run-with-Chrome-on-Linux/
# https://gist.github.com/curtismcmullan/7be1a8c1c841a9d8db2c
# http://stackoverflow.com/questions/10792403/how-do-i-get-chrome-working-with-selenium-using-php-webdriver
# http://stackoverflow.com/questions/26133486/how-to-specify-binary-path-for-remote-chromedriver-in-codeception
# http://stackoverflow.com/questions/40262682/how-to-run-selenium-3-x-with-chrome-driver-through-terminal
# http://askubuntu.com/questions/760085/how-do-you-install-google-chrome-on-ubuntu-16-04

# Versions
CHROME_DRIVER_VERSION=`curl -sS chromedriver.storage.googleapis.com/LATEST_RELEASE`
SELENIUM_STANDALONE_VERSION=3.4.0
SELENIUM_SUBDIR=$(echo "$SELENIUM_STANDALONE_VERSION" | cut -d"." -f-2)

# Remove existing downloads and binaries so we can start from scratch.
rm ~/google-chrome-stable_current_amd64.deb
rm ~/selenium-server-standalone-*.jar
rm ~/chromedriver_linux64.zip
sudo rm /usr/local/bin/chromedriver
sudo rm /usr/local/bin/selenium-server-standalone.jar

# Install dependencies.
sudo apt-get update
sudo apt-get install -y unzip openjdk-8-jre-headless xvfb libxi6 libgconf-2-4

# Install Chrome.
wget -N https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb -P ~/
sudo dpkg -i --force-depends ~/google-chrome-stable_current_amd64.deb
sudo apt-get -f install -y
sudo dpkg -i --force-depends ~/google-chrome-stable_current_amd64.deb

# Install ChromeDriver.
wget -N http://chromedriver.storage.googleapis.com/$CHROME_DRIVER_VERSION/chromedriver_linux64.zip -P ~/
unzip ~/chromedriver_linux64.zip -d ~/
rm ~/chromedriver_linux64.zip
sudo mv -f ~/chromedriver /usr/local/bin/chromedriver
sudo chown root:root /usr/local/bin/chromedriver
sudo chmod 0755 /usr/local/bin/chromedriver

# Install Selenium.
wget -N http://selenium-release.storage.googleapis.com/$SELENIUM_SUBDIR/selenium-server-standalone-$SELENIUM_STANDALONE_VERSION.jar -P ~/
sudo mv -f ~/selenium-server-standalone-$SELENIUM_STANDALONE_VERSION.jar /usr/local/bin/selenium-server-standalone.jar
sudo chown root:root /usr/local/bin/selenium-server-standalone.jar
sudo chmod 0755 /usr/local/bin/selenium-server-standalone.jar
```
start-chrome.sh:

```bash
#!/usr/bin/env bash

# Run Chrome via Selenium Server
start-chrome() {
    xvfb-run java -Dwebdriver.chrome.driver=/usr/local/bin/chromedriver -jar /usr/local/bin/selenium-server-standalone.jar
}

start-chrome-debug() {
    xvfb-run java -Dwebdriver.chrome.driver=/usr/local/bin/chromedriver -jar /usr/local/bin/selenium-server-standalone.jar -debug
}

# Run Chrome Headless
start-chrome-headless() {
    chromedriver --url-base=/wd/hub
}

# Start
start-chrome
# start-chrome-debug
# start-chrome-headless
```
