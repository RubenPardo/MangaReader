package com.example.proyectofinalopentech.presentation.common

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(title:String, backCallback: (()->Unit)?,contentDescription: String = ""){

    CenterAlignedTopAppBar (
        navigationIcon  = {
            if(backCallback!=null){
                IconButton(
                    modifier = Modifier.semantics {
                        this.contentDescription = contentDescription
                    },
                    onClick = { backCallback.invoke() })
                {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )


                }
            }
        },
        title = {
            Text(title, style = MaterialTheme.typography.titleLarge) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )

    )

}