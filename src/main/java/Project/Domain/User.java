package Project.Domain;

import Project.Domain.Enums.EnergyLabel;
import Project.Domain.Enums.Hardware;
import Project.Domain.Enums.IdentificationMethod;
import Project.Domain.Enums.UsesWebsite;

import java.util.List;

/**
 * Created by arjan on 28-3-2017.
 */
public class User {
    private long carTracker;
    private Hardware Hardware;
    private IdentificationMethod IdentificationMethod;
    private UsesWebsite UsesWebsite;
    private EnergyLabel EnergyLabel;
    private List<Tour> tours;
    private List<Invoice> invoices;

    public User(long carTracker) {
    }

    public User(long carTracker, Hardware hardware, IdentificationMethod identificationMethod, UsesWebsite usesWebsite, EnergyLabel energyLabel, List<Tour> tours, List<Invoice> invoices) {
        this.carTracker = carTracker;
        Hardware = hardware;
        IdentificationMethod = identificationMethod;
        UsesWebsite = usesWebsite;
        EnergyLabel = energyLabel;
        this.tours = tours;
        this.invoices = invoices;
    }

    public long getCarTracker() {
        return carTracker;
    }

    public void setCarTracker(long carTracker) {
        this.carTracker = carTracker;
    }

    public Hardware getHardware() {
        return Hardware;
    }

    public void setHardware(Hardware Hardware) {
        this.Hardware = Hardware;
    }

    public IdentificationMethod getIdentificationMethod() {
        return IdentificationMethod;
    }

    public void setIdentificationMethod(IdentificationMethod IdentificationMethod) {
        this.IdentificationMethod = IdentificationMethod;
    }

    public UsesWebsite getUsesWebsite() {
        return UsesWebsite;
    }

    public void setUsesWebsite(UsesWebsite UsesWebsite) {
        this.UsesWebsite = UsesWebsite;
    }

    public EnergyLabel getEnergyLabel() {
        return EnergyLabel;
    }

    public void setEnergyLabel(EnergyLabel EnergyLabel) {
        this.EnergyLabel = EnergyLabel;
    }

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = tours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getCarTracker() != user.getCarTracker()) return false;
        if (getHardware() != user.getHardware()) return false;
        if (getIdentificationMethod() != user.getIdentificationMethod()) return false;
        if (getUsesWebsite() != user.getUsesWebsite()) return false;
        return getEnergyLabel() == user.getEnergyLabel();
    }

    @Override
    public int hashCode() {
        int result = (int) (getCarTracker() ^ (getCarTracker() >>> 32));
        result = 31 * result + getHardware().hashCode();
        result = 31 * result + getIdentificationMethod().hashCode();
        result = 31 * result + getUsesWebsite().hashCode();
        result = 31 * result + getEnergyLabel().hashCode();
        return result;
    }
}
