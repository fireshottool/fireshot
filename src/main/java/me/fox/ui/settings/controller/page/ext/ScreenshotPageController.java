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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lombok.Getter;
import me.fox.Fireshotapp;
import me.fox.config.Config;
import me.fox.config.FileConfig;
import me.fox.config.ScreenshotConfig;
import me.fox.services.JsonService;
import me.fox.ui.settings.components.ext.ChoiceBoxComponent;
import me.fox.ui.settings.components.ext.LocationChooserComponent;
import me.fox.ui.settings.components.ext.ToggleSwitchComponent;
import me.fox.ui.settings.controller.page.PageController;
import me.fox.ui.settings.events.ChoiceChangeEvent;
import me.fox.ui.settings.events.FileLocationChangeEvent;

/**
 * @author (Ausgefuchster)
 * @version (~ 05.04.2021)
 */

@Getter
public class ScreenshotPageController extends PageController {

    @FXML
    private LocationChooserComponent locationChooser;
    @FXML
    private ToggleSwitchComponent uploadCheckBox, saveCheckBox;
    @FXML
    private ChoiceBoxComponent<String> imageTypeChoice;

    @FXML
    private void initialize() {
        this.imageTypeChoice.getChoiceBox().getItems().addAll("png", "jpg");
    }

    @FXML
    private void imageLocationChanged(FileLocationChangeEvent event) {
        JsonService jsonService = Fireshotapp.getInstance().use(JsonService.class);
        jsonService.getConfig().getFileConfig().setImageLocation(event.getFile().getPath());
    }

    @FXML
    private void uploadCheckBoxChanged(ActionEvent event) {
        JsonService jsonService = Fireshotapp.getInstance().use(JsonService.class);
        jsonService.getConfig().getScreenshotConfig().setUpload(this.uploadCheckBox.isSelected());
    }

    @FXML
    private void saveCheckBoxChanged(ActionEvent event) {
        JsonService jsonService = Fireshotapp.getInstance().use(JsonService.class);
        jsonService.getConfig().getScreenshotConfig().setLocalSave(this.saveCheckBox.isSelected());
    }

    @FXML
    private void imageTypeChanged(ChoiceChangeEvent<String> event) {
        JsonService jsonService = Fireshotapp.getInstance().use(JsonService.class);
        jsonService.getConfig().getFileConfig().setImageType(event.getChoice());
    }

    @Override
    public void applyConfig(Config config) {
        FileConfig fileConfig = config.getFileConfig();
        this.locationChooser.setInitialDirectory(fileConfig.getImageLocation());
        this.imageTypeChoice.select(fileConfig.getImageType());

        ScreenshotConfig screenshotConfig = config.getScreenshotConfig();
        this.uploadCheckBox.setSelected(screenshotConfig.isUpload());
        this.saveCheckBox.setSelected(screenshotConfig.isLocalSave());
    }
}