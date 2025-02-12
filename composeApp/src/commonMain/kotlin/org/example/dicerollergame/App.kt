package org.example.dicerollergame

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dicerollergame.composeapp.generated.resources.Res
import dicerollergame.composeapp.generated.resources.congrast
import dicerollergame.composeapp.generated.resources.dice_1
import dicerollergame.composeapp.generated.resources.dice_2
import dicerollergame.composeapp.generated.resources.dice_3
import dicerollergame.composeapp.generated.resources.dice_4
import dicerollergame.composeapp.generated.resources.dice_5
import dicerollergame.composeapp.generated.resources.dice_6
import dicerollergame.composeapp.generated.resources.start_game
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val isPlayer1 = remember { mutableStateOf(true) }
        var playerScores = remember { mutableStateOf(Array(2) { 0 }) }
        var diceImg = arrayOf(
            Res.drawable.dice_1,
            Res.drawable.dice_2,
            Res.drawable.dice_3,
            Res.drawable.dice_4,
            Res.drawable.dice_5,
            Res.drawable.dice_6
        )
        var currentDiceImg = remember { mutableStateOf(Res.drawable.start_game) }

        if (playerScores.value[0] >= 50 || playerScores.value[1] >= 50) {
            currentDiceImg.value = Res.drawable.congrast
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(28.dp))
                Image(
                    painter = painterResource(currentDiceImg.value),
                    contentDescription = null,
                    modifier = Modifier.padding(12.dp)
                        .height(350.dp)
                        .width(250.dp)
                )

                Spacer(modifier = Modifier.height(28.dp))
                if (playerScores.value[0] >= 50) {
                    Text(
                        text = "Player_1", fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
                } else {
                    Text(
                        text = "Player_2", fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))
                OutlinedButton(
                    onClick = {
                        playerScores.value = Array(2) { 0 }
//                        isPlayer1.value = true
                        currentDiceImg.value = Res.drawable.start_game
                    }
                ) {
                    Text(text = "RESTART")
                }
            }

        } else {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(buildAnnotatedString {
                    append("Welcome to ")
                    withStyle(
                        style = SpanStyle(
                            color = Color.Red,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                    ) {
                        append("DiceRollerGame")
                    }
                }, fontSize = 24.sp)

                Spacer(modifier = Modifier.height(28.dp))

                Image(
                    painter = painterResource(currentDiceImg.value),
                    contentDescription = null,
                    modifier = Modifier.padding(12.dp)
                        .height(350.dp)
                        .width(250.dp)
                )

                Spacer(modifier = Modifier.height(28.dp))

                OutlinedButton(
                    onClick = {
                        var randomNumber = (1..6).random()
                        currentDiceImg.value = diceImg[randomNumber - 1]
                        if (isPlayer1.value) {
                            playerScores.value[0] += randomNumber
                        } else {
                            playerScores.value[1] += randomNumber
                        }
                        isPlayer1.value = !isPlayer1.value
                    }
                ) {

                    if (isPlayer1.value) {
                        Text(text = "Roll the Dice for Player_1")
                    } else {
                        Text(text = "Roll the Dice for Player_2")
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(buildAnnotatedString {
                        append("Player_1 Score \n")
                        withStyle(
                            style = SpanStyle(
                                color = Color.Magenta,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Monospace,
                                fontSize = 20.sp
                            )
                        ) {
                            append("${playerScores.value[0]}")
                        }
                    }, textAlign = TextAlign.Center)

                    Text(buildAnnotatedString {
                        append("Player_2 Score \n")
                        withStyle(
                            style = SpanStyle(
                                color = Color.Magenta,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Monospace,
                                fontSize = 20.sp
                            )
                        ) {
                            append("${playerScores.value[1]}")
                        }
                    }, textAlign = TextAlign.Center)
                }

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "First to 50 wins!🎊",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 22.sp,
                    color = Color.DarkGray,
                    fontFamily = FontFamily.SansSerif
                )

                Spacer(modifier = Modifier.height(20.dp))
                OutlinedButton(
                    onClick = {
                        playerScores.value[0] = 0
                        playerScores.value[1] = 0
//                        playerScores.value = Array(2) { 0 }
                        isPlayer1.value = true
                        currentDiceImg.value = Res.drawable.start_game
                    }
                ){
                    Text(text = "RESET", fontWeight = FontWeight.Bold)
                }

            }
        }
    }
}