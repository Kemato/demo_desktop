package com.todo.demo.demo_desktop.util;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jfxtras.scene.control.LocalTimePicker;

public class FieldChangeListener {
    private final BooleanProperty changed = new SimpleBooleanProperty(false);

    public FieldChangeListener(TextField textField) {
        String initialValue = textField.getText();
        textField.textProperty().addListener((observable, oldValue, newValue) ->
                changed.set(!newValue.equals(initialValue))
        );
    }

    public FieldChangeListener(TextArea textArea) {
        String initialValue = textArea.getText();
        textArea.textProperty().addListener((observable, oldValue, newValue) ->
                changed.set(!newValue.equals(initialValue))
        );
    }

    public FieldChangeListener(DatePicker datePicker) {
        Object initialValue = datePicker.getValue();
        datePicker.valueProperty().addListener(
                (observable, oldValue, newValue) ->
                        changed.set(!newValue.equals(initialValue))
        );
    }

    public FieldChangeListener(LocalTimePicker localTimePicker) {
        Object initialValue = localTimePicker.getLocalTime();
        localTimePicker.localTimeProperty().addListener(
                (observable, oldValue, newValue) ->
                        changed.set(!newValue.equals(initialValue))
        );
    }

    public <T> FieldChangeListener(ComboBox<T> comboBox) {
        T initialValue = comboBox.getValue();
        comboBox.valueProperty().addListener(
                (observable, oldValue, newValue)
                        -> changed.set(!newValue.equals(initialValue))
        );
    }

    public BooleanProperty changedProperty(){
        return changed;
    }

    public boolean isChanged(){
        return changed.get();
    }
}
