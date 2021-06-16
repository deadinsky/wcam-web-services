# fydp-project

A Spring Boot project built in Java for the purposes of prototyping the web services for a wearable cardiovascular abnormality monitor. This was made for the purposes of the capstone design project which spans the entirety of the fourth year of an Engineering degree.

Since this is a simple Spring Boot project, you can run it as such. Doing a `mvn clean install` and a `java -jar <name>.jar>` is only one such way to do so. To set up properly, you will need the credentials for a connected database, as well as a phone number to enter if you want to test out mobile alerts. The database schema is identical to the class structure, and there is one script needed after recreation which is included in the repository.

Included in this is the ability to use the web interface to navigate all of the information available in the database, as well as the various web services this provides. This includes multi-level account management, a two-medium alert system, and various search options to navigate the database wrapper.