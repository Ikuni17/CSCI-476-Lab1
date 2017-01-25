// CC Information starts with %B
// TODO handle CL arguments

import java.io.*;
import java.util.*;

public class Main {

    static List<String> lines = new ArrayList<>();
    static String dumpPath = "memorydump.dmp";
    static List<CCinfo> foundCCinfo = new ArrayList<>();

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
    }
}

