package se.dykstrom.swing.dadjoke.rest

import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue

class JokeClientIT {

    private val client = JokeClient()

    @Test
    fun shouldGetRandomJoke(): Unit = runBlocking {
        // When
        val joke = client.getRandomJoke().joke

        // Then
        assertTrue(joke.isNotBlank())
    }

    @Test
    fun shouldGetRandomJokeBySearchTerm(): Unit = runBlocking {
        // When
        val joke = client.getRandomJokeBySearchTerm("hipster").joke

        // Then
        assertTrue(joke.isNotBlank())
    }
}
