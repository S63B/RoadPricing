package Project.Domain;

import Project.Domain.Enums.Status;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arjan on 28-3-2017.
 */
public class Invoice {
    private LocalDate billDate;
    private User user;
    private double totalPrice;
    private Status Status;
    final int daysToBill = 30;


    public Invoice() {
    }

    public Invoice(LocalDate billDate, User user, double totalPrice, Status Status) {
        this.billDate = billDate;
        this.user = user;
        this.totalPrice = totalPrice;
        this.Status = Status;
    }

    private List<Tour> unbilledTours(){
        List<Tour> unbilledTours = new ArrayList<>();
        for (Tour t : user.getTours()) {
            final long days = ChronoUnit.DAYS.between(t.getStartTime(), LocalDate.now());
            System.out.println("Days between: " + days);

            if(days <= daysToBill ){
                unbilledTours.add(t);
            }
        }
        return unbilledTours;
    }

    private double calculateTotalPrice(){
        return unbilledTours().stream().mapToDouble(t -> t.getKm() * t.getCategoryRate()).sum();
    }

    public LocalDate getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Status getStatus() {
        return Status;
    }

    public void setStatus(Status Status) {
        this.Status = Status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;

        Invoice invoice = (Invoice) o;

        if (Double.compare(invoice.getTotalPrice(), getTotalPrice()) != 0) return false;
        if (!getBillDate().equals(invoice.getBillDate())) return false;
        if (!getUser().equals(invoice.getUser())) return false;
        return getStatus() == invoice.getStatus();
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getBillDate().hashCode();
        result = 31 * result + getUser().hashCode();
        temp = Double.doubleToLongBits(getTotalPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getStatus().hashCode();
        return result;
    }
}
