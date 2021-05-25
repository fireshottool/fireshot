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
import javafx.scene.control.ChoiceBox;
import lombok.Getter;
import me.fox.ui.settings.components.SettingsComponent;
import me.fox.ui.settings.events.ChoiceChangeEvent;

/**
 * @author (Ausgefuchster)
 * @version (~ 08.04.2021)
 */

@Getter
public class ChoiceBoxComponent<T> extends SettingsComponent<ChoiceChangeEvent<T>> {

    private final ChoiceBox<T> choiceBox;

    public ChoiceBoxComponent(@NamedArg("text") String text) {
        super(text);
        this.choiceBox = new ChoiceBox<>();
        this.choiceBox.getSelectionModel().selectedItemProperty()
                .addListener(((observable, oldValue, newValue) -> super.getOnAction().handle(
                        new ChoiceChangeEvent<>(
                                this,
                                newValue
                        )
                )));

        this.getChildren().add(this.choiceBox);
    }

    public void select(T item) {
        this.choiceBox.getSelectionModel().select(item);
    }
}
