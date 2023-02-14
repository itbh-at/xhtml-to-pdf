# XHTML to PDF Converter

An XHTML to PDF converter using the awesome _OPEN HTML TO PDF_ library and Quarkus, the Supersonic Subatomic Java
Framework.

This converter accepts an XHTML input file and outputs the rendered PDF into the directory of the HTML file. The `.html`
extension in the filename is replaced by `.pdf`.

If you want to learn more about _Quarkus_, please visit its website: <https://quarkus.io/>

If you want to learn more about _OPEN HTML TO PDF_, please visit its website:
<https://github.com/danfickle/openhtmltopdf>

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar <XHTML file>`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar <XHTML file>`.
