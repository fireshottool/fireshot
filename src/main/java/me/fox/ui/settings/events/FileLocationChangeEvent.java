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

package me.fox.ui.settings.events;

import javafx.event.Event;
import lombok.Getter;

import java.io.File;

/**
 * @author (Ausgefuchster)
 * @version (~ 05.05.2021)
 */

@Getter
public class FileLocationChangeEvent extends Event {

    private final File file;

    public FileLocationChangeEvent(Object source, File file) {
        super(source, null, null);
        this.file = file;
    }
}
