FIRE
====

Unit tested Spring application with Restful API.

Requirements
------------

* Java Developement Kit
* Git (optional)
* Zip (optional)

Installation
------------

Download this application using Git.  

```git_ssh
git clone git@github.com:RenatasNarmontas/fire.git
```
or
```git_https
git clone https://github.com/RenatasNarmontas/fire.git
```

Alternative way: you can download archive from `https://github.com/RenatasNarmontas/fire/archive/master.zip` and extract it to any folder.

Usage
-----

Navigate to `fire` folder and start embedded application server.

```start_server
gradlew bootRun
```

You can access the application API using favourite browser.
* Navigate to `http://localhost:8080/fire?age=0&student=no&income=0` to receive recommended bundle
* Navigate to `http://localhost:8080/bundle?Junior+Saver&age=17&student=false&income=0` to verify bundle availability
 
Test
----

Navigate to `fire` folder and run command.

```test
gradlew test
```
 
-----

#### 2017 &copy; Renatas Narmontas
