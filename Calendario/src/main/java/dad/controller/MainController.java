package dad.controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.time.LocalDate;

public class MainController {

    @FXML
    private GridPane calendarGrid;

    @FXML
    private Text yearLabel;

    private final IntegerProperty yearProperty = new SimpleIntegerProperty(LocalDate.now().getYear());

    @FXML
    public void initialize() {
        // Actualizar la etiqueta de año al iniciar
        updateYearLabel();

        // Crear los calendarios para los 12 meses
        for (int month = 1; month <= 12; month++) {
            MonthCalendar monthCalendar = new MonthCalendar();
            monthCalendar.monthProperty().set(month);
            monthCalendar.yearProperty().bind(yearProperty);
            int row = (month - 1) / 4;
            int col = (month - 1) % 4;
            calendarGrid.add(monthCalendar, col, row);
        }

        // Escuchar cambios en el año para actualizar la etiqueta
        yearProperty.addListener((obs, oldVal, newVal) -> updateYearLabel());
    }

    @FXML
    private void onPreviousYear() {
        yearProperty.set(yearProperty.get() - 1);
    }

    @FXML
    private void onNextYear() {
        yearProperty.set(yearProperty.get() + 1);
    }

    private void updateYearLabel() {
        // Mostrar solo el año actual
        yearLabel.setText(String.valueOf(yearProperty.get()));
    }
}
