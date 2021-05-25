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
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.NonNull;
import me.fox.ui.settings.components.SettingsComponent;
import me.fox.ui.settings.events.ColorChangeEvent;
import me.fox.ui.settings.events.ColorListChangeEvent;

import java.util.List;

/**
 * @author (Ausgefuchster)
 * @version (~ 18.04.2021)
 */

@Getter
public class ColorPickerComponent extends SettingsComponent<ColorChangeEvent> {

    private final ColorPicker colorPicker = new ColorPicker();
    private final ObjectProperty<EventHandler<ColorListChangeEvent>> onChangeAction = new ObjectPropertyBase<EventHandler<ColorListChangeEvent>>() {
        @Override
        public Object getBean() {
            return this;
        }

        @Override
        public String getName() {
            return "onChangeAction";
        }
    };

    public ColorPickerComponent(@NamedArg("text") String text) {
        super(text);

        super.getChildren().add(this.colorPicker);
        this.colorPicker.getCustomColors().addListener(this::onChanged);
        this.colorPicker.setOnAction(event -> super.getOnAction().handle(new ColorChangeEvent(
                this,
                this.colorPicker.getValue()))
        );
    }

    /***************************************************************************
     *                                                                         *
     * Properties                                                              *
     *                                                                         *
     **************************************************************************/
    public final ObjectProperty<EventHandler<ColorListChangeEvent>> onChangeActionProperty() {
        return onChangeAction;
    }

    public final EventHandler<ColorListChangeEvent> getOnChangeAction() {
        return onChangeActionProperty().get();
    }

    private void onChanged(ListChangeListener.Change<? extends Color> change) {
        if (this.getOnChangeAction() != null)
            this.getOnChangeAction().handle(
                    new ColorListChangeEvent(
                            this,
                            change
                    )
            );
    }

    public void setColor(Color color) {
        this.colorPicker.setValue(color);
    }

    public void setColor(@NonNull String color) {
        try {
            this.setColor(Color.web(color));
        } catch (IllegalArgumentException ignored) {
        }
    }

    public void setCustomColors(List<Color> customColors) {
        this.getColorPicker().getCustomColors().clear();
        this.getColorPicker().getCustomColors().addAll(customColors);
    }
}
