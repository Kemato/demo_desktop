module com.todo.demo.demo_desktop {


    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires static lombok;
    requires jakarta.validation;

    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires java.net.http;

    opens com.todo.demo.demo_desktop.controller to javafx.fxml;
    opens com.todo.demo.demo_desktop.service to javafx.fxml;

    exports com.todo.demo.demo_desktop;
    exports com.todo.demo.demo_desktop.controller;
}
