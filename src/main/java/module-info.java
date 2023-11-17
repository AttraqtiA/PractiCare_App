module com.example.practicare {
    requires javafx.controls;
    requires javafx.fxml;
    /*requires rt;
    requires jfxrt;*/

    opens com.example.practicare to javafx.fxml;
    exports com.example.practicare;
    opens com.example.practicare.Controller to javafx.fxml;
    exports com.example.practicare.Controller;
    exports com.example.practicare.Controller.Doctor_Feature;
    opens com.example.practicare.Controller.Doctor_Feature to javafx.fxml;
    exports com.example.practicare.Controller.Patient_Feature;
    opens com.example.practicare.Controller.Patient_Feature to javafx.fxml;

    opens PC_object to javafx.base;



}