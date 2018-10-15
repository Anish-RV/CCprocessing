public class Card {

    private String Name;
    private String Cardno;
    private Double Limit;
    private Double Balance;
    private boolean Valid;
    public Card(String name, String cardno, Double limit, Double balance, boolean valid) {
        Name = name;
        Cardno = cardno;
        Limit = limit;
        Balance = balance;
        Valid = valid;
    }
    public String getName() {
        return this.Name;
    }
    public String getCardno() {
        return this.Cardno;
    }
    public Double getLimit() {
        return this.Limit;
    }
    public Double getBalance() {
        return this.Balance;
    }
    public boolean getvalidity() {
        return this.Valid;
    }
    public void changebalance(Double newbal) {
        if (this.Valid) {
            this.Balance += newbal;
            if (this.Balance > this.Limit) {
                this.Balance = this.Limit;
            }
        }
    }
    public void chargecredit(Double credit) {
        if (this.Valid) {
            this.Balance -= credit;
        }
    }
}
