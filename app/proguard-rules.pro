#-Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in F:\WXL\Studio2.0\sdkLocation/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
#-dontwarn
#
#
#-optimizationpasses 5          # 指定代码的压缩级别
#-dontusemixedcaseclassnames   # 是否使用大小写混合
#-dontpreverify           # 混淆时是否做预校验
#-verbose                # 混淆时是否记录日志
#
#
#-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*  # 混淆时所采用的算法
#
#-keep public class * extends android.app.Activity      # 保持哪些类不被混淆
#-keep public class * extends android.app.Application   # 保持哪些类不被混淆
#-keep public class * extends android.app.Service       # 保持哪些类不被混淆
#-keep public class * extends android.content.BroadcastReceiver  # 保持哪些类不被混淆
#-keep public class * extends android.content.ContentProvider    # 保持哪些类不被混淆
#-keep public class * extends android.app.backup.BackupAgentHelper # 保持哪些类不被混淆
#-keep public class * extends android.preference.Preference        # 保持哪些类不被混淆
#-keep public class com.android.vending.licensing.ILicensingService    # 保持哪些类不被混淆
#-keep public class * extends android.widget.BaseAdapter
#
#-keepclasseswithmembernames class * {  # 保持 native 方法不被混淆
#    native <methods>;
#}
#-keepclasseswithmembers class * {   # 保持自定义控件类不被混淆
#    public <init>(android.content.Context, android.util.AttributeSet);
#}
#-keepclasseswithmembers class * {# 保持自定义控件类不被混淆
#    public <init>(android.content.Context, android.util.AttributeSet, int);
#}
#-keepclassmembers class * extends android.app.Activity { # 保持自定义控件类不被混淆
#    public void *(android.view.View);
#}
#-keepclassmembers enum * {     # 保持枚举 enum 类不被混淆
#    public static **[] values();
#    public static ** valueOf(java.lang.String);
#}
#-keep class * implements android.os.Parcelable { # 保持 Parcelable 不被混淆
#    public static final android.os.Parcelable$Creator *;
#}
#
#-dontwarn org.apache.**
#-keep class org.apache.** { *;}
#-dontwarn org.dom4j.**
#-keep class org.dom4j.** { *;}
##Warning:ytx.org.apache.http.impl.auth.SPNegoScheme: can't find referenced class org.ietf.jgss.Oid
#-dontwarn ytx.org.apache.**
#-keep class ytx.org.apache.** { *;}
##Warning:com.google.gson.jpush.internal.w: can't find referenced class com.google.gson.jpush.internal.w$com.google.gson.jpush.internal.y
#-dontwarn com.google.gson.**
#-keep class com.google.gson.** { *;}
#-dontwarn android.support.**
# -keep public class * extends android.view.View {
#     public <init>(android.content.Context);
#     public <init>(android.content.Context, android.util.AttributeSet);
#     public <init>(android.content.Context, android.util.AttributeSet, int);
#     public void set*(...);
# }
#   #过滤jar宝
#   #-dontwarn commons-net-3.3.jar.**
#   -keep class commons-net-3.3.jar.** { *; }
#   #-dontwarn jpush-android-2.1.3.jar.**
#   -keep class jpush-android-2.1.3.jar.** { *; }
# #  -dontwarn hellocharts-library-1.5.8.jar.**
#   -keep class hellocharts-library-1.5.8.jar.** { *; }
#   #-dontwarn mpermissions.jar.**
#   -keep class mpermissions.jar.** { *; }
#   -dontwarn core.jar.**
#   -keep class core.jar.** { *; }
#  # -dontwarn CCP_REST_SDK_JAVA_v2.7r.jar.**
#   -keep class CCP_REST_SDK_JAVA_v2.7r.jar.** { *; }
#   #-dontwarn aspectjrt.jar.**
#   -keep class aspectjrt.jar.** { *; }
#
# #申明第三方库，以免出现调用错误
##  -libraryjars libs/umeng-analytics-v5.2.4.jar
##  -libraryjars libs/jpush-android-2.1.3.jar
##  -libraryjars libs/hellocharts-library-1.5.8.jar
##  -libraryjars libs/mpermissions.jar
##  -libraryjars libs/core.jar
##  -libraryjars libs/commons-net-3.3.jar
##  -libraryjars libs/CCP_REST_SDK_JAVA_v2.7r.jar
##  -libraryjars libs/aspectjrt.jar
#
##  -libraryjars libs/gson-2.6.2.jar
##  -libraryjars libs/libammsdk.jar
##  -libraryjars libs/okhttp-3.2.0.jar
##  -libraryjars libs/okio-1.6.0.jar
##  -libraryjars libs/SimplifySpan.jar
##  -libraryjars libs/umeng-analytics-v5.6.4.jar
##  -libraryjars libs/universal-image-loader-1.9.5.jar
##  -libraryjars libs/UPPayAssistEx.jar
##  -libraryjars libs/UPPayPluginExStd.jar
#
#
# #友盟混淆
##    -dontwarn umeng-analytics-v5.2.4.jar.**
##    -keep class umeng-analytics-v5.2.4.jar.** {*; }
##-dontwarn com.umeng.**
##-keep class com.umeng*.** {*; }
#  #保持 Serializable 不被混淆
#  -keepnames class * implements java.io.Serializable
# #保持 Serializable 不被混淆并且enum 类也不被混淆
#  -keepclassmembers class * implements java.io.Serializable {
#      static final long serialVersionUID;
#      private static final java.io.ObjectStreamField[] serialPersistentFields;
#      !static !transient <fields>;
#      !private <fields>;
#      !private <methods>;
#      private void writeObject(java.io.ObjectOutputStream);
#      private void readObject(java.io.ObjectInputStream);
#      java.lang.Object writeReplace();
#      java.lang.Object readResolve();
#  }
#  #如果使用有Gson，则添加以下配置
#   -keepattributes Signature
#   -keepattributes *Annotation*
#   -keep class sun.misc.Unsafe { *; }
#
#
##Warning:library class com.hyphenate.chat.EMMipushReceiver extends or implements program class com.xiaomi.mipush.sdk.PushMessageReceiver
#   -dontwarn com.hyphenate.**
#   -dontwarn com.baidu.**
##   Warning:com.easemob.chat.EMPushNotificationHelper: can't find referenced class com.google.android.gms.gcm.GoogleCloudMessaging
#   -dontwarn com.easemob.chat.**
#
#



