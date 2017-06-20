# IView X Java #

[![Build Status](https://travis-ci.org/FG-SE/iviewx-java.svg?branch=master)](https://travis-ci.org/FG-SE/iviewx-java)
[![Bintray](https://img.shields.io/bintray/v/fg-se/iviewx-java/iviewx-java.svg)](https://bintray.com/fg-se/iviewx-java/iviewx-java)
[![Javadoc](https://img.shields.io/badge/javadoc-online-brightgreen.svg)](https://fg-se.github.io/iviewx-java/javadoc/)
[![Wiki](https://img.shields.io/badge/wiki-online-brightgreen.svg)](https://github.com/FG-SE/iviewx-java/wiki)
[![GitHub license](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://raw.githubusercontent.com/FG-SE/iviewx-java/master/LICENSE)

The **iviewx-java** library is a Java adapter for the SMI IView X SDK.

The library consists of two parts:

The **[iviewx-java/core][1]** library contains classes and interfaces
that allow fetching and stabilization of chronologically ordered data
from sources that provide a conforming receiver. The core library
also offers services for asynchronous data recording and integration
with **[RxJava][2]**.

The **[iviewx-java/iviewx][3]** library contains the adapter to the SMI
IView X SDK, the receivers and data types to integrate with the core
library, and serialization functionality for storing or transmitting
eyetracking data.

## Getting started ##

The following instructions will get you up and running with the
**iviewx-java** library.

### Prerequisites ###

* Java 1.8 or higher
* IView X SDK 4.2 or higher
* IView X Eyetracking Server
* The SDK `/bin` folder in your PATH environment variable

**Note:** The library has been tested with the SMI Red-m Eye Tracker
          and the IView X SDK 4.2. Other models and SDK versions might
          fail to run, or need a few tweaks to the SDK adapter. See the
          developer section of the User Guide for further information.

### Installation ###

If you are using a build tool, you can add **iviewx-java** as a
dependency.

#### Gradle: ####

```groovy
compile 'de.unihannover.se.iviewxjava:iviewx-java:1.0.0'
```

#### Maven: ####

```xml
<dependency>
  <groupId>de.unihannover.se.iviewxjava</groupId>
  <artifactId>iviewx-java</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```

All versions of the library, as well as the  above snippets with
the most recent version inserted, can be found on the projects
[Bintray package site][4].

The library is hosted on JCenter, so make sure to add it as a
repository in your build configuration.

#### Building from Source: ####

If you do not use a build tool, you can also build the project from
source.

Simply clone this repository and run one of the *archives*
Gradle tasks to produce a JAR file, which you can add to
your project.

```bash
# Produces a JAR without the dependencies
gradlew.bat jar

# Produces a fat JAR, containing all of the dependencies
gradlew.bat fatJar
```

**Note:** The use of a build tool for dependency management is advised

## Running the Tests ##

To run the tests for the **iviewx-java** library, clone this repository
and call the *test* Gradle task.

```bash
gradlew.bat test
```

The **iviewx-java** repository is integrated with Travis CI, to
identify failing builds early on.

## Documentation ##

We offer a User Guide, containing detailed information on how to
set up a project with **iviewx-java**, how to work with the library,
code examples, application design advice and developer information.

For a detailed description of all classes and their methods, we
provide a online version of the projects Javadoc.

* **[User Guide (WIP)][5]**
* **[Javadoc][6]**

## Troubleshooting ##

If you find a bug, or have a request for a feature, please open
a new [Issue][15] and provide thorough information on your request.

If you have trouble writing a good issue, check out this
[Article by Wiredcraft][16] that explains what information to
include in a good GitHub issue.

## Versioning ##

This project uses [Semantic Versioning][7].

For the available versions,
see the [Bintray package site][4].

## Authors ##

* **Luca Fülbier** - *Developer* - ([GitHub][8])
* **Oliver Karras** - *Project Supervisor* - ([FGSE Homepage][9])

## License ##

This project is licensed under the Apache 2.0 License.

See the [LICENSE][10] file for details.

See the [LICENSE-3RD-PARTY][11] file for details on the licenses of
the dependencies used by this library.

## Acknowledgements ##

* Prof. Dr. Kurt Schneider ([FGSE Website][12]) of the Fachgebiet
  Software Engineering at the Leibniz University Hannover ([Website][13])
  for initiating and open-sourcing the project
* The [Mockito Project][14] for being a great reference on how to
  present your open source library on GitHub

[1]: https://github.com/FG-SE/iviewx-java/tree/master/src/main/java/de/unihannover/se/iviewxjava/core
[2]: https://github.com/ReactiveX/RxJava
[3]: https://github.com/FG-SE/iviewx-java/tree/master/src/main/java/de/unihannover/se/iviewxjava/iviewx
[4]: https://bintray.com/fg-se/iviewx-java/iviewx-java
[5]: https://github.com/FG-SE/iviewx-java/wiki
[6]: https://fg-se.github.io/iviewx-java/javadoc/
[7]: http://semver.org/
[8]: https://github.com/lucafuelbier
[9]: http://www.se.uni-hannover.de/pages/de:mitarbeiter_oliver_karras
[10]: https://github.com/FG-SE/iviewx-java/blob/master/LICENSE
[11]: https://github.com/FG-SE/iviewx-java/blob/master/LICENSE-3RD-PARTY
[12]: http://www.se.uni-hannover.de/pages/de:mitarbeiter_kurt_schneider
[13]: http://www.se.uni-hannover.de/pages/de:startseite
[14]: https://github.com/mockito/mockito
[15]: https://github.com/FG-SE/iviewx-java/issues
[16]: https://wiredcraft.com/blog/how-we-write-our-github-issues/