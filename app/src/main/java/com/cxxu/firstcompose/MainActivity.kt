package com.cxxu.firstcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
//            Text("Hello world!")
            MessageCard0("Android")
            MessageCard1(
                /*这里是MessageCard1()的参数,可以直接用构造函数实例化一个,赋值给默认参数(形参)*/
                msg = Message("Colleague", "Hey, take a look at Jetpack Compose, it's great!")
            )
            MessageCard2(
                msg = Message("MessageCard2", "show!")
            )
//            setContent {
//            }
//            FirstComposeTheme {
            //                // A surface container using the 'background' color from the theme
            //                Surface(color = MaterialTheme.colors.background) {
            //                    Greeting("Android")
            //                }
            //            }
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
fun PreviewMessageCard() {
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
//    单一接口函数,神略圆括号花括号的lambda对象作为参数
    Row {
/*首先放入一个image*/
        Image(
            painter = painterResource(R.drawable.painter1),
            /*注意引用图片资源是R.drawable.xxx;而不像字符资源是R.id.xxx*/
            contentDescription = "Contact profile picture"
        )
        Column {
            /*注意,这个lambda对象内部的语句是两个函数调用(作为"表达式"主体)(而不是用逗号隔开的参数*/
            Text(text = msg.author)
            Text(text = msg.body)
        }
    }

}

@Preview
@Composable
fun MessageCard2Pre() {
    MessageCard2(
        msg = Message("MessageCard2", "show!")
    )
}
//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    FirstComposeTheme {
//        Greeting("Android")
//    }
//}