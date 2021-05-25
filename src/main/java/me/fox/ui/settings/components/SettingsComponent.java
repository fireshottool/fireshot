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

import javax.swing.*;
import java.awt.*;

/**
 * @author (Ausgefuchster)
 * @version (~ 19.11.2020)
 */


public abstract class SettingsComponent extends JPanel {

    public SettingsComponent(Point location) {
        this.setSize(400, 70);
        this.setLocation(location);
        this.setVisible(true);
        this.setLayout(null);
    }
}
