import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class UnitTest {
    @Test
    public void add() {
        String line = "Add Ronan 4637284738485647 $1000";
        Processing.process(line);
        Double balance = main.money.get("Ronan").getBalance();
        Double limit = main.money.get("Ronan").getLimit();
        assertTrue(balance == 0.0);
        assertTrue(limit == 1000.0);
        assertTrue(!main.money.get("Ronan").getvalidity());
    }
    @Test
    public void chargeandcredit() {
        String addline = "Add Ronan 4637284338485637 $1000";
        Processing.process(addline);
        String chargeline = "Charge Ronan $500";
        Processing.process(chargeline);
        Double balance = main.money.get("Ronan").getBalance();
        Double limit = main.money.get("Ronan").getLimit();
        assertTrue(balance == 500.0);
        assertTrue(limit == 1000.0);
        String creditline = "Credit Ronan $700";
        Processing.process(creditline);
        balance = main.money.get("Ronan").getBalance();
        limit = main.money.get("Ronan").getLimit();
        assertTrue(balance == -200.0);
        assertTrue(limit == 1000.0);
    }
}