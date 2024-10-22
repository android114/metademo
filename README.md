步骤 1: 获取 Facebook App ID
登录到 Facebook for Developers：https://developers.facebook.com/。
创建一个新应用或选择一个已有的应用。
在应用仪表板中，找到你的 App ID。

步骤 2: 在 AndroidManifest.xml 中添加 App ID
在你的 AndroidManifest.xml 文件中，添加一个 meta-data 元素，来指定 Facebook App ID。确保在 <application> 标签内添加以下代码：

<application
    android:allowBackup="true"
    android:icon="@mipmap/ic_launcher"
    android:label="@string/app_name"
    android:roundIcon="@mipmap/ic_launcher_round"
    android:theme="@style/Theme.AppCompat.Light.NoActionBar">

    <!-- Facebook App ID -->
    <meta-data
        android:name="com.facebook.sdk.ApplicationId"
        android:value="@string/facebook_app_id" />

    <!-- Facebook Activity -->
    <activity
        android:name="com.facebook.FacebookActivity"
        android:configChanges="keyboard|keyboardHidden|screenLayout|screenSize|smallestScreenSize|locale|layoutDirection|fontScale|screenDensity|directionalLayout"
        android:label="@string/app_name"
        android:theme="@style/Theme.AppCompat.Light.NoActionBar" />

    <!-- Chrome 自定义标签活动 -->
    <activity
        android:name="androidx.browser.customtabs.CustomTabActivity"
        android:exported="true">
        <intent-filter>
            <action android:name="android.intent.action.VIEW" />
            <category android:name="android.intent.category.DEFAULT" />
            <category android:name="android.intent.category.BROWSABLE" />
            <data android:scheme="http" />
            <data android:scheme="https" />
        </intent-filter>
    </activity>

    <!-- 其他活动 -->
    <activity android:name=".MainActivity">
        <intent-filter>
            <action android:name="android.intent.action.MAIN" />
            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
    </activity>

</application>

步骤 3: 在 res/values/strings.xml 文件中定义 App ID
在你的项目的 res/values/strings.xml 文件中，添加一个字符串资源来存储 Facebook App ID：

<resources>
    <string name="app_name">YourAppName</string>
    <string name="facebook_app_id">YOUR_FACEBOOK_APP_ID</string>
</resources>
将 YOUR_FACEBOOK_APP_ID 替换为你在 Facebook 开发者平台上获得的实际 App ID。

步骤 4: 添加 Facebook SDK 依赖项
确保你的项目中已添加 Facebook SDK 的依赖项。在 build.gradle 文件（通常是 app/build.gradle）中添加以下依赖：

groovy
dependencies {
    implementation 'com.facebook.android:facebook-android-sdk:[最新版本]'
}
或
kotlin
dependencies {
    implementation libs.facebook.android.sdk
}

请确保将 [最新版本] 替换为当前的 Facebook SDK 版本，可以在 Facebook SDK for Android 页面上找到最新的版本号。

步骤 5: 初始化 Facebook SDK
在你的应用的 MainActivity 或自定义的 Application 类中初始化 Facebook SDK：

java
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        //初始化
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main);
    }
}

要为您的 Android 应用添加开发和发布密钥哈希以进行 Facebook 集成，请按照以下步骤操作：

第一步：生成密钥哈希
您需要为开发和发布密钥生成密钥哈希。以下是具体步骤：

1. 生成开发密钥哈希
您可以使用 keytool 命令生成密钥哈希。该命令包含在 JDK 中。在终端中运行以下命令：

bash
keytool -exportcert -alias your_debug_key_alias -keystore path_to_your_debug_keystore | openssl sha1 -binary | openssl base64
your_debug_key_alias：通常对于调试构建是 androiddebugkey，但您可以在您的密钥库中检查具体的别名。
path_to_your_debug_keystore：对于调试构建，通常位于 ~/.android/debug.keystore。
默认情况下，调试密钥库的密码是 android。

例如，对于默认调试密钥库，您可以使用以下命令：

bash
keytool -exportcert -alias androiddebugkey -keystore ~/.android/debug.keystore | openssl sha1 -binary | openssl base64
2. 生成发布密钥哈希
对于发布密钥，您需要使用用于签名应用的密钥库。命令与调试密钥类似：

bash
keytool -exportcert -alias your_release_key_alias -keystore path_to_your_release_keystore | openssl sha1 -binary | openssl base64
请将 your_release_key_alias 和 path_to_your_release_keystore 替换为您的实际值。

第二步：将密钥哈希添加到 Facebook 应用
访问 Facebook for Developers 网站。
导航到您的应用。
在左侧边栏中，单击 设置，然后选择 基本设置。
向下滚动到 密钥哈希 部分。
添加您的开发和发布密钥哈希（每行一个）。
单击页面底部的 保存更改。
第三步：验证密钥哈希（可选）
您可以通过以下方法验证您的密钥哈希是否设置正确：

Facebook 登录：尝试在您的应用中使用 Facebook 登录，看看是否成功。
Logcat：检查 Logcat 中的任何错误，这可能表明密钥哈希存在问题。
密钥哈希示例
生成密钥哈希后，它看起来可能类似于以下内容：

开发密钥哈希：D6:F5:0A:9D:34:1B:7D:2F:8B:00:8C:1B:28:A6:C8:2B:3C:F0:89:C5:3F:9A:5B:0C:FF:A2:CE:23:4A:A6
发布密钥哈希：B3:74:27:F3:9C:A2:41:9E:07:FA:8B:89:27:92:58:6B:4D:80:10:EA:C7:EC:7F:1E:30:E8:5B:8D:7D:36:A7:FE
确保用您实际生成的哈希替换这些哈希。
