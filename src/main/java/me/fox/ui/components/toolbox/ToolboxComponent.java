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

package me.fox.ui.components.toolbox;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

/**
 * @author (Ausgefuchster)
 * @version (~ 24.10.2020)
 */

@Getter
@Setter
public abstract class ToolboxComponent extends JButton {

    private boolean doubleSelect;
    private int stage;

    public ToolboxComponent(BufferedImage icon) {
        this.setLayout(null);
        this.setOpaque(true);
        this.setBackground(Color.decode("#ebebeb"));
        if (icon != null) {
            this.setIcon(new ImageIcon(icon.getScaledInstance(26, 26, Image.SCALE_SMOOTH)));
        }
    }

    public ToolboxComponent(BufferedImage icon, boolean doubleSelect) {
        this.setLayout(null);
        this.setOpaque(true);
        this.setBackground(Color.decode("#ebebeb"));
        if (icon != null) {
            this.setIcon(new ImageIcon(icon.getScaledInstance(26, 26, Image.SCALE_SMOOTH)));
        }
        this.doubleSelect = doubleSelect;
    }

    public void select(ActionEvent event) {
        switch (this.stage) {
            case 2:
                this.setBorderPainted(true);
                this.setBackground(Color.decode("#ebebeb"));
                this.stage = 0;
                break;
            case 1:
                this.setBorderPainted(true);
                this.setBackground(Color.decode("#000000"));
                this.stage = 2;
                break;
            case 0:
                this.setBorderPainted(false);
                this.setBackground(Color.decode("#9c9c9c"));
                if (this.doubleSelect) {
                    this.stage = 1;
                } else {
                    this.stage = 2;
                }
                break;
        }
    }

    public void unselect() {
        this.setBorderPainted(true);
        this.setBackground(Color.decode("#ebebeb"));
        this.stage = 0;
    }
}
