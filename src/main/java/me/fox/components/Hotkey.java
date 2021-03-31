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

package me.fox.components;

import lombok.Getter;
import lombok.Setter;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

/**
 * @author (Ausgefuchster)
 * @version (~ 22.10.2020)
 */

@Getter
@Setter
public class Hotkey {

    private String name, displayName;
    private int hotkey;
    private Integer[] requiredKeys;

    public Hotkey(String name, String displayName, int hotkey, Integer... requiredKeys) {
        this.name = name;
        this.displayName = displayName;
        this.hotkey = hotkey;
        this.requiredKeys = requiredKeys;
    }

    /**
     * Checks if the {@link Hotkey} fits and can be invoked.
     *
     * @param event to get the pressed keyCode
     * @param keys  the keys to check
     * @return whether the {@link Hotkey} can be invoked
     */
    public boolean canInvoke(KeyEvent event, List<Integer> keys) {
        if (this.hotkey != event.getKeyCode()) return false;
        if (this.requiredKeys == null) return true;
        return this.requiredKeysPressed(keys);
    }

    /**
     * Checks whether the {@link Hotkey#requiredKeys} are pressed.
     *
     * @param keys the keys to check
     * @return whether the {@link Hotkey#requiredKeys} are pressed
     */
    private boolean requiredKeysPressed(List<Integer> keys) {
        return keys.containsAll(Arrays.asList(this.requiredKeys));
    }
}
