# address-book

An address book application that developed by using Java EE.

### How to run?

Simply:

``mvn clean install``

And then you can deploy the war file to your application server.

Application is defaultly using an embedded database (H2) and it stores data only in cache. So when application shuts down all data will be lost. If you want to use another database, you can edit the persistence.xml file to your needs.

Tested on WildFly 10.
