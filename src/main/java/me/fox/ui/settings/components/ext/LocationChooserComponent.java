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

package me.fox.ui.settings.components.ext;

import javafx.beans.NamedArg;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.stage.DirectoryChooser;
import lombok.Getter;
import me.fox.ui.settings.components.SettingsComponent;
import me.fox.ui.settings.events.FileLocationChangeEvent;

import java.io.File;


/**
 * @author (Ausgefuchster)
 * @version (~ 09.04.2021)
 */

@Getter
public class LocationChooserComponent extends SettingsComponent<FileLocationChangeEvent> {

    private final DirectoryChooser directoryChooser = new DirectoryChooser();
    private final String text;

    public LocationChooserComponent(@NamedArg("text") String text) {
        super(text);
        Button button = new Button("Choose Location");
        button.setMinWidth(105);

        this.text = text;

        this.getChildren().add(button);
        this.setSpacing(40);
        button.setOnAction(event -> {
            File file = this.directoryChooser.showDialog(null);
            if (file == null) return;
            String filePath = file.toString();
            super.getLabel().setText(this.text + filePath);
            super.getLabel().setTooltip(new Tooltip(super.getLabel().getText()));
            super.getOnAction().handle(
                    new FileLocationChangeEvent(this, file)
            );
        });
    }

    public void setInitialDirectory(String path) {
        this.directoryChooser.setInitialDirectory(new File(path));
        super.getLabel().setText(this.text + path);
        super.getLabel().setTooltip(new Tooltip(super.getLabel().getText()));
    }
}
