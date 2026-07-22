# ProGuard 规则文件 - 两步验证应用

# 保留应用入口和数据模型
-keep class org.huahao.totp.** { *; }

# 保留 Compose 关键类（R8 已自动处理大部分）
-keepnames class * implements androidx.compose.runtime.Composable
-keepnames class * implements androidx.compose.ui.tooling.preview.Preview
-keepattributes Signature
-keepattributes *Annotation*

# 保留 ML Kit 条码扫描（使用反射和原生模型加载）
-keep class com.google.mlkit.** { *; }
-dontwarn com.google.mlkit.**

# 保留 CameraX
-keep class androidx.camera.** { *; }
-dontwarn androidx.camera.**

# 保留 ZXing
-keep class com.google.zxing.** { *; }
-dontwarn com.google.zxing.**

# 保留 DataStore
-keep class androidx.datastore.** { *; }
-dontwarn androidx.datastore.**

# Kotlin 序列化
-keepattributes kotlinx.serialization.SerializationConstructorMarker
-keepclassmembers class kotlinx.serialization.json.** { *** Companion; }
-keepclasseswithmembers class kotlinx.serialization.json.** { kotlinx.serialization.KSerializer serializer(...); }

# 移除调试信息
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int d(...);
    public static int i(...);
    public static int w(...);
    public static int e(...);
}

# 移除断言
-assumenosideeffects class kotlin.jvm.internal.Intrinsics {
    static void checkParameterIsNotNull(java.lang.Object, java.lang.String);
}