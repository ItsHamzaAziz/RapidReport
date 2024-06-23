package com.example.rapidreport.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.rapidreport.domain.model.Article
import com.example.rapidreport.util.dateFormatter

@Composable
fun NewsArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onCardClicked: (Article) -> Unit
) {
    // Data by default is in "2024-06-22T08:31:31Z" format
    // dateFormatter formats it in better or readable format
    val date = dateFormatter(article.publishedAt)
    Card(
        modifier = modifier.clickable { onCardClicked(article) },
        shape = RoundedCornerShape(4.dp),
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            ImageHolder(imageUrl = article.urlToImage)
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = article.source.name ?: "",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = date,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}