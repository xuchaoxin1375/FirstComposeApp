package com.cxxu.firstcompose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cxxu.firstcompose.ui.theme.FirstComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FirstComposeTheme {
                MessageCard3(Message("Android", "Jetpack Compose"))
//                MessageCard3(Message("line2","test"))
            }
//            Text("Hello world!")
            /*setContent{}中的内容在安装app后会实际的显示在屏幕上*/
            Row {

//                MessageCard0("Android")
//                MessageCard1(
//                    /*这里是MessageCard1()的参数,可以直接用构造函数实例化一个,赋值给默认参数(形参)*/
//                    msg = Message("Colleague", "Hey, take a look at Jetpack Compose, it's great!")
//                )
//                MessageCard2(
//                    msg = Message("MessageCard2", "show!")
//                )
            }

        }
    }
}

/*自定以可组合函数:(采用@Composable注解)
* 我们将在SetContent{}中调用它
*
* 我们的意图是定义一系列不同的Card(卡片布局),目前里面存放的内容比较简单(类型和数目都简单),而后我们将对其内容进行设计规划,即
* 定义并使用更多的composable function 来达到目的*/
@Composable
fun MessageCard0(name: String) {
    Text(text = "Hello $name!")
}

/*采用@Preview来启用预览,同时创建一个前缀为Preview(或者其他能够区别源函数的名字)的对应的预览函数,
在这个函数中调用想要被预览的函数本身,且要给与适当的(默认参数)来查看*/
//@Preview //(当您先要关闭某个预览compose function,把@Preview注释掉即可
@Composable
fun MessageCard0Pre() {
    /*被预览的函数*/
    MessageCard0("Android2")
}

/*更进一步:
* 构建一个数据类*/
data class Message(val author: String, val body: String)

/*编写适合于所创建的Message数据类的compose function:*/
@Composable
fun MessageCard1(msg: Message) {
    Text(text = msg.author)
    Text(text = msg.body)
}

/*创建对应的预览*/
//@Preview
@Composable
fun MessageCard1Pre(/*empty parameter for previewFunction*/) {
    MessageCard1(
        /*这里是MessageCard1()的参数,可以直接用构造函数实例化一个,赋值给默认参数(形参)*/
        msg = Message("Colleague", "Hey, take a look at Jetpack Compose, it's great!")
    )
    /*或许您会发现,文本产生了重叠,接下来我们尝试解决*/
}

/*接下来我们设计/使用一些辅助排版的composable function*/
@Composable
fun MessageCard2(msg: Message) {
    /*定义第一行的内容布局*/
//    单一接口函数,神略圆括号花括号的lambda对象作为参数
    Row {
/*首先放入一个image(作为本行的第一列内容)*/
        Image(
            painter = painterResource(R.drawable.painter1),
            /*注意引用图片资源是R.drawable.xxx;而不像字符资源是R.id.xxx*/
            contentDescription = "Contact profile picture"
        )
        /*放入一个列元素*/
        Column {
            /*注意,这个lambda对象内部的语句是两个函数调用(作为"表达式"主体)(而不是用逗号隔开的参数*/
            /*列元素的第1~3行内容分别如下定义*/
            Text(text = msg.author)
            Text(text = msg.body)
            Text(text = "testLine3")
        }
    }

}

//@Preview
@Composable
fun MessageCard2Pre() {
    MessageCard2(
        msg = Message("MessageCard2", "show!")
    )
}

@Composable
fun MessageCard3(msg: Message) {
    // Add padding around our message
    /*这里为了更加精细的控制布局和显示效果,我们启用了composable function的modifier参数(限定符)*/
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.painter1),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                // Set image size to 40 dp
                .size(40.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)
                /*尝试添加颜色设定:(采用Material主题中的颜色值做设置)
                * 加上下面这一行,头像就有外圈青色的环*/
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )

        // Add a horizontal space between the image and the column
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = msg.author,
                /*依然尝试采用MaterialDesign的值来设置颜色,同时采用其排版值(方案subtitle2)*/
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )
            // Add a vertical space between the author and message texts
            Spacer(modifier = Modifier.height(4.dp))
//            Text(
//                text = msg.body,
//                style = MaterialTheme.typography.body2
//            )
            /*形状封装:依然采用MaterialTheme的值来设计*/
            Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp) {
                Text(
                    text = msg.body,
                    /*填充*/
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.body2
                )
            }

        }
    }
}

//@Preview
@Composable
fun MessageCard3Pre() {
    MessageCard3(msg = Message("MessageCard3", "isn't it beautiful?"))
}


/*布局采样主题化*/


@Preview
@Composable
fun PreviewMessageCard() {
    /*主题名在创建项目(Activity)之初就已经生成了,当然也可以搜索ui.theme自行查找*/
    FirstComposeTheme {
        MessageCard3(
            msg = Message("Colleague", "Take a look at Jetpack Compose, it's great!")
        )
    }
}

/**启用深色主题
您可以启用深色主题（或夜间模式），以避免显示屏过亮（尤其是在夜间），或者只是节省设备电量。由于支持 Material Design，Jetpack Compose 默认能够处理深色主题。使用 Material 颜色、文本和背景时，系统会自动适应深色背景。

您可以在文件中以单独函数的形式创建多个预览，也可以向同一个函数中添加多个注解。

下面我们来添加新的预览注解并启用夜间模式。*/

@Preview(name = "Light Mode")
@Composable
fun MessageCard3PreLight() {
    FirstComposeTheme() {
        MessageCard3(
            msg = Message("Colleague", "Hey, take a look at Jetpack Compose, it's great!")
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun MessageCard3PreDark() {
    FirstComposeTheme() {
        MessageCard3(
            msg = Message("Colleague", "Hey, take a look at Jetpack Compose, it's great!")
        )
    }
}

