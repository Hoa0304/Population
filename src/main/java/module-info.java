module com.ttchoa22ite.population {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.javadoc;
    requires java.sql.rowset;
    requires jbcrypt;




    opens com.ttchoa22ite.population to javafx.fxml;
    exports com.ttchoa22ite.population;


    opens com.ttchoa22ite.population.controllers.login to javafx.fxml, javafx.graphics;
    exports com.ttchoa22ite.population.controllers.login;




    opens com.ttchoa22ite.population.models to java.sql, javafx.graphics;
    exports com.ttchoa22ite.population.models;


    opens com.ttchoa22ite.population.utils to java.sql.rowset;
    exports com.ttchoa22ite.population.utils;
}