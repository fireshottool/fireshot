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

package me.fox.ui.frames;

import lombok.Getter;
import me.fox.ui.panels.ColorPicker;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * @author (Ausgefuchster)
 * @version (~ 22.11.2020)
 */

@Getter
public class ColorPickerDialog extends JDialog {

    private final ColorPicker colorPicker;
    private final JButton ok, cancel;

    public ColorPickerDialog(JFrame parent, ChangeListener changeListener) {
        super(parent, "Color picker");
        this.colorPicker = new ColorPicker(changeListener);

        this.ok = new JButton("Ok");
        this.cancel = new JButton("Cancel");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        this.setup();
    }

    private void setup() {
        this.setVisible(false);
        this.setLayout(null);
        this.setSize(255, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(this);
        this.setAlwaysOnTop(true);
        this.add(this.colorPicker);
        this.colorPicker.setLocation(5, 5);

        this.add(this.ok);
        this.ok.setLocation(40, 220);
        this.ok.setSize(80, 35);

        this.add(this.cancel);
        this.cancel.setLocation(140, 220);
        this.cancel.setSize(80, 35);
    }

    public void setColor(Color color) {
        this.colorPicker.setColor(color);
    }
}
