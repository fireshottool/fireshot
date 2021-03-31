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

package me.fox.listeners.keyboard;

import lombok.AllArgsConstructor;
import me.fox.services.HotkeyService;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author (Ausgefuchster)
 * @version (~ 22.10.2020)
 */
@AllArgsConstructor
public class HotkeyListener extends KeyAdapter {

    private final HotkeyService hotkeyService;

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == 255) return;
        this.hotkeyService.registerKey(event.getKeyCode());
        if (this.hotkeyService.isChangingHotkey()) return;
        this.hotkeyService.invokeIfPresent(event);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (this.hotkeyService.isChangingHotkey()) return;
        if (event.getKeyCode() == 255) return;
        this.hotkeyService.unregisterKey(event.getKeyCode());
    }
}
