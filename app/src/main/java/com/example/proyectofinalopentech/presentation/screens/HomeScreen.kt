package com.example.proyectofinalopentech.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectofinalopentech.R
import com.example.proyectofinalopentech.navigation.Screen
import com.example.proyectofinalopentech.ui.theme.appDesc
import com.example.proyectofinalopentech.ui.theme.appTitle
import com.example.proyectofinalopentech.ui.theme.primaryButton

@Composable
fun HomeScreen(
    goToSearchMangas: () -> Unit
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Column {
            Text(text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.appTitle,
                modifier = Modifier.width(200.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(id = R.string.app_description), style = MaterialTheme.typography.appDesc)
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            onClick = { goToSearchMangas.invoke() }) {
            Text(stringResource(id = R.string.read), style = MaterialTheme.typography.primaryButton)
        }
    }

}
