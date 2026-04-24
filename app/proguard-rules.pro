# ProGuard 规则文件 - 两步验证应用

# 保留数据模型类
-keep class com.huahao.authenticator.** { *; }

# 保留 Compose
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**
-keep class androidx.lifecycle.** { *; }
-dontwarn androidx.lifecycle.**

# 保留 ML Kit 条码扫描
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

# 保留 Kotlinx 序列化
-keep class kotlinx.serialization.** { *; }
-dontwarn kotlinx.serialization.**

# 保留 OkHttp（虽然代码中导入了但没实际使用，先保留以防万一）
-dontwarn okhttp3.**
-keep class okhttp3.** { *; }
-dontwarn okio.**
-keep class okio.** { *; }

# 优化选项
-optimizationpasses 5
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-allowaccessmodification
