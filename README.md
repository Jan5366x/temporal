# Temporal
_(temporary codename)_

**Timing Debug Tool**

More information will follow soon.

**>>> This tool is still in development and is not ready to use! <<<**

OpenJDK:<br>
https://openjdk.java.net

OpenJfx setup:<br>
1. Download JFX - https://gluonhq.com/products/javafx/
2. unzip it to a desired location
3. Add an environment variable pointing to the lib directory of the runtime

    **Linux / Mac**
    ```
    export PATH_TO_FX=path/to/javafx-sdk-13/lib
    ```
   
   **Windows**
   ```
   set PATH_TO_FX="path\to\javafx-sdk-13\lib"
   ```
   _(Or via extend system settings -> environment variables ui)_

You can find more setup details at:

https://openjfx.io/openjfx-docs/

### VM options for Temporal Viewer
```
--module-path ${PATH_TO_FX}
--add-modules=javafx.controls,javafx.fxml
```

**OpenJFX Note:**

Make sure to add the required modules, keeping into account transitive dependencies are automatically
resolved (for instance, there is no need to add javafx.graphics module,
since it is transitively required by the javafx.controls module).

### Token Example
```
Indicator   |Identifier  |Sub Branch |Timestamp     |Type  |Entry Name           |Tags ...
$$temporal$$|c45D67s5D5af|main       |324234234234  |notify|file ready to upload |file transfer  |client
$$temporal$$|c45D67s5D5af|main       |324233436345  |start |upload file          |file transfer  |client
$$temporal$$|c45D67s5D5af|main       |324233436387  |end   |upload file          |file transfer  |client
$$temporal$$|c45D67s5D5af|main       |324233436387  |notify|process received file|file processing|server
```
*  Indicator don't have to be on the start of the log or console line
*  Values are separated by | and leading and trailing spaces are trimmed
*  If `start` and `end`/`failed` type is used the entry name must be identical

#### Allowed types
* **Single Entry**
  * notify
  * error
  * warn
* **Range Entry**
  * start _(will start a time range which must be followed by `end` or `fail`)_
  * end _(will mark the time range as successfully completed)_
  * fail _(will mark the time range as failed)_