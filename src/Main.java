/*
Bradley White and Isaac Sotelo
CSCI 476: Lab 1
January 30, 2017
*/

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    // Assume the memory dump file is in the same directory as this main file
    static String dumpPath = "./memorydump.dmp";
    static List<CCinfo> foundCCInfo = new ArrayList<>();
    static List<Track1Info> foundTrack1Info = new ArrayList<>();
    static List<Track2Info> foundTrack2Info = new ArrayList<>();
    static Pattern fullPattern = Pattern.compile("%[A-Z]\\d{13,19}\\^{1}\\w{1,26}\\/\\w{1,26}\\^{1}\\d{7,}\\?;\\d{13,19}={1}\\d{14,}\\?");
    static Matcher fullMatcher;
    // Regular expression to match track 1 data, example: %B1234567890123^Brad/White^1705123?
    static Pattern track1Pattern = Pattern.compile("%[A-Z]\\d{13,19}\\^{1}\\w{1,26}\\/\\w{1,26}\\^{1}\\d{7,}\\?");
    static Matcher track1Matcher;
    // Regular expression to match track 2 data, example: ;1234567890123=17051231930123?
    static Pattern track2Pattern = Pattern.compile(";\\d{13,19}={1}\\d{14,}\\?");
    static Matcher track2Matcher;

    public static void main(String[] args) throws IOException {
        try {
            // Open the memory dump file with the correct encoding
            File fileDir = new File(dumpPath);
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
            String str;

            // Read each line until end of file
            while ((str = in.readLine()) != null) {
                // Pass a line into the matcher to parse
                fullMatcher = fullPattern.matcher(str);
                //track1Matcher = track1Pattern.matcher(str);
                //track2Matcher = track2Pattern.matcher(str);
                // Check if the matcher found a string that satisfies the regex
                if(fullMatcher.find()){
                    // Split the matched string into the two tracks of information
                    String[] split = fullMatcher.group().split("\\?{1}");
                    // Further split each track into constituent parts
                    String[] track1 = split[0].split("\\^{1}");
                    String[] track2 = split[1].split("={1}");
                    // Check if both tracks have the same account number
                    if(track1[0].substring(2).equals(track2[0].substring(1))){
                        // Create a new CCinfo object with the information and add it to the ArrayList
                        foundCCInfo.add(new CCinfo(
                                track1[1].replace("/", " "),
                                track1[0].substring(2),
                                track1[2].substring(2, 4) + "/20" + track1[2].substring(0, 2),
                                track1[2].substring(4, 7),
                                track2[1].substring(7, 11),
                                track2[1].substring(11, 14)));
                    }
                }
                // Check if the matchers found a string that satisfies the regex
                /*if (track1Matcher.find()) {
                    // Parse the string into its constituent parts and create a new object to save it
                    String matchedString = track1Matcher.group();
                    String[] split = matchedString.split("\\^{1}");
                    foundTrack1Info.add(new Track1Info(split[1].replace("/", " "), split[0].substring(2), split[2].substring(0, 4), split[2].substring(4, 7)));
                }
                if (track2Matcher.find()) {
                    // Parse the string into its constituent parts and create a new object to save it
                    String matchedString = track2Matcher.group();
                    String[] split = matchedString.split("\\={1}");
                    foundTrack2Info.add(new Track2Info(split[0].substring(1), split[1].substring(0, 4), split[1].substring(4, 7), split[1].substring(7, 11), split[1].substring(11, 14)));
                }*/
                // Used to print each line out for testing regex in IntelliJ console
                //System.out.println(str);
            }

            // Close the reader
            in.close();
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Keep track of how many matches are found between Track 1 and Track 2 objects
        /*int count = 0;
        Iterator iter1 = foundTrack1Info.iterator();
        boolean foundMatch = false;

        // Iterate through all Track 1 objects and compare each to all Track 2 objects to find matches
        while (iter1.hasNext()) {
            // Get the next Track 1 object
            Track1Info temp1 = (Track1Info) iter1.next();
            // Create a new Track 2 iterator to start from the beginning of the ArrayList
            Iterator iter2 = foundTrack2Info.iterator();
            while (iter2.hasNext()) {
                Track2Info temp2 = (Track2Info) iter2.next();
                // Compare the credit card number of each track's info
                if (temp1.getCcNumber().equals(temp2.getCcNumber())) {
                    // TODO: format the exp date correctly before passing into constructor
                    foundCCInfo.add(new CCinfo(temp1.getName(), temp1.getCcNumber(), temp1.getExpDate(), temp1.getServiceCode(), temp2.getPin(), temp2.getCvvNumber()));
                    count++;
                    // TODO: Need to remove the matched tracks from info to avoid duplicates, this code produces runtime error currently
                    foundMatch = true;
                    foundTrack1Info.remove(temp1);
                    foundTrack2Info.remove(temp2);
                }
            }
        }*/
        // Print out any results found in the memory dump
        System.out.printf("There are %d pieces of credit card information in the memory data!%n%n", foundCCInfo.size());
        Iterator iterator = foundCCInfo.iterator();
        int i = 1;
        while (iterator.hasNext()) {
            System.out.printf("<Information of the %s credit card>:%n", getOrdinal(i));
            CCinfo temp = (CCinfo) iterator.next();
            temp.printCCinfo();
            i++;
        }

    }

    // Used to print the correct suffix for each number
    public static String getOrdinal(int i) {
        String[] sufixes = new String[]{"th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th"};
        switch (i % 100) {
            case 11:
            case 12:
            case 13:
                return i + "th";
            default:
                return i + sufixes[i % 10];
        }
    }
}

// Class to save Credit Card info
class CCinfo {
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