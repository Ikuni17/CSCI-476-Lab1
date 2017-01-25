public class Track1Info {
    String name, ccNumber, expDate;
    int pin, cvvNum;

    Track1Info(String name, String ccNumber, String expDate, int pin, int cvvNum) {
        this.name = name;
        this.ccNumber = ccNumber;
        this.expDate = expDate;
        this.pin = pin;
        this.cvvNum = cvvNum;
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

    public int getCvvNum() {
        return cvvNum;
    }

    public void printTrack1Info() {
        System.out.printf("Cardholder's Name: %s%nCard Number: %s%nExpiration Date: %s%nEncrypted PIN: %d%nCVV Number: %d%n", name, ccNumber, expDate, pin, cvvNum);
    }

}
