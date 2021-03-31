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

package me.fox.ui.panels;

import lombok.Getter;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * @author (Ausgefuchster)
 * @version (~ 11.11.2020)
 */


@Getter
public class ColorPicker extends JPanel {

    private final JColorChooser colorChooser = new JColorChooser();

    public ColorPicker(ChangeListener changeListener) {
        this.setVisible(true);
        this.setLayout(null);
        this.setSize(230, 210);
        this.colorChooser.setColor(Color.red);

        this.colorChooser.getSelectionModel().addChangeListener(changeListener);

        this.setupPanels();
    }

    private void setupPanels() {
        Component hsvPanel = this.colorChooser.getChooserPanels()[1].getComponents()[4];
        Component sideBar = this.colorChooser.getChooserPanels()[1].getComponents()[3];

        hsvPanel.setSize(200, 200);
        hsvPanel.setLocation(0, 0);
        sideBar.setSize(30, 200);
        sideBar.setLocation(200, 0);

        this.add(hsvPanel);
        this.add(sideBar);
    }

    public Color getColor() {
        return this.colorChooser.getColor();
    }

    public void setColor(Color color) {
        this.colorChooser.setColor(color);
    }
}
