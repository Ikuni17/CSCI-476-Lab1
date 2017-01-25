// CC Information starts with %B

import java.io.*;
import java.util.*;

public class Main {

    static List<String> lines = new ArrayList<>();
    static String dumpPath = "memorydump.dmp";

    public static void main(String[] args) throws IOException {
        /*for(String line : Files.readAllLines(Paths.get(dumpPath))){
	        lines.add(line);
        }

	    Scanner fileScanner = new Scanner(new File(dumpPath));
	    while(fileScanner.hasNext()){
	        lines.add(fileScanner.next());
        }

        Iterator iter = lines.listIterator();

        while(iter.hasNext()){
            System.out.println(iter.next());
        }*/
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

