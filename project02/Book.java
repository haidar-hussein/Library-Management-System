package project02;


import java.util.ArrayList;

public class Book {
    private String name;
    private String author;
    private final int id;
    private Status status;
    private final ArrayList<Member> borrower = new ArrayList<>();
    private int copies = 0;
    private static int idCounter = 0;

    // initialization block
    {
        status = Status.AVAILABLE;
    }

    // constructors

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
        idCounter++;
        id = idCounter;
    }

    public Book(String name) {
        this.name = name;
        author = "Unknown";
        idCounter++;
        id = idCounter;

    }



    // package-private getters

    int getId() {
        return id;
    }

    int getCopies() {
        return copies;
    }

    ArrayList<Member> getBorrower() {
        return borrower;
    }

    String getName() {
        return name;
    }

    Status getStatus() {
        return status;
    }



    // package-private setters

    void setStatus(Status status) {
        this.status = status;
    }

    void setCopies(int copies) {
        this.copies = copies;
    }

    void setName(String name) {
        this.name = name;
    }

    void setAuthor(String author) {
        this.author = author;
    }



    // package-private helper

    void addBorrower(Member member) {
        borrower.add(member);
    }

    void removeBorrower(Member member) {
        borrower.remove(member);
    }





    @Override
    public String toString() {

        StringBuilder borr = new StringBuilder();

        if (!borrower.isEmpty()) {
            for (int i = 0; i < borrower.size(); i++) {
                borr.append(i + 1).append(". name:" ).append(borrower.get(i).getName()).append(" ID ").append(borrower.get(i).getId());
            }
        }else {
            borr.append(", not borrowed");
        }

        String stat = "";
        if( status == Status.AVAILABLE){
            stat = "available";
        }else if( status == Status.BORROWED){
            stat = "Borrowed";
        }else if (status == Status.UNAVAILABLE){
            stat = "unavailable";
        }


        return "Book → " +
                "Name:'" + name + '\'' +
                "  Author:'" + author + '\'' +
                "  ID:" + id +
                "  Status:" + stat +
                "  Copies:" + copies +
                "  Borrower:" + borr + "\n";

    }



    public String toStringNameAuthor() {
        return "Book → " +
                "name='" + name + '\'' +
                ", author='" + author + '\'';
    }
}

