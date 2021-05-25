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

package me.fox.ui.settings.components;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.Setter;

/**
 * @author (Ausgefuchster)
 * @version (~ 10.04.2021)
 */

@Getter
@Setter
public abstract class SettingsComponent<T extends Event> extends HBox {

    private final Label label;
    private ObjectProperty<EventHandler<T>> onAction = new ObjectPropertyBase<EventHandler<T>>() {
        @Override
        public Object getBean() {
            return this;
        }

        @Override
        public String getName() {
            return "onAction";
        }
    };

    public SettingsComponent(String text) {
        this.label = new Label(text);
        this.label.setPrefWidth(200);
        this.setSpacing(40);

        this.getChildren().add(this.label);
    }

    /***************************************************************************
     *                                                                         *
     * Properties                                                              *
     *                                                                         *
     **************************************************************************/
    public final ObjectProperty<EventHandler<T>> onActionProperty() {
        return onAction;
    }

    public final EventHandler<T> getOnAction() {
        return onActionProperty().get();
    }
}
