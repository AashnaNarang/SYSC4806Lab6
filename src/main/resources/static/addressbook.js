var addressBookBuddiesLink = null;
var buddiesTable = null;
var buddyForm = null;
var submitButton = null;
var createAddyButton = null;

$(document).ready(function() {
    buddiesTable = document.getElementById("buddiesTable");
    buddyForm = document.getElementById("addBuddyForm");
    submitButton = document.getElementById("submitBtn");
    createAddyButton = document.getElementById("createAddy");
    submitButton.addEventListener("click", addNewBuddy);
    createAddyButton.addEventListener("click", createNewAddressBook);
    buddiesTable.style.display = "none";
    buddyForm.style.display = "none";
});

function createNewAddressBook(){
    fetch('http://localhost:8080/addressbook', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({})
    }).then(function (resp) {
        resp.json().then(function (addressBook) {
            if(addressBookBuddiesLink !== null) {
                clearBuddies();
            }
            addressBookBuddiesLink = addressBook._links.self.href;
            buddiesTable.style.display = "block";
            buddyForm.style.display = "block";
        });
    });
};

function addNewBuddy(e) {
    e.preventDefault();
    var name = document.getElementById("buddyName").value;
    var phone = document.getElementById("buddyPhone").value;
    fetch('http://localhost:8080/buddy', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "name": name,
            "phoneNumber": phone
        })
    }).then(function (resp) {
        resp.json().then(function (buddy) {
        console.log(buddy);
            var buddyUrl = buddy._links.self.href;

            fetch(addressBookBuddiesLink, {
                method: 'PUT',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                                  "buddy": buddyUrl
                              })
            }).then(function (resp2) {
                attachBuddyToUI(buddy.name, buddy.phoneNumber);
            })
        });
    });
}

function attachBuddyToUI(name, phoneNumber) {
    var buddyRow =  document.createElement('tr')
    buddyRow.innerHTML = "<td>"+name+"</td><td>"+phoneNumber+"</td>";
    buddiesTable.tBodies[0].insertBefore(buddyRow,buddiesTable.rows[buddiesTable.rows.length-1])
}

function clearBuddies() {
    for (var x=buddiesTable.rows.length-2; x>=0; x--) {
       buddiesTable.tBodies[0].removeChild(buddiesTable.rows[x]);
    }
}