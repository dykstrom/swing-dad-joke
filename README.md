# swing-dad-joke

A simple, modern-looking Swing application written in Kotlin, that retrieves and displays jokes from 
[icanhazdadjoke.com](https://icanhazdadjoke.com).

Libraries used:

* Custom look-and-feel by [FlatLaf](https://www.formdev.com/flatlaf).
* Component layout by [MigLayout](http://miglayout.com).
* Busy indicator by [JBusyComponent](https://code.google.com/archive/p/jbusycomponent).
* REST calls running in a [Kotlin coroutine](https://kotlinlang.org/docs/coroutines-overview.html) using the 
  [Retrofit](https://square.github.io/retrofit) REST client.
