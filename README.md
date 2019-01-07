# Temporal
_(temporary codename)_

**Timing Debug Tool**

More information will follow soon.

## Java 7 Legacy Edition
Gradle requires the variable `JDK7_HOME` in `gradle.properties` to be set!

You can create `gradle.properties` in project root directory or `gradle.properties` in `GRADLE_USER_HOME` directory.

Add whatever your path is to the JDK, e.g. for Mac
```
JDK7_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home
```

OpenJDK:<br>
https://openjdk.java.net

OpenJfx setup tutorial:<br>
https://openjfx.io/openjfx-docs/

### VM options for Temporal Viewer
```
--module-path ${PATH_TO_FX}
--add-modules=javafx.controls,javafx.fxml
```

### Token Example
```
Indicator   |Identifier  |Timestamp     |Type  |Entry Name           |Tags ...
$$temporal$$|c45D67s5D5af|324234234234  |notify|file ready to upload |file transfer  |client
$$temporal$$|c45D67s5D5af|324233436345  |start |upload file          |file transfer  |client
$$temporal$$|c45D67s5D5af|324233436387  |end   |upload file          |file transfer  |client
$$temporal$$|c45D67s5D5af|324233436387  |notify|process received file|file processing|server
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