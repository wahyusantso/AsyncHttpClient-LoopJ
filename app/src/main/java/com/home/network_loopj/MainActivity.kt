package com.home.network_loopj

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.home.network_loopj.ui.theme.NetworkLoopJTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NetworkLoopJTheme {
                val viewmodel = viewModel<MainViewModel>()
                val quote = viewmodel.quote
                val author = viewmodel.author

                ConstraintLayout(modifier = Modifier
                    .fillMaxSize()
                ) {
                    val (textQuote, textAuthor, loading) = createRefs()

                    Text(
                        text = quote,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(16.dp)
                            .constrainAs(textQuote) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom)
                            }
                    )
                    Text(
                        text = author,
                        fontSize = 14.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color.Gray,
                        modifier = Modifier
                            .constrainAs(textAuthor) {
                                top.linkTo(textQuote.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    )
                    if (viewmodel.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .constrainAs(loading) {
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    bottom.linkTo(parent.bottom)
                                }
                        )
                    }
                }
            }
        }
    }
}