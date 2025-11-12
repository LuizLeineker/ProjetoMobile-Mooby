package com.example.mooby

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mooby.ui.theme.MoobyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Navigation()
        }
    }
}
// cadastro dos gastos, resumo financerio mensal, dicas de educação, metas, cadastro user, notificação
// REDE: authenticação firebase + Armazenamento de dicas financeiras + armazenamento offline de transações e dados do usuário + Sincronização híbrida
// ROTA tela login, main, transação, graficos, dicas, metas, perfil
// tipo de Navegação:  Navigation Compose

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "initial") {
        composable("initial"){
            Initial(navController)
        }
        composable("main"){
            Main(navController)
        }
        composable("transaction"){
            Transaction(navController)
        }
        composable("insights"){
            Insights(navController)
        }
        composable("tips"){
            Tips(navController)
        }
        composable("profile"){
            Profile(navController)
        }
        composable("objective"){
            Objective(navController)
        }

    }
}

@Composable
fun Initial(navController: NavController){

    Column(
        Modifier.padding(20.dp)
    ) {
        Text("Login & Register", Modifier.padding(40.dp), fontSize = 24.sp)

        Button(onClick = {
            navController.navigate("main")
        }){
            Text("Entrar", fontSize = 15.sp)
        }
    }

}
@Composable
fun Main(navController: NavController){

    Column(
        Modifier.padding(20.dp)
    ){
        Text("TelaInicial", Modifier.padding(40.dp), fontSize = 24.sp)

        Button(onClick = {
            navController.navigate("transaction")
        }) {
            Text("Gastos", fontSize = 15.sp)
        }

        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("Voltar", fontSize = 15.sp)
        }
    }

}

@Composable
fun Transaction(navController: NavController){
    Text("Dados gastos", Modifier.padding(40.dp), fontSize = 24.sp)
}

@Composable
fun Insights(navController: NavController){
    Text("Graficos", Modifier.padding(40.dp), fontSize = 24.sp)
}

@Composable
fun Tips(navController: NavController){
    Text("Dicas", Modifier.padding(40.dp), fontSize = 24.sp)
}

@Composable
fun Profile(navController: NavController){
    Text("Perfil do USER", Modifier.padding(40.dp), fontSize = 24.sp)
}

@Composable
fun Objective(navController: NavController){
    Text("Metas mensal...", Modifier.padding(40.dp), fontSize = 24.sp)
}


