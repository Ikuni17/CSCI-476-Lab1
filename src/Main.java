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

    // Relative path to the memory dump file
    static String dumpPath = "./memorydump.dmp";
    static List<CCinfo> foundCCInfo = new ArrayList<>();
    // Regular Expression to match track 1 and track 2 data that is adjacent
    static Pattern fullPattern = Pattern.compile("%[A-Z]\\d{13,19}\\^{1}\\w{1,26}\\/\\w{1,26}\\^{1}\\d{7,}\\?;\\d{13,19}={1}\\d{14,}\\?");
    static Matcher fullMatcher;

    public static void main(String[] args) throws IOException {
        try {
            // Open the memory dump file with the correct encoding
            File dumpFile = new File(dumpPath);
            // Use a buffered reader with the correct encoding to read the memory dump
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(dumpFile), "UTF8"));
            String str;

            // Read each line until end of file
            while ((str = in.readLine()) != null) {
                // Pass a line into the matcher to parse
                fullMatcher = fullPattern.matcher(str);
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

        // Print out any results found in the memory dump
        System.out.printf("There are %d pieces of credit card information in the memory data!%n%n", foundCCInfo.size());
        Iterator iterator = foundCCInfo.iterator();
        int count = 1;
        while (iterator.hasNext()) {
            System.out.printf("<Information of the %s credit card>:%n", getOrdinal(count));
            CCinfo temp = (CCinfo) iterator.next();
            temp.printCCinfo();
            count++;
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