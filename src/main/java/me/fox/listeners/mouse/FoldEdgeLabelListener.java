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

import lombok.AllArgsConstructor;
import me.fox.ui.components.settings.FoldEdgeLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author (Ausgefuchster)
 * @version (~ 21.11.2020)
 */
@AllArgsConstructor
public class FoldEdgeLabelListener extends MouseAdapter {

    private final FoldEdgeLabel foldEdgeLabel;

    @Override
    public void mouseClicked(MouseEvent event) {
        if (foldEdgeLabel.makeEdge().contains(event.getPoint())) {
            this.foldEdgeLabel.switchColorDisplay();
        }
    }
}
