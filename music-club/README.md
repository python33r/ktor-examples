# Music Club Demo Application

Web application for a music club, implemented using [Ktor][ktr], [htmx][hx],
[Pebble][peb] templates, the [Exposed][orm] ORM framework. Also included
is an application that populates the music club's [H2 database][db], and
another that demonstrates querying that database. Both of these applications
use Exposed's SQL DSL.

## Running The Demo

### Linux & macOS

Everything is managed by the [Amper][amp] build tool. Start by creating the
database with

    ./amper run -m create

By default, this runs _without_ logging the SQL statements in the terminal.
If you want to see these, use this instead:

    ./amper run -m create -- --sql

The database is created in the file `music.mv.db`.

To test whether the database can be queried successfully, do

    ./amper run -m query

Like `create`, this runs without logging any SQL. To see the actual SQL
queries that are executed, as well as the query results, use

    ./amper run -m query -- --sql

Run the server with

    ./amper run -m server

then visit `http://0.0.0.0:8080/` to interact with the application.

You can run the tests for the application with

    ./amper test -m server

### Windows

If you are using `cmd.exe` as your command shell, omit the `./` from the
start of the Amper commands shown above.

If you are using Windows PowerShell, invoke Amper with `.\amper.bat` instead
of `./amper`.

## Just Command Runner

If you have the [Just][jst] command runner, you can run the Amper commands
described above using

    just create
    just query
    just serve
    just test

You can use the single-letter abbreviations `c`, `q`, `s` and `t` for these
recipes.

## Understanding The Code

This project is organized into four distinct Amper modules. In each module,
code can be found in the `src` directory subtree.

### `database`

The `database` module contains all the code needed to represent the databases
used by the web application. This library of code is used by the other three
Amper modules (`create`, `query` and `server`).

The database schema is defined in `Artists.kt` and `Albums.kt`. The tables
specified in these files are mapped onto corresponding entities in
`Artist.kt` and `Album.kt`.

`MusicDatabase.kt` defines an object that holds a connection to the main
Music Club database, whereas `TestDatabase.kt` defines an object to manage an
in-memory database, suitable for testing the web application. This database
is populated with a few rows of test data using the `create()` method.

### `create`

This module contains a program in `Main.kt` that reads artist and album data
from CSV files and uses it to populate the main Music Club database. This
code uses Exposed's SQL DSL to insert new rows in the relevant tables.

Sample CSV files are provided in the `csv` subdirectory of the project.

### `query`

This module contains a program in `Main.kt` that demonstrates the use of
Exposed's SQL DSL to perform simple queries of the music club database.

### `server`

This module contains a Ktor-based web application.

`Templates.kt` contains the configuration needed to support the use of
Pebble templates.

`Routing.kt` sets up the routing of GET and POST requests to the relevant
request handling code. This code is distributed across the files `Home.kt`,
`Artists.kt` and `Albums.kt`. The request handlers use Exposed's Data Access
Objects API to make the necessary queries, then use query results to render
the appropriate Pebble template into HTML. The template files can be found
in `resources/templates`.

`Errors.kt` configures a custom error page for `404 Page Not Found` responses.

`Application.kt` provides the entry point for the application. It delegates
configuration to the extension functions defined in the aforementioned files,
and specifies a server to run the application.

A few automated tests for the application can be found in `ApplicationTest.kt`,
under the `test` directory subtree. These are not exhaustive and merely
illustrate the approach that can bew followed.

[ktr]: https://ktor.io/
[hx]: https://htmx.org/
[peb]: https://pebbletemplates.io/
[orm]: https://jetbrains.github.io/Exposed/
[db]: https://www.h2database.com/
[amp]: https://amper.org/
[jst]: https://github.com/casey/just
