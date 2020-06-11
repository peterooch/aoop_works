package components;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Moked {
    private ReentrantReadWriteLock lock;
    private File file;
    private int currentReport;
    private HashMap<Integer, Boolean> reports;

    public Moked(String fileLocation) {
        file = new File(fileLocation);
        lock = new ReentrantReadWriteLock();
        currentReport = 0;
        reports = new HashMap<>();
    }

    public void writeReport(int vehicleID, LocalTime time) {
        lock.writeLock().lock();
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(++currentReport + " " + time.toString() + " " + vehicleID + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        reports.put(currentReport, false);
        lock.writeLock().unlock();
    }

    public String[] readReports(int vehicleID) {
        Vector<String> reports = new Vector<String>();

        lock.readLock().lock();
        try {
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (String.valueOf(vehicleID).equals(line.split(" ")[2]));
                    reports.add(line);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        lock.readLock().unlock();

        String[] result = new String[reports.size()];
        System.arraycopy(reports.toArray(), 0, result, 0, reports.size());
        return result;
    }

    public synchronized void confirmReports(int[] confirmed) {
        for (int reportID : confirmed)
            reports.replace(reportID, true);
    }

    public synchronized boolean canShutdown() {
        for (boolean repStatus : reports.values()) {
            if (!repStatus)
                return false;
        }
        return true;
    }
}