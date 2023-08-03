package com.example.proyectofinalopentech.presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyectofinalopentech.ui.theme.searchHint

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarComponent(
    title: String,
    hint: String,
    callback: (String) -> Unit
) {
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    if(!active){
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){

            Spacer(modifier = Modifier.weight(1.5f))

            Text(text = title, style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.weight(1.0f))

            IconButton(onClick = {
                active = true
            }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Open search bar"
                )
            }
        }
    }

    if(active){
        TextField(
            value = text,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().background(Color.Transparent),
            placeholder = {Text(hint, style = MaterialTheme.typography.searchHint)},
            trailingIcon = {IconButton(onClick = {
                active = false
                text = ""
                callback.invoke("")
            }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Close search bar"
                )
            }},
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = androidx.compose.ui.text.input.ImeAction.Done, keyboardType = KeyboardType.Text),
            onValueChange = {newValue ->
                if(newValue.trim() != text.trim()){
                    // avoid extra calls
                    callback.invoke(newValue)
                }
                 text = newValue
            },

        )
    }

}

@Preview
@Composable
fun Preview(){
    SearchBarComponent("Browse", "Buscar Manga"){}
}