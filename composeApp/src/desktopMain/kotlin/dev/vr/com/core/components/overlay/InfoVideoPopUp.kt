package dev.vr.com.core.components.overlay

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import dev.vr.com.core.components.button.CloseButton
import dev.vr.com.core.components.text.RoundedText
import dev.vr.com.core.theme.Theme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import vr.composeapp.generated.resources.*

@Composable
fun InfoVideoPopUp (
    image : DrawableResource,
    onDismiss: () -> Unit,
    onCLick: () -> Unit,
) {

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Row {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight(0.8f)
                    .clip(
                        GenericShape { size, _ ->
                            // Размер среза угла (можешь регулировать)
                            val cutX = size.width * 0.05f
                            val cutY = size.height * 0.1f

                            moveTo(0f, 0f)                 // левый верх
                            lineTo(size.width, 0f)         // правый верх
                            lineTo(size.width, size.height - cutY) // правая сторона вниз
                            lineTo(size.width - cutX, size.height) // срезанный угол
                            lineTo(0f, size.height)        // левый низ
                            close()
                        }
                    )
                    .background(Theme.colors.grayBackground)
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Column (
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth(),
                            painter = painterResource(image),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )

                        Column (
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.ic_play),
                                contentDescription = "play",
                                tint = Color.Unspecified,
                                modifier = Modifier
                                    .height(100.dp)
                                    .clickable (
                                        indication = null,
                                        interactionSource = remember { MutableInteractionSource() }
                                    ) {
                                        onCLick()
                                    }
                            )

                            Text(
                                modifier = Modifier
                                    .padding(top = 4.dp),
                                text = "ВИДЕО",
                                color = Theme.colors.textInverse,
                                fontSize = 32.sp,
                                fontFamily = FontFamily(Font(Res.font.Bold)),
                                fontWeight = FontWeight(700),
                            )
                        }
                    }


                    Row(
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .fillMaxWidth()
                    ) {
                        Column (
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            RoundedText(
                                content = {
                                    Text(
                                        text = "ВАС ЖДУТ НЕРЕАЛЬНЫЕ ЭМОЦИИ",
                                        color = Theme.colors.textInverse,
                                        fontSize = 32.sp,
                                        fontFamily = FontFamily(Font(Res.font.Bold)),
                                        fontWeight = FontWeight(700),
                                    )
                                },
                                color = Theme.colors.blueAction,
                                shape = Res.drawable.grafity_background
                            )
                            Text(
                                text = "ОТ КОМАНДНЫХ СОСТЯЗАНИЙ\nНА ВИРТУАЛЬНОЙ АРЕНЕ",
                                color = Theme.colors.textInverse,
                                fontSize = 32.sp,
                                fontFamily = FontFamily(Font(Res.font.Bold)),
                                fontWeight = FontWeight(700),
                            )
                        }

                        Column (
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            Text(
                                text = "VR ARENA",
                                color = Theme.colors.textInverse,
                                fontSize = 32.sp,
                                fontFamily = FontFamily(Font(Res.font.ExtraBold)),
                                fontWeight = FontWeight(800),
                            )

                            Text(
                                text = "Большое игровое пространство, созданное для проведения захватывающих командных сражений \n" +
                                        "в виртуальной реальности и организации незабываемых праздничных мероприятий.",
                                color = Theme.colors.secondaryText,
                                fontSize = 24.sp,
                                fontFamily = FontFamily(Font(Res.font.Light)),
                                fontWeight = FontWeight(600),
                            )
                        }
                    }

                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        thickness = 2.dp,
                        color = Theme.colors.secondaryGray
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "Спортивно-соревновательная составляющая и эффект полного погружения",
                        color = Theme.colors.blueAction,
                        textAlign = TextAlign.Center,
                        fontSize = 28.sp,
                        fontFamily = FontFamily(Font(Res.font.Bold)),
                        fontWeight = FontWeight(700),
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "в игру гарантировано подарят НЕЗАБЫВАЕМЫЕ ЭМОЦИИ всем участникам!",
                        color = Theme.colors.textInverse,
                        textAlign = TextAlign.Center,
                        fontSize = 28.sp,
                        fontFamily = FontFamily(Font(Res.font.Bold)),
                        fontWeight = FontWeight(700),
                    )

                }
            }

            CloseButton(
                modifier = Modifier
                    .padding(start = 8.dp)
            ) {
                onDismiss()
            }
        }
    }
}