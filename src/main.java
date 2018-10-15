import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
/* Takes in a .txt file with the list of commands that need to be run.
Examples of commands:
1. Add Anish 4579302848382332 $2000
2. Charge Anish $560
3. Credit Anish $399
 */
public class main {
    static HashMap<String, Card> money = new HashMap();
    static TreeSet<String> names = new TreeSet<>();
    public static void main (String args) {
        try {
            List<String> lst = Files.readAllLines(Paths.get(args));
            for (String line : lst) {
                Processing.process(line);
            }
            for (String name: names) {
                Card card = money.get(name);
                if (card.getvalidity()) {
                    System.out.print(name + ": $" + card.getBalance());
                } else {
                    System.out.print(name + ": error");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
