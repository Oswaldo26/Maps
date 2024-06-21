package com.ebookfrenzy.maps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.ebookfrenzy.maps.ui.theme.MapsTheme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.ui.Alignment

import androidx.compose.ui.text.font.FontWeight

import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState




import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.padding





class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MapsTheme {
                setContent {
                    GalleryPager()
                }
            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun GalleryPager() {
    val salones = listOf("Room 1", "Room 2", "Room 3", "Room 4")
    val pagerState = rememberPagerState()

    HorizontalPager(
        count = salones.size,
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .weight(0.75f) // Adjust weight to allow more space for the texts
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                GalleryMap(salonName = salones[page])
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = salones[page],
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = "Descripción",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Este es un texto de prueba para la descripción del salón. Aquí puede ir una breve descripción o cualquier otra información relevante sobre el salón.",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp)) // Add space at the bottom
        }
    }
}

@Composable
fun GalleryMap(salonName: String) {
    val density = LocalDensity.current
    val wallThickness = with(density) { 2.dp.toPx() }
    val padding = with(density) { 20.dp.toPx() }
    val pathThickness = with(density) { 4.dp.toPx() }
    val arrowSize = with(density) { 10.dp.toPx() }

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawGallery(this, wallThickness, padding, pathThickness, arrowSize, salonName)
    }
}

fun drawGallery(drawScope: DrawScope, wallThickness: Float, padding: Float, pathThickness: Float, arrowSize: Float, salonName: String) {
    val width = drawScope.size.width
    val totalHeight = drawScope.size.height
    val height = totalHeight * 0.6f // Use slightly less than three-quarters of the screen height
    val verticalOffset = (totalHeight - height) / 4 // Center vertically but move down a bit

    val roomWidth = (width - 3 * padding - wallThickness) / 2
    val roomHeight = (height - 3 * padding - wallThickness) / 3  // Smaller rooms

    // Draw rooms with entrances
    // Room 1 with entrance on the right
    val room1TopLeft = Offset(padding, verticalOffset + padding)
    drawScope.drawRect(
        color = Color.Black,
        topLeft = room1TopLeft,
        size = androidx.compose.ui.geometry.Size(roomWidth, roomHeight),
        style = Stroke(width = wallThickness)
    )
    drawScope.drawRect(
        color = Color.Green,
        topLeft = Offset(room1TopLeft.x + roomWidth - wallThickness / 2, room1TopLeft.y + roomHeight / 2 - wallThickness / 2),
        size = androidx.compose.ui.geometry.Size(wallThickness, roomHeight / 2)
    )

    // Room 2 with entrance on the left
    val room2TopLeft = Offset(roomWidth + 2 * padding, verticalOffset + padding)
    drawScope.drawRect(
        color = Color.Black,
        topLeft = room2TopLeft,
        size = androidx.compose.ui.geometry.Size(roomWidth, roomHeight),
        style = Stroke(width = wallThickness)
    )
    drawScope.drawRect(
        color = Color.Green,
        topLeft = Offset(room2TopLeft.x - wallThickness / 2, room2TopLeft.y + roomHeight / 2 - wallThickness / 2),
        size = androidx.compose.ui.geometry.Size(wallThickness, roomHeight / 2)
    )

    // Room 3 with entrance on the right
    val room3TopLeft = Offset(padding, verticalOffset + roomHeight + 2 * padding)
    drawScope.drawRect(
        color = Color.Black,
        topLeft = room3TopLeft,
        size = androidx.compose.ui.geometry.Size(roomWidth, roomHeight),
        style = Stroke(width = wallThickness)
    )
    drawScope.drawRect(
        color = Color.Green,
        topLeft = Offset(room3TopLeft.x + roomWidth - wallThickness / 2, room3TopLeft.y + roomHeight / 2 - wallThickness / 2),
        size = androidx.compose.ui.geometry.Size(wallThickness, roomHeight / 2)
    )

    // Room 4 with entrance on the left
    val room4TopLeft = Offset(roomWidth + 2 * padding, verticalOffset + roomHeight + 2 * padding)
    drawScope.drawRect(
        color = Color.Black,
        topLeft = room4TopLeft,
        size = androidx.compose.ui.geometry.Size(roomWidth, roomHeight),
        style = Stroke(width = wallThickness)
    )
    drawScope.drawRect(
        color = Color.Green,
        topLeft = Offset(room4TopLeft.x - wallThickness / 2, room4TopLeft.y + roomHeight / 2 - wallThickness / 2),
        size = androidx.compose.ui.geometry.Size(wallThickness, roomHeight / 2)
    )

    // Draw the outer boundary
    val outerBoundaryLeft = padding / 2
    val outerBoundaryTop = verticalOffset + padding / 2
    val outerBoundaryWidth = width - padding
    val outerBoundaryHeight = height - padding

    drawScope.drawRect(
        color = Color.Black,
        topLeft = Offset(outerBoundaryLeft, outerBoundaryTop),
        size = androidx.compose.ui.geometry.Size(outerBoundaryWidth, outerBoundaryHeight),
        style = Stroke(width = wallThickness)
    )

    // Draw entrance in the outer boundary
    val entranceWidth = roomWidth / 2
    drawScope.drawRect(
        color = Color.Green,
        topLeft = Offset((width - entranceWidth) / 2, outerBoundaryTop + outerBoundaryHeight - wallThickness / 2),
        size = androidx.compose.ui.geometry.Size(entranceWidth, wallThickness)
    )

    // Draw a path from the entrance to the entrance of the current room
    val pathStartX = (width - entranceWidth) / 2 + entranceWidth / 2
    val pathStartY = outerBoundaryTop + outerBoundaryHeight
    val pathTurnX = width / 2
    val pathEndX: Float
    val pathEndY: Float

    when (salonName) {
        "Room 1" -> {
            pathEndX = room1TopLeft.x + roomWidth - 2 * wallThickness
            pathEndY = room1TopLeft.y + roomHeight / 2
        }
        "Room 2" -> {
            pathEndX = room2TopLeft.x - wallThickness
            pathEndY = room2TopLeft.y + roomHeight / 2
        }
        "Room 3" -> {
            pathEndX = room3TopLeft.x + roomWidth - 2 * wallThickness
            pathEndY = room3TopLeft.y + roomHeight / 2
        }
        "Room 4" -> {
            pathEndX = room4TopLeft.x - wallThickness
            pathEndY = room4TopLeft.y + roomHeight / 2
        }
        else -> {
            pathEndX = width / 2
            pathEndY = outerBoundaryTop + outerBoundaryHeight - padding
        }
    }

    val pathTurnY = (pathStartY + pathEndY) / 2

    // Draw the path
    drawScope.drawLine(
        color = Color.Red,
        start = Offset(pathStartX, pathStartY),
        end = Offset(pathTurnX, pathTurnY),
        strokeWidth = pathThickness
    )

    drawScope.drawLine(
        color = Color.Red,
        start = Offset(pathTurnX, pathTurnY),
        end = Offset(pathEndX, pathEndY),
        strokeWidth = pathThickness
    )

    // Draw arrowhead
    val arrowPath = Path().apply {
        moveTo(pathEndX, pathEndY)
        lineTo(pathEndX - arrowSize, pathEndY - arrowSize / 2)
        lineTo(pathEndX - arrowSize, pathEndY + arrowSize / 2)
        close()
    }
    drawScope.drawPath(
        path = arrowPath,
        color = Color.Red
    )
}