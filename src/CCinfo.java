public class CCinfo {
    String name, ccNumber, expDate, serviceCode, pin, cvvNumber;

    CCinfo(String name, String ccNumber, String expDate, String serviceCode, String pin, String cvvNumber) {
        this.name = name;
        this.ccNumber = ccNumber;
        this.expDate = expDate;
        this.serviceCode = serviceCode;
        this.pin = pin;
        this.cvvNumber = cvvNumber;
    }

    public void printCCinfo() {
        System.out.printf("Cardholder's Name: %s%nCard Number: %s%nExpiration Date: %s%nService Code: %s%nEncrypted Pin: %s%nCVV Number: %s%n%n", name, ccNumber, expDate, serviceCode, pin, cvvNumber);
    }
}
