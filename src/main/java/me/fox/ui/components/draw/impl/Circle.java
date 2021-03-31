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

package me.fox.ui.components.draw.impl;

import lombok.Getter;
import me.fox.ui.components.draw.Drawable;

import java.awt.*;

/**
 * @author (Ausgefuchster)
 * @version (~ 06.11.2020)
 */

@Getter
public class Circle implements Drawable {

    private final Color color;
    private final Stroke stroke;
    private final int x, y;
    private final boolean fill;

    private int width;
    private int height;

    /**
     * Constructor for {@link Circle}
     *
     * @param x           to initialize {@link Circle#x}
     * @param y           to initialize {@link Circle#y}
     * @param color       to initialize {@link Circle#color}
     * @param strokeWidth to initialize {@link Circle#stroke}
     * @param fill        to initialize {@link Circle#fill}
     */
    public Circle(int x, int y, Color color, float strokeWidth, boolean fill) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.fill = fill;
        this.stroke = new BasicStroke(strokeWidth);
    }

    /**
     * Sets the width and height of the {@link Circle}
     *
     * @param width  to set width
     * @param height to set height
     */
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(this.color);
        g2d.setStroke(this.stroke);
        if (this.width != 0 && this.height != 0) {
            if (this.fill) {
                g2d.fillOval(this.x - (this.width / 2), this.y - (this.height / 2), this.width, this.height);
                return;
            }
            g2d.drawOval(this.x - (this.width / 2), this.y - (this.height / 2), this.width, this.height);
        }
    }
}
