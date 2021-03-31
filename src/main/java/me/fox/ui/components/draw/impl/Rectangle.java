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
import lombok.Setter;
import me.fox.ui.components.draw.Drawable;

import java.awt.*;

/**
 * @author (Ausgefuchster)
 * @version (~ 08.11.2020)
 */

@Getter
@Setter
public class Rectangle extends java.awt.Rectangle implements Drawable {

    private final Color color;
    private final Stroke stroke;
    private final boolean fill;

    /**
     * Constructor for {@link Rectangle}
     *
     * @param x           to initialize {@link Rectangle#x}
     * @param y           to initialize {@link Rectangle#y}
     * @param color       to initialize {@link Rectangle#color}
     * @param strokeWidth to initialize {@link Rectangle#stroke}
     * @param fill        to initialize {@link Rectangle#fill}
     */
    public Rectangle(int x, int y, Color color, float strokeWidth, boolean fill) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.fill = fill;
        this.stroke = new BasicStroke(strokeWidth);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(this.color);
        g2d.setStroke(this.stroke);
        int x = this.x;
        int y = this.y;
        int width = Math.abs(this.width);
        int height = Math.abs(this.height);

        if (width == 0 || height == 0)
            return;
        if (this.width < 0) {
            x -= width;
        }
        if (this.height < 0) {
            y -= height;
        }

        if (this.fill) {
            g2d.fillRect(x, y, width, height);
            return;
        }
        g2d.drawRect(x, y, width, height);
    }
}
