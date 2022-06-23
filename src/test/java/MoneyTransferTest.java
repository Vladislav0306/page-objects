import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @BeforeEach
    public void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificaionPage = loginPage.validLogin(authInfo);
        var verifyInfo = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificaionPage.validVerify(verifyInfo);
        var originFirstCardValue = dashboardPage.getFirstCardBalance();
        var originSecondCardValue = dashboardPage.getSecondCardBalance();

        var moneyTransferPage = dashboardPage.pushMakeDepositFirstAccount();

        var transferInfo = DataHelper.getRandomValueTransferInfo(Math.abs(originSecondCardValue), DataHelper.SECOND_ACCOUNT);

        var dashBoardPage2 = moneyTransferPage.setAmountAndCard(transferInfo);

        var actualFirstCardBalance = dashBoardPage2.getFirstCardBalance();
        var actualSecondCardBalance = dashBoardPage2.getSecondCardBalance();

        Assertions.assertEquals(originFirstCardValue + transferInfo.getAmount(), actualFirstCardBalance);
        Assertions.assertEquals(originSecondCardValue - transferInfo.getAmount(), actualSecondCardBalance);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV2() {
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificaionPage = loginPage.validLogin(authInfo);
        var verifyInfo = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificaionPage.validVerify(verifyInfo);
        var originFirstCardValue = dashboardPage.getFirstCardBalance();
        var originSecondCardValue = dashboardPage.getSecondCardBalance();

        var moneyTransferPage = dashboardPage.pushMakeDepositFirstAccount();

        var transferInfo = DataHelper.getRandomValueTransferInfo(Math.abs(originSecondCardValue), DataHelper.SECOND_ACCOUNT);

        var dashBoardPage2 = moneyTransferPage.setAmountAndCard(transferInfo);

        var actualFirstCardBalance = dashBoardPage2.getFirstCardBalance();
        var actualSecondCardBalance = dashBoardPage2.getSecondCardBalance();

        Assertions.assertEquals(originFirstCardValue + transferInfo.getAmount(), actualFirstCardBalance);
        Assertions.assertEquals(originSecondCardValue - transferInfo.getAmount(), actualSecondCardBalance);
    }
}
