package project02;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.YearMonth;


public class Main {


    private static int getValidInput(Scanner input, int min, int max) {
        // this method gets a range as a parameter , and dont let the user enters another input and also handles exceptions
        int value;
        while (true) {
            try {
                value = input.nextInt();
                input.nextLine();
                if (value < 0) {
                    System.out.println("Please enter a positive number");
                    continue;
                } else if (value > max || value < min) {
                    System.out.println("Please enter a valid input (" + min + "-" + max + ")");
                    continue;
                }
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid input (integer)");
                input.nextLine();
            }
        }
    }

    private static String getValidName(Scanner input, String[] exceptions) {
        // this method gets a valid string input , and the string array exceptions is because sometimes
        // the user needs to do actions like cancelling by entering a specific numner

        String name;

        while (true) {

            name = input.nextLine().trim();

            if (name.isBlank()) {
                System.out.println("Please enter an unempty input");
                continue;
            }

            for (int i = 0; i < exceptions.length; i++) {

                if (exceptions[i].equals(name)) {
                    return name;
                }
            }

            if (name.matches("[0-9]+")) {
                System.out.println("Please enter a valid input containing letters");
                continue;
            }
            return name;
        }
    }

    private static Book searchForBook(Scanner input, Library mylibrary, boolean onlyShow) {
        // this method is for searching a book using an id or its name ,and the only show boolean is
        // for some cases that the user just wanna see the books like show books section
        Book selectedBook = null;


        System.out.println("Search Books\n1-Search Book by name\n2-Search book by ID\n0-Back");
        int searchBook = getValidInput(input, 0, 2);

        if (searchBook == 0) {
            System.out.println("Going back..");
            return null;
        } else if (searchBook == 1) {
            System.out.println("Enter the name of the book");

            String searchName = getValidName(input, new String[]{});

            ArrayList<Book> result = mylibrary.searchBookByNamePart(searchName);

            if (result.isEmpty()) {
                System.out.println("No books were found\n1-Search again\n0-back");

                while (true) {
                    int choice = getValidInput(input, 0, 1);

                    if (choice == 0) {
                        System.out.println("Going back..");
                        return null;
                    } else if (choice == 1) {

                        System.out.println("Enter the name of the book");
                        searchName = getValidName(input, new String[]{});
                        result = mylibrary.searchBookByNamePart(searchName);

                        if (result.isEmpty()) {
                            System.out.println("Still no book is found\n1-Search again\n0-back");
                        } else {

                           break;
                        }

                    }
                }
            }

            System.out.println();

            for (int i = 0; i < result.size(); i++) {

                if (result.get(i) == result.getLast()) {
                    System.out.println(i + 1 + ". " + result.get(i));
                } else {
                    System.out.println(i + 1 + ". " + result.get(i) + "\n");
                }
            }

            if (onlyShow) {
                return null;
            }

            System.out.println("Choose the book (by number written before the book)\n0-Cancel");

            int choice = getValidInput(input, 0, result.size());

            if (choice == 0){
                return null;
            }

            selectedBook = result.get(choice - 1);


        } else if (searchBook == 2) {
            System.out.println("Enter the id of the book");

            if (mylibrary.getBookMaxId() == 0) {
                System.out.println("There is no books yet");
                return null;
            }
            int editId = getValidInput(input, 1, mylibrary.getBookMaxId());

            Book found = mylibrary.searchBookByIdAndGetBook(editId);

            if (found == null) {
                System.out.println("This id doesnt exist");
                System.out.println("1-Choose another ID\n0-Back");
                int choice;
                while (true) {
                    choice = getValidInput(input, 0, 1);
                    if (choice == 0) {
                        System.out.println("Going back");
                        return null;
                    } else if (choice == 1) {
                        editId = getValidInput(input, 1, mylibrary.getBookMaxId());
                        found = mylibrary.searchBookByIdAndGetBook(editId);

                        if (found == null) {
                            System.out.println("ID still doesn't exist\n1-Choose another ID\n0-Back");
                        } else {
                            selectedBook = found;
                            break;
                        }


                    }

                }
            } else {
                selectedBook = found;
                System.out.println(selectedBook);

                if (onlyShow) {
                    return null;
                }
            }


        }

        return selectedBook;

    }

    private static Member searchForMember(Scanner input, Library mylibrary, boolean onlyShow) {
        // this method is for searching a members using an id or its name ,and the only show boolean is
        // for some cases that the user just wanna see the books like show members section

        // after this method you should check if it returns null , because wehenver the user wants to cancel searching this method returns null
        Member selectedMember = null;


        System.out.println("Search Members\n1-Search Member by name\n2-Search Member by ID\n0-Back");
        int searchMember = getValidInput(input, 0, 2);

        if (searchMember == 0) {
            System.out.println("Going back..");
            return null;
        } else if (searchMember == 1) {
            System.out.println("Enter the name of the Member");

            String searchName = getValidName(input, new String[]{});

            ArrayList<Member> result = mylibrary.searchMemberByNamePart(searchName);

            if (result.isEmpty()) {
                System.out.println("No Members were found\n1-Search again\n0-back");

                while (true) {
                    int choice = getValidInput(input, 0, 1);

                    if (choice == 0) {
                        System.out.println("Going back..");
                        return null;
                    } else if (choice == 1) {

                        System.out.println("Enter the name of the Member");
                        searchName = getValidName(input, new String[]{});
                        result = mylibrary.searchMemberByNamePart(searchName);

                        if (result.isEmpty()) {
                            System.out.println("Still no Members were found\n1-Search again\n0-back");
                        } else {
                            break;
                        }

                    }
                }
            }


            for (int i = 0; i < result.size(); i++) {

                if (result.get(i) == result.getLast()) {
                    System.out.println(i + 1 + ". " + result.get(i));
                } else {
                    System.out.println(i + 1 + ". " + result.get(i) + "\n");
                }


            }


            if (onlyShow) {
                return null;
            }

            System.out.println("Choose the Member (by number written before the Member)");

            int choice = getValidInput(input, 1, result.size());

            selectedMember = result.get(choice - 1);


        } else if (searchMember == 2) {
            System.out.println("Enter the id of the Member  (ID 1001 must exist , try entering it)");

            if (mylibrary.getMemberMaxId() == 0) {
                System.out.println("There is no members yet");
                return null;
            }
            int targetId = getValidInput(input, 1, mylibrary.getMemberMaxId());

            Member found = mylibrary.searchMemberByIdAndGetMember(targetId);

            if (found == null) {
                System.out.println("This id doesnt exist");
                System.out.println("1-Choose another ID\n0-Back");
                int choice;
                while (true) {
                    choice = getValidInput(input, 0, 1);
                    if (choice == 0) {
                        System.out.println("Going back");
                        return null;
                    } else if (choice == 1) {
                        targetId = getValidInput(input, 1, mylibrary.getMemberMaxId());
                        found = mylibrary.searchMemberByIdAndGetMember(targetId);

                        if (found == null) {
                            System.out.println("ID still doesn't exist\n1-Choose another ID\n0-Back");
                        } else {
                            selectedMember = found;
                            break;
                        }


                    }

                }
            } else {
                selectedMember = found;
                System.out.println(selectedMember);

                if (onlyShow) {
                    // this is for showing case , no specific member has to be selected , we just need to print the members
                    return null;
                }
            }


        }

        return selectedMember;

    }

    private static Member searchForMemberUsingID(Scanner input, Library mylibrary) {
        // this mehtod is for searching a memebr only by id , (when the user is not the admin)

        Member selectedMember = null;

        System.out.println("Enter your ID  (ID 1001 must exist , try entering it)\n0-Back");


        int targetId = getValidInput(input, 0, mylibrary.getMemberMaxId());

        if (targetId == 0) {
            System.out.println("Going back..");
            return null;
        }

        Member found = mylibrary.searchMemberByIdAndGetMember(targetId);

        if (found == null) {
            System.out.println("This id doesnt exist");
            System.out.println("1-Choose another ID\n0-Back");
            int choice;
            while (true) {
                choice = getValidInput(input, 0, 1);
                if (choice == 0) {
                    System.out.println("Going back");
                    return null;
                } else if (choice == 1) {
                    targetId = getValidInput(input, 1, mylibrary.getMemberMaxId());
                    found = mylibrary.searchMemberByIdAndGetMember(targetId);

                    if (found == null) {
                        System.out.println("ID still doesn't exist\n1-Choose another ID\n0-Back");
                    } else {
                        selectedMember = found;
                        break;
                    }


                }

            }
        } else {
            selectedMember = found;

        }

        return selectedMember;
    }

    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 100;

    public static void main(String[] args) {

        Library mylibrary = Library.getLibrary();

        Scanner input = new Scanner(System.in);
        int menu;


        do {

            System.out.println("Are you an admin or a member?\n1-Admin\n2-Member\n3-Exit");

            menu = getValidInput(input, 1, 3);


            switch (menu) {

                case 1: {
                    // Admin section

                    System.out.println("1-Books Management\n2-Members Management\n0-back");

                    int adminMenu = getValidInput(input, 0, 2);

                    switch (adminMenu) {

                        case 1: {
                            // books management
                            System.out.println("Books Management\n1-Add\n2-Edit\n3-delete\n4-show\n0-Back");

                            int booksManagementMenu = getValidInput(input, 0, 4);


                            switch (booksManagementMenu) {


                                // add book
                                case 1: {


                                    String name;
                                    System.out.println("0-Cancel adding\nName:");

                                    // checking invalid inputs and cancel option
                                    name = getValidName(input, new String[]{"0"});


                                    // if adding is canceled
                                    if (name.equals("0")) {
                                        System.out.println("Cancelled!");
                                        break;

                                        // in case someone enters the same book name
                                    } else if (mylibrary.checkSameBook(name)) {
                                        int sameBookIndex = mylibrary.getSameBook(name);
                                        System.out.println("Is this the same book as "
                                                + mylibrary.getBooksInfo().get(sameBookIndex).toStringNameAuthor() + " ? ");
                                        System.out.println("1-Yes\n2-No");


                                        int answer = getValidInput(input, 1, 2);


                                        if (answer == 1) {
                                            mylibrary.addCopy(name);

                                            System.out.println("You have successfully added a copy of this book\n\n" +
                                                    mylibrary.getBooksInfo().get(mylibrary.searchBookByName(name)));
                                            break;
                                        }


                                    }

                                    // if it is not the same book

                                    System.out.println("0-Cancel adding\n1-Unknown author\nAuthor:");
                                    String author;

                                    author = getValidName(input, new String[]{"0", "1"});


                                    if (author.equals("0")) {
                                        System.out.println("Cancelled");
                                        break;
                                    } else if (author.equals("1")) {
                                        // author = "Unknown"; (this is handled in the constructor)
                                        Book book = new Book(name);
                                        mylibrary.addBook(book);
                                        System.out.println("You have successfully made a new book\n\n" + book);

                                    } else {
                                        Book book = new Book(name, author);
                                        mylibrary.addBook(book);
                                        System.out.println("You have made a new book successfully\n\n" + book);
                                        break;
                                    }


                                    break;
                                }


                                // edit book
                                case 2: {
                                    Book selectedBook = searchForBook(input, mylibrary, false);

                                    if (selectedBook == null) {
                                        break;

                                    }


                                    // changing the name or author after choosing the way of searching

                                    System.out.println("1-Change name\n2-Change author\n0-Back");
                                    int editMenu = getValidInput(input, 0, 2);

                                    // back
                                    if (editMenu == 0) {
                                        System.out.println("Going back");
                                        break;

                                        //Change books name
                                    } else if (editMenu == 1) {
                                        System.out.println("What is the new name of this book ?\n" + selectedBook);
                                        String newName = getValidName(input, new String[]{});

                                        selectedBook.setName(newName);

                                        System.out.println("You have updated the book's name successfully\n" + selectedBook);

                                        // change author's name
                                    } else if (editMenu == 2) {
                                        System.out.println("What is the new name of the author of the book\n" + selectedBook + " ?");
                                        String newAuthor = getValidName(input, new String[]{});

                                        selectedBook.setAuthor(newAuthor);

                                        System.out.println("You have successfully updated the author of the book\n" + selectedBook);

                                    }


                                    break;
                                }


                                // delete book
                                case 3: {

                                    Book selectedBook = searchForBook(input, mylibrary, false);

                                    if (selectedBook == null) {
                                        break;
                                    }

                                    // the user will be asked if he wants to remove only one copy from the book or all copies
                                    // (in case the book has more than one copy)
                                    if(selectedBook.getCopies() > 1) {

                                        System.out.println("1-Remove one copy (copies: " + selectedBook.getCopies()
                                                + "→" + (selectedBook.getCopies() - 1) + ")");
                                        System.out.println("2-Remove all copies\n0-back");
                                        int choice = getValidInput(input, 0, 2);

                                        switch (choice) {

                                            // back
                                            case 0: {
                                                System.out.println("Going back..");
                                                break;
                                            }


                                            // delete one copy
                                            case 1: {
                                                System.out.println("Are you sure you want to remove one copy from the book ? \n" + selectedBook);
                                                System.out.println("1-Yes\n2-No");

                                                int confirm = getValidInput(input, 1, 2);

                                                if (confirm == 2) {

                                                    break;

                                                } else if (confirm == 1) {
                                                    selectedBook.setCopies(selectedBook.getCopies() - 1);
                                                    System.out.println("You have successfully deleted a copy of the book");
                                                    if (selectedBook.getCopies() == 0) {
                                                        selectedBook.setStatus(Status.UNAVAILABLE);
                                                    }
                                                }
                                                break;
                                            }


                                            // delete all copies
                                            case 2: {
                                                System.out.println("Are you sure you want to remove all copies of the book ? \n" + selectedBook);
                                                System.out.println("1-yes\n2-No");
                                                int confirm = getValidInput(input, 1, 2);
                                                if (confirm == 2) {
                                                    System.out.println("Deleting is cancelled");
                                                    break;
                                                } else if (confirm == 1) {
                                                    selectedBook.setCopies(0);
                                                    selectedBook.setStatus(Status.UNAVAILABLE);
                                                }


                                            }
                                        }

                                        // else if the book has only one copy then it will get deleted
                                    }else {

                                        System.out.println("Are you sure you want to remove one copy from the book ? \n" + selectedBook);
                                        System.out.println("1-Yes\n2-No");

                                        int confirm = getValidInput(input, 1, 2);

                                        if (confirm == 2) {

                                            break;

                                        } else if (confirm == 1) {
                                            selectedBook.setCopies(0);
                                            selectedBook.setStatus(Status.UNAVAILABLE);
                                            System.out.println("You have successfully deleted the book");
                                        }


                                    }




                                    break;
                                }


                                //show book
                                case 4: {

                                    if (mylibrary.getBooksInfo().isEmpty()) {
                                        System.out.println("No books in library yet");
                                        break;
                                    }

                                    System.out.println("Show Books\n1-Show all\n2-Search\n0-Back");
                                    int choice = getValidInput(input, 0, 2);


                                    switch (choice) {

                                        //show all books
                                        case 1: {


                                            for (int i = 0; i < mylibrary.getBooksInfo().size(); i++) {

                                                System.out.println(mylibrary.getBooksInfo().get(i));

                                            }
                                            break;

                                        }

                                        // search book and show
                                        case 2: {

                                            Book selectedBook = searchForBook(input, mylibrary, true);

                                            if (selectedBook == null) {
                                                break;
                                            }


                                            System.out.println(selectedBook);

                                            break;

                                        }

                                        case 0: {
                                            System.out.println("Going back");
                                            break;

                                        }

                                    }


                                    break;
                                }


                                // back
                                case 0: {

                                    System.out.println("Going back...");

                                    break;
                                }
                            }
                            break;
                        }

                        case 2: {
                            //members management


                            System.out.println("Members Management\n1-Add\n2-Edit\n3-Delete\n4-Show\n0-Back");

                            int membersManagementMenu = getValidInput(input, 0, 4);


                            switch (membersManagementMenu) {

                                // add member
                                case 1: {
                                    System.out.println("0-Cancel adding\nName:");
                                    String name = getValidName(input, new String[]{"0"});

                                    if (name.equals("0")) {
                                        System.out.println("Going back..");
                                        break;
                                    }

                                    int year;
                                    int month;
                                    int day;

                                    Date birthday;
                                    while (true) {


                                        System.out.println("When is your birthday?\nYear:");
                                        year = getValidInput(input, 1900, Date.getNowYear());
                                        System.out.println("Month:");
                                        month = getValidInput(input, 1, 12);
                                        System.out.println("Day:");
                                        day = getValidInput(input, 1, YearMonth.of(year, month).lengthOfMonth());

                                        birthday = new Date(year, month, day);

                                        if (birthday.calcAge() < MIN_AGE) {
                                            System.out.println("The user must be at least 18");

                                        } else if (birthday.calcAge() > MAX_AGE) {
                                            System.out.println("Bro, you’ve become a dinosaur , enter a younger age (<100) ☠️");

                                        } else {
                                            Member member = new Member(name, birthday);
                                            mylibrary.addMember(member);
                                            System.out.println("You have successfully made a new member\n" + member);

                                            break;
                                        }

                                        System.out.println("0-Cancel adding\n1-Re-enter birthday");
                                        int choice = getValidInput(input, 0, 1);

                                        if (choice == 0) {
                                            System.out.println("Canceled");
                                            break;
                                        }


                                    }


                                    break;
                                }


                                //edit member
                                case 2: {


                                    Member selectedMember = searchForMember(input, mylibrary, false);

                                    if (selectedMember == null) {
                                        break;
                                    }

                                    System.out.println("Edit member\n1-Edit name\n2-Edit birthday\n0-Back");
                                    int editMenu = getValidInput(input, 0, 2);

                                    switch (editMenu) {

                                        case 0: {
                                            System.out.println("Going back..");
                                            break;
                                        }

                                        case 1: {
                                            System.out.println("Enter the new name of the member" + selectedMember);
                                            String name = getValidName(input, new String[]{});
                                            selectedMember.setName(name);
                                            System.out.println("You have successfully updated member's name\n" + selectedMember);

                                            break;
                                        }

                                        case 2: {
                                            System.out.println("Enter the new date of birth of the member\n" + selectedMember);

                                            Date newBD;
                                            while (true) {

                                                System.out.println("Year:");
                                                int year = getValidInput(input, 1900, Date.getNowYear());
                                                System.out.println("Month:");
                                                int month = getValidInput(input, 1, 12);
                                                System.out.println("Day");
                                                int day = getValidInput(input, 1, YearMonth.of(year, month).lengthOfMonth());

                                                newBD = new Date(year, month, day);

                                                if (newBD.calcAge() < MIN_AGE) {
                                                    System.out.println("Age must be at least 18");

                                                } else if (newBD.calcAge() > MAX_AGE) {
                                                    System.out.println("Bro, you’ve become a dinosaur , enter a younger age (<100) ☠️");
                                                } else {
                                                    selectedMember.setBirthday(newBD);
                                                    System.out.println("Member's date of birth has been updated successfully\n" + selectedMember);
                                                    break;
                                                }

                                                System.out.println("1-Re-enter date of birth\n0-Back");
                                                int choice = getValidInput(input, 0, 1);
                                                if (choice == 0) {
                                                    System.out.println("Going back..");
                                                    break;
                                                }

                                            }


                                        }
                                    }


                                    break;
                                }


                                // delete member
                                case 3: {


                                    Member selectedMember = searchForMember(input, mylibrary, false);

                                    if (selectedMember == null) {
                                        break;
                                    }

                                    if (!selectedMember.getBorrowed().isEmpty()) {
                                        System.out.println(selectedMember.getName() + " is currently borrowing the book/s: " +
                                                selectedMember.borrowedBookToString() + "\nPlease return the book/s before deleting");
                                        break;
                                    }

                                    System.out.println("Are you sure you want to delete this member?\n1-Yes\n2-No\n" + selectedMember);
                                    int choice = getValidInput(input, 1, 2);

                                    if (choice == 1) {
                                        mylibrary.getMembersInfo().remove(selectedMember);
                                        System.out.println("You have successfully removed the member");
                                    } else if (choice == 2) {
                                        System.out.println("Cancelled");

                                    }


                                    break;
                                }


                                // show member
                                case 4: {

                                    if (mylibrary.getMembersInfo().isEmpty()) {
                                        System.out.println("There is no member yet!");
                                        break;
                                    }

                                    System.out.println("1-Show all\n2-Search\n0-Back");
                                    int choice = getValidInput(input, 0, 2);

                                    if (choice == 0) {
                                        System.out.println("Going back..");
                                        break;
                                    } else if (choice == 1) {
                                        for (int i = 0; i < mylibrary.getMembersInfo().size(); i++) {
                                            System.out.println(mylibrary.getMembersInfo().get(i) + "\n");
                                        }

                                    } else if (choice == 2) {

                                        // using this method as void method (just to search and print members)
                                        Member selectedMember = searchForMember(input, mylibrary, true);

                                        if (selectedMember == null) {
                                            // in case anything is added after the search for book method , so when the user presses
                                            //back (0) , it will return null and then the code shouldn't keep running . so if null break
                                            break;
                                        }

                                    }

                                    break;
                                }


                                // back
                                case 0: {

                                    System.out.println("Going back..");

                                    break;
                                }
                            }
                            break;
                        }

                        case 0:
                            // back
                            System.out.println("Going back...");
                            break;


                    }

                    break;
                }

                case 2: {
                    // member section



                         Member memberUsing = null;

                        System.out.println("1-Login your account to get access to member's features\n2-Continue as a guest\n0-Back");

                        int login = getValidInput(input, 0, 2);

                        if (login == 0) {
                            System.out.println("Going back..");
                            break;

                        } else if (login == 1) {

                            if (mylibrary.getMembersInfo().isEmpty()) {
                                System.out.println("There is no members yet\n1-Register with the admin\n2-Continue as a guest");
                                int noMember = getValidInput(input, 1, 2);
                                if (noMember == 1) {
                                    break;
                                }
                            }

                            memberUsing = searchForMemberUsingID(input, mylibrary);
                            if (memberUsing == null) {
                                break;
                            }
                            System.out.println("Welcome " + memberUsing.getName());

                        } else if (login == 2) {
                            System.out.println("You're browsing as a guest , register with the admin to unlock full features");
                        }



                        int memberSectionMenu;

                    do {

                        System.out.println("1-Books Section\n2-My Profile\n0-back");
                        memberSectionMenu = getValidInput(input, 0, 2);



                        switch (memberSectionMenu) {

                            case 1: {
                                // Books section

                                System.out.println("Books Section\n1-Show books\n2-Borrow book\n3-Return book\n0-Back");
                                int booksSectionMenu = getValidInput(input, 0, 3);


                                switch (booksSectionMenu) {

                                    // show book
                                    case 1: {

                                        if (mylibrary.getBooksInfo().isEmpty()) {
                                            System.out.println("No books in library yet");
                                        }

                                        System.out.println("1-Show all\n2-Search Book\n0-Back");
                                        int choice = getValidInput(input, 0, 2);

                                        switch (choice) {

                                            case 0: {
                                                System.out.println("Going back..");
                                                break;
                                            }

                                            case 1: {
                                                System.out.println();
                                                for (int i = 0; i < mylibrary.getBooksInfo().size(); i++) {
                                                    System.out.println(mylibrary.getBooksInfo().get(i) + "\n");
                                                }

                                                break;
                                            }

                                            case 2: {
                                                Book selectedBook = searchForBook(input, mylibrary, true);
                                                if (selectedBook == null) {
                                                    break;
                                                }


                                            }
                                        }

                                        break;
                                    }


                                    // Borrow book
                                    case 2: {

                                        if (memberUsing == null) {
                                            System.out.println("You need an account to use this feature\n1-ask the admin to add you\n2-Login");
                                            int login2 = getValidInput(input, 1, 2);
                                            if (login2 == 1) {
                                                break;
                                            } else if (login2 == 2) {
                                                memberUsing = searchForMemberUsingID(input, mylibrary);
                                                if (memberUsing == null) {
                                                    break;
                                                }
                                            }
                                        }


                                        if (!mylibrary.thereIsAvailableBooks()) {
                                            System.out.println("No available books in library , you can view the books from show section");
                                            break;
                                        }

                                        System.out.println("1-Show all books\n2-Search\n0-Back");
                                        int choice = getValidInput(input, 0, 2);
                                        Book bookChosen = null;

                                        switch (choice) {

                                            case 0: {
                                                System.out.println("Going back..");
                                                break;
                                            }

                                            case 1: {


                                                while (true) {

                                                    for (int i = 0; i < mylibrary.getBooksInfo().size(); i++) {
                                                        System.out.println((i + 1) + ". " + mylibrary.getBooksInfo().get(i));
                                                    }
                                                    System.out.println("Choose the book (by number written before the book)");

                                                    int chosen = getValidInput(input, 1, mylibrary.getBooksInfo().size()) - 1;

                                                    if (mylibrary.getBooksInfo().get(chosen).getStatus() != Status.AVAILABLE) {
                                                        System.out.println("This book is currently unavailable\n1-Choose another book\n0-Back");
                                                        int choseAgain = getValidInput(input, 0, 1);
                                                        if (choseAgain == 0) {
                                                            break;
                                                        } else if (choseAgain == 1) {
                                                            continue;
                                                        }
                                                    } else {
                                                        bookChosen = mylibrary.getBooksInfo().get(chosen);
                                                        break;
                                                    }


                                                    // this is because if the user presses 0 (cancel) , then bookChosen will stay null
                                                }
                                                if (bookChosen == null) {
                                                    break;
                                                }


                                                if (mylibrary.borrow(memberUsing, bookChosen)) {
                                                    System.out.println(memberUsing.getName() +
                                                            " has successfully borrowed the book " + bookChosen.getName());

                                                    // in case the method returns false (which is almost impossible)
                                                } else {
                                                    System.out.println("something went wrong , you haven't borrowed this book , please try again later");
                                                }

                                                break;

                                            }

                                            case 2: {



                                                while (true) {

                                                    bookChosen = searchForBook(input, mylibrary, false);
                                                    if (bookChosen == null) {
                                                        break;
                                                    } else {

                                                        if (bookChosen.getStatus() != Status.AVAILABLE) {
                                                            System.out.println("his book is currently unavailable\n1-Choose another book\n0-Back");
                                                            int choseAgain = getValidInput(input, 0, 1);
                                                            if (choseAgain == 0) {
                                                                break;
                                                            } else if (choseAgain == 1) {
                                                                continue;
                                                            }
                                                        }else {
                                                            break;
                                                        }


                                                    }
                                                }


                                                // this is because if the user presses 0 (cancel) , then bookChosen will stay null
                                                if (bookChosen == null){
                                                    break;
                                                }

                                                if (mylibrary.borrow(memberUsing, bookChosen)) {
                                                    System.out.println(memberUsing.getName() +
                                                            " has successfully borrowed the book " + bookChosen.getName());

                                                    // in case the method returns false (which is almost impossible)
                                                } else {
                                                    System.out.println("something went wrong , you haven't borrowed this book , please try again later");
                                                }




                                            }
                                        }


                                        break;
                                    }


                                    //Return book
                                    case 3: {

                                        if (memberUsing == null) {
                                            System.out.println("You need an account to use this feature\n1-ask the admin to add you\n2-Login");
                                            int login2 = getValidInput(input, 1, 2);
                                            if (login2 == 1) {
                                                break;
                                            } else if (login2 == 2) {
                                                memberUsing = searchForMemberUsingID(input, mylibrary);
                                                if (memberUsing == null) {
                                                    break;
                                                }
                                            }
                                        }


                                        if (memberUsing.getBorrowed().isEmpty()) {
                                            System.out.println("Your borrowed list is empty. Nothing to return yet");
                                            break;
                                        } else {

                                            int maxBorrowedBooks = memberUsing.getBorrowed().size();

                                            for (int i = 0; i < maxBorrowedBooks; i++) {
                                                System.out.println((i + 1) + ". " + memberUsing.getBorrowed().get(i) + "\n");
                                            }

                                            System.out.println("Choose the book you want to return (by number written before the book)");

                                            int returnBook = getValidInput(input, 1, maxBorrowedBooks) - 1;

                                            Book returned = memberUsing.getBorrowed().get(returnBook);


                                            // returnBook is a boolean method that does the logic and then returns weather
                                            // returning the book has done or not

                                            if (mylibrary.returnBook(memberUsing, returned)) {
                                                System.out.println(memberUsing.getName() + " has successfully returned the book " + returned.getName());
                                                break;

                                                // in case the method returns false (which is almost impossible)
                                            } else {
                                                System.out.println("Something went wrong , you haven't returned this book , please try again later");
                                                break;
                                            }


                                        }


                                    }

                                    // back
                                    case 0: {

                                        System.out.println("Going back..");
                                        break;
                                    }
                                }
                                break;

                            }


                            // My profile
                            case 2: {

                                if (memberUsing == null) {
                                    System.out.println("You need an account to use this feature\n1-ask the admin to add you\n2-Login");
                                    int login2 = getValidInput(input, 1, 2);
                                    if (login2 == 1) {
                                        break;
                                    } else if (login2 == 2) {
                                        memberUsing = searchForMemberUsingID(input, mylibrary);
                                        if (memberUsing == null) {
                                            break;
                                        }
                                    }
                                }

                                System.out.println(memberUsing);


                                break;
                            }

                            // back
                            case 0: {
                                // Back
                                System.out.println("Going back...");

                                break;
                            }


                        }

                    }while( memberSectionMenu != 0);
                    break;
                }

                case 3:
                    // exit
                    System.out.println("Not a goodbye but see you later ;)");
                    break;
            }
        } while (menu != 3);

    }
}
