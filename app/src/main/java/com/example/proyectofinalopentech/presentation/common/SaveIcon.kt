package com.example.proyectofinalopentech.presentation.common

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.proyectofinalopentech.R

@Composable
fun SaveIcon(
    modifier: Modifier,
    size: Dp = 72.dp,
    withShadow: Boolean = true,
    isMarked: Boolean,
    iconMarked: ImageVector,
    iconDefault: ImageVector,
    onClick: () -> Unit,
) {
    IconButton(
       modifier = modifier,
        onClick = onClick
    ) {
        // shadow
        if(withShadow) Icon(
            modifier = Modifier.size(size+8.dp).offset(3.dp, 3.dp).blur(radius = 2.dp),
            imageVector = iconMarked,
            contentDescription = stringResource(id = R.string.save_manga),
            tint = Color(0, 0, 0, 50)
        )
        // icon
        Icon(
            modifier = Modifier.size(size),
            imageVector = if(isMarked) iconMarked else iconDefault,
            contentDescription = stringResource(id = R.string.save_manga),
            tint = if(isMarked) Color.Red else Color.Black
        )
    }
}