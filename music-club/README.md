# Music Club Demo Application

Web application for a music club, implemented using [Ktor][ktr], [htmx][hx],
[Pebble][peb] templates, the [Exposed][orm] ORM framework. Also included
is a small application that creates the music club's [H2 database][db], and
another that demonstrates querying that database using Exposed's SQL DSL.

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

Like `create`, with runs without logging any SQL. To see the actual SQL
queries that are executed, as well as the query results, use this instead:

    ./amper run -m query -- --sql

Run the server with

    ./amper run -m server

then visit `http://0.0.0.0:8080/` to interact with the application.

### Windows

If you are using `cmd.exe` as your command shell, omit the `./` from the
start of the commands shown above.

If you are using Windows PowerShell, invoke Amper with `.\amper.bat` instead
of `./amper`.

## Examining The Code

### Database

Amper module `database` contains all the code needed to connect to the
databases used by the application, create the required database tables, and
interact with those tables. This library of code is used by the query demo
and the server.

The database schema is defined in `Artists.kt` and `Albums.kt`. The tables
specified in these files are mapped into corresponding entities in
`Artist.kt` and `Album.kt`.

`MusicDatabase.kt` defines an object representing the Music Club database,
and also provides code to populate the database with sample data.

`TestDatabase.kt` defines an object representing a smaller, simpler database,
suitable for testing the web application.

### Query Demo

Amper module `query` contains a program in `Main.kt` that demonstrates
the use of Exposed's SQL DSL to perform simple queries of the music club
database.

### Server

Amper module `server` contains a Ktor-based web application.

`Templates.kt` contains the configuration needed to support the use of
Pebble templates.

`Routing.kt` sets up the routing of GET and POST requests to the relevant
request handling code. This code is distributed across the files `Home.kt`,
`Artists.kt` and `Albums.kt`. The request handlers use Exposed's Data Access
Objects API to make the necessary queries, then use query results to render
the appropriate Pebble template into HTML. The template files can be found
in `resources/templates`.

`Application.kt` provides the entry point for the application. It delegates
configuration to the extension functions defined in the aforementioned files,
and specifies a server to run the application.

[ktr]: https://ktor.io/
[hx]: https://htmx.org/
[peb]: https://pebbletemplates.io/
[orm]: https://jetbrains.github.io/Exposed/
[db]: https://www.h2database.com/
[amp]: https://amper.org/
