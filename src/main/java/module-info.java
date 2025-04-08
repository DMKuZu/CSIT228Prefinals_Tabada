module com.csit228.csit228_prefinalstabada {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.csit228.csit228_prefinalstabada to javafx.fxml;
    exports com.csit228.csit228_prefinalstabada;
}