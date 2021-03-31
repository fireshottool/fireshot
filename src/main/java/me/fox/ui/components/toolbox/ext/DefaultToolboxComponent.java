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

package me.fox.ui.components.toolbox.ext;

import me.fox.ui.components.toolbox.ToolboxComponent;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * @author (Ausgefuchster)
 * @version (~ 25.10.2020)
 */

public class DefaultToolboxComponent extends ToolboxComponent {

    public DefaultToolboxComponent(BufferedImage icon, ActionListener actionListener) {
        super(icon);
        this.setSize(32, 32);
        this.addActionListener(actionListener);
    }

    public DefaultToolboxComponent(ActionListener actionListener) {
        super(null);
        this.setSize(32, 32);
        this.addActionListener(actionListener);
    }

    public DefaultToolboxComponent(BufferedImage icon, ActionListener actionListener, boolean selectable, boolean doubleSelect) {
        super(icon, doubleSelect);
        this.setSize(32, 32);
        this.addActionListener(actionListener);
        if (selectable) {
            this.addActionListener(this::select);
        }
    }


    public DefaultToolboxComponent(ActionListener actionListener, boolean selectable, boolean doubleSelect) {
        super(null, doubleSelect);
        this.setSize(32, 32);
        this.addActionListener(actionListener);
        if (selectable) {
            this.addActionListener(this::select);
        }
    }
}
