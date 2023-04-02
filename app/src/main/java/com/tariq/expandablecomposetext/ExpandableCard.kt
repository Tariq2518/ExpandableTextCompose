package com.tariq.expandablecomposetext

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tariq.expandablecomposetext.ui.theme.Shapes
import com.tariq.expandablecomposetext.ui.theme.color1
import com.tariq.expandablecomposetext.ui.theme.color2

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpandableCard() {
    var expandedState by remember { mutableStateOf(false) }

    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = Shapes.medium,
        onClick = {
            expandedState = !expandedState
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(6f),
                    text = "Expandable Card",
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium)
                        .weight(1f)
                        .rotate(rotationState),
                    onClick = {
                        expandedState = !expandedState
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down arrow"
                    )

                }
            }
            if (expandedState) {
                Text(
                    text = "absdcbab cbasdjbasdjc ja sjdbcajksdcvkahsbdk sahdvckasbcbsjdbcjabsc djcb," +
                            "kjabsdvblasbvhasbdvhasbdvbasbdjb sjdbavhjabsj asdvbhjas sdvhjsd hsdbvj" +
                            "asjkdvajsbvjhabsd ahsvdhas;ljiosan as casbdhcashdb b dhvahsdvajdn",
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }

            var text by remember {
                mutableStateOf("")
            }

            Spacer(modifier = Modifier.size(8.dp))
            OutlinedTextField(
                label = { Text(text = "Name") },
                value = text,
                onValueChange = {
                    text = it
                },
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = { Text(text = "Type Here") },
                textStyle = TextStyle.Default
            )

            var password by rememberSaveable {
                mutableStateOf("")
            }
            var passwordVisibility by remember {
                mutableStateOf(false)
            }

            Spacer(modifier = Modifier.size(12.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = { Text(text = "Password") },
                textStyle = TextStyle.Default,
                label = { Text(text = "Password") },
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        Icon(imageVector = Icons.Default.Lock, contentDescription = "Password")

                    }
                },
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                )
            )

            Spacer(modifier = Modifier.size(12.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                GradientButton(
                    text = "Login",
                    textColor = Color.White,
                    gradient = Brush.horizontalGradient(
                        colors = listOf(
                            color1,
                            color2
                        )
                    )
                ) {

                }
            }
        }

    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview

private fun ExpandableCardPreview() {
    ExpandableCard()
}