/*
 * Copyright 2021 Johan Dykström
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package se.dykstrom.swing.dadjoke

import kotlinx.coroutines.*
import kotlinx.coroutines.swing.Swing
import se.dykstrom.swing.dadjoke.rest.JokeClient

class MainController(private val view: MainView) {

    private val jokeClient = JokeClient()

    init {
        view.button.addActionListener { handleSearchAction() }
        view.textField.addActionListener { handleSearchAction() }
    }

    private fun handleSearchAction() {
        val searchTerm = view.textField.text.trim()
        launchOnSwingThread {
            try {
                view.busyComponent.isBusy = true
                view.textArea.text = getJoke(searchTerm).joke
            } catch (e: Exception) {
                view.textArea.text = "Failed to get joke: ${e.message}"
            } finally {
                view.busyComponent.isBusy = false
            }
        }
    }

    private suspend fun getJoke(searchTerm: String) =
        if (searchTerm.isBlank())
            jokeClient.getRandomJoke()
        else
            jokeClient.getRandomJokeBySearchTerm(searchTerm)
}

/**
 * Launches a new coroutine on the Swing EDT without blocking it and returns a reference to the coroutine as a [Job].
 * The coroutine is cancelled when the resulting job is [cancelled][Job.cancel].
 *
 * @param block the coroutine code which will be invoked in the Swing coroutine context.
 */
fun launchOnSwingThread(block: suspend CoroutineScope.() -> Unit): Job {
    return MainScope().launch(context = Dispatchers.Swing, block = block)
}
