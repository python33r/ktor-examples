# Dice Rolling Simulator

An application that simulates the rolling of a one or more d4, d6, d8, d10,
d12 or d20 dice.

This is based on the `html-dsl` example and uses the [kotlinx.html][kxh]
library, in conjunction with Ktor's `respondHtml()` function. It builds on
that example by having two separate pages: a home page that displays a form,
on which the number of dice and number of die sides can be selected; and
a results page, displaying the results of the dice roll.

Build and run the server with

    ./amper run

Then visit `http://0.0.0.0:8080/` to choose your dice.


[kxh]: https://github.com/Kotlin/kotlinx.html
