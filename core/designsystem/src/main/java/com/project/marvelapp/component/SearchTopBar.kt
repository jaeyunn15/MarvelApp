package com.project.marvelapp.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.project.marvelapp.core.designsystem.R

@Composable
fun SearchTopBar(
    currentSearchText: String?,
    updateSearchText: (String) -> Unit,
    clearSearchText: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                drawLine(
                    color = Color.Transparent,
                    strokeWidth = 1f,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height)
                )
            }
    ) {
        TextField(
            modifier = Modifier
                .weight(1f)
                .background(color = Color.Transparent),
            value = currentSearchText ?: "",
            onValueChange = updateSearchText,
            leadingIcon = {
                Image(
                    modifier = Modifier,
                    painter = painterResource(id = R.drawable.ic_baseline_search_24),
                    contentDescription = null
                )
            },
            trailingIcon = {
                if (currentSearchText.isNullOrEmpty().not()) {
                    Image(
                        modifier = Modifier.clickable { clearSearchText() },
                        painter = painterResource(id = R.drawable.ic_baseline_close_24),
                        contentDescription = null
                    )
                }
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search_hint_text),
                    color = Color.Gray,

                )
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent,
                textColor = Color.Gray
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
                keyboardType = KeyboardType.Text
            ),
            maxLines = 1
        )
    }
}