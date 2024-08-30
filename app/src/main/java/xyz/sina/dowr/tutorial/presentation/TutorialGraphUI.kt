package xyz.sina.dowr.tutorial.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.sina.dowr.tutorial.data.TutorialModel
import xyz.sina.dowr.ui.theme.DowrTheme

@Composable
fun TutorialGraphUI(tutorialModel: TutorialModel){

    Column(modifier = Modifier.fillMaxWidth().padding(20.dp)) {
        Image(modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .clip(RoundedCornerShape(10.dp)), alignment = Alignment.Center ,painter = painterResource(id = tutorialModel.image), contentDescription = null)
        Text(modifier = Modifier.fillMaxWidth(), style = TextStyle(textDirection = TextDirection.Rtl, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center),text = tutorialModel.title)
        Text(modifier = Modifier.fillMaxWidth(), style = TextStyle(textDirection = TextDirection.Rtl, textAlign = TextAlign.Justify),text = tutorialModel.description)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGraph(){
    DowrTheme {
        TutorialGraphUI(tutorialModel = TutorialModel.ThirdPage)
    }
}