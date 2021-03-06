package ro.teamnet.zth.appl;

import java.util.List;

import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.dao.LocationDao;
import ro.teamnet.zth.appl.domain.Location;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Ionutz on 5/4/2015.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocationDaoTest {

    static Location location = new Location();
    LocationDao locationDao = new LocationDao();

    @Test
    public void aTestInsertLocation() {
        location.setCity("Bucuresti test");
        location.setPostalCode("1234");
        location.setStateProvince("Buc test");
        location.setStreetAddress("test adresa");
        location = locationDao.insertLocation(location);

        assertEquals(locationDao.getLocationById(location.getId()), location);
    }

    @Test
    public void bTestUpdateLocation() {
        EntityManager entityManager = new EntityManagerImpl();
        location = entityManager.findById(Location.class, 1200);
        location.setStreetAddress("new postal code");
        location = locationDao.updateLocation(location);
        assertEquals(locationDao.getLocationById(location.getId()), location);
    }

    @Test
    public void cTestDeleteLocation() {
        locationDao.deleteLocation(location);
        Location locById = locationDao.getLocationById(location.getId());

        assertNull(locById);
    }

    @Test
    public void dGetAllLocations() {
        List<Location> oldLoc = locationDao.getAllLocations();
        //add new location
        location.setCity("Bucuresti test2");
        location.setPostalCode("1234");
        location.setStateProvince("Buc test2");
        location.setStreetAddress("test adresa2");
        locationDao.insertLocation(location);
        List<Location> newLoc = locationDao.getAllLocations();

        assertEquals(oldLoc.size(), newLoc.size()-1);
    }
}