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
import me.fox.Fireshotapp;
import me.fox.services.HotkeyService;
import me.fox.ui.components.settings.ext.HotkeyComponent;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author (Ausgefuchster)
 * @version (~ 29.11.2020)
 */
@AllArgsConstructor
public class HotkeyComponentListener extends MouseAdapter {

    private final HotkeyComponent hotkeyComponent;

    @Override
    public void mouseClicked(MouseEvent event) {
        if (this.hotkeyComponent.isInside()) {
            this.hotkeyComponent.getHotkeyLabel().setForeground(Color.GRAY);
            Fireshotapp.getInstance().use(HotkeyService.class).setChangingHotkey(true);
        }
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        this.hotkeyComponent.setInside(true);
    }

    @Override
    public void mouseExited(MouseEvent event) {
        this.hotkeyComponent.getHotkeyLabel().setForeground(Color.BLACK);
        this.hotkeyComponent.setInside(false);
        Fireshotapp.getInstance().use(HotkeyService.class).setChangingHotkey(false);
    }
}
