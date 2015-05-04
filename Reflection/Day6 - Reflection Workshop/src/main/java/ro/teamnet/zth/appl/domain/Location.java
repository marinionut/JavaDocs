package ro.teamnet.zth.appl.domain;
import java.lang.Long;
import com.sun.org.apache.xerces.internal.xs.StringList;
import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

/**
 * Created by Ionutz on 29.04.2015.
 */
@Table(name = "location")
public class Location {
    @Id(name = "LOCATION_ID")
    private Long id;
    @Column(name = "STREET_ADRESS")
    private String streetAddress;
    @Column(name = "POSTAL_CODE")
    private String postalCode;
    @Column(name = "CITY")
    private String city;
    @Id(name = "STATE_PROVINCE")
    private String stateProvince;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", streetAddress='" + streetAddress + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", stateProvince='" + stateProvince + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (id != null ? !id.equals(location.id) : location.id != null) return false;
        if (streetAddress != null ? !streetAddress.equals(location.streetAddress) : location.streetAddress != null)
            return false;
        if (postalCode != null ? !postalCode.equals(location.postalCode) : location.postalCode != null) return false;
        if (city != null ? !city.equals(location.city) : location.city != null) return false;
        return !(stateProvince != null ? !stateProvince.equals(location.stateProvince) : location.stateProvince != null);

    }

}
