package ru.job4j.monitortrackertaxi.withunmutablepoint;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public class MonitorVehicleTracker {

    private final ConcurrentHashMap<String, ImmutablePoint> locations;
    private final Map<String, ImmutablePoint> unmodifiableMap;

    public MonitorVehicleTracker(Map<String, ImmutablePoint> points) {
        locations = new ConcurrentHashMap<>(points);
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }
    
    public synchronized Map<String, ImmutablePoint> getLocations() {
        return Collections.unmodifiableMap(new HashMap<>(locations));
    }
    
    public synchronized Map<String, ImmutablePoint> getLocation() {
        return unmodifiableMap;
    }
    
    public synchronized void setLocations(String id, int x, int y) {
        if (locations.replace(id, new ImmutablePoint(x, y)) == null)
            throw new IllegalArgumentException("недопустимое имя такси: " + id);
    }
}
