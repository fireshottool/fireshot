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

package me.fox.ui.settings;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.JMetroStyleClass;
import jfxtras.styles.jmetro.Style;
import lombok.Getter;
import me.fox.components.ConfigManager;
import me.fox.config.Config;
import me.fox.ui.settings.controller.SettingsController;

import java.io.IOException;

/**
 * @author (Ausgefuchster)
 * @version (~ 30.03.2021)
 */

@Getter
public class SettingsWindow implements ConfigManager {

    private final Stage stage = new Stage();
    private final SettingsController settingsController;

    public SettingsWindow() {
        JMetro jMetro = new JMetro(Style.DARK);
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/settings.fxml"));
        try {
            jMetro.setScene(new Scene(fxmlLoader.load()));
            this.stage.setScene(jMetro.getScene());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.settingsController = fxmlLoader.getController();

        this.settingsController.setStage(this.stage);
        this.stage.setTitle("Fireshotapp - Settings");
        this.stage.setOnCloseRequest(this::stageClose);
        this.stage.getScene().getRoot().getStyleClass().add(JMetroStyleClass.BACKGROUND);
        this.stage.setMinWidth(650);
        this.stage.setMinHeight(660);
        this.stage.setHeight(600);
    }

    public void stageClose(WindowEvent event) {
        this.settingsController.cancel(null);
    }

    public void show() {
        this.stage.show();
    }

    @Override
    public void applyConfig(Config config) {
        this.settingsController.applyConfig(config);
    }
}
