// CC Information starts with %B
// TODO handle CL arguments

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    //static List<String> lines = new ArrayList<>();
    static String dumpPath = "memorydump.dmp";
    static List<Track1Info> foundTrack1Info = new ArrayList<>();
    static List<Track1Info> foundTrack2Info = new ArrayList<>();
    static Pattern track1Pattern = Pattern.compile("%B\\d{13,19}\\^{1}\\w{2,26}\\/\\w{2,26}\\^{1}\\d{7,}\\?");
    static Matcher track1Matcher;
    static Pattern track2Pattern = Pattern.compile(";\\d{13,19}={1}\\d{14,}\\?");
    static Matcher track2Matcher;

    public static void main(String[] args) throws IOException {
        try {
            File fileDir = new File(dumpPath);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF8"));

            String str;

            while ((str = in.readLine()) != null) {
                track1Matcher = track1Pattern.matcher(str);
                track2Matcher = track2Pattern.matcher(str);
                if(track1Matcher.find()) {
                    System.out.println(track1Matcher.group());
                    String matchedString = track1Matcher.group();
                    String[] split = matchedString.split("\\^{1}");
                    System.out.println(split[0].substring(2));
                    foundTrack1Info.add(new Track1Info(split[1].replace("/", " "), split[0].substring(2), split[2].substring(0,4), split[2].substring(4,7)));
                }
                if(track2Matcher.find()) {
                    System.out.println(track2Matcher.group());
                }
                //System.out.println(str);
            }

            in.close();
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //foundTrack1Info.add(new Track1Info("Brad", "123445678", null, 1999, 378));
        foundTrack1Info.get(0).printTrack1Info();
    }
}

