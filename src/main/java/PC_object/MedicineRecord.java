package PC_object;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MedicineRecord extends RecordClass{
    private LinkedList<Medicine> list_purchased_med = new LinkedList<>();
    private String payment_type;
    public MedicineRecord (LocalDate transaction_date, LinkedList<Medicine> purchased_med, String payment_type){
        // medicine purchased
        super(transaction_date);
        this.category = "Medicine Purchase";
        this.payment_type = payment_type;
        this.list_purchased_med = purchased_med;
        this.details = "Payment : " + this.payment_type;
        for (Medicine medicine : this.list_purchased_med) {
            this.details = this.details + "\n" + medicine.getName() + " x" + medicine.getAmount() + " - " + medicine.getTotal_display();
        }

        int checkout = 0;
        for (int a = 0; a < list_purchased_med.size(); a++) {
            checkout = checkout + list_purchased_med.get(a).getAmount() * list_purchased_med.get(a).getPrice();
        }

        this.details = this.details + "\nTotal : $" +  checkout;
    }


}
