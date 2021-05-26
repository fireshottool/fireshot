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

package me.fox.ui.settings.controller.page.ext;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import me.fox.config.Config;
import me.fox.ui.settings.components.ext.HotkeyComponent;
import me.fox.ui.settings.controller.page.PageController;

import java.util.stream.Collectors;


/**
 * @author (Ausgefuchster)
 * @version (~ 13.05.2021)
 */

public class HotkeyPageController extends PageController {

    @FXML
    private VBox container;

    @Override
    public void applyConfig(Config config) {
        if (this.container.getChildren().size() != 0) return;
        this.container.setSpacing(40);
        this.container.getChildren().addAll(
                config.getHotkeyConfig().getHotkeys()
                        .stream()
                        .map(HotkeyComponent::new).collect(Collectors.toList())
        );
    }
}
