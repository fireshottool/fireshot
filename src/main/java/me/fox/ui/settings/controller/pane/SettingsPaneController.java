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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
import me.fox.components.ConfigManager;
import me.fox.config.Config;
import me.fox.ui.settings.controller.SettingsController;
import me.fox.ui.settings.controller.page.PageController;
import me.fox.ui.settings.controller.page.ext.AppearancePageController;
import me.fox.ui.settings.controller.page.ext.DrawPageController;
import me.fox.ui.settings.controller.page.ext.HotkeyPageController;
import me.fox.ui.settings.controller.page.ext.ScreenshotPageController;


/**
 * @author (Ausgefuchster)
 * @version (~ 03.04.2021)
 */


@Setter
@Getter
public class SettingsPaneController implements ConfigManager {

    private final PageController drawPageController = new DrawPageController();
    private final PageController hotkeyPageController = new HotkeyPageController();
    private final PageController screenshotPageController = new ScreenshotPageController();
    private final PageController appearancePageController = new AppearancePageController();
    private SettingsController settingsController;
    @FXML
    private VBox drawPage, screenshotPage;
    @FXML
    private ScrollPane appearancePage, hotkeyPage;

    public void showPanel(String panelName) {
        switch (panelName) {
            case "Appearance":
                this.settingsController.getSettingsPaneController().showAppearancePage();
                break;
            case "Draw":
                this.settingsController.getSettingsPaneController().showDrawPage();
                break;
            case "Screenshot":
                this.settingsController.getSettingsPaneController().showScreenshotPage();
                break;
            case "Hotkeys":
                this.settingsController.getSettingsPaneController().showHotkeyPage();
        }
    }

    public void showDrawPage() {
        this.drawPage.setVisible(true);
        this.drawPage.setManaged(true);

        this.hideScreenshotPage();
        this.hideAppearancePage();
        this.hideHotkeyPage();
    }

    public void hideDrawPage() {
        this.drawPage.setVisible(false);
        this.drawPage.setManaged(false);
    }

    public void showScreenshotPage() {
        this.screenshotPage.setVisible(true);
        this.screenshotPage.setManaged(true);

        this.hideDrawPage();
        this.hideAppearancePage();
        this.hideHotkeyPage();
    }

    public void hideScreenshotPage() {
        this.screenshotPage.setVisible(false);
        this.screenshotPage.setManaged(false);
    }

    public void showAppearancePage() {
        this.appearancePage.setVisible(true);
        this.appearancePage.setManaged(true);

        this.hideDrawPage();
        this.hideScreenshotPage();
        this.hideHotkeyPage();
    }

    public void hideAppearancePage() {
        this.appearancePage.setVisible(false);
        this.appearancePage.setManaged(false);
    }

    public void showHotkeyPage() {
        this.hotkeyPage.setVisible(true);
        this.hotkeyPage.setManaged(true);

        this.hideDrawPage();
        this.hideScreenshotPage();
        this.hideAppearancePage();
    }

    public void hideHotkeyPage() {
        this.hotkeyPage.setVisible(false);
        this.hotkeyPage.setManaged(false);
    }

    @Override
    public void applyConfig(Config config) {
        this.appearancePageController.applyConfig(config);
        this.screenshotPageController.applyConfig(config);
        this.hotkeyPageController.applyConfig(config);
        this.drawPageController.applyConfig(config);
    }
}
