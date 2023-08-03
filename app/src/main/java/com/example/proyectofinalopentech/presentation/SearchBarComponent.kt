package com.example.proyectofinalopentech.presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyectofinalopentech.ui.theme.searchHint
import com.example.proyectofinalopentech.ui.theme.textFieldActiveBackground
import com.example.proyectofinalopentech.ui.theme.textFieldText


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarComponent(
    hint: String,
    callback: (String) -> Unit
) {
    var text by rememberSaveable { mutableStateOf("") }
    var showCloseIcon by rememberSaveable { mutableStateOf(text.isNotEmpty()) }
    val focusManager = LocalFocusManager.current

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        value = text,
        shape = RoundedCornerShape(24.dp),
        singleLine = true,
        textStyle = MaterialTheme.typography.textFieldText,
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.textFieldActiveBackground,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        placeholder = {Text(hint,modifier= Modifier.padding(0.dp), style = MaterialTheme.typography.searchHint)},
        leadingIcon = {Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Search icon"
        )},
        trailingIcon = {if(showCloseIcon) IconButton(onClick = {
            text = ""
            showCloseIcon = false
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
            showCloseIcon = text.trim().isNotEmpty()
        },
    )



}

@Preview
@Composable
fun Preview(){
    SearchBarComponent("Search Manga", ){}
}