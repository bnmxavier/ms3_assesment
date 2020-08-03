# ms3_assesment
Java  program to read csv's and input valid records into an sql database. The program will also print invalid records to <input_filename>-bad.csv and statistics to a .log file. 


Instructions:
Assuming maven is avaliable in system path
1. Open command line and cd to the ms3_maven_project dir 
2. Delete the directory target (I uploaded the project before I added the gitignore)
3. run 'mvn compile'
4. Copy the target .csv file to ms3_maven_project/target/classes
5. Run the program with 'java com.company.Main <input_filename>'. Do not include the csv file extension in the command line argument
6. Output files will be found in ms3_maven_project/target/classes


Approach Overview

Things done well:
I used maven to organize dependencies and for ease of use on other machines. I used a Record class to help organize the data once it was read and to determine whether that data was a valid or invalid record. I used different classes to handle the various types of input and output, sql, csv, log file etc. These classes also included methods for mapping record objects to the correct input/output format for the given input/output. I used streams to read in and parse data from the csv to improve performance. I formatted all valid records into a single sql insert statement to minimize the number of times the program needed to connect to the database. 

Things that could be improved:
The CSVParser object would be more extensible and cleaner if I had used a library such as OpenCSV to handle csv input and output. The method ’buildAllInsert()’ that turns a list of valid records into an sql insert statement uses a for loop to iterate through the list of records. This could be improved by using a stream and writing a custom mapping function.   
