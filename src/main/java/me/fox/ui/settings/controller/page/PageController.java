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

package me.fox.ui.settings.controller.page;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import lombok.Getter;
import me.fox.Fireshotapp;
import me.fox.components.ConfigManager;
import me.fox.services.JsonService;
import me.fox.ui.settings.events.ColorListChangeEvent;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author (Ausgefuchster)
 * @version (~ 14.05.2021)
 */


@Getter
public abstract class PageController extends VBox implements ConfigManager {

    @FXML
    private void customColorsChanged(ColorListChangeEvent event) {
        System.out.println(event.getSource());
        JsonService jsonService = Fireshotapp.getInstance().use(JsonService.class);
        List<String> colorList = event.getChange().getList().stream().map(Color::toString).collect(Collectors.toList());
        jsonService.getConfig().getSettingsConfig().getCustomColors().clear();
        jsonService.getConfig().getSettingsConfig().getCustomColors()
                .addAll(colorList);
    }
}
