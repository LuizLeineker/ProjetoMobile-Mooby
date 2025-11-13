package com.example.mooby

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mooby.navigation.Navigation
import com.example.mooby.ui.theme.MoobyTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Garante que o Firebase seja inicializado corretamente
        FirebaseApp.initializeApp(this)
        Log.d("FirebaseInit", "✅ Firebase inicializado com sucesso")

        enableEdgeToEdge()
        setContent {
            MoobyTheme {
                Navigation()
            }
        }
    }
}
