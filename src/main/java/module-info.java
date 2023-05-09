module edu.guilford {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.io;

    opens edu.guilford to javafx.fxml;
    exports edu.guilford;
}
