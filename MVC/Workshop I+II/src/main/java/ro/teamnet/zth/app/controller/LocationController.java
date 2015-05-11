package ro.teamnet.zth.app.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;

/**
 * Created by Ionutz on 07.05.2015.
 */
@MyController(urlPath = "/locations")
public class LocationController {
    @MyRequestMethod(urlPath = "/all")
    public String getAllLocations() {
        return "allLocations";
    }

    @MyRequestMethod(urlPath = "/one")
    public String getOneLocation() {
        return "oneRandomLocation";
    }
}
