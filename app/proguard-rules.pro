# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#一颗星表示只是保持该包下的类名，而子包下的类名还是会被混淆；
#-keep class pr.tongson.bean.*

#两颗星表示把本包和所含子包下的类名都保持；
#-keep class pr.tongson.bean.**

#（上面两种方式保持类后，会发现类名虽然未混淆，但里面的具体方法和变量命名还是变了）


#既可以保持该包下的类名，又可以保持类里面的内容不被混淆;
#-keep class pr.tongson.bean.*{*;}

#既可以保持该包及子包下的类名，又可以保持类里面的内容不被混淆;
#-keep class pr.tongson.bean.**{*;}

#保持某个类名不被混淆（但是内部内容会被混淆）
#-keep class pr.tongson.bean.KeyBoardBean
#保持某个类的 类名及内部的所有内容不会混淆
#-keep class pr.tongson.bean.KeyBoardBean{*;}

#保持类中特定内容，而不是所有的内容可以使用如下：
#-keep class pr.tongson.bean.KeyBoardBean{
#  #匹配所有构造器
#  <init>;
#  #匹配所有域
#  <fields>;
#  #匹配所有方法
#  <methods>;
#}
#（上面就保持住了KeyBoardBean这个类中的所有的构造方法、变量、和方法）

#可以在<fields>或<methods>前面加上private 、public、native等来进一步指定不被混淆的内容
#-keep class pr.tongson.algorithm.Calculate{
#  #保持该类下所有的共有方法不被混淆
#  public <methods>;
#  #保持该类下所有的共有内容不被混淆
#  public *;
#  #保持该类下所有的私有方法不被混淆
#  private <methods>;
#  #保持该类下所有的私有内容不被混淆
#  private *;
#  #保持该类的String类型的构造方法
#  public <init>(java.lang.String);
#}

#如果不需要保持类名，只需要保持该类下的特定方法保持不被混淆，需要使用keepclassmembers，而不是keep，因为keep方法会保持类名。
##保持ProguardTest类下test(String)方法不被混淆
#-keepclassmembernames class pr.tongson.algorithm.Calculate{
#  public void test(java.lang.String);
#}
#如果拥有某成员，保留类和类成员
#-keepclasseswithmembernames class pr.tongson.algorithm.Calculate


#jni方法不可混淆，因为native方法是要完整的包名类名方法名来定义的，不能修改，否则找不到；
##保持native方法不被混淆
#-keepclasseswithmembernames class * {
#  native <methods>;
#}

#建议：
# 发布一款应用除了设minifyEnabled为ture，你也应该设置zipAlignEnabled为true，像Google Play强制要求开发者上传的应用必须是经过zipAlign的，
# zipAlign可以让安装包中的资源按4字节对齐，这样可以减少应用在运行时的内存消耗。


#混淆情况记录
#例子中使用：classA和classB，在加混淆的情况下多种结果：
#如果classA没有被keep，则不会看到classA的class文件
#如果classA没有被keep，classB被保持，同时classB引用到了classA，这个时候能够看到被混淆的classA的class文件，如显示为a
#如果classA中通过反射，获取到classB，那么classB的类名及反射用到的方法必须keep住
#jar包混淆，暴露出的类、方法、方法的参数需要keep住
#情况说明：工程Demo依赖了小米渠道的依赖，小米依赖又依赖了Common，对Common进行混淆但是不对小米渠道混淆，那么小米的依赖中使用到的Common中的类都需要keep住



#-------------------------------------------基本不用动区域--------------------------------------------
#---------------------------------基本指令区----------------------------------
-optimizationpasses 5       # 指定代码的压缩级别
-dontusemixedcaseclassnames     # 是否使用大小写混合
-dontskipnonpubliclibraryclasses        # 指定不去忽略非公共的库类
-dontskipnonpubliclibraryclassmembers       # 指定不去忽略包可见的库类的成员
-dontpreverify      # 混淆时是否做预校验
-verbose        # 混淆时是否记录日志
-printmapping proguardMapping.txt
-optimizations !code/simplification/cast,!field/*,!class/merging/*      # 混淆时所采用的算法
-keepattributes *Annotation*,InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
#----------------------------------------------------------------------------
-ignorewarnings     # 是否忽略检测，（是）
#---------------------------------默认保留区---------------------------------
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {*;}
-keep class androidx.** {*;}
#-ignorewarnings -keep class * { public private *; }

#如果有引用v4包可以添加下面这行
-keep class android.support.v4.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment

-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
#表示不混淆R文件中的所有静态字段
-keep class **.R$* {
    public static <fields>;
}
-keepclassmembers class * {
    void *(**On*Event);
}
#----------------------------------------------------------------------------

#---------------------------------webview------------------------------------
-keepclassmembers class fqcn.of.javascript.interface.for.Webview {
   public *;
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, jav.lang.String);
}


###############################去掉所有打印
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** e(...);
    public static *** i(...);
    public static *** v(...);
    public static *** println(...);
    public static *** w(...);
    public static *** wtf(...);
}

#-assumenosideeffects class java.io.PrintStream {
#
#   public *** println(...);
#
#   public *** print(...);
#
#}
-assumenoexternalsideeffects class java.lang.StringBuilder {
    public java.lang.StringBuilder();
    public java.lang.StringBuilder(int);
    public java.lang.StringBuilder(java.lang.String);
    public java.lang.StringBuilder append(java.lang.Object);
    public java.lang.StringBuilder append(java.lang.String);
    public java.lang.StringBuilder append(java.lang.StringBuffer);
    public java.lang.StringBuilder append(char[]);
    public java.lang.StringBuilder append(char[], int, int);
    public java.lang.StringBuilder append(boolean);
    public java.lang.StringBuilder append(char);
    public java.lang.StringBuilder append(int);
    public java.lang.StringBuilder append(long);
    public java.lang.StringBuilder append(float);
    public java.lang.StringBuilder append(double);
    public java.lang.String toString();
}

-assumenoexternalreturnvalues public final class java.lang.StringBuilder {
    public java.lang.StringBuilder append(java.lang.Object);
    public java.lang.StringBuilder append(java.lang.String);
    public java.lang.StringBuilder append(java.lang.StringBuffer);
    public java.lang.StringBuilder append(char[]);
    public java.lang.StringBuilder append(char[], int, int);
    public java.lang.StringBuilder append(boolean);
    public java.lang.StringBuilder append(char);
    public java.lang.StringBuilder append(int);
    public java.lang.StringBuilder append(long);
    public java.lang.StringBuilder append(float);
    public java.lang.StringBuilder append(double);
}


#下面是第三方依赖的混淆设置
# -keep class   com.bumptech.glide.**
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}



# 友盟
-keep class com.umeng.** { *; }

-keep class com.uc.** { *; }

-keep class com.efs.** { *; }

-keepclassmembers class*{
     public<init>(org.json.JSONObject);
}
-keepclassmembers enum*{
      publicstatic**[] values();
      publicstatic** valueOf(java.lang.String);
}



-keep class  com.google.android.material.** { *; }
-keep class  me.jessyan.autosize.** { *; }
#-keep class  com.blankj.utilcode.**  { *; }
-keep class   com.youth.banner.** { *; }
-keep class   com.didichuxing.doraemonkit.** { *; }
-keep class   com.squareup.leakcanary.** { *; }

# 百度地图 混淆规则
-keep class com.baidu.** {*;}
-keep class vi.com.** {*;}
-keep class com.baidu.vi.** {*;}
-keep class mapsdkvi.com.gdi.bgl.android.java.** {*;}


-dontwarn com.baidu.*

-dontwarn org.xmlpull.v1.XmlPullParser
-dontwarn org.xmlpull.v1.XmlSerializer
-keep class org.xmlpull.v1.* {*;}
-keep class de.greenrobot.event.* {*;}
-keepclassmembers class ** {
    public void onEvent*(**);
}
# txt文本阅读
-keep class com.bifan.** {*;}

# pdf文本阅读
-keep class com.shockwave.**

# txt文本阅读
#-keep class com.blankj.utilcode.** {*;}

# glide混淆
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
#glide如果你的API级别<=Android API 27 则需要添加 4.6.1
-dontwarn com.bumptech.glide.load.resource.bitmap.VideoDecoder
-dontwarn me.iwf.photopicker.adapter.**


-keep class   com.ahyq.general.apkupdate.AppUpdateVersion { *; }
-keep class  com.ahyq.general.apkupdate.AppUpdateVersion$**{*;}


-keep class  com.awa.iSV3101.SerialPort{ *; }
-keep class  com.ahyq.sjj.bluetoothprint.print.PrintMsgEvent{ *; }



