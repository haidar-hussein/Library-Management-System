package project02;

import java.util.ArrayList;


public class Member {

    private String name;
    private int age;
    private Date birthday;
    private final int id;
    private final ArrayList<Book> borrowed = new ArrayList<>();
    private static int idCount = 1000;

    // constructor
    public Member(String name, Date birthday) {
        this.birthday = birthday;
        this.name = name;


        this.age = birthday.calcAge();
        idCount++;
        id = idCount;

    }



    // package-private getters

    String getName() {
        return name;
    }

    int getAge() {
        return age;
    }

    ArrayList<Book> getBorrowed() {
        return borrowed;
    }

    int getId() {
        return id;
    }




    // package-private setters

    void setName(String name) {
        this.name = name;
    }

    void setBirthday(Date birthday) {
        this.birthday = birthday;
        this.age = birthday.calcAge();
    }



    // package-access helper

    void addBorrowed(Book book) {
        this.borrowed.add(book);
    }

    void removeBorrowed(Book book) {
        this.borrowed.remove(book);
    }












    @Override
    public String toString() {

        StringBuilder all = new StringBuilder();

        if (!borrowed.isEmpty()) {

            for (int i = 0; i < borrowed.size(); i++) {
                all.append(i + 1).append(".") .append(borrowed.get(i).getName()).append("(ID:").append(borrowed.get(i).getId()).append(")   ");
            }

        }else{
            all.append("none");
        }

        return
                "name:'" + name + '\'' +
                        "  ID=" + id +
                        "  Age:" + age +
                        "  Birthday:" + birthday +
                        "  Has borrowed: " + all + "\n";
    }

    public String borrowedBookToString(){

        StringBuilder all = new StringBuilder();

        for (int i = 0; i < borrowed.size(); i++) {
            all.append(i + 1).append(".") .append(borrowed.get(i).getName()).append("(ID:").append(borrowed.get(i).getId()).append(")   ");
        }

        return all.toString();
    }
}
