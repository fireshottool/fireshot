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

package me.fox.listeners.mouse;

import me.fox.ui.panels.toolbox.Toolbox;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author (Ausgefuchster)
 * @version (~ 24.10.2020)
 */

public class ToolboxListener extends MouseAdapter {

    private final Toolbox toolbox;
    private int distanceX, distanceY;

    /**
     * Constructor for {@link ToolboxListener}
     *
     * @param toolbox to set {@link ToolboxListener#toolbox}
     */
    public ToolboxListener(Toolbox toolbox) {
        this.toolbox = toolbox;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        this.distanceX = event.getXOnScreen() - this.toolbox.getX();
        this.distanceY = event.getYOnScreen() - this.toolbox.getY();
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        this.toolbox.setLocation(event.getXOnScreen() - this.distanceX, event.getYOnScreen() - this.distanceY);
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        this.toolbox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
