package dad.controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;

public class MonthCalendar extends GridPane {
    private final IntegerProperty monthProperty = new SimpleIntegerProperty();
    private final IntegerProperty yearProperty = new SimpleIntegerProperty();

    public MonthCalendar() {
        setHgap(5);
        setVgap(5);
        setAlignment(Pos.CENTER);

        // Actualizar el calendario cuando cambien el mes o el año
        monthProperty.addListener((obs, oldVal, newVal) -> updateCalendar());
        yearProperty.addListener((obs, oldVal, newVal) -> updateCalendar());
    }

    private void updateCalendar() {
        // Limpiar el calendario actual
        getChildren().clear();

        // Obtener el año y el mes actuales
        int year = yearProperty.get();
        int month = monthProperty.get();

        // Agregar el nombre del mes
        Text monthName = new Text(YearMonth.of(year, month).getMonth().toString());
        monthName.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        add(monthName, 0, 0, 7, 1); // Combinar 7 columnas

        // Agregar los nombres de los días de la semana
        DayOfWeek[] daysOfWeek = DayOfWeek.values();
        for (int i = 0; i < daysOfWeek.length; i++) {
            Label dayLabel = new Label(daysOfWeek[i].toString().substring(0, 1)); // Primera letra del día
            dayLabel.setStyle("-fx-font-weight: bold;");
            add(dayLabel, i, 1);
        }

        // Obtener el primer día del mes y el número total de días
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        int daysInMonth = yearMonth.lengthOfMonth();

        // Determinar el día de la semana del primer día del mes
        int startDay = firstDayOfMonth.getDayOfWeek().getValue() % 7; // Lunes = 0, ..., Domingo = 6

        // Añadir los días del mes al calendario
        int row = 2; // Comienza después de los días de la semana
        int col = startDay;
        for (int day = 1; day <= daysInMonth; day++) {
            Label dayLabel = new Label(String.valueOf(day));
            add(dayLabel, col, row);

            col++;
            if (col == 7) {
                col = 0;
                row++;
            }
        }
    }

    public IntegerProperty monthProperty() {
        return monthProperty;
    }

    public IntegerProperty yearProperty() {
        return yearProperty;
    }
}
