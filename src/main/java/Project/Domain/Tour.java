package Project.Domain;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

public class Tour {
    private String region;
    private double categoryRate;
    private LocalDate startTime;
    private LocalDate endTime;
    private int km;
    private boolean billed;

    public Tour() {
    }

    public Tour(String region, double categoryRate, LocalDate startTime, LocalDate endTime, int km, boolean billed) {
        this.region = region;
        this.categoryRate = categoryRate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.km = km;
        this.billed = billed;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getCategoryRate() {
        return categoryRate;
    }

    public void setCategoryRate(double categoryRate) {
        this.categoryRate = categoryRate;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public boolean isBilled() {
        return billed;
    }

    public void setBilled(boolean billed) {
        this.billed = billed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tour)) return false;

        Tour tour = (Tour) o;

        if (Double.compare(tour.getCategoryRate(), getCategoryRate()) != 0) return false;
        if (getKm() != tour.getKm()) return false;
        if (!getRegion().equals(tour.getRegion())) return false;
        if (!getStartTime().equals(tour.getStartTime())) return false;
        return getEndTime().equals(tour.getEndTime());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getRegion().hashCode();
        temp = Double.doubleToLongBits(getCategoryRate());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getStartTime().hashCode();
        result = 31 * result + getEndTime().hashCode();
        result = 31 * result + getKm();
        return result;
    }
}
