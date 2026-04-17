package project02;

import java.util.ArrayList;

public class Library {

    private static Library library = new Library();

    private final ArrayList<Member> membersInfo = new ArrayList<>();
    private final ArrayList<Book> booksInfo = new ArrayList<>();


    // singleton
    private Library() {
    }
    public static Library getLibrary(){
        return library;
    }


    //package-private members methods

    int getMemberMaxId() {
        int max = 0;

        for (int i = 0; i < membersInfo.size(); i++) {
            if (max < membersInfo.get(i).getId()) {
                max = membersInfo.get(i).getId();
            }
        }
        return max;
    }

    ArrayList<Member> getMembersInfo() {
        return membersInfo;
    }

    void addMember(Member member) {
        membersInfo.add(member);
    }

    ArrayList<Member> searchMemberByNamePart(String name) {

        ArrayList<Member> result = new ArrayList<>();

        for (int i = 0; i < membersInfo.size(); i++) {
            if (membersInfo.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(membersInfo.get(i));
            }
        }
        return result;

    }

    Member searchMemberByIdAndGetMember(int id) {

        for (int i = 0; i < membersInfo.size(); i++) {
            if (id == membersInfo.get(i).getId()) {
                return membersInfo.get(i);
            }
        }

        return null;
    }



    // book methods

    ArrayList<Book> getBooksInfo() {
        return booksInfo;
    }

    int searchBookByName(String target) {
        for (int i = 0; i < booksInfo.size(); i++) {
            if (booksInfo.get(i).getName().equalsIgnoreCase(target)) {
                return i;
            }

        }
        return -1;
    }

    Book searchBookByIdAndGetBook(int id) {

        for (int i = 0; i < booksInfo.size(); i++) {
            if (booksInfo.get(i).getId() == id)
                return booksInfo.get(i);
        }
        return null;
    }


    boolean checkSameBook(String book) {
        if (searchBookByName(book) == -1) return false;

        return true;
    }

    int getSameBook(String book) {
        return searchBookByName(book);
    }

    void addBook(Book book) {
        // this method will be called if the check same book method doesnt return -1
        booksInfo.add(book);

        book.setCopies(book.getCopies() + 1);

    }

    void addCopy(String name) {
        int add_index = searchBookByName(name);
        int copies = booksInfo.get(add_index).getCopies();
        booksInfo.get(add_index).setCopies(copies + 1);

    }

    ArrayList<Book> searchBookByNamePart(String name) {
        ArrayList<Book> result = new ArrayList<>();

        for (int i = 0; i < booksInfo.size(); i++) {
            if (booksInfo.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(booksInfo.get(i));
            }
        }

        return result;
    }

    int getBookMaxId() {
        int max = 0;

        for (int i = 0; i < booksInfo.size(); i++) {
            if (max < booksInfo.get(i).getId()) {
                max = booksInfo.get(i).getId();
            }
        }
        return max;
    }

    boolean borrow(Member member, Book book) {

        if (book.getCopies() == 0) return false;

        else if (book.getCopies() != 0) {
            member.addBorrowed(book);
            book.addBorrower(member);
            book.setCopies(book.getCopies() - 1);
            if (book.getCopies() == 0) book.setStatus(Status.BORROWED);
            return true;
        }
        return false;
    }

    boolean returnBook(Member member, Book book) {
        if (!member.getBorrowed().contains(book)) {
            return false;
        }


        book.setCopies(book.getCopies() + 1);
        member.removeBorrowed(book);
        book.removeBorrower(member);
        book.setStatus(Status.AVAILABLE);
        return true;

    }

    boolean thereIsAvailableBooks() {

        for (int i = 0; i < booksInfo.size(); i++) {
            if (booksInfo.get(i).getStatus() == Status.AVAILABLE)
                return true;
        }

        return false;
    }

}
