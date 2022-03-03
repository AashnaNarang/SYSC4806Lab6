package springmvc.buddyinfo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AddressBook {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BuddyInfo> buddies;

    public AddressBook() {
        buddies = new ArrayList<BuddyInfo>();
    }

    public void addBuddy(BuddyInfo buddy) {
        buddies.add(buddy);
    }

    public List<BuddyInfo> getBuddies() {
        return buddies;
    }

    public void setBuddies(ArrayList<BuddyInfo> buddies) {
        this.buddies = buddies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String s = "Address Book: " + this.id + "\n";
        for (BuddyInfo buddy : buddies) {
            s += buddy.toString() + "\n";
        }
        return s;
    }

//    public static void main(String[] args) {
//        BuddyInfo a = new BuddyInfo("Aashna", "6131234567");
//        BuddyInfo b = new BuddyInfo("Bob", "6132345678");
//        AddressBook book = new AddressBook();
//        book.addBuddy(a);
//        book.addBuddy(b);
//        System.out.println(book.toString());
//    }
}
