package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogfileHandler {
    public void writeToLogFile(String in_file_name, int num_total_recs, int num_valid_recs, int num_invalid_recs){ // Writes record stats to a logfile of a given name

        try {
            File logFile = new File(in_file_name + ".log");
            if (logFile.createNewFile()) { //Create new file or use existing one to ensure program is re-runnable
                //System.out.println("File created: " + logFile.getName()); // debug
            } else {
                //System.out.println("File already exists."); // debug
            }
            FileWriter fw = new FileWriter(logFile);
            fw.write("# of records received: " + num_total_recs + "\n");
            fw.write("# of records successful: " + num_valid_recs+ "\n");
            fw.write("# of records of records failed: " + num_invalid_recs);

            fw.close();


        } catch (IOException e) {
            System.out.println("An error occurred."); // debug
            e.printStackTrace();
        }

    }
}
