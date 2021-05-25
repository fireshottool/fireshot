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

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import me.fox.components.Hotkey;
import me.fox.ui.settings.components.SettingsComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author (Ausgefuchster)
 * @version (~ 05.05.2021)
 */

public class HotkeyComponent extends SettingsComponent<ActionEvent> {

    private final TextField textField;
    private final Hotkey hotkey;

    public HotkeyComponent(Hotkey hotkey) {
        super(hotkey.getDisplayName());

        this.hotkey = hotkey;

        this.textField = new TextField();
        this.textField.setOnKeyReleased(this::keyReleased);
        this.textField.setOnKeyPressed(this::keyPressed);
        this.textField.setPrefWidth(250);
        this.textField.setTextFormatter(new TextFormatter<>((change) -> {
            change.setText(change.getText().toUpperCase());
            return change;
        }));

        this.setText();

        this.getChildren().add(this.textField);
    }

    private void keyPressed(KeyEvent event) {
        if (event.isShortcutDown()) return;
        this.textField.setText("");
    }

    private void keyReleased(KeyEvent event) {
        if (this.isUnwanted(event.getCode()) || !event.isShortcutDown()) return;
        StringBuilder stringBuilder = new StringBuilder();
        if (event.isAltDown())
            stringBuilder.append("Alt + ");
        if (event.isControlDown())
            stringBuilder.append("Ctrl + ");

        if (event.isShiftDown())
            stringBuilder.append("Shift + ");

        stringBuilder.append(event.getText().toUpperCase());
        this.textField.setText(stringBuilder.toString());
        this.changeHotkey(stringBuilder.toString(), event.getCode().impl_getCode());
    }

    private void changeHotkey(String hotkeyText, int keyCode) {
        List<Integer> requiredKeys = new ArrayList<>();
        if (hotkeyText.contains("Alt"))
            requiredKeys.add(18);
        if (hotkeyText.contains("Ctrl"))
            requiredKeys.add(17);
        if (hotkeyText.contains("Shift"))
            requiredKeys.add(16);
        this.hotkey.setHotkey(keyCode);
        this.hotkey.setRequiredKeys(requiredKeys.toArray(new Integer[0]));
    }

    private boolean isUnwanted(KeyCode keyCode) {
        return keyCode == KeyCode.CONTROL
                || keyCode == KeyCode.SHIFT
                || keyCode == KeyCode.ALT
                || keyCode == KeyCode.PRINTSCREEN
                || keyCode == KeyCode.ENTER
                || keyCode == KeyCode.ESCAPE
                || keyCode == KeyCode.BACK_SPACE;
    }


    private void setText() {
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(this.hotkey.getRequiredKeys())
                .forEach(var -> {
                    stringBuilder.append(java.awt.event.KeyEvent.getKeyText(var));
                    stringBuilder.append(" + ");
                });
        stringBuilder.append(java.awt.event.KeyEvent.getKeyText(this.hotkey.getHotkey()));
        this.textField.setText(stringBuilder.toString());
    }
}
