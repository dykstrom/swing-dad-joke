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

import net.miginfocom.swing.MigLayout
import org.divxdede.swing.busy.JBusyComponent
import org.divxdede.swing.busy.ui.BasicBusyLayerUI
import java.awt.Color
import java.awt.Dimension
import javax.swing.*

class MainView {

    val button = JButton("Get joke")
    val textArea = JTextArea()
    val textField = JTextField()

    lateinit var busyComponent: JBusyComponent<JPanel>

    fun frame() : JFrame {
        val frame = JFrame("icanhazdadjoke.com")
        frame.contentPane.add(panel())
        frame.preferredSize = Dimension(400, 300)
        frame.pack()
        return frame
    }

    private fun panel(): JComponent {
        val label = JLabel("Search for joke")

        textArea.lineWrap = true
        textArea.wrapStyleWord = true

        val panel = JPanel(MigLayout())
        panel.add(label, "span 2, wrap")
        panel.add(textField, "width 90%")
        panel.add(button, "width 10%, wrap")
        panel.add(textArea, "width 100%, height 100%, span 2")

        // Create a busy component without any veil
        busyComponent = JBusyComponent(panel, BasicBusyLayerUI(0, 0f, Color.WHITE))
        return busyComponent
    }
}