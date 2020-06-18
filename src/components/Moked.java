package components;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// Helper class to make the HashMap best used
class Report {
    public int id, vehicleid;
    public LocalTime time;
    public boolean confirmed = false;

    public Report(int id, int vehicleid, LocalTime time) {
        this.id = id;
        this.vehicleid = vehicleid;
        this.time = time;
    }

    public String toString() {
        return "ID: " + id + ", Time:" + time + ", Vehicle ID: " + id;
    }
}

public class Moked {
    private ReentrantReadWriteLock lock;
    private Path file;
    private int currentReport;
    private HashMap<Integer, Report> reports;

    public Moked(String fileLocation) {
        file = Paths.get(fileLocation);
        try {
            Files.deleteIfExists(file);
            Files.createFile(file);
        } catch (IOException io) {
            // Do something
        }
        lock = new ReentrantReadWriteLock();
        currentReport = 0;
        reports = new HashMap<>();
    }

    public void writeReport(int vehicleID, LocalTime time) {
        lock.writeLock().lock();
        Report report = new Report(++currentReport, vehicleID, time);
        reports.put(currentReport, report);
        try {
            Files.write(file, report.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lock.writeLock().unlock();
    }

    public int[] readReports(int vehicleID) {
        Vector<Integer> reportIDs = new Vector<Integer>();

        lock.readLock().lock();
        for (Report report : reports.values()) {
            if (report.vehicleid == vehicleID && report.confirmed == false)
                reportIDs.add(report.id);
        }
        lock.readLock().unlock();

        int len = reportIDs.size();
        int[] result = new int[len];
        for (int i = 0; i < len; i++)
            result[i] = reportIDs.get(i).intValue();

        return result;
    }

    public synchronized void confirmReports(int id, int[] confirmed) {
        lock.writeLock().lock();
        for (int reportID : confirmed) {
            reports.get(reportID).confirmed = true;
            try {
                String line = "Vehicle #" + id + " confirmed report #" + reportID + "\n";
                Files.write(file, line.toString().getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                // Do something
            }
        }
        lock.writeLock().unlock();
    }

    public synchronized boolean canShutdown() {
        boolean result = true;
        lock.readLock().lock();
        for (Report report : reports.values()) {
            if (!report.confirmed) {
                result = false;
                break;
            }
        }
        lock.readLock().unlock();
        return result;
    }
}