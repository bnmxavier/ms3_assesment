package com.company;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class
CSVParser {
    File input;
    File output;

    public List<Record> proccessInput(String file_name) throws IOException { //Function to read in data from csv and store it in an arraylist of Record objects
        String inputFilePath = file_name;
        List<Record> inputList = new ArrayList<Record>();

        try {

            File inputFile = new File(inputFilePath);
            InputStream inputFS = new FileInputStream(inputFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
            inputList = br.lines().skip(1).map(mapToRecord).collect(Collectors.toList()); // Use stream to read in data from csv file and map to Record objects
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputList;
    }private Function<String, Record> mapToRecord = (line) -> { //Function to map csv input to a record object
        return new Record(line);
    };

    public void writeRecordsToFile(List<Record> recs_to_write, String file_name){ // Given a list of records, write them to file
        String outputFilePath = file_name + "-bad.csv";
        File outputFile = new File(outputFilePath);
        try {
            FileWriter myWriter = new FileWriter(outputFilePath);
            for(Record r: recs_to_write){
                myWriter.write(r.toString());
            }
            myWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String escapeSpecialCharacters(String data) { // Handle commas within
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
}
