# Changelog

All notable changes to this project will be documented in this file.

## **Fireshotapp 0.4.1** - 2021-5-29

### Changed

- Toolbox now only shows when selection width and height are greater than 0
- Point in drawing now fits to the stroke size
- Settings ui update
- New settings options for:
  - Color picker for Zoom (middle rectangle, border, cross and grid)
  - Toggle option for Zoom (middle rectangle, cross, and grid)
  - Image pin drop shadow (color and radius)

### Fixed

- Hotkeys work again after using something from the toolboxes
- Zoom on multiple monitors

## **Fireshotapp 0.4.0** - 2021-03-20

### Added

- Pin feature (Pin the screenshot in an Always-on-Top window)
  - Pin icon
- System tray information message, when the screenshot is copied to your clipboard

### Changed

- Escape and confirm as global key
- Confirm(return) keycode changed from 13 to 10
- Stroke style from the selection border

### Fixed

- Image now gets saved as alphabetic
- Confirm hotkey works again
- You don't have to interact with the window to be able to escape/cancel the screenshot

## **Fireshotapp 0.3.4** - 2021-03-13

### Changed

- Implemented newer version of [lukasl-dev/jwinkey (1.0.2)](https://github.com/lukasl-dev/jwinkey)

### Fixed

- Performance issues
- Screenshot window now blocks key presses

## **Fireshotapp 0.3.3** - 2021-03-07

### Fixed

- Drawings work again
- Updates work again

## **Fireshotapp 0.3.2** - 2021-03-06

### Added

- *Take screenshot* option to system tray
- Confirm message for google search

### Changed

- Confirm message for OCR upload
- Confirm message for normal upload
- Some code cleanup

### Fixed

- Keylistener from [kristian/system-hook](https://github.com/kristian/system-hook)
  to [lukasl-dev/jwinkey](https://github.com/lukasl-dev/jwinkey)
    - Accented characters (e.g. *é*, *ê*, *è...*) work again
- Correct display of zoom in corners of the screen

## **Fireshotapp 0.3.1** - 2021-02-20

- First release
- Screenshot-Tool for easy and fast usage
    - Select a section of your screen to create a screenshot
    - Draw on the screenshot
    - Get the screenshot copied to your clipboard
    - Share the screenshot with your friends or just keep it for your self
  