package com.example.newsapptheme.presentation.screens.home


import android.app.DatePickerDialog
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapptheme.domain.model.News
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {
    FormScreen(homeScreenViewModel)
}

@Composable
fun FormScreen(viewModel: HomeScreenViewModel) {
    // Individual states for each field in the form


    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.US)

    // Helper function to validate URL
    fun isValidUrl(url: String): Boolean {
        val urlPattern = Regex("^(https?|ftp)://[^\\s/$.?#].[^\\s]*\$")
        return url.matches(urlPattern)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text(text = "Create New Article", style = MaterialTheme.typography.h5)
        }
        item {
            // Title Input field
            OutlinedTextField(
                value = viewModel.title.value,
                onValueChange = { viewModel.title.value = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            // Description Input field
            OutlinedTextField(
                value = viewModel.description.value,
                onValueChange = { viewModel.description.value = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )
        }
        // Multiple Content Fields (dynamic list)
        item {
            Text(text = "Contents:")
        }
        items(viewModel.contents.value) { content ->
            OutlinedTextField(
                value = content,
                onValueChange = { newContent ->
                    val updatedContents = viewModel.contents.value.toMutableList()
                    val index = viewModel.contents.value.indexOf(content)
                    if (index != -1) {
                        updatedContents[index] = newContent
                        viewModel.contents.value = updatedContents
                    }
                },
                label = { Text("Content") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            // Button to add new content
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                androidx.compose.material3.Button(
                    onClick = {
                        viewModel.contents.value += ""
                    },
                    modifier = Modifier.fillMaxWidth(0.4f)
                ) {
                    Text(text = "Add Content", color = Color.White)
                }
            }
        }
        item {
            // Author Input field
            OutlinedTextField(
                value = viewModel.author.value,
                onValueChange = { viewModel.author.value = it },
                label = { Text("Author") },
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            // URL to Image Input field
            OutlinedTextField(
                value = viewModel.urlToImage.value,
                onValueChange = { viewModel.urlToImage.value = it },
                label = { Text("URL to Image") },
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            // URL Input field
            OutlinedTextField(
                value = viewModel.url.value,
                onValueChange = { viewModel.url.value = it },
                label = { Text("URL") },
                modifier = Modifier.fillMaxWidth(),
                isError = !isValidUrl(viewModel.url.value)
            )
            if (!isValidUrl(viewModel.url.value)) {
                Text(
                    text = "Invalid URL",
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption
                )
            }
        }


        item {
            // Date Picker for PublishedAt
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Published At: ${viewModel.publishedAt.value}")
                androidx.compose.material3.Button(onClick = {
                    val datePicker = DatePickerDialog(
                        context,
                        { _, year, month, dayOfMonth ->
                            calendar.set(year, month, dayOfMonth)
                            viewModel.publishedAt.value = dateFormat.format(calendar.time)
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    )
                    datePicker.show()
                }) {
                    Text(text = "Pick Date", color = Color.White)
                }
            }
        }


        item {
            // Submit Button
            androidx.compose.material3.Button(
                onClick = {
                    if (isValidUrl(viewModel.url.value)) {
                        viewModel.addNews(
                            News(
                                title = viewModel.title.value,
                                description = viewModel.description.value,
                                content = viewModel.contents.value.map { it },  // Combine multiple contents
                                author = viewModel.author.value,
                                urlToImage = viewModel.urlToImage.value,
                                url = viewModel.url.value,
                                publishedAt = viewModel.publishedAt.value
                            )
                        )
                        // Handle the form submission
                        Toast.makeText(context, "Form submitted", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Please enter a valid URL", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Submit", color = Color.White)
            }
        }




    }
}


@Preview(showBackground = true)
@Composable
fun FormScreenPreview() {
//    FormScreen(homeScreenViewModel)
}