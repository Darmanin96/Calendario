package dad.controller;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;

public class MainController {
    @FXML
    private GridPane calendarGrid;

    private final IntegerProperty yearProperty = new SimpleIntegerProperty(LocalDate.now().getYear());

    @FXML
    public void initialize() {
        for (int month = 1; month <= 12; month++) {
            MonthCalendar monthCalendar = new MonthCalendar();
            monthCalendar.monthProperty().set(month); // Aquí el valor debe ser entre 1 y 12
            monthCalendar.yearProperty().bind(yearProperty);
            int row = (month - 1) / 4;
            int col = (month - 1) % 4;
            calendarGrid.add(monthCalendar, col, row);
        }

    }

    @FXML
    private void onPreviousYear() {
        yearProperty.set(yearProperty.get() - 1);
    }

    @FXML
    private void onNextYear() {
        yearProperty.set(yearProperty.get() + 1);
    }
}
