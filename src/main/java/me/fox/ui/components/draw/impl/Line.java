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
import me.fox.Fireshotapp;
import me.fox.services.DrawService;
import me.fox.ui.components.draw.Drawable;

import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author (Ausgefuchster)
 * @version (~ 23.10.2020)
 */

@Getter
public class Line implements Drawable {

    private final List<Point> points = new CopyOnWriteArrayList<>();

    private final Color color = Fireshotapp.getInstance().use(DrawService.class).getDrawColor();
    private final Stroke stroke = new BasicStroke(Fireshotapp.getInstance().use(DrawService.class).getCurrentStrokeWidth(),
            BasicStroke.CAP_ROUND,
            BasicStroke.JOIN_ROUND);

    /**
     * Adds a {@link Point} to {@link Line#points}.
     *
     * @param point to add to {@link Line#points}
     */
    public void addPoint(Point point) {
        this.points.add(point);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setStroke(this.getStroke());

        for (int i = 0; i < this.getPoints().size(); i++) {
            List<Point> points = this.getPoints();
            g2d.setColor(this.getColor());

            if (i + 1 != points.size()) {
                g2d.drawLine(points.get(i).x, points.get(i).y, points.get(i + 1).x, points.get(i + 1).y);
            } else {
                g2d.drawLine(points.get(i).x, points.get(i).y, points.get(i).x, points.get(i).y);
            }
        }
    }
}
