# Another Minecraft Chat Client

![license](https://img.shields.io/github/license/Defective4/Minecraft-Chat-Client)
![version](https://img.shields.io/github/v/release/Defective4/Minecraft-Chat-Client)
![lastCommit](https://img.shields.io/github/last-commit/Defective4/Minecraft-Chat-Client)
![version](https://img.shields.io/badge/latest_mc_version-1.19.2-success)
[![Java CI](https://github.com/Defective4/Another-Minecraft-Chat-Client/actions/workflows/maven.yml/badge.svg)](https://github.com/Defective4/Another-Minecraft-Chat-Client/actions/workflows/maven.yml)
![downloads](https://img.shields.io/github/downloads/Defective4/Another-Minecraft-Chat-Client/total)

<img width=10% src="https://raw.githubusercontent.com/Defective4/Another-Minecraft-Chat-Client/master/logo.png"/>  

[:book: Wiki](https://github.com/Defective4/Another-Minecraft-Chat-Client/wiki) • [:arrow_down: Downloads](https://github.com/Defective4/Another-Minecraft-Chat-Client/releases) • [:hammer: Build from source](https://github.com/defective4/another-Minecraft-Chat-Client/#building-from-source)

AMCC is a GUI application that lets you join a Minecraft server and chat freely without opening your game.

|Table of contents|
|-----------------|
|* [:heart: Sponsors](https://github.com/Defective4/Another-Minecraft-Chat-Client#heart-sponsors)
|* [Main features](https://github.com/Defective4/Another-Minecraft-Chat-Client#main-features)             |
|* [Downloads](https://github.com/Defective4/Another-Minecraft-Chat-Client#downloads) |
|* [Using the Plugin API](https://github.com/Defective4/Another-Minecraft-Chat-Client#using-the-plugin-api)  |
|* [Using the Protocol implementation](https://github.com/Defective4/Another-Minecraft-Chat-Client#using-the-protocol-implemenetation-in-your-project)  |
|* [Building from source](https://github.com/Defective4/Another-Minecraft-Chat-Client#building-from-source) |
|* [Translations](https://github.com/Defective4/Another-Minecraft-Chat-Client#current-translations-see-wiki) |


# Main features
* 🔑 Microsoft authentication support
* 📖 Complete GUI with Minecraft styled server list, in-game player list with skins and a tabbed pane allowing you to chat on multiple clients.
* 🎨 Minecraft style UI elements, such as chat font (Minecraftia), configurable buttons and text fields.
* 📋 Tray support.
* ⚙️ My own lightweight implementation of Minecraft protocol, supporting versions 1.8 to 1.19.2.
* 📦 Basic inventory handling and item using.
* ⏰ Automatic messages and responses, perfect for AFKing.
* :electric_plug: Easily extensible via plugins!

# Downloads
You can download latest version of AMCC along with plugin and protocol API on the [releases](https://github.com/Defective4/Another-Minecraft-Chat-Client/releases) page.

# Using the Plugin API

You can include our Plugin API in your project by:

### Manual download

You can download the the Plugin API on
the [releases](https://github.com/Defective4/Another-Minecraft-Chat-Client/releases) page.

### Adding a Maven dependency

Paste this in your `pom.xml`

```xml
<dependency>
    <groupId>io.github.defective4.amcc</groupId>
    <artifactId>amcc-api</artifactId>
    <version>{version}</version>
</dependency>
```

`{version}` is the release version you want to develop for. For example `1.10.0`

### Adding a Gradle dependency

Paste this in your `build.gradle`

```gradle
dependencies {
  implementation 'io.github.defective4.amcc:amcc-api:{version}'
}
```

`{version}` is the release version you want to develop for. For example `1.10.0`

# Using the Protocol implemenetation in your project

You can include our project implementation in your project by:

### Manual download

You can download the the protocol implementation on
the [releases](https://github.com/Defective4/Another-Minecraft-Chat-Client/releases) page.

### Adding a Maven dependency

Paste this in your `pom.xml`

```xml
<dependency>
    <groupId>io.github.defective4.amcc</groupId>
    <artifactId>amcc-protocol</artifactId>
    <version>1.10.0</version>
</dependency>
```

### Adding a Gradle dependency

Paste this in your `build.gradle`

```gradle
dependencies {
  implementation 'io.github.defective4.amcc:amcc.protocol:1.10.0'
}
```

# Building from source

If you don't want to download the pre-built release you can also build the application, plugin API and protocol
implementation from source.

0. Make sure you have Apache Maven installed. If not, please [download](https://maven.apache.org/download.cgi) and
   install it.
1. `git clone https://github.com/Defective4/Another-Minecraft-Chat-Client.git`
2. `cd Another-Minecraft-Chat-Client`
3. `mvn clean package`

After all actions are complete, you will have:

* A runnable AMCC application binary in the `amcc-app/target` directory.  
  It's named `amcc-app-{version}-jar-with-dependencies.jar`
* Plugin API with javadocs and dependencies in the `amcc-api/target` directory.
* Protocol implementation with javadocs and dependencies in the `amcc-protocol/target` directory.

# 📙 My goals

This project is my take on implementing Minecraft's protocol from scratch.<br>
It started as a simple command line chat client and was quickly wrapped in a GUI.<br>
Now my main goal is to implement as many features from Minecraft's original protocol as I can
without using any other third-party libraries.

# :heart: Sponsors ([Sponsor this project](https://github.com/sponsors/Defective4))
- [Swofty](https://github.com/Swofty-Developments) - Thank you!

## Current translations (see [wiki](https://github.com/Defective4/Another-Minecraft-Chat-Client/wiki/Features#translations))

* English - Defective4
* Polish - Defective4
* Chinese - [qiuzilu](https://github.com/qiuzilu) and [xiaoliang2012](https://github.com/xiaoliang2012)
