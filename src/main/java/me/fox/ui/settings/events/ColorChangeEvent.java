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

package me.fox.ui.settings.events;

import javafx.event.Event;
import javafx.scene.paint.Color;
import lombok.Getter;

/**
 * @author (Ausgefuchster)
 * @version (~ 05.05.2021)
 */

@Getter
public class ColorChangeEvent extends Event {

    private final Color newColor;


    public ColorChangeEvent(Object source, Color newColor) {
        super(source, null, null);
        this.newColor = newColor;
    }

    public String colorAsHex() {
        return "#" + newColor.toString().substring(2, 8);
    }
}
