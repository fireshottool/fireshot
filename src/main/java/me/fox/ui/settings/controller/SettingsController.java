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

package me.fox.ui.settings.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import me.fox.Fireshotapp;
import me.fox.components.ConfigManager;
import me.fox.config.Config;
import me.fox.services.JsonService;
import me.fox.ui.settings.controller.page.ext.AppearancePageController;
import me.fox.ui.settings.controller.page.ext.DrawPageController;
import me.fox.ui.settings.controller.page.ext.HotkeyPageController;
import me.fox.ui.settings.controller.page.ext.ScreenshotPageController;
import me.fox.ui.settings.controller.pane.ModulePaneController;
import me.fox.ui.settings.controller.pane.SettingsPaneController;

import java.io.IOException;

/**
 * @author (Ausgefuchster)
 * @version (~ 05.04.2021)
 */

@Getter
@Setter
public class SettingsController implements ConfigManager {

    private Stage stage;

    private ModulePaneController modulePaneController;
    private SettingsPaneController settingsPaneController = new SettingsPaneController();

    @FXML
    private VBox container, modulePane, settingsPane;
    @FXML
    private SplitPane splitPane;


    @FXML
    private void initialize() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/modulePane.fxml"));
        this.splitPane.getItems().add(loader.load());
        this.modulePaneController = loader.getController();
        this.modulePaneController.setSettingsController(this);

        loader = new FXMLLoader(this.getClass().getResource("/fxml/settingsPane.fxml"));
        loader.setControllerFactory(param -> {
            if (param == SettingsPaneController.class)
                return settingsPaneController;
            if (param == DrawPageController.class)
                return settingsPaneController.getDrawPageController();
            if (param == ScreenshotPageController.class)
                return settingsPaneController.getScreenshotPageController();
            if (param == HotkeyPageController.class)
                return settingsPaneController.getHotkeyPageController();
            if (param == AppearancePageController.class)
                return settingsPaneController.getAppearancePageController();
            return null;
        });
        this.splitPane.getItems().add(loader.load());
        this.settingsPaneController.setSettingsController(this);

    }


    @FXML
    public void save(ActionEvent event) {
        this.stage.hide();
        JsonService jsonService = Fireshotapp.getInstance().use(JsonService.class);
        jsonService.saveAndApply();
    }

    @FXML
    public void cancel(ActionEvent event) {
        this.stage.hide();
        JsonService jsonService = Fireshotapp.getInstance().use(JsonService.class);
        jsonService.read(null);
    }

    @FXML
    public void apply(ActionEvent event) {
        JsonService jsonService = Fireshotapp.getInstance().use(JsonService.class);
        jsonService.saveAndApply();
    }

    @Override
    public void applyConfig(Config config) {
        this.settingsPaneController.applyConfig(config);
        this.modulePaneController.applyConfig(config);
    }
}
