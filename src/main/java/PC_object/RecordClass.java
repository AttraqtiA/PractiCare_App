package PC_object;

import java.time.LocalDate;
import java.util.*;
import java.time.format.DateTimeFormatter;

public class RecordClass {
    protected String category, details;
    protected String transaction_date;
    protected int no_ID;

    // nyimpen date
    public RecordClass(LocalDate transaction_date) {
        this.transaction_date = transaction_date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getNo_ID() {
        return no_ID;
    }

    public void setNo_ID(int no_ID) {
        this.no_ID = no_ID;
    }
}
