/*
 * Fireshotapp is an easy to use screenshot program
 *     Copyright (C) 2021  Ausgefuchster
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package me.fox.ui.settings.components.ext;

import javafx.beans.NamedArg;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Spinner;
import me.fox.ui.settings.components.SettingsComponent;
import me.fox.ui.settings.events.SpinnerValueChangeEvent;


/**
 * @author (Ausgefuchster)
 * @version (~ 07.04.2021)
 */

public class IntegerSpinnerComponent extends SettingsComponent<SpinnerValueChangeEvent<Integer>> {

    private final Spinner<Integer> spinner;

    public IntegerSpinnerComponent(@NamedArg("text") String text,
                                   @NamedArg("spinnerMin") int spinnerMin,
                                   @NamedArg("spinnerMax") int spinnerMax,
                                   @NamedArg("spinnerInitial") int spinnerInitial) {
        super(text);

        if (spinnerMax == -1)
            spinnerMax = Integer.MAX_VALUE;

        this.spinner = new Spinner<>(spinnerMin, spinnerMax, spinnerInitial);

        this.spinner.setEditable(true);
        this.spinner.getEditor().textProperty().addListener(this::onChange);

        this.getChildren().add(this.spinner);
    }

    private void onChange(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (!newValue.matches("\\d*") || newValue.equals("") || newValue.length() > 8) {
            this.spinner.getEditor().setText(oldValue);
            return;
        }
        if (!oldValue.matches("\\d*"))
            return;
        int intNewValue = Integer.parseInt(newValue);
        if (super.getOnAction() != null)
            super.getOnAction().handle(
                    new SpinnerValueChangeEvent<>(this, intNewValue)
            );
    }

    public void setValue(Integer value) {
        this.spinner.getValueFactory().setValue(value);
    }
}
