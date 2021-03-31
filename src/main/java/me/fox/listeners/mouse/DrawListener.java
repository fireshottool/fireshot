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

import me.fox.Fireshotapp;
import me.fox.services.DrawService;
import me.fox.services.HotkeyService;
import me.fox.ui.components.draw.impl.Line;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author (Ausgefuchster)
 * @version (~ 23.10.2020)
 */

public class DrawListener extends MouseAdapter {

    private final DrawService drawService;
    private boolean second;

    /**
     * Constructor for {@link DrawListener}
     *
     * @param drawService to set {@link DrawListener#drawService}
     */
    public DrawListener(DrawService drawService) {
        this.drawService = drawService;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        if (this.drawService.isDraw()) {
            if (this.drawService.isCircle()) {
                this.drawService.addCircle(event.getPoint());
                return;
            }
            if (this.drawService.isRectangle()) {
                this.drawService.addRectangle(event.getPoint());
                return;
            }
            if (Fireshotapp.getInstance().use(HotkeyService.class).getPressedKeys().contains(16)) {
                this.drawService.addPoint(event.getPoint());
                return;
            }
            if (this.drawService.isLine()) {
                if (!this.second) {
                    this.drawService.addLine();
                    this.drawService.addPoint(event.getPoint());
                    this.second = true;
                    return;
                }
                this.drawService.addPoint(event.getPoint());
                this.second = false;
                return;
            }
            this.drawService.addLine();
            this.drawService.addPoint(event.getPoint());
        }
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        if (this.drawService.isDraw()) {
            if (this.drawService.isCircle()) {
                this.drawService.resizeCurrentCircle(event.getPoint());
                return;
            }
            if (this.drawService.isRectangle()) {
                this.drawService.resizeRectangle(event.getPoint());
                return;
            }
            if (this.drawService.isLine() || Fireshotapp.getInstance().use(HotkeyService.class).getPressedKeys().contains(16)) {
                Line line = (Line) this.drawService.getDrawings().peek();
                if (line.getPoints().size() == 1) {
                    line.getPoints().add(event.getPoint());
                    this.second = false;
                } else {
                    line.getPoints().set(line.getPoints().size() - 1, event.getPoint());
                }
                return;
            }

            this.drawService.addPoint(event.getPoint());
        }
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        if (this.drawService.isDraw() &&
                !Fireshotapp.getInstance().getScreenshotFrame().getCursor().getName().equals("drawing")) {
            Fireshotapp.getInstance().getScreenshotFrame().setCursor(this.drawService.getDrawCursor());
        }
    }
}
