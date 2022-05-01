# EmployeeCSVProject

## How to run it:
- go into **target** directory;
- go into **classes** directory;
- type in the **terminal** the following code:
```aidl
java com.sparta.sort.manager.start.Starter
```
or go into Starter **class** and run the code.

## How to clone the repo (assuming you have git installed)
type into terminal the following code:
```
git clone https://github.com/costi-a/EmployeeCSVProject
```

A basic program that reads data from 2 files
(one with 10k employees and the other with more 
than 65k), divides the employees in "unique"
(they have a unique ID), "duplicates" (they have
a duplicate ID) and "null" (they have at least
one data that is null) and stores them into
the db.

There are 2 phases when storing the data, first
it stores them in **"one go"** and then it stores
them in a separate table using **multithreading**.

I have tried between 4 and 6 threads and the result
is that with multithreading it's just more or less
1 second faster (probably because in the process
we divide the list in multiple sublist) and
there is no much difference having 4-5 or 6 threads
(at least on my laptop).

The main difference between the loops and Lambdas
is the **returnUniqueEmployees**, the loop takes
around 75 seconds (**large** file) to get
compiled while the Lambdas expression 264 seconds (
all the other methods get compiled between 0 and 2 seconds 
for both loop and Lambdas).

All the timing was registered on the large file.