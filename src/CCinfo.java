public class CCinfo {
    String name, ccNumber, expDate;
    int pin, ccvNum;

    CCinfo(String name, String ccNumber, String expDate, int pin, int ccvNum) {
        this.name = name;
        this.ccNumber = ccNumber;
        this.expDate = expDate;
        this.pin = pin;
        this.ccvNum = ccvNum;
    }

    public String getName() {
        return name;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public String getExpDate() {
        return expDate;
    }

    public int getPin() {
        return pin;
    }

    public int getCcvNum() {
        return ccvNum;
    }

}
