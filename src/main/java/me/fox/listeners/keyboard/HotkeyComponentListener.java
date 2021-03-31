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
import me.fox.Fireshotapp;
import me.fox.services.HotkeyService;
import me.fox.ui.components.settings.ext.HotkeyComponent;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author (Ausgefuchster)
 * @version (~ 29.11.2020)
 */
@AllArgsConstructor
public class HotkeyComponentListener extends KeyAdapter {

    private final HotkeyComponent hotkeyComponent;

    @Override
    public void keyPressed(KeyEvent event) {
        if (this.hotkeyComponent.isInside() && Fireshotapp.getInstance().use(HotkeyService.class).isChangingHotkey()) {
            if (event.getKeyCode() == KeyEvent.VK_WINDOWS
                    || event.getKeyCode() == KeyEvent.VK_SHIFT
                    || event.getKeyCode() == KeyEvent.VK_CONTROL
                    || event.getKeyCode() == KeyEvent.VK_ALT)
                return;
            this.hotkeyComponent.updateHotkey(event);
        }
    }
}
