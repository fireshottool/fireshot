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

package me.fox.ui.components;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

/**
 * @author (Ausgefuchster)
 * @version (~ 21.10.2020)
 */

@Getter
@Setter
public class ScalePoint extends Rectangle {

    private final int direction;

    /**
     * Constructor for {@link ScalePoint}
     *
     * @param direction that the {@link ScalePoint} represents
     */
    public ScalePoint(int direction) {
        this.direction = direction;
        this.setSize(10, 10);
    }

    public void updateLocation(Rectangle rect) {
        switch (this.direction) {
            case Cursor.SW_RESIZE_CURSOR:
                setLocation(rect.x - 5, rect.y + rect.height - 5);
                break;
            case Cursor.SE_RESIZE_CURSOR:
                setLocation(rect.x + rect.width - 5, rect.y + rect.height - 5);
                break;
            case Cursor.NW_RESIZE_CURSOR:
                setLocation(rect.x - 5, rect.y - 5);
                break;
            case Cursor.NE_RESIZE_CURSOR:
                setLocation(rect.x + rect.width - 5, rect.y - 5);
                break;
            case Cursor.N_RESIZE_CURSOR:
                setLocation(rect.x + rect.width / 2 - 5, rect.y - 5);
                break;
            case Cursor.S_RESIZE_CURSOR:
                setLocation(rect.x + rect.width / 2 - 5, rect.y + rect.height - 5);
                break;
            case Cursor.W_RESIZE_CURSOR:
                setLocation(rect.x - 5, rect.y + rect.height / 2 - 5);
                break;
            case Cursor.E_RESIZE_CURSOR:
                setLocation(rect.x + rect.width - 5, rect.y + rect.height / 2 - 5);
                break;
        }
    }
}
