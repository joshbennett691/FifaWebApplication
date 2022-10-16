import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Test{
    enum Actions {
        Click,
        SendKeys,
    }

    public static void performAction(WebDriver driver, String path, WebDriverWait wait, boolean isList, Actions action, String keys, int index){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
            if (!isList) {
                WebElement e = driver.findElement(By.xpath(path));
                if (action.equals(Actions.Click)){
                    e.click();
                }
                else if (action.equals(Actions.SendKeys)){
                    e.sendKeys(keys);
                }
            }
            else if (isList){
                List<WebElement> e = driver.findElements(By.xpath(path));
                if (action.equals(Actions.Click)){
                    e.get(index).click();
                }
                else if (action.equals(Actions.SendKeys)){
                    e.get(index).sendKeys(keys);
                }
            }
        } catch(org.openqa.selenium.TimeoutException t) {
            ;
        }

    }

    public static void shadowSnipe(boolean active, WebDriver driver, WebDriverWait wait, WebDriverWait instantWait,
                                   String chemBuyNow, int numberOfSearches, String coinBalance){
        while(active){
            // Initial Setup

            // Click transfer button
            performAction(driver, "//*[@class='ut-tab-bar-item icon-transfer']", wait, false, Actions.Click,"", 0);
            // Click search transfer market button
            performAction(driver, "//*[@class='tileContent']", wait, false, Actions.Click, "", 0);
            // Click consumables tab
            performAction(driver, "//button[text()='Consumables']", wait, false, Actions.Click, "", 0);
            // Click consumable type
            performAction(driver, "//span[text()='Position Change']", wait, false, Actions.Click, "", 0);
            // Click Chemistry Styles
            performAction(driver, "//li[text()='Chemistry Styles']", wait, false, Actions.Click, "", 0);
            // Click specific chem style
            performAction(driver, "/html/body/main/section/section/div[2]/div/div[2]/div/div[1]/div[1]/div[4]/div/div/img", wait, false, Actions.Click, "", 0);
            // Select shadow
            performAction(driver, "/html/body/main/section/section/div[2]/div/div[2]/div/div[1]/div[1]/div[4]/div/ul/li[20]", wait, false, Actions.Click, "", 0);
            // Enter chem styles buy now price
            performAction(driver, "//*[@class='ut-number-input-control']", wait, true, Actions.SendKeys, chemBuyNow, 3);

            // Loop
            for (int i = 0; i < numberOfSearches; i++){
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='view-navbar-currency-coins']")));
                coinBalance = driver.findElement(By.xpath("//*[@class='view-navbar-currency-coins']")).getText();
                coinBalance = coinBalance.replace(",", "");
                if (Integer.parseInt(coinBalance) < Integer.parseInt(chemBuyNow)){
                    active = false;
                    break;
                }
                // Click search button
                performAction(driver, "//*[@class='btn-standard call-to-action']", wait, false, Actions.Click, "", 0);
                // Click buy now
                performAction(driver, "//*[@class='btn-standard buyButton currency-coins']", instantWait, false, Actions.Click, "", 0);
                // Confirm buy now
                performAction(driver, "//*[@class='ea-dialog-view--body']//*[@class='ut-button-group']//*[not(@class)]", instantWait, false, Actions.Click, "", 0);
                // Go back
                performAction(driver, "//*[@class='ut-navigation-button-control']", wait, false, Actions.Click, "", 0);
                // increment bid price
                performAction(driver, "//*[@class='btn-standard increment-value']", wait, true, Actions.Click, "", 0);

            }
            driver.navigate().refresh();

        }

    }

    public static void playerSnipe(boolean active, WebDriver driver, WebDriverWait wait, WebDriverWait instantWait,
                                   String playerName, String playerBuyNow, int numberOfSearches, String coinBalance, int counter){
        while(active){
            // Initial Setup

            // Click transfer button
            performAction(driver, "//*[@class='ut-tab-bar-item icon-transfer']", wait, false, Actions.Click,"", 0);
            // Click search transfer market button
            performAction(driver, "//*[@class='tileContent']", wait, false, Actions.Click, "", 0);
            // Input players name
            performAction(driver, "//*[@class='ut-text-input-control']", wait, false, Actions.SendKeys, playerName, 0);
            // Click player name from results list
            performAction(driver, "//*[@class='btn-text']", wait, false, Actions.Click, "", 0);
            // Enter players buy now price
            performAction(driver, "//*[@class='ut-number-input-control']", wait, true, Actions.SendKeys, playerBuyNow, 3);

            // Loop
            for (int i = 0; i < numberOfSearches; i++){
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='view-navbar-currency-coins']")));
                coinBalance = driver.findElement(By.xpath("//*[@class='view-navbar-currency-coins']")).getText();
                coinBalance = coinBalance.replace(",", "");
                if (Integer.parseInt(coinBalance) < Integer.parseInt(playerBuyNow)){
                    active = false;
                    break;
                }
                // Click search button
                performAction(driver, "//*[@class='btn-standard call-to-action']", wait, false, Actions.Click, "", 0);
                counter += 1;
                System.out.println("Times Searched: " + Integer.toString(counter));
                // Click buy now
                performAction(driver, "//*[@class='btn-standard buyButton currency-coins']", instantWait, false, Actions.Click, "", 0);
                // Confirm buy now
                performAction(driver, "//*[@class='ea-dialog-view--body']//*[@class='ut-button-group']//*[not(@class)]", instantWait, false, Actions.Click, "", 0);
                // Go back
                performAction(driver, "//*[@class='ut-navigation-button-control']", wait, false, Actions.Click, "", 0);
                // increment bid price
                performAction(driver, "//*[@class='btn-standard increment-value']", wait, true, Actions.Click, "", 0);
            }
            driver.navigate().refresh();
        }
    }

    public static void customPlayerSnipe(boolean active, WebDriver driver, WebDriverWait wait, WebDriverWait instantWait,
                                   String playerName, String playerBuyNow, int numberOfSearches, String coinBalance, int counter){
        while(active){
            // Initial Setup

            // Click transfer button
            performAction(driver, "//*[@class='ut-tab-bar-item icon-transfer']", wait, false, Actions.Click,"", 0);
            // Click search transfer market button
            performAction(driver, "//*[@class='tileContent']", wait, false, Actions.Click, "", 0);
            // Input players name
            performAction(driver, "//*[@class='ut-text-input-control']", wait, false, Actions.SendKeys, playerName, 0);
            // Click player name from results list
            performAction(driver, "//*[@class='btn-text']", wait, false, Actions.Click, "", 0);
            // Select card rarity
            performAction(driver, "/html/body/main/section/section/div[2]/div/div[2]/div/div[1]/div[1]/div[3]/div/div", wait, false,Actions.Click, "", 0);
            // Select rarity
            performAction(driver,"/html/body/main/section/section/div[2]/div/div[2]/div/div[1]/div[1]/div[3]/div/ul/li[9]", wait, false, Actions.Click,
                    "", 0);

            // Enter players buy now price
            performAction(driver, "//*[@class='ut-number-input-control']", wait, true, Actions.SendKeys, playerBuyNow, 3);

            // Loop
            for (int i = 0; i < numberOfSearches; i++){
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='view-navbar-currency-coins']")));
                coinBalance = driver.findElement(By.xpath("//*[@class='view-navbar-currency-coins']")).getText();
                coinBalance = coinBalance.replace(",", "");
                if (Integer.parseInt(coinBalance) < Integer.parseInt(playerBuyNow)){
                    active = false;
                    break;
                }
                // Click search button
                performAction(driver, "//*[@class='btn-standard call-to-action']", wait, false, Actions.Click, "", 0);
                counter += 1;
                System.out.println("Times Searched: " + Integer.toString(counter));
                // Click buy now
                performAction(driver, "//*[@class='btn-standard buyButton currency-coins']", instantWait, false, Actions.Click, "", 0);
                // Confirm buy now
                performAction(driver, "//*[@class='ea-dialog-view--body']//*[@class='ut-button-group']//*[not(@class)]", instantWait, false, Actions.Click, "", 0);
                // Go back
                performAction(driver, "//*[@class='ut-navigation-button-control']", wait, false, Actions.Click, "", 0);
                // increment bid price
                performAction(driver, "//*[@class='btn-standard increment-value']", wait, true, Actions.Click, "", 0);
            }
            driver.navigate().refresh();
        }
    }

    public static void cardTypeSnipe(boolean active, WebDriver driver, WebDriverWait wait, WebDriverWait instantWait,
                                   String playerBuyNow, int numberOfSearches, String coinBalance, int counter){
        while(active){
            // Initial Setup

            // Click transfer button
            performAction(driver, "//*[@class='ut-tab-bar-item icon-transfer']", wait, false, Actions.Click,"", 0);
            // Click search transfer market button
            performAction(driver, "//*[@class='tileContent']", wait, false, Actions.Click, "", 0);
            // Click card rarity
            performAction(driver, "/html/body/main/section/section/div[2]/div/div[2]/div/div[1]/div[1]/div[3]/div/div", wait, false,Actions.Click, "", 0);
            // Select card type
            performAction(driver, "/html/body/main/section/section/div[2]/div/div[2]/div/div[1]/div[1]/div[3]/div/ul/li[10]", wait, false,Actions.Click, "", 0);
            // Enter buy now price
            performAction(driver, "//*[@class='ut-number-input-control']", wait, true, Actions.SendKeys, playerBuyNow, 3);

            // Loop
            for (int i = 0; i < numberOfSearches; i++){
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='view-navbar-currency-coins']")));
                coinBalance = driver.findElement(By.xpath("//*[@class='view-navbar-currency-coins']")).getText();
                coinBalance = coinBalance.replace(",", "");
                if (Integer.parseInt(coinBalance) < Integer.parseInt(playerBuyNow)){
                    active = false;
                    break;
                }
                // Click search button
                performAction(driver, "//*[@class='btn-standard call-to-action']", wait, false, Actions.Click, "", 0);
                counter += 1;
                System.out.println("Times Searched: " + Integer.toString(counter));
                // Click buy now
                performAction(driver, "//*[@class='btn-standard buyButton currency-coins']", instantWait, false, Actions.Click, "", 0);
                // Confirm buy now
                performAction(driver, "//*[@class='ea-dialog-view--body']//*[@class='ut-button-group']//*[not(@class)]", instantWait, false, Actions.Click, "", 0);
                // Go back
                performAction(driver, "//*[@class='ut-navigation-button-control']", wait, false, Actions.Click, "", 0);
                // increment bid price
                performAction(driver, "//*[@class='btn-standard increment-value']", wait, true, Actions.Click, "", 0);
            }
            driver.navigate().refresh();
        }
    }

    public static void main(String [] args){
        boolean active = true;
        int counter = 0;
        String playerName = "Kevin De Bruyne";
        String playerBuyNow = "136000";
        int calculatedBuyPrice = Integer.parseInt(playerBuyNow) - (((Integer.parseInt(playerBuyNow) / 10) / 2) + 1000);
        String chemBuyNow = "2000";
        int numberOfSearches = 100;
        String coinBalance = "";

        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        ChromeOptions options = new ChromeOptions();
        String profile = "/Users/josh/Library/Application Support/Google/Chrome/Default";
        options.addArguments("user-data-dir=" + profile);
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        WebDriverWait instantWait = new WebDriverWait(driver, Duration.ofMillis(1000));
        driver.get("https://www.ea.com/fifa/ultimate-team/web-app/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='view-navbar-currency-coins']")));
        coinBalance = driver.findElement(By.xpath("//*[@class='view-navbar-currency-coins']")).getText();
        coinBalance = coinBalance.replace(",", "");

//        shadowSnipe(active, driver, wait, instantWait, chemBuyNow, numberOfSearches, coinBalance);
        playerSnipe(active, driver, wait, instantWait,playerName, Integer.toString(calculatedBuyPrice), numberOfSearches, coinBalance, counter);
//          cardTypeSnipe(active, driver, wait, instantWait, playerBuyNow, numberOfSearches, coinBalance, counter);
    }
}