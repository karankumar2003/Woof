package com.example.woof

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.woof.ui.theme.WoofTheme
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Expand
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.woof.data.Dog
import com.example.woof.data.dogs

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            WoofTheme {
                WoofApp()
            }
        }
    }
}


@Composable
fun WoofApp() {

    Scaffold(
        topBar = {
            TopAppBar()
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colors.background)

        ) {
            items(dogs) {
                DogItem(dog = it)
            }
        }
    }


}


@Composable
fun DogItem(dog: Dog, modifier: Modifier = Modifier) {
    var expandedState by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    spring(
                        Spring.DampingRatioMediumBouncy,
                        Spring.StiffnessLow
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                DogIcon(dog.imageResourceId)
                DogInformation(dog.name, dog.age)
                Spacer(modifier = Modifier.weight(1f))
                ExpandIconButton(expanded = expandedState,
                    { expandedState = !expandedState }
                )

            }
            if (expandedState) {
                DogHobby(hobbyRes = dog.hobbies)
            }

        }


    }

}


@Composable
fun DogIcon(@DrawableRes dogIcon: Int, modifier: Modifier = Modifier) {
    Image(
        modifier = modifier
            .size(64.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(50)),
        painter = painterResource(dogIcon),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}


@Composable
fun DogInformation(@StringRes dogName: Int, dogAge: Int, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = stringResource(dogName),
            modifier = modifier.padding(top = 8.dp),
            style = MaterialTheme.typography.h2
        )
        Text(
            text = stringResource(R.string.years_old, dogAge),
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
fun ExpandIconButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = "Tap to Expand Or Collapse"
        )
    }
}

@Composable
fun DogHobby(@StringRes hobbyRes: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
                bottom = 8.dp
            )
    ) {
        Text(
            "Hobby:",
            style = MaterialTheme.typography.h3
        )
        Text(
            stringResource(id = hobbyRes),
            style = MaterialTheme.typography.body1
        )
    }

}

@Composable
fun TopAppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_woof_logo),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .padding(8.dp)
        )


        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h1
        )
    }
}

@Preview
@Composable
fun WoofPreview() {
    WoofTheme(darkTheme = false) {
        WoofApp()
    }
}

@Preview
@Composable
fun WoofDarkThemePreview() {
    WoofTheme(darkTheme = true) {
        WoofApp()
    }
}