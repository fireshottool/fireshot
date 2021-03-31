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

package me.fox.config;

import lombok.Data;

/**
 * @author (Ausgefuchster)
 * @version (~ 15.11.2020)
 */

@Data
public class ScreenshotConfig {

    private String zoomMiddleRectColor = "#ffffff";
    private String zoomBorderColor = "#ffffff";
    private String zoomCrossColor = "#0019ff";
    private String zoomGridColor = "#000000";
    private String dimColor = "#000000";
    private boolean zoomMiddleRect = true;
    private boolean askForUpload = true;
    private boolean zoomCross = true;
    private boolean localSave = true;
    private boolean zoomGrid = true;
    private boolean upload = false;
    private boolean zoom = true;
}
