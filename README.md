# Library-Management-System-Project
## Overview
The LMS software is designed to be used by the admin of a Library in any educational institution. The project is made using Java and MySQL.

## Objective
The objective of this software is to ease the tasks related to the management of a Library. The LMS provides the following functionalities:
* Manage Books - Add / Remove Books, View Books Record 
* Manage Students - Add / Remove Students, View Students Record
* Issue Book 
* Return Book
* View The Status of currently issued Books
## Main Window of the LMS
![1](https://user-images.githubusercontent.com/53531220/105802244-c3e73080-5fc0-11eb-8eea-99107e45ab4b.JPG)

## A brief explaination of the functionalities

### Books
In this window, we will able to add / remove books in the LMS. 
* The "Generate an ID" button will generate a random ID for the new books(to be added) which will be a random alpha-numeric string, every time when the button is clicked.
* Provide all the book details and click "Add Book" to Add the book details to the record.
* To delete a book, fetch the book in the table, select the respective row and click on "Remove book". Note that Book details cannot be removed if it is currently Issued by Someone. 
![2](https://user-images.githubusercontent.com/53531220/105802256-c8134e00-5fc0-11eb-87a7-5eff223861b7.JPG)

### Students
In this window, we will be able to add / remove student(members) in the LMS.
* Works almost same as the Books functionality, but here the ID of the student will not be a random number, because every student has a unique ID fixed by the Institution which will be used here.
* Note that a student record cannot be removed if he/she has some issued books which are not yet returned.

![3](https://user-images.githubusercontent.com/53531220/105802265-ccd80200-5fc0-11eb-832b-3b851040c5f1.JPG)

### Issue
Functinality to issue a book to a student:
* Provide student ID and click "Get Details" to get the student name. Do same for the book.
* Provide an issue date and a return date of the book to the student.
* Here Return Date means the deadline for the returning the book.
* Click "Get Book issued" to Issue the book. Note that, book cannot be issued if it is currently issued by someone else. 

![4](https://user-images.githubusercontent.com/53531220/105802279-d2cde300-5fc0-11eb-9570-dc6019ae47e4.JPG)

### Return
Functinality to return a book to the Library, Enter the book Id and click "Search", to search the relevant book to be returned. Then click on "Return Book" to complete the return process.

![5](https://user-images.githubusercontent.com/53531220/105802308-e1b49580-5fc0-11eb-8ba9-f878f7a6fc31.JPG)

### Status
This functionality helps to view issue details of all the currently issued books from the Library.
* The first table shows issue details of all the books currently issued.
* The second table shows the book issue details of all such books, where the student(who is currently having the book) has not yet returned the book even after his/her deadline for returning that book has passed. 
* Once a book is returned, the issue details will be removed from the tables 

![6](https://user-images.githubusercontent.com/53531220/105802317-e5e0b300-5fc0-11eb-9bcb-bc3595464196.JPG)


## Tools used
* Java Swing For GUI
* MySQL database saving and manipulating data
* IDE - Eclipse
* External Plugins: mysql-connector-java-8.0.22, jcalender-1.4

Executable Jar file also provided here.


