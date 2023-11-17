package PC_object;

public class Medicine {
    private String name, category, dose, price_display, total_display;
    private int price, amount;

    public Medicine(String name, String category, String dose, int price) {
        this.name = name;
        this.category = category;
        this.dose = dose;
        this.price = price;
        this.price_display = "$" + price;
    }

    public void CalculateTotalIndividu() {
        int total = price * amount;
        total_display = "$" + total;
    }

    public String getTotal_display() {
        return total_display;
    }
    public void setTotal_display() {
        this.total_display = total_display;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }
    public String getPrice_display() {
        return price_display;
    }

    public void setPrice_display(String price_display) {
        this.price_display = price_display;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

