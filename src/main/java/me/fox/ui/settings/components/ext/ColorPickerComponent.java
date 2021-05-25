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

import lombok.Getter;
import me.fox.Fireshotapp;
import me.fox.services.DrawService;
import me.fox.ui.components.settings.FoldEdgeLabel;
import me.fox.ui.components.settings.SettingsComponent;
import me.fox.ui.frames.ColorPickerDialog;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author (Ausgefuchster)
 * @version (~ 21.11.2020)
 */


@Getter
public class ColorPickerComponent extends SettingsComponent {

    private final FoldEdgeLabel foldEdgeLabel;
    private final JLabel label;
    private final JButton button;
    private final ColorPickerDialog colorPickerDialog;

    public ColorPickerComponent(String label, Point location, ActionListener colorChangedListener, JFrame frame) {
        super(location);

        this.foldEdgeLabel = new FoldEdgeLabel(Color.red.toString());
        this.label = new JLabel(label);
        this.button = new JButton("Choose color");
        this.colorPickerDialog = new ColorPickerDialog(frame, this::colorChanged);

        this.getColorPickerDialog().getOk().addActionListener(colorChangedListener);
        this.getColorPickerDialog().getCancel().addActionListener(this::cancelPerformed);

        this.setupLabel();
        this.setupButton();
    }

    private void setupLabel() {
        this.label.setLocation(5, 10);
        this.label.setSize(100, 60);
        this.add(foldEdgeLabel);
        this.foldEdgeLabel.setLocation(80, 10);
        this.foldEdgeLabel.setSize(100, 60);
        this.foldEdgeLabel.setColor(Color.red);
        this.add(label);
    }

    private void setupButton() {
        this.button.setLocation(200, 30);
        this.button.setSize(100, 30);
        this.add(button);
        this.button.addActionListener(this::actionPerformed);
    }

    private void actionPerformed(ActionEvent event) {
        this.colorPickerDialog.setVisible(true);
    }

    private void cancelPerformed(ActionEvent event) {
        this.colorPickerDialog.setVisible(false);
        this.foldEdgeLabel.setColor(Fireshotapp.getInstance().use(DrawService.class).getDrawColor());
    }

    private void colorChanged(ChangeEvent event) {
        this.setColor(this.colorPickerDialog.getColorPicker().getColor());
    }

    public Color getColor() {
        return this.foldEdgeLabel.getColor();
    }

    public void setColor(Color color) {
        this.foldEdgeLabel.setColor(color);
        this.colorPickerDialog.setColor(color);
    }
}
