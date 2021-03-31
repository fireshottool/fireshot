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

import me.fox.ui.components.draw.Drawable;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author (Ausgefuchster)
 * @version (~ 10.11.2020)
 */

public class PaintedToolBoxComponent extends DefaultToolboxComponent {

    private final Drawable drawable;

    public PaintedToolBoxComponent(ActionListener actionListener, Drawable drawable) {
        super(null, actionListener);
        this.drawable = drawable;
    }

    public PaintedToolBoxComponent(ActionListener actionListener,
                                   boolean selectable, boolean doubleSelect, Drawable drawable) {
        super(null, actionListener, selectable, doubleSelect);
        this.drawable = drawable;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.drawable.draw((Graphics2D) g);
    }
}
