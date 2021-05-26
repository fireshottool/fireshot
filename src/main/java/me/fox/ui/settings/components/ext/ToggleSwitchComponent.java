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
import javafx.event.ActionEvent;
import lombok.Getter;
import me.fox.ui.settings.components.SettingsComponent;
import org.controlsfx.control.ToggleSwitch;


/**
 * @author (Ausgefuchster)
 * @version (~ 08.04.2021)
 */

@Getter
public class ToggleSwitchComponent extends SettingsComponent<ActionEvent> {

    private final ToggleSwitch toggleSwitch;

    public ToggleSwitchComponent(@NamedArg("text") String text) {
        this(text, false);
    }

    public ToggleSwitchComponent(@NamedArg("text") String text, @NamedArg("selected") boolean selected) {
        super(text);
        this.toggleSwitch = new ToggleSwitch();
        this.toggleSwitch.setSelected(selected);
        this.getChildren().add(this.toggleSwitch);
        this.toggleSwitch.selectedProperty().addListener((observable, oldValue, newValue) ->
                super.getOnAction().handle(new ActionEvent(this, null)));
    }

    public boolean isSelected() {
        return this.toggleSwitch.isSelected();
    }

    public void setSelected(boolean selected) {
        this.toggleSwitch.setSelected(selected);
    }
}
