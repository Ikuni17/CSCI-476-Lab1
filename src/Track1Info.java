public class Track1Info {
    String name, ccNumber, expDate, serviceCode;

    Track1Info(String name, String ccNumber, String expDate, String serviceCode) {
        this.name = name;
        this.ccNumber = ccNumber;
        this.expDate = expDate;
        this.serviceCode = serviceCode;
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

    public String getServiceCode() {
        return serviceCode;
    }

    public void printTrack1Info() {
        System.out.printf("Cardholder's Name: %s%nCard Number: %s%nExpiration Date: %s%nService Code: %s%n", name, ccNumber, expDate, serviceCode);
    }

}
