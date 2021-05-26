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

package me.fox.ui.settings.controller.pane;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import lombok.Getter;
import lombok.Setter;
import me.fox.Fireshotapp;
import me.fox.components.ConfigManager;
import me.fox.config.Config;
import me.fox.services.JsonService;
import me.fox.ui.settings.controller.SettingsController;


/**
 * @author (Ausgefuchster)
 * @version (~ 03.04.2021)
 */

@Setter
@Getter
public class ModulePaneController implements ConfigManager {

    private SettingsController settingsController;

    @FXML
    private ListView<String> listView;

    @FXML
    private void initialize() {
        this.listView.setOnMouseClicked(event -> {
            String selectedItem = this.listView.getSelectionModel().getSelectedItem();
            this.settingsController.getSettingsPaneController()
                    .showPanel(selectedItem);
            Fireshotapp.getInstance().use(JsonService.class).getConfig().getSettingsConfig().setLastPane(selectedItem);
        });
    }

    @Override
    public void applyConfig(Config config) {
        String lastPane = config.getSettingsConfig().getLastPane();
        this.listView.getSelectionModel().select(lastPane);
        this.settingsController.getSettingsPaneController().showPanel(lastPane);
    }
}
