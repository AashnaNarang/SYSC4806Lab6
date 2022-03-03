package springmvc.buddyinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class MainController {

    @Autowired
    AddressBookRepository repository;

    @GetMapping("/home")
    public String index(Model model) {
        return "home";
    }

    @PostMapping("/addbuddy")
    public String addBuddy(@ModelAttribute BuddyInfo buddy, @RequestParam(value="id") Long id, Model model) {
        List<AddressBook> addressBook = repository.findAddressBookById(id);
        AddressBook book = addressBook.get(0);
        book.addBuddy(buddy);
        AddressBook b = repository.save(book);

        model.addAttribute("addressbook", b);
        model.addAttribute("newBuddy", new BuddyInfo());
        return "addressbook";
    }

    @PostMapping("/addy")
    public String addAddressBook(Model model) {
        AddressBook addressbook = new AddressBook();
        AddressBook book = repository.save(addressbook);
        model.addAttribute("addressbook", book);
        model.addAttribute("newBuddy", new BuddyInfo());
        return "addressbook";
    }

}