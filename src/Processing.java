public class Processing {
    /* For processing 3 main commands to the program:
    1. Add: This command adds a new credit card to the system. Takes in 3 inputs (Name,
        Card number and credit limit)
    2. Charge: This command charges a credit card. Takes in Name and charge.
    3. Credit: This command credits a card. Takes in Name and credit.
     */
    public static void process(String line) {
        if (line.charAt(0) == 'A') {
            processadd(line);
        } else if (line.charAt(0) ==  'C' && line.charAt(1) ==  'h') {
            processcharge(line);
        } else if (line.charAt(0) ==  'C' && line.charAt(1) ==  'r') {
            processcredit(line);
        }

    }

    private static void processadd(String line) {
        int i;
        for (i = 5; i < line.length(); i++) {
            if (line.charAt(i) == ' ') {
                break;
            }
        }
        String name = line.subSequence(4,i).toString();
        i++;
        int cardstart = i;
        while(i < line.length()) {
            if (line.charAt(i) == ' ') {
                break;
            }
            i++;
        }
        String cardno = line.substring(cardstart, i);
        i += 2;
        int limitstart = i;
        while(i < line.length()) {
            if (line.charAt(i) == ' ') {
                break;
            }
            i++;
        }
        Double limit = Double.parseDouble(line.substring(limitstart, i));
        boolean valid = luhnAlg(cardno.toString());
        Card newcard = new Card(name,cardno,limit,0.0, valid);
        main.money.put(name,newcard);
        main.names.add(name);
    }

    private static void processcharge(String line) {
        int i;
        for (i = 7; i < line.length(); i++) {
            if (line.charAt(i) == ' ') {
                break;
            }
        }
        String name = line.subSequence(7,i).toString();
        i += 2;
        int balancestart = i;
        while(i < line.length()) {
            if (line.charAt(i) == ' ') {
                break;
            }
            i++;
        }
        Double newbal = Double.parseDouble(line.substring(balancestart, i));
        Card user = main.money.get(name);
        user.changebalance(newbal);
        main.money.replace(name,user);
    }

    private static void processcredit(String line) {
        int i;
        for (i = 7; i < line.length(); i++) {
            if (line.charAt(i) == ' ') {
                break;
            }
        }
        String name = line.subSequence(7,i).toString();
        i += 2;
        int creditstart = i;
        while(i < line.length()) {
            if (line.charAt(i) == ' ') {
                break;
            }
            i++;
        }
        Double credit = Double.parseDouble(line.substring(creditstart, i));
        Card user = main.money.get(name);
        user.chargecredit(credit);
        main.money.replace(name,user);
    }
    /* Credit to Chris Wareham for the following implementation of the
    Luhn Algorithm.
    (https://github.com/eix128/gnuc-credit-card-checker/blob/master/CCCheckerPro/src/com/gnuc/java/ccc/Luhn.java)
     */

    public static boolean luhnAlg(String ccNumber)
    {
        int sum = 0;
        boolean alternate = false;
        for (int i = ccNumber.length() - 1; i >= 0; i--)
        {
            int n = Integer.parseInt(ccNumber.substring(i, i + 1));
            if (alternate)
            {
                n *= 2;
                if (n > 9)
                {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
}
