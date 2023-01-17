package data;

import lombok.Value;

public class DataHelper {

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private int code;
    }

    public static VerificationCode getVerificationCode() {
        return new VerificationCode(12345);
    }

    @Value
    public static class NumberCard {
        private String card;
    }


    public static NumberCard getNumberCard1() {
        return new NumberCard("5559 0000 0000 0001");
    }

    public static NumberCard getNumberCard2() {
        return new NumberCard("5559 0000 0000 0002");
    }

    public static NumberCard getNumberCard3() {
        return new NumberCard("5559 0000 0000 0000");
    }

    @Value
    public static class Amount {
        private int amount;
    }

}

