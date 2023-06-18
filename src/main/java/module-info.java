module com.ibilcerpg.ibilcerpg {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ibilcerpg.ibilcerpg to javafx.fxml;
    exports com.ibilcerpg.ibilcerpg;
    opens com.ibilcerpg.ibilcerpg.Controllers to javafx.fxml;
    exports com.ibilcerpg.ibilcerpg.Controllers;
}