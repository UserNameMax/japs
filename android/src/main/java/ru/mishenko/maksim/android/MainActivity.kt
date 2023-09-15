package ru.mishenko.maksim.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.arkivanov.decompose.defaultComponentContext
import ru.mishenko.maksim.common.App
import ru.mishenko.maksim.common.ui.root.Root

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = Root(componentContext = defaultComponentContext())
        setContent {
            App(root = root)
        }
    }
}