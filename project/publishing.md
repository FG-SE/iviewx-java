# Deployment #

This document describes how to publish a new `iviewx-java` version
to JCenter via Bintray.

## Bintray ##

In order to publish your build artifacts to Bintray, you need to sign
up through the [Bintray OSS Signup](https://bintray.com/signup/oss) and
join the `fg-se` organization that owns the `iviewx-java` repository
and the `iviewx-java` package that you will upload your artifacts to.
Organizations are managed inside Bintray, so you will have to be
invited by an organization administrator to join.

## Authentication ##

To publish `iviewx-java` to Bintray, you have to provide your Bintray
credentials to the `gradle-bintray-plugin`.

To do that, you need to create a `gradle-local.properties` file at
the top-level of the project and add these lines to the document
(insert your own credentials):

```
bintray_user=iviewxuser
bintray_key=1234abcd5678ef
```

**NOTE:** Make sure you do not commit this file into version control! It
          contains private information you do not want to be published.
          This file is git-ignored in this repository by default.

The username can be found on the top-right on your bintray menu bar.
The API-key can be found on your Bintray user page via `Edit -> API Key`.

If no credentials were supplied in the properties file, the build will
fall back to the environment variables `BINTRAY_USER` and `BINTRAY_KEY`.
Using the environment variables isn't recommended, as they get harder
to manage if you want to deploy to other Bintray repositories aswell.

## Versioning ##

Before deploying the build artifact to Bintray, you have to provide new
versioning information. To do that, update the `bintray.pkg.version`
closure, the version attribute in the
`publishing.publications.IViewXJavaPublication`, and the version attribute
inside the `build.gradle` file:

```
version = '1.0.0'

...

IViewXJavaPublication(MavenPublication) {
    version: '1.0.0'
}

...

version {
    name = '1.0.0'
    desc = 'Version 1.0 of the iviewx-java library'
    released = new Date()
    vcsTag = '1.0.0'
}
```

The `iviewx-java` project uses [Semantic Versioning](http://semver.org/),
so make sure your new versioning information complies with the rules.

## Publishing ##

If you followed the above steps you are now ready to publish to Bintray,
which will in turn publish to JCenter. To do that, simply call the
`publishing -> bintrayUpload` task through your IDEs Gradle view, or
execute the task from the command line:

```
./gradlew bintrayUpload      # Linux
./gradlew.bat bintrayUpload  # Windows
```

If you want to publish to your local Maven repository, you can use the
`publishToMavenLocal` task instead.