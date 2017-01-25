// CC Information starts with %B
// TODO handle CL arguments

import java.io.*;
import java.util.*;

public class Main {

    static List<String> lines = new ArrayList<>();
    static String dumpPath = "memorydump.dmp";
    static List<Track1Info> foundTrack1Info = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        try {
            File fileDir = new File(dumpPath);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF8"));

            String str;

            while ((str = in.readLine()) != null) {
                System.out.println(str);
            }

            in.close();
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        foundTrack1Info.add(new Track1Info("Brad", "123445678","09/2019", 1999, 378));
        foundTrack1Info.get(0).printTrack1Info();
    }
}

