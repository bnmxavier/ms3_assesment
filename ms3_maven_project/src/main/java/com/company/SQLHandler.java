package com.company;
import java.sql.*;
import java.util.List;


public class SQLHandler {

        public String buildInsertStatementFromRecord(Record r){ // Turn a Record into an sql statement, this should probably be moved the Record class

            String insert_statement =  "(" + wrapInQuotes(r.getF_name()) + "," + wrapInQuotes(r.getL_name()) + "," + wrapInQuotes(r.getEmail())
                    + "," + wrapInQuotes(r.getSex()) + "," + wrapInQuotes(r.getE()) + ","+ wrapInQuotes(r.getF()) + ","
                    + wrapInQuotes(r.getG()) + ","+ wrapInQuotes(r.getH()) + ","+ wrapInQuotes(r.getI()) + ","+ wrapInQuotes(handleQuotesInData(r.getJ())) + " )";
            //System.out.println(insert_statement); //debug statement
            return insert_statement;
        }

        public String buildAllInsert(List<Record> all_rs ){ // Build an sql statement with all records to be inserted. Somewhat inefficient, could probably use streams

            String toReturn = "INSERT INTO RECORD(A,B,C,D,E,F,G,H,I,J) " + "VALUES ";

            for (int i = 0 ; i < all_rs.size() - 1; i++){ // need the minus one because we want to have different behavior to close the sql statement on the last record
                toReturn = toReturn + buildInsertStatementFromRecord(all_rs.get(i)) + ",";
            }

            toReturn = toReturn + buildInsertStatementFromRecord(all_rs.get(all_rs.size()-1)) + ";"; // handle last record and add semi colon to close sql statement
            return  toReturn;
        }

        public void insertRecords(List<Record> all_rs, String in_file_name){ // Takes a list of records and database name, inserts them into given sql db

            Connection c = null;
            Statement stmt = null;

            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:" + in_file_name + ".db");
                //System.out.println("Opened " + in_file_name + " database successfully"); //debug

                stmt = c.createStatement();
                String sql = buildAllInsert(all_rs);
                stmt.executeUpdate(sql);
                stmt.close();
                c.close();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            //System.out.println("Records inserted successfully"); // debug

        }
    public void clearAllRecords(String in_file_name){
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + in_file_name + ".db");
            //System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "DELETE FROM RECORD;";
            stmt.executeUpdate(sql);
            sql = "VACUUM;";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        //System.out.println("Record cleared successfully"); // debug

    }


        public void openOrCreateDB(String in_file_name) { //Creates db if it doesn't already exist
            Connection c = null;

            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:" +in_file_name + ".db");
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            //System.out.println("Opened " + in_file_name + " database"); // debug
        }
        public void createTable(String in_file_name){ // Create Records table if it doesn't exist
            Connection c = null;
            Statement stmt = null;

            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:" + in_file_name + ".db");
                //System.out.println("Opened " + in_file_name + " database successfully"); // debug

                stmt = c.createStatement();
                String sql = "CREATE TABLE IF NOT EXISTS RECORD " + //Create table, I didn't include a primary key as I can't assume any column data is unique
                        "( A           TEXT, " +
                        " B           TEXT, " +
                        " C        TEXT, " +
                        " D         TEXT, " +
                        " E        TEXT, " +
                        " F        TEXT, " +
                        " G        TEXT, " +
                        " H        TEXT, " +  //Decided to leave H and I as text input for simplicity
                        " I        TEXT, " +
                        " J        TEXT )";
                stmt.executeUpdate(sql);
                stmt.close();
                c.close();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            //System.out.println("Record Table created"); // debug
        }
        public String wrapInQuotes(String to_wrap){ // Helper function to help format strings for sql inserts
            return "\'"+to_wrap+"\'";
        }


        public void selectAllRFromTable(String in_file_name){ // Debug function to check database values
            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:" +in_file_name + ".db");
                c.setAutoCommit(false);
                //System.out.println("Opened " + in_file_name + " database successfully"); //debug

                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery( "SELECT * FROM RECORD;" );

                while ( rs.next() ) {
                    String  a = rs.getString("A");
                    String  b = rs.getString("B");
                    String  C = rs.getString("C");
                    String  d = rs.getString("D");
                    String  e = rs.getString("E");
                    String  f = rs.getString("F");
                    String  g = rs.getString("G");
                    String  h = rs.getString("H");
                    String  i = rs.getString("I");
                    String  j = rs.getString("J");

                    System.out.println( "A = " + a );
                    System.out.println( "B = " + b );
                    System.out.println( "C = " + C );
                    System.out.println( "D = " + d );
                    System.out.println( "E = " + e );
                    System.out.println( "F = " + f );
                    System.out.println( "G = " + g );
                    System.out.println( "H = " + h );
                    System.out.println( "I = " + i );
                    System.out.println( "J = " + j );
                    System.out.println();
                }
                rs.close();
                stmt.close();
                c.close();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            //System.out.println("SELECT* done successfully"); // debug
        }

        public String handleQuotesInData(String value_to_handle){ // Escapes any ' present in data to be inserted into sql database.

            String[] split_string = value_to_handle.split("'");
            if(split_string.length > 1){ // check to see if ' is present in string, if not, ignore
                String to_return = split_string[0] + "'"; //Adds back ' we split on
                for(int i = 1; i < split_string.length; i++){ // For loop to escape any ' present in data
                    to_return = to_return + "'" + split_string[i];
                }
                return to_return;
            }
            else return value_to_handle;
        }
        }


