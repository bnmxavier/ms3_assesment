package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) {
        //
        if (args.length == 0){
            System.out.println("Program requires at least one argument: Filename, do not include .csv in the filename");
            return;
        }
        CSVParser myParser = new CSVParser();
        List<Record> inputList = new ArrayList<Record>();

        String in_file_name = args[0] + ".csv"; //Assume file is a csv
        String formated_file_name = args[0]; // Use this to build names for the database, log file and bad records csv


        try {
            inputList = myParser.proccessInput(in_file_name); // read data from csv into program
        } catch (IOException e) { // handle IO issues
            e.printStackTrace();
        }

        inputList.remove(inputList.size()-1); // Remove last element from input list, program reads eof as a blank record

        List<Record> validList = inputList.stream().filter( line ->line.isValidRecord()).collect(Collectors.toList()); // Use stream to filter records into valid/invalid lists
        List<Record> invalidList = inputList.stream().filter( line ->!line.isValidRecord()).collect(Collectors.toList());

        SQLHandler myHandler = new SQLHandler();

        myHandler.openOrCreateDB(formated_file_name); // check to see if db exists to ensure program is re-runnable
        myHandler.createTable(formated_file_name); // create record table if it does not exist to ensure program is re-runnable
        myHandler.clearAllRecords(formated_file_name); //delete existing records from table to ensure program is re-runnable
        myHandler.insertRecords(validList, formated_file_name); // Insert all valid records into record table

        myParser.writeRecordsToFile(invalidList, formated_file_name); // Write invalid records to <input-filename>-bad.csv

        LogfileHandler lf = new LogfileHandler();
        lf.writeToLogFile(formated_file_name, inputList.size(), validList.size(), invalidList.size()); // Create new logfile, write record stats to it
    }
}
