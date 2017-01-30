public class Track2Info {
    String ccNumber;
    String expDate;
    String serviceCode;
    String pin;
    String cvvNumber;

    Track2Info(String ccNumber, String expDate, String serviceCode, String pin, String cvvNumber) {
        this.ccNumber = ccNumber;
        this.expDate = expDate;
        this.serviceCode = serviceCode;
        this.pin = pin;
        this.cvvNumber = cvvNumber;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public String getExpDate() {
        return expDate;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public String getPin() {
        return pin;
    }

    public String getCvvNumber() {
        return cvvNumber;
    }

    public void printTrack2Info() {
        System.out.printf("Card Number: %s%nExpiration Date: %s%nService Code: %s%nEncrypted Pin: %s%nCVV Number: %s%n%n", ccNumber, expDate, serviceCode, pin, cvvNumber);
    }

}
