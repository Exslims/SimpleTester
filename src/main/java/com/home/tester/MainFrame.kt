package com.home.tester

import java.awt.Dimension
import javax.swing.JFrame

open class MainFrame : JFrame("SimpleTester") {
    init {
        this.setLocationRelativeTo(null)
        this.preferredSize = Dimension(300,300)
        this.defaultCloseOperation = EXIT_ON_CLOSE
    }
}
