package com.company;

public class Record { // Class to hold a line of csv data

    String f_name;
    String l_name;
    String email;
    String sex;
    String e;
    String f;
    String g;
    String h;
    String i;
    String j;

    public Record(String line_read){ // Constructor that takes a line read and builds a Record object
        //System.out.println(line_read);
        String input[] = line_read.split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)"); //Split line read into an array corresponding to csv columns, handle data that has a comma within

        this.f_name = "";
        this.l_name = "";
        this.email = "";
        this.sex = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        this.i = "";
        this.j = "";


        // Switch statement to handle values missing from the end of the line read
        switch(input.length){
            case 1:
                this.f_name = input[0];
                this.l_name = "";
                this.email = "";
                this.sex = "";
                this.e = "";
                this.f = "";
                this.g = "";
                this.h = "";
                this.i = "";
                this.j = "";
                break;
            case 2:
                this.f_name = input[0];
                this.l_name = input[1];
                this.email = "";
                this.sex = "";
                this.e = "";
                this.f = "";
                this.g = "";
                this.h = "";
                this.i = "";
                this.j = "";
                break;
            case 3:
                this.f_name = input[0];
                this.l_name = input[1];
                this.email = input[2];
                this.sex = "";
                this.e = "";
                this.f = "";
                this.g = "";
                this.h = "";
                this.i = "";
                this.j = "";
                break;
            case 4:
                this.f_name = input[0];
                this.l_name = input[1];
                this.email = input[2];
                this.sex = input[3];
                this.e = "";
                this.f = "";
                this.g = "";
                this.h = "";
                this.i = "";
                this.j = "";
                break;
            case 5:
                this.f_name = input[0];
                this.l_name = input[1];
                this.email = input[2];
                this.sex = input[3];
                this.e = input[4];
                this.f = "";
                this.g = "";
                this.h = "";
                this.i = "";
                this.j = "";
                break;
            case 6:
                this.f_name = input[0];
                this.l_name = input[1];
                this.email = input[2];
                this.sex = input[3];
                this.e = input[4];
                this.f = input[5];
                this.g = "";
                this.h = "";
                this.i = "";
                this.j = "";
                break;
            case 7:
                this.f_name = input[0];
                this.l_name = input[1];
                this.email = input[2];
                this.sex = input[3];
                this.e = input[4];
                this.f = input[5];
                this.g = input[6];
                this.h = "";
                this.i = "";
                this.j = "";
                break;
            case 8:
                this.f_name = input[0];
                this.l_name = input[1];
                this.email = input[2];
                this.sex = input[3];
                this.e = input[4];
                this.f = input[5];
                this.g = input[6];
                this.h = input[7];
                this.i = "";
                this.j = "";
                break;
            case 9:
                this.f_name = input[0];
                this.l_name = input[1];
                this.email = input[2];
                this.sex = input[3];
                this.e = input[4];
                this.f = input[5];
                this.g = input[6];
                this.h = input[7];
                this.i = input[8];
                this.j = "";
                break;
            case 10:
                this.f_name = input[0];
                this.l_name = input[1];
                this.email = input[2];
                this.sex = input[3];
                this.e = input[4];
                this.f = input[5];
                this.g = input[6];
                this.h = input[7];
                this.i = input[8];
                this.j = input[9];
        }
    }


    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getG() {
        return g;
    }

    public void setG(String g) {
        this.g = g;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public String getJ() {
        return j;
    }

    public void setJ(String j) {
        this.j = j;
    }
    public boolean isValidRecord(){ // Check Record to see if it is missing any data, return false if it is
        //Thought of using switch statement here but I need to check every value in the object, there's probably a better way of doing this
        if (this.f_name.isBlank() || this.f_name.isEmpty()){
            return false;
        }
        else if(this.l_name.isBlank() || this.l_name.isEmpty()){
            return false;
        }
        else if(this.email.isBlank() || this.email.isEmpty()){
            return false;
        }
        else if(this.sex.isBlank() || this.sex.isEmpty()){
            return false;
        }
        else if(this.e.isBlank() || this.e.isEmpty()){
            return false;
        }
        else if(this.f.isBlank() || this.f.isEmpty()){
            return false;
        }
        else if(this.g.isBlank() || this.g.isEmpty()){
            return false;
        }
        else if(this.h.isBlank() || this.h.isEmpty()){
            return false;
        }
        else if(this.i.isBlank() || this.i.isEmpty()){
           return false;
        }
        else if(this.j.isBlank() || this.j.isEmpty()){
            return false;
        }
        return true;
    }
    public void printRecord(){ // Print record to console for debugging
        System.out.print(this.f_name + " ");
        System.out.print(this.l_name + " ");
        System.out.print(this.email+ " ");
        System.out.print(this.sex+ " ");
        System.out.print(this.e+ " ");
        System.out.print(this.f+ " ");
        System.out.print(this.g+ " ");
        System.out.print(this.h+ " ");
        System.out.print(this.i+ " ");
        System.out.print(this.j);
        System.out.println(" : ");
    }
    public String toString(){ // Turn object into string that can be written to csv
        String to_return = this.f_name + "," + this.l_name + "," + this.email + "," + this.sex + "," +  this.e +  "," + this.f+","+
                this.g + "," + this.h + "," + this.i + "," + this.j + "\n";
        //System.out.println(to_return); // debug statement
        return to_return;
    }

}
//"\""
//"\"" +